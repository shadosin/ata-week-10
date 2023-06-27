package com.kenzie.discussion.handler;

import com.kenzie.discussion.cli.input.ATAUserInput;
import com.kenzie.discussion.cli.DiscussionCliState;
import com.kenzie.discussion.dynamodb.Topic;
import com.kenzie.discussion.dynamodb.TopicDao;

/**
 * Handler for the CREATE_TOPIC operation.
 */
public class CreateTopicHandler implements DiscussionCliOperationHandler {
    private final TopicDao topicDao;
    private final ATAUserInput userHandler;

    /**
     * Constructs handler with its dependencies.
     * @param topicDao TopicDao
     * @param userHandler the ATAUserHandler, for user input
     */
    public CreateTopicHandler(TopicDao topicDao, ATAUserInput userHandler) {
        this.topicDao = topicDao;
        this.userHandler = userHandler;
    }

    @Override
    public String handleRequest(DiscussionCliState state) {
        String topicName = userHandler.getString("", "New topic name: ");
        String description = userHandler.getString("", "New topic description: ");

        Topic newTopic = new Topic(topicName, description);
        newTopic = topicDao.createTopic(newTopic);
        state.setCurrentTopic(newTopic);

        return String.format("New topic '%s' was created!%nTopic changed to '%s'",
                             newTopic.getName(), newTopic.getName());
    }
}
