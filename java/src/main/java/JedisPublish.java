import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

/**
 * Created by user on 2016/08/09.
 */
public class JedisPublish extends JedisBase {

    public static void main(String... args) throws InterruptedException {
        publish("test", "from jedis!!!");
    }

    private static void publish(String channel, String message) throws InterruptedException {
        System.out.println("publish start.");

        JedisShardInfo settings = new JedisShardInfo(HOSTNAME, PORT_NUMBER);
        //Azure Redis Cacheでいうプライマリアクセスキー
        settings.setPassword(AUTH);
        Jedis jedis = new Jedis(settings);
        jedis.publish(channel, message);
        jedis.quit();

        System.out.println("publish end.");
    }

}
