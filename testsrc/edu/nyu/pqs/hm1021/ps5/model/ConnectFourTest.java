package edu.nyu.pqs.hm1021.ps5.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import edu.nyu.pqs.hm1021.ps5.controller.Controller;
import edu.nyu.pqs.hm1021.ps5.controller.IController;

public class ConnectFourTest {
	
	IConnectFour model1;
	IConnectFour model2;
	IConnectFour model3;
	IConnectFour model4;
	IController controller;

	@Before
	public void setUp() throws Exception {
		model1 = ConnectFour.getInstance();
		model2 = ConnectFour.getInstance();
		model3 = ConnectFour.getInstance();
		model4 = ConnectFour.getInstance();
		controller = new Controller();
	}

	@Test
	public void testEquals() {
		assertTrue(model1.equals(model2));
	}
	
	@Test
	public void testStartGame() {
		assertTrue(model1.startGame(6, 7));
		assertTrue(model2.startGame(3, 3));
	}
	
	@Test
	public void testRestartGame(){
		assertTrue(model1.resetGame());
	}
	
	@Test
	public void testDropCoin_idealCase(){
		assertTrue(model1.startGame(6, 7));
		assertEquals(5,model1.dropCoin(3, Color.RED));
	}
	
	@Test
	public void testDropCoin_fewRowsFilled(){
		model1.startGame(6, 7);
		model1.dropCoin(3, Color.RED);
		model1.dropCoin(3, Color.RED);
		model1.dropCoin(3, Color.RED);
		assertEquals(2,model1.dropCoin(3, Color.RED));
	}
	
