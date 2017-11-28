package lt.vtvpmc.ems.valgykla;

import java.util.List;

public class Song {

    private String sng;
    private String work_dir = ""; // Added due to the need for variable to working to dir.
    private List lyr;

    public Song(String title, List<String> lyrics)
    {
        this.lyr = lyrics;
        this.sng = title;
    }

    public void setWorkDir(String str) // Added due to the need for variable to working to dir.
    {
        this.work_dir = str;
    }

    public String getWorkDir() // Added due to the need for variable to working to dir.
    {
        return this.work_dir;
    }

    public Object getLyrics(int i)
    {
        return this.lyr.get(i);
    }

    public String getTitle()
    {
        return this.sng;
    }

    public void setTitle(String newsng)
    {
        this.sng = newsng;
    }

    public void setLyrics(List newlyr)
    {
        this.lyr = newlyr;
    }

    public int size()
    {
        return this.lyr.size();
    }
}