package com.beicroon.construct.base.entity;

import com.beicroon.construct.annotation.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coordinate implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    @ApiModelProperty(name = "经度")
    private BigDecimal lng;

    @ApiModelProperty(name = "纬度")
    private BigDecimal lat;

    public Coordinate(float lng, float lat) {
        this(BigDecimal.valueOf(lng), BigDecimal.valueOf(lat));
    }

    public Coordinate(double lng, double lat) {
        this(BigDecimal.valueOf(lng), BigDecimal.valueOf(lat));
    }

    public Coordinate(String lng, String lat) {
        this(new BigDecimal(lng), new BigDecimal(lat));
    }

    public String toLngLatString() {
        this.setScale();

        return String.format("%s,%s", this.lng.toPlainString(), this.lat.toPlainString());
    }

    public String toLatLngString() {
        this.setScale();

        return String.format("%s,%s", this.lat.toPlainString(), this.lng.toPlainString());
    }

    private void setScale() {
        if (this.lng != null) {
            this.lng = this.lng.setScale(6, RoundingMode.DOWN);
        }

        if (this.lat != null) {
            this.lat = this.lat.setScale(6, RoundingMode.DOWN);
        }
    }

}