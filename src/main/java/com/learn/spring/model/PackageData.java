package com.learn.spring.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Package data
 *
 * @author km185223
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class PackageData implements Serializable {
    private String name;

    private double weight;

    private double height;

    private boolean fragile;
}
