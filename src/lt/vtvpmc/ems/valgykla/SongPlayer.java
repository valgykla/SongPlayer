package lt.vtvpmc.ems.valgykla;

public class SongPlayer {
    private int speed;
    private int milliseconds;
    private Song song;

    public void setSpeed(int speed) {
        this.speed = 1000/speed;
    }

    public void setSong(final Song song) {
        this.song=song;
    }

    public void play() {

        for (int i = 0; i < song.getLyrics().size(); i++) {
            System.out.println("[" + song.getTitle() + "]" + song.getLyrics().get(i));
            Pause.duration(this.speed);
        }

    }

}
