package com.kenzie.icecreamparlor.model;

import java.util.List;

public class Sundae {

    List<Flavor> flavors;

    public Sundae(List<Flavor> flavors) {
        this.flavors = flavors;
    }

    public List<Flavor> getFlavors() {
        return flavors;
    }

    public void setFlavors(List<Flavor> flavors) {
        this.flavors = flavors;
    }
}
