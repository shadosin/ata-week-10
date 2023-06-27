package com.kenzie.discussion.cli;


import com.kenzie.discussion.cli.input.ATAUserInput;
import com.kenzie.discussion.handler.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The CLI app to interact with user to fetch Discussion app data for them.
 */
public class DiscussionCli {
    // Mappings from user-entered options to DiscussionCliOperation enums (see handleRequests())
    private final Map<String, DiscussionCliOperation> userOperationOptions = new HashMap<>();
    private final Map<DiscussionCliOperation, DiscussionCliOperationHandler> operationHandlers = new HashMap<>();
    private final ATAUserInput userHandler;
    private final LoginHandler loginHandler;
    private final ViewTopicsHandler viewTopicsHandler;
    private final CreateTopicHandler createTopicHandler;
    private final ViewTopicMessagesHandler viewTopicMessagesHandler;
    private final ChangeTopicHandler changeTopicHandler;
    private final CreateTopicMessageHandler createTopicMessageHandler;
    private final ExitHandler exitHandler;
    private final DiscussionCliState state;

    /**
     * Creates a new DiscussionCli to interact with user.
     * @param userHandler Handler for user interactions
     * @param loginHandler Handler for user login
     * @param viewTopicsHandler Handler to retrieve discussion topics
     * @param createTopicHandler Handler to create new discussion topics
     * @param viewTopicMessagesHandler Handler to retrieve discussion messages
     * @param changeTopicHandler Handler to change topic CLI is interacting with
     * @param createTopicMessageHandler Handler to create a new discussion message
     * @param exitHandler Handler to exit the CLI
     * @param state Tracks the internal state of the Discussion CLI
     */
    public DiscussionCli(final ATAUserInput userHandler,
                         final LoginHandler loginHandler,
                         final ViewTopicsHandler viewTopicsHandler,
                         final CreateTopicHandler createTopicHandler,
                         final ViewTopicMessagesHandler viewTopicMessagesHandler,
                         final ChangeTopicHandler changeTopicHandler,
                         final CreateTopicMessageHandler createTopicMessageHandler,
                         final ExitHandler exitHandler,
                         final DiscussionCliState state) {
        this.userHandler = userHandler;
        this.loginHandler = loginHandler;
        this.viewTopicsHandler = viewTopicsHandler;
        this.createTopicHandler = createTopicHandler;
        this.viewTopicMessagesHandler = viewTopicMessagesHandler;
        this.changeTopicHandler = changeTopicHandler;
        this.createTopicMessageHandler = createTopicMessageHandler;
        this.exitHandler = exitHandler;
        this.state = state;

        registerHandlers();
    }

    /**
     * For as long as the user wants to keep using the CLI app, keeps handling their requests,
     * e.g. to fetch a member, fetch a message.
     */
    public void handleRequests() {
        displayWelcomeMessage();

        state.setNextOperation(DiscussionCliOperation.LOGIN);
        DiscussionCliOperation nextOperation;
        do {
            nextOperation = getNextRequestedCliOperation();
            if (operationHandlers.containsKey(nextOperation)) {
                System.out.println(operationHandlers.get(nextOperation).handleRequest(state));
            } else {
                System.out.println("Hm, got a valid DiscussionCliOperation that the DiscussionCli doesn't " +
                                   "know how to handle, please fix this!: " + nextOperation + ". Exiting");
                state.setNextOperation(DiscussionCliOperation.EXIT);
            }
        } while (state.getNextOperation() != DiscussionCliOperation.EXIT);
    }

    private void registerHandlers() {
        userOperationOptions.put("1", DiscussionCliOperation.VIEW_TOPICS);
        userOperationOptions.put("2", DiscussionCliOperation.CHANGE_TOPIC);
        userOperationOptions.put("3", DiscussionCliOperation.VIEW_TOPIC_MESSAGES);
        userOperationOptions.put("4", DiscussionCliOperation.CREATE_TOPIC);
        userOperationOptions.put("5", DiscussionCliOperation.CREATE_TOPIC_MESSAGE);
        userOperationOptions.put("6", DiscussionCliOperation.EXIT);

        operationHandlers.put(DiscussionCliOperation.LOGIN, loginHandler);
        operationHandlers.put(DiscussionCliOperation.VIEW_TOPICS, viewTopicsHandler);
        operationHandlers.put(DiscussionCliOperation.CHANGE_TOPIC, changeTopicHandler);
        operationHandlers.put(DiscussionCliOperation.CREATE_TOPIC, createTopicHandler);
        operationHandlers.put(DiscussionCliOperation.VIEW_TOPIC_MESSAGES, viewTopicMessagesHandler);
        operationHandlers.put(DiscussionCliOperation.CREATE_TOPIC_MESSAGE, createTopicMessageHandler);
        operationHandlers.put(DiscussionCliOperation.EXIT, exitHandler);
    }

    private DiscussionCliOperation getNextRequestedCliOperation() {
        if (null != state.getNextOperation()) {
            DiscussionCliOperation nextOperation = state.getNextOperation();
            state.setNextOperation(null);
            return nextOperation;
        }

        String prompt = "\nWhat would you like to do?\n" +
                        userOperationOptions.entrySet().stream()
                            .map(entry -> entry.getKey() + ": " + entry.getValue().getUserVisibleRepresentation())
                            .collect(Collectors.joining("\n"));

        String nextCliOperationOption;

        do {
            nextCliOperationOption = userHandler.getString(userOperationOptions.keySet(), prompt, "> ")
                                                .trim();
        } while ("".equals(nextCliOperationOption));

        return userOperationOptions.get(nextCliOperationOption);
    }

    private void displayWelcomeMessage() {
        System.out.println("Welcome to the Discussion app CLI.");
    }
}
