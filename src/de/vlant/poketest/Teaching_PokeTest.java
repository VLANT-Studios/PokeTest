package de.vlant.poketest;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Teaching_PokeTest extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1530072364L;
	private static JLabel[] crosses;
	private JButton next;
	private JButton backToStart;
	private int count;
	private JFrame endScreen;
	private JLabel curPokemn;
	private JLabel curCount;
	
	public class Pokemon {
		private String name;
		private int id;
		
		public Pokemon(String name, int id) {
			this.id = id;
			this.name = name;
		}
		
		public String getString() {
			return name;
		}
		
		public int getId() {
			return id;
		}
	}
	
	public Teaching_PokeTest() {
		super("PokeTest Erklärmodus");
		URL iconURL = getClass().getResource("/de/vlant/poketest/assets/vlant.png");
		ImageIcon icon = new ImageIcon(iconURL);
		this.setIconImage(icon.getImage());
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public void run() {
		this.setSize(1100, 500);
		
		JPanel panel = new JPanel(null);
		JLabel chars = new JLabel("a b c d e f g h i j k l m n o p q r s t u v w x y z  !  ?");
		chars.setFont(PokeTestAPI.icognitoFont.deriveFont(30f));
		chars.setBounds(50, 100, 1100, 50);
		next = new JButton("Weiter");
		next.setBounds(500, 300, 100, 50);
		next.addActionListener(this);
		curCount = new JLabel("Bisherige Versuche: 0");
		curCount.setBounds(475, 30, 175, 50);
		curPokemn = new JLabel(" ");
		curPokemn.setFont(PokeTestAPI.icognitoFont.deriveFont(72f));
		curPokemn.setBounds(520, 150, 100, 100);
		panel.add(chars);
		panel.add(next);
		panel.add(curCount);
		panel.add(curPokemn);
		
		loadAndDrawCrosses(panel);
		
		this.add(panel);
		this.setVisible(true);
	}

	private void loadAndDrawCrosses(JPanel panel) {
		ImageIcon cross_ = new ImageIcon(PokeTest.class.getResource("/de/vlant/poketest/assets/kreuz.png"));
		cross_ = new ImageIcon(PokeTestAPI.getScaledImage(cross_.getImage(), 25, 25));
		JLabel cross;
		ArrayList<JLabel> crosses_ = new ArrayList<JLabel>();
		
		for (int i = 0; i < 28; i++) {
			cross = new JLabel(cross_);
			cross.setOpaque(false);
			panel.add(cross);
			if (i < 13)
				cross.setBounds(i*35+30, 100, 40, 40);
			else if (i == 13)
				cross.setBounds(i*35+40, 100, 40, 40);
			else if (i < 21)
				cross.setBounds(i*35+50, 100, 40, 40);
			else if (i < 23)
				cross.setBounds(i*35+60, 100, 40, 40);
			else if (i < 27)
				cross.setBounds(i*35+70, 100, 40, 40);
			else
				cross.setBounds(i*35+80, 100, 40, 40);
			cross.setVisible(false);
			crosses_.add(cross);
		}
		crosses = crosses_.toArray(new JLabel[28]);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		
		if (source == next) {
			if (next.getText().equalsIgnoreCase("Weiter")) {
				Pokemon pkm = generateIcognito();
				crosses[pkm.getId()].setVisible(true);
				curPokemn.setText(pkm.getString());
				curCount.setText("Bisherige Versuche: " + count);
				if (hasGotAll())
					next.setText("Beenden");
			} else if (next.getText().equalsIgnoreCase("Beenden")) {
				this.setVisible(false);
				showEndScreen();
			}
		} else if (source == backToStart) {
			endScreen.setVisible(false);
			PokeTest.window.setVisible(true);
			PokeTest.window.revalidate();
		}
	}

	private void showEndScreen() {
		endScreen = new JFrame("PokeTest Erklärmodus Endbildschirm");
		URL iconURL = getClass().getResource("/de/vlant/poketest/assets/vlant.png");
		ImageIcon icon = new ImageIcon(iconURL);
		endScreen.setIconImage(icon.getImage());
		endScreen.setSize(380, 230);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel label = new JLabel("Benötigte Versuche: " + count);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		backToStart = new JButton("Zurück zum Hauptfenster");
		backToStart.addActionListener(this);
		backToStart.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel vlant = new JLabel("© 2020, VLANT Studios");
		vlant.setAlignmentX(Component.CENTER_ALIGNMENT);
//		website.setFont(website.getFont().deriveFont(Font.BOLD));
//		website.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(PokeTestAPI.spacer());
		panel.add(label);
		panel.add(PokeTestAPI.spacer(10));
		panel.add(backToStart);
		panel.add(PokeTestAPI.spacer(12));
		panel.add(vlant);
//		panel.add(website);
		
		endScreen.add(panel);
		endScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		endScreen.setVisible(true);
	}

	private boolean hasGotAll() {
		int i = 0;
		for (JLabel lbl : crosses) {
			if (lbl.isVisible())
				i++;
			else
				break;
		}
		if (i == 28)
			return true;
		else
			return false;
	}

	private Pokemon generateIcognito() {
		String pokmn = "";
		int max = 28; // excluded
		int min = 0;
		int id = (int) (Math.random() * (max - min)) + min;
		count++;
		switch (id) {
			case 1:
				pokmn = "A";
				break;
			case 2:
				pokmn = "B";
				break;
			case 3:
				pokmn = "C";
				break;
			case 4:
				pokmn = "D";
				break;
			case 5:
				pokmn = "E";
				break;
			case 6:
				pokmn = "F";
				break;
			case 7:
				pokmn = "G";
				break;
			case 8:
				pokmn = "H";
				break;
			case 9:
				pokmn = "I";
				break;
			case 10:
				pokmn = "J";
				break;
			case 11:
				pokmn = "K";
				break;
			case 12:
				pokmn = "L";
				break;
			case 13:
				pokmn = "M";
				break;
			case 14:
				pokmn = "N";
				break;
			case 15:
				pokmn = "O";
				break;
			case 16:
				pokmn = "P";
				break;
			case 17:
				pokmn = "Q";
				break;
			case 18:
				pokmn = "R";
				break;
			case 19:
				pokmn = "S";
				break;
			case 20:
				pokmn = "T";
				break;
			case 21:
				pokmn = "U";
				break;
			case 22:
				pokmn = "V";
				break;
			case 23:
				pokmn = "W";
				break;
			case 24:
				pokmn = "X";
				break;
			case 25:
				pokmn = "Y";
				break;
			case 26:
				pokmn = "Z";
				break;
			case 27:
				pokmn = "!";
				break;
			case 0:
				pokmn = "?";
				break;
			default:
				break;
		}
		if (id > 0)
			return new Pokemon(pokmn, id-1);
		else
			return new Pokemon(pokmn, 27);
	}
}
