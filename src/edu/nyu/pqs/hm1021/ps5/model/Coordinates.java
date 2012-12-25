package edu.nyu.pqs.hm1021.ps5.model;

/**
 * This class is used to store coordinates (row,column) for a particular spot in
 * the grid. This class is immutable.
 * 
 * @author hiral
 * 
 */
public class Coordinates {

	private int row;
	private int col;

	/**
	 * Creates Coordinate for the give row and column
	 * @param r row
	 * @param c column
	 */
	public Coordinates(int r, int c) {
		row = r;
		col = c;
	}

	/**
	 * Returns the row of the Coordinate
	 * @return row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Returns the column of the Coordinate
	 * @return column
	 */
	public int getCol() {
		return col;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
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
		if (!(obj instanceof Coordinates)) {
			return false;
		}
		Coordinates other = (Coordinates) obj;
		if (col != other.col) {
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
		return "Coordinates [row=" + row + ", col=" + col + "]";
	}
}
