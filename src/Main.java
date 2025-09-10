import java.util.ArrayList;
import java.util.Scanner;

public static Playlist playlist = new Playlist();
public static Scanner scanner = new Scanner(System.in);
public static ArrayList<Album> arrayOfAlbums = new ArrayList<>();

public static void main() {
    Album heathen = new Album("Heathen");
    arrayOfAlbums.add(heathen);
    heathen.addSong("I Would Be Your Slave", 300);
    heathen.addSong("Everyone Says Hi", 180);
    heathen.addSong("A Better Future", 240);
    heathen.addSong("Slow Burn", 330);
    heathen.addSong("Heathen", 360);
    playlist.addSong(heathen, "I Would Be Your Slave");
    playlist.addSong(heathen, "A Better Future");
    playlist.addSong(heathen, "Slow Burn");

    System.out.println(playlist);

    mainMenu();
}

private static void mainMenu() {
    loop: while (true) {
        System.out.println("""
                Welcome to the Java Music Player. What would you like to do?
                Press 1 to start playing music.
                Press 2 to print the playlist.
                Press 3 to check if a song is in the playlist.
                Press 4 to add song to the playlist.
                Press 5 to remove a song from playlist.
                Press 6 to add an album.
                Press 7 to add song to an album.
                Press 8 to sort playlist by length in descending order.
                Press 9 to sort playlist by length in ascending order.
                Press 10 to print your entire library.
                Press 11 to terminate the program.
                """);

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> startMusic();
            case 2 -> System.out.println(playlist);
            case 3 -> checkSong();
            case 4 -> addSongToPlaylist();
            case 5 -> removeSong();
            case 6 -> addAlbum();
            case 7 -> addSongToAlbum();
            case 8 -> playlist.sortByDescendingLength();
            case 9 -> playlist.sortByAscendingLength();
            case 10 -> printLibrary();
            case 11 -> {
                break loop;
            }
            default -> System.out.println("Invalid input entered.");
        }
    }
}

private static void startMusic() {
    playlist.startPlaylist();
    System.out.println(playlist);

    loop: while (true) {
        System.out.println("""
                \nPress 1 to go to the next song.
                Press 2 to go back to the previous song.
                Press 3 to rewind the song.
                Press 4 to remove current song.
                Press 5 to go back to the main menu.""");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> nextSong();
            case 2 -> previousSong();
            case 3 -> rewind();
            case 4 -> removeCurrentSong();
            case 5 -> {
                break loop;
            }
            default -> System.out.println("Invalid input entered.");
        }
    }
}

private static void nextSong() {
    Song song = playlist.nextSong();
    if (song == null) {
        System.out.println("Reached the end of the list.");
    } else {
        System.out.format("Now playing: %s", song.getTitle());
    }
}

private static void previousSong() {
    Song song = playlist.previousSong();
    if (song == null) {
        System.out.println("Reached the start of the list.");
    } else {
        System.out.format("Now playing %s", song.getTitle());
    }
}

private static void rewind() {
    Song song = playlist.rewind();
    System.out.println(song == null ? "No track playing." : String.format("%s being replayed.", song.getTitle()));
}

private static void removeCurrentSong() {
    String[] value = playlist.removeCurrentSong();
    if (value[0].equals("Deleted previous entry")) {
        System.out.format("Successfully deleted: %s", value[1]);
    } else if (value[0].equals("Deleted next entry")) {
        System.out.format("Successfully deleted %s", value[1]);
    } else {
        System.out.println("No song playing.");
    }
}

private static void checkSong() {
    System.out.println("Which song's presence would you like to check in the playlist?");
    String title = scanner.nextLine();
    boolean songCheck = playlist.checkPlaylist(title);
    System.out.println(songCheck ? String.format("%s found.", title) : String.format("%s not found.", title));
}

private static void removeSong() {
    System.out.println("Enter name of the song that you would like to remove: ");
    String title = scanner.nextLine();

    boolean b = playlist.removeSong(title);
    System.out.println(b ? String.format("%s not found.", title) : String.format("%s removed.", title));
}

private static void addSongToPlaylist() {
    System.out.println("Enter album title: ");
    String albumTitle = scanner.nextLine();
    Album album = null;

    for (Album albumName : arrayOfAlbums) {
        if (albumName.getName().equals(albumTitle))
            album = albumName;
    }

    if (album == null) {
        System.out.format("%s not found.", albumTitle);
        return;
    }

    System.out.println("Enter song title: ");
    String song = scanner.nextLine();

    int check = playlist.addSong(album, song);
    if (check == 1) {
        System.out.format("%s successfully added.", song);
    } else if (check == 0) {
        System.out.format("%s already exists in the playlist.", song);
    } else {
        System.out.format("%s not found on %s", song, albumTitle);
    }
}

private static void addAlbum() {
    System.out.println("Enter name of the album.");
    String title = scanner.nextLine();
    for (Album album : arrayOfAlbums) {
        if (album.getName().equals(title)) {
            System.out.format("An album called %s already exists.", title);
            return;
        }
    }

    Album album = new Album(title);
    arrayOfAlbums.add(album);
    System.out.format("%s successfully added to your library.", title);
}

private static void addSongToAlbum() {
    System.out.println("Which album do you want to add a song to?");
    String albumTitle = scanner.nextLine();

    for (Album album : arrayOfAlbums) {
        if (album.getName().equals(albumTitle)) {
            System.out.print("Enter title of the song: ");
            String title = scanner.nextLine();

            System.out.print("Enter length of the song: ");
            int length = scanner.nextInt();

            boolean check = album.addSong(title, length);
            if (check) {
                System.out.format("%s successfully added to %s.", title, albumTitle);
            } else {
                System.out.format("%s already exists on %s", title, albumTitle);
            }
            return;
        }
    }

    System.out.format("No %s found.", albumTitle);
}

private static void printLibrary() {
    int albumCount = 1;
    for (Album album : arrayOfAlbums) {
        System.out.format("\t%d - %s", albumCount++, album.getName().toUpperCase());
        int songCount = 1;
        for (Song song : album.getSongs()) {
            System.out.format("%d. %s", songCount++, song);
        }
    }
}