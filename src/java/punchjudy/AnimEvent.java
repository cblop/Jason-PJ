package punchjudy;

public class AnimEvent extends Event {
	String animName;
	//Actor object;
	public AnimEvent(String obj, float sTime, float d, String aname) {
		super(obj, sTime, d);
		animName = aname;
	}

	protected void trigger(Entity obj) {
		if (obj instanceof Actor) {
			Actor act = (Actor) obj;
			act.currentAnim = act.animations.get(animName);
            act.animations.get(animName).playAnim();
		}
	}
}
