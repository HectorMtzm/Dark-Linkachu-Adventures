package utility;

import java.io.File;

import javax.sound.sampled.*;

public class Sounds extends Thread{

	private File soundFile;

	//Sounds
	public static final String bossLevel = "bossLvl.wav";
	public static final String bossDie = "bossDie.wav";
	public static final String youDie = "youDie.wav";
	public static final String shield = "shieldTake.wav";
	public static final String arrow = "arrow.wav";
	public static final String monsterDie = "monsterDie.wav";
	public static final String monsterHurt = "monsterHurt.wav";
	public static final String fireball = "fire.wav";
	public static final String gem = "gem.wav";
//	public static final String jump = "jump.wav";
//	public static final String spikes = "spikes.wav";

	public Sounds() {
	}

	public void playSound(String soundName){
		new Thread(() -> {
			try {
				soundFile = new File("src/main/resources/sounds/" + soundName);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				Clip clip = AudioSystem.getClip();
				AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundFile);
				clip.open(inputStream);
				FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN); gainControl.setValue(-10.0f);
				clip.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
}
