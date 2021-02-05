package com.geek.shengka.content.service.recommand.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ShortVideoRecommandParam extends SmallVideoRecommandParam{
	private Long categoryId;	//频道id
}
