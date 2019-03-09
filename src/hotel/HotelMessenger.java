package hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/*
 * @author Daniel Christodoulopoulos
 */

public class HotelMessenger {

	private static Stage dialogStage;
	private static Image img;
	private static ImageView imgView;
	

	public void showMessagesAndStart(String board) {
		dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initStyle(StageStyle.UNDECORATED);
		Button playButton = new Button("OK");
		playButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				dialogStage.hide();
				boardSelectedMessage(board);
			}
		});
		VBox vbox = new VBox(new Text("Welcome to Medialab Hotel"), playButton);
		vbox.setSpacing(10);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(50));

		dialogStage.setScene(new Scene(vbox));
		dialogStage.show();
	}

	public static String chooseFolderBoard(List<String> choices) {
		ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
		dialog.setTitle("Choice Board");
		dialog.setHeaderText("Choose Board");
		dialog.setContentText("Choose Board:");
		dialog.setResizable(true);

		Optional<String> result = dialog.showAndWait();

		if (result.isPresent()){
		    System.out.println("Your choice: " + result.get());
		   return result.get().toString();
		}
		else {
			HotelGame.killGame();
		}
		
		return null;
	}
	public void boardSelectedMessage(String board) {
		System.out.println("HotelGame.java: boardSelectedMessage");
		dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initStyle(StageStyle.UNDECORATED);
		Button continueButton = new Button("Play");
		continueButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				dialogStage.hide();
				HotelGame.playGame();
			}
		});
		VBox vbox = new VBox(new Text("Board Selected Randomly"), new Text("Name of Board : " + board), continueButton);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(50));
		vbox.setSpacing(10);

		dialogStage.setScene(new Scene(vbox));
		dialogStage.show();

	}

	public void playerTurn(String name) {
		HotelToolBox.setCurrentPlayerText(name); 	// update ToolBox current player name
		System.out.println("HotelMessenger.java: playersOrderingMessage");
		dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initStyle(StageStyle.UNDECORATED);
		Button continueButton = new Button("OK");
		continueButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				dialogStage.hide();
				rollDiceMessage();
			}
		});

		VBox vbox = new VBox(new Text("Player turn : " + name), continueButton);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(50));
		vbox.setSpacing(10);

		dialogStage.setScene(new Scene(vbox));
		dialogStage.show();
	}

	public void rollDiceMessage() {
		System.out.println("HotelMessenger.java: rollDiceMessage");
		dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initStyle(StageStyle.UNDECORATED);
		Button OKButton = new Button("OK");
		OKButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				dialogStage.hide();
			}
		});

		VBox vbox = new VBox(new Text("Please roll dice"), OKButton);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(50));
		vbox.setSpacing(10);

		dialogStage.setScene(new Scene(vbox));
		dialogStage.show();
	}

	public static void showDice(int n,int flag) {
		dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initStyle(StageStyle.UNDECORATED);
		Button OKButton = new Button("OK");
		OKButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				dialogStage.hide();
				HotelGame.setStopFlag(0);
				//normal dice
				if(flag==0) {
					HotelGame.getCurrentPlayer().transitionMove();
				}
				//calculate days
				else {
					System.out.println("HotelMessenger.java: OI meres einai "+n);
					HotelToolBox.disableButton(1, true); //disable roll dice again
					generalInfoMessage(HotelGame.getCurrentHotelEntrance().getName(), "Fee", "You must pay "+HotelGame.getCurrentHotelEntrance().getOwner().getName()+" for " + n+" days so = " + HotelGame.getCurrentHotelEntrance().getMaxUpgradeDailyCost()*n+" MLS.");
					HotelPlayer owner = HotelGame.getCurrentHotelEntrance().getOwner();
					HotelPlayer guest = HotelGame.getCurrentPlayer();
					owner.setMLS(owner.getMLS()+HotelGame.getCurrentHotelEntrance().getMaxUpgradeDailyCost()*n);
					guest.setMLS(guest.getMLS()-HotelGame.getCurrentHotelEntrance().getMaxUpgradeDailyCost()*n);
				}
			}
		});

		HotelGame.getFileReader();
		img = HotelFileReader.getDice(n);
		imgView = new ImageView();
		imgView.setImage(img);

		VBox vbox = new VBox(new Text("Dice is : " + n));
		vbox.setSpacing(10);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(50));
		vbox.getChildren().add(imgView);
		vbox.getChildren().add(OKButton);

		dialogStage.setScene(new Scene(vbox));
		dialogStage.show();
	}

	public static void bankMessage() {
		dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initStyle(StageStyle.UNDECORATED);
		Button yes = new Button("YES");
		Button no = new Button("NO");
		yes.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				dialogStage.hide();
				System.out.println("HotelMessenger.java : Thelw lefta");
				HotelGame.setStopFlag(0);
				HotelGame.getCurrentPlayer().move();

			}
		});
		no.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				dialogStage.hide();
				System.out.println("HotelMessenger.java : Den Thelw lefta");
				HotelGame.setStopFlag(0);
				HotelGame.getCurrentPlayer().transitionMove();
			}
		});

		VBox vbox = new VBox(new Text("Thes Lefta"));
		vbox.setSpacing(10);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(50));
		vbox.getChildren().addAll(yes,no);

		dialogStage.setScene(new Scene(vbox));
		dialogStage.show();
	}
	
	public static void generalInfoMessage(String title, String header, String Content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setGraphic(null);
		alert.setResizable(true);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(Content);
		alert.showAndWait();
	}
	
	// an patihei to buy hotel
	public static void showToBuyHotels(List<String> choices) {
		if(choices.isEmpty()) {
			generalInfoMessage("Choices of Hotels","Hotels","You don't have enough money or not for sale");
		}
		else {
			ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
			dialog.setGraphic(null);
			dialog.setResizable(true);
			dialog.setTitle("Choices of Hotels");
			dialog.setHeaderText("Hotels");
			dialog.setContentText("Choose a Hotel:");
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(option -> {
				for (HotelCard h : HotelFileReader.getHotelsCards()) {
					if (h.getName().equals(option)) {
						System.out.println("HotelToolBox.java: Dialekses na agoraseis to " + option);
						confirmBuyHotel(h);
					}
				}

			});
		}
	}
	
	public static void confirmBuyHotel(HotelCard h) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setResizable(true);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Buy " + h.getName());
		alert.setContentText("After this purchase you will have " + (HotelGame.getCurrentPlayer().getMLS()-h.getPlotCost()) + " MLS.");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			System.out.println("HotelToolBox.java: Agora tou " + h.getName());
			
			// an to exei hdh kapoios to hotel kanw remove to hotel apo tin lista tou kai ton plirwnw
			if(h.getOwner()!=null) {
				// plirwnw thn upoxrewtiki aksia oikopedou ston palio idioktiki
				generalInfoMessage("Buy " + h.getName(), "Buy from " + h.getOwner().getName(), "You will buy " + h.getName() + " from " + h.getOwner().getName());
				h.getOwner().setMLS(h.getOwner().getMLS()+h.getMandatoryPurchaseCost());
				h.getOwner().removeHotel(h);
			}
			
			h.setOwner(HotelGame.getCurrentPlayer());
			HotelGame.getCurrentPlayer().addHotel(h);
			// afairw ta mls pou plirwsa
			HotelGame.getCurrentPlayer().setMLS(HotelGame.getCurrentPlayer().getMLS()-h.getPlotCost());
			HotelToolBox.disableButton(3, true);	//disable buy hotel button
			
			generalInfoMessage("Success Buy Hotel", "Success Purchase", "You are the owner of "+h.getName());
			
		} 
	}
	
	// an patihei to buy entrance
	public static void showPossibleEntrances(List<String> choices) {
		if(choices.isEmpty()) {
			generalInfoMessage("Choices of Hotels", "Error", "You need more money \n or you need to build main building first");
		}
		else {
			ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
			dialog.setGraphic(null);
			dialog.setResizable(true);
			dialog.setTitle("Choices of Hotels");
			dialog.setHeaderText("Hotels");
			dialog.setContentText("Choose a Hotel for entrance:");
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(option -> {
				for (HotelCard h : HotelFileReader.getHotelsCards()) {
					if (h.getName().equals(option)) {
						System.out.println("HotelMessenger.java: Dialekses na valeis eisodo gia to " + option);
						confirmBuyEntrance(h);
						return ;
					}
				}

			});
		}
	}
	
	
	public static void confirmBuyEntrance(HotelCard h) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setResizable(true);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Buy Entrance for " + h.getName());
		
		// an dn exeis freee entrance tha plirwseis
		if(HotelGame.getPassHall()==0) {
			alert.setContentText("After this purchase you will have " + (HotelGame.getCurrentPlayer().getMLS()-h.getEntranceCost()) + " MLS.");
		}
		else {
			alert.setContentText("Free entrance");
		}

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			System.out.println("HotelMessenger.java: Agora eisodo gia to " + h.getName());
			
			// choose a box to put entrance random
			HotelBoardBox tmp = HotelGameBoard.getRandomEntranceBox(h);
			if(tmp==null) {
				// an dn uparxei xwros
				generalInfoMessage("Error", "Entrance Error", "You cannot build any entrance");
				HotelGame.setPassHall(0);  //restore default for city hall
				return;
			}
			//add hotel card entrance to board box
			System.out.println("HotelMessenger.java: Dialextike gia eisodo to " + tmp._getX() + " y = "+tmp._getY());
			tmp.setHotelEntrance(h);
			//add entrance to player class
			HotelGame.getCurrentPlayer().addEntrance();
			// plirwse gia to entrance an dn einai free
			if(HotelGame.getPassHall()==0){
				HotelGame.getCurrentPlayer().setMLS(HotelGame.getCurrentPlayer().getMLS()-h.getEntranceCost());
			}
	
			generalInfoMessage("Entrance Info", "New Entrance", "New Entrance at x = " +tmp._getX() + " y = " +tmp._getY());

			// restore default times gia hall
			HotelGame.setPassHall(0);
			
			HotelToolBox.disableButton(4, true);	//disable buy entrance button
			
		} 
	}
	
	
	// an patithei to req building
	public static void showHotelsForBuilding(List<String> choices) {
		if(choices.isEmpty()) {
			generalInfoMessage("Choices of Hotels", "Error", "No hotels");
		}
		else {
			ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
			dialog.setGraphic(null);
			dialog.setResizable(true);
			dialog.setTitle("Choices of Hotels");
			dialog.setHeaderText("Hotels");
			dialog.setContentText("Choose a Hotel for building:");
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(option -> {
				for (HotelCard h : HotelFileReader.getHotelsCards()) {
					if (h.getName().equals(option)) {
						System.out.println("HotelMessenger.java: Dialekses na xtiseis gia to " + option);
						showBuildingsForHotel(h);
					}
				}

			});
		}
	}
	
	public static void showBuildingsForHotel(HotelCard h) {
		
		
		// an den exei aitithei akomi 
		if(HotelToolBox.getReqDone()==0) {
			System.out.println("HotelMessenger.java: Den exei ginei akomi aitisi");
			// prepei prwta na doume an h aitisi egrkinetai
			Random rand = new Random();
	
			// Obtain a number between [0 - 99].
			int res = rand.nextInt(100);
			String msg;
			if(res<=49) msg = "Accept";
			else if (res<=69) msg = "Decline";
			else if (res<=84) msg = "Free";
			else msg = "Double Fee";
			
			generalInfoMessage("Request Building", "Request result", msg);
			HotelToolBox.setRequestBuildingResult(msg);
			HotelToolBox.setReqDone(1); // egine to req
			
			// Decline
			if(res>=50 && res<=69) {
				System.out.println("HotelMessenger.java Request Decline");
				HotelToolBox.disableButton(2, true);	//disable req building button and return
				// kanw restore tis default times afou egine decline
				HotelToolBox.setReqDone(0);
				HotelToolBox.setBuildMul(-1);
				return ;
			}
			int mul = 1;
			if (res>=70 && res<=84) mul = 0;
			else if (res>=85 && res<=99) mul = 2;
			HotelToolBox.setBuildMul(mul);

		}
		
		System.out.println("HotelMessenger.java: Exei ginei i aitisi proxwrame");
			// an exw hdh parei aitisi alla prin ekana cancel
		
		// an den exei xtistei akomi to main tote mono auto mporeis na xtiseis
		if (h.getBuildStatus()==0) {
			System.out.println("HotelMessenger.java: Den exei xtistei akomi to main ara pame gia main");

			
			int afterBuildMLS = HotelGame.getCurrentPlayer().getMLS() -h.getMainBuildingCost()*HotelToolBox.getBuildMul();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setResizable(true);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Buy Building for " + h.getName());
			alert.setContentText("This is first building so your only choice is main building.\nAfter buy you will have " + afterBuildMLS+" MLS");
			

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				// buy
				System.out.println("HotelMessenger.java: You buy main building ");
				h.setBuildStatus(1);	// xtistike to main building change status
				HotelGame.getCurrentPlayer().setMLS(afterBuildMLS); // plirwse thn trapeza
				// update available hotels. afairese auto to hotel
				HotelGame.addToBuildedHotels(h); // to vazw sto builded kai kanei update to info bar
				
				HotelToolBox.disableButton(2, true);
				generalInfoMessage("Success Buy Building", "Success Purchase", "Build Main Building for "+h.getName());
				
				// kanw restore tis default times afou egine h agora
				HotelToolBox.setBuildMul(-1);
				HotelToolBox.setReqDone(0);
			} 
		}
		else {
			// epilogi gia alla ktiria upgrades klp
			ArrayList<Upgrade> hotelUpgrades = h.getUpgrades();

			List<String> choices = new ArrayList<String>();
			for(Upgrade tmp : hotelUpgrades) {
				if(tmp.getBuildStatus()==0)
					choices.add(tmp.getID()+". "+tmp.getPurchase()+" MLS");
			}
			
			// an ektos apo ta upgrades exeis kai exterior
			if(h.getExteriorBuildCost()!=-1) {
				choices.add("Exterior: "+h.getExteriorBuildCost()+" MLS");
			}
			
			
			ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0),choices);
			dialog.setResizable(true);
			dialog.setTitle("Choose Building");
			dialog.setHeaderText("Buy Upgrade for "+h.getName());
			dialog.setContentText("Choose upgrade: ");
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(selected -> {System.out.println("HotelMessenger.java: Your choice: " + selected);
			
			for(Upgrade tmp : hotelUpgrades) {
				if(selected.equals(tmp.getID()+". "+tmp.getPurchase()+" MLS")) {
					// an dialekses kati apo ta upgraades
					confirmBuyUpgradeFor(tmp,h);
					return ;
				}
			}
			
			// an den dialekses apo ta upgrades alla mporei na dialekses exterior
			if(selected.equals("Exterior: "+h.getExteriorBuildCost()+" MLS")) {
				System.out.println("HotelMessenger.java: Exterior build");
				confirmExteriorFor(h);
				return ;
			}
			
				});
				
		
		}
	}
	
	// confirm buy upgrade
	public static void confirmBuyUpgradeFor(Upgrade up, HotelCard h) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setResizable(true);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Buy Upgrade for " + h.getName());
		alert.setContentText("After this purchase you will have " + (HotelGame.getCurrentPlayer().getMLS()-up.getPurchase()*HotelToolBox.getBuildMul()) + " MLS.");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			System.out.println("HotelMessenger.java: Agora upgrade " + up.getID()+" gia to " + h.getName());
			
			// twra prepei na plirwsw to analogo
			HotelPlayer curr = HotelGame.getCurrentPlayer();
			
			// afairw ta xrimata apo ton player
			curr.setMLS(curr.getMLS()-up.getPurchase()*HotelToolBox.getBuildMul());
			h.addUpgrade(up);	// prosthetw sto ksenodoxeio to upgrade
			up.setBuildStatus(1); // enimerwnw to build status tou upgrade se 1 
			
			HotelToolBox.disableButton(2, true);	//disable reqBuild entrance button
			
			// kanw restore tis default times afou egine h agora
			HotelToolBox.setBuildMul(-1);
			HotelToolBox.setReqDone(0);
			generalInfoMessage("Success Buy Upgrade", "Success Purchase", "Upgrade "+up.getID()+" ready for "+h.getName());
			
		} 
	}
	
	
	//confirm buy exterior
	public static void confirmExteriorFor(HotelCard h) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setResizable(true);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Buy Exterior for " + h.getName());
		alert.setContentText("After this purchase you will have " + (HotelGame.getCurrentPlayer().getMLS()-h.getExteriorBuildCost()*HotelToolBox.getBuildMul()) + " MLS.");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			System.out.println("HotelMessenger.java: Agora exterior gia to " + h.getName());
			
			// twra prepei na plirwsw to analogo
			HotelPlayer curr = HotelGame.getCurrentPlayer();
			
			// afairw ta xrimata apo ton player
			curr.setMLS(curr.getMLS()-h.getExteriorBuildCost()*HotelToolBox.getBuildMul());
			
			// enimerono tis times gia to exterior
			h.setExteriorStatus(1);
			
			HotelToolBox.disableButton(2, true);	//disable reqBuild entrance button
			
			// kanw restore tis default times afou egine h agora
			HotelToolBox.setBuildMul(-1);
			HotelToolBox.setReqDone(0);
			
			generalInfoMessage("Success Buy Exterior", "Success Purchase", "Exterior ready for "+h.getName());
			
		} 
	}
	
	public static void winnerMessage() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Winner");
		alert.setHeaderText("We have a Winner: " +HotelGame.getPlayerList().get(0).getName());
		alert.setContentText("Play Again?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			try {
				System.out.println("HotelMessenger.java: Start new Game");
				HotelGame.startNewGame();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("HotelMessenger.java: Start Button Pressed Exception");
				e.printStackTrace();
			}
		} else {
			HotelGame.killGame();
		}
		
	}

}
