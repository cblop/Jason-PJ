package punchjudy;

public class Event {
	float startTime;
	protected float dur;
	float elapsed;
	float speed;
	private boolean running;
	protected String objectID;

	Event(String obj, float sTime, float d) {
		setObjectID(obj);
		startTime = sTime;
		dur = d;
		elapsed = 0;
		setRunning(false);
		speed = dur / 30; // assume frame rate is 30
	}

	protected void trigger(Entity object) {
		setRunning(true);
	}

	void stop() {
		setRunning(false);
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public String getObjectID() {
		return objectID;
	}

	public void setObjectID(String obj) {
		this.objectID = obj;
	}

}
