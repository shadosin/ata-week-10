package com.kenzie.discussion.handler;


import com.kenzie.discussion.cli.DiscussionCliOperation;
import com.kenzie.discussion.cli.DiscussionCliState;

/**
 * Handler for the EXIT operation.
 */
public class ExitHandler implements DiscussionCliOperationHandler {
    @Override
    public String handleRequest(DiscussionCliState state) {
        state.setNextOperation(DiscussionCliOperation.EXIT);
        return "Hope you enjoyed, good-bye!";
    }
}
