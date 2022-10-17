package com.honger1234.config;

import com.fasterxml.classmate.ResolvedType;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.honger1234.annotation.ApiIgp;
import com.honger1234.annotation.ApiNeed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import springfox.documentation.builders.BuilderDefaults;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.service.Operation;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.EnumTypeDeterminer;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.spi.service.contexts.ParameterContext;
import springfox.documentation.spring.web.plugins.DocumentationPluginsManager;
import springfox.documentation.spring.web.readers.operation.OperationParameterReader;
import springfox.documentation.spring.web.readers.parameter.ExpansionContext;
import springfox.documentation.spring.web.readers.parameter.ModelAttributeParameterExpander;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.base.Predicates.not;
import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.schema.Collections.isContainerType;
import static springfox.documentation.schema.Maps.isMapType;
import static springfox.documentation.schema.Types.isBaseType;
import static springfox.documentation.schema.Types.typeNameFor;

/**
 * 重写 swagger2 的 ModelAttributeParameterExpander 支持get请求的自定义注解
 * 局限：json数据不生效
 */
@SuppressWarnings({"Guava", "rawtypes"})
@Component
@Order
@Slf4j
public class MyOperationBuilderPlugin extends OperationParameterReader implements OperationBuilderPlugin {

    private final EnumTypeDeterminer enumTypeDeterminer;
    private final ModelAttributeParameterExpander expander;

    private Boolean changed;

    @Autowired
    private DocumentationPluginsManager pluginsManager;

    @Autowired
    public MyOperationBuilderPlugin(ModelAttributeParameterExpander expander, EnumTypeDeterminer enumTypeDeterminer) {
        super(expander, enumTypeDeterminer);
        this.enumTypeDeterminer = enumTypeDeterminer;
        this.expander = expander;
    }


    /**
     * Implement this method to override the Operation using the OperationBuilder available in the context
     *
     * @param context - context that can be used to override the parameter attributes
     * @see Operation
     * @see OperationBuilder
     */
    @Override
    public void apply(OperationContext context) {
        changed = false;
        List<Parameter> parameters = readParameters(context);
        if (changed){
            // 反射给parameters赋值
            try {
                Field parametersField = OperationBuilder.class.getDeclaredField("parameters");
                parametersField.setAccessible(true);
                List<Parameter> source = BuilderDefaults.nullToEmptyList(parameters);
                parametersField.set(context.operationBuilder(), source);
            } catch ( Exception e ) {
                log.error("动态更改swagger参数错误", e);
            }
        }
        context.operationBuilder().parameters(context.getGlobalOperationParameters());
    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return super.supports(documentationType);
    }


    @SuppressWarnings("Guava")
    private List<Parameter> readParameters(final OperationContext context) {

        List<ResolvedMethodParameter> methodParameters = context.getParameters();
        List<Parameter> parameters = newArrayList();

        for (ResolvedMethodParameter methodParameter : methodParameters) {
            ResolvedType alternate = context.alternateFor(methodParameter.getParameterType());
            if (!shouldIgnore(methodParameter, alternate, context.getIgnorableParameterTypes())) {

                ParameterContext parameterContext = new ParameterContext(methodParameter,
                        new ParameterBuilder(),
                        context.getDocumentationContext(),
                        context.getGenericsNamingStrategy(),
                        context);

                List<Parameter> tempItems;

                if (shouldExpand(methodParameter, alternate)) {
                    tempItems = expander.expand(
                            new ExpansionContext("", alternate, context.getDocumentationContext()));
                } else {
                    tempItems = new ArrayList<>(Collections.singleton(pluginsManager.parameter(parameterContext)));
//                    tempItems = new ArrayList<>();
                }

                // 判断遍历 是否有自定义注解 有就进行操作
                Optional<ApiIgp> apiIgpOptional = methodParameter.findAnnotation(ApiIgp.class);
                if ( apiIgpOptional.isPresent() ) {
                    String[] properties = apiIgpOptional.get().value();
                    tempItems = tempItems.stream().filter(parameter -> {

                        for (String property : properties) {
                            // 匹配黑名单
                            if ( property.equals(parameter.getName())
                                    || parameter.getName().startsWith(property + ".") ) {
                                return false;
                            }
                        }
                        return true;
                    }).collect(Collectors.toList());
                    changed = true;
                }

                Optional<ApiNeed> apiNeedOptional = methodParameter.findAnnotation(ApiNeed.class);
                if ( apiNeedOptional.isPresent() ) {
                    String[] properties = apiNeedOptional.get().value();
                    tempItems = tempItems.stream().filter(parameter -> {
                        for (String property : properties) {
                            // 匹配 白名单
                            if ( property.equals(parameter.getName())
                                    || parameter.getName().startsWith(property + ".") ) {
                                return true;
                            }
                        }
                        return false;
                    }).collect(Collectors.toList());
                    changed = true;
                }

                parameters.addAll(tempItems);
            }
        }

        return FluentIterable.from(parameters).filter(not(hiddenParams())).toList();
    }

    private Predicate<Parameter> hiddenParams() {
        return Parameter::isHidden;
    }

    private boolean shouldIgnore(
            final ResolvedMethodParameter parameter,
            ResolvedType resolvedParameterType,
            final Set<Class> ignorableParamTypes) {

        if (ignorableParamTypes.contains(resolvedParameterType.getErasedType())) {
            return true;
        }
        return FluentIterable.from(ignorableParamTypes)
                .filter(isAnnotation())
                .filter(parameterIsAnnotatedWithIt(parameter)).size() > 0;

    }

    private Predicate<Class> parameterIsAnnotatedWithIt(final ResolvedMethodParameter parameter) {
        return parameter::hasParameterAnnotation;
    }

    private Predicate<Class> isAnnotation() {
        return Annotation.class::isAssignableFrom;
    }

    private boolean shouldExpand(final ResolvedMethodParameter parameter, ResolvedType resolvedParamType) {
        return !parameter.hasParameterAnnotation(RequestBody.class)
                && !parameter.hasParameterAnnotation(RequestPart.class)
                && !parameter.hasParameterAnnotation(RequestParam.class)
                && !parameter.hasParameterAnnotation(PathVariable.class)
                && !isBaseType(typeNameFor(resolvedParamType.getErasedType()))
                && !enumTypeDeterminer.isEnum(resolvedParamType.getErasedType())
                && !isContainerType(resolvedParamType)
                && !isMapType(resolvedParamType);

    }
}