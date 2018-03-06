let soap = require('soap');
let url = 'http://localhost:8080/library?wsdl';
let args = {name: 'value'};

soap.createClient(url, (err, client) => {
    client.getAllBooks(args, (err, result) => {
        console.log(result);
    });

    

});