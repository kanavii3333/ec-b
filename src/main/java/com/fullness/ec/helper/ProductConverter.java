package com.fullness.ec.helper;
import com.fullness.ec.entity.Product;
import com.fullness.ec.entity.ProductCategory;
import com.fullness.ec.entity.ProductStock;
import com.fullness.ec.form.CategoryForm;
import com.fullness.ec.form.ProductForm;

public class ProductConverter {
    public static Product convertToEntity(ProductForm productForm, byte[] imageByte){
        Product product = new Product();
        product.setProductName(productForm.getProductName());
        product.setPrice(productForm.getPrice());
        product.setImageUrl(ImageUploadHelper.uploadFile(productForm.getFile().getOriginalFilename(), imageByte));
        product.setProductCategory(new ProductCategory());
        product.getProductCategory().setProductCategoryId(productForm.getCategoryId());
        product.setProductStock(new ProductStock());
        product.getProductStock().setQuantity(productForm.getQuantity());
        return product;
    }
    
    public static ProductCategory convertFormCategoryToEntity(CategoryForm categoryForm){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(categoryForm.getProductCategoryId());
        productCategory.setProductCategoryName(categoryForm.getProductCategoryName());
        return productCategory;
    }
}