package com.sparta.springcore;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private final ProductRepository productRepository;
    public ProductService() {
        ProductRepository productRepository = new ProductRepository();  //  사용을 위해 객체 생성
        this.productRepository = productRepository;
        //  혹은 위의 두줄을 this.productRepository = new ProductRepository; 한줄로 대체 가능


    }

    public Product createProduct(ProductRequestDto requestDto) throws SQLException {
// 요청받은 DTO 로 DB에 저장할 객체 만들기
        Product product = new Product(requestDto);

        productRepository.createProduct(product);   //requestDto 말고 product를 넘겨주는 이유: 멤버변수가 더 있음

        return product;
    }

    public Product productUpdate(Long id, ProductMypriceRequestDto requestDto) throws SQLException {
        if(requestDto.getMyprice() <= 0){
            throw new NullPointerException("희망 최저가는 0원 이상으로 해주세요");
        }

        Product product = productRepository.getProduct(id);

        if (product == null) {   //  서비스에서 해줘야 할 일이라고 보자
            throw new NullPointerException("해당 아이디가 존재하지 않습니다.");
        }

        productRepository.updateMyPrice(id, requestDto.getMyprice());   // id와 requestDto에 있는 Myprice를 가지고 업데이트

        return product;
    }

    public List<Product> getProducts() throws SQLException {
        List<Product> products = productRepository.getProducts();    // 레포지토리로 일감 넘겨줌


        return products;

    }
}
