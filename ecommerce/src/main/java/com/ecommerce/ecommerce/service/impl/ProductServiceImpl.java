package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.dto.ProductDto;
import com.ecommerce.ecommerce.dto.Response;
import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.entity.Product;
import com.ecommerce.ecommerce.exception.NotFoundException;
import com.ecommerce.ecommerce.mapper.EntityDtoMapper;
import com.ecommerce.ecommerce.repository.CategoryRepo;
import com.ecommerce.ecommerce.repository.ProductRepo;
import com.ecommerce.ecommerce.service.interf.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final EntityDtoMapper entityDtoMapper;

    @Override
    public Response createProduct(Long categoryId, String name, String description, BigDecimal price, String imgUrl) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new NotFoundException("Category not found"));

        Product product = new Product();
        product.setCategory(category);
        product.setPrice(price);
        product.setName(name);
        product.setDescription(description);
        product.setImageUrl(imgUrl);

        productRepo.save(product);
        return Response.builder()
                .status(200)
                .message("Product created successfully.")
                .build();
    }

    @Override
    public Response updateProduct(Long productId, Long categoryId, String imgUrl, String name, String description, BigDecimal price) {
        Product product = productRepo.findById(productId).orElseThrow(()-> new NotFoundException("Product Not found"));

        Category category = null;

        if(categoryId != null){
            category = categoryRepo.findById(categoryId).orElseThrow(()->new NotFoundException("Category not found"));
        }

       if(category != null) product.setCategory(category);
       if(name != null) product.setName(name);
       if(price != null) product.setPrice(price);
       if(description != null) product.setDescription(description);
       if(imgUrl != null) product.setImageUrl(imgUrl);

       productRepo.save(product);

        return Response.builder()
                .status(200)
                .message("Product updated successfully")
                .build();
    }

    @Override
    public Response deleteProduct(Long productId) {
        Product product = productRepo.findById(productId).orElseThrow(()-> new NotFoundException("Product not found")) ;
        productRepo.delete(product);

        return Response.builder()
                .status(200)
                .message("Product deleted successfully")
                .build();
    }

    @Override
    public Response getProductById(Long productId) {
        Product product = productRepo.findById(productId).orElseThrow(()-> new NotFoundException("Product not found"));
        ProductDto productDto = entityDtoMapper.mapProductToDtoBasic(product);

        return Response.builder()
                .status(200)
                .product(productDto)
                .build();
    }

    @Override
    public Response getAllProducts() {
        List<ProductDto> productList = productRepo.findAll(Sort.by(Sort.Direction.DESC,"id")).stream()
                .map(entityDtoMapper::mapProductToDtoBasic)
                .collect(Collectors.toList());
        return Response.builder()
                .status(200)
                .productDtoList(productList)
                .build();
    }

    @Override
    public Response getProductsByCategory(Long categoryId) {
        List<Product> products = productRepo.findByCategoryId(categoryId);
        if(products.isEmpty()){
            throw new NotFoundException("Not Products found for this category");
        }

        List<ProductDto> productDtoList = products.stream()
                .map(entityDtoMapper::mapProductToDtoBasic)
                .collect(Collectors.toList());

        return Response.builder()
                .status(200)
                .productDtoList(productDtoList)
                .build();
    }

    @Override
    public Response searchProduct(String searchValue) {
        return null;
    }

    @Override
    public Response createBulkProducts(List<ProductDto> products) {
        List<Product> productList  = products.stream().map(p ->{
            Category category = categoryRepo.findById(p.getCategoryDto().getId())
                    .orElseThrow(()-> new NotFoundException("Category Id " + p.getCategoryDto().getId() + " Not found"));

            Product product = new Product();
            product.setName(p.getName());
            product.setDescription(p.getDescription());
            product.setPrice(p.getPrice());
            product.setImageUrl(p.getImageUrl());
            product.setCategory(category);

            return product;
        }).toList();

        productRepo.saveAll(productList);
        return  Response.builder()
                .status(200)
                .message("Bulk products created: " + productList.size())
                .build();
    }
}
