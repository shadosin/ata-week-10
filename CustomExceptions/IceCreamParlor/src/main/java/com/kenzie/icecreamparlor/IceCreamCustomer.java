package com.kenzie.icecreamparlor;

import com.kenzie.icecreamparlor.exception.FlavorOutOfStockException;
import com.kenzie.icecreamparlor.exception.NoSuchFlavorException;

import com.amazonaws.services.s3.model.AmazonS3Exception;

import java.util.List;


/**
 * Class to define behavior of a customer at an ice cream parlor.
 */
public class IceCreamCustomer {

    public static final String HAPPY = "Ooo, what a yummy ice cream.";
    public static final String SAD = "Too bad, they didn't have what I wanted.";

    /**
     * The customer gets an ice cream scoop of a given flavor from the ice cream parlor.
     *
     * @param flavor the name of a flavor of an ice cream scoop
     *               for the customer to enjoy.
     * @return the mood of the customer, HAPPY if they got what they wanted, SAD otherwise.
     */
    public String enjoyAnIceCreamScoop(String flavor) {
        IceCreamParlorService iceCreamParlorService = new IceCreamParlorService();

        try {
            iceCreamParlorService.getScoop(flavor);
        } catch (NoSuchFlavorException | FlavorOutOfStockException e) {
            return SAD;
        }

        return HAPPY;
    }

    /**
     * The customer gets an ice cream sundae, containing the request flavors,
     *      from the ice cream parlor.
     *
     * @param flavors the name of a flavors of ice cream scoops
     *               for the customer to enjoy in a sundae.
     * @return the mood of the customer, HAPPY if they got what they wanted, SAD otherwise.
     */
    public String enjoyAnIceCreamSundae(List<String> flavors) {
        IceCreamParlorService iceCreamParlorService = new IceCreamParlorService();

        try {
            iceCreamParlorService.getSundae(flavors);
        } catch (NoSuchFlavorException ex) {
            return SAD;
        }

        return HAPPY;
    }
}
