package de.vlant.poketest;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class PokeTest extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 78364264L;
	private static JButton start_button;
	private static JButton end_button;
	private static PokeTest end_window;
	public static PokeTest window;
	private static int count;
	private static int count_;
	private static ArrayList<Integer> stats;
	private static PokeTest final_window;
	private static JButton final_end_button;
	private static JCheckBox skip_window;
	private static ActionEvent ev;
	private static PokeTest loading_screen;
	private static JButton teach_button;

	public PokeTest(String string) {
		super(string);
		URL iconURL = getClass().getResource("/de/vlant/poketest/assets/vlant.png");
		ImageIcon icon = new ImageIcon(iconURL);
		this.setIconImage(icon.getImage());
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()); 
			} catch (Exception e2) {}
		}
	}
	
	public PokeTest() {
		super();
		URL iconURL = getClass().getResource("/de/vlant/poketest/assets/vlant.png");
		ImageIcon icon = new ImageIcon(iconURL);
		this.setIconImage(icon.getImage());
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()); 
			} catch (Exception e2) {}
		}
	}

	public void actionPerformed(ActionEvent ev) {
		Object source = ev.getSource();
		if (source == start_button) {
			String input = JOptionPane.showInputDialog(window, "Wie oft soll der Versuch wiederholt werden?");
			if (input != null) {
				try {
					count = Integer.parseInt(input.trim());
				}
				catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(window, "Ungültige Zahl!");
					return;
				}
				if (count < 2) {
					JOptionPane.showMessageDialog(window, "Zu kleine Zahl!");
					return;
				} else if (count > 500000) {
					JOptionPane.showMessageDialog(window, "Zu große Zahl!");
					return;
				}
				count_ = count;
				count--;
				window.setVisible(false);
				initLoadingScreen();
				runPokemonTry();
			} else return;
		} else if (source == teach_button) {
			Teaching_PokeTest tpk = new Teaching_PokeTest();
			window.setVisible(false);
			tpk.run();
		} else if (source == end_button) {
			if (count > 0) {
				count--;
				runPokemonTry();
			}
			else
				showFinalStats();
		} else if (source == final_end_button) {
			System.exit(0);
		}
	}
	
	private static void showFinalStats() {
		if (end_window != null)
			end_window.setVisible(false);
		final_window = new PokeTest("PokeTest Auswertung");

		final_end_button = new JButton("Beenden");
		final_end_button.addActionListener(new PokeTest());
		final_end_button.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel final_stats = new JLabel("Durchschnittlich wurden " + getStats() + " Versuche gebraucht, bei " + count_ + " Testläufen.");
		final_stats.setAlignmentX(Component.CENTER_ALIGNMENT);
		JPanel panel = new JPanel();
		BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(layout);
		panel.add(PokeTestAPI.spacer(28));
		panel.add(final_stats);
		panel.add(PokeTestAPI.spacer(10));
		panel.add(final_end_button);
		panel.add(PokeTestAPI.spacer(40));
		JLabel label = new JLabel("© 2020, VLANT Studios");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(label);
//		website.setFont(website.getFont().deriveFont(Font.BOLD));
//		website.setAlignmentX(Component.CENTER_ALIGNMENT);
//		panel.add(website);
		final_window.add(panel);
		
		final_window.setSize(700, 300);
		final_window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		loading_screen.setVisible(false);
		final_window.setVisible(true);
	}
	
	public static int getStats() {
		int local_count = 0;
		int i_sum = 0;
		for (int i : stats) {
			i_sum += i;
			local_count++;
		}
		return (i_sum/local_count);
	}

	public static void main(String[] args) {
		stats = new ArrayList<Integer>();
		end_button = new JButton();
		ev = new ActionEvent(end_button, 0, "");
		JLabel font_label = new JLabel("Text");
		PokeTestAPI.setDefaultFont(font_label.getFont().deriveFont(16f).deriveFont(Font.PLAIN));
		PokeTestAPI.initIcognitoFont();
		
//		String username = JOptionPane.showInputDialog("Bitte den Benutzername eingeben!");
//		if (username == null) return;
//		String key = JOptionPane.showInputDialog("Bitte den Lizenzkey eingeben!");
//		if (key == null) return;
//		JFrame jop = new JFrame("Lizensierung");
//		jop.add(new JLabel("Wird lizensiert...", SwingConstants.CENTER));
//		jop.setVisible(true);
//		if (!licenseOK(username, key, jop))
//			return;
		
		window = new PokeTest("PokeTest Hauptfenster");

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		start_button = new JButton("Beginnen");
		start_button.setAlignmentX(Component.CENTER_ALIGNMENT);
		start_button.addActionListener(new PokeTest());
		teach_button = new JButton("Erklärmodus");
		teach_button.setAlignmentX(Component.CENTER_ALIGNMENT);
		teach_button.addActionListener(new PokeTest());
		skip_window = new JCheckBox("Auflistung nach jedem Versuch überspringen, direkt zum Endergebnis springen");
		skip_window.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel label = new JLabel("© 2020, VLANT Studios");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
//		website.setFont(website.getFont().deriveFont(Font.BOLD));
//		website.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel.add(PokeTestAPI.spacer());
		panel.add(skip_window);
		panel.add(PokeTestAPI.spacer(12));
		panel.add(start_button);
		panel.add(PokeTestAPI.spacer(8));
		panel.add(teach_button);
		panel.add(PokeTestAPI.spacer(10));
		panel.add(label);
//		panel.add(website);
		window.add(panel);
		
		window.setSize(650, 300);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		window.setVisible(true);
	}
	
