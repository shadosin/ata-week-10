package com.kenzie.discussion.handler;

import com.kenzie.discussion.cli.input.ATAUserInput;
import com.kenzie.discussion.cli.input.TextTable;
import com.kenzie.discussion.cli.DiscussionCliState;
import com.kenzie.discussion.dynamodb.Topic;
import com.kenzie.discussion.dynamodb.TopicDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Handler for the VIEW_TOPICS operation.
 */
public class ViewTopicsHandler implements DiscussionCliOperationHandler {
    private static final int MAX_NUM_TOPICS_TO_VIEW = 100;

    private final TopicDao topicDao;
    private final ATAUserInput userHandler;

    /**
     * Constructs handler with its dependencies.
     * @param topicDao The TopicDao
     * @param userHandler the ATAUserHandler, for user input
     */
    public ViewTopicsHandler(TopicDao topicDao, ATAUserInput userHandler) {
        this.topicDao = topicDao;
        this.userHandler = userHandler;
    }

    @Override
    public String handleRequest(DiscussionCliState state) {
        int numTopics = userHandler.getInteger(
            1,
            MAX_NUM_TOPICS_TO_VIEW,
            "We'll display topics. Select maximum number of topics to view in case there are many: "
        );
        List<Topic> topics = topicDao.getTopics(numTopics);
        state.setListedTopics(topics);
        return renderTopics(topics);
    }

    private String renderTopics(List<Topic> topics) {
        List<String> headers = Arrays.asList("#", "Topic name");
        List<List<String>> rows = new ArrayList<>();
        int topicNum = 0;
        for (Topic topic : topics) {
            List<String> row = new ArrayList<>();
            row.add(Integer.toString(topicNum++));
            row.add(topic.getName());
            rows.add(row);
        }

        return new TextTable(headers, rows).toString();
    }
}
