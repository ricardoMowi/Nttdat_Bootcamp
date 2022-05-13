package com.nttdata.bootcamp.myfirstms.model.dto;

import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection ="product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Product {
    @Id
    private String id;
    private String clientId;
    private Date creation_Date;
    private int maximum_transaction_limit;
    private Double maintenance_commission;
    private Double amount;
    private String productType;
    private String status;
    private String[] owners;
    private String[] authorized_signer;
}
