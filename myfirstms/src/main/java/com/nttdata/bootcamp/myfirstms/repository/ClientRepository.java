package com.nttdata.bootcamp.myfirstms.repository;
import com.nttdata.bootcamp.myfirstms.model.dto.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends  MongoRepository<Client, String>{
    
}
