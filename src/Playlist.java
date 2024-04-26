import java.util.*;

public class Playlist {
    private final LinkedList<Song> songs = new LinkedList<>();
    private ListIterator<Song> playlistIterator;
    private boolean goingForward = true;

    public int addSong(Album album, String title) {
        Song song = album.findSong(title);
        boolean value = checkPlaylist(song);

        if (song == null) {
            return -1;
        } else if (value) {
            return 0;
        }

        songs.add(song);
        return 1;
    }

    public void addSong(Album album, int trackNo) {
        Song song = album.findSong(trackNo);
        boolean value = checkPlaylist(song);

        if (song == null || value) {
            return;
        }

        songs.add(song);
    }

    public boolean removeSong(String title) {
        for (Song song : songs) {
            if (song.getTitle().equals(title)) {
                songs.remove(song);
                return true;
            }
        }

        return false;
    }

    public void startPlaylist() {
        playlistIterator = songs.listIterator();
    }

    public Song nextSong() {
        if(!goingForward) {
            playlistIterator.next();
            goingForward = true;
        }

        return playlistIterator.hasNext() ? playlistIterator.next() : null;

    }

    public Song previousSong() {
        if (goingForward && playlistIterator.hasPrevious()) {
            playlistIterator.previous();
            goingForward = false;
        }

        return playlistIterator.hasPrevious() ? playlistIterator.previous() : null;
    }

    public String[] removeCurrentSong() {
        if (goingForward && playlistIterator.hasPrevious()) {

            String deletedItem = playlistIterator.previous().getTitle();
            playlistIterator.next();

            playlistIterator.remove();
            return new String[] {"Deleted previous entry: ", deletedItem};

        } else if (!goingForward && playlistIterator.hasNext()) {

            String deletedItem = playlistIterator.next().getTitle();
            playlistIterator.previous();

            playlistIterator.remove();
            return new String[] {"Deleted next entry: ", deletedItem};
        } else {
            return new String[] {"a" , "a"}; //dummy values
        }
    }

    public Song rewind() {
        if (playlistIterator.hasPrevious() && goingForward) {
            goingForward = false;
            return playlistIterator.previous();
        } else if (playlistIterator.hasNext() && !goingForward) {
            goingForward = true;
            return playlistIterator.next();
        } else {
            return null;
        }
    }

    public boolean checkPlaylist(String title) {
        return songs.stream().anyMatch(song -> song.getTitle().equals(title));
    }

    private boolean checkPlaylist(Song songToCheck) {
        return songs.stream().anyMatch(song -> song == songToCheck);
    }

    public void sortByDescendingLength() {
        songs.sort((o1, o2) -> Double.compare(o2.getLength(), o1.getLength()));

        System.out.println(this);
    }

    public void sortByAscendingLength() {
        songs.sort(Comparator.comparingDouble(Song::getLength));

        System.out.println(this);
    }

    @Override
    public String toString() {
        System.out.println("Lance's Playlist: ");
        int count = 1;
        StringBuilder stringBuilder = new StringBuilder();
        for (Song song : songs) {
            stringBuilder.append(count++).append(". ").append(song).append("\n");
        }

        return stringBuilder.toString();
    }
}
