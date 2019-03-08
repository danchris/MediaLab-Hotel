package hotel;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/*
 * @author Daniel Christodoulopoulos
 */


class Upgrade{
	
	private int id;
	private int purchase;
	private int daily;
	private int buildStatus = 0;
	
	/*
	 * @param id of upgrade
	 * @param cost of purchase
	 * @param daily cost of upgrade
	 */
	public Upgrade(int id, int purchase, int daily) {
		this.id = id;
		this.purchase = purchase;
		this.daily = daily;
	}
	
	/*
	 * @return id of upgrade
	 */
	public int getID() {
		return id;
	}
	
	/*
	 * @param build status of upgrade
	 */
	public void setBuildStatus(int i) {
		this.buildStatus = i;
	}
	
	/*
	 * @return build status of upgrade
	 */
	public int getBuildStatus() {
		return buildStatus;
	}
	
	/*
	 * @return purchase cost of upgrade
	 */
	public int getPurchase() {
		return purchase;
	}
	
	/*
	 * @return daily cost of upgrade
	 */
	public int getDaily() {
		return daily;
	}
}

public class HotelCard {

	private int id;
	private String name;
	private int plotCost;
	private int mandatoryPurchaseCost;
	private int entranceCost;
	private int mainBuildingCost;
	private int onlyMainBuildingDailyCost;
	private ArrayList<Upgrade> upgrades = null;
	private int exteriorBuildCost = -1;
	private int exteriorDailyCost = -1;
	private int exteriorStatus = 0;
	private HotelPlayer owner = null;
	private int buildStatus = 0;		//0 if its not build yet | 1 if its build
	private ArrayList<HotelBoardBox> entrances = null;
	private static int maxUpgrade = -1;
	private static int maxUpgradeDailyCost = -1;

	/*
	 * @param lines of file
	 * @param id of card
	 */
	
