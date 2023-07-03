package com.kenzie.discussion;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.discussion.dynamodb.MemberDao;
import com.kenzie.discussion.dynamodb.TopicMessage;
import com.kenzie.discussion.dynamodb.TopicMessageDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class TopicMessageDaoTest {
    // PARTICIPANTS - add at least one relevant test (remember to mock DynamoDBMapper!)
    // You can use MemberDaoTest as a guide!
    private TopicMessageDao messageDao;
    @Mock
    private DynamoDBMapper mapper;
    @BeforeEach
    public void setup(){
        initMocks(this);
        messageDao = new TopicMessageDao(mapper);
    }

    @Test
    public void creatingMessage_saves_Message(){
        TopicMessage message = new TopicMessage();

        // Call the method under test
        messageDao.saveTopicMessage(message);

        // Verify that the DynamoDBMapper's save method was called with the correct message
        verify(mapper).save(message);
    }


}
