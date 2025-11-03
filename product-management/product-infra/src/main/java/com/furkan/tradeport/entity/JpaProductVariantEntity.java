package com.furkan.tradeport.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product_variant")
public class JpaProductVariantEntity {

    @Id
    private String id;
    private String attributeKey;
    private String attributeValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private JpaProductEntity product;

    public JpaProductVariantEntity() {
    }

    public JpaProductVariantEntity(String id, String attributeKey, String attributeValue) {
        this.id = id;
        this.attributeKey = attributeKey;
        this.attributeValue = attributeValue;
    }

    public String getId() {
        return id;
    }

    public String getAttributeKey() {
        return attributeKey;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public JpaProductEntity getProduct() {
        return product;
    }

    public void setProduct(JpaProductEntity product) {
        this.product = product;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public void setAttributeKey(String attributeKey) {
        this.attributeKey = attributeKey;
    }
}
