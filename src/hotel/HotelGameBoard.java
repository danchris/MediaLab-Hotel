package hotel;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.util.Pair;

public class HotelGameBoard extends Pane{
	
	private static HotelBoardBox[][] gridBoard = new HotelBoardBox[12][15];
	private HotelBoardBox startBox = null;
	private static ArrayList<HotelBoardBox> visited;
	private ArrayList<HotelBoardBox> path;

	public HotelGameBoard(String[][] file) throws IOException {
		setPrefWidth(600);
		//setPrefSize(600,600);
		HotelBoardBox a;

		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 15; j++) {
				a = new HotelBoardBox(file[i][j], i, j);
				if (file[i][j].equals("S"))
					startBox = a;
				if(Character.isDigit(file[i][j].charAt(0))) a.setHotelCard(HotelFileReader.get(Integer.parseInt(file[i][j])));
				gridBoard[i][j] = a;
				this.getChildren().add(a);
			}
		}
		
		findPath();
	}
	
	private void findPath() {
		Pair<Integer,Integer> next;
		HotelBoardBox curr = startBox;
		visited = new ArrayList<HotelBoardBox>();
		path = new ArrayList<HotelBoardBox>();

		
		next = findNextBox(curr._getX(),curr._getY());
		if(next != null) {
			curr.setArrow();
			if (next.getValue() > curr._getY())
				curr.rotateImageView(curr.getImageView(),2);
			else if (next.getKey() > curr._getX())
				curr.rotateImageView(curr.getImageView(),3);
			else if (next.getKey() < curr._getX())
				curr.rotateImageView(curr.getImageView(),1);
			curr = gridBoard[next.getKey()][next.getValue()];
			startBox.setNext(curr);
			path.add(startBox);
		}
		else {
			System.out.println("HotelBoards.java: Return null find next Box");
			return ;
		}
		do {
			System.out.println("HotelGameBoard.java: Do while");
			path.add(curr);
			visited.add(curr);
			System.out.println(curr.getID());
			next = findNextBox(curr._getX(),curr._getY());
			if(curr.getID().equals("C")) 
				curr.setLabel("Hall");
			else if(curr.getID().equals("B")) 
				curr.setLabel("Bank");
			else if(curr.getID().equals("H"))
				curr.setLabel("$");
			else if(curr.getID().equals("E"))
				curr.setHammer();
			if(next.getKey()==10 && next.getValue()==12) {
				System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEE" + curr._getX() + curr._getY());
			}
			curr.setNext(gridBoard[next.getKey()][next.getValue()]);
			curr = gridBoard[next.getKey()][next.getValue()];
		} while (curr != startBox);

	}

	// Returns a pair of integers of next box of path
	public Pair<Integer, Integer> findNextBox(int x, int y) {

		System.out.println("HotelGameBoard.java: FindNExtBox eimai o " + x + " " + y);
		if (y + 1 <= 14 && gridBoard[x][y + 1].canGo() && !visited.contains(gridBoard[x][y+1]))
			return new Pair<Integer, Integer>(x, y + 1);
		else if (x + 1 <= 11 && gridBoard[x + 1][y].canGo() && !visited.contains(gridBoard[x+1][y]))
			return new Pair<Integer, Integer>(x + 1, y);
		else if (y - 1 >= 0 && gridBoard[x][y - 1].canGo() && !visited.contains(gridBoard[x][y-1]))
			return new Pair<Integer, Integer>(x, y - 1);
		else if (x - 1 >= 0 && gridBoard[x - 1][y].canGo() && !visited.contains(gridBoard[x-1][y]))
			return new Pair<Integer, Integer>(x - 1, y);

		return null;
	}
	
	public HotelBoardBox getStartBox() {
		return startBox;
	}
	
	public void printGridBoard() {
		for (HotelBoardBox[] tmpRow : gridBoard) {
			for (HotelBoardBox tmpCol : tmpRow) {
				tmpCol.printHotelBoardBox();
			}
			System.out.println("");
		}
	}
	
	public HotelBoardBox[][] getGridBoard() {
		return gridBoard;
	}
	
	public ArrayList<HotelBoardBox> getPath(){
		return path;
	}

}
