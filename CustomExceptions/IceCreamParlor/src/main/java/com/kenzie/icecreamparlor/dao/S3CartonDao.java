package com.kenzie.icecreamparlor.dao;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.kenzie.icecreamparlor.model.Carton;
import com.kenzie.icecreamparlor.model.Flavor;
import org.apache.commons.lang.NotImplementedException;

import java.util.HashMap;
import java.util.Map;

public class S3CartonDao implements CartonDao {
    private Map<String, Carton> cartonMap;

    public S3CartonDao(){
        cartonMap = new HashMap<>();
        cartonMap.put("vanilla", new Carton(new Flavor("vanilla"), 10));
        cartonMap.put("chocolate", new Carton(new Flavor("chocolate"), 0));
    }

    /**
     * Retrieves a carton from our datastore given a flavor.
     *
     * @param id - the name of the flavor to get a carton for.
     * @return the Carton matching the flavor given.
     * @throws AmazonS3Exception if S3 encountered an exception or the requested file does not exist.
     */
    @Override
    public Carton getCarton(String id) throws AmazonS3Exception {
        if (cartonMap.containsKey(id)) {
            return new Carton(cartonMap.get(id));
        }else{
            throw new AmazonS3Exception("Requested file " + id + ".json does not exist.");
        }
    }

    /**
     * Saves a carton to our datastore.
     *
     * @param carton - the Carton to save
     * @return the saved Carton
     */
    @Override
    public Carton saveCarton(Carton carton) {
        throw new NotImplementedException();
    }
}
