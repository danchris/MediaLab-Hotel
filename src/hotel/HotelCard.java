package hotel;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Pair;

public class HotelCard {

	private int id;
	private String name;
	private int plotCost;
	private int mandatoryPurchaseCost;
	private int entranceCost;
	private int mainBuildingCost;
	private int onlyMainBuildingDailyCost;
	private ArrayList<Pair<Integer, Integer>> upgrades = null;
	private int exteriorBuildCost = -1;
	private int exteriorDailyCost = -1;
	private HotelPlayer owner = null;
	private int buildStatus = 0;		//0 if its not build yet | 1 if its build
	private ArrayList<HotelBoardBox> entrances = null;

	public HotelCard(ArrayList<String> lines, int id) {

		this.id = id;
		this.name = lines.get(0);
		this.plotCost = Integer.parseInt(lines.get(1));
		this.mandatoryPurchaseCost = Integer.parseInt(lines.get(2));
		this.entranceCost = Integer.parseInt(lines.get(3));
		this.mainBuildingCost = Integer.parseInt(lines.get(4));
		this.onlyMainBuildingDailyCost = Integer.parseInt(lines.get(5));

		if (lines.size() >= 6) {
			upgrades = new ArrayList<Pair<Integer, Integer>>();
			Pair<Integer, Integer> tmpPair;
			for (int i = 6; i < lines.size() - 2; i += 2) {
				tmpPair = new Pair<Integer, Integer>(Integer.parseInt(lines.get(i)),
						Integer.parseInt(lines.get(i + 1)));
				upgrades.add(tmpPair);
			}
			if (lines.size() >= 8) {
				exteriorBuildCost = Integer.parseInt(lines.get(lines.size() - 2));
				exteriorDailyCost = Integer.parseInt(lines.get(lines.size() - 1));
			}
		}
	}

	public void printHotelCard() {
		System.out.println("Name: " + this.name);
		System.out.println("Kostos oikopedou " + this.plotCost + " , Upoxrewtiko kostos " + this.mandatoryPurchaseCost);
		System.out.println("Kostos eisodou " + this.entranceCost);
		System.out.println(
				"Kostos main ktiriou " + this.mainBuildingCost + " , Daily costos " + this.onlyMainBuildingDailyCost);

		for (Pair<Integer, Integer> tmp : this.upgrades) {
			System.out.println("Upgrade kostos : " + tmp.getKey() + " , Daily upgrade kostos " + tmp.getValue());
		}
		if (exteriorBuildCost != -1) {
			System.out.println("Ekswterikos xwros kostos: " + this.exteriorBuildCost + " , Daily ekswterikos "
					+ this.exteriorDailyCost);
		}
	}

	public String getName() {
		return name;
	}

	public int getID() {
		return id;
	}

	public int getPlotCost() {
		return plotCost;
	}

	public int getMandatoryPurchaseCost() {
		return mandatoryPurchaseCost;
	}

	public int getEntranceCost() {
		return entranceCost;
	}

	public int getMainBuildingCost() {
		return mainBuildingCost;
	}

	public int getOnlyMainBuildingDailyCost() {
		return onlyMainBuildingDailyCost;
	}

	public ArrayList<Pair<Integer, Integer>> getUpgrades() {
		return upgrades;
	}

	public int getExteriorBuildCost() {
		return exteriorBuildCost;
	}

	public int getExteriorDailyCost() {
		return exteriorDailyCost;
	}
	
	public HotelPlayer getOwner() {
		return owner;
	}
	
	public void setOwner(HotelPlayer p) {
		this.owner = p;
	}
	
	int getBuildStatus() {
		return buildStatus;
	}
	
	void setBuildStatus(int i) {
		this.buildStatus = i;
	}

	public void hotelCardDialogBox() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Hotel Box Information");
		alert.setGraphic(null);
		alert.setHeaderText(name + " Hotel");
		GridPane info = new GridPane();
		info.setHgap(10); //horizontal gap in pixels => that's what you are asking for
		info.setVgap(10); //vertical gap in pixels
		info.setPadding(new Insets(10, 10, 10, 10));
		info.add(new Text("Name: " + name), 0, 0);
		info.add(new Text("oikopedou: " + plotCost), 0, 1);
		info.add(new Text("Upoxrewtiko : " + mandatoryPurchaseCost), 1, 1);
		info.add(new Text("eisodou : " + entranceCost), 0, 2);
		info.add(new Text("main ktiriou : " + mainBuildingCost), 0, 3);
		info.add(new Text("Daily : " + onlyMainBuildingDailyCost), 1, 3);
		int j = 4, counter = 1;
		for (Pair<Integer, Integer> tmp : upgrades) {
			info.add(new Text("Upgrade " + counter + " : " + tmp.getKey()), 0, j);
			info.add(new Text("Daily upgrade "+counter+" : " + tmp.getValue()), 1, j);
			counter++;
			j++;
		}
		if (exteriorBuildCost != -1) {
			info.add(new Text("Ekswterikos xwros: " + exteriorBuildCost), 0, j);
			info.add(new Text("Daily ekswterikos: " + exteriorDailyCost), 1, j);
		}
		alert.getDialogPane().setContent(info);
		alert.showAndWait();

	}
	
	public ArrayList<HotelBoardBox> getEntrances(){
		return entrances;
	}
	
	public void addEntrance(HotelBoardBox b) {
		System.out.println("HotelCard.java: Prosthetw stis eisodous mou to kouti x = "+b._getX() + " y = "+b._getY());
		if(entrances==null) entrances = new ArrayList<HotelBoardBox>();
		entrances.add(b);
	}
}
