package hotel;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

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
	private static int stopFlag;
	private static HotelPlayer currentPlayer;
	private static HotelBoardBox currentBox;
	private static HotelBoardBox nextBox;
	private static PauseTransition pause;
	private static PauseTransition pause1;

	// private static int popupFlag = 0;
	// private static PauseTransition delay = new
	// PauseTransition(Duration.seconds(2));
	/*
	 * static { delay.setOnFinished(e -> { popup.hide(); popupFlag = 1; }); }
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		try {
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent t) {
					Platform.exit();
					System.exit(0);
				}
			});
			System.out.println("HotelGame.java: Start Application");
			this.primaryStage = primaryStage;
			prepareGame();

		} catch (Exception ex) {
			System.out.println("HotelGame.java: Exception throw");
			System.out.println(ex);
		}

	}

	/*
	 * @Override public void start(Stage primaryStage) throws Exception { // TODO
	 * Auto-generated method stub Platform.runLater(() -> { try {
	 * System.out.println("HotelGame.java: Start Application"); this.primaryStage =
	 * primaryStage; prepareGame();
	 * 
	 * } catch (Exception ex) {
	 * System.out.println("HotelGame.java: Exception throw");
	 * System.out.println(ex); } });
	 * 
	 * }
	 */
	public static void prepareGame() throws IOException {
		stopFlag = 0;
		pause = new PauseTransition(Duration.seconds(1));
		pause.setOnFinished(event -> {
			continueToGame();
		});
		pause1 = new PauseTransition(Duration.seconds(5));
		// pause.setOnFinished(event -> {continueToGame();}
		// );
		gui = new HotelGUI();
		gui.fileReaderTurnOn();
		gui.createMainWindow(primaryStage);

		playerList = new ArrayList<HotelPlayer>();
		for (int i = 1; i <= 3; i++)
			playerList.add(new HotelPlayer("Player " + i, -1, -1, 12000));
		Collections.shuffle(playerList);

		System.out.println("HotelGame.java: Tha arxisei prwtos o " + playerList.get(0).getName());
		hotelMessenger = new HotelMessenger();
		hotelMessenger.showMessagesAndStart(gui.getFileReader().getFolderName());

	}

	public static void playGame() {
		if (stopFlag == 0) {
			System.out.println("HotelGame.java: Game Starting...");

			gui.createBoards();

			gui.getInfoBar().getAvailableHotels().setText("Available Hotels: 0");
			startX = gui.getGameBoard().getStartBox()._getX();
			startY = gui.getGameBoard().getStartBox()._getY();
			for (HotelPlayer i : playerList) {
				i.setBox(gui.getStartBox());
			}

			// Set starting pawn in the start box
			gui.getStartBox().setPawn(playerList.get(0).getImg());

			currentPlayer = playerList.get(0);

			currentPlayer = playerList.get(0);
			currentBox = gui.getGameBoard().getGridBoard()[startX][startY];
			currentPlayer.setBox(currentBox);
			nextBox = gui.getGameBoard().getGridBoard()[startX + 1][startY];
			timer = new HotelTimer(gui.getInfoBar());
			hotelMessenger.playerTurn(playerList.get(0).getName());
			// pause.play();
			// continueToGame();

		} else {
			System.out.println("HotelGame.java : Game is stopped playGame function");
		}
	}

	public static void continueToGame() {
		System.out.println("Eimai sto continue");
		// timer.start();
		if (stopFlag == 0) {
			//currentPlayer.move(currentPlayer.getCurrentBox().getNext(), currentPlayer.getCurrentBox());
			System.out.println("Stamatisa");
		}
		pause.play();

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
	}

	public static void stopGame() {
		// TODO Stop Game stop timer etc.
		System.out.println("HotelGame.java: Stop Game timer will stop, stopFlag set to 1");
		timer.stop();
		stopFlag = 1;
	}

	public static ArrayList<HotelPlayer> getPlayerList() {
		return playerList;
	}

	public static int getStopFlag() {
		return stopFlag;
	}

	public static void setStopFlag(int n) {
		System.out.println("HotelGame.java : Allazw to flag se " + n);
		stopFlag = n;
	}

	public static HotelPlayer getCurrentPlayer() {
		return currentPlayer;
	}

	public static HotelBoardBox getCurrentBoardBox() {
		return currentBox;
	}

	public static HotelBoardBox getNextBoardBox() {
		return nextBox;
	}

	private static void pressAnyKeyToContinue() {
		System.out.println("Press Enter key to continue...");
		try {
			System.in.read();
		} catch (Exception e) {
		}
	}

	public static Stage getStage() {
		return primaryStage;
	}
	
	public static HotelFileReader getFileReader() {
		return gui.getFileReader();
	}
}
