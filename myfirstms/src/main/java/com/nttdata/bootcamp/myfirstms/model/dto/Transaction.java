package com.nttdata.bootcamp.myfirstms.model.dto;

import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(value="transaction")
@Getter
@Setter
@NoArgsConstructor

public class Transaction {
    @Id
    private String id;
    private Date register_date;
    private String idProduct;
    private Double amount;
    private String transaction_type;
    private String status;
}
