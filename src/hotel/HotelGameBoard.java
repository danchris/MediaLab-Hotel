package hotel;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.util.Pair;

public class HotelGameBoard extends Pane{
	
	private static HotelBoardBox[][] gridBoard = new HotelBoardBox[12][15];
	private static HotelBoardBox startBox = null;
	private static ArrayList<HotelBoardBox> visited;

	public HotelGameBoard(String[][] file) throws IOException {
		this.setPrefSize(600, 600);
		HotelBoardBox a;

		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 15; j++) {
				a = new HotelBoardBox(file[i][j], i, j);
				if (file[i][j].equals("S"))
					startBox = a;
				gridBoard[i][j] = a;
				this.getChildren().add(a);
			}
		}
		
		findPath();
	}
	
	private void findPath() {
		int x;
		int y;
		Pair<Integer,Integer> next;
		HotelBoardBox curr = startBox;
		visited = new ArrayList<HotelBoardBox>();

		do {
			System.out.println("HotelBoards.java: Do while");
			x = curr._getX();
			y = curr._getY();
			curr.setArrow();
			visited.add(curr);
			System.out.println(curr.getID());
			next = findNextBox(x,y);
			
			if(next != null) {
				if(next.getValue() > y) curr.rotateImageView(2);
				else if (next.getKey() > x) curr.rotateImageView(3);
				else if (next.getKey() < x) curr.rotateImageView(1);
			}
			else {
				System.out.println("HotelBoards.java: Return null find next Box");
				return ;
			}
			curr = gridBoard[next.getKey()][next.getValue()];
		} while (curr != startBox);

	}

	// Returns a pair of integers of next box of path
	public Pair<Integer, Integer> findNextBox(int x, int y) {

		System.out.println("HotelBoards.java: FindNExtBox eimai o " + x + " " + y);
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

}
