package com.eastnetic.todoapp.common.util;

import com.eastnetic.todoapp.common.domain.response.PagedResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PageUtil {
	
	public static <T, R> PagedResponse<R> paginate(Page<T> data, Function<T, R> mapper) {
		List<R> content = data.stream()
		                      .map(mapper)
		                      .collect(Collectors.toList());
		
		return PagedResponse.<R>builder()
		                    .content(content)
		                    .page(data.getNumber() + 1)
		                    .size(data.getSize())
		                    .totalPages(data.getTotalPages())
		                    .totalItems(data.getTotalElements())
		                    .build();
	}
	
	/**
	 * Create a sort object from the query string
	 *
	 * @param sortsQuery the query string
	 *
	 * @return the sort object
	 */
	public static Sort getSort(String[] sortsQuery) {
		// TODO validate the sort query based on the entity fields
		if (sortsQuery == null || sortsQuery.length == 0) {
			return Sort.unsorted();
		}
		
		List<Order> orders = Stream.of(sortsQuery)
		                           .map(query -> query.split(","))
		                           .filter(query -> query.length == 2)
		                           .map(order -> Order.by(order[0].trim())
		                                              .with(Sort.Direction.fromString(order[1].trim())))
		                           .collect(Collectors.toList());
		return Sort.by(orders);
	}
	
}
