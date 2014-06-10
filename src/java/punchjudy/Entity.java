package punchjudy;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Entity {
	public PApplet parent;
	
	public PImage sprite;
	public Coord location;
	public Coord target;
	public Coord zoom;
	public Coord zoomTarget;
	public Coord anchor;
	float zoomSpeed;
	float speed;
	float horient;

	Entity(PApplet p, PImage spr, Coord loc, Coord sca){
		parent = p;
		sprite = spr;
		location = loc;
		target = location;
		zoom = sca;
		zoomTarget = sca;
		horient = 1.0f;
		anchor = new Coord(0.0f, 0.0f);
	}

	public void moveTo(Coord trgt, float spd) {
		//target = new Coord (trgt.getX() + (0.5f * sprite.width), trgt.getY());

        if (trgt.getX() > location.getX() && horient == -1.0){
                fliph();
        }
        else if (trgt.getX() < location.getX() && horient == 1.0){
                fliph();
        }

		target = trgt;


		/*
        if (target.getX() < location.getX() && horient == 1.0){
                fliph();
        }
        else if (target.getX() > location.getX() && horient == -1.0){
                fliph();
        }
		*/

		if (horient == 1.0f) {
                //target.setX(target.getX() + (horient * (0.5f * sprite.width)));
                target.setX(target.getX() + (0.5f * sprite.width));
		}

		speed = spd;
	}

	void processMouse() {
		if (parent.mousePressed) {
			//System.out.println("Speed: " + speed);
			moveTo(new Coord(parent.mouseX, parent.mouseY), 6.0f);

		}
	}

	void fliph() {
		if (horient == 1.0f) {
			location.setX(location.getX() - (0.5f * sprite.width));
			//location.setX(location.getX() - sprite.width);
			//target.setX(target.getX() - (0.5f * sprite.width));
			horient = -1.0f;
		}
		else {
			//location.setX(location.getX() - sprite.width);
			//target.setX(target.getX() + sprite.width);
			location.setX(location.getX() + (0.5f * sprite.width));
			//target.setX(target.getX() + (0.5f * sprite.width));
			horient = 1.0f;
		}
	}
	
	void move() {
		Coord toTarget = new Coord(target.getX() - location.getX(), target.getY() - location.getY());
		float toTargetLength = (float) Math.sqrt((toTarget.getX() * toTarget.getX()) + (toTarget.getY() * toTarget.getY()));
		toTarget.setX(toTarget.getX() / toTargetLength);
		toTarget.setY(toTarget.getY() / toTargetLength);

		// this is a kludge?
		if (toTargetLength > speed) {
			//location.setX(location.getX() + Math.signum(toTarget.getX()) * speed);
			//location.setY(location.getY() + Math.signum(toTarget.getY()) * speed);
			location.setX(location.getX() + toTarget.getX() * speed);
			location.setY(location.getY() + toTarget.getY() * speed);
		}
		else {
			location = target;
		}

	}

	void zoomTo(Coord trgt, float spd) {
		zoomTarget = trgt;
		zoomSpeed = spd;
	}

	void zoom() {
		// duplicated code - too similar to move()
		Coord toTarget = new Coord(zoomTarget.getX() - zoom.getX(), zoomTarget.getY() - zoom.getY());
		double toTargetLengthD = Math.sqrt((toTarget.getX() * toTarget.getX()) + (toTarget.getY() * toTarget.getY()));
		float toTargetLength = (float) toTargetLengthD;
		toTarget.setX(toTarget.getX() / toTargetLength);
		toTarget.setY(toTarget.getY() / toTargetLength);

		// this is a kludge?
		if (toTargetLength > zoomSpeed) {
			zoom.setX(zoom.getX() + toTarget.getX() * zoomSpeed);
			zoom.setY(zoom.getY() + toTarget.getY() * zoomSpeed);
		}

	}

	void update() {
		//processMouse();
	}

	void display() {
		move();
		parent.imageMode(PConstants.CENTER);
		//zoom();
		parent.pushMatrix();
		parent.scale(horient * zoom.getX(), zoom.getY());
		parent.image(sprite, horient * location.getX(), location.getY(), sprite.width, sprite.height);
		//parent.image(sprite, horient * (location.getX() - 
		//		(anchor.getX() * sprite.width)), location.getY() - (anchor.getY() * sprite.height), sprite.width, sprite.height);
		parent.popMatrix();
	}
}
