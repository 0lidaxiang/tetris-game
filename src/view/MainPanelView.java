package view;

import java.awt.*;
import javax.swing.*;

import controller.KeysEvent;
import controller.TetrisController;

/**
 *
 */
public class MainPanelView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel rightPanel;
	private GridBagConstraints rightPanelBag;
	private int width = 499;
	private int height = 606;
	private int screenWidth;
	private int screenHeight;
	private TablePanel tp;
	private JLabel socreLabel;
	private JLabel gameStartLabel;
	private JLabel gameOverLabel;

	public MainPanelView(TablePanel newTp) {
		// get the screen width
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setScreenWidth(d.width);
		setScreenHeight(d.height);

		bindKyListener();// 专门用于绑定键盘事件

		this.setTitle("Tetris Game-SEGroup15th");
		this.setSize(width, height);
		this.setResizable(false);
		setTp(newTp);
		drawPanel();
		this.setBounds((getScreenWidth() - width) / 2, (getScreenHeight() - height) / 2, width, height);// set the JFrame location
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	// 专门用于绑定键盘事件
	public void bindKyListener() {
		JButton btn = new JButton();
		this.add(btn);
		btn.addKeyListener(new KeysEvent());
	}

	public void drawPanel() {
		Container contentPane = this.getContentPane();
		contentPane.setBackground(Color.BLACK); // 将JFrame实例背景设置为蓝绿色
		contentPane.add(getTp(), BorderLayout.CENTER);

		rightPanel = new JPanel(); // 创建一个JPanel的实例
		rightPanel.setBackground(Color.gray); // 将JPanel的实例背景设置为黄色
		rightPanel.setLayout(new GridLayout(2, 1));
		rightPanelBag = new GridBagConstraints();

		controlOfRightPanel();
		statusOfRightPanel();

		contentPane.add(rightPanel, BorderLayout.EAST); // 将JPanel实例添加到JFrame的东侧
	}

	public void statusOfRightPanel() {
		JPanel statusJPanel = new JPanel();
		statusJPanel.setBackground(Color.gray);
		statusJPanel.setLayout(new GridLayout(12, 1));

		GridBagConstraints bag = new GridBagConstraints();
		bag.fill = GridBagConstraints.BOTH;
		bag.anchor = GridBagConstraints.WEST;
		JLabel statusLabel = new JLabel("Status: ");
		statusLabel.setForeground(Color.green);
		statusJPanel.add(statusLabel, bag);
		
		socreLabel = new JLabel("\t  \t  \t  \t  \t Score : \t" + TetrisController.getScore());
		statusJPanel.add(socreLabel, bag);

		statusJPanel.add(new JLabel("\t  \t  \t  \t  \t (Remove one line,"), bag);
		statusJPanel.add(new JLabel("\t  \t  \t  \t  \t you get 10 points)"), bag);

		rightPanelBag.gridx = 0;
		rightPanelBag.gridy = 1;
		rightPanel.add(statusJPanel, rightPanelBag);
	}

	public void controlOfRightPanel() {
		JPanel controlJPanel = new JPanel();
		controlJPanel.setBackground(Color.gray);
		controlJPanel.setLayout(new GridLayout(15, 1));

		GridBagConstraints bag = new GridBagConstraints();
		bag.fill = GridBagConstraints.BOTH;
		bag.anchor = GridBagConstraints.WEST;

		controlJPanel.add(new JLabel(), bag);

		JLabel newLabel = new JLabel("Control Keys :");
		newLabel.setForeground(Color.green);
		controlJPanel.add(newLabel, bag);

		controlJPanel.add(new JLabel("\t  \t  \t  \t  \t S:Start Game"), bag);

		controlJPanel.add(new JLabel("\t  \t  \t  \t  \t P:Pause Game"), bag);
		
		controlJPanel.add(new JLabel("\t  \t  \t  \t  \t E:Exit Round"), bag);
		
		controlJPanel.add(new JLabel("\t  \t  \t  \t  \t R:Restart Game"), bag);

		controlJPanel.add(new JLabel("\t  \t  \t  \t  \t Q:Quit Round"), bag);
		
		controlJPanel.add(new Label(), bag);

		controlJPanel.add(new JLabel("\t  \t  \t  \t  \t A:Move Fast"), bag);

		controlJPanel.add(new JLabel("\t  \t  \t  \t  \t B:Move Left"), bag);

		controlJPanel.add(new JLabel("\t  \t  \t  \t  \t M:Move Right"), bag);

		controlJPanel.add(new JLabel("\t  \t  \t  \t  \t H:Clockwise"), bag);

		controlJPanel.add(new JLabel("\t  \t  \t  \t  \t N:Counterclockwise \t  \t  \t  \t  \t"), bag);


		rightPanelBag.gridx = 0;
		rightPanelBag.gridy = 1;
		rightPanel.add(controlJPanel, rightPanelBag);
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	public TablePanel getTp() {
		return tp;
	}

	public void setTp(TablePanel newTp) {
		this.tp = newTp;
	}
	
	public JLabel getSocreLabel() {
		return socreLabel;
	}

	public void setSocreLabel(JLabel socreLabel) {
		this.socreLabel = socreLabel;
	}

	public JLabel getGameStartLabel() {
		return gameStartLabel;
	}

	public void setGameStartLabel(String str) {
		this.gameStartLabel = new JLabel(str
				, JLabel.CENTER);
		this.gameStartLabel.setFont(new Font("Dialog", 1, 20));
		this.gameStartLabel.setForeground(Color.GREEN);
	}

	public JLabel getGameOverLabel() {
		return gameOverLabel;
	}

	public void setGameOverLabel(int socre) {
		this.gameOverLabel = new JLabel(
				"<html><p>Game Over !</p><br><p>Your score is   " + socre + " ! </p></html>", JLabel.CENTER);
		this.gameOverLabel.setFont(new Font("Dialog", 1, 20));
		this.gameOverLabel.setForeground(Color.GREEN);
	}
}