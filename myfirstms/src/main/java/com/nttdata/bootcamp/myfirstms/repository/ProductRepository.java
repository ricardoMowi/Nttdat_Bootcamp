package com.nttdata.bootcamp.myfirstms.repository;

import com.nttdata.bootcamp.myfirstms.model.dto.Product;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository <Product, String>{
    public Product findByIdClient();
}
