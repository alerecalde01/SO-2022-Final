import java.net.*;
import java.io.*;
import java.util.Scanner;

public class server{
    static int PUERTO = 4000;
    ServerSocket sc;
    Socket so;
    DataOutputStream salida;
    DataInputStream entrada;
    String mensajeRecibido;

    /**
     * 
     */

//     BufferedReader d
// = new BufferedReader(new InputStreamReader(in));




    public void initServidor(){
        
        Scanner teclado = new Scanner(System.in);
        try{
            sc = new ServerSocket(PUERTO);
            so = new Socket();
            
            System.out.println("Corriendo servidor en  " + PUERTO);
            so = sc.accept();
            System.out.println("Coexion establecida\n");
            BufferedReader d = new BufferedReader(new InputStreamReader(System.in));
            salida = new DataOutputStream(so.getOutputStream());
            String msn = "";

            while(!msn.equals("x")){
                
                mensajeRecibido = d.readLine();//Leemos respuesta
                System.out.println("Cliente: " + mensajeRecibido);
                System.out.println("Servidor: ");
                msn = teclado.nextLine();
                salida.writeUTF(msn);//enviamos mensaje

            }
            sc.close();
        }catch(Exception e){
                
        }
    }

    public static void main(String[] args){
        server o = new server();
        o.initServidor();
    }
}