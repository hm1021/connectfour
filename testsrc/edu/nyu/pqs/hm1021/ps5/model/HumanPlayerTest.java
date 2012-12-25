package edu.nyu.pqs.hm1021.ps5.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class HumanPlayerTest {
	
	Player one;
	Player two;
	
	@Before
	public void setUP(){
		one = PlayerFactory.getPlayer(PlayerType.HUMAN, Color.RED);
	}

	@Test
	public void test_humanPlayer() {
		two = PlayerFactory.getPlayer(PlayerType.HUMAN, Color.RED);
		assertTrue(one.equals(two));
	}
	
	@Test
	public void testMakeMove_checkRow(){
		IConnectFour model1 = ConnectFour.getInstance();
		model1.startGame(6, 7);
		Coordinates c = one.makeMove(model1, 3);
		assertEquals(5,c.getRow());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMakeMove_nullModel(){
		HumanPlayer one = new HumanPlayer(Color.RED);
		one.makeMove(null, 3);
	}
	
	@Test
	public void testMakeMove_checkColumn(){
		IConnectFour model1 = ConnectFour.getInstance();
		model1.startGame(6, 7);
		Coordinates c = one.makeMove(model1, 3);
		assertEquals(3,c.getCol());
	}
	
	@Test
	public void testMakeMove_noRowLeft(){
		IConnectFour model1 = ConnectFour.getInstance();
		model1.startGame(6, 7);
		one.makeMove(model1, 3);
		one.makeMove(model1, 3);
		one.makeMove(model1, 3);
		one.makeMove(model1, 3);
		one.makeMove(model1, 3);
		one.makeMove(model1, 3);
		Coordinates c = one.makeMove(model1, 3);
		assertEquals(-1,c.getRow());
	}
	
	@Test
	public void testMakeMove_randomRow(){
		IConnectFour model1 = ConnectFour.getInstance();
		model1.startGame(6, 7);
		one.makeMove(model1, 3);
		one.makeMove(model1, 3);
		one.makeMove(model1, 3);
		Coordinates c = one.makeMove(model1, 3);
		assertEquals(2,c.getRow());
	}
	
	@Test
	public void testGetType(){
		assertEquals(PlayerType.HUMAN,one.getType());
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
		two = PlayerFactory.getPlayer(PlayerType.COMPUTER, Color.RED);
		assertFalse(one.equals(two));
	}
	
	@Test
	public void testEquals_differentColor(){
		two = PlayerFactory.getPlayer(PlayerType.HUMAN, Color.YELLOW);
		assertFalse(one.equals(two));
	}
	
	@Test
	public void testToString(){
		String a = "HumanPlayer [type=" + PlayerType.HUMAN + ", color=" + Color.RED + "]";
		assertTrue(a.equals(one.toString()));
	}
	
	@Test
	public void testHashCode(){
		two = PlayerFactory.getPlayer(PlayerType.HUMAN, Color.RED);
		assertEquals(one.hashCode(),two.hashCode());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testHashCode_nullColor(){
		one = PlayerFactory.getPlayer(PlayerType.HUMAN, null);
		two = PlayerFactory.getPlayer(PlayerType.HUMAN, null);
		assertEquals(one.hashCode(),two.hashCode());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testHashCode_nullType(){
		one = PlayerFactory.getPlayer(null, Color.RED);
		two = PlayerFactory.getPlayer(null, Color.RED);
		assertEquals(one.hashCode(),two.hashCode());
	}
}
