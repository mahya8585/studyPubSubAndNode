import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.JedisShardInfo;

/**
 * Jedisからのパターンsubscribeテスト
 * Created by user on 2016/08/09.
 */
public class JedisPSubsclibe {

    public static void main(String[] args) throws InterruptedException {
        new JedisPSubsclibe().startSubscriber();
    }

    /**
     * パターンマッチング形式でチャネルを指定するsubscribeを開始する（停止の処理は今回は特に作ってない）
     */
    private void startSubscriber() {
        final JedisPubSub jedisPubSub = init();
        try {
            System.out.println("connecting");
            JedisShardInfo settings = new JedisShardInfo(【HOSTNAME】, 【PORT NUMBER】);
            //Azure Redis Cacheでいうプライマリアクセスキー
            settings.setPassword(【AUTH】);
            Jedis jedis = new Jedis(settings);

            System.out.println("subscribe : start");
            //チャネル名をパターンマッチングさせる
            jedis.psubscribe(jedisPubSub, "t*");

        } catch (Exception e) {
            System.out.println(">>> OH NOES Sub - " + e.getMessage());
        }
    }

    /**
     * JedisPubSub のオーバーライド設定
     *
     * @return JedisPubSub
     */
    private JedisPubSub init() {
        return new JedisPubSub() {
            @Override
            public void onPUnsubscribe(String pattern, int subscribedChannels) {
                System.out.println("onPUnsubscribe");
            }

            @Override
            public void onPSubscribe(String pattern, int subscribedChannels) {
                System.out.println("onPSubscribe");
            }

            @Override
            public void onPMessage(String pattern, String channel, String message) {
                System.out.println("PMessage received : " + message + " : by " + channel);
            }
        };
    }

}
