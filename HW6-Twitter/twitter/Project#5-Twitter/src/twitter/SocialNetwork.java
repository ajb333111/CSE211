/* Copyright (c) 2007-2014 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * SocialNetwork provides methods that operate on a social network.
 * 
 * A social network is represented by a Map<String, Set<String>> where map[A] is
 * the set of people that person A follows on Twitter, and all people are
 * represented by their Twitter usernames.  Users can't follow themselves.
 * If A doesn't follow anybody, then map[A] may be undefined or map[A] may be the
 * empty set; this is true even if A is followed by other people in the network.
 * Twitter usernames are not case sensitive, so "ernie" is the same as "ERNie".  A
 * username should appear at most once as a key in the map or in any given map[A] set.
 * 
 * DO NOT change the method signatures and specifications of these methods, but you should implement their
 * method bodies, and you may add new public or private methods or classes if you like.
 */
public class SocialNetwork {

    /**
     * Guess who might follow whom, from evidence found in tweets.
     * 
     * @param tweets
     *            a list of tweets providing the evidence, not modified by this
     *            method.
     * @return a social network (as defined above) in which Ernie follows Bert
     *         if and only if there is evidence for it in the given list of
     *         tweets.
     *         One kind of evidence that Ernie follows Bert is if Ernie
     *         @-mentions Bert in a tweet. This must be implemented. Other kinds
     *         of evidence may be used at the implementor's discretion.
     *         All the Twitter usernames in the returned social network must be 
     *         either authors or @-mentions in the list of tweets.
     */
    public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
    	Map<String, Set<String>> map = new HashMap<String, Set<String>>();    	
    	if(tweets == null) {
    		throw new RuntimeException("not implemented");
    	} else {
    		for(Tweet t: tweets) {
    			Set<String> follow = new HashSet<String>();
    			String[] str = t.getText().split(" |//.");
    			for(int i=0;i<str.length;i++) {
    				if(str[i].contains("@")) {
    					String follower = str[i].substring(str[i].indexOf('@'));
    					follow.add(follower);
    				}
    			}
    			map.put(t.getAuthor(), follow);
    		}
    	}
    	return map;
    }
      
    /**
     * Find the people in a social network who have the greatest influence, in
     * the sense that they have the most followers.
     * 
     * @param followsGraph
     *            a social network (as defined above)
     * @return a list of all distinct Twitter usernames in followsGraph, in
     *         descending order of follower count.
     */
    public static List<String> influencers(Map<String, Set<String>> followsGraph) {
    	List<String> list = new ArrayList<String>();
    	if(followsGraph == null) {
    		throw new RuntimeException("not implemented");
    	} else {
    		Map<String, Integer> map = new HashMap<String, Integer>();
    		int max = 0;
    		String name = "";
    		for(Map.Entry<String, Set<String>> entry :followsGraph.entrySet()) {
    			int num = entry.getValue().size();
    			map.put(entry.getKey(), num);
    		}
    		while(!map.isEmpty()) {
    			for(Map.Entry<String, Integer> entry :map.entrySet()) {
    				if(entry.getValue()>=max) {
    					max = entry.getValue();
    					name = entry.getKey();    					
    				}
    			}
    			list.add(name);
    			map.remove(name);
    			max = 0;
    		}
    	}
    	return list;
    }
    
    public static Map<String, Set<String>> Awareness(List<Tweet> tweets) {
    	Map<String, Set<String>> followers = guessFollowsGraph(tweets);
    	Map<String, Set<String>> map = new HashMap<String, Set<String>>();
    	if(tweets == null) {
    		throw new RuntimeException("not implemented");
    	} else {
    		for(Map.Entry<String, Set<String>> entry :followers.entrySet()) {
    			Set<String> set = new HashSet<String>();
    			for(Map.Entry<String, Set<String>> entry1 :followers.entrySet()) {
    				if(entry.getValue().contains(entry1.getKey())) {
    						Set<String> p = entry1.getValue();
    						for(String s: p) {
    							if(!set.contains(s)) {
    								set.add(s);
    							}
    						}
    					}
    			}
    			map.put(entry.getKey(), set);
    		}
    	}
    	return map;
    }
}
