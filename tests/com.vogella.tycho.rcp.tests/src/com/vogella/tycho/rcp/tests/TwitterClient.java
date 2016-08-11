package com.vogella.tycho.rcp.tests;

public class TwitterClient {

	
	public void sendTweet(ITweet tweet) {
		String message = tweet.getMessage();
		// send the message to Twitter
		System.out.println(message);
	}
}
