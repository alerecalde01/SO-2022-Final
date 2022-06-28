const net = require('net');
const readline = require('readline-sync')

const server = net.createServer()

server.on('connection', (socket)=>{
    console.log('Conexion establecida')
    socket.on('data', (data)=>{
        console.log('\nCliente: ' + data)
        sendLine()
    })

    // socket.on('close', ()=>{
    //     console.log('Comunicación finalizada')
    // })

    socket.on('error', (err)=>{
        console.log(err.message)
    })

    function sendLine(){
        var line = readline.question('Servidor: ')
        if (line == "0"){
            socket.end()
        }else{
            socket.write(line)
        }
    
    }

})

server.listen(4000, ()=>{
    console.log('servidor esta escuchando en la puerta', server.address().port)
})