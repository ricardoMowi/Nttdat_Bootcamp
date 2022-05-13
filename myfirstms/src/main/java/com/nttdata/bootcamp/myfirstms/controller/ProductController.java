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
    @Autowired
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


    // @GetMapping("getByClient/{id}")
    // @ResponseStatus(HttpStatus.OK)
    // public List<Product> getByClient(@PathVariable("id") String id){
    //     return product_repo.findByClientId(id);
    // }    


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


    ///Microservicio 0: GetDataClient 
    @GetMapping("GetDataClient/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> GetDataClient(@PathVariable("id") String id){
      Map<String, Object> salida = new HashMap<>();

      //Validar id del cliente
      Optional<Client> client_doc = client_repo.findById(id);
      if (client_doc.isPresent()) {
        //data del cliente
        salida.put("client", client_doc);
        //obtener cantidad de productos
        List <Product> Products_1 = product_repo.findByProductTypeAndStatus("SAVING_ACCOUNT","ACTIVE");  
        List <Product> Products_2 = product_repo.findByProductTypeAndStatus("CURRENT_ACCOUNT","ACTIVE"); 
        List <Product> Products_3 = product_repo.findByProductTypeAndStatus("FIXED_TERM_ACCOUNT","ACTIVE"); 
        int Q_1 =  Products_1.size();
        int Q_2 =  Products_2.size();
        int Q_3 =  Products_3.size();
        //prepara data para enviar
        salida.put("cant_cuenta_ahorro", Q_1);
        salida.put("cant_cuenta_corriente", Q_2);
        salida.put("cant_cuenta_plazo_fijo", Q_3);
      }else{
        salida.put("status", "Id de Cliente no encontrado");
      }
      return ResponseEntity.ok(salida);
    }

}
