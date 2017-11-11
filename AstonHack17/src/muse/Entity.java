package muse;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

public class Entity {
	private Image image;
	private Point position;
	private boolean isVisible;
	
	public Entity(int x, int y) {
		
	}
	
	public void setXPos(int x) {
		position = new Point(x, position.getY());
	}
	
	public void setYPos(int y) {
		position = new Point(position.getX(), y);

	}
	
	public void setPos(int x, int y) {
		position = new Point(x, y);

	}
	
	public void setVisible(boolean visible) {
		isVisible = visible;
	}
	
	public Point getPos() {
		return position;
	}
	
	public Image getImage() {
		return image;
	}
	
	public boolean getVisibility() {
		return isVisible;
	}
}
