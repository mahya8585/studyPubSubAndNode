var publisher = require('redis').createClient(【PORT NUMBER】,【HOSTNAME】, {auth_pass: 【AUTH】, tls: {servername: 【HOSTNAME】】}});

//publishするチャネル名＋送信する文字列
publisher.publish('test', 'FROM pub.js!!!!');


//CLIか何かでsubしてね