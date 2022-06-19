package windows;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import CityGuide.CityGuide;
import CityGuide.JSONFileHandler;
import placeAttributes.Address;
import places.Landscape;
import places.Place;
import places.Landscape;
import java.awt.Font;

public class LandscapeWindow {

	private static Landscape[] landscapes;
	Object landscapeSelect;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArrayList<Place> placeList = CityGuide.getInstance().getPlaceMap().get("landscapes");
                    ArrayList<Place> newCPlaceList = (ArrayList<Place>) placeList.clone();
                    Iterator<Place> cIt = placeList.iterator();
                    while (cIt.hasNext()){
                        Place p = cIt.next();
                        if(!p.getAddress().getCity().equals(args[0])){
                            newCPlaceList.remove(p);
                        }
                    }
                    landscapes = new Landscape[newCPlaceList.size()];
                    landscapes = newCPlaceList.toArray(landscapes);
					LandscapeWindow window = new LandscapeWindow(landscapes);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LandscapeWindow(Landscape[] landscapes) {
		initialize(landscapes);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Landscape[] landscapes) {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 700, 600);
		String data[][] = new String[landscapes.length][4];
		for (int i = 0; i < landscapes.length; i++) {
			for (int j = 0; j < 4; j++) {
				if (j == 0) {
					data[i][j] = landscapes[i].getName();
				}
				if (j == 1) {
					data[i][j] = landscapes[i].getAddress().toString();
				}
				if (j == 2) {
					data[i][j] = String.valueOf(landscapes[i].getType());
				}
				if (j == 3) {
					if (landscapes[i].hasFee()) {
						data[i][j] = "		O";
					} else {
						data[i][j] = "		x"; 
					}
				}
			}

		}
		String comboLandscape[] = new String[landscapes.length];
		for (int i = 0; i < comboLandscape.length; i++) {
			comboLandscape[i] = landscapes[i].getName();
		}

		String column[] = { "Name", "Address", "Type", "Fee" };
		frame.getContentPane().setLayout(null);
		JTable jt = new JTable(data, column);
		jt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jt.setColumnSelectionAllowed(true);
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jt.getColumnModel().getColumn(0).setPreferredWidth(200);
		jt.getColumnModel().getColumn(1).setPreferredWidth(300);
		jt.getColumnModel().getColumn(2).setPreferredWidth(100);
		jt.getColumnModel().getColumn(3).setPreferredWidth(100);

		JScrollPane sp = new JScrollPane(jt);
		sp.setBounds(0, 0, 710, 400);
		frame.getContentPane().add(sp);
		JButton returnButton = new JButton("Return to main screen");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String status;
				if(CityGuide.getInstance().ifAdmin()) {
					status="admin";
				}
				else {
					status="user";
				}
				MainWindow.main(status);
				frame.dispose();
			}
		});
		returnButton.setBounds(519, 410, 139, 30);
		frame.getContentPane().add(returnButton);
		JComboBox comboBox = new JComboBox(comboLandscape);
		comboBox.setBounds(241, 414, 213, 22);
		frame.getContentPane().add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				landscapeSelect = comboBox.getItemAt(comboBox.getSelectedIndex());
				Landscape tempLandscape = null;
				for (int i = 0; i < landscapes.length; i++) {
					if ((landscapes[i].getName()).equals(landscapeSelect)) {
						tempLandscape = landscapes[i];
						break;
					}
				}
				PlaceDisplayerWindow.setPlace(tempLandscape);
				PlaceDisplayerWindow.main("landscape");

			}

		});
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {				
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				String status;
				if(CityGuide.getInstance().ifAdmin()) {
					status="admin";
				}
				else {
					status="user";
				}
				MainWindow.main(status);

			}
		});

		JLabel lblNewLabel = new JLabel("Choose your Landscape here ---->    ");
		lblNewLabel.setBounds(38, 413, 172, 25);
		frame.getContentPane().add(lblNewLabel);
		frame.setSize(715, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
