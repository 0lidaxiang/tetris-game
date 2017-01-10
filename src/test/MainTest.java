package test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class MainTest {
	public ModelTest modelTest = new ModelTest();
	public static TestKeysEvent keysEvent = new TestKeysEvent();

	@BeforeClass
	public static void before() {
		System.out.println("====================================begain=====================================");
	}

	/*
	 * Test Function
	 */
	@Test
	public void startGameTest() {
		keysEvent.startGameTest();
	}


	@Test
	public void pauseGameTest() {
		keysEvent.pauseGameTest();
	}

	@Test
	public void quitRoundTest() {
		keysEvent.quitRoundTest();
	}

	@Test
	public void recoverSpeedTest() {
		keysEvent.recoverSpeedTest();
	}

	/*
	 * Test Model
	 */

	@Test
	public void EBoxUpdate90NextXYTest() {
		modelTest.update90NextXYTest();
	}

	@Test
	public void LeftLBoxUpdate180NextXYTest() {
		modelTest.update180NextXYTest();
	}

	@Test
	public void IBoxUpdate270NextXYTest() {
		modelTest.update270NextXYTest();
	}

	@Test
	public void rightZBoxUpdate360NextXYTest() {
		modelTest.update360NextXYTest();
	}

	@AfterClass
	public static void end() {
		System.out.println("====================================end thk====================================");
	}

}
