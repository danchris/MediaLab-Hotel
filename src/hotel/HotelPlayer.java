package hotel;

import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;

/*
 * @author Daniel Christodoulopoulos
 */

public class HotelPlayer {

	private String name;
	private int id;
	private int x;
	private int y;
	private int mls;
	private int maxMLS;
	private Image img;
	private HotelBoardBox box;
	private int startYet;
	private int dice;
	private int isSet;
	private PauseTransition pause;
	private ArrayList<HotelCard> hotels = null;
	private int entrancesNumber = 0;

	public HotelPlayer(String name, int id, int x, int y, int mls) throws IOException {
		this.name = name;
		this.id = id;
		this.x = x;
		this.y = y;
		this.mls = mls;
		this.maxMLS = mls;
		this.isSet = 0;
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
			System.out.println("HotelPlayer.java: Pause finished");
			transitionMove();
		});

	}
	
	public int getIsSet() {
		return isSet;
	}

	public void setIsSet(int a) {
		this.isSet = a;
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
	
	public int getID() {
		return this.id;
	}

	public void setMLS(int mls) {
		this.mls = mls;
		System.out.println("HotelPlayer.java: Eimai o " + name + " kai ta nea moy lefta einai " + mls);
		HotelInfoBar.setMLS(id,this.mls);
		if (mls > maxMLS)
			maxMLS = mls;
		if(this.mls<0) {
			HotelGame.removePlayerFromGame();
		}
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
		return this.box;
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
		if (HotelGame.getStopFlag() == 1)
			return;
		HotelBoardBox tmp = box;
		dice = 1;
		while(dice > 0) {
			dice--;
			tmp = tmp.getNext();
		}
		System.out.println("Tha paw sto " + tmp.getID() + tmp._getX() + tmp._getY());
		if(tmp.hasPlayer() && !tmp.getPlayer().equals(this)) {
			while (tmp.hasPlayer()) {
				System.out.println("Ela");
				tmp = tmp.getNext();
			}
		}
		box.removePawn();
		box.setPlayer(null);
		tmp.setPawn(img);
		tmp.setPlayer(this);
		this.box = tmp;
	}

	public int getStartYet() {
		return this.startYet;
	}

	public void setStartYet() {
		this.startYet = 1;
	}

	public void transitionMove() {
		if (HotelGame.getStopFlag() == 1)
			return;
		System.out.println("HotelPlayer.java: Dice " + dice);
		if (dice > 0) {
			if (!box.getNext().hasPlayer()) {
				dice--;
				box.getNext().setPawn(img);
				box.removePawn();
				box.setPlayer(null);
				this.box = box.getNext();
				box.setPlayer(this);
				// an eimai sthn trapeza energopoihse to button
				if (box.getID().equals("B")) {
					System.out.println("HotelPlayer.java: Transision Move perasa apo trapeza na energopoihsw to koumpi");
					HotelToolBox.disableButton(5, false);	//enable bank button
				}
				// an eimai sto hall energopoihse to button
				if (box.getID().equals("C")) {
					System.out.println("HotelPlayer.java: Transision Move perasa apo city hall na energopoihsw to koumpi");
					HotelGame.setPassHall(1); // energopoihse thn global metavliti
					HotelToolBox.disableButton(4, false);	//enable buy entrance button
				}
			} else {
				System.out.println("HotelPlayer.java: Einai allos mprosta mou");
				HotelBoardBox tmp = box;
				int c = 1;
				// most dummy solution for not conflict the cars in same box i know it's bad...
				// my English too :P
				if(tmp.getNext().hasPlayer()) {
					if(tmp.getNext().getID().equals("B")) {
						System.out.println("HotelPlayer.java: Transision Move perasa apo trapeza na energopoihsw to koumpi");
						HotelToolBox.disableButton(5, false);	//enable bank button
					}
					// an eimai sto hall energopoihse to button
					if (box.getID().equals("C")) {
						System.out.println("HotelPlayer.java: Transision Move perasa apo city hall na energopoihsw to koumpi");
						HotelGame.setPassHall(1); // energopoihse thn global metavliti
						HotelToolBox.disableButton(4, false);	//enable buy entrance button
					}
					tmp = tmp.getNext().getNext();
					c++;
					if(tmp.hasPlayer()) {
						if(tmp.equals("B")) {
							System.out.println("HotelPlayer.java: Transision Move perasa apo trapeza na energopoihsw to koumpi");
							HotelToolBox.disableButton(5, false);	//enable bank button
						}
						// an eimai sto hall energopoihse to button
						if (box.getID().equals("C")) {
							System.out.println("HotelPlayer.java: Transision Move perasa apo city hall na energopoihsw to koumpi");
							HotelGame.setPassHall(1); // energopoihse thn global metavliti
							HotelToolBox.disableButton(4, false);	//enable buy entrance button
						}
						tmp = tmp.getNext();
						c++;
					}
				}
				System.out.println("HotelPlayer.java: Prosperasa " + c + " koutia");
				dice-=c;
				tmp.setPawn(img);
				box.removePawn();
				box.setPlayer(null);
				this.box = tmp;
				tmp.setPlayer(this);
			}
			pause.play();
		}
		else {
			System.out.println("HotelPlayer.java: TransisionMove box " + box.getID());
			HotelGame.finishMove();
		}

	}

	public void setDice(int dice) {
		this.dice = dice;
	}
	
	public ArrayList<HotelCard> getHotels(){
		System.out.println("HotelPlayer.java: Get Hotels");
		
		return this.hotels;
	}
	
	public void addHotel(HotelCard h) {
		if(hotels==null) hotels = new ArrayList<HotelCard>();
		this.hotels.add(h);
	}
	
	public void removeHotel(HotelCard h) {
		this.hotels.remove(h);
	}
	
	public void addEntrance() {
		this.entrancesNumber++;
		System.out.println("HotelGame.java: Eimai o "+name+" kai auksanw ta entrances se "+entrancesNumber);
	}
	
	public int getEntrances() {
		return this.entrancesNumber;
	}
}
