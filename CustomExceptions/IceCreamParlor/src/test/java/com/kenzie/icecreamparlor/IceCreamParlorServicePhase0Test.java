package com.kenzie.icecreamparlor;

import com.kenzie.icecreamparlor.exception.NoSuchFlavorException;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class IceCreamParlorServicePhase0Test {

    @Test
    public void getScoop_noSuchFlavor_throwsAmazonS3Exception() {
        // GIVEN
        IceCreamParlorService iceCreamParlorService = new IceCreamParlorService();

        // WHEN + THEN
        assertThrows(Exception.class,
                     () -> iceCreamParlorService.getScoop("unknownFlavor"),
                     "Expected getScoop to throw when requesting a scoop for a flavor that does not exist!");
    }

    @Test
    public void getSundae_noSuchFlavor_throwsNoSuchFlavorException() {
        // GIVEN
        IceCreamParlorService iceCreamParlorService = new IceCreamParlorService();

        // WHEN + THEN
        assertThrows(NoSuchFlavorException.class,
                     () -> iceCreamParlorService.getSundae(Collections.singletonList("unknownFlavor")),
                     "Expected getSundae to throw when requesting a flavor that does not exist!");
    }
}
