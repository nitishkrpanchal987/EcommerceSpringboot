package com.practice.ecommercePrac.service.category;

import com.practice.ecommercePrac.exceptions.AlreadyExistsException;
import com.practice.ecommercePrac.exceptions.CategoryNotFoundException;
import com.practice.ecommercePrac.model.Category;
import com.practice.ecommercePrac.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{
    private final CategoryRepository categoryRepository;
    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException("Category not found!!"));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return Optional.of(category).filter(c -> !categoryRepository.existsByName(c.getName()))
                .map(categoryRepository::save).orElseThrow(()-> new AlreadyExistsException("Category already exists!!"));
    }

    @Override
    public Category updateCategory(Category category, Long id) {
        return Optional.ofNullable(getCategoryById(id)).map(oldCategory -> {
            oldCategory.setName(category.getName());
            return categoryRepository.save(oldCategory);
        }).orElseThrow(()-> new CategoryNotFoundException("Category not found!!"));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.findById(id).ifPresentOrElse(categoryRepository::delete, ()-> {throw new CategoryNotFoundException("Category not found!!");});
    }
}
