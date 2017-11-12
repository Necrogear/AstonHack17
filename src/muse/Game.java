package muse;

import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.particles.ParticleEmitter;
import org.newdawn.slick.particles.ParticleSystem;


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
	private int health = 10;
	private int score = 0;

	
	private int counter1 = 0;
	
	private Background sun;
	private Background sky;
	private Background scen1;
	private Background scen2;
	private Background scen3;
	private Background scen4;

	private Background sun2;
	private Background sky2;
	private Background scen12;
	private Background scen22;
	private Background scen32;
	private Background scen42;
	
	private Music music;
	private Sound roar;
	private int counter;
	
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
		sun = new Background("sun1.png",5,0);
		sky = new Background("sky1.png",6,0);
		scen4 = new Background("scenarybg4.png",4,0.5);
		scen3 = new Background("scenarybg3.png",3,1.5);
		scen2 = new Background("scenarybg2.png",2,3.0);
		scen1 = new Background("scenarybg1.png",1,4.5);
		
		sun2 = new Background("sun1.png",5,0);
		sky2 = new Background("sky1.png",6,0);
		scen42 = new Background("scenarybg4.png",4,0.5);
		scen32 = new Background("scenarybg3.png",3,1.5);
		scen22 = new Background("scenarybg2.png",2,3.0);
		scen12 = new Background("scenarybg1.png",1,4.5);
		
		goose = new Goose();
		goose2 = new Goose();
		goose2.setXPos(goose2.getPos().getX()+200);
	    roar = new Sound("assets/roar.ogg");
	    music = new Music("assets/jpark.ogg");
	    music.loop();
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		if(health>0) {
		incrementScore();
		g.drawImage(bg, 0, 0);
		
		g.drawImage(sky.getImage(),sky.getPos().getX(),0);
		g.drawImage(sky2.getImage(),sky2.getPos().getX()+1600,0);
		g.drawImage(sun.getImage(),sun.getPos().getX(),0);
		g.drawImage(sun2.getImage(),sun2.getPos().getX()+1600,0);
		g.drawImage(scen4.getImage(),scen4.getPos().getX(),0);
		g.drawImage(scen42.getImage(),scen42.getPos().getX()+1600,0);
		g.drawImage(scen3.getImage(),scen3.getPos().getX(),0);
		g.drawImage(scen32.getImage(),scen32.getPos().getX()+1600,0);
		g.drawImage(scen2.getImage(),scen2.getPos().getX(),0);
		g.drawImage(scen22.getImage(),scen22.getPos().getX()+1600,0);
		g.drawImage(scen1.getImage(),scen1.getPos().getX(),0);
		g.drawImage(scen12.getImage(),scen12.getPos().getX()+1600,0);
				
		g.drawImage(goose.getImage(), goose.getPos().getX(),goose.getPos().getY());
		g.drawImage(goose2.getImage(), goose2.getPos().getX(),goose2.getPos().getY());
		g.drawImage(dinosaur.getImage().getScaledCopy(2), dinosaur.getPos().getX(),dinosaur.getPos().getY());
		if (blinking) {
			g.drawString("YER BLINKIN", 40, 40);
		}else {
			g.drawString("YER NAWT BLINKIN", 40, 40);
		}
		g.drawString("SCORE:  "+ score, 1500, 80);
		g.drawString("HEALTH: "+ health, 1500, 100);	}
		else {
			g.drawImage(new Image("assets/gameover1.png"),0,0);
			g.drawString(""+score, 1400, 200);
		}
		
	}

	public void update(GameContainer gc, int delta) throws SlickException {
		
		sky.update();
		sky2.update();
		sun.update();
		sun2.update();
		scen4.update();
		scen42.update();
		scen3.update();
		scen32.update();
		scen2.update();
		scen22.update();
		scen1.update();
		scen12.update();
		
		dinosaur.update();
		if(goose.getPos().getX()<=-700) {
			goose.reset();
		}
		if(goose2.getPos().getX()<=-700) {
			goose2.reset();
		}
		goose.update();
		goose2.update();
		
		checkHit1(goose);
		checkHit1(goose2);
		
		if(dinosaur.inAir()&&counter>2) {
			dinosaur.swapImage();
			counter = 0;
		}
		counter++;
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
		if((input.isKeyPressed(Input.KEY_SPACE) || blinking)) {
			blinking=false;
			dinosaur.jump();
		}
		
	}
	
	private void incrementScore() {
		score++;
	}
	
	private void checkHit1(Goose goose) {
		if(counter1==0) {
			if(dinosaur.getPos().getX()>(goose.getPos().getX()-63) && (dinosaur.getPos().getX()<(goose.getPos().getX()+82) 
			&& (dinosaur.getPos().getY()>(goose.getPos().getY()) && (dinosaur.getPos().getY()<(goose.getPos().getY()+200))))) {
				counter1=50;
				health-=1;
				roar.play();
			}
		}
		else {
			counter1--;
		}
	}
	void oscEvent(OscMessage msg) {
		if (msg.checkAddrPattern("/muse/eeg") == true) {
			int i = 0;
			if (msg.get(i).floatValue() > 900) {
				//System.out.println("BLINK");
				blinking = true;
			} else {
				//System.out.println("NO BLINK");
				blinking = false;
			}
		}
	}

}
