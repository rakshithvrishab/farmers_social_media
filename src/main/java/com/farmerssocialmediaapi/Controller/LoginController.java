package com.farmerssocialmediaapi.Controller;

import com.farmerssocialmediaapi.Dto.LoginDto;
import com.farmerssocialmediaapi.Repository.FarmerRepo;
import com.farmerssocialmediaapi.Repository.MerchantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/Login")
public class LoginController {
    @Autowired
    private FarmerRepo farmerRepo;
    @Autowired
    private MerchantRepo merchantRepo;

    @PostMapping("/LoginVerify")
    public ResponseEntity<?> LoginVerify(@RequestBody LoginDto loginDto)
    {
        if(loginDto.getUsertype().equals("Farmer"))
        {
            var farmer = farmerRepo.findByEmail(loginDto.getUseremail()).orElseThrow(()->new RuntimeException("User not found"));
            if(farmer.getPassword().equals(loginDto.getPassword()))
            {
                return new ResponseEntity<>(farmer,HttpStatus.OK);
            }
            else
            {
                throw new RuntimeException("Invalid Password");
            }
        }
        else if(loginDto.getUsertype().equals("Merchant"))
        {
            var merchant = merchantRepo.findByEmail(loginDto.getUseremail()).orElseThrow(()->new RuntimeException("User not found"));
            if(merchant.getPassword().equals(loginDto.getPassword()))
            {
                return new ResponseEntity<>(merchant,HttpStatus.OK);
            }
            else
            {
                throw new RuntimeException("Invalid Password");
            }
        }
        else
        {
            throw new RuntimeException("Invalid Usertype");
        }
    }
}
