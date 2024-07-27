package com.farmerssocialmediaapi.Controller;

import com.farmerssocialmediaapi.Entity.Comments;
import com.farmerssocialmediaapi.Repository.CommentsRepo;
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
@RequestMapping("/Comments")
public class CommentsController {
    @Autowired
    private CommentsRepo commentsRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private FarmerRepo farmerRepo;

    @GetMapping("/GetCommentsByPost/{postid}")
    public ResponseEntity<?> GetCommentsByPost(@PathVariable Integer postid)
    {
        return new ResponseEntity<>(commentsRepo.findByPostId(postid), HttpStatus.OK);
    }

    @PostMapping("/AddComment/{postid}/{farmerid}")
    public ResponseEntity<?> AddComment(@RequestBody Comments comments,@PathVariable Integer postid, @PathVariable Integer farmerid )
    {
        var farmer = farmerRepo.findById(farmerid).orElseThrow(()->new RuntimeException("Farmer not found"));
        var existingpost = postRepo.findById(postid).orElseThrow(()->new RuntimeException("Post not found"));

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        comments.setFarmer(farmer);
        comments.setPost(existingpost);
        comments.setDatetime(formattedDateTime);
        commentsRepo.save(comments);

        return new ResponseEntity<>("Comment Posted Successfully",HttpStatus.OK);
    }

    @DeleteMapping("/Delete/{postid}")
    public ResponseEntity<?> DeletePost(@PathVariable Integer postid)
    {
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }

}
