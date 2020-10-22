package utility;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class Sounds extends Thread{

	private final int BUFFER_SIZE = 524288; //128Kb
	private File soundFile;
	private AudioInputStream audioStream;
	private AudioFormat audioFormat;
	private SourceDataLine sourceLine;

	//Sounds
	public static String youDie = "youDie.wav";
	public static String shield = "shieldTake.wav";
	public String arrow = "arrow.wav";
	public String monsterDie = "monsterdie.wav";
	public String hurtMonster = "hurtmonster.wav";
	public String bossLevel = "bossLvl.wav";
	public String bossDie = "bossDie.wav";
	public String fireball = "fire.wav";
	public String gem = "gem.wav";
	public String jump = "jump.wav";
	public String spikes = "spikes.wav";

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
				clip.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
}
