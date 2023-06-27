package com.kenzie.dynamodb.annotationsloadsave.save;

import com.amazonaws.regions.Regions;
import com.kenzie.dynamodb.DynamoDbClientProvider;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BookIntegrationTest {
    private static final String NEW_ASIN = "140003342X";
    private static final String DOESNT_EXIST_ASIN = "B00IQO403K";

    private BookDao bookDao;
    private DynamoDBMapper dynamoDBMapper;

    @BeforeEach
    public void setup() {
        AmazonDynamoDB client = DynamoDbClientProvider.getDynamoDBClient(Regions.US_EAST_1);
        dynamoDBMapper = new DynamoDBMapper(client);
        bookDao = new BookDao(dynamoDBMapper);

        // Make sure the ASINs we don't expect in db aren't there before tests begin
        // (after getting a DynamoDBMapper)
        deleteTestData();
    }

    @AfterEach
    public void teardown() {
        deleteTestData();
    }

    @Test
    public void getBook_thatExists_returnsExpectedTitle() {
        // GIVEN
        // An ASIN in the table
        String fireNextAsin = "B00EGMV00W";
        // The corresponding title
        String expectedTitle = "The Fire Next Time";

        // WHEN
        Book book = bookDao.getBook(fireNextAsin);

        // THEN
        // The returned title matches expected
        assertEquals(
            expectedTitle,
            book.getTitle(),
            String.format(
                "When getBook by ASIN, '%s', expected book to contain title, '%s', but book was: %s",
                fireNextAsin,
                expectedTitle,
                book.toString())
        );
    }

    @Test
    public void getBook_thatDoesNotExist_returnsNull() {
        // GIVEN
        // An ASIN not in table
        String nonexistentAsin = DOESNT_EXIST_ASIN;

        // WHEN
        Book book = bookDao.getBook(nonexistentAsin);

        // THEN
        // No book found (null returned)
        assertNull(
            book,
            String.format(
                "Expected getBook to return null for ASIN, '%s', but book returned was: %s",
                nonexistentAsin,
                book)
        );
    }

    @Test
    public void saveBook_newBook_returnsBookWithCorrectAttributes() {
        // GIVEN
        // new book to save
        Book newBook = new Book();
        String asin = NEW_ASIN;
        newBook.setAsin(asin);
        newBook.setAuthor("Toni Morrison");
        newBook.setTitle("Song of Solomon");
        newBook.setYearPublished(1977);

        // WHEN
        bookDao.saveBook(newBook);

        // THEN
        // returned book matches the one we saved
        Book book = bookDao.getBook(asin);
        assertEquals(newBook, book, "Expect getting book after saving it to return same book values");
    }

    @Test
    public void saveBook_fromBookDao_returnstoStringForIdDac046f0() {
        // GIVEN
        // a saved version of book with incorrect title
        Book newBook = new Book();
        String asin = NEW_ASIN;
        newBook.setAsin(asin);
        newBook.setAuthor("Toni Morrison");
        newBook.setTitle("Song of Solomon-typo");
        newBook.setYearPublished(1977);
        bookDao.saveBook(newBook);
        // corrected title
        String correctedTitle = "Song of Solomon";

        // WHEN
        // fetch book and update title
        Book updateBook = bookDao.getBook(asin);
        updateBook.setTitle(correctedTitle);
        bookDao.saveBook(updateBook);


        // THEN
        // book is found, and the title has been updated
        Book book = bookDao.getBook(asin);
        assertEquals(
            correctedTitle,
            book.getTitle(),
            String.format("Expected updating book with ASIN, '%s', would result in having corrected title, '%s', " +
                          "but book was: %s",
                          asin,
                          correctedTitle,
                          book.toString())
        );
    }

    public void deleteTestData() {
        Book book = new Book();
        book.setAsin(NEW_ASIN);
        dynamoDBMapper.delete(book);
        book.setAsin(DOESNT_EXIST_ASIN);
        dynamoDBMapper.delete(book);
    }
}
