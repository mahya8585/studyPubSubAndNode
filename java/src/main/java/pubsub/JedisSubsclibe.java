package pubsub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.JedisShardInfo;

/**
 * Jedisからのsubscribeテスト
 * Created by user on 2016/08/09.
 */
public class JedisSubsclibe extends JedisBase {

    public static void main(String[] args) throws InterruptedException {
        new JedisSubsclibe().startSubscriber();
    }

    /**
     * subscribeを開始する（停止の処理は今回は特に作ってない）
     */
    private void startSubscriber() {
        final JedisPubSub jedisPubSub = init();
        try {
            System.out.println("connecting");
                JedisShardInfo settings = new JedisShardInfo(HOSTNAME, PORT_NUMBER);
                //Azure Redis Cacheでいうプライマリアクセスキー
                settings.setPassword(AUTH);
                Jedis jedis = new Jedis(settings);

            System.out.println("subscribe : start");
            jedis.subscribe(jedisPubSub, "test");

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
            public void onUnsubscribe(String channel, int subscribedChannels) {
                System.out.println("onUnsubscribe");
            }

            @Override
            public void onSubscribe(String channel, int subscribedChannels) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onMessage(String channel, String message) {
                System.out.println("Message received : " + message + " : by " + channel);
            }
        };
    }

}
