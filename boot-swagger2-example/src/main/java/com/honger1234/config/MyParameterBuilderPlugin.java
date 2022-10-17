package com.honger1234.config;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Optional;
import com.honger1234.annotation.ApiIgp;
import com.honger1234.annotation.ApiNeed;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.javassist.*;
import org.apache.ibatis.javassist.bytecode.AnnotationsAttribute;
import org.apache.ibatis.javassist.bytecode.ConstPool;
import org.apache.ibatis.javassist.bytecode.annotation.Annotation;
import org.apache.ibatis.javassist.bytecode.annotation.StringMemberValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ParameterBuilderPlugin;
import springfox.documentation.spi.service.contexts.ParameterContext;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 重写 swagger2 的 ParameterBuilderPlugin 支持自定义白名单黑名单注解
 * 局限：只能对post中的json数据生效，如果要对get的拼接参数请看MyOperationBuilderPlugin
 */
@Component
@Order
@Slf4j
public class MyParameterBuilderPlugin implements ParameterBuilderPlugin {
    @Autowired
    private TypeResolver typeResolver;

    @Override
    public void apply(ParameterContext parameterContext) {
        ResolvedMethodParameter methodParameter = parameterContext.resolvedMethodParameter();
        Class<?> originClass = parameterContext.resolvedMethodParameter().getParameterType().getErasedType();

        // 排除属性
        ApiIgp igpOptional = null;
        @SuppressWarnings("Guava")
        Optional<ApiIgp> apiIgpOptional = methodParameter.findAnnotation(ApiIgp.class);
        if ( apiIgpOptional.isPresent() ) {
            igpOptional = apiIgpOptional.get();
        }

        // 需要属性
        ApiNeed needOptional = null;
        @SuppressWarnings("Guava")
        Optional<ApiNeed> apiNeedOptional = methodParameter.findAnnotation(ApiNeed.class);
        if ( apiNeedOptional.isPresent() ) {
            needOptional = apiNeedOptional.get();
        }

        if (null != igpOptional || null != needOptional ) {
            Random random = new Random();
            //model 名称
            String name = originClass.getSimpleName() + "_" + UUID.randomUUID().toString().replace("-", "");
            try {
                // 排除 (黑名单)
                if ( null != igpOptional ) {
                    String[] properties = igpOptional.value();
                    parameterContext.getDocumentationContext()
                            .getAdditionalModels()
                            //向documentContext的Models中添加我们新生成的Class
                            .add(typeResolver.resolve(createRefModelIgp(properties, originClass.getPackage()+"."+name, originClass)));
                }
                // 需要 (白名单)
                if ( null != needOptional ) {
                    String[] properties = needOptional.value();
                    parameterContext.getDocumentationContext()
                            .getAdditionalModels()
                            //向documentContext的Models中添加我们新生成的Class
                            .add(typeResolver.resolve(createRefModelNeed(properties, originClass.getPackage()+"."+name, originClass)));
                }
            } catch (Exception e) {
                log.error("swagger切面异常", e);
            }
            //修改Map参数的ModelRef为我们动态生成的class
            parameterContext.parameterBuilder()
                    .parameterType("body")
                    .modelRef(new ModelRef(name))
                    .name(name);
        }

    }

    /**
     *  创建自定义mode给swagger2 排除参数
     * @param properties 需要排除的参数
     * @param name model 名称
     * @param origin originClass
     * @return r
     */
    private Class<?> createRefModelIgp(String[] properties, String name, Class<?> origin) {
        ClassPool pool = ClassPool.getDefault();
        // 动态创建一个class
        CtClass ctClass = pool.makeClass( name);
        try {
            Field[] fields = origin.getDeclaredFields();
            List<Field> fieldList = Arrays.asList(fields);
            List<String> ignoreProperties = Arrays.asList(properties);
            // 过滤掉 properties 的参数
            List<Field> dealFields = fieldList.stream().filter(s -> !ignoreProperties.contains(s.getName())).collect(Collectors.toList());
            addField2CtClass(dealFields, origin, ctClass);
            return ctClass.toClass();
        } catch (Exception e) {
            log.error("swagger切面异常", e);
            return null;
        }
    }

    /**
     *  创建自定义mode给swagger2 需要参数
     * @param properties 需要排除的参数
     * @param name model 名称
     * @param origin originClass
     * @return r
     */
    private Class<?> createRefModelNeed(String[] properties, String name, Class<?> origin) {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeClass( name);
        try {
            Field[] fields = origin.getDeclaredFields();
            List<Field> fieldList = Arrays.asList(fields);
            List<String> ignoreProperties = Arrays.asList(properties);
            // 过滤掉 非 properties 的参数
            List<Field> dealFields = fieldList.stream().filter(s -> ignoreProperties.contains(s.getName())).collect(Collectors.toList());
            addField2CtClass(dealFields, origin, ctClass);
            return ctClass.toClass();
        } catch (Exception e) {
            log.error("swagger切面异常", e);
            return null;
        }
    }

    private void addField2CtClass(List<Field> dealFields, Class<?> origin, CtClass ctClass ) throws NoSuchFieldException, NotFoundException, CannotCompileException {
        // 倒序遍历
        for (int i = dealFields.size() - 1; i >= 0; i--) {
            Field field = dealFields.get(i);
            CtField ctField = new CtField(ClassPool.getDefault().get(field.getType().getName()), field.getName(), ctClass);
            ctField.setModifiers(Modifier.PUBLIC);
            ApiModelProperty ampAnno = origin.getDeclaredField(field.getName()).getAnnotation(ApiModelProperty.class);
            String attributes = java.util.Optional.ofNullable(ampAnno).map(ApiModelProperty::value).orElse("");
            //添加model属性说明
            if (StringUtils.isNotBlank(attributes) ){
                ConstPool constPool = ctClass.getClassFile().getConstPool();
                AnnotationsAttribute attr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
                Annotation ann = new Annotation(ApiModelProperty.class.getName(), constPool);
                ann.addMemberValue("value", new StringMemberValue(attributes, constPool));
                attr.addAnnotation(ann);
                ctField.getFieldInfo().addAttribute(attr);
            }
            ctClass.addField(ctField);
        }
    }




    @Override
    public boolean supports(DocumentationType documentationType) {
        return true;
    }
}
