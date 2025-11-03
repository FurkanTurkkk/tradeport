package com.furkan.tradeport.dto;

import java.math.BigDecimal;

public record ProductDto(String id,
        String categoryId,
        BigDecimal price,
        String currency,
        String brand,
        String productCode,
        String productName,
        String barcode,
        String description) {
}
