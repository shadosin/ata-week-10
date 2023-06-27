package com.kenzie.dynamodb.annotationsloadsave.load;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BookDaoTest {

    @Mock
    DynamoDBMapper mapper;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void getBook_bookExists_returnsBookPopulatedWithValues() {
        //GIVEN
        String asin = "B00EGMV00W";
        Book greatGatsby = new Book();
        greatGatsby.setAsin(asin);
        greatGatsby.setAuthor("Fitzgerald");
        greatGatsby.setTitle("The Great Gatsby");
        greatGatsby.setYearPublished(1925);
        when(mapper.load(Book.class, asin)).thenReturn(greatGatsby);

        //WHEN
        BookDao bookDao = new BookDao(mapper);
        Book bookResult = bookDao.getBook(asin);

        //THEN
        assertEquals(greatGatsby.getAsin(), bookResult.getAsin(), "Book ASINs do not match!");
        assertEquals(greatGatsby.getAuthor(), bookResult.getAuthor(), "Book authors do not match!");
        assertEquals(greatGatsby.getTitle(), bookResult.getTitle(), "Book titles do not match!");
        assertEquals(greatGatsby.getYearPublished(), bookResult.getYearPublished(),
                     "Publishing years do not match!");
    }

}
