package com.learn.spring.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Manifest data
 *
 * @author km185223
 */
@Data
@NoArgsConstructor
@ApiModel
public class ManifestData implements Serializable {
    private String currencyCode;

    private String totalValue;

    private List<PackageData> packages = new ArrayList<>();
}
