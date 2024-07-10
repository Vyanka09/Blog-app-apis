package com.vyankatesh.blog.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vyankatesh.blog.entity.Category;
import com.vyankatesh.blog.exceptions.CategoryNotFound;
import com.vyankatesh.blog.payloads.CategoryDto;
import com.vyankatesh.blog.repository.CategoryRepository;
import com.vyankatesh.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	private CategoryRepository categoryRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
		
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		Category category=categoryDtoToCategory(categoryDto);
		categoryRepository.save(category);
		return categoryToCategoryDto(category);
	}


	@Override
	public List<CategoryDto> getAllCategories() {
		// TODO Auto-generated method stub
		List<Category> categoryList=categoryRepository.findAll();
		return categoryList.stream().map(category->categoryToCategoryDto(category)).collect(Collectors.toList());
	}

	@Override
	public CategoryDto getCategoryById(int id) {
		// TODO Auto-generated method stub
		Category optCategory= categoryRepository.findById(id).orElseThrow(()->new CategoryNotFound("Category"
				,"Id",id));
		
		return categoryToCategoryDto(optCategory);
		
		
	}

	@Override
	public CategoryDto updateCategory(int id, CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		Category category= categoryRepository.findById(id).orElseThrow(()->new CategoryNotFound("Category","Id",id));
		Category updatedCategory=categoryDtoToCategory(categoryDto);
		updatedCategory.setCategoryId(id);
		categoryRepository.save(updatedCategory);
		return categoryToCategoryDto(updatedCategory);
	}

	@Override
	public ResponseEntity<String> deleteCategory(int id) {
		// TODO Auto-generated method stub
		Category category= categoryRepository.findById(id).orElseThrow(()->new CategoryNotFound("Category","Id",id));
		categoryRepository.delete(category);
		return new ResponseEntity<String>("Category deleted successfully with id "+id,HttpStatus.OK);
	}
	
	public CategoryDto categoryToCategoryDto(Category category)
	{
		return modelMapper.map(category, CategoryDto.class);
	}
	
	public Category categoryDtoToCategory(CategoryDto categoryDto)
	{
		return modelMapper.map(categoryDto, Category.class);
	}

}
