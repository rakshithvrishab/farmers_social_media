package com.farmerssocialmediaapi.Controller;

import com.farmerssocialmediaapi.Entity.Post;
import com.farmerssocialmediaapi.Repository.FarmerRepo;
import com.farmerssocialmediaapi.Repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@CrossOrigin("*")
@RequestMapping("/Post")
public class PostController {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private FarmerRepo farmerRepo;

    @GetMapping("/GetAllPosts")
    public ResponseEntity<?> GetAllPosts()
    {
        return new ResponseEntity<>(postRepo.findAll(), HttpStatus.OK);
    }

    @PostMapping("/AddPost/{farmerid}")
    public ResponseEntity<?> AddPost(@RequestBody Post post, @PathVariable Integer farmerid)
    {
        var farmer = farmerRepo.findById(farmerid).orElseThrow(()->new RuntimeException("Farmer not found"));
        post.setFarmer(farmer);

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        post.setDatetime(formattedDateTime);
        postRepo.save(post);
        return new ResponseEntity<>("Posted Successfully", HttpStatus.OK);
    }

    @PutMapping("/UpdatePost/{postid}")
    public ResponseEntity<?> UpdatePost(@RequestBody Post post, @PathVariable Integer postid)
    {
        var existingpost = postRepo.findById(postid).orElseThrow(()->new RuntimeException("Post not found"));
        existingpost.setImage(post.getImage());
        postRepo.save(existingpost);
        return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
    }

    @GetMapping("/GetMyPosts/{farmerid}")
    public ResponseEntity<?> GetMyPosts(@PathVariable Integer farmerid)
    {
        return new ResponseEntity<>(postRepo.findByFarmerId(farmerid), HttpStatus.OK);
    }


}
