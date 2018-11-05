package Controller;

public class Player {
	
	int x;
	int y;
	
	public Player (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void move (int dx, int dy) {
		x += dx;
		y += dy;
	}
	
	public int getX () {
		return x;
	}

	public int getY () {
		return y;
	}
}
