package com.nttdata.bootcamp.myfirstms.repository;

import java.util.List;

import com.nttdata.bootcamp.myfirstms.model.dto.Product;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository <Product, String>{
    List<Product> findByClientId(String clientId);
    List<Product> findByProductTypeAndStatus(String ProductType, String Status);
    //Long countProductTypeAndStatus(String ProductType, String Status);
}
