package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeysEvent implements KeyListener {
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		//长时间按住键
		String userInput = "" + e.getKeyChar();
		if (userInput.equals("S")) {
			TetrisController.setSleepTime(300);
			// System.out.println("按住S" + TetrisController.getSleepTime() +
			// "\n");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//松开某个键
		String userInput = "" + e.getKeyChar();

		switch (userInput) {
		case "0":
			startGame();
			break;
		case "P":
			pauseGame();
			break;
		case "Q":
			quitGame();
			break;
		case "A":
			moveLeftBox();
			break;
		case "F":
			moveRightBox();
			break;
		case "D":
			rotateLeftBox();
			break;
		case "E":
			rotateRightBox();
			break;
		case "S":
			dropFast();
			break;
		default:
			break;
		}

//		System.out.println("userInput" + userInput);
//		System.out.println(TetrisController.getSleepTime());
	}

	private void dropFast() {
		// 松开按键"S"后恢复原速度
		TetrisController.setSleepTime(2500);
//		System.out.println("松开S  " + "\n" + +TetrisController.getSleepTime());
	}

	public void startGame() {
		// 修改tetriscontroller里面的一个控制开关，让游戏的方块可以更改坐标
		TetrisController.setIsMove(true);
	}

	public void pauseGame() {

	}

	public void quitGame() {
		System.exit(0);
	}

	public void moveLeftBox() {

	}

	public void moveRightBox() {

	}

	public void rotateLeftBox() {

	}

	public void rotateRightBox() {

	}
	
}
