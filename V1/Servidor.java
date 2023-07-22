import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(13524);
            System.out.println("Esperando conexiones nuevas...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado.");

            BufferedReader in = new BufferedReader(new 
InputStreamReader(clientSocket.getInputStream()));

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Mensaje recibido: " + message);
            }

            System.out.println("Cliente desconectado.");
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

