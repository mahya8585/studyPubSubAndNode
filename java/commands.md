# 仙台IT文化祭

## 準備
<font size="5pt">   
- cd /Users/maaya/.m2/repository/redis/clients/jedis/2.9.0
- jshell --class-path jedis-2.9.0.jar

- import redis.clients.jedis.Jedis
- Jedis jedis = new Jedis("localhost")

## List

- jedis.lpush("10-28", "文化祭", "IT", "仙台")
- jedis.rpush("10-28", "2017")
- jedis.lrange("10-28", 0, 3)
- jedis.rpop("10-28")
- jedis.lpop("10-28")
- jedis.lrange("10-28", 0, 3)

## set
- jedis.sadd(“miyagi”, "牛タン", "ずんだ", "笹かま", "焼きそば");
- jedis.smembers(“miyagi”);
- jedis.smove(“miyagi”, “shizuoka”, "焼きそば");
- jedis.smembers(“miyagi”);
- jedis.smembers(“shizuoka”);
   
- jedis.sadd(“miyagi”, "焼きそば");
- jedis.sadd(“shizuoka”, “お茶”);
- jedis.sunion(“miyagi”, “shizuoka”);

## zset
- jedis.zadd(“miyagi”, 10.0, "牛タン");
- Map<String, Double> members = new HashMap<>();
- members.put("ずんだ", 20.0);
- members.put("笹かま", 40.0);
- jedis.zadd(“miyagi”, members);
    
- jedis.zrange(“miyagi”, 0, 100).forEach(m -> System.out.println(m));
- jedis.zrevrange(“miyagi”, 0, 100).forEach(mr -> System.out.println(mr));

## hash
- jedis.hset(“むすび丸”, "顔", "おにぎり");
- Map<String, String> fields = new HashMap<>();
- fields.put("出身", "仙台");
- fields.put("尊敬する人", "伊達政宗");
- jedis.hmset(“むすび丸”, fields);
   
- jedis.hdel(redisKey, "尊敬する人");
- jedis.hgetAll(redisKey);

## expire / ttl

- jedis.set("ping", "pong")
- jedis.expire("ping", 60)
- jedis.get("ping")
- jedis.ttl("ping")

</font>