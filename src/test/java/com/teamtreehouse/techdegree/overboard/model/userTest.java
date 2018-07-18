package com.teamtreehouse.techdegree.overboard.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.teamtreehouse.techdegree.overboard.exc.AnswerAcceptanceException;
import com.teamtreehouse.techdegree.overboard.exc.VotingException;


public class userTest {

private User userA;
private User userB;
private Board testBoard;
private Question unitQuestion;
private Answer unitAnswer;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public  void setUp() throws Exception {
		testBoard = new Board("NFL");
		userA = new User(testBoard, "rpetchen");
		userB = new User(testBoard, "rpetchen87");
		unitQuestion = userA.askQuestion("Best NFl Team?");
		unitAnswer = userB.answerQuestion(unitQuestion, "Arizona Cardinals");
	}

	
	@Test
	public void upQuestionerReputationTest() throws Exception{
		
		userB.upVote(unitQuestion);
		
		assertEquals(5, userA.getReputation() );
	}
	
	@Test
	public void upAnswererReputationTest()  throws Exception {
		
		userA.upVote(unitAnswer);
		
		assertEquals(10, userB.getReputation() );
	}
	
	@Test
	public void acceptAnswerReputationTest()  throws Exception {
		
		userA.acceptAnswer(unitAnswer);
		
		assertEquals(15, userB.getReputation() );
	}
	
	@Test
	public void sameUserUpVoteQuestionTest() throws Exception {
		thrown.expect(VotingException.class);
	    thrown.expectMessage("You cannot vote for yourself!");
	    
		userA.upVote(unitQuestion);
	}
	
	@Test
	public void sameUserDownVoteQuestionTest() throws Exception {
		thrown.expect(VotingException.class);
	    thrown.expectMessage("You cannot vote for yourself!");
	    
		userB.downVote(unitAnswer);
	}
	
	@Test
	public void sameUserUpVoteAnswerTest() throws Exception {
		thrown.expect(VotingException.class);
	    thrown.expectMessage("You cannot vote for yourself!");
	    
		userB.upVote(unitAnswer);
	}
	
	@Test
	public void sameUserDownVoteAnswerTest() throws Exception {
		thrown.expect(VotingException.class);
	    thrown.expectMessage("You cannot vote for yourself!");
	    
		userA.downVote(unitQuestion);
	}
	
	@Test
	public void acceptQuestionTest() throws Exception {
		thrown.expect(AnswerAcceptanceException.class);
	    thrown.expectMessage("Only rpetchen can accept this answer as it is their question");
	    
		userB.acceptAnswer(unitAnswer);
	}

}
