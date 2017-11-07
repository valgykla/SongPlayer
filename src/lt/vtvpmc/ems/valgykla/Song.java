package lt.vtvpmc.ems.valgykla;

import java.util.List;

public class Song {
    private String title;
    private List<String> lyrics;

    public Song(String title, List<String> lyrics) {
        this.title = title;
        this.lyrics=lyrics;
    }

    public List<String> getLyrics() {
        return lyrics;
    }

    public String getTitle() {
        return title;
    }
}
