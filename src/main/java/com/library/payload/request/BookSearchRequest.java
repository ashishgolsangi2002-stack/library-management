package com.library.payload.request;

import java.util.List;

import com.library.payload.response.PageResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookSearchRequest{
	private String searchTerm;
	private Long genreId;
	private Boolean availableOnly;
	private Integer page=0;
	private Integer size=20;
	private String sortBy="createdAt";
	private String sortDirection="DESC";
}