package com.honger1234.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PagerIDto {
	@ApiModelProperty(value = "页码", required = true, example = "1")
	private int page;

	@ApiModelProperty(value = "每页条数", required = true, example = "10")
	private int size;
}
