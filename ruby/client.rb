require 'socket'

hostname = 'localhost' #Indicamos el host
port = 4000            #Indicamos el puerto

conexion= TCPSocket.open(hostname, port) #Abrimos la conexion



loop do
    print "\nCliente: "                 #Comenzamos el loop
    mensaje = gets
    conexion.puts mensaje  #Enviamos el mensaje al servidor
    mensaje_servidor = conexion.gets          #Obtenemos el mensaje del servidor
    puts  "\nServidor: " + mensaje_servidor                    #Mostramos el mensaje en consola
    
end


#Para correr el programa:
#Correr el archivo servidor.rb primero
#ruby cliente.rb
