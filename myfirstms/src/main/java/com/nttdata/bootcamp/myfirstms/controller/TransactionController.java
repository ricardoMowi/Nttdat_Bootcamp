package com.nttdata.bootcamp.myfirstms.controller;
import com.nttdata.bootcamp.myfirstms.model.dto.Transaction;
import com.nttdata.bootcamp.myfirstms.repository.TransactionRepository;

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
@RequestMapping("nttdata/transaction")
public class TransactionController {
    @Autowired
    private TransactionRepository transaction_repo;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createTransaction(@RequestBody Transaction new_transaction ){
        transaction_repo.save(new_transaction);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> getAllDocs(){
        return transaction_repo.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable("id") String id, @RequestBody Transaction temp_trans) {
      //Map<String, Object> salida = new HashMap<>();
      Optional<Transaction> transaction_doc = transaction_repo.findById(id);
      if (transaction_doc.isPresent()) {
        Transaction _transaction = transaction_doc.get();
        _transaction.setAmount(temp_trans.getAmount());
        return new ResponseEntity<>(transaction_repo.save(_transaction), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } 

    @DeleteMapping("delete/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deleteClient(@PathVariable("id") String id) {
      Map<String, Object> salida = new HashMap<>();
      try {
        Optional<Transaction> transaction_doc = transaction_repo.findById(id);
        if (transaction_doc.isPresent()) {
          transaction_repo.deleteById(id);
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

    
}
