package Main;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	private static Clip musicClip;
	 public static void RunMusic(String path) {
	        try {
	            
	            if (musicClip != null && musicClip.isRunning()) {
	                musicClip.stop();
	                musicClip.close();
	            }
	            
	            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
	            musicClip = AudioSystem.getClip();
	            musicClip.open(inputStream);
	            musicClip.loop(Clip.LOOP_CONTINUOUSLY); 
	        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
	            e.printStackTrace();
	        }
	    }
    public static void RunSound(String path) {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.loop(0);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    
    
    
}
