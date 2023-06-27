package com.kenzie.icecreamparlor.model;

public class Carton {

    private Flavor flavor;
    private int scoopsLeft;

    public Carton(Flavor flavor, int scoopsLeft) {
        this.flavor = flavor;
        this.scoopsLeft = scoopsLeft;
    }

    public Carton(Carton copyFrom) {
        this.flavor = copyFrom.flavor;
        this.scoopsLeft = copyFrom.scoopsLeft;
    }

    public Flavor getFlavor() {
        return flavor;
    }

    public int getScoopsLeft() {
        return scoopsLeft;
    }

    public void removeScoops(int numScoops) {
        if (scoopsLeft >= numScoops) {
            scoopsLeft -= numScoops;
        } else {
            throw new IllegalArgumentException(
                    String.format("Request for %d scoops is more than this carton contains!", numScoops));
        }
    }

    @Override
    public String toString() {
        return "Carton{" +
                "flavor=" + flavor +
                ", scoopsLeft=" + scoopsLeft +
                '}';
    }
}
