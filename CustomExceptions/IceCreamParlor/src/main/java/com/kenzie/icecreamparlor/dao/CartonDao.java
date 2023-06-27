package com.kenzie.icecreamparlor.dao;

import com.kenzie.icecreamparlor.model.Carton;

public interface CartonDao {

    /**
     * Retrieves a carton from our datastore given a flavor.
     *
     * @param id - the name of the flavor to get a carton for.
     * @return the Carton matching the flavor given.
     * @throws RuntimeException if an error occurred
     */
    Carton getCarton(String id) throws RuntimeException;

    /**
     * Saves a carton to our datastore.
     *
     * @param carton - the Carton to save
     * @return the saved Carton
     */
    Carton saveCarton(Carton carton);
}
