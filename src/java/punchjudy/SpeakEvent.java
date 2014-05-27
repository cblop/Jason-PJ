package punchjudy;

public class SpeakEvent extends Event {
	String actor;
	protected String dialogue;
	public SpeakEvent(String act, float sTime, float d, String dial) {
		super(act, sTime, d);
		actor = act;
		dialogue = dial;
		System.out.println("SpeakEvent constructor: " + sTime);
	}

	protected void trigger(Entity obj) {
		if (obj instanceof Actor) {
				Actor actor = (Actor) obj;
                setRunning(true);
                actor.setSpeech(dialogue);
                actor.sayLine();
                actor.speaking = true;
		}
	}
	
	void stop() {
		setRunning(false);
	}
}
