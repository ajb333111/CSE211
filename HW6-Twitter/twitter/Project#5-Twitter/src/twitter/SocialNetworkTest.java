/* Copyright (c) 2007-2014 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

/*
 * TODO: your testing strategies for these methods should go here
 */

public class SocialNetworkTest {
	private static Instant d1;
    private static Instant d2;
    private static Instant d3;
    
    private static Tweet tweet1;
    private static Tweet tweet2;
    private static Tweet tweet3;
    
    @BeforeClass
    public static void setUpBeforeClass() {
        d1 = Instant.parse("2014-09-14T10:00:00Z");
        d2 = Instant.parse("2014-09-14T11:00:00Z");
        d3 = Instant.parse("2014-09-14T12:00:00Z");
        
        tweet1 = new Tweet(0, "alyssa", "is it reasonable to talk about rivest so much?", d1);
        tweet2 = new Tweet(1, "bbitdiddle", "rivest talk in 30 minutes.@hype", d2);
        tweet3 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes @hype", d3);
    }
    @Test
    public void testGuessFollowsGraphEmpty() {
    	
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(new ArrayList<>());
        Map<String, Set<String>> followsGraph1 = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1,tweet2,tweet3));
        assertTrue(followsGraph.isEmpty());
        assertTrue(followsGraph1.size() == 2);
    }
    
    @Test
    public void testInfluencersEmpty() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        Map<String, Set<String>> followsGraph1 = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1,tweet2,tweet3));
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        List<String> influencers1 = SocialNetwork.influencers(followsGraph1);
        
        assertTrue(influencers.isEmpty());
        assertTrue(influencers1.size()==2);
    }

/*
 * Warning: all the tests you write here must be runnable against any SocialNetwork class that follows
 * the spec.  It will be run against several staff implementations
 * of SocialNetwork, which will be done by overwriting (temporarily) your version of SocialNetwork
 * with the staff's version.  DO NOT strengthen the spec of SocialNetwork or its methods.
 * In particular, your test cases must not call helper methods of your own that you have
 * put in SocialNetwork, because that means you're testing a stronger spec than SocialNetwork says. 
 * If you need such helper methods, define them in a different class.  If you only need
 * them in this test class, then keep them in this test class. 
 */

}
