package kvs;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class JedisKvsTest{
    public static final String redisKey = "仙台";
    public static final String listKey = "sendai";
    JedisKvs target = new JedisKvs();

    @Before
    public void before(){
        Jedis jedis = new Jedis("localhost");
        jedis.del(redisKey);
        jedis.del(listKey);
    }


    @Test
    public void 文字列の操作をする() throws Exception {
        String result = target.getStringValue();
        System.out.println(result);

        result = target.getStringValue();
        System.out.println(result);

        assertThat(result, is("IT文化祭"));
    }


    @Test
    public void Listの操作をする() throws Exception {
        List<String> results = target.getListValue(listKey);

        assertThat(results.size(), is(2));
        assertThat(String.join(" ", results), is("IT 文化祭"));
    }

}