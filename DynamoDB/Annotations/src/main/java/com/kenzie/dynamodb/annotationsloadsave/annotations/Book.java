package com.kenzie.dynamodb.annotationsloadsave.annotations;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

public class Book {
    private String asin;
    private String author;
    private String title;
    private Integer yearPublished;

}