	@Test
	public void testDropCoin_noRowsLeftInColumn(){
		model1.startGame(6, 7);
		model1.dropCoin(3, Color.RED);
		model1.dropCoin(3, Color.RED);
		model1.dropCoin(3, Color.RED);
		model1.dropCoin(3, Color.RED);
		model1.dropCoin(3, Color.RED);
		model1.dropCoin(3, Color.RED);
		assertEquals(-1,model1.dropCoin(3, Color.RED));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDropCoin_badColumn(){
		model1.startGame(6, 7);
		assertEquals(-1,model1.dropCoin(8, Color.RED));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testStartGame_withBadRow() {
		model1.startGame(-1, 5);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testStartGame_withBadColumn() {
		model1.startGame(5, -1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDropCoin_withBadColumn() {
		model1.dropCoin(-1, Color.RED);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDropCoin_withBadColor() {
		model1.dropCoin(5, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testgetEmptyRowForColumn_withBadColumn() {
		model1.getEmptyRowForColumn(-1, Color.RED);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testgetEmptyRowForColumn_withBadColumnTwo() {
		model1.getEmptyRowForColumn(10, Color.RED);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testgetEmptyRowForColumn_withBadColor() {
		model1.getEmptyRowForColumn(5, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCheckIfWin_withBadColumn() {
		model1.checkIfWin(3,-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCheckIfWin_withBadColumnTwo() {
		model1.checkIfWin(3,10);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCheckIfWin_withBadRow() {
		model1.checkIfWin(-1,3);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCheckIfWin_withBadRowTwo() {
		model1.checkIfWin(30,1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetColor_withBadColumn() {
		model1.setColor(3,-1,Color.RED);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetColor_withBadColumnTwo() {
		model1.setColor(3,10,Color.RED);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetColor_withBadRow() {
		model1.setColor(-1,3,Color.RED);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetColor_withBadRowTwo() {
		model1.setColor(30,1,Color.RED);
	}
	
	@Test
	public void testCheckIfWin_rowwise(){
		model1.startGame(6, 7);
		model1.dropCoin(1, Color.RED);
		model1.dropCoin(2, Color.RED);
		model1.dropCoin(3, Color.RED);
		model1.dropCoin(4, Color.RED);
		assertTrue(model1.checkIfWin(5, 3));
	}
	
	@Test
	public void testCheckIfWin_columnwise(){
		model3.startGame(6, 7);
		model3.dropCoin(3, Color.RED);
		model3.dropCoin(3, Color.RED);
		model3.dropCoin(3, Color.RED);
		model3.dropCoin(3, Color.RED);
		assertTrue(model3.checkIfWin(3, 3));
	}
	
	@Test
	public void testCheckIfWin_rowwise_cornerRow(){
		model1.startGame(3, 7);
		model1.dropCoin(1, Color.RED);
		model1.dropCoin(1, Color.RED);
		model1.dropCoin(1, Color.RED);
		
		model1.dropCoin(2, Color.RED);
		model1.dropCoin(2, Color.RED);
		model1.dropCoin(2, Color.RED);
		
		model1.dropCoin(3, Color.RED);
		model1.dropCoin(3, Color.RED);
		model1.dropCoin(3, Color.RED);
		model1.dropCoin(3, Color.RED);
		
		model1.dropCoin(4, Color.RED);
		model1.dropCoin(4, Color.RED);
		model1.dropCoin(4, Color.RED);
		model1.dropCoin(4, Color.RED);
		
		assertTrue(model1.checkIfWin(0, 1));
	}
	
	@Test
	public void testCheckIfWin_columnwise_cornerColumn(){
		model3.startGame(6, 7);
		model3.dropCoin(0, Color.RED);
		model3.dropCoin(0, Color.RED);
		model3.dropCoin(0, Color.RED);
		model3.dropCoin(0, Color.RED);
		assertTrue(model3.checkIfWin(5, 0));
	}
	
	@Test
	public void testCheckIfWin_forwardDiagonal(){
		model2.startGame(6, 7);
		model2.dropCoin(3, Color.RED);
		model2.dropCoin(4, Color.YELLOW);
		model2.dropCoin(4, Color.RED);
		model2.dropCoin(5, Color.RED);
		model2.dropCoin(5, Color.YELLOW);
		model2.dropCoin(5, Color.RED);
		model2.dropCoin(6, Color.YELLOW);
		model2.dropCoin(6, Color.RED);
		model2.dropCoin(6, Color.YELLOW);
		model2.dropCoin(6, Color.RED);
		assertTrue(model2.checkIfWin(5,3));
	}
	
	@Test
	public void testCheckIfWin_backwardDiagonal(){
		model4.startGame(6, 7);
		model4.dropCoin(3, Color.RED);
		model4.dropCoin(2, Color.YELLOW);
		model4.dropCoin(2, Color.RED);
		model4.dropCoin(1, Color.RED);
		model4.dropCoin(1, Color.YELLOW);
		model4.dropCoin(1, Color.RED);
		model4.dropCoin(0, Color.YELLOW);
		model4.dropCoin(0, Color.RED);
		model4.dropCoin(0, Color.YELLOW);
		model4.dropCoin(0, Color.RED);
		assertTrue(model4.checkIfWin(4,2));
	}
	
	@Test
	public void testCheckIfWin_rowwise_false(){
		model1.startGame(6, 7);
		model1.dropCoin(2, Color.RED);
		model1.dropCoin(3, Color.RED);
		model1.dropCoin(4, Color.RED);
		assertFalse(model1.checkIfWin(5, 3));
	}
	
	@Test
	public void testCheckIfWin_columnwise_false(){
		model3.startGame(6, 7);
		model3.dropCoin(3, Color.RED);
		model3.dropCoin(3, Color.RED);
		model3.dropCoin(3, Color.RED);
		assertFalse(model3.checkIfWin(3, 3));
	}
	
	@Test
	public void testCheckIfWin_forwardDiagonal_false(){
		model2.startGame(6, 7);
		model2.dropCoin(3, Color.RED);
		model2.dropCoin(4, Color.YELLOW);
		model2.dropCoin(4, Color.RED);
		model2.dropCoin(5, Color.RED);
		model2.dropCoin(5, Color.YELLOW);
		model2.dropCoin(6, Color.YELLOW);
		model2.dropCoin(6, Color.RED);
		model2.dropCoin(6, Color.YELLOW);
		model2.dropCoin(6, Color.RED);
		assertFalse(model2.checkIfWin(5,3));
	}
	
	@Test
	public void testCheckIfWin_backwardDiagonal_false(){
		model4.startGame(6, 7);
		model4.dropCoin(3, Color.RED);
		model4.dropCoin(2, Color.YELLOW);
		model4.dropCoin(2, Color.RED);
		model4.dropCoin(1, Color.RED);
		model4.dropCoin(1, Color.YELLOW);
		model4.dropCoin(1, Color.RED);
		model4.dropCoin(0, Color.YELLOW);
		model4.dropCoin(0, Color.RED);
		model4.dropCoin(0, Color.YELLOW);
		assertFalse(model4.checkIfWin(4,2));
	}
	
	@Test
	public void testCheckIfBoardFull() {
		model1.startGame(4, 5);
		
		model1.dropCoin(0, Color.YELLOW);
		model1.dropCoin(0, Color.RED);
		model1.dropCoin(0, Color.YELLOW);
		model1.dropCoin(0, Color.RED);
		
		model1.dropCoin(1, Color.YELLOW);
		model1.dropCoin(1, Color.YELLOW);
		model1.dropCoin(1, Color.YELLOW);
		model1.dropCoin(1, Color.YELLOW);
		
		model1.dropCoin(2, Color.RED);
		model1.dropCoin(2, Color.RED);
		model1.dropCoin(2, Color.RED);
		model1.dropCoin(2, Color.RED);
		
		model1.dropCoin(3, Color.YELLOW);
		model1.dropCoin(3, Color.YELLOW);
		model1.dropCoin(3, Color.RED);
		model1.dropCoin(3, Color.YELLOW);
		
		model1.dropCoin(4, Color.RED);
		model1.dropCoin(4, Color.YELLOW);
		model1.dropCoin(4, Color.RED);
		model1.dropCoin(4, Color.RED);
		
		assertTrue(model1.checkIfBoardFull());
	}
	
	@Test
	public void testCheckIfBoardFull_notFull() {
		model1.startGame(4, 5);
		
		model1.dropCoin(0, Color.YELLOW);
		model1.dropCoin(0, Color.RED);
		model1.dropCoin(0, Color.YELLOW);
		model1.dropCoin(0, Color.RED);
		
		model1.dropCoin(1, Color.YELLOW);
		model1.dropCoin(1, Color.YELLOW);
		model1.dropCoin(1, Color.YELLOW);
		model1.dropCoin(1, Color.YELLOW);
		
		model1.dropCoin(2, Color.RED);
		model1.dropCoin(2, Color.RED);
		model1.dropCoin(2, Color.RED);
		model1.dropCoin(2, Color.RED);
		
		model1.dropCoin(3, Color.YELLOW);
		model1.dropCoin(3, Color.YELLOW);
		model1.dropCoin(3, Color.RED);
		model1.dropCoin(3, Color.YELLOW);
		
		model1.dropCoin(4, Color.RED);
		model1.dropCoin(4, Color.YELLOW);
		model1.dropCoin(4, Color.RED);
		
		assertFalse(model1.checkIfBoardFull());
	}
	
	@Test
	public void testCheckIfBoardFull_emptyBoard() {
		model1.startGame(4, 5);	
		assertFalse(model1.checkIfBoardFull());
	}
	
	@Test
	public void testSetColor_goodCase(){
		model1.startGame(6, 7);
		assertTrue(model1.setColor(5, 3, Color.RED));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetColor_badRowBadColumn(){
		model1.startGame(6, 7);
		assertFalse(model1.setColor(-1, -1, Color.RED));
	}
	
	@Test
	public void testGetRows(){
		model1.startGame(6, 7);
		assertEquals(6,model1.getRows());
	}
	
	@Test
	public void testGetCols(){
		model1.startGame(6, 7);
		assertEquals(7,model1.getCols());
	}
}
