package edu.nyu.pqs.hm1021.ps5.model;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

public class PlayerFactoryTest {

	@Test
	public void test_humanPlayer_testType() {
		Player one = PlayerFactory.getPlayer(PlayerType.HUMAN, Color.RED);
		assertTrue(one.getType().equals(PlayerType.HUMAN));
	}
	
	@Test
	public void test_computerPlayerPlayer_testType() {
		Player one = PlayerFactory.getPlayer(PlayerType.COMPUTER, Color.RED);
		assertTrue(one.getType().equals(PlayerType.COMPUTER));
	}

	@Test
	public void test_humanPlayer_testColor() {
		Player one = PlayerFactory.getPlayer(PlayerType.HUMAN, Color.RED);
		assertEquals(Color.RED,one.getColor());
	}
	
	@Test
	public void test_computerPlayerPlayer_testColor() {
		Player one = PlayerFactory.getPlayer(PlayerType.COMPUTER, Color.RED);
		assertEquals(Color.RED,one.getColor());
	}
}
