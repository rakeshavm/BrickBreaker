package Brickbreaker;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
@SuppressWarnings("unused")
public class Main extends JFrame implements KeyListener, ActionListener {
	static JFrame obj;
	JFrame a = new JFrame();
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int x = screenSize.width;
	private int y = screenSize.height;

	public Main() {
		getContentPane().setBackground(Color.BLACK);

		JButton b5 = new JButton("Level 5");
		b5.addActionListener(this);
		b5.setBackground(Color.green);
		JLabel label_3 = new JLabel("");

		JLabel label = new JLabel("");

		JLabel label_2 = new JLabel("");
		JButton b4 = new JButton("Level 4");
		b4.addActionListener(this);
		b4.setBackground(Color.green);
		JButton b1 = new JButton("Level 1");
		b1.setBackground(Color.green);

		b1.addActionListener(this);
		JButton b3 = new JButton("Level 3");
		b3.addActionListener(this);
		b3.setBackground(Color.green);
		JButton b2 = new JButton("Level 2");
		b2.addActionListener(this);
		b2.setBackground(Color.green);
		JLabel label_1 = new JLabel("");

		JLabel lblNewLabel = new JLabel("BRICK BREAKER");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setFont(new Font("serif",Font.BOLD, 55));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addGroup(groupLayout.createSequentialGroup()
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addComponent(label_3)
																.addComponent(label)
																.addComponent(label_2))
														.addGap(650))
												.addGroup(groupLayout.createSequentialGroup()
														.addComponent(b1)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(b2)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(b3)))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(b4)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(b5))
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(350)
										.addComponent(lblNewLabel)))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(223)
						.addComponent(lblNewLabel)
						.addGap(117)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(label_3)
								.addComponent(label)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(label_2)
										.addGap(8)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(b1)
												.addComponent(b2)
												.addComponent(b3)
												.addComponent(b4)
												.addComponent(b5))))
						.addGap(11))
				);
		getContentPane().setLayout(groupLayout);

	}
	public static void main(String[] args) {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x1 = screenSize.width;
		int y1 = screenSize.height;
		obj = new Main();
		obj.setBackground(Color.BLACK);
		obj.setBounds(10, 10, x1, y1);
		obj.setTitle("Brick Breaker");		
		obj.setResizable(true);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}


	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		this.obj.dispose();
		a = new JFrame();
		JButton c = (JButton) e.getSource();
		if(c.getText().equals("Level 1")) {
			Gameplay1 g = new Gameplay1(3,8,7,this);
			this.a.setBounds(10, 10, this.x, this.y);
			this.a.setTitle("Brick Breaker");		
			this.a.setResizable(true);
			this.a.setVisible(true);
			this.a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.a.getContentPane().add(g);
		}
		else if(c.getText().equals("Level 2")) {

			//JFrame a = new JFrame();
			Gameplay2 g = new Gameplay2(4,9,6,this);
			this.a.setBounds(10, 10, this.x, this.y);
			this.a.setTitle("Breakout Ball");		
			this.a.setResizable(true);
			this.a.setVisible(true);
			this.a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.a.getContentPane().add(g);
		}
		else if(c.getText().equals("Level 3")) {
			//JFrame a = new JFrame();
			Gameplay3 g = new Gameplay3(5,11,6,this);
			this.a.setBounds(10, 10, this.x, this.y);
			this.a.setTitle("Breakout Ball");		
			this.a.setResizable(true);
			this.a.setVisible(true);
			this.a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.a.getContentPane().add(g);
		}
		else if(c.getText().equals("Level 4")) {
			//JFrame a = new JFrame();
			Gameplay4 g = new Gameplay4(6,11,4,this);
			this.a.setBounds(10, 10, this.x, this.y);
			this.a.setTitle("Breakout Ball");		
			this.a.setResizable(true);
			this.a.setVisible(true);
			this.a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.a.getContentPane().add(g);
		}
		else if(c.getText().equals("Level 5")) {
			//JFrame a = new JFrame();
			Gameplay5 g = new Gameplay5(7,13,3,this);
			this.a.setBounds(10, 10, this.x, this.y);
			this.a.setTitle("Breakout Ball");		
			this.a.setResizable(true);
			this.a.setVisible(true);
			this.a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.a.getContentPane().add(g);
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			a.dispose();
			obj.setVisible(true);
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}