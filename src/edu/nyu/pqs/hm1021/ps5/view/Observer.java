package edu.nyu.pqs.hm1021.ps5.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

import edu.nyu.pqs.hm1021.ps5.controller.IController;
import edu.nyu.pqs.hm1021.ps5.model.Player;
import edu.nyu.pqs.hm1021.ps5.model.PlayerType;

/**
 * This class implements IObserver interface and ActionListener interface. It
 * uses Java's AWT Swing library for creating the layout and frames. It contains
 * methods to start, reset, exit the game and listeners for events from the
 * controller.
 * 
 * @author hiral
 * 
 */
public class Observer implements IObserver, ActionListener {

	private final IController controller;

	private int row = 6;
	private int column = 7;
	private boolean isPlayerSet = false;

	private JButton singlePlayer = new JButton("Single Player");;
	private JButton twoPlayer = new JButton("Two Players");
	private JButton start = new JButton("Start");
	private JButton exit = new JButton("Exit");
	private JButton reset = new JButton("Reset");

	private JPanel startPanel = new JPanel();
	private JFrame startFrame = new JFrame();
	private JLabel rowsLabel = new JLabel("Rows");
	private JLabel colsLabel = new JLabel("Cols");
	private JPanel winner = new JPanel();
	private JTextField rowsField = new JTextField("6", 10);
	private JTextField colsField = new JTextField("7", 10);
	private JPanel grid = new JPanel();
	private JPanel resetAndExit = new JPanel();
	private JFrame gridFrame = new JFrame();

	private Border border = BorderFactory.createLineBorder(Color.BLACK);

	private List<JButton> columnButtons = new ArrayList<JButton>();
	private JPanel[][] panelList;

	public Observer(final IController ctrl) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		this.controller = ctrl;
		controller.addGameObserver(this);

		gridFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		startWindow();
	}

	/**
	 * This is the first window that is displayed when the game is initialized.
	 * This window shows an option to set rows and columns. Also user is
	 * expected to select either Single Player or Double Player after which user
	 * can click start button to begin.
	 */
	protected void startWindow() {
		startPanel.setBackground(java.awt.Color.white);
		startFrame.getContentPane().add(BorderLayout.CENTER, startPanel);

		singlePlayer.addActionListener(this);
		twoPlayer.addActionListener(this);
		start.addActionListener(this);
		exit.addActionListener(this);
		startPanel.add(rowsLabel);
		startPanel.add(rowsField);
		startPanel.add(colsLabel);
		startPanel.add(colsField);
		startPanel.add(singlePlayer);
		startPanel.add(twoPlayer);
		startPanel.add(start);
		startPanel.add(exit);
		startFrame.setSize(200, 200);
		startFrame.setLocationRelativeTo(null);
		startFrame.setVisible(true);
	}

	/**
	 * Once the game starts, the grid is created which has *column* number of
	 * buttons in first row to select a column and then *row* * *column* number
	 * of panels.
	 * 
	 * @param row number of rows
	 * @param col number of columns
	 */
	protected void grid(int row, int col) {
		startFrame.dispose();
		gridFrame = new JFrame();
		grid.setBackground(java.awt.Color.blue);
		grid.setLayout(new GridLayout(0, col));
		grid.setPreferredSize(new Dimension(50 * col, 50 * col * 4 / 5));
		resetAndExit.setLayout(new GridLayout(3, 0));
		resetAndExit
				.setPreferredSize(new Dimension(col * 50, col * 50 * 1 / 5));

		for (int i = 0; i < col; i++) {
			JButton button = new JButton(i + 1 + "");
			columnButtons.add(i, button);
			grid.add(button);
			columnButtons.get(i).addActionListener(this);
		}
		panelList = new JPanel[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				JPanel panel = new JPanel();
				panel.setBorder(border);
				panel.setBackground(Color.WHITE);
				panelList[i][j] = panel;
				grid.add(panel);
			}
		}
		reset.addActionListener(this);
		exit.addActionListener(this);
		resetAndExit.add(reset);
		resetAndExit.add(exit);
		resetAndExit.add(winner);
		winner.removeAll();
		winner.setVisible(false);
		gridFrame.add(BorderLayout.CENTER, grid);
		gridFrame.add(BorderLayout.SOUTH, resetAndExit);
		gridFrame.setSize(50 * col, 50 * col);
		gridFrame.setLocationRelativeTo(null);
		gridFrame.setVisible(true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void gameStarted(int row, int column) {
		grid(row, column);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void win(Player player) {
		winner.add(new JLabel(ExtractPlayer.getPlayer(player.getColor())
				+ " won!"));
		winner.setVisible(true);
		for (JButton button : columnButtons) {
			button.removeActionListener(this);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void columnSelected(int row, int column, Color c) {
		panelList[row][column].setBackground(c);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void gameReset() {
		grid.removeAll();
		resetAndExit.removeAll();
		gridFrame.dispose();
		columnButtons.clear();
		gameStarted(row, column);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setColumn(int col) {
		this.column = col;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void selectPlayerTypes() {
		isPlayerSet = true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void gameEnded() {
		controller.removeGameObserver(this);
		System.exit(0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == start) {
			try {
				int row = Integer.parseInt(rowsField.getText()) > 0 ? Integer
						.parseInt(rowsField.getText()) : 6;
				int column = Integer.parseInt(colsField.getText()) > 0 ? Integer
						.parseInt(colsField.getText()) : 7;
				controller.fireSetRowColumn(row, column);

			} catch (Exception exc) {
			}
			if (isPlayerSet) {
				startFrame.dispose();
				controller.startGame(row, column);
			}
		}
		if (e.getSource() == exit) {
			controller.endGame();
		}
		if (e.getSource() == reset) {
			controller.gameReset();
		}
		if (e.getSource() == singlePlayer) {
			isPlayerSet = true;
			controller.setPlayer(PlayerType.HUMAN, PlayerType.COMPUTER);
		}
		if (e.getSource() == twoPlayer) {
			isPlayerSet = true;
			controller.setPlayer(PlayerType.HUMAN, PlayerType.HUMAN);
		}
		for (int i = 0; i < columnButtons.size(); i++) {
			if (e.getSource() == columnButtons.get(i)) {
				controller.columnSelected(i);
			}
		}
	}

}
