package com.furkan.tradeport.command;


import java.math.BigDecimal;
import java.util.Set;

public record CreateProductCommand (String categoryId,
                                    BigDecimal price,
                                    String currency,
                                    String brand,
                                    String productCode,
                                    String productName,
                                    String barcode,
                                    String description,
                                    Set<CreateProductVariantCommand> variants) {

}
