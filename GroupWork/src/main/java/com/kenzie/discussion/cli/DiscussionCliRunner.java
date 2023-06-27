package com.kenzie.discussion.cli;

import com.kenzie.discussion.dynamodb.DynamoDbClientProvider;
import com.kenzie.discussion.cli.input.ATAUserInput;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.discussion.dynamodb.MemberDao;
import com.kenzie.discussion.dynamodb.TopicDao;
import com.kenzie.discussion.dynamodb.TopicMessageDao;
import com.kenzie.discussion.handler.*;

/**
 * Provides a main method to instantiate and run the DiscussionCli we will be
 * working with in this lesson.
 */
public class DiscussionCliRunner {
    private static DynamoDBMapper mapper;
    private static ATAUserInput userHandler;

    /**
     * Starts the CLI application.
     * @param args no args expected
     */
    public static void main(String[] args) {
        DiscussionCli cli = new DiscussionCli(
            getAtaUserHandler(),
            getLoginHandler(),
            getViewTopicsHandler(),
            getCreateTopicHandler(),
            getViewTopicMessagesHandler(),
            getChangeTopicHandler(),
            getCreateTopicMessageHandler(),
            getExitHandler(),
            new DiscussionCliState()
        );
        cli.handleRequests();
    }

    /**
     * Provides an ATAUserHandler.
     * @return the ATAUserHandler
     */
    private static ATAUserInput getAtaUserHandler() {
        if (null == userHandler) {
            userHandler = new ATAUserInput();
        }

        return userHandler;
    }

    /**
     * Provides the LoginHandler.
     * @return a new LoginHandler
     */
    private static LoginHandler getLoginHandler() {
        return new LoginHandler(getMemberDao(), getAtaUserHandler());
    }

    /**
     * Provides the ViewTopicsHandler.
     * @return a new ViewTopicsHandler
     */
    private static ViewTopicsHandler getViewTopicsHandler() {
        return new ViewTopicsHandler(getTopicDao(), getAtaUserHandler());
    }

    /**
     * Provides the CreateTopicHandler.
     * @return a new CreateTopicHandler
     */
    private static CreateTopicHandler getCreateTopicHandler() {
        return new CreateTopicHandler(getTopicDao(), getAtaUserHandler());
    }

    /**
     * Provides the ViewTopicMessagesHandler.
     * @return a new ViewTopicMessagesHandler
     */
    private static ViewTopicMessagesHandler getViewTopicMessagesHandler() {
        return new ViewTopicMessagesHandler(getTopicMessageDao());
    }

    /**
     * Provides the ChangeTopicHandler.
     * @return a new ChangeTopicHandler
     */
    private static ChangeTopicHandler getChangeTopicHandler() {
        return new ChangeTopicHandler(getAtaUserHandler());
    }

    /**
     * Provides the CreateTopicMessageHandler.
     * @return a new CreateTopicMessageHandler
     */
    private static CreateTopicMessageHandler getCreateTopicMessageHandler() {
        return new CreateTopicMessageHandler(getAtaUserHandler());
    }

    /**
     * Provides the ExitHandler.
     * @return a new ExitHandler
     */
    private static ExitHandler getExitHandler() {
        return new ExitHandler();
    }

    /**
     * Provides the MemberDao to access member items.
     * @return a new MemberDao
     */
    private static MemberDao getMemberDao() {
        return new MemberDao(getDynamoDBMapper());
    }

    /**
     * Provides the TopicDao to access member items.
     * @return a new TopicDao
     */
    private static TopicDao getTopicDao() {
        return new TopicDao(getDynamoDBMapper());
    }

    /**
     * Provides the TopicMessageDao to access member items.
     * @return a new TopicMessageDao
     */
    private static TopicMessageDao getTopicMessageDao() {
        return new TopicMessageDao(getDynamoDBMapper());
    }

    /**
     * Uses the default DynamoDB client provider for default region to return a (shared) DynamoDBMapper instance.
     * @return DynamoDBMapper, ready to use!
     */
    private static DynamoDBMapper getDynamoDBMapper() {
        if (null == mapper) {
            String region = "us-east-1";

            AmazonDynamoDB client = AmazonDynamoDBClientBuilder
                    .standard()
                    // this will use multiple providers to look for AWS credentials
                    .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
                    // This should be the same region the CloudFormation stack with tables was deployed in
                    .withRegion(region)
                    .build();
            mapper = new DynamoDBMapper(client);
        }
        return mapper;
    }
}
