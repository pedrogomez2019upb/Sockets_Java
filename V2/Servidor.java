import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(13524);
            System.out.println("Esperando Nueva Conexión...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado.");

            List<Cancion> canciones = new ArrayList<>();
            canciones.add(new Cancion("Yambeque", 2020, "Pop"));
            canciones.add(new Cancion("The Four Horseman", 1995, "Rock"));
            canciones.add(new Cancion("Stereo Love", 2010, "Electrónica"));

            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            boolean clienteActivo = true;
            while (clienteActivo) {
                String consulta = (String) in.readObject();

                if (consulta.equalsIgnoreCase("salir")) {
                    clienteActivo = false;
                    System.out.println("Has salido!.");
                } else {
                    List<Cancion> resultado = buscarCanciones(canciones, consulta);
                    out.writeObject(resultado);
                }
            }

            serverSocket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static List<Cancion> buscarCanciones(List<Cancion> canciones, String consulta) {
        List<Cancion> resultado = new ArrayList<>();
        for (Cancion cancion : canciones) {
            if (cancion.getTitulo().equalsIgnoreCase(consulta) ||
                    String.valueOf(cancion.getAno()).equals(consulta) ||
                    cancion.getGenero().equalsIgnoreCase(consulta)) {
                resultado.add(cancion);
            }
        }
        return resultado;
    }
}
