//接続
var fs = require('fs');
var http = require('http');
var server = http.createServer();

server.on('request', function(req, res) {
  var stream = fs.createReadStream('pubsub.html');
  res.writeHead(200, {'Content-Type': 'text/html'});
  stream.pipe(res);
});
var io = require('socket.io').listen(server);
server.listen(3001);

//redis接続
var client = require('redis').createClient(【PORT NUMBER】,【HOSTNAME】, {auth_pass: 【AUTH】, tls: {servername: 【HOSTNAME】】}});

//sub設定
client.subscribe('test');
client.on('message', function(channel, message) {
    console.log('channel: ' + channel + ', message: ' + message);
    io.sockets.emit('msg', {msg: message});
});
console.log('load subscriber : end');

//pub設定
io.sockets.on('connection', function (socket) {
    socket.on('publisher', function (msg) {
        console.log('start publish');
        var publisher = require('redis').createClient(【PORT NUMBER】,【HOSTNAME】, {auth_pass: 【AUTH】, tls: {servername: 【HOSTNAME】】}});
        publisher.publish('test', msg);
        console.log('end publish');
    });
});

