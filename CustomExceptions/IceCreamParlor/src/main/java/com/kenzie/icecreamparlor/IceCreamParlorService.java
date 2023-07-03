package com.kenzie.icecreamparlor;

import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.google.common.collect.Lists;
import com.kenzie.icecreamparlor.dao.CartonDao;
import com.kenzie.icecreamparlor.dao.DynamoDBCartonDao;
import com.kenzie.icecreamparlor.dao.S3CartonDao;
import com.kenzie.icecreamparlor.exception.FlavorAlreadyExistsException;
import com.kenzie.icecreamparlor.exception.FlavorOutOfStockException;
import com.kenzie.icecreamparlor.exception.NoSuchFlavorException;
import com.kenzie.icecreamparlor.model.Carton;
import com.kenzie.icecreamparlor.model.Flavor;
import com.kenzie.icecreamparlor.model.Scoop;
import com.kenzie.icecreamparlor.model.Sundae;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to define behavior of an ice cream parlor.
 * Exposes operations to `IceCreamCustomer`s.
 */
public class IceCreamParlorService {
    private CartonDao cartonDao;

    public IceCreamParlorService() {
        cartonDao = new DynamoDBCartonDao();
    }

    public IceCreamParlorService(CartonDao cartonDao) {
        this.cartonDao = cartonDao;
    }

    /**
     * Retrieves a scoop of the requested flavor of ice cream.
     *
     * @param flavorName - the flavor of the ice cream scoop to retrieve.
     * @return a scoop of the desired ice cream flavor.
     */
    public Scoop getScoop(String flavorName) throws NoSuchFlavorException, FlavorOutOfStockException {
        try {
            Carton carton = cartonDao.getCarton(flavorName);

            if (carton.getScoopsLeft() <= 0) {
                throw new FlavorOutOfStockException();
            }

            carton.removeScoops(1);
            cartonDao.saveCarton(carton);

            return new Scoop(carton.getFlavor());
        } catch (ResourceNotFoundException e) {
            throw new NoSuchFlavorException(String.format("We don't have that flavor"));

        }
    }

    /**
     * Retrieves an ice cream sundae containing the requested flavors.
     *
     * @param flavorNames - the list of flavors of the ice cream scoop to put
     *                    in the sundae.
     * @return a sundae containing the desired ice cream flavors.
     * @throws NoSuchFlavorException if a desired flavor doesn't exist
     */
    public Sundae getSundae(List<String> flavorNames) throws NoSuchFlavorException {
        List<Flavor> flavors = Lists.newArrayList();

        for (String flavorName : flavorNames) {
            try {
                flavors.add(cartonDao.getCarton(flavorName).getFlavor());
            } catch (ResourceNotFoundException ex) {
                throw new NoSuchFlavorException(String.format("We don't serve a flavor called [%s]!", flavorName));
            }
        }
        return new Sundae(flavors);
    }

    /**
     * Adds a new flavor to the set of flavors available
     *
     * @param flavor - the flavor of the ice cream scoop to add.
     * @return void
     */
    public void addFlavor(Flavor flavor) throws FlavorAlreadyExistsException {
    }
}
