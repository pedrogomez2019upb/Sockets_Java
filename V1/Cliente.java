import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 13524);
            System.out.println("Conectado al servidor.");

            BufferedReader inFromUser = new BufferedReader(new 
InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), 
true);

            String message;
            do {
                System.out.print("Escribe un mensaje o 'salir' para salir: ");
                message = inFromUser.readLine();
                out.println(message);
            } while (!message.equalsIgnoreCase("salir"));

            System.out.println("Te has desconectado del servidor.");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

