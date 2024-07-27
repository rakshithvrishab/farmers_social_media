package com.farmerssocialmediaapi.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    private String usertype;

    private String useremail;

    private String password;
}
