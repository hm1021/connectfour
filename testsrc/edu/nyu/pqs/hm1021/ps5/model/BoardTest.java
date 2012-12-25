package edu.nyu.pqs.hm1021.ps5.model;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {

	Board one;
	Board two;

	@Before
	public void setUp() {
		one = new Board.Builder().setRows(6).setColumns(7).build();
	}

	@Test
	public void testSetPlayerType() {
		assertTrue(one.setPlayerType(4, 5, Color.RED));
	}

	@Test
	public void testSetPlayerType_spotNotAvailable() {
		one.setPlayerType(4, 5, Color.RED);
		assertFalse(one.setPlayerType(4, 5, Color.RED));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetPlayerType_withBadColumn() {
		one.setPlayerType(3,-1,Color.RED);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetPlayerType_withBadColumnTwo() {
		one.setPlayerType(3,10,Color.RED);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetPlayerType_withBadRow() {
		one.setPlayerType(-1,3,Color.RED);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetPlayerType_withBadRowTwo() {
		one.setPlayerType(30,1,Color.RED);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetPlayerType_withBadColor() {
		one.setPlayerType(3,1,null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetColor_withBadColumn() {
		one.setColor(3,-1,Color.RED);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetColor_withBadColumnTwo() {
		one.setColor(3,10,Color.RED);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetColor_withBadRow() {
		one.setColor(-1,3,Color.RED);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetColor_withBadRowTwo() {
		one.setColor(30,1,Color.RED);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetColor_withBadColumn() {
		one.getColor(3,-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetColor_withBadColumnTwo() {
		one.getColor(3,10);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetColor_withBadRow() {
		one.getColor(-1,3);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetColor_withBadRowTwo() {
		one.getColor(30,1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetEmptyRowForColumn_withBadColumn() {
		one.getEmptyRowForColumn(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetEmptyRowForColumn_withBadColumnTwo() {
		one.getEmptyRowForColumn(10);
	}
	
	@Test
	public void testEquals() {
		Board two = one;
		assertTrue(one.equals(two));
	}

	@Test
	public void testEquals_nullObject() {
		two = null;
		assertFalse(one.equals(two));
	}

	@Test
	public void testEquals_differentTypeOfObject() {
		Object two = new Object();
		assertFalse(one.equals(two));
	}

	@Test
	public void testEquals_differentColumn() {
		two = new Board.Builder().setRows(6).setColumns(4).build();
		assertFalse(one.equals(two));
	}

	@Test
	public void testEquals_differentRow() {
		two = new Board.Builder().setRows(2).setColumns(7).build();
		assertFalse(one.equals(two));
	}

	@Test
	public void testToString() {
		String a = "Board [row=" + one.getRow() + ", column=" + one.getColumn() + "]";
		assertTrue(a.equals(one.toString()));
	}

	@Test
	public void testHashCode() {
		two = one;
		assertEquals(one.hashCode(), two.hashCode());
	}

	@Test
	public void testHashCode_nullRow() {
		one = new Board.Builder().setRows(0).setColumns(7).build();
		two = new Board.Builder().setRows(0).setColumns(7).build();
		assertEquals(one.hashCode(), two.hashCode());
	}
}
