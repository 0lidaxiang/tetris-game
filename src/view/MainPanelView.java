package view;

import java.awt.*;
import javax.swing.*;

import controller.KeysEvent;

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

	/**
	 * Default constructor
	 */

	public MainPanelView(TablePanel newTp) {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();// get the screen width
		setScreenWidth(d.width);
		setScreenHeight(d.height);

		bindKyListener();// 专门用于绑定键盘事件

		this.setTitle("Tetris Game-SE-15th-HW");
		this.setSize(width, height);
		this.setResizable(false);
		setTp(newTp);
		drawPanel(getTp());
		this.setBounds((getScreenWidth() - width) / 2, (getScreenHeight() - height) / 2, width, height);// set
																										// the
																										// JFrame
																										// location
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	// 专门用于绑定键盘事件
	public void bindKyListener() {
		JButton btn = new JButton();
		this.add(btn);
		btn.addKeyListener(new KeysEvent());
	}

	public void drawPanel(TablePanel tp) {
		Container contentPane = this.getContentPane();
		contentPane.setBackground(Color.BLACK); // 将JFrame实例背景设置为蓝绿色
		contentPane.add(getTp(), BorderLayout.CENTER);

		rightPanel = new JPanel(); // 创建一个JPanel的实例
		rightPanel.setBackground(Color.gray); // 将JPanel的实例背景设置为黄色
		rightPanel.setLayout(new GridLayout(3, 1));
		rightPanelBag = new GridBagConstraints();
		nextOfRightPanel();
		statusOfRightPanel();
		controlOfRightPanel();
		contentPane.add(rightPanel, BorderLayout.EAST); // 将JPanel实例添加到JFrame的南侧
	}

	public void nextOfRightPanel() {
		JPanel nextJPanel = new JPanel();
		nextJPanel.setBackground(Color.gray);
		nextJPanel.setLayout(new GridLayout(2, 2));

		GridBagConstraints bag = new GridBagConstraints();
		bag.gridx = 0;
		bag.gridy = 0;
		bag.gridwidth = 2;
		bag.gridheight = 1;
		bag.weightx = 0;
		bag.weighty = 0;
		bag.fill = GridBagConstraints.BOTH;
		bag.anchor = GridBagConstraints.WEST;
		JLabel newLabel = new JLabel("Next box: ");
		newLabel.setForeground(Color.green);
		nextJPanel.add(newLabel, bag);

		bag.gridx = 1;
		bag.gridy = 1;
		bag.gridwidth = 1;
		bag.gridheight = 1;
		bag.fill = GridBagConstraints.NONE;
		JButton boxButton = new JButton("Next box\n is");
		boxButton.setSize(80, 80);
		nextJPanel.add(boxButton, bag);

		rightPanelBag.gridx = 0;
		rightPanelBag.gridy = 0;
		rightPanelBag.gridwidth = 1;
		rightPanelBag.gridheight = 1;
		rightPanelBag.weightx = 0;
		rightPanelBag.weighty = 0;
		rightPanelBag.fill = GridBagConstraints.BOTH;
		rightPanelBag.anchor = GridBagConstraints.WEST;
		rightPanel.add(nextJPanel, rightPanelBag);
	}

	public void statusOfRightPanel() {
		JPanel statusJPanel = new JPanel();
		statusJPanel.setBackground(Color.gray);
		statusJPanel.setLayout(new GridLayout(3, 2));

		GridBagConstraints bag = new GridBagConstraints();
		bag.gridx = 0;
		bag.gridy = 0;
		bag.gridwidth = 2;
		bag.gridheight = 1;
		bag.weightx = 0;
		bag.weighty = 0;
		bag.fill = GridBagConstraints.BOTH;
		bag.anchor = GridBagConstraints.WEST;
		JLabel newLabel = new JLabel("Status: ");
		newLabel.setForeground(Color.green);
		statusJPanel.add(newLabel, bag);

		bag.gridx = 1;
		bag.gridy = 1;
		bag.gridwidth = 1;
		bag.fill = GridBagConstraints.NONE;
		statusJPanel.add(new JLabel("Level :1"), bag);

		bag.gridx = 1;
		bag.gridy = 2;
		statusJPanel.add(new JLabel("Score :1"), bag);

		rightPanelBag.gridx = 0;
		rightPanelBag.gridy = 1;
		rightPanel.add(statusJPanel, rightPanelBag);
	}

	public void controlOfRightPanel() {
		JPanel controlJPanel = new JPanel();
		controlJPanel.setBackground(Color.gray);
		controlJPanel.setLayout(new GridLayout(7, 2));

		GridBagConstraints bag = new GridBagConstraints();
		bag.gridx = 0;
		bag.gridy = 0;
		bag.gridwidth = 2;
		bag.gridheight = 1;
		bag.weightx = 0;
		bag.weighty = 0;
		bag.fill = GridBagConstraints.BOTH;
		bag.anchor = GridBagConstraints.WEST;
		JLabel newLabel = new JLabel("Control Keys :");
		newLabel.setForeground(Color.green);
		controlJPanel.add(newLabel, bag);

		bag.gridx = 1;
		bag.gridy = 1;
		bag.gridwidth = 1;
		bag.fill = GridBagConstraints.NONE;
		controlJPanel.add(new JLabel("0:Start Game"), bag);

		bag.gridx = 0;
		bag.gridy = 2;
		controlJPanel.add(new JLabel("P:Pause Game"), bag);

		bag.gridx = 0;
		bag.gridy = 3;
		controlJPanel.add(new JLabel("Q:Quit Game"), bag);

		bag.gridx = 0;
		bag.gridy = 4;
		controlJPanel.add(new JLabel("A:Move Left"), bag);

		bag.gridx = 0;
		bag.gridy = 5;
		controlJPanel.add(new JLabel("F:Move Right"), bag);

		bag.gridx = 0;
		bag.gridy = 6;
		controlJPanel.add(new JLabel("D:MOVE Down"), bag);

		bag.gridx = 0;
		bag.gridy = 6;
		controlJPanel.add(new JLabel("E:MOVE Up"), bag);

		rightPanelBag.gridx = 0;
		rightPanelBag.gridy = 2;
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
}