package com.kenzie.icecreamparlor.model;

import java.util.Objects;

public class Flavor {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flavor flavor = (Flavor) o;
        return Objects.equals(name, flavor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    private String name;

    public Flavor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Flavor{" +
                "name='" + name + '\'' +
                '}';
    }
}
