package com.honger1234.controller;

import com.honger1234.annotation.ApiIgp;
import com.honger1234.annotation.ApiNeed;
import com.honger1234.entity.PagerIDto;
import com.honger1234.entity.UseInfo;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/v1/users")
@Api(value = "User API接口", tags = {"User API接口"})
public class UserController {

	@ApiOperation(value = "用户登录", notes = "用户登录接口")
	@ApiResponses({
			@ApiResponse(code = 0, message = "success"),
			@ApiResponse(code = 10001, message = "用户名错误", response = IllegalArgumentException.class),
			@ApiResponse(code = 10002, message = "密码错误")
	})
	@PostMapping(value = "/login")
	public String login(@ApiParam(name = "username", value = "用户名", required = true) @RequestParam String username,
						@ApiParam(name = "password", value = "密码", required = true) @RequestParam String password){
		return "{'username':'" + username + "', 'password':'" + password + "'}";
	}


	@ApiOperation(value = "修改用户信息", notes = "修改用户信息")
	@ApiImplicitParams({
			@ApiImplicitParam(dataTypeClass = String.class, paramType = "header", name = "phone", required = true, value = "手机号"),
			@ApiImplicitParam(dataTypeClass = String.class, paramType = "query", name = "nickname", required = true, value = "nickname", defaultValue = "双击666"),
			@ApiImplicitParam(dataTypeClass = String.class, paramType = "path", name = "platform", required = true, value = "平台", defaultValue = "PC"),
			@ApiImplicitParam(dataTypeClass = String.class, paramType = "body", name = "password", required = true, value = "密码")
	})
	@PutMapping(value = "/{platform}/regist")
	public String regist(@RequestHeader String phone, @RequestParam String nickname, @PathVariable String platform, @RequestBody String password){
		return "{'username':'" + phone + "', 'nickname':'" + nickname + "', 'platform': '" + platform + "', 'password':'"+password+"'}";
	}


	@ApiOperation(value = "用户列表", notes = "查询用户列表")
	@GetMapping(value = "/list")
	public String getUserList(PagerIDto pager){
		return "[{'id': "+pager.getPage()+", 'username': 'zhangsan"+pager.getSize()+"'}]";
	}

	@ApiOperation(value = "删除用户", notes = "删除用户")
	@DeleteMapping("/{id}")
	public String removeUser(@PathVariable Long id){
		System.out.println(id);
		return "success";
	}

	@ApiOperation(value = "排除属性注解")
	@GetMapping("/swaggerTest1")
	public String swaggerTest1(@ApiIgp ({"password"})@RequestBody UseInfo useInfo){
		return "success";
	}

	@ApiOperation(value = "只需要属性注解")
	@GetMapping("/swaggerTest2")
	public String swaggerTest2(@ApiNeed({"username","age"}) UseInfo useInfo){
		return "success";
	}

	@ApiIgnore
	@RequestMapping("/ignoreApi")
	public String ignoreApi(){
		return "docs";
	}
}