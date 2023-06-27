package com.kenzie.discussion;

import com.kenzie.discussion.cli.DiscussionCliOperation;
import com.kenzie.discussion.cli.DiscussionCliState;
import com.kenzie.discussion.dynamodb.Topic;
import com.kenzie.discussion.dynamodb.TopicMessage;
import com.kenzie.discussion.dynamodb.TopicMessageDao;
import com.kenzie.discussion.handler.ViewTopicMessagesHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ViewTopicMessagesHandlerTest {
    private ViewTopicMessagesHandler handler;
    private DiscussionCliState state;

    @Mock
    private TopicMessageDao topicMessageDao;

    @BeforeEach
    public void setup() {
        initMocks(this);
        handler = new ViewTopicMessagesHandler(topicMessageDao);
        state = new DiscussionCliState();
    }

    @Test
    void handleRequest_whenNoCurrentTopic_nextOperationIsViewTopics() {
        // GIVEN
        // state has topic
        String topicName = "abc";
        List<TopicMessage> messages = Collections.emptyList();
        when(topicMessageDao.getMessagesForTopicName(topicName)).thenReturn(messages);

        // WHEN
        handler.handleRequest(state);

        // THEN
        assertEquals(DiscussionCliOperation.VIEW_TOPICS, state.getNextOperation());
    }


    @Test
    void handleRequest_whenSuccessful_noNextOperationIsSet() {
        // GIVEN
        // state has topic
        Topic topic = new Topic("abc", "def");
        state.setCurrentTopic(topic);
        List<TopicMessage> messages = Collections.emptyList();
        when(topicMessageDao.getMessagesForTopicName(topic.getName())).thenReturn(messages);

        // WHEN
        handler.handleRequest(state);

        // THEN
        assertNull(state.getNextOperation());
    }
}
