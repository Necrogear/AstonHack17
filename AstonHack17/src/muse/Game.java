package muse;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import oscP5.OscMessage;
import oscP5.OscP5;

public class Game extends BasicGame {
	
	private static OscP5 museServer;
	static int recvPort = 5000;
	private boolean blinking = false;
	
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

	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		if (blinking) {
			g.drawString("YER BLINKIN", 40, 40);
		}else
			g.drawString("YER NAWT BLINKIN", 40, 40);

	}

	public void update(GameContainer gc, int delta) throws SlickException {

	}
	
	void oscEvent(OscMessage msg) {
		if (msg.checkAddrPattern("/muse/eeg") == true) {
			int i = 0;
			// System.out.print("EEG on channel " + i + ": " + msg.get(i).floatValue() +
			// "\n");
			if (msg.get(i).floatValue() > 1000) {
				System.out.println("BLINK");
				blinking = true;
			} else {
				System.out.println("NO BLINK");
				blinking = false;
			}
		}
	}

}
