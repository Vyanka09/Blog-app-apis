package com.vyankatesh.blog.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vyankatesh.blog.payloads.CategoryDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="Categories Api")
@RequestMapping("/api/categories")
public interface CategoryApi {

	@ApiOperation(value="Api for adding category")
	@PostMapping("")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto);
	
	@ApiOperation(value="Api for retrieving all categories")
	@GetMapping("")
	public ResponseEntity<List<CategoryDto>> getAllCategories();
	
	@ApiOperation(value="Api for reteriving single category by category id")
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable int categoryId);
	
	@ApiOperation(value="Api for updating single category by category id")
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@PathVariable int categoryId,@Valid @RequestBody CategoryDto  categoryDto);
	
	@ApiOperation(value="Api for deleting category by category id")
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable int categoryId);
}
