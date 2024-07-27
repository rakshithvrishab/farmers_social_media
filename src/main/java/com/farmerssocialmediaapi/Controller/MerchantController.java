package com.farmerssocialmediaapi.Controller;

import com.farmerssocialmediaapi.Entity.Farmer;
import com.farmerssocialmediaapi.Entity.Merchant;
import com.farmerssocialmediaapi.Repository.MerchantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/Merchant")
public class MerchantController {
    @Autowired
    private MerchantRepo merchantRepo;

    @PostMapping("/AddMerchant")
    public ResponseEntity<String> AddMerchant(@RequestBody Merchant merchant)
    {
        if (merchantRepo.existsByEmail(merchant.getEmail()))
        {
            throw new RuntimeException("Email already registered");
        }
        else {
            merchantRepo.save(merchant);
            return new ResponseEntity<>("Merchant Added Successfully", HttpStatus.CREATED);
        }

    }

    @GetMapping("/GetAllMerchants")
    public ResponseEntity<?> GetAllMerchants()
    {
        return new ResponseEntity<>(merchantRepo.findAll(), HttpStatus.OK);
    }


    @PutMapping("/UploadProfilePhoto/{id}")
    public ResponseEntity<String> UploadProfilePhoto(@RequestBody Farmer farmer, @PathVariable Integer id)
    {
        var existingmerchant = merchantRepo.findById(id).orElseThrow(()->new RuntimeException("Farmer not found"));
        existingmerchant.setProfilephoto(farmer.getProfilephoto());
        merchantRepo.save(existingmerchant);
        return new ResponseEntity<>("Profile Photo Uploaded", HttpStatus.OK);

    }

    @GetMapping("/GetProfileImage/{id}")
    public ResponseEntity<?> GetProfilePhoto(@PathVariable Integer id)
    {
        String file = merchantRepo.findById(id).orElseThrow(()->new RuntimeException("Profile not found")).getProfilephoto();
        return new ResponseEntity<>(file, HttpStatus.OK);

    }
}
