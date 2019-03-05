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
import javafx.util.Pair;

/*
 * @author Daniel Christodoulopoulos
 */

public class HotelGame extends Application {

	private static HotelGUI gui;
	private static HotelTimer timer;
	private static ArrayList<HotelPlayer> playerList;
	private static ArrayList<HotelBoardBox> path;
	private static ArrayList<HotelCard> hotelsCards;
	private static ArrayList<HotelCard> buildedHotels;
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
	private static int playerActive;			//id of player if its active or 0 if player turn ends

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
		hotelsCards = HotelFileReader.getHotelsCards();

		playerList = new ArrayList<HotelPlayer>();
		for (int i = 1; i <= 3; i++)
			playerList.add(new HotelPlayer("Player " + i, i, -1, -1, 12000));
		Collections.shuffle(playerList);

		System.out.println("HotelGame.java: Tha arxisei prwtos o " + playerList.get(0).getName());
		hotelMessenger = new HotelMessenger();
		hotelMessenger.showMessagesAndStart(gui.getFileReader().getFolderName());

	}

	public static void playGame() {
		if (stopFlag == 0) {
			System.out.println("HotelGame.java: Game Starting...");
			currentPlayer = playerList.get(0);

			gui.createBoards();

			gui.getInfoBar().getAvailableHotels().setText("Available Hotels: "+HotelFileReader.getHotelsCards().size());
			startX = gui.getGameBoard().getStartBox()._getX();
			startY = gui.getGameBoard().getStartBox()._getY();
			for (HotelPlayer i : playerList) {
				i.setBox(gui.getStartBox());
			}
/*
			// Set starting pawn in the start box
			gui.getStartBox().setPawn(playerList.get(0).getImg());
			currentPlayer.setIsSet(1);
			currentBox = gui.getGameBoard().getGridBoard()[startX][startY];
			
			currentPlayer.setBox(currentBox);
			nextBox = gui.getGameBoard().getGridBoard()[startX + 1][startY];
*/			timer = new HotelTimer(gui.getInfoBar());
			timer.start();
			completeATurn(0);
			

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
		if (n==0) {
			System.out.println("HotelGame.java: setStopFlag setted to zero so game continue");
			//completeATurn(playerActive);
		}
	}

	public static int getPlayerActive() {
		return playerActive;
	}

	public static void setPlayerActive(int n) {
		System.out.println("HotelGame.java : Allazw to playerActive se " + n);
		playerActive = n;
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
	
	public static void completeATurn(int id) {
		if(stopFlag==0) {
			System.out.println("HotelGame.java: completeATurn arxh kanw disable ta buttons ReqBuild,BuyHotel,BuyEntrance kai Bank kai enable to dice");
			
			HotelToolBox.disableButton(1, false);	//enable dice button
			HotelToolBox.disableButton(2, true);	//disable req build button
			HotelToolBox.disableButton(3, true);	//disable buy hotel button
			HotelToolBox.disableButton(4, true);	//disable buy entrance button
			HotelToolBox.disableButton(5, true);	//disable bank button
			HotelToolBox.disableButton(6, true);	//disable pass button
			
			if(id==3) id = 0;
			currentPlayer = playerList.get(id);
			if(playerList.get(id).getIsSet()==0) {
				System.out.println("HotelGame.java: Den exw paiksei akomi eimai o "+id);
				// vazw sto gui to pioni 
				gui.getStartBox().setPawn(playerList.get(id).getImg());
				currentBox = gui.getGameBoard().getGridBoard()[startX][startY];
				currentPlayer.setBox(currentBox);
			//	nextBox = gui.getGameBoard().getGridBoard()[startX + 1][startY];
				playerList.get(id).setIsSet(1);
			}
			playerActive = id;
			hotelMessenger.playerTurn(playerList.get(id).getName());
		}
		else {
			System.out.println("HotelGame.java: completeATurn flag==1 paused");
		}
	}
	
	public static void finishMove() {
		System.out.println("HotelGame.java: finishMove Teleiwsa to transmove tha kanw disable to roll dice");
		System.out.println("HotelGame.java: finishMove thetw stopFlag se 1");
		//setStopFlag(1);
		
		HotelToolBox.disableButton(1, true); // disable dice button
		HotelToolBox.disableButton(6, false); // enable pass button
		
		currentBox = currentPlayer.getCurrentBox();
		System.out.println("HotelGame.java: finishMove to currentBox einai id = " + currentBox.getID()+"  x = " + currentBox._getX() + "  y = "+currentBox._getY());
		if (currentBox.getID().equals("H")) {
			System.out.println("HotelGame.java: finishMove stamatises se dollario ara mporeis na kaneis aitisi gia agora");
			Pair<HotelCard,HotelCard> neighbors = HotelGameBoard.getNeighborHotels(currentBox);
			if (neighbors != null) {
				System.out.println("HotelGame.java: To currentBox einai x = " + currentBox._getX() + " y = " + currentBox._getY());
				System.out.println("HotelGame.java: Geitones exw to " + neighbors.getKey().getName() + " kai to " + neighbors.getValue().getName());
				
				HotelToolBox.disableButton(3, false); // enable buy hotel button
			}
			else {
				System.out.println("HotelGame.java: FinishMove kati pige lathos me thn getneighborshotels");
			}	
		}
		else if (currentBox.getID().equals("E")) {
			System.out.println("HotelGame.java: finishMove eisai se aksina mporeis na xtiseis");
		//	setStopFlag(0);
		}
		else if (currentBox.getID().equals("B")) {
			System.out.println("HotelGame.java: finishMove eisai sthn trapeza mporeis na pareis lefta");
			//setStopFlag(0);
		}
		else if (currentBox.getID().equals("C")) {
			System.out.println("HotelGame.java: finishMove eisai sto dimarxeio mporeis na agoraseis eisodo");
		//	setStopFlag(0);
		}
		else if (currentBox.getID().equals("S")) {
			System.out.println("HotelGame.java: finishMove eisai sthn afetiria");
			//setStopFlag(0);
		}

		// uncomment line to play all players
		//completeATurn(playerActive+1);
		//completeATurn(playerActive); //only first player plays
	}
}