//	private static boolean licenseOK(String username, String key, JFrame old_dialog) {
//		old_dialog.setVisible(false);
//		String dir = System.getProperty("user.home") + "\\AppData\\LocalLow\\VLANT-Studios\\LemonData";
//		if (!(new File(dir)).exists())
//			(new File(dir)).mkdirs();
//		LicenseState state = LicenseManager.license(username, key, "PokeTest", dir + "\\ldatapt.vlant");
//		String message = "";
//		boolean returnB = false;
//		switch (state) {
//		case ALREADY_LICENSED:
//			message = "Dieser Nutzer wird bereits auf einem anderen PC verwendet! Um die Nutzung zurückzusetzen: bncenter.vlant.de/progcenter/PokeTest/reset";
//			break;
//		case ERROR_LICENSE_MANAGER:
//			message = "Fehler bei Lizensierung... Fehlercode: #VL510 (Bitte Mail an lizenz@vlant.de)";
//			break;
//		case ERROR_WITH_DECRYPTION:
//			message = "Offline-Lizensierung gescheitert! Bitte mit dem Internet verbinden und erneut versuchen. Fehlercode: #VL520 (Bitte Mail an lizenz@vlant.de)";
//			break;
//		case ERROR_WITH_ENCRYPTION:
//			message = "Lizensierung erfolgt, aber Fehler aufgetreten. Fehlercode: #VL530 (Bitte Mail an lizenz@vlant.de)";
//			returnB = true;
//			break;
//		case ERROR_WITH_SAVE:
//			message = "Offline-Lizensierung nicht verfügbar. Bitte mit dem Internet verbinden und erneut versuchen!";
//			break;
//		case ERROR_WITH_SAVING:
//			message = "Lizensierung erfolgt, aber Fehler aufgetreten. Fehlercode: #VL540 (Bitte Mail an lizenz@vlant.de)";
//			returnB = true;
//			break;
//		case KEY_WRONG:
//			message = "Falscher Lizenzschlüssel!";
//			break;
//		case NAME_WRONG:
//			message = "Falscher Benutzername!";
//			break;
//		case NO_MAC_ADDR:
//			message = "An diesem PC gibt es keine Möglichkeit zur Identifizierung. Fehlercode: #VL900 (Bitte Mail an lizenz@vlant.de)";
//			break;
//		case NO_SAFE:
//			message = "Offline-Lizensierung nicht verfügbar. Bitte mit dem Internet verbinden und erneut versuchen!";
//			break;
//		case PROG_NOT_LICENSED:
//			message = "Die Lizenz ist nicht für dieses Programm gültig.";
//			break;
//		case UNKNOWN_ERROR:
//			message = "Unbekannter Fehler. Fehlercode: #VL000 (Bitte Mail an lizenz@vlant.de)";
//			break;
//		case LICENSE_OK:
//			return true;
//		}
//		JOptionPane.showMessageDialog(null, message);
//		return returnB;
//	}

	private static void initLoadingScreen() {
		loading_screen = new PokeTest("Bitte warten...");
		loading_screen.setSize(400, 50);
		loading_screen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		loading_screen.setVisible(true);
	}
	
	private static void displayWindow(String output, int tries) {
		loading_screen.setVisible(false);
		if (end_window != null)
			end_window.setVisible(false);
		end_window = new PokeTest("PokeTest Endfenster");

		JTextArea pokemons_display = new JTextArea(output);
		pokemons_display.setEditable(false);
		end_button = new JButton("Nächster Versuch / Ende");
		end_button.addActionListener(new PokeTest());
		JPanel panel = new JPanel();
		BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(layout);
		panel.add(new JLabel("Gesamte Versuche: " + String.valueOf(tries)));
		panel.add(end_button);
		panel.add(new JLabel("Icognitos gezogen:"));
		panel.add(pokemons_display);
		JScrollPane scrollPane = new JScrollPane (panel, 
	            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
	            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		end_window.add(scrollPane);
		
		end_window.setSize(600, 500);
		end_window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		end_window.setVisible(true);
	}
	
	private void runPokemonTry() {
		String pokmn = "";
		StringBuffer output = new StringBuffer();
		boolean[] pokemons = new boolean[28];
		int i = 0;
		while (true) { 
			int max = 28; // excluded
			int min = 0;
			int r = (int) (Math.random() * (max - min)) + min;
			i++;
			switch (r) {
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
					pokmn = "?";
					break;
				case 0:
					pokmn = "!";
					break;
				default:
					break;
			}
			if (!skip_window.isSelected())
				output.append(pokmn + "\n");
			pokemons[r] = true;
			int g = 0;
			for (boolean got : pokemons) {
				if (got)
					g++;
			}
			if (g == 28)
				break;
		}
		stats.add(i);
		if (skip_window.isSelected()) {
			actionPerformed(ev);
		}
		else
			displayWindow(output.toString(), i);
	}

}
