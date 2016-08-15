//接続
var fs = require('fs');
var server = require('http').createServer();

server.on('request', function(req, res) {
  var stream = fs.createReadStream('chat.html');
  res.writeHead(200, {'Content-Type': 'text/html'});
  stream.pipe(res);
});
var io = require('socket.io').listen(server);
server.listen(3001);

//redisサーバーの接続先情報を定義します
var client = require('redis').createClient(【PORT NUMBER】,【HOSTNAME】, {auth_pass: 【AUTH】, tls: {servername: 【HOSTNAME】】}});
var redis = require('socket.io-redis');
io.adapter(redis(client));

//コネクション
io.sockets.on('connection', function (socket) {
  //コネクション時のwelcomeメッセージ
  socket.emit('greeting', { msg: 'Welcome!' }, function (data) {
    console.log('result: ' + data);
  });

  //メッセージの取得とemit
  socket.on('msg', function (msg) {
      io.sockets.emit('msg', {msg: msg});
  });

});