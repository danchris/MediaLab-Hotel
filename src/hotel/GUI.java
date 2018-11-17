package hotel;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * @author Daniel Christodoulopoulos
 */
public class GUI extends Application {
	

	public void start(Stage primaryStage) throws Exception {
		Platform.runLater(() -> {
			try {
				System.out.println("GUI.java: Create GUI");

				primaryStage.setTitle("MediaLab Hotel");
				
				SplitPane rootPane = new SplitPane();
			    rootPane.setOrientation(Orientation.VERTICAL);
			    rootPane.setDividerPosition(0,0);
			  //  rootPane.setPrefWidth(1300);
			   // rootPane.setPrefHeight(750);
				SplitPane menuInfo = new SplitPane();
				menuInfo.setOrientation(Orientation.VERTICAL);
				VBox menuBarBox = new VBox();
				MenuBar menuBar = new MenuBar();
				Menu Game = new Menu("Game");
				Menu Statistics = new Menu("Statistics");
				MenuItem Start = new MenuItem("Start");
				MenuItem Stop = new MenuItem("Stop");
				MenuItem Cards = new MenuItem("Cards");
				MenuItem Exit = new MenuItem("Exit");
				MenuItem Hotels = new MenuItem("Hotels");
				MenuItem Entrances = new MenuItem("Entrances");
				MenuItem Profits = new MenuItem("Profits");
				Game.getItems().addAll(Start,Stop,Cards,Exit);
				Statistics.getItems().addAll(Hotels,Entrances,Profits);
				menuBar.getMenus().add(Game);
				menuBar.getMenus().add(Statistics);
				menuBarBox.getChildren().add(menuBar);
				VBox infoBarBox = new VBox(new Label("Info Bar"));
				menuInfo.getItems().addAll(menuBarBox,infoBarBox);
				
				SplitPane boards = new SplitPane();
				boards.setOrientation(Orientation.HORIZONTAL);
				HBox GameBoard = new HBox(new Label("Game Board"));
				HBox OtherBoard = new HBox(new Label("Other Board"));
				boards.getItems().addAll(GameBoard,OtherBoard);
		//		SplitPane horizontal = new SplitPane();
				//horizontal.setOrientation(Orientation.HORIZONTAL);
			//	horizontal.getItems().add(infoBarBox);
		//		VBox GameBoard = new VBox(new Label("Game Board"));
			//	VBox otherBoard = new VBox(new Label("Other Board"));
		//		BorderPane borderPane = new BorderPane();
		    //    borderPane.setTop(menuBar);
			//	rootPane.getChildren().addAll(menuInfo,boards);
				rootPane.getItems().addAll(menuInfo,boards);
				Scene scene = new Scene(rootPane,512,512);
				/*
				SplitPane horizontal = new SplitPane(new VBox(new Label("Game Board")), new VBox(new Label("some other content")));

				horizontal.setOrientation(Orientation.VERTICAL);

				BorderPane borderPane = new BorderPane(horizontal);
				HBox infoBarBox = new HBox(new Label("Info Bar Box"));
				horizontal.getItems().add(infoBarBox);
		        MenuBar menuBar = new MenuBar();
		        Menu Game = new Menu("Game");
		        Menu Statistics = new Menu("Statistics");
		        VBox menuBarBox = new VBox(menuBar);
		        menuBar.getMenus().add(Game);
		        menuBar.getMenus().add(Statistics);				
		        borderPane.setTop(menuBarBox);
				Scene scene = new Scene(borderPane, 600, 400);
			//	Scene scene = new Scene(root);
*/
				primaryStage.setScene(scene);
				primaryStage.show();

			} catch (Exception ex) {
				System.out.println("GUI.java: Exception throw");
				System.out.println(ex);
			}
		});

	}
	
	
	@Override
	public void stop(){
		System.out.println("GUI.java: Hotel Application Exiting...");
	    Platform.exit();
	}
}
