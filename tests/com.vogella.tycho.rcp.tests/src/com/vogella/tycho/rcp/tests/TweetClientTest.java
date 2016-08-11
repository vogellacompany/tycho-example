package com.vogella.tycho.rcp.tests;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class TweetClientTest {

	@Test
	public void testSendingTweet() {
		TwitterClient twitterClient = new TwitterClient();

		ITweet iTweet = mock(ITweet.class);
		
		when(iTweet.getMessage()).thenReturn("Using Mockito is great");
		
		twitterClient.sendTweet(iTweet);
		
		verify(iTweet, atLeastOnce()).getMessage();
	}

}
