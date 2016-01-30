//import com.mashape.unirest.http.Unirest

print(1 + 2);

var Unirest = Java.type('com.mashape.unirest.http.Unirest');

var jsonResponse = Unirest.get("https://api.github.com/zen").asJson()

print(jsonResponse);

