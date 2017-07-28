import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Main {

	private JFrame frmFirst;

	//Launch the application.
	public static void main(String[] args) {
		createWindow();
	}

	public static void createWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmFirst.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Create the application.
	public Main() {
		initialize();
	}

	// Initialize the contents of the frame.
	private void initialize() {
		frmFirst = new JFrame();
		frmFirst.setTitle("Choose Hardness");
		frmFirst.setBounds(500, 250, 450, 300);
		frmFirst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFirst.getContentPane().setLayout(null);

		addEasyButton();

		addMediumButton();

		addHardButton();

		addBackgroundImage();

	}

	private void addEasyButton() {
		JButton btnEasy = new JButton("Easy");
		btnEasy.setForeground(Color.WHITE);
		btnEasy.setBackground(new Color(255, 204, 51));
		btnEasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LevelsForEasy nw = new LevelsForEasy();
				frmFirst.setVisible(false);
				nw.LevelWindow();
			}
		});
		btnEasy.setBounds(10, 98, 89, 23);
		frmFirst.getContentPane().add(btnEasy);

	}

	private void addMediumButton() {
		JButton btnMedium = new JButton("Medium");
		btnMedium.setForeground(Color.WHITE);
		btnMedium.setBackground(new Color(255, 204, 51));
		btnMedium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LevelsForMedium levels = new LevelsForMedium();
				frmFirst.setVisible(false);
				levels.LevelWindow();
			}
		});
		btnMedium.setBounds(180, 98, 89, 23);
		frmFirst.getContentPane().add(btnMedium);
	}

	private void addHardButton() {
		JButton btnHard = new JButton("Hard");
		btnHard.setForeground(Color.WHITE);
		btnHard.setBackground(new Color(255, 204, 51));
		btnHard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LevelsForHard levels = new LevelsForHard();
				frmFirst.setVisible(false);
				levels.LevelWindow();
			}
		});
		btnHard.setBounds(335, 98, 89, 23);
		frmFirst.getContentPane().add(btnHard);
	}

	private void addBackgroundImage() {
		JLabel label = new JLabel(" ");
		label.setIcon(new ImageIcon("windowItems\\frstBg.jpg"));
		label.setBounds(0, -13, 319, 196);
		frmFirst.getContentPane().add(label);

		JLabel label_1 = new JLabel(" ");
		label_1.setIcon(new ImageIcon("windowItems\\frstBg.jpg"));
		label_1.setBounds(152, 77, 282, 196);
		frmFirst.getContentPane().add(label_1);

		JLabel label_2 = new JLabel(" ");
		label_2.setIcon(new ImageIcon("windowItems\\frstBg.jpg"));
		label_2.setBounds(0, 168, 153, 105);
		frmFirst.getContentPane().add(label_2);

		JLabel label_3 = new JLabel(" ");
		label_3.setBounds(254, 78, 153, 105);
		frmFirst.getContentPane().add(label_3);

		JLabel label_4 = new JLabel(" ");
		label_4.setIcon(new ImageIcon("windowItems\\frstBg.jpg"));
		label_4.setBounds(281, 0, 153, 105);
		frmFirst.getContentPane().add(label_4);
	}
}