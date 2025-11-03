package com.furkan.tradeport.dto;

import java.math.BigDecimal;
import java.util.Set;

public record CreateProductRequest (String categoryId,
                                    BigDecimal price,
                                    String currency,
                                    String brand,
                                    String productCode,
                                    String productName,
                                    String barcode,
                                    String description,
                                    Set<CreateProductVariantRequest> variants) {

}
