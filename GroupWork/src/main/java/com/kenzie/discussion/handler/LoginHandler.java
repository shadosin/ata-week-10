package com.kenzie.discussion.handler;

import com.kenzie.discussion.cli.input.ATAUserInput;
import com.kenzie.discussion.cli.DiscussionCliOperation;
import com.kenzie.discussion.cli.DiscussionCliState;
import com.kenzie.discussion.dynamodb.Member;
import com.kenzie.discussion.dynamodb.MemberDao;

/**
 * Handles the request to login (or create) a member.
 */
public class LoginHandler implements DiscussionCliOperationHandler {
    private MemberDao memberDao;
    private ATAUserInput userHandler;

    /**
     * Constructs a new LoginHandler with provided DAO.
     * @param memberDao the DAO for accessing members
     * @param userHandler Handler to manage user interactions
     */
    public LoginHandler(MemberDao memberDao, ATAUserInput userHandler) {
        this.memberDao = memberDao;
        this.userHandler = userHandler;
    }

    @Override
    public String handleRequest(DiscussionCliState state) {
        String username = requestUsername();
        Member member = findOrCreateMember(username);

        state.setCurrentMember(member);
        state.setNextOperation(DiscussionCliOperation.VIEW_TOPICS);

        return renderResponse(member);
    }

    private String requestUsername() {
        return userHandler.getString("", "username: ");
    }

    private Member findOrCreateMember(String username) {
        Member member = memberDao.getMember(username);

        if (null == member) {
            member = new Member(username);
            memberDao.createMember(member);
        }

        return member;
    }

    private String renderResponse(Member member) {
        return String.format("Welcome, %s!", member.display());
    }
}
