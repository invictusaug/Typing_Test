import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random; 

public class Frame extends JFrame implements ActionListener, KeyListener{

	String passage = "", typedPass = "", message = "";
	int typed = 0, count = 0, WPM;
	double start, end, elapsed, seconds;
	boolean running, ended;
	final int SCREEN_WIDTH, SCREEN_HEIGHT, DELAY = 100;
	JButton button;
	Timer timer;
	JLabel label;
	public Frame() {
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SCREEN_WIDTH = 720;
		SCREEN_HEIGHT = 400;
		this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		button = new JButton("Start");
		button.setFont(new Font("MV Boli", Font.BOLD, 30));
		button.setForeground(Color.BLUE);
		button.setVisible(true);
		button.addActionListener(this);
		button.setFocusable(false);
		label = new JLabel();
		label.setText("Click the Start Button");
		label.setFont(new Font("MV Boli",Font.BOLD, 30));
		label.setVisible(true);
		label.setOpaque(true);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBackground(Color.LIGHT_GRAY);
		this.add(button, BorderLayout.SOUTH);
		this.add((label), BorderLayout.NORTH);
		this.getContentPane().setBackground(Color.WHITE);
		this.addKeyListener(this);
		this.setFocusable(true);
		this.setResizable(false);
		this.setTitle("Typing Test");
		this.revalidate();
	}
	public void paint(Graphics g) {
		super.paint(g);
		draw(g); 
	}
	public void draw(Graphics g) {
		g.setFont(new Font("MV Boli", Font.BOLD, 30));
		if(running) {
			if(passage.length() > 1) {
				g.drawString(passage.substring(0, 50), g.getFont().getSize(), g.getFont().getSize()*5);
				g.drawString(passage.substring(50, 100), g.getFont().getSize(), g.getFont().getSize()*7);
				g.drawString(passage.substring(100, 150), g.getFont().getSize(), g.getFont().getSize()*9);
				g.drawString(passage.substring(150, 200), g.getFont().getSize(), g.getFont().getSize()*11);
			}
			g.setColor(Color.GREEN);
			if(typedPass.length() > 0) {
				if(typed < 50) {
					g.drawString(passage.substring(0, typed), g.getFont().getSize(), g.getFont().getSize()*5);
				} else {
					g.drawString(passage.substring(0, 50), g.getFont().getSize(), g.getFont().getSize()*5);
				}
			}
			if(typedPass.length() > 50) {
				if(typed < 100) {
					g.drawString(passage.substring(50, typed), g.getFont().getSize(), g.getFont().getSize()*7);
				} else {
					g.drawString(passage.substring(50, 100), g.getFont().getSize(), g.getFont().getSize()*7);
				}
			}
			if(typedPass.length() > 100) {
				if(typed < 150) {
					g.drawString(passage.substring(100, typed), g.getFont().getSize(), g.getFont().getSize()*9);
				} else {
					g.drawString(passage.substring(100, 150), g.getFont().getSize(), g.getFont().getSize()*9);
				}
			}
			if(typedPass.length() > 150) {
				g.drawString(passage.substring(0, typed), g.getFont().getSize(), g.getFont().getSize()*11); 
			}
			running = false;
		}
		if(ended) {
			if(WPM <= 40) {
				message = "You are an Average Typist";
			} else if(WPM > 40 && WPM <= 60) {
				message = "You are an Good Typist";
			} else if(WPM > 60 && WPM <= 100) {
				message = "You are an Excellent Typist";
			} else {
				message = "You are an Elite Typist";
			}
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.setColor(Color.BLUE);
			g.drawString("Typing Test Completed!", (SCREEN_WIDTH-metrics.stringWidth("Typing Test Completed!")),  g.getFont().getSize()*5);
			g.drawString(message, (SCREEN_WIDTH-metrics.stringWidth(message)/2), g.getFont().getSize()*11);
			timer.stop();
			ended=false;
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if(passage.length() > 1) {
			if(count == 0) {
				start = LocalTime.now().toNanoOfDay();
			} else if(count == 200) {
				end = LocalTime.now().toNanoOfDay();
				elapsed = end - start;
				seconds = elapsed / 1000000000.0;
				WPM = (int)(((200.0/5)/seconds)*60);
				ended = true;
				running = false;
				count++;
			}
			char[] pass = passage.toCharArray();
			if(typed < 200) {
				running = true;
				if(e.getKeyChar() == pass[typed]) {
					typedPass = typedPass + pass[typed];
					typed++;
					count++;
				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == button) {
			passage = getPassage();
			timer = new Timer(DELAY, this);
			timer.start();
			running = true;
			ended = false;
			typedPass = "";
			message = "";
			typed = 0;
			count = 0;
		}
		if(running) {
			repaint();
		}
		if(ended) {
			repaint();
		}
	}
	public static String getPassage() {
		ArrayList<String> Passages = new ArrayList<String>();
		String pas1 = "Coding is not just about writing lines of code. It's an art that requires creativity, logic, and problem-solving skills. Like a painter with a blank canvas, a programmer transforms ideas into reality using code as their brush.";
		String pas2 = "The digital revolution has transformed the way we live, work, and communicate. From smartphones to social media, technology is at the heart of our daily lives, shaping the future in ways we never imagined.";
		String pas3 = "History is a journey through time, where each event is a stepping stone leading to the present. Understanding our past helps us make informed decisions for the future, guiding humanity on a path of progress and discovery.";
		String pas4 = "The sounds of nature create a symphony that soothes the soul. The rustling leaves, chirping birds, and flowing rivers blend harmoniously, reminding us of the beauty and tranquility that the natural world offers.";
		String pas5 = "Education is the most powerful tool we have to change the world. It opens doors to new opportunities, empowers individuals, and fosters a deeper understanding of the world around us, driving positive change in society.";
		String pas6 = "The universe is a vast and mysterious place, filled with wonders beyond our imagination. From distant galaxies to black holes, the quest to understand the cosmos is a journey that pushes the boundaries of human knowledge.";
		String pas7 = "Teamwork is the foundation of success in any endeavor. When individuals come together, combining their skills and strengths, they achieve more than they ever could alone. Collaboration is the key to unlocking potential.";
		String pas8 = "Success is not always about talent or intelligence; it's about persistence. The ability to keep going, even when faced with challenges, is what sets achievers apart. Every failure is a step closer to success.";
		String pas9 = "Diversity is what makes our world vibrant and dynamic. Different cultures, perspectives, and ideas come together to create a rich tapestry of experiences. Embracing diversity fosters innovation and understanding.";
		String pas10 = "Artificial Intelligence is reshaping industries and redefining the future. From autonomous vehicles to personalized recommendations, AI is revolutionizing how we interact with technology, opening new possibilities for innovation.";
		Passages.add(pas1);
		Passages.add(pas2);
		Passages.add(pas3);
		Passages.add(pas4);
		Passages.add(pas5);
		Passages.add(pas6);
		Passages.add(pas7);
		Passages.add(pas8);
		Passages.add(pas9);
		Passages.add(pas10);
		Random rand = new Random();
		int place = rand.nextInt(10);
		String toReturn = Passages.get(place).substring(0, 200);
		if(toReturn.charAt(199) == 32) {
			toReturn = toReturn.strip();
			toReturn = toReturn + ".";
		}
		return toReturn;
	}
}
