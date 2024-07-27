package com.farmerssocialmediaapi.Controller;

import com.farmerssocialmediaapi.Entity.Farmer;
import com.farmerssocialmediaapi.Entity.Products;
import com.farmerssocialmediaapi.Repository.FarmerRepo;
import com.farmerssocialmediaapi.Repository.ProductsRepo;
import com.fasterxml.jackson.datatype.jsr310.deser.key.OffsetTimeKeyDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@CrossOrigin("*")
@RequestMapping("/Products")
public class ProductsController {
    @Autowired
    private FarmerRepo farmerRepo;

    @Autowired
    private ProductsRepo productsRepo;

    @PostMapping("/AddProducts/{farmerid}")
    public ResponseEntity<?> AddProducts(@RequestBody Products products, @PathVariable Integer farmerid)
    {
        var existingFarmer = farmerRepo.findById(farmerid).orElseThrow(()->new RuntimeException("Farmer not found"));
        products.setFarmer(existingFarmer);

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        products.setDatetime(formattedDateTime);
        products.setStatus("unsold");

        productsRepo.save(products);
        return new ResponseEntity<>("Product Added Successfully", HttpStatus.OK);
    }

    @GetMapping("/GetAllProducts")
    public ResponseEntity<?> GetAllProducts()
    {
        return new ResponseEntity<>(productsRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/GetMyProducts/{farmerid}")
    public ResponseEntity<?> GetMyProducts(@PathVariable Integer farmerid)
    {
        return new ResponseEntity<>(productsRepo.findByFarmerId(farmerid), HttpStatus.OK);
    }


    @PutMapping("/UpdateStatus/{productid}")
    public ResponseEntity<?> UpdateStatus(@PathVariable Integer productid)
    {
        var product = productsRepo.findById(productid).orElseThrow(()->new RuntimeException("Product not found"));
        product.setStatus("sold");
        productsRepo.save(product);
        return new ResponseEntity<>("Product Status Updated Successfully", HttpStatus.OK);
    }
}
