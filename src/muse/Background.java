package muse;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;

public class Background {
	
	private Image image;
	private int order;
	private double speed;
	private Point position;
	
	public Background(String bg, int order, double d) throws SlickException {
		String str = "assets/"+bg;
		image = new Image(str);
		this.order = order;
		this.speed = d;
		position = new Point(0,0);
	}
	
	public void update() {
		position.setX((float) (position.getX()-speed));
		if (position.getX()<=-1600) {
			position.setX(position.getX()+1600);
		}
	}
	
	public Image getImage() {
		return image;
	}

	public int getOrder() {
		return order;
	}
	
	public Point getPos() {
		return position;
	}

}
