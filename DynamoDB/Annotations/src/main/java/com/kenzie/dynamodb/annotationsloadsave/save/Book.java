package com.kenzie.dynamodb.annotationsloadsave.save;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

/**
 * Book POJO which maps to the Books table.
 */
@DynamoDBTable(tableName = "DynamoDbAnnotationsLoadSave-Books")
public class Book {
    private String asin;
    private String author;
    private String title;
    private Integer yearPublished;

    @DynamoDBHashKey(attributeName = "asin")
    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    @DynamoDBAttribute(attributeName = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @DynamoDBAttribute(attributeName = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @DynamoDBAttribute(attributeName = "year_published")
    public Integer getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(Integer yearPublished) {
        this.yearPublished = yearPublished;
    }

    @Override
    public String toString() {
        return "Book{" +
               "asin='" + asin + '\'' +
               ", author='" + author + '\'' +
               ", title='" + title + '\'' +
               ", yearPublished=" + yearPublished +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        return Objects.equals(asin, book.asin) &&
               Objects.equals(author, book.author) &&
               Objects.equals(title, book.title) &&
               Objects.equals(yearPublished, book.yearPublished);
    }

    @Override
    public int hashCode() {

        return Objects.hash(asin, author, title, yearPublished);
    }
}
