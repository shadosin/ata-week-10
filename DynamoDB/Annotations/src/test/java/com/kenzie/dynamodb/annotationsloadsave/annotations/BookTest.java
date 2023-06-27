package com.kenzie.dynamodb.annotationsloadsave.annotations;

import com.kenzie.dynamodb.annotationsloadsave.DynamoDBTestUtils;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookTest {

    @Test
    public void dynamoDBTable_classShouldBeAnnotated_annotatedForBooksTable() {
        Optional<DynamoDBTable> tableAnnotation = DynamoDBTestUtils.getAnnotation(Book.class, DynamoDBTable.class);
        assertTrue(tableAnnotation.isPresent(), "Expected the Book class to be annoted with DynamoDBTable.");
        assertEquals("DynamoDbAnnotationsLoadSave-Books", tableAnnotation.get().tableName(),
                     "Expected DynamoDbAnnotationsLoadSave-Books to be the tableName for the DynamoDBTable " +
                         "annotation.");

    }

    @Test
    public void asin_getDynamoAnnotations_getterOrFieldIsAnnotated() {
        //GIVEN
        Optional<DynamoDBHashKey> hashKeyAnnotation = DynamoDBTestUtils.getAnnotation(Book.class,
                "asin", DynamoDBHashKey.class);
        assertTrue(hashKeyAnnotation.isPresent(), "Expected getAsin to be annotated with " +
                "DynamoDBHashKey.");
        assertEquals("asin", hashKeyAnnotation.get().attributeName(),
                "Expected asin as the asin dynamoDB attribute name.");

    }

    @Test
    public void yearPublished_getDynamoAnnotations_getterOrFieldIsAnnotated() {
        Optional<DynamoDBAttribute> yearPublishedAnnotation = DynamoDBTestUtils.getAnnotation(Book.class,
                "yearPublished", DynamoDBAttribute.class);
        assertTrue(yearPublishedAnnotation.isPresent(), "Expected getYearPublished to be annotated with " +
                "DynamoDBAttribute.");
        assertEquals("year_published", yearPublishedAnnotation.get().attributeName(),
                "Expected year_published as the yearPublished DynamoDB attribute name.");
    }

    @Test
    public void author_getDynamoAnnotations_getterOrFieldIsAnnotated() {
        Optional<DynamoDBAttribute> authorAnnotation = DynamoDBTestUtils.getAnnotation(Book.class,
                "author", DynamoDBAttribute.class);
        assertTrue(authorAnnotation.isPresent(), "Expected getAuthor to be annotated with " +
                "DynamoDBAttribute.");
        assertEquals("author", authorAnnotation.get().attributeName(),
                "Expected author as the author DynamoDB attribute name in the annotation.");
    }

    @Test
    public void title_getDynamoAnnotations_getterOrFieldIsAnnotated() {
        Optional<DynamoDBAttribute> titleAnnotation = DynamoDBTestUtils.getAnnotation(Book.class,
                "title", DynamoDBAttribute.class);
        assertTrue(titleAnnotation.isPresent(), "Expected getTitle to be annotated with " +
                "DynamoDBAttribute.");
        assertEquals("title", titleAnnotation.get().attributeName(),
                "Expected title as the title DynamoDB attribute name in the annotation.");
    }

}
