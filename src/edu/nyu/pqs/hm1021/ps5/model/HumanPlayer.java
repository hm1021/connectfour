package edu.nyu.pqs.hm1021.ps5.model;

import java.awt.Color;

/**
 * This class implements Player. Object of this class can be created by calling
 * PlayerFactory.getInstance(PlayerType, Color). This is an example of static
 * factory method pattern. This class creates an object of PlayerType HUMAN.
 * 
 * @author hiral
 * 
 */
public class HumanPlayer implements Player {

	private PlayerType type;
	private Color c;

	/**
	 * This constructor is called by the PlayerFactory getInstance method to get
	 * the instance of HumanPlayer.
	 * 
	 * @param c
	 *            Color for the player
	 */
	protected HumanPlayer(Color c) {
		this.type = PlayerType.HUMAN;
		this.c = c;
	}

	/**
	 * Gives the type of the player. In this case it will return
	 * PlayerType.HUMAN
	 * 
	 * @return type of the player
	 */
	@Override
	public PlayerType getType() {
		return type;
	}

	/**
	 * This method accepts an instance of IConnectFour and a column. Being the
	 * human player, it sends the column to IConnectFour to drop the coin in the
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
	@Override
	public Coordinates makeMove(IConnectFour logic, int column) {
		if (logic == null)
			throw new IllegalArgumentException(
					"IConnectFour object can not be null");

		return new Coordinates(logic.dropCoin(column, c), column);
	}

	/**
	 * returns the color allocated to this player
	 * 
	 * @return Color allocated to this player for the game
	 */
	@Override
	public Color getColor() {
		return c;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "HumanPlayer [type=" + type + ", color=" + c + "]";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((c == null) ? 0 : c.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (!(obj instanceof HumanPlayer)) {
			return false;
		}
		HumanPlayer other = (HumanPlayer) obj;
		if (c != other.c) {
			return false;
		}
		if (type != other.type) {
			return false;
		}
		return true;
	}

}
