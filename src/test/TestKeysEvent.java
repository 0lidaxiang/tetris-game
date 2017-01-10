package test;

import controller.KeysEvent;
import controller.TetrisController;
import junit.framework.TestCase;

public class TestKeysEvent extends TestCase {

	public static KeysEvent keysEvent = new KeysEvent();

	public void startGameTest() {
		keysEvent.startGame();
		assertEquals(false, TetrisController.isPause);
	}

	public void exitGameTest() {
		keysEvent.exitGame();
		assertEquals(1, KeysEvent.flag);
	}

	public void pauseGameTest() {
		keysEvent.pauseGame();
		assertEquals(true, TetrisController.isPause);
	}

	public void reStartRoundTest() {
		keysEvent.reStartRound();
		assertEquals(true, TetrisController.isStart);
		assertEquals(true, TetrisController.isPause);
	}

	public void quitRoundTest() {
		keysEvent.quitRound();
		assertEquals(false, TetrisController.isStart);
	}

	public void moveLeftBoxTest() {
		keysEvent.moveLeftBox();
		assertEquals(2, KeysEvent.flag);
	}

	public void moveRightBoxTest() {
		keysEvent.moveRightBox();
		assertEquals(3, KeysEvent.flag);
	}

	public void clockwiseBoxTest() {
		keysEvent.clockwiseBox();
		assertEquals(4, KeysEvent.flag);
	}

	public void counterclockwiseBoxTest() {
		keysEvent.counterclockwiseBox();
		System.out.println(KeysEvent.flag);
		// assertEquals(5, KeysEvent.flag);
	}

	public void recoverSpeedTest() {
		keysEvent.recoverSpeed();
		assertEquals(1100, TetrisController.sleepTime);
	}

}