	public HotelCard(ArrayList<String> lines, int id) {

		this.id = id;
		this.name = lines.get(0);
		this.plotCost = Integer.parseInt(lines.get(1));
		this.mandatoryPurchaseCost = Integer.parseInt(lines.get(2));
		this.entranceCost = Integer.parseInt(lines.get(3));
		this.mainBuildingCost = Integer.parseInt(lines.get(4));
		this.onlyMainBuildingDailyCost = Integer.parseInt(lines.get(5));

		if (lines.size() >= 6) {
			int counter = 1;
			upgrades = new ArrayList<Upgrade>();
			Upgrade tmpUp;
			for (int i = 6; i < lines.size() - 2; i += 2) {
				tmpUp = new Upgrade(counter++,Integer.parseInt(lines.get(i)),Integer.parseInt(lines.get(i+1)));
				upgrades.add(tmpUp);
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

		for (Upgrade tmp : this.upgrades) {
			System.out.println("Upgrade "+ tmp.getID()+" kostos : " + tmp.getPurchase() + " , Daily upgrade kostos " + tmp.getDaily());
		}
		if (exteriorBuildCost != -1) {
			System.out.println("Ekswterikos xwros kostos: " + this.exteriorBuildCost + " , Daily ekswterikos "
					+ this.exteriorDailyCost);
		}
	}

	/*
	 * @return name of hotel
	 */
	public String getName() {
		return name;
	}

	/*
	 * @return id of hotel
	 */
	public int getID() {
		return id;
	}

	/*
	 * @return land/plot cost
	 */
	public int getPlotCost() {
		return plotCost;
	}

	/*
	 * @return mandatoryPurchaseCost
	 */
	public int getMandatoryPurchaseCost() {
		return mandatoryPurchaseCost;
	}

	/*
	 * @return entrance cost
	 */
	public int getEntranceCost() {
		return entranceCost;
	}

	/*
	 * @return main building cost
	 */
	public int getMainBuildingCost() {
		return mainBuildingCost;
	}

	/*
	 * @return main building daily cost
	 */
	public int getOnlyMainBuildingDailyCost() {
		return onlyMainBuildingDailyCost;
	}

	/*
	 * @return list of upgrades of hotel
	 */
	public ArrayList<Upgrade> getUpgrades() {
		return upgrades;
	}
	
	/*
	 * @param new upgrade
	 */
	public void addUpgrade(Upgrade up) {
		if(upgrades == null) {
			upgrades = new ArrayList<Upgrade>();
			maxUpgrade = up.getID();
			maxUpgradeDailyCost = up.getDaily();
		}
			if(up.getDaily() > maxUpgradeDailyCost && maxUpgrade!=0) {
				maxUpgradeDailyCost = up.getDaily();
				maxUpgrade = up.getID();
			}
		upgrades.add(up);
	}

	/*
	 * @@return exterior cost
	 */
	public int getExteriorBuildCost() {
		return exteriorBuildCost;
	}

	/*
	 * @return exterior daily cost
	 */
	public int getExteriorDailyCost() {
		return exteriorDailyCost;
	}
	
	/*
	 * @return owner of hotel
	 */
	public HotelPlayer getOwner() {
		return owner;
	}
	
	/*
	 * @param owner of hotel
	 */
	public void setOwner(HotelPlayer p) {
		this.owner = p;
		if(entrances!=null) {
			for(HotelBoardBox b : entrances) {
				b.updateEntranceColor();
			}
		}
	}
	
	
	int getBuildStatus() {
		return buildStatus;
	}
	
	void setBuildStatus(int i) {
		System.out.println("HotelCard.java: Allakse to build status tou " + this.name + " se " + i);
		this.buildStatus = i;
		if (i==1) maxUpgradeDailyCost = onlyMainBuildingDailyCost;
	}
	
	/*
	 * @return entrances of hotel
	 */
	public ArrayList<HotelBoardBox> getEntrances(){
		return entrances;
	}
	
	/*
	 * @param box of new entrance
	 */
	public void addEntrance(HotelBoardBox b) {
		System.out.println("HotelCard.java: Prosthetw stis eisodous mou to kouti x = "+b._getX() + " y = "+b._getY());
		if(entrances==null) entrances = new ArrayList<HotelBoardBox>();
		entrances.add(b);
	}
	
	/*
	 * @param exterior status
	 */
	public void setExteriorStatus(int s) {
		this.exteriorStatus = s;
		if(s==1) {
			maxUpgrade = 0;		// if maxUpgrade == 0 means that exterior building is the max upgrade
			maxUpgradeDailyCost = exteriorDailyCost;
		}
	}
	
	/*
	 * @return exterior status
	 */
	public int getExteriorStatus() {
		return exteriorStatus;
	}

	/*
	 * @return max upgrade
	 */
	public int getMaxUpgrade() {
		return maxUpgrade;
	}

	/*
	 * @return max upgrade daily cost
	 */
	public int getMaxUpgradeDailyCost() {
		return maxUpgradeDailyCost;
	}
	
	public void hotelCardDialogBox() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setResizable(true);
		alert.setTitle("Hotel Box Information");
		alert.setGraphic(null);
		alert.setHeaderText(name + " Hotel");
		GridPane info = new GridPane();
		info.setHgap(10); //horizontal gap in pixels => that's what you are asking for
		info.setVgap(10); //vertical gap in pixels
		info.setPadding(new Insets(10, 10, 10, 10));
		info.add(new Text("Name: " + name), 0, 0);
		info.add(new Text("oikopedou: " + plotCost+" MLS"), 0, 1);
		info.add(new Text("Upoxrewtiko : " + mandatoryPurchaseCost+" MLS"), 1, 1);
		info.add(new Text("eisodou : " + entranceCost+" MLS"), 0, 2);
		info.add(new Text("main ktiriou : " + mainBuildingCost+" MLS"), 0, 3);
		info.add(new Text("Daily : " + onlyMainBuildingDailyCost+" MLS"), 1, 3);
		int j = 4;
		for (Upgrade tmp : upgrades) {
			info.add(new Text("Upgrade " + tmp.getID()+" : " + tmp.getPurchase()+" MLS"), 0, j);
			info.add(new Text("Daily upgrade "+tmp.getID()+" : " + tmp.getDaily()+" MLS"), 1, j);
			j++;
		}
		if (exteriorBuildCost != -1) {
			info.add(new Text("Ekswterikos xwros: " + exteriorBuildCost+" MLS"), 0, j);
			info.add(new Text("Daily ekswterikos: " + exteriorDailyCost+" MLS"), 1, j);
		}
		alert.getDialogPane().setContent(info);
		alert.showAndWait();

	}
	
	public void hotelCardStatistics() {
		System.out.println("HotelCard.java: HotelCardStatistics");
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setResizable(true);
		alert.setTitle("Hotel Card Statistc");
		alert.setGraphic(null);
		alert.setHeaderText(name + " Hotel");
		GridPane info = new GridPane();
		info.setHgap(10); //horizontal gap in pixels => that's what you are asking for
		info.setVgap(10); //vertical gap in pixels
		info.setPadding(new Insets(10, 10, 10, 10));
		info.add(new Text("Name: " + name), 0, 0);
		
		if(owner!=null)
			info.add(new Text("Owner: " + owner.getName()), 0, 1);
		else 
			info.add(new Text("Owner: None"), 0, 1);
		System.out.println("HotelCard.java: Owner ok");
		if(exteriorBuildCost!=-1)
			info.add(new Text("Max : "+exteriorBuildCost+" MLS"), 0, 2);
		else 
			info.add(new Text("Max : "+upgrades.get(upgrades.size()-1).getID()), 0, 2);
		System.out.println("HotelCard.java: upgrade ok");
		
		if(buildStatus==0)
			info.add(new Text("Current : No Building Yet"), 0, 3);
		else {
			if(maxUpgrade==-1) {
				info.add(new Text("Current : Main"), 0, 3);
			}
			else {
				info.add(new Text("Current : " + getUpgradeById(maxUpgrade).getPurchase()), 0, 3);
			}
		}
		System.out.println("HotelCard.java: current ok");
		
		
		alert.getDialogPane().setContent(info);
		alert.showAndWait();

	}
	

	Upgrade getUpgradeById(int id) {
		for (Upgrade u:upgrades) {
			if(u.getID()==id) {
				return u;
			}
		}
		
		return null;
	}
}
