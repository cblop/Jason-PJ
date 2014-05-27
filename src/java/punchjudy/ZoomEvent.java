package punchjudy;

public class ZoomEvent extends Event{
	Coord target;
	ZoomEvent(String obj, float sTime, float d, Coord targ) {
		super(obj, sTime, d);
		target = targ;
	}

	protected void trigger(Entity object) {
		setRunning(true);
		Coord toTarget = new Coord(target.getX() - object.zoom.getX(), target.getY() - object.zoom.getY());
		float toTargetLength = (float) Math.sqrt((toTarget.getX() * toTarget.getX()) + (toTarget.getX() * toTarget.getY()));
		object.zoomTo(target, toTargetLength / dur);
	}
}
