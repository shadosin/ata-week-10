package com.kenzie.icecreamparlor;

import com.google.common.collect.ImmutableList;
import com.kenzie.icecreamparlor.exception.FlavorOutOfStockException;
import com.kenzie.icecreamparlor.exception.NoSuchFlavorException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IceCreamCustomerTest {

    @Test
    public void enjoyAnIceCreamScoop_flavorExists_returnsHappyResponse() throws NoSuchFlavorException, FlavorOutOfStockException {
        IceCreamCustomer underTest = new IceCreamCustomer();

        String result = underTest.enjoyAnIceCreamScoop("vanilla");

        assertEquals(IceCreamCustomer.HAPPY, result);
    }

    @Test
    public void enjoyAnIceCreamScoop_flavorDoesNotExist_returnsSadResponse() throws NoSuchFlavorException, FlavorOutOfStockException {
        IceCreamCustomer underTest = new IceCreamCustomer();

        String result = underTest.enjoyAnIceCreamScoop("unknownFlavor");

        assertEquals(IceCreamCustomer.SAD, result);
    }

    @Test
    public void enjoyAnIceCreamSundae_allFlavorsExist_returnsHappyResponse() throws NoSuchFlavorException, FlavorOutOfStockException {
        IceCreamCustomer underTest = new IceCreamCustomer();

        String result = underTest.enjoyAnIceCreamSundae(ImmutableList.of("vanilla", "chocolate"));

        assertEquals(IceCreamCustomer.HAPPY, result);
    }

    @Test
    public void enjoyAnIceCreamSundae_anyFlavorDoesNotExist_returnsSadResponse() throws NoSuchFlavorException, FlavorOutOfStockException {
        IceCreamCustomer underTest = new IceCreamCustomer();

        String result = underTest.enjoyAnIceCreamSundae(ImmutableList.of("vanilla", "unknownFlavor"));

        assertEquals(IceCreamCustomer.SAD, result);
    }
}
