package punchjudy;

public class Coord {
	private float x;
	private float y;

	public Coord(float xpos, float ypos) {
		setX(xpos);
		setY(ypos);
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}
}
