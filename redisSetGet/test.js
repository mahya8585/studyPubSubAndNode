// Add your cache name and access key.
var client = require('redis').createClient(【PORT NUMBER】,【HOSTNAME】, {auth_pass: 【AUTH】, tls: {servername: 【HOSTNAME】】}});

client.set("key1", "Hello", function(err, reply) {
        console.log(reply);
});

client.get("key1",  function(err, reply) {
        console.log(reply);
});