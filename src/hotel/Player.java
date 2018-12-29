package hotel;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.scene.image.Image;


/*
 * @author Daniel Christodoulopoulos
 */


public class Player {

	private String name;
	private int x;
	private int y;
	private int mls;
	private Image img;

	public Player(String name, int x, int y, int mls) throws IOException {
		this.name = name;
		this.x = x;
		this.y = y;
		this.mls = mls;
		try {
			if (name.equals("Player 1")) {
				img = new Image(new FileInputStream(
						"/home/daniel/Sxolh/ROH Y/7o eksamino/MediaLab/project/Hotel/imgs/blue.png"));
			}
			else if (name.equals("Player 2")) {
				img = new Image(new FileInputStream(
						"/home/daniel/Sxolh/ROH Y/7o eksamino/MediaLab/project/Hotel/imgs/red.png"));
			}
			else if (name.equals("Player 3")) {
				img = new Image(new FileInputStream(
						"/home/daniel/Sxolh/ROH Y/7o eksamino/MediaLab/project/Hotel/imgs/green.png"));
			}
			else {
				throw new IOException("Player.java: Error Name");
			}
		} catch (IOException e) {
			System.out.println("Player.java: IOException Image Error");
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
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
}
