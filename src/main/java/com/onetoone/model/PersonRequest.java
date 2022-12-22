package com.onetoone.model;

import com.onetoone.entity.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest extends PersonResponse {

    private String firstName ;

    private String lastName ;

    private AddressRequest address ;
}
