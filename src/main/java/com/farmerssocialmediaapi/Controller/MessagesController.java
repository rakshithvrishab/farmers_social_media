package com.farmerssocialmediaapi.Controller;

import com.farmerssocialmediaapi.Entity.Messages;
import com.farmerssocialmediaapi.Repository.FarmerRepo;
import com.farmerssocialmediaapi.Repository.MerchantRepo;
import com.farmerssocialmediaapi.Repository.MessagesRepo;
import org.aspectj.weaver.ResolvedPointcutDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/Messages")
public class MessagesController {

    @Autowired
    private FarmerRepo farmerRepo;

    @Autowired
    private MerchantRepo merchantRepo;

    @Autowired
    private MessagesRepo messagesRepo;

    @PostMapping("/SendMessage/{farmerid}/{merchantid}")
    public ResponseEntity<?> SendMessage(@PathVariable Integer farmerid, @PathVariable Integer merchantid, @RequestBody Messages messages)
    {
        var farmer = farmerRepo.findById(farmerid).orElseThrow(()->new RuntimeException("Farmer not found"));
        var merchant = merchantRepo.findById(merchantid).orElseThrow(()->new RuntimeException("Merchant not found"));

        messages.setFarmer(farmer);
        messages.setMerchant(merchant);
        messagesRepo.save(messages);

        return new ResponseEntity<>("Mesaage Sent Successfully", HttpStatus.OK);
    }

    @GetMapping("/GetMessages/{farmerid}/{merchantid}")
    public ResponseEntity<?> GetMessage(@PathVariable Integer farmerid, @PathVariable Integer merchantid)
    {
        return new ResponseEntity<>(messagesRepo.findByFarmerIdAndMerchantId(farmerid,merchantid),HttpStatus.OK);
    }
}
