## 使う前にやるべきこと

① Java SE 8 の環境を整える

② redis接続先設定を書き換える      
接続先設定（Redis)を書き換える。   
e.g.)   
JedisShardInfo settings = new JedisShardInfo(【HOSTNAME】, 【PORT NUMBER】);
↓   
JedisShardInfo settings = new JedisShardInfo(foo.bar.server.net, 6379);    
   
   
## 作ったテストアプリ
- JedisPublish   
指定したチャネルに文字列をpubする。今はテストなので送れる文字列もチャネルも固定になってる。    
   
- JedisSubsclibe   
指定したチャネル(今は固定)をsubする。現在subを終了する処理は織り込んでいない。
停止させたい場合はJedisPubSub.unsubscribe()を呼び出す。
Redisと切断する場合はJedis.quit().   
   
- JedisPSubsclibe   
subするチャネルの設定をパターンマッチングにできる。それ以外はJedisSubsclibeの処理と同じ。   
   
