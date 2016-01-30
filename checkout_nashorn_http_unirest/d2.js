var unirest = require('unirest');

//unirest.get("https://api.github.com/zen")
unirest.get("https://api.github.com/users/svene")
.header('Accept', 'application/json')
.header('User-Agent', 'Unirest Node.js')
.send()
.end(function (response) {
  console.log(response.body);
});

