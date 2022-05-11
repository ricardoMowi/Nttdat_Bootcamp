package com.nttdata.bootcamp.myfirstms.controller;
import com.nttdata.bootcamp.myfirstms.model.dto.Client;
import com.nttdata.bootcamp.myfirstms.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("nttdata/client")
public class ClientController {
    @Autowired
    private ClientRepository client_repo;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createClient(@RequestBody Client new_client ){
        client_repo.save(new_client);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getAllClients(){
        return client_repo.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") String id, @RequestBody Client temp_client) {
      Optional<Client> client_doc = client_repo.findById(id);
      if (client_doc.isPresent()) {
        Client _client = client_doc.get();
        _client.setAddress(temp_client.getAddress());
        _client.setEmail(temp_client.getEmail());
        _client.setName(temp_client.getName());
        return new ResponseEntity<>(client_repo.save(_client), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") String id) {
      try {
        client_repo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    

    
}
