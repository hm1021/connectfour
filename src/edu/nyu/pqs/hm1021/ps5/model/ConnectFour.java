package edu.nyu.pqs.hm1021.ps5.model;

import java.awt.Color;

/**
 * This class implements IConnectFour interface. All the game related logic is
 * contained in this class. This class also implements Singleton Pattern i.e.
 * only one instance of this class gets created and distributed.
 * 
 * @author hiral
 * 
 */
public class ConnectFour implements IConnectFour {

	private static final IConnectFour instance = new ConnectFour();
	private Board grid;

	private ConnectFour() {
	}

	/**
	 * Returns the instance of the singleton object.
	 * 
	 * @return Instance of ConnectFour object
	 */
	public static IConnectFour getInstance() {
		return instance;
	}

	private void initialize(int row, int column) {
		grid = new Board.Builder().setRows(row).setColumns(column).build();
	}

	/**
	 * Controller class calls this method to inform the model about start of the
	 * game. Accepts rows and columns to decide size of the grid.
	 * 
	 * @param row
	 *            number of rows in the grid
	 * @param column
	 *            number of columns in the grid
	 * @throws IllegalArgumentException
	 *             if the row or column is less than -1
	 */
	@Override
	public boolean startGame(int row, int column) {
		if (row < 0 || column < 0)
			throw new IllegalArgumentException(
					"Row or column can not be negative.");

		initialize(row, column);
		return true;
	}

	/**
	 * To reset the game.
	 * 
	 * @return true if reset without any issues
	 */
	@Override
	public boolean resetGame() {
		return grid.resetGrid();
	}

	/**
	 * Takes as an argument column number and Color. Finds out a row to place
	 * the coin in that particular column.
	 * 
	 * @param column
	 *            The column to add the coin to
	 * @param c
	 *            Color that has been allocated to that particular player
	 * @return returns the row number if able to drop the coin in the column
	 *         else returns -1
	 * @throws IllegalArgumentException
	 *             If the column number is less than 0 or exceeding size of the
	 *             grid <br>
	 *             If the color passed is null
	 */
	@Override
	public int dropCoin(int column, Color c) {
		if (c == null)
			throw new IllegalArgumentException("Color can not be null");

		if (column < 0)
			throw new IllegalArgumentException(
					"Column number exceeding grid size");

		int row = getEmptyRowForColumn(column, c);
		if (row != -1) {
			grid.setPlayerType(row, column, c);
			return row;
		} else
			return -1;
	}

	/**
	 * This is a method for Computer Player implementation. This method acts
	 * like drop coin, wherein it does not actually place the coin in the
	 * particular column. It just find out if there exists a row for the column.
	 * 
	 * @param column
	 *            The column to add the coin to
	 * @param c
	 *            Color that has been allocated to that particular player
	 * @return returns the row number if able to drop the coin in the column
	 *         else returns -1
	 * @throws IllegalArgumentException
	 *             If the column number is less than 0 or exceeding size of the
	 *             grid <br>
	 *             If the color passed is null
	 */
	@Override
	public int getEmptyRowForColumn(int column, Color c) {
		if (c == null)
			throw new IllegalArgumentException("Color can not be null");

		if (column < 0 || column > grid.getColumn())
			throw new IllegalArgumentException(
					"Column number not within grid size");

		int row = grid.getEmptyRowForColumn(column);
		if (row == -1)
			return -1;
		else
			return row;
	}

	/**
	 * This method checks if some player has won. It takes a coordinate from the
	 * controller and checks row-wise, column-wise and both diagonal-wise to see
	 * if there are four boxes with same color.
	 * 
	 * @param row
	 *            row from where the check should start
	 * @param col
	 *            column from where the check should start
	 * @return true if someone has won else false
	 * @throws IllegalArgumentException
	 *             If the column number is less than 0 or exceeding size of the
	 *             grid <br>
	 *             If the row number is less than 0 or exceeding size of the
	 *             grid
	 */
	@Override
	public boolean checkIfWin(int row, int col) {

		if (row < 0 || col < 0 || row > grid.getRow() || col > grid.getColumn())
			throw new IllegalArgumentException(
					"Row or column not within grid size.");

		int backwardDiagnal = checkBackwarddDiagonal(row, col);
		int forwardDiagnal = checkForwardDiagonal(row, col);
		int columnwise = checkColumn(row, col);
		int rowwise = checkRow(row, col);

		if (backwardDiagnal >= 3)
			return true;
		else if (forwardDiagnal >= 3)
			return true;
		else if (columnwise >= 3)
			return true;
		else if (rowwise >= 3)
			return true;

		return false;
	}

