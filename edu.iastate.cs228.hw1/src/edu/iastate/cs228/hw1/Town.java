package edu.iastate.cs228.hw1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


/**
 *  @author Aden Koziol
 *
 */
public class Town {

	private int length, width;
	public TownCell[][] grid;

	/**
	 * Constructor to be used when user wants to generate grid randomly, with the given seed.
	 * This constructor does not populate each cell of the grid (but should assign a 2D array to it).
	 * @param length
	 * @param width
	 */
	public Town(int length, int width) {
		this.length = length;
		this.width = width;
		grid = new TownCell[length][width];
	}

	/**
	 * Constructor to be used when user wants to populate grid based on a file.
	 * Please see that it simple throws FileNotFoundException exception instead of catching it.
	 * Ensure that you close any resources (like file or scanner) which is opened in this function.
	 * @param inputFileName
	 * @throws FileNotFoundException
	 */
	public Town(String inputFileName) throws FileNotFoundException {
		File file = new File(inputFileName);
		Scanner scan = new Scanner(file);
		int length = scan.nextInt();
		int width = scan.nextInt();
		this.length = length;
		this.width = width;
		grid = new TownCell[length][width];
		scan.nextLine();



		for (int i = 0; i < length; i++) {
			String[] arr = scan.nextLine().split(" ");
			for (int j = 0; j < arr.length; j++) {
				switch (arr[j]) {
					case "R":
						grid[i][j] = new Reseller(this,i,j);
						break;
					case "E":
						grid[i][j] = new Empty(this,i,j);
						break;
					case "C":
						grid[i][j] = new Casual(this,i,j);
						break;
					case "O":
						grid[i][j] = new Outage(this,i,j);
						break;
					case "S":
						grid[i][j] = new Streamer(this,i,j);
						break;
					case "R\t":
						grid[i][j] = new Reseller(this,i,j);
						break;
					case "E\t":
						grid[i][j] = new Empty(this,i,j);
						break;
					case "C\t":
						grid[i][j] = new Casual(this,i,j);
						break;
					case "O\t":
						grid[i][j] = new Outage(this,i,j);
						break;
					case "S\t":
						grid[i][j] = new Streamer(this,i,j);
						break;
					default:
						break;
				}
			}
		}
		scan.close();
	}

	/**
	 * Returns width of the grid.
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns length of the grid.
	 * @return
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Initialize the grid by randomly assigning cell with one of the following class object:
	 * Casual, Empty, Outage, Reseller OR Streamer
	 */
	public void randomInit(int seed) {
		Random rand = new Random(seed);
		for (int i = 0; i < length; i++)
		{
			for (int j = 0; j < width; j++)
			{
				int num = rand.nextInt(5);
				if (num == TownCell.RESELLER)
					grid[i][j] = new Reseller(this, i, j);
				if (num == TownCell.EMPTY)
					grid[i][j] = new Empty(this, i, j);
				if (num == TownCell.CASUAL)
					grid[i][j] = new Casual(this, i, j);
				if (num == TownCell.OUTAGE)
					grid[i][j] = new Outage(this, i, j);
				if (num == TownCell.STREAMER)
					grid[i][j] = new Streamer(this, i, j);
			}
		}
	}

	/**
	 * Output the town grid. For each square, output the first letter of the cell type.
	 * Each letter should be separated either by a single space or a tab.
	 * And each row should be in a new line. There should not be any extra line between
	 * the rows.
	 */
	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < getLength(); i++)
		{
			s += "\n";
			for (int j = 0; j < getWidth(); j++)
			{
				if (grid[i][j].who().equals(State.CASUAL))
					s += "C ";
				if (grid[i][j].who().equals(State.EMPTY))
					s += "E ";
				if (grid[i][j].who().equals(State.RESELLER))
					s += "R ";
				if (grid[i][j].who().equals(State.OUTAGE))
					s += "O ";
				if (grid[i][j].who().equals(State.STREAMER))
					s += "S ";
			}
		}
		return s;
	}
}