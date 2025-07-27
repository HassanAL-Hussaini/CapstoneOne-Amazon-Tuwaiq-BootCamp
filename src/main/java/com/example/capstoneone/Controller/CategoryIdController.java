package com.example.capstoneone.Controller;

import com.example.capstoneone.Model.CategoryId;
import com.example.capstoneone.Service.CategoryIdService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryIdController {

    private final CategoryIdService categoryIdService;

    // Get all categories
    @GetMapping("/get")
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.ok(categoryIdService.getCategoryIdList());
    }

    // Add a new category
    @PostMapping("/add")
    public ResponseEntity<String> addCategory(@RequestBody @Valid CategoryId categoryId) {
        categoryIdService.addCategoryId(categoryId);
        return ResponseEntity.ok("Category added successfully");
    }

    // Update existing category by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable String id, @RequestBody @Valid CategoryId categoryId) {
        boolean isUpdated = categoryIdService.updateCategoryId(id, categoryId);
        if (isUpdated) {
            return ResponseEntity.ok("Category updated successfully");
        }
        return ResponseEntity.status(404).body("Category not found");
    }

    // Delete a category by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable String id) {
        boolean isDeleted = categoryIdService.deleteCategoryId(id);
        if (isDeleted) {
            return ResponseEntity.ok("Category deleted successfully");
        }
        return ResponseEntity.status(404).body("Category not found");
    }
}