	private int checkColumn(int row, int col) {
		/*
		 * Logic: Get the color at current (row,column). Go to the right until a
		 * different color or null is encountered. Do same on left side. Sum up
		 * the count of right and left.
		 */
		int down = 0;
		int up = 0;

		Color c = grid.getColor(row, col);

		int i = row + 1;
		while (i < grid.getRow() && c == grid.getColor(i, col)) {
			down++;
			i++;
		}

		int j = row - 1;
		while (j >= 0 && c == grid.getColor(j, col)) {
			up++;
			j--;
		}

		return down + up;
	}

	private int checkRow(int row, int col) {
		/*
		 * Logic: Get the color at current (row,column). Go to the up until a
		 * different color or null is encountered. Do same on down. Sum up the
		 * count of up and down.
		 */
		int right = 0;
		int left = 0;

		Color c = grid.getColor(row, col);

		int i = col + 1;
		while (i < grid.getColumn() && c == grid.getColor(row, i)) {
			left++;
			i++;
		}

		int j = col - 1;
		while (j >= 0 && c == grid.getColor(row, j)) {
			right++;
			j--;
		}

		return right + left;
	}

	private int checkForwardDiagonal(int row, int col) {
		/*
		 * Logic: Get the color at current (row,column). Go right up in the
		 * diagonal until a different color or null is encountered. Do same on
		 * left down side of the diagonal. Sum up the count of rightup and
		 * leftdown.
		 */
		int rightup = 0;
		int leftdown = 0;

		Color c = grid.getColor(row, col);

		int i = row + 1;
		int j = col - 1;
		while (i < grid.getRow() && j >= 0 && c == grid.getColor(i, j)) {
			i++;
			j--;
			rightup++;
		}

		int m = row - 1;
		int n = col + 1;
		while (m >= 0 && n < grid.getColumn() && c == grid.getColor(m, n)) {
			n++;
			m--;
			leftdown++;
		}

		return rightup + leftdown;
	}

	private int checkBackwarddDiagonal(int row, int col) {
		/*
		 * Logic: Get the color at current (row,column). Go left up in the
		 * diagonal until a different color or null is encountered. Do same on
		 * right down side of the diagonal. Sum up the count of right down and
		 * leftup.
		 */
		int rightdown = 0;
		int leftup = 0;

		Color c = grid.getColor(row, col);

		int i = row + 1;
		int j = col + 1;
		while (i < grid.getRow() && j < grid.getColumn()
				&& c == grid.getColor(i, j)) {
			i++;
			j++;
			rightdown++;
		}

		int m = row - 1;
		int n = col - 1;
		while (m >= 0 && n >= 0 && c == grid.getColor(m, n)) {
			n--;
			m--;
			leftup++;
		}

		return rightdown + leftup;
	}

	/**
	 * This method checks if the board is completely filled.
	 * 
	 * @return true if the board is full else false
	 */
	@Override
	public boolean checkIfBoardFull() {
		return grid.checkIfFull();
	}

	/**
	 * This method sets the color of (row,column) in the grid
	 * 
	 * @param row
	 *            row to color
	 * @param col
	 *            column to color
	 * @param c
	 *            Color
	 * @return returns true if the model successfully sets the color at
	 *         (row,column)
	 * @throws IllegalArgumentException
	 *             If the column number is less than 0 or exceeding size of the
	 *             grid <br>
	 *             If the row number is less than 0 or exceeding size of the
	 *             grid <br>
	 */
	@Override
	public boolean setColor(int row, int col, Color c) {

		if (row > grid.getRow() || row < 0 || col > grid.getColumn() || col < 0)
			throw new IllegalArgumentException(
					"Row or column not within grid size.");

		grid.setColor(row, col, c);
		return true;
	}

	/**
	 * returns number of rows in the grid
	 * 
	 * @return number of rows
	 */
	@Override
	public int getRows() {
		return grid.getRow();
	}

	/**
	 * returns number of columns in the grid
	 * 
	 * @return number of columns
	 */
	@Override
	public int getCols() {
		return grid.getColumn();
	}

}
