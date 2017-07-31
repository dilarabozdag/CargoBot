import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.TransferHandler;

public class Game{
	private static JFrame frmCargobotgame;
	public Board board = LevelReader.board;
	Game game;
	public CommandReader cr;
	private HashMap<String, Function> functions;
	public static ArrayList<String[]> commandArrayArrayList = new ArrayList<String[]>();
	Crane crane ;
	public static BufferedImage craneImage;

	private String[] commandElement1;
	private String[] commandElement2;
	private String[] commandElement3;
	private String[] commandElement4;

	JTextField mainCommands;
	JTextField userCommands2;
	JTextField userCommands3; 
	JTextField userCommands4;

	/**
	 * Launch the application.
	 * @throws IOException
	 */
	public static void startGame() throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				frmCargobotgame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Game(HashMap<String, Function> functions) {
		cr = new CommandReader();
		this.functions = functions;
		game=this;
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		createMainFrame();

		try {
			createTargetBoardP();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			addCraneImage();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			createCurrentBoardP();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		create0Button();
		create1Button();
		create2Button();
		create3Button();
		
		createLeftButton();
		createDownButton();
		createRightButton();
		
		createRedButton();
		createYellowButton();
		createBlueButton();
		createGreenButton();

		createTextField1();
		createTextField2();
		createTextField3();
		createTextField4();

		createRunButton();
	}

	private void createMainFrame() {
		frmCargobotgame = new JFrame();
		frmCargobotgame.getContentPane().setBackground(Color.pink);
		frmCargobotgame.getContentPane().setLayout(null);
		frmCargobotgame.setTitle("CargoBotGame");
		frmCargobotgame.setBounds(450, 100, 650, 700);
		frmCargobotgame.setVisible(true);
		frmCargobotgame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void createTargetBoardP() throws IOException {
		final BufferedImage targetBg = ImageIO.read(new File("windowItems\\targetBg.jpg"));


		final BufferedImage bluebox = ImageIO.read(new File("images\\bluebox.png"));
		final BufferedImage redbox = ImageIO.read(new File("images\\redbox.png"));
		final BufferedImage greenbox = ImageIO.read(new File("images\\greenbox.png"));
		final BufferedImage yellowbox = ImageIO.read(new File("images\\yellowbox.png"));

		JPanel targetBoard = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(targetBg, 0, 0, 650, 250,null);
				
				for (int i = 0; i < LevelReader.targetBoxes.size(); i++) {
					if(LevelReader.targetBoxes.get(i).boxName.equals("bluebox")){
						g.drawImage(bluebox,LevelReader.targetBoxes.get(i).xPosition , LevelReader.targetBoxes.get(i).yPosition, null);
					} else if(LevelReader.targetBoxes.get(i).boxName.equals("redbox")){
						g.drawImage(redbox,LevelReader.targetBoxes.get(i).xPosition , LevelReader.targetBoxes.get(i).yPosition, null);
					} else if(LevelReader.targetBoxes.get(i).boxName.equals("greenbox")){
						g.drawImage(greenbox,LevelReader.targetBoxes.get(i).xPosition , LevelReader.targetBoxes.get(i).yPosition, null);
					}else if(LevelReader.targetBoxes.get(i).boxName.equals("yellowbox")){
						g.drawImage(yellowbox,LevelReader.targetBoxes.get(i).xPosition , LevelReader.targetBoxes.get(i).yPosition, null);
					}
				}
			}
		};
		targetBoard.setBounds(0, 0, 650, 250);
		targetBoard.setBackground(new Color(204, 204, 255));

		frmCargobotgame.getContentPane().add(targetBoard);
	}

	private void addCraneImage() throws IOException {
		craneImage = ImageIO.read(new File("images\\crane.png"));
		crane = new Crane(craneImage);
	}
	
