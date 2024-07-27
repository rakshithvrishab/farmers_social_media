package com.farmerssocialmediaapi.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String comment;

    @OneToOne
    @JoinColumn(name = "farmerid")
    private Farmer farmer;

    private String datetime;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "postid")
    @JsonBackReference
    private Post post;

}
