package com.nttdata.bootcamp.myfirstms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nttdata.bootcamp.myfirstms.model.dto.Client;
import com.nttdata.bootcamp.myfirstms.model.dto.Product;
import com.nttdata.bootcamp.myfirstms.repository.ClientRepository;
import com.nttdata.bootcamp.myfirstms.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("microservices")
public class MicroservicesController {

    @Autowired
    private ProductRepository pro_repo;
    @Autowired
    private ClientRepository client_repo;


    //Clase interna para validar cliente y cuentas
    public HashMap<String, Object> validateClient(String id) {        
        HashMap<String, Object> map = new HashMap<>();
        Optional<Client> client_doc = client_repo.findById(id);
        if (client_doc.isPresent()) {
            //Existe, obtener cantidad de cuentas
            List <Product> Products_1 = pro_repo.findByProductTypeAndClientId("SAVING_ACCOUNT",id);  
            List <Product> Products_2 = pro_repo.findByProductTypeAndClientId("CURRENT_ACCOUNT",id);  
            List <Product> Products_3 = pro_repo.findByProductTypeAndClientId("FIXED_TERM_ACCOUNT",id);  
            int Q_1 =  Products_1.size();
            int Q_2 =  Products_2.size();
            int Q_3 =  Products_3.size();
            
            //Armar hashmap
            map.put("message", "Id de cliente encontrado");
            map.put("cant_cuenta_ahorro", Q_1);
            map.put("cant_cuenta_corriente", Q_2);
            map.put("cant_cuenta_plazo_fijo", Q_3);
        }else{
            map.put("message", "Id de cliente no encontrado");
        }
        return map;
    }


    //Clase interna para crear cuenta del tipo cuenta ahorro
    public HashMap<String, Object> createCurrentAccount(@RequestBody Product new_product ){
        HashMap<String, Object> map = new HashMap<>();
        try{
            new_product.setMaintenanceCommission(0.0); 
            map.put("account", new_product);
            pro_repo.save(new_product);
        }catch(Exception e) {
            e.printStackTrace();
            map.put("mensaje", "error");
        }                    
        return map;
    }
    //Clase interna para crear cuenta del tipo cuenta corriente
    public HashMap<String, Object> createSavingAccount(@RequestBody Product new_product ){
        HashMap<String, Object> map = new HashMap<>();
        try{
            new_product.setMaximumTransactionLimit(0); 
            map.put("account", new_product);
            pro_repo.save(new_product);
        }catch(Exception e) {
            e.printStackTrace();
            map.put("mensaje", "error");
        }             
        return map;
    }

    //Clase interna para crear cuenta del tipo cuenta plazo fijo
    public HashMap<String, Object> createFixedTermAccount(@RequestBody Product new_product ){
        HashMap<String, Object> map = new HashMap<>();
        try{
            new_product.setMaintenanceCommission(0.0); 
            map.put("account", new_product);
            pro_repo.save(new_product);
        }catch(Exception e) {
            e.printStackTrace();
            map.put("mensaje", "error");
        }                    
        return map;
    }


    //Microservicio para crear cuentas
    @PostMapping("createProduct")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> createProduct(@RequestBody Product new_product){
      Map<String, Object> salida = new HashMap<>();      
      HashMap<String, Object> data_client = validateClient(new_product.getClientId());  
      String message = (data_client.get("message")).toString();

      if(message == "Id de cliente no encontrado"){
        salida.put("message", "Id de cliente no encontrado");  
      }else{

        String productType = new_product.getProductType();
        if(productType == "CURRENT_ACCOUNT"){
            HashMap<String, Object> create_product_a = createCurrentAccount(  new_product );
            salida.put("ouput", create_product_a);
        }else if(productType == "SAVING_ACCOUNT"){
            HashMap<String, Object> create_product_b = createSavingAccount(  new_product );
            salida.put("ouput", create_product_b);
        }else if(productType == "FIXED_TERM_ACCOUNT"){
            HashMap<String, Object> create_product_c = createFixedTermAccount(  new_product );
            salida.put("ouput", create_product_c);
        }
      }            
      return ResponseEntity.ok(salida);
    }

}
