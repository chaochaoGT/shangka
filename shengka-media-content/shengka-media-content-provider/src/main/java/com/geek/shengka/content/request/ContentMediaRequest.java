package com.geek.shengka.content.request;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentMediaRequest {
	
	private String watchMode;
	private String categoryCode;
	private int pageSize;
    @NotNull
	private Integer pageNumber;
	private String lastIndexId;
	private String authorId;
    private String needCdnPrefix;

}
