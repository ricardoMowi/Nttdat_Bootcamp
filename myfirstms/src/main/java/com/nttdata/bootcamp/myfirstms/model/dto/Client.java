package com.nttdata.bootcamp.myfirstms.model.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(value="client")
@Getter
@Setter
@NoArgsConstructor

public class Client {
    @Id
    private String id;
    private String client_type;
    private String name;
    private String last_name;
    private String RUC;
    private String address;
    private String email;
    private String status;
}
