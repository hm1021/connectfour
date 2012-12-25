package edu.nyu.pqs.hm1021.ps5.model;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class ComputerPlayerTest {
	
	Player one;
	Player two;
	
	@Before
	public void setUP(){
		one = PlayerFactory.getPlayer(PlayerType.COMPUTER, Color.RED);
	}
	
	@Test
	public void test_computerPlayer() {
		one = PlayerFactory.getPlayer(PlayerType.COMPUTER, Color.RED);
		two = PlayerFactory.getPlayer(PlayerType.COMPUTER, Color.RED);
		assertTrue(one.equals(two));
	}

	@Test
	public void testMakeMove_checkRow(){
		ComputerPlayer one = new ComputerPlayer(Color.RED);
		IConnectFour model1 = ConnectFour.getInstance();
		model1.startGame(6, 7);
		Coordinates c = one.makeMove(model1, 3);
		assertEquals(one.getCoordinates().getRow(),c.getRow());
	}
	
	@Test
	public void testMakeMove_checkColumn(){
		ComputerPlayer one = new ComputerPlayer(Color.RED);
		IConnectFour model1 = ConnectFour.getInstance();
		model1.startGame(6, 7);
		Coordinates c = one.makeMove(model1, 3);
		assertEquals(one.getCoordinates().getCol(),c.getCol());
	}
	
	@Test
	public void testMakeMove_noRowLeft(){
		ComputerPlayer one = new ComputerPlayer(Color.RED);
		IConnectFour model1 = ConnectFour.getInstance();
		model1.startGame(6, 7);
		model1.dropCoin(3, Color.RED);
		model1.dropCoin(3, Color.RED);
		model1.dropCoin(3, Color.RED);
		model1.dropCoin(3, Color.RED);
		model1.dropCoin(3, Color.RED);
		model1.dropCoin(3, Color.RED);
		Coordinates c = one.makeMove(model1, 3);
		assertEquals(one.getCoordinates().getRow(),c.getRow());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMakeMove_nullModel(){
		ComputerPlayer one = new ComputerPlayer(Color.RED);
		Coordinates c = one.makeMove(null, 3);
		assertEquals(one.getCoordinates().getRow(),c.getRow());
	}
	
	@Test
	public void testMakeMove_winningRowwise(){
		ComputerPlayer one = new ComputerPlayer(Color.RED);
		IConnectFour model1 = ConnectFour.getInstance();
		model1.startGame(6, 7);
		model1.dropCoin(3, Color.RED);
		model1.dropCoin(3, Color.RED);
		model1.dropCoin(3, Color.RED);
		Coordinates c = one.makeMove(model1, 3);
		assertEquals(2,c.getRow());
	}
	
	@Test
	public void testMakeMove_winningColumnwise(){
		ComputerPlayer one = new ComputerPlayer(Color.RED);
		IConnectFour model1 = ConnectFour.getInstance();
		model1.startGame(6, 7);
		model1.dropCoin(2, Color.RED);
		model1.dropCoin(3, Color.RED);
		model1.dropCoin(4, Color.RED);
		Coordinates c = one.makeMove(model1, 3);
		assertEquals(1,c.getCol());
	}
	
	@Test
	public void testMakeMove_winningForwardDiagonalwise_forColumn(){
		ComputerPlayer one = new ComputerPlayer(Color.RED);
		IConnectFour model1 = ConnectFour.getInstance();
		model1.startGame(6, 7);
		model1.dropCoin(2, Color.RED);
		model1.dropCoin(3, Color.YELLOW);
		model1.dropCoin(3, Color.RED);
		model1.dropCoin(4, Color.RED);
		model1.dropCoin(4, Color.YELLOW);
		model1.dropCoin(4, Color.RED);
		model1.dropCoin(5, Color.YELLOW);
		model1.dropCoin(5, Color.RED);
		model1.dropCoin(5, Color.YELLOW);
		Coordinates c = one.makeMove(model1, 3);
		assertEquals(5,c.getCol());
	}
	
	@Test
	public void testMakeMove_winningForwardDiagonalwise_forRow(){
		ComputerPlayer one = new ComputerPlayer(Color.RED);
		IConnectFour model1 = ConnectFour.getInstance();
		model1.startGame(6, 7);
		model1.dropCoin(2, Color.RED);
		model1.dropCoin(3, Color.YELLOW);
		model1.dropCoin(3, Color.RED);
		model1.dropCoin(4, Color.RED);
		model1.dropCoin(4, Color.YELLOW);
		model1.dropCoin(4, Color.RED);
		model1.dropCoin(5, Color.YELLOW);
		model1.dropCoin(5, Color.RED);
		model1.dropCoin(5, Color.YELLOW);
		Coordinates c = one.makeMove(model1, 3);
		assertEquals(2,c.getRow());
	}
	
	@Test
	public void testMakeMove_winningBackwardDiagonalwise_forRow(){
		ComputerPlayer one = new ComputerPlayer(Color.RED);
		IConnectFour model1 = ConnectFour.getInstance();
		model1.startGame(6, 7);
		model1.dropCoin(5, Color.RED);
		model1.dropCoin(4, Color.YELLOW);
		model1.dropCoin(4, Color.RED);
		model1.dropCoin(3, Color.RED);
		model1.dropCoin(3, Color.YELLOW);
		model1.dropCoin(3, Color.RED);
		model1.dropCoin(2, Color.YELLOW);
		model1.dropCoin(2, Color.RED);
		model1.dropCoin(2, Color.YELLOW);
		Coordinates c = one.makeMove(model1, 3);
		assertEquals(2,c.getRow());
	}
	
	@Test
	public void testMakeMove_winningBackwardDiagonalwise_forColumn(){
		ComputerPlayer one = new ComputerPlayer(Color.RED);
		IConnectFour model1 = ConnectFour.getInstance();
		model1.startGame(6, 7);
		model1.dropCoin(5, Color.RED);
		model1.dropCoin(4, Color.YELLOW);
		model1.dropCoin(4, Color.RED);
		model1.dropCoin(3, Color.RED);
		model1.dropCoin(3, Color.YELLOW);
		model1.dropCoin(3, Color.RED);
		model1.dropCoin(2, Color.YELLOW);
		model1.dropCoin(2, Color.RED);
		model1.dropCoin(2, Color.YELLOW);
		Coordinates c = one.makeMove(model1, 3);
		assertEquals(2,c.getCol());
	}
	
	@Test
	public void testGetType(){
		assertEquals(PlayerType.COMPUTER,one.getType());
	}
	
	@Test
	public void testGetColor(){
		assertEquals(Color.RED,one.getColor());
	}
	
	@Test
	public void testEquals_sameObject(){
		two = one;
		assertTrue(one.equals(two));
	}
	
	@Test
	public void testEquals_nullObject(){
		two = null;
		assertFalse(one.equals(two));
	}
	
	@Test
	public void testEquals_differentTypeOfObject(){
		Object two = new Object();
		assertFalse(one.equals(two));
	}
	
	@Test
	public void testEquals_differentTypes(){
		two = PlayerFactory.getPlayer(PlayerType.HUMAN, Color.RED);
		assertFalse(one.equals(two));
	}
	
	@Test
	public void testEquals_differentColor(){
		two = PlayerFactory.getPlayer(PlayerType.COMPUTER, Color.YELLOW);
		assertFalse(one.equals(two));
	}
	
	@Test
	public void testToString(){
		String a = "ComputerPlayer [type=" + PlayerType.COMPUTER + ", color=" + Color.RED + "]";
		assertTrue(a.equals(one.toString()));
	}
	
	@Test
	public void testHashCode(){
		two = PlayerFactory.getPlayer(PlayerType.COMPUTER, Color.RED);
		assertEquals(one.hashCode(),two.hashCode());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testHashCode_nullColor(){
		one = PlayerFactory.getPlayer(PlayerType.COMPUTER, null);
		two = PlayerFactory.getPlayer(PlayerType.COMPUTER, null);
		assertEquals(one.hashCode(),two.hashCode());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testHashCode_nullType(){
		one = PlayerFactory.getPlayer(null, Color.RED);
		two = PlayerFactory.getPlayer(null, Color.RED);
		assertEquals(one.hashCode(),two.hashCode());
	}
}
