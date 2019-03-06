package hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Pair;

/*
 * @author Daniel Christodoulopoulos
 */

public class HotelToolBox extends Pane {

	private static Button dice;
	private static Button reqBuilding;
	private static Button buyHotel;
	private static Button buyEntrance;
	private static Button bank;
	private static Button pass;
	private HotelPlayer curr;
	private GridPane grid;
	private int diceNumber;
	private Text diceRes;
	private Text reqBuildingRes;

	public HotelToolBox() {
		setPrefWidth(200);
		grid = new GridPane();

		dice = new Button("Roll Dice");
		diceRes = new Text("Dice Result: ");

		reqBuilding = new Button("Request Building");
		reqBuildingRes = new Text("Request Result: ");

		buyHotel = new Button("Buy Hotel");
		buyEntrance = new Button("Buy Entrance");
		bank = new Button("Request 1000 MLS");
		pass = new Button("Pass");

		dice.setOnAction(actionEvent -> {
			HotelGame.setStopFlag(1);
			diceNumber = new Random().nextInt(6) + 1;
			System.out.println("Dice selected is " + diceNumber);
			curr = HotelGame.getCurrentPlayer();
			curr.setDice(diceNumber);
			HotelMessenger.showDice(diceNumber);
			diceRes.setText("Dice Result: " + Integer.toString(diceNumber));
		});

		buyHotel.setOnAction(actionEvent -> {
			HotelGame.setStopFlag(1);

			System.out.println("HotelToolBox.java: BuyHotel Action");
			Pair<HotelCard, HotelCard> neighbors = HotelGameBoard.getNeighborHotels(HotelGame.getCurrentBoardBox());
			List<String> choices = new ArrayList<String>();
			curr = HotelGame.getCurrentPlayer();
			// check if have enough money for hotels and hotels are still available
			int tmp = neighbors.getKey().getPlotCost();
			if (curr.getMLS() >= tmp && neighbors.getKey().getBuildStatus() == 0)
				choices.add(neighbors.getKey().getName());
			tmp = neighbors.getValue().getPlotCost();
			if (curr.getMLS() >= tmp && neighbors.getValue().getBuildStatus() == 0)
				choices.add(neighbors.getValue().getName());

			HotelMessenger.showToBuyHotels(choices);
		});

		pass.setOnAction(actionEvent -> {

			System.out.println("HotelToolBox.java: Pass Action");
			HotelGame.setStopFlag(0);
			// uncomment for play all players
			// HotelGame.completeATurn(HotelGame.getPlayerActive()+1);
			HotelGame.completeATurn(HotelGame.getPlayerActive());

		});

		bank.setOnAction(actionEvent -> {
			HotelGame.setStopFlag(1);
			System.out.println("HotelToolBox.java: Bank button Action");
			curr = HotelGame.getCurrentPlayer();
			System.out.println("HotelToolBox.java: o " + curr.getName() + " tha parei +1000 MLS");
			curr.setMLS(curr.getMLS() + 1000);
			bank.setDisable(true);
		});

		buyEntrance.setOnAction(actionEvent -> {
			HotelGame.setStopFlag(1);
			System.out.println("HotelToolBox.java: buyEntrance Action");
			curr = HotelGame.getCurrentPlayer();

			/*
			// allos exei hdh eisodo edw
			if(curr.getCurrentBox().getHotelEntrance()!=null) {
				System.out.println("HotelToolBox.java: Exei kapoios allos eisodo edw");
				HotelMessenger.generalInfoMessage("Buy Entrance", "Error", "You can not buy entrance here. "+curr.getCurrentBox().getEntranceOwner().getName() + " has already one");
				
				return ;
			}
			*/
			System.out.println("HotelToolBox.java: Possible Entrances");
			System.out.println(curr.getHotels());
			
			// den exeis kanena hotel
			if (curr.getHotels() == null || curr.getHotels().isEmpty()) {
				System.out.println("HotelToolBox.java: Den exeis kanena hotel");
				HotelMessenger.generalInfoMessage("Buy Entrance", "Error", "You don't own any hotel");
				
				return ;
			} else {
				ArrayList<HotelCard> currHotels = curr.getHotels();
				//ArrayList<HotelBoardBox> possibleEntrances = HotelGameBoard.getPossibleBoxesForBuildOrEntrance(curr.getCurrentBox());

				List<String> choices = new ArrayList<String>();
				for (HotelCard h : currHotels) {
					// an exeis ta lefta tote valto sto choices kai exei to hotel to main building
					if ((curr.getMLS()-h.getEntranceCost()) >=0 && h.getBuildStatus()==0) {
						choices.add(h.getName());
					}
				}
				HotelMessenger.showPossibleEntrances(choices);
			}

		});
		
		reqBuilding.setOnAction(actionEvent -> {
			System.out.println("HotelToolBox.java: Request Building Action");

		});

		grid.add(dice, 0, 0);
		grid.add(diceRes, 0, 1);
		grid.add(reqBuilding, 0, 2);
		grid.add(reqBuildingRes, 0, 3);
		grid.add(buyHotel, 0, 4);
		grid.add(buyEntrance, 0, 5);
		grid.add(bank, 0, 6);
		grid.add(pass, 0, 7);
		grid.setVgap(15);

		getChildren().add(grid);

	}

	// f = true -> disable
	// f = false -> enable
	// i = 1 -> dice
	// i = 2 -> reqBuilding
	// i = 3 -> buyHotel
	// i = 4 -> buyEntrance
	// i = 5 -> bank
	// i = 6 -> pass
	public static void disableButton(int i, boolean f) {
		if (i == 1)
			dice.setDisable(f);
		else if (i == 2)
			reqBuilding.setDisable(f);
		else if (i == 3)
			buyHotel.setDisable(f);
		else if (i == 4)
			buyEntrance.setDisable(f);
		else if (i == 5)
			bank.setDisable(f);
		else if (i == 6)
			pass.setDisable(f);
		else {
			System.out.println("HotelToolBox.java: disableButton wrong input");
		}
	}

}
