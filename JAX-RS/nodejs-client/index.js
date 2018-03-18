let Client = require('node-rest-client').Client;
 
let client = new Client();
 
// direct way 
client.get("http://localhost:8080/rest/library/books",  (data, response) => {
	console.log('Out inventory in JSON:')
	console.log();

    console.log(data);
   
});