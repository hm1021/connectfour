package edu.nyu.pqs.hm1021.ps5.model;

import java.awt.Color;

/**
 * This class implements Player. Object of this class can be created by calling
 * PlayerFactory.getInstance(PlayerType, Color). This is an example of static
 * factory method pattern. This class creates an object of PlayerType COMPUTER.
 * 
 * @author hiral
 * 
 */
public class ComputerPlayer implements Player {

	private PlayerType type;
	private Color c;
	private Coordinates coors_forTestPurposes;

	/**
	 * This constructor is called by the PlayerFactory getInstance method to get
	 * the instance of ComputerPlayer.
	 * 
	 * @param c
	 *            Color for the player
	 */
	protected ComputerPlayer(Color c) {
		type = PlayerType.COMPUTER;
		this.c = c;
	}

	/**
	 * Gives the type of the player. In this case it will return
	 * PlayerType.COMPUTER
	 * 
	 * @return type of the player
	 */
	@Override
	public PlayerType getType() {
		return type;
	}

	/**
	 * This method accepts an instance of IConnectFour and a column. Being the
	 * computer player, it uses a very basic AI to decide the move. It looks
	 * ahead one move and makes that move if it leads to winning the game. IF no
	 * such move exists then it just returns a random empty spot to play.
	 * 
	 * @param IConnectFour
	 *            instance to check for logic
	 * @param column
	 * @return Coordinates of the spot where the ComputerPlayer suggests to
	 *         place the coin
	 * @throws IllegalArgumentException
	 *             If the IConnectFour object is not initialized and null
	 */
	@Override
	public Coordinates makeMove(IConnectFour logic, int column) {

		if (logic == null)
			throw new IllegalArgumentException(
					"IConnectFour object can not be null");

		/*
		 * Logic: in the below for loop, for every column, it tries to drop the
		 * coin and checks if that move leads to winning, if it does then
		 * returns those coordinates else it unsets the color on previously set
		 * coordinate and checks for next column.
		 */
		column = logic.getCols();
		int row;
		for (int i = 0; i < column; i++) {
			row = logic.dropCoin(i, c);
			if (row != -1) {
				if (logic.checkIfWin(row, i)) {
					return new Coordinates(row, i);
				} else
					logic.setColor(row, i, null);
			}
		}

		/*
		 * As there does not exist any move that leads to winning, it simply
		 * finds a random column, and drops the coin if there exists an empty
		 * row.
		 */
		boolean bool = false;
		while (bool != true) {
			int i = (int) (Math.random() * (logic.getCols()));
			row = logic.dropCoin(i, c);
			if (row != -1) {
				bool = true;
				coors_forTestPurposes = new Coordinates(row, i);
				return new Coordinates(row, i);
			}
		}

		return new Coordinates(-1, column);
	}

	/**
	 * This method is just for test purposes to know the random number generated
	 * by the makeMove() function.
	 * 
	 * @return
	 */
	protected Coordinates getCoordinates() {
		return coors_forTestPurposes;
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
		return "ComputerPlayer [type=" + type + ", color=" + c + "]";
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
		if (!(obj instanceof ComputerPlayer)) {
			return false;
		}
		ComputerPlayer other = (ComputerPlayer) obj;
		if (c != other.c) {
			return false;
		}
		if (type != other.type) {
			return false;
		}
		return true;
	}

}
