package lt.vtvpmc.ems.valgykla;

import java.util.Arrays;

public class App {

    public static void main(String[] args) {
	int skaicius=0;
	int greitis=0;
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
        final Song song2 = new Song("Yesterday2", Arrays.asList("Yesterday all my troubles seemed so far away.2",
			     "Now it looks as though they're here to stay.2",
			     "Oh, I believe in yesterday.2",
			     "Suddenly I'm not half the man I used to be.2",
			     "There's a shadow hanging over me.2",
			     "Oh, yesterday came suddenly.2",
			     "Why she had to go, I don't know, she wouldn't say.2",
			     "I said something wrong, now I long for yesterday.2",
			     "Yesterday love was such an easy game to play.2",
			     "Now I need a place to hide away.2",
			     "Oh, I believe in yesterday.2",
			     "Why she had to go, I don't know, she wouldn't say.2",
			     "I said something wrong, now I long for yesterday.2",
			     "Yesterday love was such an easy game to play.2",
			     "Now I need a place to hide away.2",
			     "Oh, I believe in yesterday.2",
			     "Mm mm mm mm mm mm mm2"));
        System.out.println("pasirink daina: \n 1-yesteday \n 2-ysteday2");
		skaicius=klavetura.nextInt();
		
		final SongPlayer player = new SongPlayer();
		if (skaicius==1) {player.setSong(song);}
		else if (skaicius==2) {player.setSong(song2);}
		
		
		System.out.println("pasirink greiti nuo 1 iki 5 ");
		greitis=klavetura.nextInt();
		
		
		player.setSpeed(greitis);
		player.play();

    }
}
