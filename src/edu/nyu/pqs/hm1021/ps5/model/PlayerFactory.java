package edu.nyu.pqs.hm1021.ps5.model;

import java.awt.Color;

/**
 * This class implements Static Factory Pattern to get instance of a player.
 * There are two different types of Players possible namely HUMAN and COMPUTER.
 * So based on the type in the input, this class gets the instance of that
 * particular PlayerType.
 * 
 * @author hiral
 * 
 */
public class PlayerFactory {

	/**
	 * Returns Player with the specified PlayerType and Color.
	 * 
	 * @param type
	 *            PlayerType -- HUMAN or COMPUTER
	 * @param c
	 *            Color
	 * @return Player
	 * @throws IllegalArgumentException
	 *             if the type passed is null or the color passed is null
	 */
	public static Player getPlayer(PlayerType type, Color c) {
		if (type == null)
			throw new IllegalArgumentException(
					"PlayerType can not be negative.");

		if (c == null)
			throw new IllegalArgumentException("Color can not be negative.");

		if (type == PlayerType.HUMAN) {
			return new HumanPlayer(c);
		} else {
			return new ComputerPlayer(c);
		}
	}
}
