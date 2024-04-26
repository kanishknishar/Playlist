import java.util.ArrayList;

public class Album {
    private final String name;
    private final ArrayList<Song> songs = new ArrayList<>();

    public Album(String name) {
        this.name = name;
    }

    public boolean addSong(String title, int length) {
        Song song = findSong(title);

        if (song != null) {
            return false;
        }

        songs.add(new Song(title, length));
        return true;
    }

    public Song findSong(String title) {
        for (Song song : songs) {
            if (song.getTitle().equals(title)) {
                return song;
            }
        }

        return null;
    }

    public Song findSong(int trackNo) {
        int index = trackNo - 1;
        if (index < 0 || trackNo >= songs.size()) {
            return null;
        }

        return songs.get(index);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }
}
