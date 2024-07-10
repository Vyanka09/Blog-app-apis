package com.vyankatesh.blog.payloads;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponsePagination {

	private List<PostResponseDto> content;
	private int pageNumber;
	private int pageSize;
	private long totalPages;
	private long totalElements;
	private boolean lastPage;
}
