package edu.nyu.pqs.hm1021.ps5.controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.nyu.pqs.hm1021.ps5.model.ConnectFour;
import edu.nyu.pqs.hm1021.ps5.model.Coordinates;
import edu.nyu.pqs.hm1021.ps5.model.IConnectFour;
import edu.nyu.pqs.hm1021.ps5.model.Player;
import edu.nyu.pqs.hm1021.ps5.model.PlayerFactory;
import edu.nyu.pqs.hm1021.ps5.model.PlayerType;
import edu.nyu.pqs.hm1021.ps5.view.IObserver;

/**
 * This class implements IController interface. It acts as a bridge between
 * IConnectFour and IObserver. This class receives events from observer. With
 * every event received, a call to IConnectFour is made to check for logic and a
 * call to IObserver is made to reflect the event on the screens of all the
 * observers.
 * 
 * @author hiral
 * 
 */
public class Controller implements IController {

	private List<IObserver> observers;
	private IConnectFour model;
	private Player player1;
	private Player player2;
	private Player currentPlayer;
	private int row = 6;
	private int column = 7;

	/**
	 * This constructor is used by the observer to get an instance of Controller
	 * class.
	 */
	public Controller() {
		observers = new ArrayList<IObserver>();
		model = ConnectFour.getInstance();
	}

	/**
	 * This method is called by the IObserver when the user clicks the start
	 * button. This method is supposed to inform the model of start of the game
	 * and also call fireGameStarted() method to inform all the observers.
	 * 
	 * @param row
	 *            dimensions of the grid
	 * @param column
	 *            dimensions of the grid
	 * @throws IllegalArgumentException
	 *             if the row or column is less than 0
	 */
	@Override
	public void startGame(int row, int column) {
		if (row < 0 || column < 0)
			throw new IllegalArgumentException(
					"Row or column can not be negative.");

		currentPlayer = player1;
		model.startGame(row, column);
		fireGameStarted(row, column);
	}

	/**
	 * This method is called by the IObserver when the user clicks on a column
	 * button to make a move. This method is supposed to inform the model of
	 * column being selected event and also call fireColumnSelected() method to
	 * inform all the observers.
	 * 
	 * @param column
	 *            dimensions of the grid
	 * @throws IllegalArgumentException
	 *             if the column is less than 0 or exceeds size of the grid
	 */
	@Override
	public boolean columnSelected(int column) {
		if (model.checkIfBoardFull()) {
			gameReset();
			return false;
		}
		if (column < 0 || column > this.column)
			throw new IllegalArgumentException(
					"Column number exceeding grid size");

		if (currentPlayer.getType() == PlayerType.HUMAN) {
			columnSelectHelper(column);
			if (currentPlayer.getType() == PlayerType.COMPUTER) {
				columnSelectHelper(column);
			}
		}
		return false;
	}

	private void columnSelectHelper(int column) {
		int row = -1;
		Coordinates v;

		if (currentPlayer.getType() == PlayerType.HUMAN) {
			v = currentPlayer.makeMove(model, column);
			row = v.getRow();
			if (row == -1)
				return;
		} else {
			v = currentPlayer.makeMove(model, column);
			row = v.getRow();
			column = v.getCol();
		}

		fireColumnSelected(row, column, currentPlayer.getColor());
		if (model.checkIfWin(row, column)) {
			firePlayerWon(currentPlayer);
			return;
		}
		setNextTurn();

		return;
	}

	protected int getRow() {
		return row;
	}

	protected int getColumn() {
		return column;
	}

	/**
	 * Adds an IObserver to the list of observers. All the observers listed in
	 * the list get notified of every event.
	 * 
	 * @param observer
	 *            an instance of IObserver
	 * @return returns true if IObserver is added successfully else false
	 * @throws IllegalArgumentException
	 *             if the observer passed is null
	 */
	@Override
	public boolean addGameObserver(IObserver observer) {
		if (observer == null)
			throw new IllegalArgumentException("Observer can not be null");
		return observers.add(observer);
	}

	/**
	 * Removes an IObserver object from the list of observers
	 * 
	 * @param observer
	 *            an instance of IObserver
	 * @return returns true if IObserver is added successfully else false
	 * @throws IllegalArgumentException
	 *             if the observer passed is null
	 */
	@Override
	public boolean removeGameObserver(IObserver observer) {
		if (observer == null)
			throw new IllegalArgumentException("Observer can not be null");

		return observers.remove(observer);
	}

	/**
	 * This method is called by the IObserver when the user clicks on exit
	 * button. This method is supposed to inform the model of end of game event
	 * and also calls fireGameEnded() method to inform all the observers.
	 */
	@Override
	public void endGame() {
		fireGameEnded();
	}

