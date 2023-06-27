package com.kenzie.discussion.handler;

import com.kenzie.discussion.cli.input.ATAUserInput;
import com.kenzie.discussion.cli.DiscussionCliOperation;
import com.kenzie.discussion.cli.DiscussionCliState;
import com.kenzie.discussion.dynamodb.Topic;

/**
 * Handles the CHANGE_TOPIC operation.
 */
public class ChangeTopicHandler implements DiscussionCliOperationHandler {
    private ATAUserInput userHandler;

    /**
     * Constructs handler with its dependencies.
     * @param userHandler the ATAUserHandler, for user input
     */
    public ChangeTopicHandler(ATAUserInput userHandler) {
        this.userHandler = userHandler;
    }

    @Override
    public String handleRequest(DiscussionCliState state) {
        int topicNumber = userHandler.getInteger(0, state.getListedTopics().size() - 1, "Select topic number:");
        Topic nextTopic = state.getListedTopics().get(topicNumber);
        state.setCurrentTopic(nextTopic);
        state.setNextOperation(DiscussionCliOperation.VIEW_TOPIC_MESSAGES);

        return String.format("Topic changed to '%s'", nextTopic.getName());
    }
}
