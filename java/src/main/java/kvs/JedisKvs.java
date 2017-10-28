package kvs;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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


    /**
     * Set型
     * @param redisKey
     * @return
     */
    Set<String> getSetValue(String redisKey) {
        jedis.sadd(redisKey, "牛タン", "ずんだ", "笹かま", "焼きそば");
        System.out.println(jedis.smembers(redisKey));

        String dstKey = "shizuoka";
        jedis.smove(redisKey, dstKey, "焼きそば");
        System.out.println(jedis.scard(redisKey));
        System.out.println(jedis.smembers(redisKey));
        System.out.println(jedis.smembers(dstKey));

        return jedis.sunion(redisKey, dstKey);
    }

    /**
     * SortedSet
     * @param redisKey
     * @return
     */
    Set<String> getSourtedSetValue(String redisKey) {
        jedis.zadd(redisKey, 10.0, "牛タン");

        Map<String, Double> members = new HashMap<>();
        members.put("ずんだ", 20.0);
        members.put("笹かま", 40.0);
        jedis.zadd(redisKey, members);

        return jedis.zrevrange(redisKey, 0, 100);

    }

    /**
     * Hash型
     * @param redisKey
     * @return
     */
    Map<String, String> getHashValue(String redisKey) {
        jedis.hset(redisKey, "顔", "おにぎり");

        Map<String, String> fields = new HashMap<>();
        fields.put("出身", "仙台");
        fields.put("尊敬する人", "伊達政宗");
        jedis.hmset(redisKey, fields);

        jedis.hdel(redisKey, "尊敬する人");

        return jedis.hgetAll(redisKey);
    }

    /**
     * Expire
     * @param redisKey
     */
    void setExpire(String redisKey) {
        jedis.set(redisKey, "test");
        jedis.expire(redisKey, 2);

        long count = 0;
        for(int i = 0; i < 10000; i++){
            long ttl = jedis.ttl(redisKey);

            if(ttl > 0) {
                System.out.println(ttl + " -> " + jedis.get(redisKey));
            } else {
                System.out.println("ttl = 0 -> " + jedis.get(redisKey));
                count = i;
                break;
            }

            count = i;
        }

        System.out.println("finish. : " + count);
    }

}
