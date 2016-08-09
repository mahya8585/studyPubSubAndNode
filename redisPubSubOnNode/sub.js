var subscriber = require('redis').createClient(【PORT NUMBER】,【HOSTNAME】, {auth_pass: 【AUTH】, tls: {servername: 【HOSTNAME】】}});

//subscribeするチャネルの設定
subscriber.subscribe('test');
subscriber.on('message', function(channel, message) {
	console.log('channel: ' + channel + ', message: ' + message);
});

//CLIか何かでpubしてね