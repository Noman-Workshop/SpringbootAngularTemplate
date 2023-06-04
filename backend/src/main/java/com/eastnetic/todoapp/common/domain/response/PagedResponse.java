package com.eastnetic.todoapp.common.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagedResponse <T> implements Serializable {
	
	private List<T> content;
	private int page;
	private int size;
	private int totalPages;
	private long totalItems;
	
}