package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import model.BoxModel;
import model.EBox;
import model.IBox;
import model.LeftLBox;
import model.LeftZBox;
import model.RightLBox;
import model.RightZBox;
import model.TBox;
import view.MainPanelView;
import view.TablePanel;

/**
 *
 */
public class TetrisController {
	public static TetrisController tetris;
	public static MainPanelView mainJFrame;
	private static boolean isStart;// 控制游戏是否在本轮继续还是重新开一轮
	private static boolean isPause;// 控制游戏是否处于暂停状态
	private static int sleepTime;
	private static boolean isMoveCols;// 判定积木是否横向移动
	private int initialX, initialY; //
	private static int score; // 用户在本轮中的得分

	public TetrisController() {
		this.setScore(0);
		setSleepTime(1100);// 初始化程序睡眠时间，用户看到的就是方块移动速度，值越大，速度越慢
	}

	public static void main(String[] args) {
		tetris = new TetrisController();
		tetris.start();
	}

	public void start() {
		setStart(true);
		setPause(true); // 刚打开游戏，第一轮处于暂停状态
		setScore(0);
		MainPanelView mainPanelView = new MainPanelView(createTp());
		setMainJFrame(mainPanelView);// 画面板和其他部分

		// 进入玩游戏状态并监听键盘事件
		while (true) {
			// 判断是否处于横向移动状态
			if (this.isMoveCols()) {
				TetrisController.setMoveCols(false);
			} else {
				// 判断是否开始游戏状态
				if (isStart()) {
					if (!isPause()) {
//						mainJFrame.remove(mainJFrame.getGameStartLabel());//移除暂停状态的那个文字显示
//						mainJFrame.getGameStartLabel().setVisible(false);//移除暂停状态的那个文字显示
						
						removeLines();// 检查是否有满足一行，若有则记录。最后消除
						checkIsOver();// 检查是否下一个位置中有被标记的方格，有则终止程序

						mainJFrame.getTp().setGameStatus(0);// 更改tablePanel中的状态，表示可以显示方块了而不是显示文字状态

						// 检查是否可以移动
						if (mainJFrame.getTp().getBox().getStatus() == 1) {
							canMove();// 如果可以移动，则每次移动一个格子。
						} else {
							mainJFrame.getTp().setBox(createRandomBoxModel());// 如果box被锁定不能移动，说明需要更新box状态了。
							continue;
						}
					}
//					else {
					//控制游戏处于暂停时显示文字
//						mainJFrame.setGameStartLabel("<html><p>Game Pausing!</p><br><p>Press 'S' to start.</p></html>");
//						mainJFrame.add(mainJFrame.getGameStartLabel());
//						mainJFrame.setVisible(true);// 必须有，要不然会报错
//					}
				} else {
					mainJFrame.getTp().setVisible(false);
					mainJFrame.setGameOverLabel(getScore());
					mainJFrame.add(mainJFrame.getGameOverLabel());
					mainJFrame.remove(mainJFrame.getTp());
					mainJFrame.setVisible(true);
				}
			}

			mainJFrame.repaint();// 不断flush画面

			// 重画一次之后就睡眠，user看到的就是方块移动的速度。因为 程序sleep时方块停止移动
			try {
				Thread.sleep(getSleepTime());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 消除一行
	public void removeLines() {
		ArrayList<Integer> removeedRows = new ArrayList<Integer>();
		checkFlag(removeedRows);

		// 如果发现有某几行满了
		if (!removeedRows.isEmpty()) {
			moveLines(removeedRows);
		}

		// 最后清空该数组，等待下次消除
		removeedRows.clear();
	}

	// 检查和记录当前所有的满足一行的rows值
	public void checkFlag(ArrayList<Integer> removeedRows) {
		boolean f = true;

		for (int j = 0; j < 23; j++) {
			for (int i = 0; i < 13; i++) {
				if (mainJFrame.getTp().getPositioinFlag()[i][j][0] == 0) {
					f = false;
				}
			}
			if (f) {
				removeedRows.add(j);
			} else {
				f = true;
			}
		}
	}

	// 1标记被消除的行flag为0
	// 2往下移动所有没有被消除的方格的坐标
	public void moveLines(ArrayList<Integer> removeedRows) {
		// 标记被消除的行的所有方格flag为0
		for (int m = 0; m < removeedRows.size(); m++) {
			for (int i = 0; i < 13; i++) {
				mainJFrame.getTp().getPositioinFlag()[i][removeedRows.get(m)][0] = 0;
			}
		}

		// 往下移动所有没有被消除的方格的坐标
		// 所有被消除的行开始往上
		for (int m = removeedRows.size() - 1; m >= 0; m--) {
			for (int j = removeedRows.get(m); j >= 0; j--) {
				for (int i = 0; i < 13; i++) {
					if (mainJFrame.getTp().getPositioinFlag()[i][j][0] == 1) {
						mainJFrame.getTp().getPositioinFlag()[i][j][0] = 0;
						mainJFrame.getTp().getPositioinFlag()[i][j + removeedRows.size()][0] = 1;
					}
				}
			}
		}

		// 记录得分
		this.setScore(getScore() + 10);
		// 重新写入显示的分数
		mainJFrame.getSocreLabel().setText("\t  \t  \t  \t  \t Score : \t" + getScore());
		;// 不断flush画面
	}

	// 判断是否触顶
	public void checkIsOver() {
		int nextX;
		int nextY;

		if (mainJFrame.getTp().getBox().getY()[0] == 0) {
			for (int m = 0; m < 4; m++) {
				nextX = mainJFrame.getTp().getBox().getNextX()[m];
				nextY = mainJFrame.getTp().getBox().getNextY()[m];

				if (mainJFrame.getTp().getPositioinFlag()[nextX][nextY][0] == 1) {
					setStart(false);
				}
			}
		}
	}

	// main中start()调用，移动
	public void canMove() {
		// 先把下一个位置坐标给他，设置好
		mainJFrame.getTp().getBox().setY(mainJFrame.getTp().getBox().getNextY());

		int[] tempX = mainJFrame.getTp().getBox().getX();
		int[] tempY = mainJFrame.getTp().getBox().getY();
		// 再更新下一个位置的下一个坐标
		mainJFrame.getTp().getBox().updateNextXY();

		// 移动过程中会判断是否box触底以及 判断下方是否有积木
		int nextX;
		int nextY;
		// 如果这个方格被标记，则开始判断是否在当前积木的下一个位置中
		for (int m = 0; m < 4; m++) {
			nextX = mainJFrame.getTp().getBox().getNextX()[m];
			nextY = mainJFrame.getTp().getBox().getNextY()[m];

			if (nextX < 0) {
				nextX = 0;
			}

			if (nextX > 12) {
				nextX = 12;
			}

			if ((mainJFrame.getTp().getPositioinFlag()[nextX][nextY][0] == 1)) {
				mainJFrame.getTp().getBox().setStatus(0);
				for (int n = 0; n < 4; n++) {
					mainJFrame.getTp().updatePositioinFlag(tempX[n], tempY[n], 0, 1);// 对该格子标记要画颜色

					// 计算当前积木的颜色对应colorCode
					Color c = mainJFrame.getTp().getBox().getBaseColor();
					int colorCode = 0;
					if (c.getRGB() == Color.BLUE.getRGB()) {
						colorCode = 0;
					}
					if (c.getRGB() == Color.RED.getRGB()) {
						colorCode = 1;
					}
					if (c.getRGB() == Color.YELLOW.getRGB()) {
						colorCode = 2;
					}
					if (c.getRGB() == Color.GREEN.getRGB()) {
						colorCode = 3;
					}
					if (c.getRGB() == Color.PINK.getRGB()) {
						colorCode = 4;
					}

					mainJFrame.getTp().updatePositioinFlag(tempX[n], tempY[n], 1, colorCode);// 要画的格子的颜色,0代表蓝色
				}
				break;
			}
		}
	}

	public TablePanel createTp() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		TablePanel tp = new TablePanel(200, 400, d.width, d.height, 2);
		tp.setBox(createRandomBoxModel());

		return tp;
	}

	public BoxModel createRandomBoxModel() {
		BoxModel newBox;
		int random = (int) (Math.random() * 1000) % 7;

		switch (random) {
		case 0:
			newBox = this.createEBox();
			break;
		case 1:
			newBox = this.createIBox();
			break;
		case 2:
			newBox = this.createTBox();
			break;
		case 3:
			newBox = this.createLeftLBox();
			break;
		case 4:
			newBox = this.createRightLBox();
			break;
		case 5:
			newBox = this.createLeftZBox();
			break;
		case 6:
			newBox = this.createRightZBox();
			break;
		default:
			newBox = this.createIBox();
			break;
		}
		return newBox;
	}

	// 新建一个田字积木并初始化参数
	private BoxModel createEBox() {
		BoxModel newBox = new EBox();
		newBox.updateNextXY();
		newBox.setStatus(1);
		return newBox;
	}

	// 新建一个I积木并初始化参数
	private BoxModel createIBox() {
		BoxModel newBox = new IBox();
		newBox.updateNextXY();
		newBox.setStatus(1);
		return newBox;
	}

	// 新建一个Z积木并初始化参数
	private BoxModel createTBox() {
		BoxModel newBox = new TBox();
		newBox.updateNextXY();
		newBox.setStatus(1);
		return newBox;
	}

	// 新建一个LeftL积木并初始化参数
	private BoxModel createLeftLBox() {
		BoxModel newBox = new LeftLBox();
		newBox.updateNextXY();
		newBox.setStatus(1);
		return newBox;
	}

	// 新建一个RightL积木并初始化参数
	private BoxModel createRightLBox() {
		BoxModel newBox = new RightLBox();
		newBox.updateNextXY();
		newBox.setStatus(1);
		return newBox;
	}

	// 新建一个LeftZ积木并初始化参数
	private BoxModel createLeftZBox() {
		BoxModel newBox = new LeftZBox();
		newBox.updateNextXY();
		newBox.setStatus(1);
		return newBox;
	}

	// 新建一个RightZ积木并初始化参数
	private BoxModel createRightZBox() {
		BoxModel newBox = new RightZBox();
		newBox.updateNextXY();
		newBox.setStatus(1);
		return newBox;
	}

	public void setBoxInitialXY(int initialX, int initialY) {
		this.initialX = initialX;
		this.initialY = initialY;
	}

	public static MainPanelView getMainJFrame() {
		return mainJFrame;
	}

	public static void setMainJFrame(MainPanelView mainJFrame) {
		TetrisController.mainJFrame = mainJFrame;
	}

	public int getInitialX() {
		return initialX;
	}

	public void setInitialX(int initialX) {
		this.initialX = initialX;
	}

	public int getInitialY() {
		return initialY;
	}

	public void setInitialY(int initialY) {
		this.initialY = initialY;
	}

	public static int getSleepTime() {
		return sleepTime;
	}

	public static void setSleepTime(int sleepTime) {
		TetrisController.sleepTime = sleepTime;
	}

	public static boolean isStart() {
		return isStart;
	}

	public static void setStart(boolean flag) {
		isStart = flag;
	}

	public static boolean isPause() {
		return isPause;
	}

	public static void setPause(boolean isPause) {
		TetrisController.isPause = isPause;
	}

	public boolean isMoveCols() {
		return isMoveCols;
	}

	public static void setMoveCols(boolean isMoveCols) {
		TetrisController.isMoveCols = isMoveCols;
	}

	public static int getScore() {
		return score;
	}

	public void setScore(int score) {
		TetrisController.score = score;
	}

}