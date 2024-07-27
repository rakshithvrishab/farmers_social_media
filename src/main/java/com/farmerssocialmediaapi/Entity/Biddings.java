package com.farmerssocialmediaapi.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Biddings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int bidamount;

    private LocalDate bidDate;

    @ManyToOne()
    @JsonBackReference
    private Products products;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})   //to fetch data overriding lazy property
    private Merchant merchant;
}
