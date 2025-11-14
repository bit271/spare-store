package com.bit.partsstore.services;

import com.bit.partsstore.DTO.CategoryRequest;
import com.bit.partsstore.DTO.CategoryResponse;
import com.bit.partsstore.models.Category;
import com.bit.partsstore.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private static final String CATEGORY_ALREADY_EXISTS = "Category already exists";
    private static final String CATEGORY_DOESNT_FOUND = "Category doesn't found";
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryResponse> getCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public CategoryResponse addCategory(CategoryRequest request) {
        if (categoryRepository.existsByName(request.getName())) {
            throw new RuntimeException(CATEGORY_ALREADY_EXISTS);
        }
        Category category = createCategoryFromRequest(request);
        Category saved = categoryRepository.save(category);
        return mapToResponse(saved);
    }

    public Category deleteCategory(int id) {
        return categoryRepository.findById(id)
                .map(category -> {
                    categoryRepository.delete(category);
                    return category;
                })
                .orElseThrow(() -> new RuntimeException(CATEGORY_DOESNT_FOUND));
    }

    private Category createCategoryFromRequest(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        return category;
    }

    private CategoryResponse mapToResponse(Category category) {
        return new CategoryResponse(category.getId(), category.getName());
    }

}
