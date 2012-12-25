package edu.nyu.pqs.hm1021.ps5.model;

import java.awt.Color;

public interface Player {

	/**
	 * Gives the type of the player. In this case it will return
	 * PlayerType.COMPUTER
	 * 
	 * @return type of the player
	 */
	public PlayerType getType();

	/**
	 * This method accepts an instance of IConnectFour and a column. Being the
	 * player, it sends the column to IConnectFour to drop the coin in the
	 * column. If no more rows exist then it just returns -1 in the row field of
	 * the Coordinate.
	 * 
	 * @param IConnectFour
	 *            instance to check for logic
	 * @param column
	 *            The column selected by Human player to play
	 * @return Coordinates of the spot where the coin actually drops
	 * @throws IllegalArgumentException
	 *             If the IConnectFour object is not initialized and null
	 */
	public Coordinates makeMove(IConnectFour logic, int column);

	/**
	 * returns the color allocated to this player
	 * 
	 * @return Color allocated to this player for the game
	 */
	public Color getColor();

}
