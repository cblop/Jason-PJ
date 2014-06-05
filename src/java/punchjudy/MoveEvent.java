package punchjudy;
import java.util.HashMap;


public class MoveEvent extends Event {
	Coord target;

    HashMap<String, Coord> locs = new HashMap<String, Coord>();

	public MoveEvent(String obj, float sTime, float d, String targ) {
		super(obj, sTime, d);
		//target = targ;
		//target = new Coord (targ.getX() + (0.5f * obj.sprite.width), targ.getY());
        locs.put("offstageLeft", PuppetShow.OFFSTAGELEFT);
        locs.put("offstageRight", PuppetShow.OFFSTAGERIGHT);
        locs.put("stageLeft", PuppetShow.STAGELEFT);
        locs.put("stageRight", PuppetShow.STAGERIGHT);
        locs.put("stageLeftCentre", PuppetShow.STAGELEFTCENTRE);
        locs.put("stageRightCentre", PuppetShow.STAGERIGHTCENTRE);
        locs.put("stageCentre", PuppetShow.STAGECENTRE);
		target = locs.get(targ);
	}
	
	protected void trigger(Entity object) {
		setRunning(true);
		Coord toTarget = new Coord(target.getX() - object.location.getX(), target.getY() - object.location.getY());
		float toTargetLength = (float) Math.sqrt((toTarget.getX() * toTarget.getX()) + (toTarget.getY() * toTarget.getY()));
		object.moveTo(target, toTargetLength / dur);
		
		/*
        if (target.getX() > object.location.getX() && object.horient == -1.0){
                object.fliph();
        }
        else if (target.getX() < object.location.getX() && object.horient == 1.0){
                object.fliph();
        }
        */
	}

}
