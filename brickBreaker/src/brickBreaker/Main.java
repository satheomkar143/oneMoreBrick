package brickBreaker;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame fm = new JFrame();
		GamePlay gamePlay = new GamePlay();
		fm.setBounds(10, 10, 700, 600);
		fm.setTitle("One More Brick");
		//fm.setResizable(false);
		fm.setVisible(true);
		fm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		fm.add(gamePlay);

	}
}
