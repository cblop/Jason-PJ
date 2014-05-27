package punchjudy;

import java.util.HashMap;


//import punchjudy.Object;
import processing.core.PApplet;
import processing.core.PConstants;

public class Actor extends Entity {
	PApplet parent;
	HashMap<String, Animation> animations = new HashMap<String, Animation>();
	HashMap<String, Dialogue> dialogue = new HashMap<String, Dialogue>();
	Animation currentAnim;
	Dialogue speech;
	Boolean speaking;
	Actor(PApplet p, Coord loc, Coord sca, HashMap<String, Animation> anims, HashMap<String, Dialogue> dial){
		// constructor
		super(p, anims.get("rest").frames[0], loc, sca);
		parent = p;
		animations = anims;
		dialogue = dial;
		currentAnim = anims.get("rest");
		speaking = false;
		speech = dialogue.get("hello"); // very, very bad
		speech.subtitle = ""; // nooooooo
		//anchor = new Coord(0.0f, 0.0f);
		horient = 1.0f;
	}

	Actor(PApplet p, Coord loc, Coord sca, HashMap<String, Animation> anims){
		// constructor
		super(p, anims.get("rest").frames[0], loc, sca);
		parent = p;
		animations = anims;
		currentAnim = anims.get("rest");
		speaking = false;
		//anchor = new Coord(1.0f, 1.0f);
	}

	void setSpeech(String spch) {
		if (dialogue != null) {
            speech = dialogue.get(spch);
            System.out.println("Speech string: " + speech.subtitle);
		}
	}

	void moveMouth() {
		//boolean flag = false;
		if (dialogue != null) {
                speech.fft.forward(speech.audio.mix);
                for (int i = 0; i < speech.fft.specSize() / 16; i++) { // only process the first 16th of the spectrum
                        if((speech.fft.getBand(i)) > 20) {
                                // move the mouth
                                //animations.get("talk").playAnim();
                                //sprite = animations.get("talk").frames[0];
                        }
                        else {
                                //sprite = animations.get("rest").frames[0];
                        }
                }
		}
	}

	void sayLine() {
		if (dialogue != null) {
                if (!speech.audio.isPlaying()) {
                        speech.audio.play(0); // 0 means from the beginning
                }
		}
	}

	void showSubs() {
		if (dialogue != null) {
                parent.textSize(32);
                parent.fill(0, 0, 0);
                parent.text(speech.subtitle, Math.abs(location.getX())-2, Math.abs(location.getY()) + 50);
                parent.textSize(32);
                parent.fill(0, 0, 0);
                parent.text(speech.subtitle, Math.abs(location.getX())+2, Math.abs(location.getY()) + 50);
                parent.fill(0, 0, 0);
                parent.text(speech.subtitle, Math.abs(location.getX()), Math.abs(location.getY()) + 52);
                parent.fill(0, 0, 0);
                parent.text(speech.subtitle, Math.abs(location.getX()), Math.abs(location.getY()) + 48);

                parent.fill(255, 255, 255);
                parent.text(speech.subtitle, Math.abs(location.getX()), Math.abs(location.getY()) + 50);
		}
	}

	void speak() {
		if (dialogue != null) {
                if (speech.audio.position() >= speech.audio.length() - 70) { // need 70 samples tolerance
                        speaking = false;
                }
                if (speaking == true) {
                        moveMouth();
                }
		}


	}

	void update() {
		processMouse();
		currentAnim.update();
		sprite = currentAnim.frames[currentAnim.currentFrame];
	}

	void display() {
		move();
		zoom();
		if (dialogue != null) {
                speak();
		}
		parent.pushMatrix();
		//parent.imageMode(PConstants.CENTER);
		//parent.translate(horient * location.getX(), location.getY());
		parent.scale(horient * zoom.getX(), zoom.getY());
		//parent.image(sprite, 0, 0);
		parent.image(sprite, horient * location.getX(), location.getY(), sprite.width, sprite.height);
		//parent.image(sprite, horient * (location.getX() - 
		//		(anchor.getX() * sprite.width)), location.getY() - (anchor.getY() * sprite.height), sprite.width, sprite.height);
		parent.popMatrix();
		/*
		if ((speech.subtitle != "") && (speaking == true)) {
			showSubs();
		}
		*/

	}

}
