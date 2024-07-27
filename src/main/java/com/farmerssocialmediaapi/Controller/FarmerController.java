package com.farmerssocialmediaapi.Controller;

import com.farmerssocialmediaapi.Entity.Farmer;
import com.farmerssocialmediaapi.Repository.FarmerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/Farmer")
public class FarmerController {
@Autowired
 private FarmerRepo farmerRepo;

    @PostMapping("/AddFarmer")
    public ResponseEntity<String> AddFarmer(@RequestBody Farmer farmer)
    {
        if (farmerRepo.existsByEmail(farmer.getEmail()))
        {
            throw new RuntimeException("Email id already registered");
        }
        else {
            farmerRepo.save(farmer);
            return new ResponseEntity<>("Farmer Added Successfully", HttpStatus.CREATED);
        }

    }

    @PutMapping("/UploadProfilePhoto/{id}")
    public ResponseEntity<String> UploadProfilePhoto(@RequestBody Farmer farmer, @PathVariable Integer id)
    {
        var existingfarmer = farmerRepo.findById(id).orElseThrow(()->new RuntimeException("Farmer not found"));
        existingfarmer.setProfilephoto(farmer.getProfilephoto());
        farmerRepo.save(existingfarmer);
        return new ResponseEntity<>("Profile Photo Uploaded", HttpStatus.OK);

    }

    @GetMapping("/GetProfileImage/{id}")
    public ResponseEntity<?> GetProfilePhoto(@PathVariable Integer id)
    {
        String file = farmerRepo.findById(id).orElseThrow(()->new RuntimeException("Profile not found")).getProfilephoto();
        return new ResponseEntity<>(file, HttpStatus.OK);

    }

    @GetMapping("/GetAllFarmers")
    public ResponseEntity<?> GetAllFarmers()
    {
        return new ResponseEntity<>(farmerRepo.findAll(), HttpStatus.OK);
    }
}
