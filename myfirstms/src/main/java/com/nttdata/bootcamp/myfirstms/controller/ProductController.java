package com.nttdata.bootcamp.myfirstms.controller;
import com.nttdata.bootcamp.myfirstms.model.dto.Product;
import com.nttdata.bootcamp.myfirstms.repository.ProductRepository;

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
@RequestMapping("nttdata/product")
public class ProductController {
    @Autowired
    private ProductRepository product_repo;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createproduct(@RequestBody Product new_product ){
        product_repo.save(new_product);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllproducts(){
        return product_repo.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateproduct(@PathVariable("id") String id, @RequestBody product temp_product) {
      Optional<Product> product_doc = product_repo.findById(id);
      if (product_doc.isPresent()) {
        Product _product = product_doc.get();
        _product.setAddress(temp_product.getAddress());
        _product.setEmail(temp_product.getEmail());
        _product.setName(temp_product.getName());
        return new ResponseEntity<>(product_repo.save(_product), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") String id) {
      try {
        product_repo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    

    
}
