package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

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
	static MainPanelView mainJFrame;
	private static boolean isStart;
	private static int sleepTime;
	private static boolean isMoveCols;
	private int initialX, initialY;

	public TetrisController() {
		setSleepTime(2000);// 初始化程序睡眠时间，用户看到的就是方块移动速度，值越大，速度越慢
	}

	public static void main(String[] args) {
		TetrisController tetris = new TetrisController();
		tetris.start();
	}

	public void start() {
		setIsStart(true);// for test,表示可以移动方块了，也就是游戏开始
		// setIsStart(false);// 表示初始状态时不可以移动方块了，直到按‘S’才启动
		MainPanelView mainPanelView = new MainPanelView(createTp());
		setMainJFrame(mainPanelView);// 画面板和其他部分

		// 进入玩游戏状态并监听键盘事件
		while (true) {
			// 判断是否处于横向移动状态
			if (this.isMoveCols()) {
				TetrisController.setMoveCols(false);
			} else {
				if (getIsStart()) {
					// 检查是否有满足一行，若有则记录。最后消除
					removeLines();

					mainJFrame.getTp().setGameStatus(0);// 更改tablePanel中的状态，表示可以显示方块了而不是显示文字状态

					// 检查是否可以移动
					if (mainJFrame.getTp().getBox().getStatus() == 1) {
						canMove();// 如果可以移动，则每次移动一个格子。
					} else {
						mainJFrame.getTp().setBox(createRandomBoxModel());// 如果box被锁定不能移动，说明需要更新box状态了。
						continue;
					}
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
		
		//如果发现有某几行满了
		if (!removeedRows.isEmpty()) {
			moveLines(removeedRows);
		}
	}
	
	// 检查和记录当前所有的满足一行的rows值
	public void checkFlag(ArrayList<Integer> removeedRows){
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
	
	public void moveLines(ArrayList<Integer> removeedRows) {
		// 标记被消除的行flag为0
		for (int m = 0; m < removeedRows.size(); m++) {
			for (int i = 0; i < 13; i++) {
				if (mainJFrame.getTp().getPositioinFlag()[i][removeedRows.get(m)][0] == 1) {
					mainJFrame.getTp().getPositioinFlag()[i][removeedRows.get(m)][0] = 0;
				}
			}
		}
		
		// 往下移动所有没有被消除的方格的坐标
		for (int j = 0; j < 23; j++) {
			for (int i = 0; i < 13; i++) {
				if (mainJFrame.getTp().getPositioinFlag()[i][j][0] == 1) {
					mainJFrame.getTp().getPositioinFlag()[i][j + removeedRows.size()][0] = 1;
				}
			}
		}
	}

	public void canMove() {
		// 先把下一个位置坐标给他，设置好
		// mainJFrame.getTp().getBox().setX(mainJFrame.getTp().getBox().getNextX());
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
		// tp.setBox(new RightZBox());

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
			newBox = this.createTBox();
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

	public static boolean getIsStart() {
		return isStart;
	}

	public static void setIsStart(boolean flag) {
		isStart = flag;
	}

	public boolean isMoveCols() {
		return isMoveCols;
	}

	public static void setMoveCols(boolean isMoveCols) {
		TetrisController.isMoveCols = isMoveCols;
	}

}