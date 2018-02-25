package com.learn.spring.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Quote to be saved in database
 *
 * @author km185223
 */
@Data
@Entity
@ApiModel(value="Quotes", description="Sample model for the documentation")
public class Quote implements Serializable {

//    private Address pickupAddress;
//
//    private Address dropoffAddress;
//
//    private Date pickupTime;
//
//    private Date dropoffTime;
//
//    private ManifestData manifestData;\

    @Id
//    @Id @GeneratedValue(generator="system-uuid")
//    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private String name;

    private int cost;
}
