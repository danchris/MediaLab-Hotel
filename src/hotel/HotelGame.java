package hotel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
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
	private static ArrayList<HotelCard> buildedHotels = null;
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
	private static int passHall = 0;			// 0 if not pass hall | 1 if pass hall
	private static HotelCard hotelEntrance = null;

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
			HotelGame.primaryStage = primaryStage;
			prepareGame();

		} catch (Exception ex) {
			System.out.println("HotelGame.java: Exception throw");
			System.out.println(ex);
		}

	}


	public static void prepareGame() throws IOException {
		stopFlag = 0;
		pause = new PauseTransition(Duration.seconds(1));
		pause.setOnFinished(event -> {
			continueToGame();
		});
		setPause1(new PauseTransition(Duration.seconds(5)));
		gui = new HotelGUI();
		gui.fileReaderTurnOn();
		gui.createMainWindow(primaryStage);
		setHotelsCards(HotelFileReader.getHotelsCards());

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

			gui.getInfoBar().setAvailableHotels(HotelFileReader.getHotelsCards().size());
			startX = gui.getGameBoard().getStartBox()._getX();
			startY = gui.getGameBoard().getStartBox()._getY();
			for (HotelPlayer i : playerList) {
				i.setBox(gui.getStartBox());
			}

			timer = new HotelTimer(gui.getInfoBar());
			timer.start();
			completeATurn(0);
			


		} else {
			System.out.println("HotelGame.java : Game is stopped playGame function");
		}
	}

	public static void continueToGame() {
		System.out.println("Eimai sto continue");
		if (stopFlag == 0) {
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
	
	public static void killGame() {
		if (timer != null)
			timer.stop();
		System.out.println("HotelGame.java: Hotel Application Exiting...");
		Platform.exit();
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

	public static Stage getStage() {
		return primaryStage;
	}
	
	public static HotelFileReader getFileReader() {
		return gui.getFileReader();
	}
	
	public static void addToBuildedHotels(HotelCard h) {
		if(buildedHotels == null) buildedHotels = new ArrayList<HotelCard>();
		buildedHotels.add(h);
		// update info bar
		gui.getInfoBar().setAvailableHotels(gui.getInfoBar().getAvailableHotels()-1);
	}
	
	public static HotelCard getCurrentHotelEntrance() {
		return hotelEntrance;
	}

	static void completeATurn(int index) {
		if(stopFlag==0) {
			System.out.println("HotelGame.java: completeATurn arxh kanw disable ta buttons ReqBuild,BuyHotel,BuyEntrance kai Bank kai enable to dice");
			
			
			HotelToolBox.disableButton(1, false);	//enable dice button
			HotelToolBox.disableButton(2, true);	//disable req build button
			HotelToolBox.disableButton(3, true);	//disable buy hotel button
			HotelToolBox.disableButton(4, true);	//disable buy entrance button
			HotelToolBox.disableButton(5, true);	//disable bank button
			HotelToolBox.disableButton(6, true);	//disable pass button
			
			if(index==playerList.size()) index = 0;
			currentPlayer = playerList.get(index);
			if(playerList.get(index).getIsSet()==0) {
				System.out.println("HotelGame.java: Den exw paiksei akomi eimai o "+index);
				// vazw sto gui to pioni 
				gui.getStartBox().setPawn(playerList.get(index).getImg());
				currentBox = gui.getGameBoard().getGridBoard()[startX][startY];
				currentPlayer.setBox(currentBox);
				playerList.get(index).setIsSet(1);
			}
			playerActive = index;
			hotelMessenger.playerTurn(playerList.get(index).getName());
		}
		else {
			System.out.println("HotelGame.java: completeATurn flag==1 paused");
		}
	}
	
	public static void finishMove() {
		System.out.println("HotelGame.java: finishMove Teleiwsa to transmove tha kanw disable to roll dice");
		System.out.println("HotelGame.java: finishMove thetw stopFlag se 1");
		
		HotelToolBox.disableButton(1, true); // disable dice button
		
		
		currentBox = currentPlayer.getCurrentBox();
		
		// edw tha vlepw an uparxei eisodos kai na plirwnei o kathenas ton allon

		checkForEntranceAndPay();
		
		
		HotelToolBox.disableButton(6, false); // enable pass button
		
		
		System.out.println("HotelGame.java: finishMove to currentBox einai id = " + currentBox.getID()+"  x = " + currentBox._getX() + "  y = "+currentBox._getY());
		
		// edw agorazeis hotels
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
		// edw agorazeis eisodous kai kaneis aitiseis gia ktiria
		else if (currentBox.getID().equals("E")) {
			System.out.println("HotelGame.java: finishMove eisai se aksina mporeis na xtiseis h buy eisodo");
			HotelToolBox.disableButton(2, false);	//enable request building button
			HotelToolBox.disableButton(4, false);	//enable buy entrance button
		}
		// edw pairneis lefta apo tin trapeza
		else if (currentBox.getID().equals("B")) {
			System.out.println("HotelGame.java: finishMove eisai sthn trapeza mporeis na pareis lefta");
			HotelToolBox.disableButton(5, false);	//enable bank button
		}
		// edw agorazeis tzampa eisodo gia opoio hotel exeis
		else if (currentBox.getID().equals("C")) {
			System.out.println("HotelGame.java: finishMove eisai sto dimarxeio mporeis na agoraseis eisodo");
			HotelToolBox.disableButton(4, false);	//enable buy entrance button
		}
		// afetiria dn kanies tipota
		else if (currentBox.getID().equals("S")) {
			System.out.println("HotelGame.java: finishMove eisai sthn afetiria");
		}
	}
	
	
	static void checkForEntranceAndPay() {
		String msg;
		
		// an den uparxe eisodos epestrepse dn plirwneis tipota
		if(currentBox.getHotelEntrance()==null) {
			System.out.println("HotelGame.java: Den uparxei kamia eisodos sunexeia tou game");
			return ;
		}
		
		HotelPlayer entranceOwner = currentBox.getEntranceOwner();
		if(entranceOwner.getID()==currentPlayer.getID()) {
			System.out.println("HotelGame.java: Esu exeis thn eisodo dn plirwneis");
			return ;
		}
		
		hotelEntrance = currentBox.getHotelEntrance();
		int maxUp = hotelEntrance.getMaxUpgrade();
		int maxDailyCost = hotelEntrance.getMaxUpgradeDailyCost();
		if(maxUp == -1) {
			maxDailyCost = hotelEntrance.getOnlyMainBuildingDailyCost();
			System.out.println("HotelGame.java: Einai xtismeno mono to vasiko ktirio ara plirwneis gia to "+hotelEntrance.getName() + " : " + maxDailyCost+ " MLS");
			msg = "Prepei na plirwseis ton " +hotelEntrance.getOwner().getName() + " gia to hotel "+ hotelEntrance.getName() + " gia to main building "+maxDailyCost+" MLS/day.";
		}else {
			System.out.println("HotelGame.java: Einani xtismeno to " + maxUp + " plirwneis " + maxDailyCost + " MLS");
			msg = "Prepei na plirwseis ton " +hotelEntrance.getOwner().getName() + " gia to hotel "+ hotelEntrance.getName() + " gia to upgrade " + maxUp +" "+maxDailyCost+" MLS/day.";
		}
		
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setResizable(true);
		alert.setTitle("Fee Message");
		alert.setHeaderText("Plirwmi gia to " + hotelEntrance.getName());
		alert.setContentText(msg);
		    
		    // rikse zari na doume poses meres tha katseis
		    alert.setOnHidden(evt -> {
		    	
		    	// thetw dice flag se 1 gia na allaksw thn leitourga tou dice
			    HotelToolBox.setDiceFlag(1);
			    HotelMessenger.generalInfoMessage("Roll Dice", "Roll Dice", "Roll Dice to calculate days");
			    HotelToolBox.disableButton(1, false); // enable to roll dice gia ligo
		    });

		    alert.show(); 
	}
	
	public static HotelPlayer getById(int id) {
		System.out.println("HotelGame.java: ID = " + id);
		for (HotelPlayer p : playerList) {
			System.out.println("HotelGame.java: EKsetazo ton " + p.getID());
			if(p.getID()==id) {
				System.out.println("HotelGame.java: Epistrefw ton " + p.getID());
				return p;
			}
		}
		
		return null;
	}
	
	public static void setPassHall(int f) {
		passHall = f;
	}
	
	public static int getPassHall() {
		return passHall;
	}
	
	public static void removePlayerFromGame() {
		
		System.out.println("HoteGame.java: Xreokopises "+currentPlayer.getName());
		HotelMessenger.generalInfoMessage("Xreokopia", "Game over for "+currentPlayer.getName(), "Xreokopises "+currentPlayer.getName());
		playerList.remove(currentPlayer);		// afairesi apo tin lista
		currentBox.removePawn(); // afairw apo to map
		for(HotelBoardBox tmp : gui.getGameBoard().getPath()) {
			System.out.println("HotelGame.java: Eksetazw to "+tmp.getID());
			if(tmp.getEntranceOwner()!=null && tmp.getEntranceOwner().getName().equals(currentPlayer.getName())) {
				tmp.deleteEntrance();
			}
		}
		if(playerList.size()==1) winner();
		HotelGame.setStopFlag(0);
		HotelToolBox.setDiceFlag(0);
		// uncomment for play all players
		 HotelGame.completeATurn(HotelGame.getPlayerActive()+1);
	}
	
	public static void winner() {
		System.out.println("HotelGame.java: Exoume winner " + playerList.get(0));
		HotelMessenger.winnerMessage();
	}

	public static ArrayList<HotelBoardBox> getPath() {
		return path;
	}

	public static void setPath(ArrayList<HotelBoardBox> path) {
		HotelGame.path = path;
	}

	public static ArrayList<HotelCard> getHotelsCards() {
		return hotelsCards;
	}

	public static void setHotelsCards(ArrayList<HotelCard> hotelsCards) {
		HotelGame.hotelsCards = hotelsCards;
	}

	public static PauseTransition getPause1() {
		return pause1;
	}

	public static void setPause1(PauseTransition pause1) {
		HotelGame.pause1 = pause1;
	}
}
