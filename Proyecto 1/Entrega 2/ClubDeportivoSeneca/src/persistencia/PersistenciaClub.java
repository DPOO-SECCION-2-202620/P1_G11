package persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import modelo.ClubDeportivo;

public class PersistenciaClub {
    
    public static void guardar(ClubDeportivo club, String ruta) throws IOException {
        try (ObjectOutputStream salida = new ObjectOutputStream(
            new FileOutputStream(ruta)
        )) {
            salida.writeObject(club);
        }
    }

    public static ClubDeportivo cargar(String ruta) throws IOException, ClassNotFoundException {
        try (ObjectInputStream entrada = new ObjectInputStream(
            new FileInputStream(ruta)
        )) {
            return (ClubDeportivo) entrada.readObject();
        }
    }
}
