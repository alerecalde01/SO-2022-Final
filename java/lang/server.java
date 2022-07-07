import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class server {
    public static void main(String[] args){
        final ServerSocket serverSocket ;
        final Socket clientSocket ;
        final BufferedReader in;
        final PrintWriter out;
        final Scanner sc = new Scanner(System.in);

        try {
            serverSocket = new ServerSocket(4000);
            System.out.println("Servidor corriendo.. " );
            clientSocket = serverSocket.accept();
            System.out.println("Conexion establecida\n");
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader (new InputStreamReader(clientSocket.getInputStream()));

            Thread sender = new Thread(new Runnable() {
                String msg; // Variable que contiene los datos ingresados por el cliente
                @Override
                public void run() {
                    while(true){
                        msg = sc.nextLine(); // Permite leer los datos ingresados por el cliente a través del teclado
                        out.println(msg);
                        out.flush();   // Fuerza el envío e impresión de los datos en el socket
                    }
                }
            });
            sender.start();

            Thread receive = new Thread(new Runnable() {
                String msg;
                @Override
                public void run() {
                    try {
                        msg = in.readLine();
                        // Loop que se ejecuta siempre que el cliente esté conectado
                        while(msg != null){
                            System.out.println("Cliente: " + msg);
                            msg = in.readLine();
                        }

                        System.out.println("El Cliente se ha desconectado");

                        out.close();
                        clientSocket.close();
                        serverSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            receive.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}