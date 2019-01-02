package hotel;

import java.io.File;
import java.util.Optional;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/*
 * @author Daniel Christodoulopoulos
 */

public class HotelMessenger {

	private static Stage dialogStage;
	private static Image img;
	private static ImageView imgView;
	private static PauseTransition pause = new PauseTransition(Duration.seconds(10));

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

		// Optional<ButtonType> result = dialogStage.showAndWait();
		dialogStage.setScene(new Scene(vbox));
		dialogStage.show();
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
				// playerTurn(HotelGame.getPlayerList().get(0).getName());
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
				// HotelGame.continueToGame();
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

	public static void showDice(int n) {
		dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initStyle(StageStyle.UNDECORATED);
		Button OKButton = new Button("OK");
		OKButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				dialogStage.hide();
				HotelGame.setStopFlag(0);
				HotelGame.getCurrentPlayer().transitionMove();
			}
		});

		HotelGame.getFileReader();
		img = HotelFileReader.getDice(n);
		imgView = new ImageView();
		imgView.setImage(img);
		// imgView.setFitHeight(20);
		// imgView.setFitWidth(20);
		VBox vbox = new VBox(new Text("Dice is : " + n));
		vbox.setSpacing(10);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(50));
		vbox.getChildren().add(imgView);
		vbox.getChildren().add(OKButton);

		// Optional<ButtonType> result = dialogStage.showAndWait();
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
				//HotelGame.getCurrentPlayer().transitionMove();
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

		// Optional<ButtonType> result = dialogStage.showAndWait();
		dialogStage.setScene(new Scene(vbox));
		dialogStage.show();
	}
}
