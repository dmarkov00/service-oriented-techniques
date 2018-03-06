let soap = require('soap');
let url = 'http://localhost:8080/library?wsdl';
let args = {name: 'value'};


console.log("Our Inventory in JSON:");
console.log();
soap.createClient(url, (err, client) => {
    client.getAllBooks({bookName: 'Orange juice'}, (err, result) => {

        console.log(result);
    });


});