package lt.vtvpmc.ems.valgykla;

import java.util.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {

    public static void main(String[] args)
    {
        float spee = (float) 1;
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
            String sng_varnme[] = {"songspeed=", "songname="};

            for(int i=0; i<args.length;i++) // Some one will want to to add speed.
            {
                if(args[i].regionMatches(false, 0, sng_varnme[0], 0, sng_varnme[0].length()))
                {
                    try { spee = Float.parseFloat(args[i].replace(sng_varnme[0], "")); } // Fail and see if it worked
                    catch(Exception|Error e) {} // Just let it pass.
                }
            }

            for(int i=0; i<args.length;i++) // TODO: Add ~ and / support.
            {
                if(args[i].regionMatches(false, 0, sng_varnme[1], 0, sng_varnme[1].length()))
                {

                    args[i] = args[i].replace(sng_varnme[1], ""); // takes out the "songname="

                    // I wan't a path to current working dir.
                    Path curRelPath = Paths.get("");
                    String curpath = curRelPath.toAbsolutePath().toString();

                    String pathseparator = "/"; // used for separating directories.
                    byte startfrom = 1; // due to how .split() and toAbsolutePath() work

                    if(new String(System.getProperty("os.name").toLowerCase()).contains("win")) //Windows AKA the worst system. BTW, Intellij just derps out on this line quite hard.
                    {
                        pathseparator = "\\"; //JUST WHY WINDOWS/DOS USE FORWARD SLASH??? TODO: Needs testing
                        startfrom = 0; // c:/whatever .  TODO: Needs adjusting and testing.
                    }
                    // Easy, just split on var:pathseparator
                    String[] pathmass = curpath.split(pathseparator);
                    String[] pathtofile = args[i].split(pathseparator);

                    int howtocut = 0; // Mostly used for subtracting.

                    for (int v = 0; v < pathtofile.length; v++) // counts how many times you need to go back
                        if (pathtofile[v].contains(".."))       // Check our victim.
                            howtocut++;                         // Add to our victim count.

                    curpath = "";                               // Empties the string.

                    for (int v = startfrom; v < (pathmass.length - howtocut); v++) // Path to our working directory - how many times we ancountered ".."
                    {
                        curpath += pathseparator + pathmass[v]; // var:curpath = var:curpath + var:pathseparator + var:pathmass[current iteration]
                    }

                    for (int v = howtocut; v < (pathtofile.length - 1); v++)        // Mostly a repeat of the previous 'for' loop.
                    {
                        curpath += pathseparator + pathtofile[v]; // var:curpath = var:curpath + var:pathseparator + var:pathtofile[current iteration]
                    }

                    curpath += pathseparator; // Cherry on top.

                    //System.out.println(curpath); // Used this to see whether or not the path construction went according to plan.

                    File sng = new File(curpath + pathtofile[pathtofile.length-1] + ".ogg"); //It's to check if it exists.
                    File lyr = new File(curpath + pathtofile[pathtofile.length-1] + ".txt"); //To check if it exists, as well as reading from file
                    if (sng.exists() && lyr.exists())                   // Existential crisis.
                    {
                        List<String> newLyr = new ArrayList<String>();  //I need a new list, not you.
                        Scanner scam = new Scanner(lyr);                // New nigerian prince SCAM.

                        while (scam.hasNextLine())                      // We don't want to crash everything, am I right?
                            newLyr.add(scam.nextLine());                // Adds

                        scam.close();                                   // Nice and tidy
                        song.setTitle(pathtofile[pathtofile.length-1]); //Setting a new title.
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
