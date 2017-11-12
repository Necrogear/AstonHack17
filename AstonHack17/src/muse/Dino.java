package muse;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;

public class Dino {
	private Image image;
	private Point position;
	private boolean isVisible;
	private static final double GRAVITY = -1;
	private static final int GROUND = 630;
	private float vel=0;
	private boolean hasSecondJump = false;
	
	
	public Dino(int x, int y) throws SlickException {
		position = new Point(x,y);
		image = new Image("assets/dino.png");
	}

	public void update() {
		if (position.getY()<=0)
			vel=0;
		if (position.getY()<GROUND)
			vel+=GRAVITY;
		position.setY(position.getY()-vel);
		if (position.getY()>GROUND)
			position.setY(GROUND);
	}
	
	public void gainSecondJump() {
		hasSecondJump = true;
	}
	
	public void useSecondJump() {
		hasSecondJump = false;
		jump();
	}
	
	public boolean getSecondJump() {
		return hasSecondJump;
	}
	
	public boolean inAir() {
		if (position.getY()>=GROUND) {
			return false;
		} else
			return true;
	}
	
	public void setXPos(int x) {
		position.setX(x);
	}
	
	public void setYPos(int y) {
		position.setY(y);

	}
	
	public void setPos(int x, int y) {
		position.setLocation(x, y);

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

	public boolean canJump2() { //jump without caring about double
		if(!inAir())
			return true;
		else
			return false;
	}	

	public boolean canJump() { //jump with caring about double
		if(!inAir() || hasSecondJump)
			return true;
		else
			return false;
	}
	
	public void jump() {
		if(hasSecondJump)
			hasSecondJump = false;
		else
			hasSecondJump = true;
		vel=25;
	}
}
