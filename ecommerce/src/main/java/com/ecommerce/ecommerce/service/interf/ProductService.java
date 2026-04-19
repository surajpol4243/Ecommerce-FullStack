package com.ecommerce.ecommerce.service.interf;

import com.ecommerce.ecommerce.dto.ProductDto;
import com.ecommerce.ecommerce.dto.Response;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Response createProduct(Long categoryId, String name, String description, BigDecimal price, String imgUrl);
    Response updateProduct(Long productId, Long categoryId, String imgUrl, String name, String description, BigDecimal price);
    Response deleteProduct(Long productId);
    Response getProductById(Long productId);
    Response getAllProducts();
    Response getProductsByCategory(Long categoryId);
    Response searchProduct(String searchValue);
    Response createBulkProducts(List<ProductDto> products);
}
