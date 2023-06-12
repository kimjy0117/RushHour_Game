import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class BGM {
	public BGM() {
		try {
		AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sound/QueensGarden.wav"));
		Clip clip = AudioSystem.getClip();
		clip.open(ais);
		clip.start();		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}