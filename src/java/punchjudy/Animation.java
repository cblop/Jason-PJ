package punchjudy;
import processing.core.PImage;

public class Animation {
	PImage[] frames;
	int currentFrame;
	boolean running;
	Animation(PImage[] frms){
		frames = frms;
		currentFrame = 0;
	}

	void playAnim() {
		running = true;
		currentFrame = (currentFrame + 1) % frames.length;
	}

	void stopAnim() {
		running = false;
		currentFrame = 0;
	}

	void update() {
		if (running) {
			playAnim();
			// have a looping flag for this
			/*
			if (currentFrame >= frames.length) {
				stopAnim();
			}
			*/
		}

	}

}
