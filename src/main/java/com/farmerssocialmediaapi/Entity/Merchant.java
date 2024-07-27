package com.farmerssocialmediaapi.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String email;

    private String address;

    private String mobile;

    private String password;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String profilephoto;

    @OneToMany(mappedBy = "merchant" , cascade = CascadeType.ALL  , fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Biddings> biddings;
}
