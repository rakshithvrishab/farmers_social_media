package com.farmerssocialmediaapi.Controller;

import com.farmerssocialmediaapi.Entity.Biddings;
import com.farmerssocialmediaapi.Repository.BiddingsRepo;
import com.farmerssocialmediaapi.Repository.MerchantRepo;
import com.farmerssocialmediaapi.Repository.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/Biddings")
public class BiddingController {
    @Autowired
    private BiddingsRepo biddingsRepo;

    @Autowired
    private MerchantRepo merchantRepo;

    @Autowired
    private ProductsRepo productsRepo;


    @PostMapping("/AddBidding/{merchantId}/{productId}")
    public ResponseEntity<?> createNewBidding(
            @RequestBody Biddings biddings,
            @PathVariable Integer merchantId,
            @PathVariable Integer productId
    ) {
        if (biddingsRepo.existsByMerchantIdAndProductsId(merchantId, productId)) {
            throw new RuntimeException("Already bidded to this product");
        } else {
            var merchant = merchantRepo.findById(merchantId).orElseThrow(() -> new RuntimeException("Merchant not found"));
            var product = productsRepo.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
            biddings.setBidDate(LocalDate.now());
            biddings.setMerchant(merchant);
            biddings.setProducts(product);
            biddingsRepo.save(biddings);
            return new ResponseEntity<>("Bidded Successfully", HttpStatus.CREATED);
        }
    }

    @GetMapping("/GetAllBiddings")
    public ResponseEntity<?> retrieveAllBiddings() {
        return new ResponseEntity<>(biddingsRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/GetSingleBiddings/{biddingId}")
    public ResponseEntity<?> retrieveSingleBidding(@PathVariable Integer biddingId) {

        Biddings existingBids = biddingsRepo.findById(biddingId).orElseThrow(() -> new RuntimeException("Bidding not found"));
        return new ResponseEntity<>(existingBids, HttpStatus.OK);
    }

    @DeleteMapping("/DeleteBidding/{biddingId}")
    public ResponseEntity<?> removeBidding(@PathVariable Integer biddingId) {
        var bid = biddingsRepo.findById(biddingId).orElseThrow(()->new RuntimeException("Bid not found"));
        biddingsRepo.delete(bid);
        return new ResponseEntity<>("Bidding removed successfully!!", HttpStatus.OK);
    }

    @GetMapping("/GetAllMerchantBids/{merchantId}")
    public ResponseEntity<?> retrieveAllBiddingByMerchant(@PathVariable Integer merchantId) {

        var merchant = merchantRepo.findById(merchantId).orElseThrow(()->new RuntimeException("Merchant not found"));

        return new ResponseEntity<>(biddingsRepo.findByMerchant(merchant), HttpStatus.OK);
    }

    @GetMapping("/GetByProducts/{productId}")
    public ResponseEntity<?> retrieveAllBiddingByProducts(@PathVariable Integer productId) {

        var product = productsRepo.findById(productId).orElseThrow(()->new RuntimeException("Product not found"));
        return new ResponseEntity<>(biddingsRepo.findByProducts(product), HttpStatus.OK);
    }

    @GetMapping("/GetById/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id)
    {
        var bidlist = biddingsRepo.findById(id).orElseThrow(()->new RuntimeException("Not biddings found")).getProducts();
        return new ResponseEntity<>(bidlist, HttpStatus.OK);
    }
}
