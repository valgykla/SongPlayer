package lt.vtvpmc.ems.valgykla;

import java.util.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {

    public static void main(String[] args)
    {
        float spee = (float) 2;
        final Song song = new Song("Yesterday", Arrays.asList("Yesterday all my troubles seemed so far away.",
                "Now it looks as though they're here to stay.",
                "Oh, I believe in yesterday.",
                "Suddenly I'm not half the man I used to be.",
                "There's a shadow hanging over me.",
                "Oh, yesterday came suddenly.",
                "Why she had to go, I don't know, she wouldn't say.",
                "I said something wrong, now I long for yesterday.",
                "Yesterday love was such an easy game to play.",
                "Now I need a place to hide away.",
                "Oh, I believe in yesterday.",
                "Why she had to go, I don't know, she wouldn't say.",
                "I said something wrong, now I long for yesterday.",
                "Yesterday love was such an easy game to play.",
                "Now I need a place to hide away.",
                "Oh, I believe in yesterday.",
                "Mm mm mm mm mm mm mm"));


        try // Oh boy, lets begin.
        {
            //int name=0; //index of song name
            String sng_varnme[] = {"songspeed=", "songname="};

            for(int i=0; i<args.length;i++) // Some one will want to to add speed.
            {
                if(args[i].regionMatches(false, 0, sng_varnme[0], 0, sng_varnme[0].length()))
                {
                    try { spee = Float.parseFloat(args[i].replace(sng_varnme[0], "")); } // Fail and see if it worked,
                    catch(Exception|Error e) {} // Just let it pass.
                }
            }

            for(int i=0; i<args.length;i++)
            {
                if(args[i].regionMatches(false, 0, sng_varnme[1], 0, sng_varnme[1].length()))
                {

                    args[i] = args[i].replace(sng_varnme[1], "");

                    Path curRelPath = Paths.get("");
                    String curpath = curRelPath.toAbsolutePath().toString();

                    String pathseparator = "/";
                    if(new String(System.getProperty("os.name").toLowerCase()).contains("win")) //Windows AKA the worst system.
                    {
                        pathseparator = "\\"; //WHY?
                    }

                    //if(!args[i].regionMatches(0,pathseparator, 1, 1))

                        String[] pathmass = curpath.split(pathseparator);
                        String[] pathtofile = args[i].split(pathseparator);

                        int howtocut = 0;

                        for (int v = 0; v < pathtofile.length; v++)
                            if (pathtofile[v].contains(".."))
                                howtocut++;

                        curpath = "";

                        for (int v = 1; v < (pathmass.length - howtocut); v++) {
                            curpath += pathseparator + pathmass[v];
                        }

                        for (int v = howtocut; v < (pathtofile.length - 1); v++) {
                            curpath += pathseparator + pathtofile[v];
                        }

                        curpath += pathseparator;
                    /*
                    else
                    {
                        curpath = "";
                        curpath = args[i];
                    }*/

                    System.out.println(curpath);

                    //String[] names = {curpath + pathtofile[pathtofile.length-1] + ".ogg", curpath + pathtofile[pathtofile.length-1] + ".txt"};

                    File sng = new File(curpath + pathtofile[pathtofile.length-1] + ".ogg");     //It's to check if it exists.
                    File lyr = new File(curpath + pathtofile[pathtofile.length-1] + ".txt");     //To check if it exists, as well as reading from file
                    if (sng.exists() && lyr.exists())                     // Existential crisis.
                    {
                        List<String> newLyr = new ArrayList<String>();                              //I need a new list, not you.
                        Scanner scam = new Scanner(lyr);                 // New nigerian prince SCAM.

                        while (scam.hasNextLine())                        // We don't want to crash everything, am I right?
                            newLyr.add(scam.nextLine());

                        scam.close();                                   // Nice and tidy
                        song.setTitle(pathtofile[pathtofile.length-1]);                         //Setting a new title.
                        song.setLyrics(newLyr);                         //REPLACE OLD WITH THE NEW. YEAH!!!
                        song.setWorkDir(curpath);                       //Set the working dir.
                    } // Fin
                }
            }
        }
        catch (Exception|Error e){} // Shh, we don't want for the user to see that we're burning and crashed in to WTC on 9/11 street.

        final SongPlayer player = new SongPlayer();
        player.setSong(song);
        player.setSpeed(spee);
        player.play();
    }
}
