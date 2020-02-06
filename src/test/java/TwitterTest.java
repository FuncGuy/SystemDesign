import org.junit.Test;

public class TwitterTest {

    @Test
    public void testShouldPostAndGetLast10HotNewsFeeds(){
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 79);
        twitter.postTweet(1, 80);
        twitter.postTweet(1, 81);
        twitter.postTweet(1, 82);
        twitter.postTweet(1, 83);
        twitter.postTweet(1, 84);
        twitter.postTweet(1, 85);
        twitter.postTweet(1, 86);
        twitter.postTweet(1, 87);
        twitter.postTweet(1, 88);
        twitter.postTweet(1, 89);
        twitter.postTweet(1, 90);
        // Hot tweets [last 10]
        twitter.postTweet(1, 91);
        twitter.postTweet(1, 92);
        twitter.postTweet(1, 93);
        twitter.postTweet(1, 94);
        twitter.postTweet(1, 95);
        twitter.postTweet(1, 96);
        twitter.postTweet(1, 97);
        twitter.postTweet(1, 98);
        twitter.postTweet(1, 99);
        twitter.postTweet(1, 100);


        System.out.println(twitter.getNewsFeed(1));
    }
}
