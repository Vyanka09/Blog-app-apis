package com.vyankatesh.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vyankatesh.blog.api.CategoryApi;
import com.vyankatesh.blog.payloads.CategoryDto;
import com.vyankatesh.blog.services.CategoryService;

@RestController
public class CategoryController implements CategoryApi{

	private CategoryService categoryService;
	
	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	@Override
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
	{
		CategoryDto newCategoryDto= categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(newCategoryDto,HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity<List<CategoryDto>> getAllCategories()
	{
		return ResponseEntity.ok(categoryService.getAllCategories());
	}
	
	@Override
	public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable int categoryId)
	{
		return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
	}
	
	@Override
	public ResponseEntity<CategoryDto> updateCategory(@PathVariable int categoryId,@Valid @RequestBody CategoryDto  categoryDto)
	{
		return ResponseEntity.ok(categoryService.updateCategory(categoryId, categoryDto));
	}
	
	@Override
	public ResponseEntity<String> deleteCategory(@PathVariable int categoryId)
	{
		return categoryService.deleteCategory(categoryId);
	}

}
