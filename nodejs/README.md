## 使う前にやるべきこと

① npmしる。   
- redis   
- socket.io   
- socket.io-redis   

② redis接続先設定を書き換える      
各JS内の接続先設定（主にRedis)を書き換える。   
e.g.)   
var client = require('redis').createClient(【PORT NUMBER】,【HOSTNAME】,{auth_pass: 【AUTH】, tls: {servername: 【HOSTNAME】】}});   
↓   
var client = require('redis').createClient(6380,'foo.bar.server.net', {auth_pass: 'asdjklrgjwekjESGJWEah4357=', tls: {servername: 'foo.bar.server.net'}});   
   
   
## 作ったテストアプリ
① 各フォルダ内のjsをnodeで実行する。   
e.g.) node server.js   

② htmlが付属されている場合はブラウザから対象ポートに接続する   
e.g.) index.htmlがある&js内で指定されたポートが8000の場合は以下URLで接続できるようにしてあります   
http://localhost:8000/   
   
- redisSetGet   
まずはRedisを使ってみよう。Pub/Sub機能ではなくて普通のKVS機能のお試しアプリ。   
データをsetして、setしたデータをgetしてくる。コンソールに表示する。それだけ。   
   
- redisPubSubOnNode   
実行するとpubもしくはsubしてくれるアプリ。それだけ。   
   
- sockettest   
socket.ioお試し処理。   
実行して8000ポートで接続するとhelloって表示される。それだけ。   
   
- redisAndSocket   
Socket.io-redisを使わないでpubsubしたアプリ。   
今回実現したかった機能はこれっぽい。   
   
- socketIoRedis   
socket.io-redisを使ってpubsubしたアプリ。チャットツールライクな作り。   
ブラウザ同士というか、表示しあいっこするならこれがよい。   