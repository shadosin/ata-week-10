package com.kenzie.icecreamparlor;

import com.kenzie.icecreamparlor.dao.DynamoDBCartonDao;
import com.kenzie.icecreamparlor.exception.NoSuchFlavorException;

import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class IceCreamParlorServicePhase4Test {
    private static final String UNKNOWN_FLAVOR = "unknownFlavor";

    @Mock
    private DynamoDBCartonDao cartonDao;

    @InjectMocks
    private IceCreamParlorService iceCreamParlorService;

    @BeforeEach
    public void setup(){
        initMocks(this);
    }

    @Test
    public void getScoop_noSuchFlavor_throwsNoSuchFlavorException() throws Exception {
        // GIVEN
        when(cartonDao.getCarton(UNKNOWN_FLAVOR)).thenThrow(ResourceNotFoundException.class);
        try {
            // WHEN
            iceCreamParlorService.getScoop(UNKNOWN_FLAVOR);
            fail("Expected FlavorOutOfStockException when getting a scoop from an empty carton!");
        } catch (Exception e) {
            assertEquals("NoSuchFlavorException", e.getClass().getSimpleName(),
                    "Expected NoSuchFlavorException when getting a scoop from a nonexistent carton!");
        }
    }

    @Test
    public void getSundae_noSuchFlavor_throwsNoSuchFlavorException() throws Exception {
        // GIVEN
        when(cartonDao.getCarton(UNKNOWN_FLAVOR)).thenThrow(ResourceNotFoundException.class);

        // WHEN + THEN
        assertThrows(NoSuchFlavorException.class,
                     () -> iceCreamParlorService.getSundae(Collections.singletonList(UNKNOWN_FLAVOR)),
                     "Expected getSundae to throw when requesting a flavor that does not exist!");
    }
}
