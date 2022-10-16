package brickBreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlay extends JPanel implements KeyListener, ActionListener {

	private boolean play = false;

	private int score = 0;

	private int totalBricks = 200;

	private Timer timer;
	private int delay = 8;

	private int playerX = 310;

	private int ballposX = 120;
	private int ballposY = 350;
	private int ballXdir = -1;
	private int ballYdir = -2;

	private MapGenerator map;

	public GamePlay() {
		map = new MapGenerator(10, 20);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}

	public void paint(Graphics g) {

//		setBackground of game panel
		g.setColor(Color.white);
		g.fillRect(1, 1, 680, 560);

		map.draw((Graphics2D) g);

//		set border of screen
		g.setColor(Color.blue);
		g.fillRect(0, 0, 3, 560); // left
		g.fillRect(0, 0, 680, 3); // top
		g.fillRect(680, 0, 3, 560); // right

//		set padel attributes
		g.setColor(Color.blue);
		// g.fillRect(playerX, 540, 100, 8);
		g.fillRoundRect(playerX, 540, 100, 15, 15, 15);

//		set ball attributes
		g.setColor(Color.green);
		g.fillOval(ballposX, ballposY, 20, 20);

		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		timer.start();

		// ball-padel interaction
		if (play) {
			if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 540, 100, 8))) {
				ballYdir = -ballYdir;
			}

			for (int i = 0; i < map.map.length; i++) {
				for (int j = 0; j < map.map[0].length; j++) {
					if (map.map[i][j] > 0) {
						int brickX = j * map.brickwidth + 75;
						int brickY = i * map.brickHeight + 50;
						int brickWidth = map.brickwidth;
						int brickHeight = map.brickHeight;

						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle brickRect = rect;

						if (ballRect.intersects(brickRect)) {
							map.setBrickValue(0, i, j);
							totalBricks--;
							score += 5;

							if (ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width) {
								ballXdir = -ballXdir;
							} else {
								ballYdir = -ballYdir;
							}
						}
					}
				}
			}

			ballposX += ballXdir;
			ballposY += ballYdir;
			if (ballposX < 0) {
				ballXdir = -ballXdir;
			}
			if (ballposY < 0) {
				ballYdir = -ballYdir;
			}
			if (ballposX > 660) {
				ballXdir = -ballXdir;
			}
		}
		repaint();

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {

		// move padel to right

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (playerX >= 600) {
				playerX = 600;
			} else {
				moveRight();
			}
		}

		// move padel to left

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (playerX < 10) {
				playerX = 10;
			} else {
				moveLeft();
			}
		}
	}

	public void moveRight() {
		play = true;
		playerX += 20;
	}

	public void moveLeft() {
		play = true;
		playerX -= 20;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
