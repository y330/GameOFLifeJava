/** GridModel is an abstract class that defines the basic grid structure for
 * the game.
 *
 * The grid is a 2D array of cells. Each cell has a state, which is either alive
 * or dead.
 *
 * The grid is a rectangular grid, with a fixed number of rows and columns. The
 * number of rows and columns are specified in the constructor. The grid is /* *
 * initialized to all dead cells. The grid is not intended to be used directly,
 * but rather through a subclass.
 *
 *
 * @author YonahAviv
 * @filename: GridModel.java
 *
 * Created at : 2021-11-18 12:00:42
 * Last modified : 2021-11-18 12:07:00
 */
class GridModel {

	/*
	 * GridModel is an abstract class that defines the basic grid structure for the
	 * game.
	 *
	 * The grid is a 2D array of cells. Each cell has a state, which is either alive
	 * or dead.
	 *
	 * The grid is a rectangular grid, with a fixed number of rows and columns. The
	 * number of rows and columns are specified in the constructor. The grid is /* *
	 * initialized to all dead cells. The grid is not intended to be used directly,
	 * but rather through a subclass.
	 */

	private int width, height, gen;
	private boolean[][] grid;

	public GridModel(int w, int h) {
		this.width = w;
		this.height = h;
		this.gen = 1;
		this.grid = new boolean[width][height];
	}

	// overload if user wants to use a starting config
	public GridModel(boolean[][] startingGrid) {
		this.width = startingGrid[0].length;
		this.height = startingGrid.length;
		this.gen = 1;
		this.grid = startingGrid;
	}

	public void randomPopulation(double fill) {
		/*
		 * Fills a grid with random cells.
		 *
		 * Args: fill: The percentage of the grid that should be filled with cells.
		 */

		for (int row = 0; row < width; row++) {
			for (int col = 0; col < height; col++) {
				/* fill with true/false */
				grid[row][col] = Math.random() < fill;
			}
		}
		System.out.println("GridModel: Call to randomPopulation(" + fill + ")");

	}

	public void newGeneration() {
		boolean[][] newgrid = new boolean[width][height];
		for (int row = 0; row < width; row++) {
			for (int col = 0; col < height; col++) {
				// get the number of neighbors
				int neighbours = getNumberOfNeighbours(row, col);

				// apply rules
				/**
				 * rules:
				 *
				 * 1. if 1 or 0 neighbours, cell dies
				 *
				 * 2. if 2 neighbours, cell stays the same
				 *
				 * 3. if 3 neighbours, cell lives
				 *
				 * 4. if 4 or more neighbours, cell dies
				 */
				switch (neighbours) {
				case 0, 1 -> newgrid[row][col] = false;
				case 2 -> newgrid[row][col] = grid[row][col];
				case 3 -> newgrid[row][col] = true;
				case 4, 5, 6, 7, 8 -> newgrid[row][col] = false;
				default -> System.out.println("GridModel: newGeneration: Error");
				}
				if (newgrid[row][col] && (row <= 0 || row >= width - 1 || col <= 0 || col >= height - 1)) {
					int[] possibleEdgeCase = edgeTeleport(row, col);
					int x2 = possibleEdgeCase[0];
					int y2 = possibleEdgeCase[1];
					newgrid[x2][y2] = true;
					newgrid[row][col] = false;
				}
			}

		}

		// System.out.println("GridModel: Call #" + gen + " to newGeneration()");
		this.grid = newgrid;
		this.gen++;
	}

	public void setCell(int row, int col, boolean b) {

		if (row >= 0 && row < width && col >= 0 && col < height) {
			/*  */
			this.grid[row][col] = b;
		}

		// System.out.println("GridModel: Call to setCell(" + row + ", " + col + ", "
		// + b + ")");
	}

	public boolean getCell(int row, int col) {

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (x == row && y == col) {
					return grid[x][y];
				}
			}

		}
		// System.out.println('GridModel: Call to getCell(' + row + "," + col + ")");

		return (row + col) % 2 == 0;
	}

	public int getWidth() {
		// System.out.println("GridModel: Call to getWidth()");
		return width;
	}

	// set
	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		// System.out.println("GridModel: Call to getHeight()");
		return height;
	}

	// set
	public void setHeight(int height) {
		this.height = height;
	}

	public int getGen() {
		// System.out.println("GridModel: Call to getGen()");
		return gen;
	}

	public int getNumberOfNeighbours(int row, int col) {
		return getNumberOfNeighboursHelper(row, col);
	}

	public boolean[][] getGrid() {
		return grid;
	}

	public void setGrid(boolean[][] grid) {
		this.grid = grid;
	}

	/*
	 * takes param starting config name and converts the int[][] matching the string
	 * to a boolean[][]
	 */

	// --------------------------------------------------------------------------------
	// helpers
	// --------------------------------------------------------------------------------

	private int getNumberOfNeighboursHelper(int row, int col) {

		/* This is a helper method to get the number of neighbours */
		int neighbours = 0;
		// include left and right edge columns
		for (int x = row - 1; x <= row + 1; x++) {
			for (int y = col - 1; y <= col + 1; y++) {
				// if reached edge teleport to opposite side

				if (x == row && y == col) {
					continue;
				}
				if (x <= 0 || x >= width - 1 || y <= 0 || y >= height - 1) {

					continue;
				}

				if (grid[x][y]) {
					neighbours++;
				}
			}
		}
		// System.out.println("GridModel: Call to getNumberOfNeighbours(" + row + ","
		// + col + ")");
		return neighbours;

	}

	// if reached edge teleport to opposite side
	private int[] edgeTeleport(int row, int col) {
		if (row == 0) {
			row = width - 1;
		}
		if (row >= width - 1) {
			row = 1;
		}
		if (col == 0) {
			col = height - 1;
		}
		if (col >= height - 1) {
			col = 1;
		} else {
			return new int[] { row, col };

		}
		return new int[] { row, col };
	}

}
