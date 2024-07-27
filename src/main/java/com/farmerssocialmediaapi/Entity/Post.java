package com.farmerssocialmediaapi.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String datetime;

    private String caption;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String image;

    @OneToOne
    @JoinColumn(name = "farmerid")
    private Farmer farmer;

    @OneToMany(cascade = CascadeType.REMOVE , fetch = FetchType.LAZY , orphanRemoval = true , mappedBy = "post")
    @JsonIgnore
    @JsonManagedReference
    private List<Comments> commentsList;


}
