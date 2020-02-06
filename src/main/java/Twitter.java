import java.util.*;

public class Twitter {

    // the global var to store timestamp
    private static int timeStamp = 0;

    // easy to find user if exist
    private HashMap<Integer, User> userMap;

    // initialize your data structure here
    public Twitter(){
        userMap = new HashMap();
    }

    // compose a new tweet
    public void postTweet(int userID, int tweetID) {
        if(!userMap.containsKey(userID)){
            userMap.put(userID, new User(userID));
        }
        userMap.get(userID).post(tweetID);
    }

    // Retrieve the 10 most recent tweet ids in the user's news feed.
    // Each item in the news feed must be posted by users who the user is followed
    // by the user himself

    // Tweets Must Be Ordered From Most Recent To Least Recent.

    public List<Integer> getNewsFeed(int userID) {
        List<Integer> rst = new ArrayList<>();
        if(!userMap.containsKey(userID)){
            return rst;
        }

        Set<Integer> users = userMap.get(userID).getFollowed();
        PriorityQueue<Tweet> minHeap = new PriorityQueue<>(10, (a, b) -> b.time - a.time);
        for(Integer u: users){
            Tweet t = userMap.get(u).getTweet_head();
            //very important!! if we add null to the head we are screwed.
            if(t != null){
                minHeap.add(t);
            }
        }

        int count = 0;

        while (!minHeap.isEmpty() && count < 10){
            Tweet t = minHeap.poll();
            rst.add(t.id);
            count++;
            if(t.next != null){
                minHeap.add(t.next);
            }
        }
        return rst;
    }

    public void follow(int followerId, int followeeId){
        if(!userMap.containsKey(followerId)){
            User user = new User(followerId);
            userMap.put(followerId, user);
        }

        if(!userMap.containsKey(followeeId)){
            User user = new User(followeeId);
            userMap.put(followeeId, user);

        }

        userMap.get(followerId).follow(followeeId);
    }

    public void unfollow(int followerId, int followeId){
        if(!userMap.containsKey(followerId) || followeId == followerId){
            return;
        }

        userMap.get(followerId).unfollow(followeId);
    }

    /**
     * OO design so User can follow, unfollow and post itself.
     * The property of the class are better to be private.
     */
    class User {
        private int id;
        // Store the Tweet that the user post
        // Think about why we use the pointer but not the arrays or set ?
        // (Similar to Using LinkedList)
        private Tweet tweet_head;
        private Set<Integer> followed;

        public Tweet getTweet_head() {
            return tweet_head;
        }

        public Set<Integer> getFollowed() {
            return followed;
        }

        User(int id) {
            this.id = id;
            followed = new HashSet<>();
            // The user should first follow itself
            // So that when we do getNewsFeed, we get get the result directly.
            follow(id);
            this.tweet_head = null;
        }

        public void follow(int id) {
            followed.add(id);
        }

        public void unfollow(int id) {
            followed.remove(id);
        }

        /**
         * everytime user post a new tweet, add it to the head of Tweet list.
         * @param id - the Tweet id
         */
        public void post(int id) {
            Tweet t = new Tweet(id);
            t.next = tweet_head;
            tweet_head = t;
        }
    }

    /**
     * Tweet link to next Tweet so that we can save a lot of time
     * when we execute getNewsFeed(userId)
     */
    private class Tweet {
        int id;
        int time;
        Tweet next;
        // String content; We don't need this property here...

        Tweet(int id) {
            this.id = id;
            time = timeStamp++;
            next = null;
        }
    }
}

