import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class cliente {
    public static void main(String[] args){
        final Socket clientSocket; // socket utilizado para enviar y revicir información del server
        final BufferedReader in;   // Se utiliza para leer datos del socket
        final PrintWriter out;     // Se utiliza para escribir datos del socket
        final Scanner sc = new Scanner(System.in); // Lee lo escrito por teclado

        try {
            String hostname = "localhost";
            int port = 4000;
            clientSocket = new Socket(hostname,port);
            System.out.println("Conexion establecida\n");
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            
            Thread sender = new Thread(new Runnable() {
                String msg;
                @Override
                public void run() {
                    while(true){
                        msg = sc.nextLine();
                        out.println(msg);
                        out.flush();
                    }
                }
            });
            sender.start();
            Thread receiver = new Thread(new Runnable() {
                String msg;
                @Override
                public void run() {
                    try {
                        msg = in.readLine();
                        while(msg!=null){
                            System.out.println("Servidor: " + msg);
                            msg = in.readLine();
                        }
                        System.out.println("Servidor fuera de servicio");
                        out.close();
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            receiver .start();
    }catch (IOException e){
        e.printStackTrace();
        }
    }
}

