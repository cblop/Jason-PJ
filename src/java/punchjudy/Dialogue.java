package punchjudy;

import ddf.minim.analysis.*;
import ddf.minim.*;
import processing.core.PApplet;

public class Dialogue {
	AudioPlayer audio;
	FFT fft;
	String subtitle;
    Minim minim;

	Dialogue(PApplet p, String filename) {
		minim = new Minim(p);
		audio = minim.loadFile(filename, 512);
		fft = new FFT(audio.bufferSize(), audio.sampleRate());
		subtitle = "";
	}

	Dialogue(PApplet p, String filename, String sub) {
		minim = new Minim(p);
		audio = minim.loadFile(filename, 512);
		fft = new FFT(audio.bufferSize(), audio.sampleRate());
		subtitle = sub;
	}
}
