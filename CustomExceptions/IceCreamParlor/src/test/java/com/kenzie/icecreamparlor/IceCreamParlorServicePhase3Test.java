package com.kenzie.icecreamparlor;

import com.kenzie.icecreamparlor.exception.NoSuchFlavorException;

import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class IceCreamParlorServicePhase3Test {
    private static final String UNKNOWN_FLAVOR = "unknownFlavor";


    @Test
    public void getScoop_noSuchFlavor_throwsResourceNotFoundException() {
        // GIVEN
        IceCreamParlorService iceCreamParlorService = new IceCreamParlorService();

        try {
            // WHEN
            iceCreamParlorService.getScoop(UNKNOWN_FLAVOR);
            fail("Expected getScoop to throw when requesting a scoop for a flavor that does not exist!");
        } catch (Exception e) {
            // THEN
            if (e instanceof AmazonS3Exception) {
                // Corresponds to Phase 3 specifically, where S3CartonDao should be replaced with DynamoDbCartonDao
                fail("Expected ResourceNotFoundException when requesting a scoop for a flavor that does not exist!");
            } else if (!(e instanceof ResourceNotFoundException) && !(e instanceof NoSuchFlavorException)) {
                // Corresponds to later phases, where you update getScoop to translate the exception in the same
                // way getSundae does already
                fail("Expected getScoop to throw DynamoDB or IceCreamParlorService-specific exception when " +
                         "requesting a scoop for a flavor that does not exist!");
            }
        }
    }

    @Test
    public void getSundae_noSuchFlavor_throwsNoSuchFlavorException() {
        // GIVEN
        IceCreamParlorService iceCreamParlorService = new IceCreamParlorService();

        // WHEN + THEN
        assertThrows(NoSuchFlavorException.class,
                     () -> iceCreamParlorService.getSundae(Collections.singletonList(UNKNOWN_FLAVOR)),
                     "Expected getSundae to throw when requesting a flavor that does not exist!");
    }
}
