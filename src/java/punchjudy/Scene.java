package punchjudy;
import java.util.HashMap;
import java.util.Queue;

import processing.core.PApplet;
import processing.core.PImage;

public class Scene {
	PApplet parent;
	PImage bgimage;
	float sceneWidth;
	float sceneHeight;
	Queue<Event> events;
	HashMap<String, Actor> actors;
	float timePassed;
	boolean running;

	Scene(PApplet p, float sWidth, float sHeight, PImage bgi, Queue<Event> es, HashMap<String, Actor> acts){
		parent = p;
		sceneWidth = sWidth;
		sceneHeight = sHeight;
		bgimage = bgi;

		events = es;
		actors = acts;
		timePassed = 0.0f;
		running = false;
	}

	void runEvents() {
		running = true;
	}

	void update() {
		if (running == true) {
			timePassed++;
			for (Event ev : events) {
				if (ev.startTime == 0) {
					//System.out.println("actor: " + actors.get(ev.getObjectID()).location.getX());
					System.out.println("Trigger");
					System.out.println(ev.getClass().toString());
					ev.trigger(actors.get(ev.getObjectID()));
					ev.startTime -= 1;
				}
				else if (ev.startTime > 0) {
					ev.startTime -= 1;
				}
			}
		}

	}
	void display() {
		// show the background image
		parent.image(bgimage, sceneWidth / 2, sceneHeight / 2, sceneWidth, sceneHeight);
	}
}
