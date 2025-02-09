package com.smashingmods.reroll.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("dimension")
public class DimensionObject {

    @JsonProperty("id")
    private final int id;
    private SpiralObject spiral;

    @JsonCreator
    public DimensionObject(@JsonProperty("id") int id) {
        this.id = id;
        spiral = new SpiralObject();
    }

    public int getId() {
        return id;
    }

    public void setSpiral(SpiralObject spiral) {
        this.spiral = spiral;
    }

    public SpiralObject getSpiral() {
        return spiral;
    }
}
