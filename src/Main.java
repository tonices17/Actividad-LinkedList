import javax.sound.midi.Soundbank;
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void imprimirListarep(LinkedList<Cancion> playlist) {
        System.out.println("------------------------------------------------");
        System.out.println("Estas son las canciones que hay en tu lista de reproducción:");
        int cont = 1;
        ListIterator<Cancion> it = playlist.listIterator();
        while (it.hasNext()) {
            System.out.println("Canción " + cont + ": " + it.next());
            cont++;
        }
        System.out.println("------------------------------------------------");
    }

    public static void imprimirMenu() {
        System.out.println("--------------------- MENÚ ---------------------");
        System.out.println("0 - Salir de la lista de reproducción");
        System.out.println("1 - Reproducir la siguiente canción en la lista");
        System.out.println("2 - Reproducir la canción previa de la lista");
        System.out.println("3 - Repetir la canción actual");
        System.out.println("4 - Imprimir la lista de canciones en la playlist");
        System.out.println("5 - Volver a imprimir el menú");
        System.out.println("6 - Eliminar la canción actual de la playlist");
        System.out.println("------------------------------------------------");
    }

    public static void play(LinkedList<Cancion> playlist) {
        boolean valido, continuar = true, haciaAdelante = true;
        String opcion;
        ListIterator<Cancion> it = playlist.listIterator();

        System.out.println("*** Vamos a reproducir la playlist \"TEMAZOS\" ***");
        System.out.println("\nEmpieza sonando -> "+playlist.getFirst().getTitulo()+"\n");
        it.next();
        while (continuar) {
            do {
                valido = true;
                imprimirMenu();
                System.out.println("Elige una opción: ");
                opcion = scanner.nextLine();
                if (!opcion.matches("[0-6]")) {
                    System.out.println("Opción no válida.");
                    valido = false;
                }
            } while (!valido);

            switch (opcion) {
                case "0":
                    continuar = false;
                    break;
                case "1":
                    try{
                        if (!haciaAdelante) {
                            if (it.hasNext()) {
                                it.next();
                                haciaAdelante = true;
                            }
                        }
                        if (it.hasNext()) {
                            System.out.println("Reproduciendo siguiente canción -> " + it.next().getTitulo()+"\n");
                        } else {
                            System.out.println("Estás en el final de la playlist, ya no hay más canciones.");
                            System.out.println("Ahora está sonando -> " + playlist.getLast().getTitulo()+"\n");
                            haciaAdelante = false;
                            it.previous();
                        }
                    } catch (NoSuchElementException e) {
                        System.out.println("La playlist está vacía. Todas las canciones han sido borradas." + "\n");
                    }
                    break;
                case "2":
                    try{
                        if(haciaAdelante){
                            if(it.hasPrevious()){
                                it.previous();
                                haciaAdelante = false;
                            }
                        }
                        if(it.hasPrevious()){
                            System.out.println("Reproduciendo la canción anterior -> " + it.previous().getTitulo()+"\n");
                        } else {
                            System.out.println("Estás en el principio de la playlist, ya no hay más canciones.");
                            System.out.println("Ahora está sonando -> " + playlist.getFirst().getTitulo()+"\n");
                            haciaAdelante = false;
                        }
                    } catch (NoSuchElementException e){
                        System.out.println("La playlist está vacía. Todas las canciones han sido borradas." + "\n");
                    }
                    break;
                case "3":
                    if(haciaAdelante){
                        try{
                            it.previous();
                            System.out.println("En estos momentos está sonando -> " + it.next().getTitulo()+"\n");
                        }catch (NoSuchElementException e){
                            System.out.println("La playlist está vacía. Todas las canciones han sido borradas."+"\n");
                        }
                    } else {
                        it.next();
                        System.out.println("En estos momentos está sonando -> " + it.previous().getTitulo()+"\n");
                    }
                    break;
                case "4":
                    imprimirListarep(playlist);
                    break;
                case "5":
                    imprimirMenu();
                    break;
                case "6":
                    try{
                        if(haciaAdelante){
                            it.previous();
                            System.out.println("En estos momentos está sonando -> " + it.next().getTitulo());
                            System.out.println("Eliminando....");
                        } else {
                            it.next();
                            System.out.println("Está sonando: " + it.previous().getTitulo());
                            System.out.println("Eliminando....");
                        }
                        try{
                            Thread.sleep(1000);
                        }catch (InterruptedException e ){
                            System.out.println("La espera se ha visto interrumpida.");
                        }
                        it.remove();
                        if(it.hasNext()){
                            try {
                                it.previous();
                            }catch (NoSuchElementException ignored){}
                            System.out.println("En estos momentos está sonando -> " + it.next().getTitulo()+"\n");
                        }else{
                            try {
                                it.next();
                            }catch (NoSuchElementException ignored){}
                            System.out.println("En estos momentos está sonando -> " + it.previous().getTitulo()+"\n");
                        }
                    }catch (NoSuchElementException e){
                        System.out.println("No hay canciones en tu lista de reproducción.");
                    }
                    break;
            }
        }
    }

    public static void main(String[] args) {

        ArrayList<Album> albumes = new ArrayList<>();
        Album album1 = new Album("Un verano sin ti", "Bad Bunny");
        Album album2 = new Album("Cracker Island", "Gorillaz");
        Album album3 = new Album("72 Seasons", "Metallica");
        album1.addSong("Moscow Mule", 3.40);
        album1.addSong("Me Porto Bonito", 2.30);
        album1.addSong("Titi me Pregunto", 4.0);
        album2.addSong("Baby Queen", 3.50);
        album2.addSong("Tarantula", 2.45);
        album2.addSong("Skinny Ape", 3.32);
        album3.addSong("Shadows Follow", 4.10);
        album3.addSong("Screaming Suicide", 3.21);
        album3.addSong("Inamorata", 2.56);
        albumes.add(album1);
        albumes.add(album2);
        albumes.add(album3);

        LinkedList<Cancion> playlist = new LinkedList<>();
        album1.addToPlayList(0, playlist);
        album1.addToPlayList("Titi me Pregunto", playlist);
        album2.addToPlayList(1, playlist);
        album2.addToPlayList("Baby Queen", playlist);
        album3.addToPlayList(2, playlist);
        album3.addToPlayList("Shadows Follow", playlist);

        play(playlist);
    }
}
