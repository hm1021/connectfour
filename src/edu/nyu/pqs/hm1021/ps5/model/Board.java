package edu.nyu.pqs.hm1021.ps5.model;

import java.awt.Color;
import java.util.Arrays;

/**
 * This class creates a board for the ConnectFour game as a double array. This
 * class implements Builder Pattern to set number of rows and columns.Rows and
 * columns are both required properties. The default values are 6 and 7
 * respectively.
 * 
 * @author hiral
 * 
 */
class Board {

	private int row, column;
	private Color[][] board;

	public static class Builder {
		private int row = 6;
		private int column = 7;

		public Builder setRows(int rows) {
			this.row = rows;
			return this;
		}

		public Builder setColumns(int cols) {
			this.column = cols;
			return this;
		}

		public Board build() {
			return new Board(this);
		}
	}

	private Board(Builder builder) {
		this.row = builder.row;
		this.column = builder.column;
		this.board = new Color[row][column];
	}

	/**
	 * This is a helper method used by the model to set the type of the player
	 * at a particular row and column.
	 * 
	 * @param row
	 *            row at which to set the color
	 * @param column
	 *            column at which to set the color
	 * @param c
	 *            Color to set at (row,column)
	 * @return true if the (row,column) spot is empty i.e. null else false
	 * @throws IllegalArgumentException
	 *             If the column number is less than 0 or exceeding size of the
	 *             grid <br>
	 *             If the color passed is null
	 */
	protected boolean setPlayerType(int row, int column, Color c) {
		if (row > this.row || row < 0 || column > this.column || column < 0)
			throw new IllegalArgumentException(
					"Row or column not within grid size.");

		if (c == null)
			throw new IllegalArgumentException("Color can not be null");

		if (board[row][column] != null) {
			return false;
		} else {
			board[row][column] = c;
			return true;
		}
	}

	/**
	 * This method is mainly for the purpose of setting some color. When a
	 * computer player tests for spots to win, it first sets the color. If the
	 * spot is not leading to a win, then it unsets the color using this method.
	 * 
	 * @param row
	 *            row at which to set the color
	 * @param column
	 *            column at which to set the color
	 * @param c
	 *            Color to set at (row,column)
	 * @throws IllegalArgumentException
	 *             If the column number is less than 0 or exceeding size of the
	 *             grid <br>
	 *             If the color passed is null
	 */
	protected void setColor(int row, int col, Color c) {
		if (row > this.row || row < 0 || col > this.column || col < 0)
			throw new IllegalArgumentException(
					"Row or column not within grid size.");

		board[row][col] = c;
	}

	/**
	 * This method retrieves the color at a particular (row,column) slot.
	 * 
	 * @param row
	 *            row number of the spot
	 * @param col
	 *            column number of the spot
	 * @return Color at the (row,column)
	 */
	protected Color getColor(int row, int col) {
		if (row > this.row || row < 0 || col > this.column || col < 0)
			throw new IllegalArgumentException(
					"Row or column not within grid size.");

		return board[row][col];
	}

	/**
	 * When a game is reset, all the slots in the grid are reset to null.
	 * 
	 * @return true if grid is reset successfully else false
	 */
	protected boolean resetGrid() {
		board = new Color[row][column];
		return true;
	}

	/**
	 * This is a method for Computer Player implementation. This method is
	 * called by model. It just find out if there exists a row for the column.
	 * 
	 * @param column
	 *            The column to add the coin to
	 * @return returns the row number if able to drop the coin in the column
	 *         else returns -1
	 * @throws IllegalArgumentException
	 *             If the column number is less than 0 or exceeding size of the
	 *             grid <br>
	 *             If the color passed is null
	 */
	protected int getEmptyRowForColumn(int column) {
		if (column > this.column || column < 0)
			throw new IllegalArgumentException("Column not within grid size.");

		int i = row - 1;
		while (i >= 0) {
			if (board[i][column] == null) {
				return i;
			}
			i--;
		}
		return -1;
	}

	protected boolean checkIfFull() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (board[i][j] == null)
					return false;
			}
		}
		return true;
	}

	/**
	 * returns number of rows in the grid
	 * 
	 * @return number of rows
	 */
	protected int getRow() {
		return row;
	}

	/**
	 * returns number of columns in the grid
	 * 
	 * @return number of columns
	 */
	protected int getColumn() {
		return column;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(board);
		result = prime * result + column;
		result = prime * result + row;
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Board)) {
			return false;
		}
		Board other = (Board) obj;
		if (!Arrays.deepEquals(board, other.board)) {
			return false;
		}
		if (column != other.column) {
			return false;
		}
		if (row != other.row) {
			return false;
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "Board [row=" + row + ", column=" + column + "]";
	}

}
