package com.furkan.tradeport.controller;

import com.furkan.tradeport.annotation.RequireRoles;
import com.furkan.tradeport.command.CreateCategoryCommand;
import com.furkan.tradeport.command.UpdateCategoryCommand;
import com.furkan.tradeport.dto.CreateCategoryRequest;
import com.furkan.tradeport.dto.UpdateCategoryRequest;
import com.furkan.tradeport.model.Category;
import com.furkan.tradeport.usecase.CreateCategoryUseCase;
import com.furkan.tradeport.usecase.DeleteCategoryUseCase;
import com.furkan.tradeport.usecase.ReadCategoryUseCase;
import com.furkan.tradeport.usecase.UpdateCategoryUseCase;
import com.furkan.tradeport.valueobject.CategoryId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final ReadCategoryUseCase readCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;

    public CategoryController(CreateCategoryUseCase createCategoryUseCase,
                              ReadCategoryUseCase readCategoryUseCase,
                              UpdateCategoryUseCase updateCategoryUseCase,
                              DeleteCategoryUseCase deleteCategoryUseCase) {
        this.createCategoryUseCase = createCategoryUseCase;
        this.readCategoryUseCase = readCategoryUseCase;
        this.updateCategoryUseCase = updateCategoryUseCase;
        this.deleteCategoryUseCase = deleteCategoryUseCase;
    }

    @RequireRoles("ROLE_ADMIN")
    @PostMapping("/create")
    public ResponseEntity<Category> createCategory(@RequestBody CreateCategoryRequest createCategoryRequest) {
        Category result = createCategoryUseCase.create(new CreateCategoryCommand(createCategoryRequest.getName(),
                createCategoryRequest.getParentId()));
        return ResponseEntity.ok().body(result);
    }

    @RequireRoles("ROLE_ADMIN")
    @PutMapping("/update/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable("categoryId") String categoryId,
                                                   @RequestBody UpdateCategoryRequest request) {
        UpdateCategoryCommand command = new UpdateCategoryCommand(
                request.getCategoryName(),
                request.getParentId()
        );

        return ResponseEntity.ok(updateCategoryUseCase.update(categoryId,command));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok().body(readCategoryUseCase.findAllCategories());
    }

    @GetMapping("{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable("categoryId") String id) {
        CategoryId categoryId = CategoryId.of(id);
        return ResponseEntity.ok().body(readCategoryUseCase.findCategoryById(categoryId));
    }

    @RequireRoles("ROLE_ADMIN")
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String>  deleteCategory(@PathVariable("categoryId") String id) {
        CategoryId categoryId = CategoryId.of(id);
        deleteCategoryUseCase.deleteCategory(categoryId);
        return ResponseEntity.ok().body("Category deleted");
    }
}
