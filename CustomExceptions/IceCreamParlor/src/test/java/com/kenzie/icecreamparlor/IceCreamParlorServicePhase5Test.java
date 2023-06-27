package com.kenzie.icecreamparlor;

import com.kenzie.icecreamparlor.dao.DynamoDBCartonDao;
import com.kenzie.icecreamparlor.exception.NoSuchFlavorException;
import com.kenzie.icecreamparlor.model.Carton;
import com.kenzie.icecreamparlor.model.Flavor;

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


public class IceCreamParlorServicePhase5Test {

    private static final String CHOCOLATE = "chocolate";
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
    public void getScoop_cartonEmpty_throwsFlavorOutOfStockException() throws Exception {
        // GIVEN
        Carton chocolateCarton = new Carton(new Flavor(CHOCOLATE), 0);

        when(cartonDao.getCarton(CHOCOLATE)).thenReturn(chocolateCarton);

        try {
            // WHEN
            iceCreamParlorService.getScoop(CHOCOLATE);
            fail("Expected FlavorOutOfStockException when getting a scoop from an empty carton!");
        } catch (Exception e) {
            // THEN
            // Using string matching here instead of assertThrows because FlavorOutOfStockException does
            // not exist at the start of the activity, so the code would not compile if we tried to reference
            // FlavorOutOfStockException.class
            assertEquals("FlavorOutOfStockException", e.getClass().getSimpleName(),
                         "Expected FlavorOutOfStockException when getting a scoop from an empty carton!");
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
