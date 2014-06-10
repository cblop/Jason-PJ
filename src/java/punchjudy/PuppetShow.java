package punchjudy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import processing.core.*;


public class PuppetShow extends PApplet {
	
	/**
	 * 
	 */
	
	// Need to have standard sprite style: 0,0 is bottom left,
	// all move events move 0,0 to the specified pixel
	private static final long serialVersionUID = 1L;
	final int screenWidth = 800;
	final int screenHeight = 600;
	final float scalef = 0.7f; // size of the actor

	public static final Coord OFFSTAGELEFT = new Coord(100, 500);
    public static final Coord OFFSTAGERIGHT = new Coord(1400, 500);
    public static final Coord STAGELEFT = new Coord(200, 500);
    public static final Coord STAGELEFTCENTRE = new Coord(350, 500);
    public static final Coord STAGERIGHT = new Coord(800, 500);
    public static final Coord STAGERIGHTCENTRE = new Coord(650, 500);
    public static final Coord STAGECENTRE = new Coord(500, 500);


	Scene scene;
	Entity stage;
	public HashMap<String, Actor> actors = new HashMap<String, Actor>();
	//List<Event> events = new ArrayList<Event>();
	static Queue<Event> events = new LinkedList<Event>();
	
	public static void addEvent(Event event) {
		synchronized(events) {
             events.add(event);
             System.out.println("Event added");
		}
	}

	// is this even used?
	public static Event getEvent() {
		synchronized(events) {
             return events.remove();
		}
	}


	public void setup() {
		size(screenWidth, screenHeight);
		PImage bgimg = loadImage("pics/Stage2.png"); // background image


        Actor punch;
        Actor judy;
        Actor joey;

		// put punch's dialogue into a hash map
		HashMap<String, Dialogue> punchDialogue = new HashMap<String, Dialogue>();
		punchDialogue.put("cool", new Dialogue(this, "sounds/cool_man.mp3", "Cool, man!"));
		punchDialogue.put("laugh", new Dialogue(this, "sounds/dude_laugh.mp3", "Hahahaha"));
		punchDialogue.put("hello", new Dialogue(this, "sounds/hello_man.mp3", "Hello, man!"));
		punchDialogue.put("disco", new Dialogue(this, "sounds/love_disco.mp3", "I love disco!"));
		punchDialogue.put("wassup", new Dialogue(this, "sounds/wassup.mp3", "Wassup!"));

		// these are punch's animations, only one frame each for now
		PImage[] punchRest = {loadImage("pics/PunchSide.png")};
		PImage[] punchFront = {loadImage("pics/PunchFront.png")};
		PImage[] punchHit = {loadImage("pics/PunchStick1.png"), loadImage("pics/PunchStick1.png"),
				loadImage("pics/PunchStick2.png"), loadImage("pics/PunchStick2.png")};

		// animations in a hash map
		HashMap<String, Animation> punchAnims = new HashMap<String, Animation>();
		punchAnims.put("rest", new Animation(punchRest));
		punchAnims.put("front", new Animation(punchFront));
		punchAnims.put("hit", new Animation(punchHit));

		// initialise the punch actor
		punch = new Actor(this, OFFSTAGELEFT, new Coord(scalef, scalef), punchAnims, punchDialogue);

		// these are judy's animations, only one frame each for now
		PImage[] judyRest = {loadImage("pics/JudySide.png")};
		PImage[] judyFront = {loadImage("pics/JudyFront.png")};

		// animations in a hash map
		HashMap<String, Animation> judyAnims = new HashMap<String, Animation>();
		judyAnims.put("rest", new Animation(judyRest));
		judyAnims.put("front", new Animation(judyFront));

		// initialise the judy actor
		judy = new Actor(this, OFFSTAGERIGHT, new Coord(scalef, scalef), judyAnims, punchDialogue);
		
		
		PImage[] joeyRest = {loadImage("pics/JoeySide.png")};
		// animations in a hash map
		HashMap<String, Animation> joeyAnims = new HashMap<String, Animation>();
		joeyAnims.put("rest", new Animation(joeyRest));

		// initialise the joey actor
		joey = new Actor(this, OFFSTAGELEFT, new Coord(scalef, scalef), joeyAnims, punchDialogue);
		
		
		PImage stageImg = loadImage("pics/Stage-top.png");
		
		// put the stage over him
		stage = new Entity(this, stageImg, new Coord(screenWidth, screenHeight), new Coord(0.5f, 0.5f));
		

		// this is a list of all the events for the animation
		

		/*
		events.add(new MoveEvent("punch", 10f, 10f, "stageLeft"));
		events.add(new MoveEvent("judy", 10f, 10f, "stageLeft"));
		*/
		/*
		//events.add(new MoveEvent("joey", 10f, 10f, "stageCentre"));
		events.add(new MoveEvent("punch", 40f, 20f, "offstageLeft"));
		events.add(new MoveEvent("judy", 40f, 20f, "offstageLeft"));
		//events.add(new MoveEvent("joey", 40f, 20f, "offstageLeft"));
        */
		
		

		//events.add(new MoveEvent(this, judy, 50, 50, stageRight));
		//events.add(new MoveEvent(this, judy, 200, 50, stageCentre));
		
		/*
        // MoveEvent arguments: object, start time, duration, target location
		events.add(new MoveEvent(this, punch, 100.0f, 20.0f, new Coord(0, 0)));
        // ZoomEvent arguments: object, start time, duration, target sprite size
		events.add(new ZoomEvent(this, punch, 100.0f, 20.0f, new Coord(3.0f, 3.0f)));
        // SpeakEvent arguments: object, start time, duration, hash map string for line
		events.add(new SpeakEvent(this, punch, 100.0f, 20.0f, "cool"));
		events.add(new MoveEvent(this, punch, 200.0f, 200.0f, new Coord(100, 200)));
		events.add(new ZoomEvent(this, punch, 200.0f, 200.0f, new Coord(1.0f, 1.0f)));
		events.add(new ZoomEvent(this, punch, 300.0f, 10.0f, new Coord(0.7f, 0.7f)));
		events.add(new MoveEvent(this, punch, 600.0f, 200.0f, new Coord(400f, 200f)));
		events.add(new SpeakEvent(this, punch, 400.0f, 20.0f, "disco"));
		*/

		actors.put("judy", judy);
        actors.put("punch", punch); // map of all actors in the scene
		//actors.put("joey", joey);

		// create and run the scene
		scene = new Scene(this, screenWidth, screenHeight, bgimg, events, actors);
		scene.runEvents();
	}
	
	public void mouseClicked() {
		for (Actor act : actors.values()) {
                act.moveTo(new Coord(mouseX, mouseY), 6.0f);
                System.out.println("X: " + act.target.getX() + "  Y: " + act.target.getY());
		}
		System.out.println(actors.get("punch").currentAnim);
	}

	public void draw() {
		// first draw the backdrop
		imageMode(PConstants.CENTER);
		scene.update();
		scene.display();
		
		// then the actors
		for (Actor act : actors.values()) {
			act.update();
			act.display();
			//System.out.println(act.location.getX());
		}
		

		// then the stage overlay
		stage.display();
		
		// finally, show the subtitles over the stage
		for (Actor act : actors.values()) {
            if ((act.speech.subtitle != "") && (act.speaking == true)) {
                act.showSubs();
            }
		}
	}
	
	public static void main(String _args[]) {
		PApplet.main(new String[] { punchjudy.PuppetShow.class.getName() });
	}
}
