package hotel;

import java.io.IOException;

import javafx.scene.image.Image;

/*
 * @author Daniel Christodoulopoulos
 */

public class HotelPlayer {

	private String name;
	private int x;
	private int y;
	private int mls;
	private Image img;

	public HotelPlayer(String name, int x, int y, int mls) throws IOException {
		this.name = name;
		this.x = x;
		this.y = y;
		this.mls = mls;
		if (name.equals("Player 1")) {
			img = HotelFileReader.getPawn("blue");
		} else if (name.equals("Player 2")) {
			img = HotelFileReader.getPawn("red");
		} else if (name.equals("Player 3")) {
			img = HotelFileReader.getPawn("green");
		} else {
			throw new IOException("Player.java: Error Name");
		}

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
	}

	public int getMLS() {
		return mls;
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
	
	@SuppressWarnings("unlikely-arg-type")
	public void move(HotelBoardBox to, HotelBoardBox from) {
		to.setPawn(img);
		from.removePawn();
		//from.getChildren().remove(from.getPawn());
	}
}
