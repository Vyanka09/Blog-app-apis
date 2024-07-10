package com.vyankatesh.blog.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.vyankatesh.blog.payloads.CategoryDto;

public interface CategoryService {

	//Create category
	CategoryDto createCategory(CategoryDto categoryDto);
	//Get all categories
	List<CategoryDto> getAllCategories();
	
	//Get Single Category
	CategoryDto getCategoryById(int id);
	
	//Update category
	CategoryDto updateCategory(int id, CategoryDto categoryDto);
	
	//Delete Category
	ResponseEntity<String> deleteCategory(int id);
}
