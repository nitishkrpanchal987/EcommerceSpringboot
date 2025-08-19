package com.practice.ecommercePrac.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.ecommercePrac.exceptions.AlreadyExistsException;
import com.practice.ecommercePrac.model.Category;
import com.practice.ecommercePrac.response.ApiResponse;
import com.practice.ecommercePrac.service.category.ICategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api.prefix}/categories")
@RequiredArgsConstructor
public class CategroyController {
    private final ICategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCategories(){
        try {
            List<com.practice.ecommercePrac.model.Category> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(new ApiResponse("found!!", categories));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category category){
        try {
            Category savedCategory = categoryService.addCategory(category);
            return ResponseEntity.ok(new ApiResponse("Successfully added", savedCategory));
        } catch (AlreadyExistsException e) {
            // TODO Auto-generated catch block
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Error: "+e.getMessage(), HttpStatus.CONFLICT));
        }
    }

    @GetMapping("/{id}/category")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id){
        try {
            Category getCategory = categoryService.getCategoryById(id);
            return ResponseEntity.ok(new ApiResponse("Found", getCategory));
        } catch (AlreadyExistsException e) {
            // TODO Auto-generated catch block
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), HttpStatus.NOT_FOUND));
        }
    }

    @GetMapping("/{name}/category")
    public ResponseEntity<ApiResponse> getCategoryByName(@PathVariable String name){
        try {
            Category getCategory = categoryService.getCategoryByName(name);
            return ResponseEntity.ok(new ApiResponse("Found", getCategory));
        } catch (AlreadyExistsException e) {
            // TODO Auto-generated catch block
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), HttpStatus.NOT_FOUND));
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ApiResponse> deleteCategoryById(@PathVariable Long id){
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok(new ApiResponse("Successfully deleted", null));
        } catch (AlreadyExistsException e) {
            // TODO Auto-generated catch block
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), HttpStatus.NOT_FOUND));
        }
    }
}
