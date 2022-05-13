package com.nttdata.bootcamp.myfirstms.repository;

import java.util.List;

import com.nttdata.bootcamp.myfirstms.model.dto.Transaction;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository <Transaction, String> {
    List<Transaction> findByIdProduct (String IdProduct);
}
