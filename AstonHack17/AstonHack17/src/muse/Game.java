package muse;

import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import oscP5.OscMessage;
import oscP5.OscP5;

public class Game extends BasicGame {
	
	private static OscP5 museServer;
	static int recvPort = 5000;
	private boolean blinking = false;
	private Dino dinosaur;
	private Image bg;
	private Goose goose;
	private Goose goose2;
	private Random rng;
	
	public Game() throws SlickException {
		super("Blinky Dinosaur");
		museServer = new OscP5(this, 5000);

	}


	public static void main(String[] args) {
		try {
			AppGameContainer agc = new AppGameContainer(new Game());
			agc.setDisplayMode(1600, 900, false);
			agc.setTargetFrameRate(60);
			agc.start();

		}catch(SlickException e){
			e.printStackTrace();
		}
	}

	public void init(GameContainer gc) throws SlickException {
		dinosaur = new Dino(100,100);
		bg = new Image("assets/bg1.png");
		goose = new Goose();
		goose2 = new Goose();
		goose2.setXPos(goose2.getPos().getX()+200);
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.drawImage(bg, 0, 0);
		g.drawImage(goose.getImage(), goose.getPos().getX(),goose.getPos().getY());
		g.drawImage(goose2.getImage(), goose2.getPos().getX(),goose2.getPos().getY());
		g.drawImage(dinosaur.getImage().getScaledCopy(2), dinosaur.getPos().getX(),dinosaur.getPos().getY());
		if (blinking ) {
			g.drawString("YER BLINKIN", 40, 40);
			//dinosaur.jump();
		}else {
			g.drawString("YER NAWT BLINKIN", 40, 40);
		}

	}

	public void update(GameContainer gc, int delta) throws SlickException {
		/*if (rng.nextInt(10)==0) {
		goose = new Goose();
		}*/
		dinosaur.update();
		if(goose.getPos().getX()<=-700) {
			goose.reset();
		}
		if(goose2.getPos().getX()<=-700) {
			goose2.reset();
		}
		goose.update();
		goose2.update();
		Input input = gc.getInput();
		
		/* With Double Jump
		if((input.isKeyPressed(input.KEY_SPACE) || blinking) && dinosaur.canJump() ) {
			blinking=false;
			dinosaur.jump();
		}*/
	
		/* Without Double Jump
		if((input.isKeyPressed(input.KEY_SPACE) || blinking) && dinosaur.canJump2() ) {
			blinking=false;
			dinosaur.jump();
		}*/
	
		/* Without Jump Restriction */
		if((input.isKeyPressed(input.KEY_SPACE) || blinking)) {
			blinking=false;
			dinosaur.jump();
		}
		
	}
	
	void oscEvent(OscMessage msg) {
		if (msg.checkAddrPattern("/muse/eeg") == true) {
			int i = 0;
			if (msg.get(i).floatValue() > 1000) {
				//System.out.println("BLINK");
				blinking = true;
			} else {
				//System.out.println("NO BLINK");
				blinking = false;
			}
		}
	}

}
