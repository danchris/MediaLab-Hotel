package hotel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;


/*
 * @author Daniel Christodoulopoulos
 */

public class HotelGame extends Application {

	private static HotelGUI gui;
	private static HotelTimer timer;
	private static ArrayList<HotelPlayer> playerList;
	private static int startX;
	private static int startY;
	private static HotelMessenger hotelMessenger;
	private static Stage primaryStage;

	// private static int popupFlag = 0;
	// private static PauseTransition delay = new
	// PauseTransition(Duration.seconds(2));
	/*
	 * static { delay.setOnFinished(e -> { popup.hide(); popupFlag = 1; }); }
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Platform.runLater(() -> {
			try {
				System.out.println("HotelGame.java: Start Application");
				this.primaryStage = primaryStage;
				prepareGame();

			} catch (Exception ex) {
				System.out.println("HotelGame.java: Exception throw");
				System.out.println(ex);
			}
		});

	}

	
	public static void prepareGame() throws IOException {
		gui = new HotelGUI(primaryStage);
		gui.getInfoBar().getAvailableHotels().setText("Available Hotels: 0");
		startX = gui.getHotelBoards().getGameBoard().getStartBox()._getX();
		startY = gui.getHotelBoards().getGameBoard().getStartBox()._getY();
		timer = new HotelTimer(gui.getInfoBar());
		playerList = new ArrayList<HotelPlayer>();
		for (int i = 1; i <= 3; i++)
			playerList.add(new HotelPlayer("Player " + i, startX, startY, 12000));
		Collections.shuffle(playerList);
		System.out.println("HotelGame.java: Tha arxisei prwtos o " + playerList.get(0).getName());
		hotelMessenger = new HotelMessenger();
		hotelMessenger.showMessagesAndStart(gui.getFileReader().getFolderName());
		
	}
	public static void playGame() {
		System.out.println("HotelGame.java: Game Starting...");
		
		// Set starting pawn in the star box
		gui.getHotelBoards().getGameBoard().getGridBoard()[startX][startY].setPawn(playerList.get(0).getImg());

		timer.start();
		
	}
	@Override
	public void stop() {
		if (timer != null)
			timer.stop();
		System.out.println("HotelGame.java: Hotel Application Exiting...");
		Platform.exit();
	}

	public static void startNewGame() {
		// TODO Start new game Timer etc.
		System.out.println("HotelGame.java: Start New Game");
		timer.stop();
		try {
			prepareGame();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("HotelGame.java : Start new Game exception prepareGame");
			e.printStackTrace();
		}
		//timer.start();
	}

	public static void stopGame() {
		// TODO Stop Game stop timer etc.
		System.out.println("HotelGame.java: Stop Game");
		timer.stop();
	}

	
	public static ArrayList<HotelPlayer> getPlayerList(){
		return playerList;
	}



}
