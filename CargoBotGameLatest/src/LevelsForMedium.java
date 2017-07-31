import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class LevelsForMedium {

	private static JFrame frmLevels;
	public LevelReader lr;

	/**
	 * Launch the application.
	 */
	public static void LevelWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmLevels.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LevelsForMedium() {
		lr = new LevelReader();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		createMainFrame();
		createLevel1();
		createLevel2();
		createLevel3();
		createLevel4();
		createLevel5();
		createBackHardness();
		createLevelEditor();
		setupBackground();
	}

	private void createMainFrame() {
		frmLevels = new JFrame();
		frmLevels.setTitle("Levels");
		frmLevels.setBounds(500, 250, 440, 300);							//the length is 240 for 8*30 (7 for boxes 1 for crane) + 10 space between crane and top box =250
		frmLevels.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLevels.getContentPane().setLayout(null);
	}

	private void createLevel1() {
		JButton btnLevel = new JButton("Level1");
		btnLevel.setForeground(Color.WHITE);
		btnLevel.setBackground(Color.ORANGE);
		btnLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					lr.read("lvlM1.cb");
					Game game = new Game(CommandReader.functions);
					frmLevels.setVisible(false);
					game.startGame();

				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnLevel.setBounds(162, 34, 89, 23);
		frmLevels.getContentPane().add(btnLevel);
	}

	private void createLevel2() {
		JButton btnLevel_1 = new JButton("Level2");
		btnLevel_1.setForeground(Color.WHITE);
		btnLevel_1.setBackground(Color.ORANGE);
		btnLevel_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					lr.read("lvlM2.cb");
					Game game = new Game(CommandReader.functions);
					frmLevels.setVisible(false);
					game.startGame();

				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnLevel_1.setBounds(162, 68, 89, 23);
		frmLevels.getContentPane().add(btnLevel_1);
	}

	private void createLevel3() {
		JButton btnLevel_2 = new JButton("Level3");
		btnLevel_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					lr.read("lvlM3.cb");
					Game game = new Game(CommandReader.functions);
					frmLevels.setVisible(false);
					game.startGame();

				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnLevel_2.setForeground(Color.WHITE);
		btnLevel_2.setBackground(Color.ORANGE);
		btnLevel_2.setBounds(162, 102, 89, 23);
		frmLevels.getContentPane().add(btnLevel_2);
	}

	private void createLevel4() {
		JButton btnLevel_3 = new JButton("Level4");
		btnLevel_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					lr.read("lvlM4.cb");
					Game game = new Game(CommandReader.functions);
					frmLevels.setVisible(false);
					game.startGame();

				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnLevel_3.setForeground(Color.WHITE);
		btnLevel_3.setBackground(Color.ORANGE);
		btnLevel_3.setBounds(162, 136, 89, 23);
		frmLevels.getContentPane().add(btnLevel_3);
	}

	private void createLevel5() {
		JButton btnLevel_4 = new JButton("Level5");
		btnLevel_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					lr.read("lvlM5.cb");
					Game game = new Game(CommandReader.functions);
					frmLevels.setVisible(false);
					game.startGame();

				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnLevel_4.setForeground(Color.WHITE);
		btnLevel_4.setBackground(Color.ORANGE);
		btnLevel_4.setBounds(162, 170, 89, 23);
		frmLevels.getContentPane().add(btnLevel_4);
	}

	private void createBackHardness() {
		JButton btnBacktohardness = new JButton("BackToHardnessChoice");
		btnBacktohardness.setForeground(Color.WHITE);
		btnBacktohardness.setBackground(Color.LIGHT_GRAY);
		btnBacktohardness.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main nw = new Main();
				frmLevels.setVisible(false);
				nw.createWindow();
			}
		});
		btnBacktohardness.setBounds(0, 11, 143, 23);
		frmLevels.getContentPane().add(btnBacktohardness);
	}

	private void createLevelEditor(){

		JButton btnLevelEditor = new JButton("Level Editor");
		btnLevelEditor.setBackground(Color.LIGHT_GRAY);
		btnLevelEditor.setForeground(Color.WHITE);
		btnLevelEditor.setBounds(300, 11, 140, 23);
		frmLevels.getContentPane().add(btnLevelEditor);

	}
	
	private void setupBackground() {
		JLabel label = new JLabel(" ");
		label.setIcon(new ImageIcon("windowItems\\levelBg.jpg"));
		label.setBounds(-42, 0, 466, 268);
		frmLevels.getContentPane().add(label);
	}


}
