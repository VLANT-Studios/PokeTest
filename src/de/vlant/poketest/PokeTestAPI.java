package de.vlant.poketest;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class PokeTestAPI {
	private static int defaultSize = 12;
	
	public static Font icognitoFont;

	public static void setDefaultFont(Font font) {
		defaultSize = font.getSize();
		FontUIResource myFont = new FontUIResource(font);
	    UIManager.put("CheckBoxMenuItem.acceleratorFont", myFont);
	    UIManager.put("Button.font", myFont);
	    UIManager.put("ToggleButton.font", myFont);
	    UIManager.put("RadioButton.font", myFont);
	    UIManager.put("CheckBox.font", myFont);
	    UIManager.put("ColorChooser.font", myFont);
	    UIManager.put("ComboBox.font", myFont);
	    UIManager.put("Label.font", myFont);
	    UIManager.put("List.font", myFont);
	    UIManager.put("MenuBar.font", myFont);
	    UIManager.put("Menu.acceleratorFont", myFont);
	    UIManager.put("RadioButtonMenuItem.acceleratorFont", myFont);
	    UIManager.put("MenuItem.acceleratorFont", myFont);
	    UIManager.put("MenuItem.font", myFont);
	    UIManager.put("RadioButtonMenuItem.font", myFont);
	    UIManager.put("CheckBoxMenuItem.font", myFont);
	    UIManager.put("OptionPane.buttonFont", myFont);
	    UIManager.put("OptionPane.messageFont", myFont);
	    UIManager.put("Menu.font", myFont);
	    UIManager.put("PopupMenu.font", myFont);
	    UIManager.put("OptionPane.font", myFont);
	    UIManager.put("Panel.font", myFont);
	    UIManager.put("ProgressBar.font", myFont);
	    UIManager.put("ScrollPane.font", myFont);
	    UIManager.put("Viewport.font", myFont);
	    UIManager.put("TabbedPane.font", myFont);
	    UIManager.put("Slider.font", myFont);
	    UIManager.put("Table.font", myFont);
	    UIManager.put("TableHeader.font", myFont);
	    UIManager.put("TextField.font", myFont);
	    UIManager.put("Spinner.font", myFont);
	    UIManager.put("PasswordField.font", myFont);
	    UIManager.put("TextArea.font", myFont);
	    UIManager.put("TextPane.font", myFont);
	    UIManager.put("EditorPane.font", myFont);
	    UIManager.put("TabbedPane.smallFont", myFont);
	    UIManager.put("TitledBorder.font", myFont);
	    UIManager.put("ToolBar.font", myFont);
	    UIManager.put("ToolTip.font", myFont);
	    UIManager.put("Tree.font", myFont);
	    UIManager.put("FormattedTextField.font", myFont);
	    UIManager.put("IconButton.font", myFont);
	    UIManager.put("InternalFrame.optionDialogTitleFont", myFont);
	    UIManager.put("InternalFrame.paletteTitleFont", myFont);
	    UIManager.put("InternalFrame.titleFont", myFont);
	}
	
	public static void initIcognitoFont() {
		try {
			icognitoFont = Font.createFont(Font.TRUETYPE_FONT, PokeTest.class.getResourceAsStream("/de/vlant/poketest/assets/Icognito.ttf")).deriveFont(16f);
		} catch (FontFormatException | IOException e) {
			icognitoFont = new Font("Comic Sans MS", Font.PLAIN, 16);
			e.printStackTrace();
		}
	}
	
	public static Image getScaledImage(Image srcImg, int w, int h) {
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}
	
	public static JLabel spacer(int size) {
		JLabel spacer = new JLabel(" ");
		spacer.setFont(spacer.getFont().deriveFont((float) size));
		return spacer;
	}

	public static JLabel spacer() {
		return spacer(defaultSize);
	}
}
