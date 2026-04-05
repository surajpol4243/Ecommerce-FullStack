package com.ecommerce.ecommerce.dto;

import com.ecommerce.ecommerce.entity.Category;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private int status;
    private String message;
    private final LocalDateTime timeStamp = LocalDateTime.now();

    private String token;
    private String role;
    private String expriationTime;

    private int totalPage;
    private long totalElement;

    private AddressDto address;

    private UserDto user;
    private List<UserDto> userList;

    private ProductDto product;
    private List<ProductDto> productDtoList;

    private CategoryDto category;
    private List<CategoryDto> categroyList;

    private OrderDto order;
    private List<OrderDto> orderDtoList;

    private OrderItemDto orderItem;
    private List<OrderItemDto> orderItemDtoList;


}
