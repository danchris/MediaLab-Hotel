package hotel;

import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;

/*
 * @author Daniel Christodoulopoulos
 */

public class HotelPlayer {

	private String name;
	private int x;
	private int y;
	private int mls;
	private int maxMLS;
	private Image img;
	private HotelBoardBox box;
	private int startYet;
	private int dice;
	private PauseTransition pause;

	public HotelPlayer(String name, int x, int y, int mls) throws IOException {
		this.name = name;
		this.x = x;
		this.y = y;
		this.mls = mls;
		this.maxMLS = mls; 
		if (name.equals("Player 1")) {
			img = HotelFileReader.getPawn("blue");
		} else if (name.equals("Player 2")) {
			img = HotelFileReader.getPawn("red");
		} else if (name.equals("Player 3")) {
			img = HotelFileReader.getPawn("green");
		} else {
			throw new IOException("Player.java: Error Name");
		}
		this.startYet = 0;
		pause = new PauseTransition(Duration.seconds(1));
		pause.setOnFinished(event -> {
			transitionMove();
		});

	}

	public String getName() {
		return name;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image i) {
		this.img = i;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMLS(int mls) {
		this.mls = mls;
		if (mls > maxMLS) maxMLS = mls;
	}

	public int getMLS() {
		return mls;
	}
	
	public int getMaxMLS() {
		return maxMLS;
	}
	
	public void setBox(HotelBoardBox b) {
		this.x = b._getX();
		this.y = b._getY();
		this.box = b;
	}
	
	public HotelBoardBox getCurrentBox() {
		return box;
	}

	public int getCurrentBoxX() {
		return x;
	}

	public int getCurrentBoxY() {
		return y;
	}

	public void setCurrentBoxX(int x) {
		this.x = x;
	}

	public void setCurrentBoxY(int y) {
		this.y = y;
	}
	
	public void move() {
	//	System.out.println("HotelPlayer.java : Eimai sthn move apo to "+ from.getID() +" sto " + to.getID());
		//to.setPawn(img);
		//from.removePawn();
	//	this.box = to;
	}
	
	public int getStartYet() {
		return startYet;
	}
	
	public void setStartYet() {
		this.startYet = 1;
	}
	
	public void transitionMove() {
		if(HotelGame.getStopFlag()==1) return ;
		if(dice>0) {
			pause.play();
			dice--;
			box.getNext().setPawn(img);
			box.removePawn();
			this.box = box.getNext();
			if(box.getID().equals("B")) {
				HotelGame.setStopFlag(1);
				HotelMessenger.bankMessage();
			}
		}
	}
	
	public void setDice(int dice) {
		this.dice = dice;
	}
}
