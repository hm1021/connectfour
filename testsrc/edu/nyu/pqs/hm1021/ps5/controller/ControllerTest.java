package edu.nyu.pqs.hm1021.ps5.controller;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import edu.nyu.pqs.hm1021.ps5.model.IConnectFour;
import edu.nyu.pqs.hm1021.ps5.model.Player;
import edu.nyu.pqs.hm1021.ps5.model.PlayerFactory;
import edu.nyu.pqs.hm1021.ps5.model.PlayerType;
import edu.nyu.pqs.hm1021.ps5.view.IObserver;
public class ControllerTest {
	
	IController controller;
	IObserver mock;
	IConnectFour mockModel;
	
	@Before
	public void setup(){
		controller = new Controller();
		mock = createMock(IObserver.class);
		mockModel = createMock(IConnectFour.class);
		controller.addGameObserver(mock);
	}

	@Test
	public void testStartGame() {
		mock.gameStarted(6, 7);
		replay(mock);
		controller.startGame(6, 7);
		verify(mock);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testStartGame_withBadRow() {
		controller.startGame(-1, 5);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testStartGame_withBadColumn() {
		controller.startGame(5, -1);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testColumnSelected_badColumn(){
		controller.setPlayer(PlayerType.HUMAN, PlayerType.HUMAN);
		controller.fireSetRowColumn(6, 7);
		controller.startGame(6, 7);
		controller.columnSelected(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testColumnSelected_badColumnTwo(){
		controller.setPlayer(PlayerType.HUMAN, PlayerType.HUMAN);
		controller.fireSetRowColumn(6, 7);
		controller.startGame(6, 7);
		controller.columnSelected(8);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFireGameStarted_badRow(){
		controller.fireGameStarted(-1, 3);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFireGameStarted_badColumn(){
		controller.fireGameStarted(3, -1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFireColumnSelected_badRow(){
		controller.fireColumnSelected(-1, 5, Color.RED);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFireColumnSelected_badRowTwo(){
		controller.fireColumnSelected(7, 5, Color.RED);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFireColumnSelected_badColumn(){
		controller.fireColumnSelected(3, -1, Color.RED);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFireColumnSelected_badColumnTwo(){
		controller.fireColumnSelected(3, 10, Color.RED);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFireColumnSelected_badColor(){
		controller.fireColumnSelected(3, 3, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFirePlayerWon_badPlayer(){
		controller.firePlayerWon(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPlayerSelected_badPlayer(){
		controller.setPlayer(null,PlayerType.COMPUTER);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPlayerSelected_badPlayerTwo(){
		controller.setPlayer(PlayerType.COMPUTER,null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFireSetRowColumn_badRow(){
		controller.fireSetRowColumn(-1, 5);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFireSetRowColumn_badRowTwo(){
		controller.fireSetRowColumn(7, 5);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFireSetRowColumn_badColumn(){
		controller.fireSetRowColumn(3, -1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFireSetRowColumn_badColumnTwo(){
		controller.fireSetRowColumn(3, 10);
	}
	
	@Test
	public void testColumnSelected(){
		mock.selectPlayerTypes();
		mock.setRow(6);
		mock.setColumn(7);
		mock.gameStarted(6, 7);
		mock.columnSelected(5, 3, Color.RED);
		replay(mock);
		controller.setPlayer(PlayerType.HUMAN, PlayerType.HUMAN);
		controller.fireSetRowColumn(6, 7);
		controller.startGame(6, 7);
		controller.columnSelected(3);
		verify(mock);
	}
	
	@Test
	public void testColumnSelected_withWinner_playerOne(){
		Player one = PlayerFactory.getPlayer(PlayerType.HUMAN, Color.RED);
		
		mock.selectPlayerTypes();
		mock.setRow(6);
		mock.setColumn(7);
		mock.gameStarted(6, 7);
		mock.columnSelected(5, 3, Color.RED);
		mock.columnSelected(5, 4, Color.YELLOW);
		mock.columnSelected(4, 3, Color.RED);
		mock.columnSelected(4, 4, Color.YELLOW);
		mock.columnSelected(3, 3, Color.RED);
		mock.columnSelected(3, 4, Color.YELLOW);
		mock.columnSelected(2, 3, Color.RED);
		mock.win(one);
		replay(mock);
		
		controller.setPlayer(PlayerType.HUMAN, PlayerType.HUMAN);
		controller.fireSetRowColumn(6, 7);
		controller.startGame(6, 7);
		controller.columnSelected(3);
		controller.columnSelected(4);
		controller.columnSelected(3);
		controller.columnSelected(4);
		controller.columnSelected(3);
		controller.columnSelected(4);
		controller.columnSelected(3);
		
		verify(mock);
	}
	
	@Test
	public void testColumnSelected_withWinner_playerTwo(){
		Player two = PlayerFactory.getPlayer(PlayerType.HUMAN, Color.YELLOW);
		
		mock.selectPlayerTypes();
		mock.setRow(6);
		mock.setColumn(7);
		mock.gameStarted(6, 7);
		mock.columnSelected(5, 3, Color.RED);
		mock.columnSelected(5, 4, Color.YELLOW);
		mock.columnSelected(4, 3, Color.RED);
		mock.columnSelected(4, 4, Color.YELLOW);
		mock.columnSelected(3, 3, Color.RED);
		mock.columnSelected(3, 4, Color.YELLOW);
		mock.columnSelected(5, 1, Color.RED);
		mock.columnSelected(2, 4, Color.YELLOW);
		mock.win(two);
		replay(mock);
		
		controller.setPlayer(PlayerType.HUMAN, PlayerType.HUMAN);
		controller.fireSetRowColumn(6, 7);
		controller.startGame(6, 7);
		controller.columnSelected(3);
		controller.columnSelected(4);
		controller.columnSelected(3);
		controller.columnSelected(4);
		controller.columnSelected(3);
		controller.columnSelected(4);
		controller.columnSelected(1);
		controller.columnSelected(4);
		
		verify(mock);
	}
	
	@Test
	public void testColumnSelected_boardFull(){
		mock.selectPlayerTypes();
		mock.setRow(2);
		mock.setColumn(2);
		mock.gameStarted(2,2);
		mock.columnSelected(1, 0, Color.RED);
		mock.columnSelected(1, 1, Color.YELLOW);
		mock.columnSelected(0, 0, Color.RED);
		mock.columnSelected(0, 1, Color.YELLOW);
		mock.gameReset();
		replay(mock);
		
		controller.setPlayer(PlayerType.HUMAN, PlayerType.HUMAN);
		controller.fireSetRowColumn(2,2);
		controller.startGame(2,2);
		controller.columnSelected(0);
		controller.columnSelected(1);
		controller.columnSelected(0);
		controller.columnSelected(1);
		controller.columnSelected(1);
		verify(mock);
	}
	
	@Test
	public void testEndGame(){
		mock.gameEnded();
		replay(mock);
		controller.endGame();
		verify(mock);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddObserver_nullCheck(){
		IController controller = new Controller();
		controller.addGameObserver(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRemoveObserver_nullCheck(){
		IController controller = new Controller();
		controller.removeGameObserver(null);
	}
	
	@Test
	public void testAddObserver(){
		IController controller = new Controller();
		controller.addGameObserver(mock);
	}
	
	@Test
	public void testRemoveObserver(){
		IController controller = new Controller();
		controller.removeGameObserver(mock);
	}
	
	@Test
	public void testGetRow(){
		Controller controller = new Controller();
		assertEquals(6,controller.getRow());
	}
	
	@Test
	public void testGetColumn(){
		Controller controller = new Controller();
		assertEquals(7,controller.getColumn());
	}
}