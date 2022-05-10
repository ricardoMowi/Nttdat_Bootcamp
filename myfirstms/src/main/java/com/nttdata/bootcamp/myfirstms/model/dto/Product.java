package com.nttdata.bootcamp.myfirstms.model.dto;

import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(value="product")
@Getter
@Setter
@NoArgsConstructor

public class Product {
    @Id
    private String id;
    private String id_client;
    private Date creation_Date;
    private Boolean is_account;
    private Boolean is_credit;
    private int maximum_transaction_limit;
    private Double maintenance_commission;
    private Double amount;
    private String status;
    private String product_type;
}
