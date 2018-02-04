package com.learn.spring.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kartik
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class Address implements Serializable {
    private String street;

    private String city;

    private String state;

    private int zip;

    private String country;

}
