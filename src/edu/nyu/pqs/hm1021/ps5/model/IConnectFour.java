package edu.nyu.pqs.hm1021.ps5.model;

import java.awt.Color;

/**
 * This interface acts as the Model of the Model View Controller pattern. A
 * class can implement this interface and coordinate with IController and
 * IObserver to implement Connect Four game. This class also implements
 * Singleton Pattern i.e. only one instance of this class gets created and
 * distributed.
 * 
 * @author hiral
 * 
 */
public interface IConnectFour {

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
	public boolean startGame(int row, int column);

	/**
	 * To reset the game.
	 * 
	 * @return true if reset without any issues
	 */
	public boolean resetGame();

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
	public int dropCoin(int column, Color c);

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
	public int getEmptyRowForColumn(int column, Color c);

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
	 *             If the color passed is null
	 */
	public boolean setColor(int row, int col, Color c);

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
	public boolean checkIfWin(int row, int col);

	/**
	 * This method checks if the board is completely filled.
	 * 
	 * @return true if the board is full else false
	 */
	public boolean checkIfBoardFull();

	/**
	 * returns number of rows in the grid
	 * 
	 * @return number of rows
	 */
	public int getRows();

	/**
	 * returns number of columns in the grid
	 * 
	 * @return number of columns
	 */
	public int getCols();
}
