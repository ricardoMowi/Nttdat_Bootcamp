package com.nttdata.bootcamp.myfirstms.controller;
import com.nttdata.bootcamp.myfirstms.model.dto.Client;
import com.nttdata.bootcamp.myfirstms.model.dto.Product;
import com.nttdata.bootcamp.myfirstms.repository.ClientRepository;
import com.nttdata.bootcamp.myfirstms.repository.ProductRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("nttdata/product")
public class ProductController {
    @Autowired
    private ProductRepository product_repo;
    private ClientRepository client_repo;

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
    public ResponseEntity<Product> updateproduct(@PathVariable("id") String id, @RequestBody Product temp_product) {
      Optional<Product> product_doc = product_repo.findById(id);
      if (product_doc.isPresent()) {
        Product _product = product_doc.get();
        _product.setAmount(temp_product.getAmount());
        return new ResponseEntity<>(product_repo.save(_product), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("delete/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deleteProduct(@PathVariable("id") String id) {
      Map<String, Object> salida = new HashMap<>();
      try {
        Optional<Product> client_doc = product_repo.findById(id);
        if (client_doc.isPresent()) {
          product_repo.deleteById(id);
          salida.put("mensaje", "Eliminado :)");
          //return ResponseEntity.ok(salida);
        }else{
          salida.put("mensaje", "Id no encontrado");
        }
      } catch (Exception e) {
        e.printStackTrace();
        salida.put("mensaje", "error");
      }
      return ResponseEntity.ok(salida);
    }



    /////////////////////
    @PostMapping("createCurrentAccount")
    @ResponseBody
    public ResponseEntity <Map<String, Object>> createCurrentAccount(@RequestBody Product new_product ){

      Map<String, Object> salida = new HashMap<>();
      //campos obligatorios
      new_product.setIs_account(true);
      new_product.setIs_credit(false);

      
      //Identificar y validar cliente tipo de cliente
      Optional<Client> client_doc = client_repo.findById(new_product.getId_client());
        if (client_doc.isPresent()) {
          //cliente identificado   
          Client temp = client_doc.get();
          if(temp.getIs_person() == true){
            //si es persona, solo una cuenta de ahorro

          }
          product_repo.save(new_product);
          salida.put("mensaje", "Cuenta creada");
        }else{
          salida.put("mensaje", "Id de cliente no encontrado");
        }
        return ResponseEntity.ok(salida);
    }
    
    @PostMapping("createSavingAccount")
    @ResponseStatus(HttpStatus.OK)
    public void createSavingAccount(@RequestBody Product new_produc ){
      //Identificar tipo de cuenta
      //Identificar tipo de cliente
      product_repo.save(new_produc);
    }

    @PostMapping("createFixedTermAccount")
    @ResponseStatus(HttpStatus.OK)
    public void createFixedTermAccount(@RequestBody Product new_produc ){
      //Identificar tipo de cuenta
      //Identificar tipo de cliente
      product_repo.save(new_produc);
    }

    
}
