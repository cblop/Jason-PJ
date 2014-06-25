package punchjudy;

import ddf.minim.analysis.*;
import ddf.minim.*;
import processing.core.PApplet;

public class Dialogue {
	AudioSample audio;
	FFT fft;
	String soundPath;
	String subtitle;
    Minim minim;

	Dialogue(PApplet p, String filename, String sub) {
		minim = new Minim(p);
		soundPath = filename;
		subtitle = sub;
	}
	
	public void playSound() {
		audio = minim.loadSample(soundPath, 512);
		//fft = new FFT(audio.bufferSize(), audio.sampleRate());
		audio.trigger();
		//audio.close();
	}
}
