package edu.nyu.pqs.hm1021.ps5.controller;

import java.awt.Color;

import edu.nyu.pqs.hm1021.ps5.model.Player;
import edu.nyu.pqs.hm1021.ps5.model.PlayerType;
import edu.nyu.pqs.hm1021.ps5.view.IObserver;

/**
 * This interface acts as the Controller of Model View Controller pattern. A
 * class can implement this interface and coordinate with IConnectFour and
 * IObserver to implement Connect Four game.
 * 
 * @author hiral
 * 
 */
public interface IController {

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
	public void startGame(int row, int column);

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
	public boolean columnSelected(int column);

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
	public boolean addGameObserver(IObserver observer);

	/**
	 * Removes an IObserver object from the list of observers
	 * 
	 * @param observer
	 *            an instance of IObserver
	 * @return returns true if IObserver is added successfully else false
	 * @throws IllegalArgumentException
	 *             if the observer passed is null
	 */
	public boolean removeGameObserver(IObserver observer);

	/**
	 * This method is called by the IObserver when the user clicks on exit
	 * button. This method is supposed to inform the model of end of game event
	 * and also calls fireGameEnded() method to inform all the observers.
	 */
	public void endGame();

	/**
	 * This method is called by the IObserver when the user clicks on reset
	 * button or if the board gets filled. This method is supposed to inform the
	 * model of reset game event and also calls fireReset() method to inform all
	 * the observers.
	 */
	public void gameReset();

	/**
	 * This method is used by the controller to switch the turns between two
	 * players.
	 */
	public void setNextTurn();

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
	public void setPlayer(PlayerType one, PlayerType two);

	/**
	 * This method is fired by the startGame() method. This method informs all
	 * the observers of game start event.
	 * 
	 * @param row
	 *            number of rows in the grid
	 * @param column
	 *            number of columns in the grid
	 */
	public void fireGameStarted(int row, int column);

	/**
	 * This method calls gameEnded() method of IObserver to inform all the
	 * observers of end of game event.
	 */
	public void fireGameEnded();

	/**
	 * This method calls selectPlayertypes() method of IObserver to inform all
	 * the observers of the event.
	 */
	public void firePlayerSelected();

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
	public void fireColumnSelected(int row, int column, Color c);

	/**
	 * This method calls win() method of IObserver to inform all the observers
	 * of win of a Player.
	 * 
	 * @param player
	 *            Player who won the game
	 * @throws IllegalArgumentException
	 *             if the player passed is null
	 */
	public void firePlayerWon(Player player);

	/**
	 * This method calls gameReset() method of IObserver to inform all the
	 * observers of reset of game event.
	 */
	public void fireReset();

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
	public void fireSetRowColumn(int row, int col);
}