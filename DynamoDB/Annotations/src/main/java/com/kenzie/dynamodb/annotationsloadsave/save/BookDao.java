package com.kenzie.dynamodb.annotationsloadsave.save;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

/**
 * Data access object to retrieve and write Book items from and to DynamoDB.
 */
public class BookDao {
    private final DynamoDBMapper dynamoDBMapper;

    /**
     * Constructs new DAO using given DynamoDB mapper.
     * @param dynamoDBMapper The DynamoDBMapper to use
     */
    public BookDao(final DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    /**
     * Gets the `Book` for the given asin.
     *
     * @param asin is the value of the id being loaded
     * @return the loaded Book
     */
    public Book getBook(String asin) {
        return dynamoDBMapper.load(Book.class, asin);
    }

    /**
     * Saves the given `Book`.
     *
     * @param book is the item that is being saved to the table
     */
    public void saveBook(Book book) {
        // PARTICIPANTS: Save a Book to the table
        dynamoDBMapper.save(book);
    }
}
