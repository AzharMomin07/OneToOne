package com.onetoone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressRequest {

    private String address1 ;

    private String address2 ;

    private String state ;

    private String city ;


}
