import javax.crypto.spec.PSource;
import java.util.ArrayList;
import java.util.LinkedList;

public class Album {
    private String nombre, artista;
    private static ArrayList<Cancion> canciones;
    private static boolean devolver;

    public Album(String nombre, String artista) {
        this.nombre = nombre;
        this.artista = artista;
        canciones = new ArrayList<>();
    }

    private static Cancion findSong(String titulo) {
        for (int i = 0; i < canciones.size(); i++) {
            if (canciones.get(i).getTitulo().equalsIgnoreCase(titulo)) {
                return canciones.get(i);
            }
        }
        return null;
    }

    public boolean addSong(String titulo, double duracion) {
        devolver = false;
        if (findSong(titulo) == null) {
            canciones.add(new Cancion(titulo, duracion));
            devolver = true;
        } else {
            System.out.println("La canción que has intentado introducir ya se encuentra en el album.");
        }
        return devolver;
    }

    public boolean addToPlayList(int numpista, LinkedList<Cancion> listarep) {
        devolver = false;
        if (numpista <= canciones.size()) {
            listarep.add(canciones.get(numpista));
            devolver = true;
        } else {
            System.out.println("No hay ninguna canción en el índice introducido. No se ha podido añadir la canción.");
        }
        return devolver;
    }

    public boolean addToPlayList(String titulo, LinkedList<Cancion> listarep) {
        devolver = false;
        Cancion cancion = findSong(titulo);
        if (findSong(titulo) == null) {
            System.out.println("La canción que has introducido no se encuentra en la playlist.");
        }else{
            listarep.add(cancion);
            devolver = true;
        }
        return devolver;
    }
}