	public  void createCurrentBoardP() throws IOException {
		
		
		final BufferedImage bluebox = ImageIO.read(new File("images\\bluebox.png"));
		final BufferedImage redbox = ImageIO.read(new File("images\\redbox.png"));
		final BufferedImage greenbox = ImageIO.read(new File("images\\greenbox.png"));
		final BufferedImage yellowbox = ImageIO.read(new File("images\\yellowbox.png"));

		JPanel currentBoard = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(craneImage, crane.xPosition, crane.yPosition, null);
				for (int i = 0; i < LevelReader.initialBoxes.size(); i++) {
					if(LevelReader.initialBoxes.get(i).boxName.equals("bluebox")){
						g.drawImage(bluebox,LevelReader.initialBoxes.get(i).xPosition , LevelReader.initialBoxes.get(i).yPosition, null);
					} else if(LevelReader.initialBoxes.get(i).boxName.equals("redbox")){
						g.drawImage(redbox,LevelReader.initialBoxes.get(i).xPosition , LevelReader.initialBoxes.get(i).yPosition, null);
					} else if(LevelReader.initialBoxes.get(i).boxName.equals("greenbox")){
						g.drawImage(greenbox,LevelReader.initialBoxes.get(i).xPosition , LevelReader.initialBoxes.get(i).yPosition, null);
					}else if(LevelReader.initialBoxes.get(i).boxName.equals("yellowbox")){
						g.drawImage(yellowbox,LevelReader.initialBoxes.get(i).xPosition , LevelReader.initialBoxes.get(i).yPosition, null);
					}
				}
			}
		};

		currentBoard.setBounds(0, 251, 650, 250);
		currentBoard.setBackground(Color.WHITE);

		frmCargobotgame.getContentPane().add(currentBoard);
	}

	private void create0Button() {
		JButton zero = new JButton("0");
		zero.setBounds(390, 510, 50, 30);
		zero.setBackground(new Color(255, 204, 204));
		zero.setTransferHandler(new ValueExportTransferHandler("0"));

		zero.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				JButton button = (JButton) e.getSource();
				TransferHandler handle = button.getTransferHandler();
				handle.exportAsDrag(button, e, TransferHandler.COPY);
			}
		});
		frmCargobotgame.getContentPane().add(zero);
	}
	
	
	private void create1Button() {
		JButton one = new JButton("1");
		one.setBounds(390, 550, 50, 30);
		one.setBackground(new Color(255, 204, 204));
		one.setTransferHandler(new ValueExportTransferHandler("1"));

		one.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				JButton button = (JButton) e.getSource();
				TransferHandler handle = button.getTransferHandler();
				handle.exportAsDrag(button, e, TransferHandler.COPY);
			}
		});
		frmCargobotgame.getContentPane().add(one);
	}
	
	private void create2Button() {
		JButton two = new JButton("2");
		two.setBounds(390, 590, 50, 30);
		two.setBackground(new Color(255, 204, 204));
		two.setTransferHandler(new ValueExportTransferHandler("2"));

		two.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				JButton button = (JButton) e.getSource();
				TransferHandler handle = button.getTransferHandler();
				handle.exportAsDrag(button, e, TransferHandler.COPY);
			}
		});
		frmCargobotgame.getContentPane().add(two);
	}
	private void create3Button() {
		JButton three = new JButton("3");
		three.setBounds(390, 630, 50, 30);
		three.setBackground(new Color(255, 204, 204));
		three.setTransferHandler(new ValueExportTransferHandler("3"));

		three.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				JButton button = (JButton) e.getSource();
				TransferHandler handle = button.getTransferHandler();
				handle.exportAsDrag(button, e, TransferHandler.COPY);
			}
		});
		frmCargobotgame.getContentPane().add(three);
	}

	private void createLeftButton() {
		JButton Left = new JButton("Left");
		Left.setBounds(457, 510, 70, 30);
		Left.setBackground(new Color(255, 204, 204));
		Left.setTransferHandler(new ValueExportTransferHandler("Left"));

		Left.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				JButton button = (JButton) e.getSource();
				TransferHandler handle = button.getTransferHandler();
				handle.exportAsDrag(button, e, TransferHandler.COPY);
			}
		});
		frmCargobotgame.getContentPane().add(Left);
	}

	private void createDownButton() {
		JButton Down = new JButton("Down");
		Down.setBounds(457, 590, 70, 30);
		Down.setBackground(new Color(255, 204, 204));
		Down.setTransferHandler(new ValueExportTransferHandler("Down"));
		Down.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				JButton button = (JButton) e.getSource();
				TransferHandler handle = button.getTransferHandler();
				handle.exportAsDrag(button, e, TransferHandler.COPY);
			}
		});

		frmCargobotgame.getContentPane().add(Down);
	}

	private void createRightButton() {
		JButton Right = new JButton("Right");
		Right.setBounds(457, 550, 70, 30);
		Right.setBackground(new Color(255, 204, 204));
		Right.setTransferHandler(new ValueExportTransferHandler("Right"));

		Right.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				JButton button = (JButton) e.getSource();
				TransferHandler handle = button.getTransferHandler();
				handle.exportAsDrag(button, e, TransferHandler.COPY);
			}
		});
		frmCargobotgame.getContentPane().add(Right);
	}
	
	private void createRedButton() {
		JButton red = new JButton("Red");
		red.setBounds(540, 510, 80, 30);
		red.setBackground(Color.red);
		red.setTransferHandler(new ValueExportTransferHandler("R"));

		red.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				JButton button = (JButton) e.getSource();
				TransferHandler handle = button.getTransferHandler();
				handle.exportAsDrag(button, e, TransferHandler.COPY);
			}
		});
		frmCargobotgame.getContentPane().add(red);
	}
	
	private void createYellowButton() {
		JButton yellow = new JButton("Yellow");
		yellow.setBounds(540, 550, 80, 30);
		yellow.setBackground(Color.yellow);
		yellow.setTransferHandler(new ValueExportTransferHandler("Y"));

		yellow.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				JButton button = (JButton) e.getSource();
				TransferHandler handle = button.getTransferHandler();
				handle.exportAsDrag(button, e, TransferHandler.COPY);
			}
		});
		frmCargobotgame.getContentPane().add(yellow);
	}	
	
	private void createBlueButton() {
		JButton blue = new JButton("Blue");
		blue.setBounds(540, 590, 80, 30);
		blue.setBackground(Color.blue);
		blue.setTransferHandler(new ValueExportTransferHandler("B"));

		blue.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				JButton button = (JButton) e.getSource();
				TransferHandler handle = button.getTransferHandler();
				handle.exportAsDrag(button, e, TransferHandler.COPY);
			}
		});
		frmCargobotgame.getContentPane().add(blue);
	}	
	
	private void createGreenButton() {
		JButton green = new JButton("Green");
		green.setBounds(540, 630, 80, 30);
		green.setBackground(Color.green);
		green.setTransferHandler(new ValueExportTransferHandler("G"));

		green.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				JButton button = (JButton) e.getSource();
				TransferHandler handle = button.getTransferHandler();
				handle.exportAsDrag(button, e, TransferHandler.COPY);
			}
		});
		frmCargobotgame.getContentPane().add(green);
	}
	private void createTextField1() {
		mainCommands = new JTextField();
		mainCommands.setBounds(50, 510, 100, 30);
		frmCargobotgame.getContentPane().add(mainCommands);
		mainCommands.setTransferHandler(new ValueImportTransferHandler());
	}

	private void createTextField2() {
		userCommands2 = new JTextField();
		userCommands2.setBounds(50, 550, 100, 30);
		frmCargobotgame.getContentPane().add(userCommands2);
		userCommands2.setTransferHandler(new ValueImportTransferHandler());
		//	textField2.setColumns(10);
	}

	private void createTextField3() {
		userCommands3 = new JTextField();
		userCommands3.setBounds(50, 590, 100, 30);
		frmCargobotgame.getContentPane().add(userCommands3);
		userCommands3.setTransferHandler(new ValueImportTransferHandler());
		//	textField3.setColumns(10);
	}

	private void createTextField4() {
		userCommands4 = new JTextField();
		userCommands4.setBounds(50, 630, 100, 30);
		frmCargobotgame.getContentPane().add(userCommands4);
		userCommands4.setTransferHandler(new ValueImportTransferHandler());
		//	textField2.setColumns(10);
	}

	private void createRunButton() {
		JButton btnRun = new JButton("RUN");
		btnRun.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 11));
		btnRun.setBounds(160, 575, 85, 30);
		btnRun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				commandElement1 = mainCommands.getText().split("-");
				commandElement2 = userCommands2.getText().split("-");
				commandElement3 = userCommands3.getText().split("-");
				commandElement4 = userCommands4.getText().split("-");
				commandArrayArrayList.add(commandElement1);
				commandArrayArrayList.add(commandElement2);
				commandArrayArrayList.add(commandElement3);
				commandArrayArrayList.add(commandElement4);
				try {
					cr.read(commandArrayArrayList);
					CommandReader.functions.get("0").execute(game);
					createCurrentBoardP();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		frmCargobotgame.getContentPane().add(btnRun);
	}


	public void runFunction(String name) {
		CommandReader.functions.get(name).execute(this);
	}

	public void compareCurrentWithTarget() {
		board.current[board.crane.xPosition][board.crane.yPosition] = null;  //target has no crane so it is easier to compare when we delete it in current
		for (int row = 0; row < board.LENGTH; row++) {
			for (int col = 0; col < board.widthIndex; col++) {
				if (board.target[row][col] != null) {                                           //if this index is not empty- if there is a box in target table--
					if (board.current[row][col] != null) {                                          // there is a box in current table--
						Box table = (Box) (board.current[row][col]);
						Box target = (Box) (board.target[row][col]);
						if (!table.equals(target)) {                                                                    //check if they are equals
							System.out.println("Guess What? You Just LOST the Game!!!");
							return;
						}
					}else {                                                                                         //if there is a box in target but not in current
						System.out.println("Guess What? You Just LOST the Game!!!");
						return;
					}
				}else {                                                                                                 //if there is no box in target but there is in current
					if (board.current[row][col] != null) {
						System.out.println("Guess What? You Just LOST the Game!!!");
						return;
					}
				}
			}
		}
		System.out.println("YOU'RE SUCH A GENIUS!");
	}
}