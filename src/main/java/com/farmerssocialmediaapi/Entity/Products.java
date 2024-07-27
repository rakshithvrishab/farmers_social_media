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
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String productname;

    private String datetime;

    private String description;

    private int productrate;

    private int quantity;

    private String status;

    @Lob
    @Column(columnDefinition="LONGTEXT")
    private String image;

    @OneToOne(cascade = CascadeType.ALL)
    private Farmer farmer;

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Biddings> biddings;
}
