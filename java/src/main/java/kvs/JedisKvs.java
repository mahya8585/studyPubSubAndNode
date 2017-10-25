package kvs;

import redis.clients.jedis.Jedis;

import java.util.List;

public class JedisKvs {
    Jedis jedis = new Jedis("localhost");

    /**
     * 文字列型のデモ
     *
     * @return
     */
    String getStringValue() {
        String redisKey = "仙台";

        if (jedis.exists(redisKey)) {
            jedis.append(redisKey, "文化祭");
        } else {
            jedis.set(redisKey, "IT");
        }

        return jedis.get(redisKey);

    }

    /**
     * List型
     * @param redisKey
     * @return
     */
    List<String> getListValue(String redisKey) {
        jedis.lpush(redisKey, "文化祭", "IT", "仙台");
        System.out.println(jedis.lrange(redisKey, 0, 3));

        jedis.rpush(redisKey, "2017");
        System.out.println(jedis.lrange(redisKey, 0, 3));

        System.out.println(jedis.rpop(redisKey));
        System.out.println(jedis.lpop(redisKey));

        return jedis.lrange(redisKey, 0, 3);
    }


}