	/**
	 * This method is called by the IObserver when the user clicks on reset
	 * button or if the board gets filled. This method is supposed to inform the
	 * model of reset game event and also calls fireReset() method to inform all
	 * the observers.
	 */
	@Override
	public void gameReset() {
		currentPlayer = player1;
		model.resetGame();
		fireReset();
	}

	/**
	 * This method is fired by the startGame() method. This method informs all
	 * the observers of game start event.
	 * 
	 * @param row
	 *            number of rows in the grid
	 * @param column
	 *            number of columns in the grid
	 */
	@Override
	public void fireGameStarted(int row, int column) {
		if (row < 0 || column < 0)
			throw new IllegalArgumentException(
					"Row or column can not be negative.");

		for (IObserver obs : observers) {
			obs.gameStarted(row, column);
		}
	}

	/**
	 * This method calls gameEnded() method of IObserver to inform all the
	 * observers of end of game event.
	 */
	@Override
	public void fireGameEnded() {
		for (IObserver obs : observers) {
			obs.gameEnded();
		}
	}

	/**
	 * This method calls selectPlayertypes() method of IObserver to inform all
	 * the observers of the event.
	 */
	@Override
	public void firePlayerSelected() {
		for (IObserver obs : observers) {
			obs.selectPlayerTypes();
		}
	}

	/**
	 * This method calls columnSelected() method of IObserver to inform all the
	 * observers of which column and row have been selected to drop the coin.
	 * 
	 * @param row
	 *            row in which the coin should be placed
	 * @param column
	 *            column in which the coin should be placed
	 * @param Which
	 *            color the coin should get depending on whose turn it was
	 * @throws IllegalArgumentException
	 *             if row or column are -1<br>
	 *             or row or column exceed the size of the grid<or>or color
	 *             passed is null
	 */
	@Override
	public void fireColumnSelected(int row, int column, Color c) {
		if (c == null)
			throw new IllegalArgumentException("Color can not be null");

		if (column < 0 || column > this.column)
			throw new IllegalArgumentException(
					"Column number exceeding grid size");

		if (row < 0 || row > this.row)
			throw new IllegalArgumentException("Row number exceeding grid size");

		for (IObserver obs : observers) {
			obs.columnSelected(row, column, c);
		}
	}

	/**
	 * This method calls win() method of IObserver to inform all the observers
	 * of win of a Player.
	 * 
	 * @param player
	 *            Player who won the game
	 * @throws IllegalArgumentException
	 *             if the player passed is null
	 */
	@Override
	public void firePlayerWon(Player player) {

		if (player == null)
			throw new IllegalArgumentException("Player can not be null.");

		for (IObserver obs : observers) {
			obs.win(player);
		}
	}

	/**
	 * This method calls gameReset() method of IObserver to inform all the
	 * observers of reset of game event.
	 */
	@Override
	public void fireReset() {
		for (IObserver obs : observers) {
			obs.gameReset();
		}
	}

	/**
	 * This method is called by the IObserver when Players are set. Accepts two
	 * PlayerType and calls PlayerFactory method to create the two Player.
	 * 
	 * @param one
	 *            PlayerType
	 * @param two
	 *            PlayerType
	 * @throws IllegalArgumentException
	 *             if the PlayerType is null
	 */
	@Override
	public void setPlayer(PlayerType one, PlayerType two) {

		if (one == null || two == null)
			throw new IllegalArgumentException(
					"PlayerType can not be negative.");

		player1 = PlayerFactory.getPlayer(one, Color.RED);
		player2 = PlayerFactory.getPlayer(two, Color.YELLOW);
		firePlayerSelected();
	}

	/**
	 * This method is used by the controller to switch the turns between two
	 * players.
	 */
	@Override
	public void setNextTurn() {
		currentPlayer = currentPlayer == player1 ? player2 : player1;
	}

	/**
	 * This method calls setRow() and setColumn() method of IObserver to inform
	 * all the observers of the dimensions of the grid.
	 * 
	 * @param row
	 *            number of rows in the grid
	 * @param col
	 *            number of columns in the grid
	 * @throws IllegalArgumentException
	 *             if the row or column passed in is -1
	 */
	@Override
	public void fireSetRowColumn(int row, int col) {
		if (col < 0 || col > this.column)
			throw new IllegalArgumentException(
					"Column number exceeding grid size");

		if (row < 0 || row > this.row)
			throw new IllegalArgumentException("Row number exceeding grid size");

		for (IObserver obs : observers) {
			obs.setRow(row);
			obs.setColumn(col);
		}
	}

}
