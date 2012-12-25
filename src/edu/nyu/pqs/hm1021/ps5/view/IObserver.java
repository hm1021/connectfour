package edu.nyu.pqs.hm1021.ps5.view;

import java.awt.Color;

import edu.nyu.pqs.hm1021.ps5.model.Player;

/**
 * This interface acts as the View of Model View Controller pattern. A class can
 * implement this interface and coordinate with IConnectFour and IConnectFour to
 * implement Connect Four game.
 * 
 * @author hiral
 * 
 */
public interface IObserver {

	/**
	 * This method is called by the controller when game starts. This code is
	 * supposed to display the start of the game even on the display.
	 * 
	 * @param row
	 *            dimensions of the grid
	 * @param column
	 *            dimensions of the grid
	 * @throws IllegalArgumentException
	 *             if the row or columns is less than 0.
	 */
	public void gameStarted(int row, int column);

	/**
	 * This method is called by the controller when a player wins. This method
	 * displays the winner and also resets the game.
	 * 
	 * @param player
	 *            winner of the game of type Player
	 * @throws IllegalArgumentException
	 *             if the Player object is null
	 */
	public void win(Player player);

	/**
	 * This method is called by the controller when a column and a row are
	 * selected to drop the coin. This class internally calls another method to
	 * show on UI which column and row got selected for the move.
	 * 
	 * @param row
	 *            row to drop the coin
	 * @param column
	 *            column to drop the coin in
	 * @param c
	 *            Color of the player
	 * @throws IllegalArgumentException
	 *             if the row or column is less than 0 or exceeds grid size. <br>
	 *             or if color passed is null.
	 */
	public void columnSelected(int row, int column, Color c);

	/**
	 * This method is called by the controller to inform of game reset event. It
	 * resets the whole grid.
	 */
	public void gameReset();

	/**
	 * This method is called by the controller to inform the observers that
	 * either Single Player or Double Player have been selected by the user so
	 * game can be started.
	 */
	public void selectPlayerTypes();

	/**
	 * This method is called by the controller to inform of game end event. This
	 * method makes a call to exit the game.
	 */
	public void gameEnded();

	/**
	 * Sets the number of rows for the grid.
	 * @param row number of rows
	 */
	public void setRow(int row);

	/**
	 * Sets the number of columns for the grid
	 * @param col columns
	 */
	public void setColumn(int col);

}
