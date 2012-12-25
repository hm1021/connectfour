package edu.nyu.pqs.hm1021.ps5.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CoordinatesTest {

	Coordinates one;
	Coordinates two;
	
	@Before
	public void setUp(){
		one = new Coordinates(6, 7);
	}
	
	@Test
	public void testGetRow() {
		assertEquals(6,one.getRow());
	}

	@Test
	public void testGetColumn() {
		assertEquals(7,one.getCol());
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
	public void testEquals_differentRows(){
		two = new Coordinates(3, 7);
		assertFalse(one.equals(two));
	}
	
	@Test
	public void testEquals_differentColor(){
		two = new Coordinates(6, 2);
		assertFalse(one.equals(two));
	}
	
	@Test
	public void testToString(){
		String a = "Coordinates [row=" + one.getRow() + ", col=" + one.getCol() + "]";
		assertTrue(a.equals(one.toString()));
	}
	
	@Test
	public void testHashCode(){
		two = new Coordinates(6, 7);
		assertEquals(one.hashCode(),two.hashCode());
	}
}
