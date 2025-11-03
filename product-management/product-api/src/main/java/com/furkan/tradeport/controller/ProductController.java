package com.furkan.tradeport.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.furkan.tradeport.annotation.RequireRoles;
import com.furkan.tradeport.command.CreateProductCommand;
import com.furkan.tradeport.command.CreateProductVariantCommand;
import com.furkan.tradeport.dto.CreateProductRequest;
import com.furkan.tradeport.dto.CreateProductResponse;
import com.furkan.tradeport.dto.CreateProductVariantRequest;
import com.furkan.tradeport.dto.ProductDto;
import com.furkan.tradeport.dto.converter.ProductDtoConverter;
import com.furkan.tradeport.model.Product;
import com.furkan.tradeport.usecase.CreateProductUseCase;
import com.furkan.tradeport.usecase.ReadProductUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductDtoConverter converter;
    private final CreateProductUseCase createProductUseCase;
    private final ReadProductUseCase readProductUseCase;

    public ProductController(ProductDtoConverter converter, CreateProductUseCase createProductUseCase, ReadProductUseCase readProductUseCase) {
        this.converter = converter;
        this.createProductUseCase = createProductUseCase;
        this.readProductUseCase = readProductUseCase;
    }

    @RequireRoles("ROLE_ADMIN")
    @PostMapping("/create")
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody CreateProductRequest request) {
        CreateProductCommand createProductCommand = converter.toCreateProductCommand(request);
        String productId = createProductUseCase.create(createProductCommand);
        return ResponseEntity.ok(new CreateProductResponse(productId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> findAll() {
        List<ProductDto> productDtoList = readProductUseCase.findAllProducts().stream()
                .map(converter::toProductDto)
                .toList();
        return ResponseEntity.ok(productDtoList);
    }

}
