package edu.nyu.pqs.hm1021.ps5.view;

import java.awt.Color;

/**
 * This class implements static method pattern. This class is used to get player
 * with the given color.
 * 
 * @author hiral
 * 
 */
public class ExtractPlayer {

	public static String getPlayer(Color c) {
		if (c == null)
			throw new IllegalArgumentException("Color can not be null.");

		if (c == Color.RED) {
			return "Player 1";
		} else if (c == Color.YELLOW) {
			return "Player 2";
		}

		return null;
	}
}