package com.sparta.springcore;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class ProductController {

    private final ProductService productService;

    public ProductController() {
        ProductService productService = new ProductService();
        this.productService = productService;
    }

    // 신규 상품 등록
    @PostMapping("/api/products")
    //  요청 받기 -> createProduct(DTO)
    public Product createProduct(@RequestBody ProductRequestDto requestDto) throws SQLException {
        Product product = productService.createProduct(requestDto);

        // 응답 보내기
        return product;
    }

    // 설정 가격 변경(희망 최저가 myprice 변경)
    @PutMapping("/api/products/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto) throws SQLException {
        Product product = productService.productUpdate(id, requestDto); // 서비스에게 역할을 보냄

        // 응답 보내기 (업데이트된 상품 id)
        return product.getId();
    }

    // 등록된 전체 상품 목록 조회
    @GetMapping("/api/products")
    public List<Product> getProducts() throws SQLException {
        List<Product> products = productService.getProducts();



        // 응답 보내기
        return products;
    }
}