package com.farmerssocialmediaapi.Entity;

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
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int messageid;

    private String message;

    @OneToOne
    @JoinColumn(name = "farmerid")
    private Farmer farmer;

    @OneToOne
    @JoinColumn(name = "merchantid")
    private  Merchant merchant;

    private String sender;
}
