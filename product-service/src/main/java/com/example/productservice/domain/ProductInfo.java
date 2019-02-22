package com.example.productservice.domain;

import java.math.BigDecimal;

/**
 * Created by zhanglh on 2018/3/29.
 */
public class ProductInfo {

    private Integer ProductId;

    private String ProductName;

    private BigDecimal ProductPrice;

    private Integer ProductStock;

    private String ProductDescription;

    private String ProductIcon;

    private Integer CategoryType;

    public Integer getProductId() {
        return ProductId;
    }

    public void setProductId(Integer productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public BigDecimal getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        ProductPrice = productPrice;
    }

    public Integer getProductStock() {
        return ProductStock;
    }

    public void setProductStock(Integer productStock) {
        ProductStock = productStock;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public void setProductDescription(String productDescription) {
        ProductDescription = productDescription;
    }

    public Integer getCategoryType() {
        return CategoryType;
    }

    public void setCategoryType(Integer categoryType) {
        CategoryType = categoryType;
    }

    public String getProductIcon() {
        return ProductIcon;
    }

    public void setProductIcon(String productIcon) {
        ProductIcon = productIcon;
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "ProductId=" + ProductId +
                ", ProductName='" + ProductName + '\'' +
                ", ProductPrice=" + ProductPrice +
                ", ProductStock=" + ProductStock +
                ", ProductDescription='" + ProductDescription + '\'' +
                ", CategoryType=" + CategoryType +
                '}';
    }
}
