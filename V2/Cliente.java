import java.io.*;
import java.net.*;
import java.util.List;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 13524);
            System.out.println("Conectado al servidor.");

            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());

            boolean clienteActivo = true;
            while (clienteActivo) {
                System.out.println("Ingrese la consulta (título, año y/o género), o escriba 'salir' para salir:");
                String consulta = inFromUser.readLine();

                outToServer.writeObject(consulta);

                if (consulta.equalsIgnoreCase("salir")) {
                    clienteActivo = false;
                } else {
                    List<Cancion> resultado = (List<Cancion>) inFromServer.readObject();
                    if (resultado.isEmpty()) {
                        System.out.println("No se encontraron canciones con la solicitud propueseta.");
                    } else {
                        System.out.println("Canciones encontradas:");
                        for (Cancion cancion : resultado) {
                            System.out.println(cancion);
                        }
                    }
                }
            }

            System.out.println("Te has desconectado del servidor.");
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
