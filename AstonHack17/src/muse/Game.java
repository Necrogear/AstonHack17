package muse;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;

import oscP5.OscMessage;
import oscP5.OscP5;

public class Game extends BasicGame {
	
	private static OscP5 museServer;
	static int recvPort = 5000;
	private boolean blinking = false;
	
	
	private Image dinosaur;
	private float y;
	
	private Image goose;
	private float x;
	
	public Game() {
		super("Blinky Dinosaur");
		museServer = new OscP5(this, 5000);

	}


	public static void main(String[] args) {
		try {
			AppGameContainer agc = new AppGameContainer(new Game());
			agc.setDisplayMode(500, 500, false);
			agc.setTargetFrameRate(60);
			agc.start();

		}catch(SlickException e){
			e.printStackTrace();
		}
	}

	public void init(GameContainer gc) throws SlickException {
		gc.getGraphics().setBackground(new Color(255, 255, 255));
		gc.getGraphics().setAntiAlias(true);
		x=400;
		dinosaur = new Image("assets/dinosaur.png");
		goose = new Image("assets/goose.png");
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.drawImage(dinosaur, 200, y);
		g.drawImage(goose, x, 350);
		g.setColor(new Color(0, 0, 0));
		if (blinking) {
			g.setColor(new Color(0, 0, 0));
			g.drawString("YER BLINKIN", 40, 40);
		}else
			g.drawString("YER NAWT BLINKIN", 40, 40);
	}

	public void update(GameContainer gc, int delta) throws SlickException {
		float movementSpeed = 0.1f;
		
		y += (movementSpeed * delta);
		x -= (movementSpeed * delta);
	}
	
	void oscEvent(OscMessage msg) {
		if (msg.checkAddrPattern("/muse/eeg") == true) {
			int i = 0;
			// System.out.print("EEG on channel " + i + ": " + msg.get(i).floatValue() +
			// "\n");
			if (msg.get(i).floatValue() > 900) {
				System.out.println("BLINK");
				blinking = true;
			} else {
				System.out.println("NO BLINK");
				blinking = false;
			}
		}
	}

}
