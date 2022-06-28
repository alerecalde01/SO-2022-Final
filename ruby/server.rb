require 'socket'
host = 'localhost'
puerto = 4000

server = TCPServer.open(host,puerto)                     #Obtenemos el puerto del servidor

puts "Servidor conectado en el #{puerto}..."
loop {
    Thread.start(server.accept) do |client|         #Conectamos el servidor
        
        mensaje_cliente = client.gets               #Obtenemos el mensaje del cliente
        puts "\nCliente: " + mensaje_cliente                        #Mostramos el mensaje en consola
        
        print "Servidor: "
        var = 0
        while var < 10                              #Creamos el loop infinito
            enviar = gets.chomp
            client.puts "Servidor: " + enviar  #Enviamos el mensaje al cliente
            mensaje_cliente = "Cliente: " + client.gets           #Obtenemos el mensaje del cliente
            puts   mensaje_cliente                    #Mostramos el mensaje del cliente
            print "Servidor: "

        end      
      
    end
}


#Para correr el archivo
#ruby servidor.rb