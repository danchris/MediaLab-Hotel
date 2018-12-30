package hotel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

	public void showMessagesAndStart(String board) {
		dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initStyle(StageStyle.UNDECORATED);
		Button playButton = new Button("Play!");
		playButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				dialogStage.hide();
				boardSelectedMessage(board);
			}
		});
		VBox vbox = new VBox(new Text("Welcome to Medialab Hotel"), playButton);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(50));

		// Optional<ButtonType> result = dialogStage.showAndWait();
		dialogStage.setScene(new Scene(vbox));
		dialogStage.show();
	}

	public void boardSelectedMessage(String board) {
		System.out.println("HotelGame.java: boardSelectedMessage");
		dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initStyle(StageStyle.UNDECORATED);
		Button continueButton = new Button("Continue");
		continueButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				dialogStage.hide();
				playerTurn(HotelGame.getPlayerList().get(0).getName());
			}
		});
		VBox vbox = new VBox(new Text("Board Selected Randomly"),
				new Text("Name of Board : " + board), continueButton);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(50));

		dialogStage.setScene(new Scene(vbox));
		dialogStage.show();

	}
	
	public void playerTurn(String name) {
		System.out.println("HotelMessenger.java: playersOrderingMessage");
		dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initStyle(StageStyle.UNDECORATED);
		Button continueButton = new Button("Continue");
		continueButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				dialogStage.hide();
				HotelGame.playGame();
			}
		});
		
		VBox vbox = new VBox(new Text("Player turn : " + name), continueButton);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(50));

		dialogStage.setScene(new Scene(vbox));
		dialogStage.show();
	}

}
