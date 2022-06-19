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
import placeAttributes.PhoneNumber;
import places.Cafe;
import places.NightSpot;
import places.Place;

import java.awt.Font;

public class NightSpotWindow {
	private static NightSpot[] nightSpots;
	Object nightSpotSelect;

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArrayList<Place> placeList = CityGuide.getInstance().getPlaceMap().get("nightspots");
                    ArrayList<Place> newCPlaceList = (ArrayList<Place>) placeList.clone();
                    Iterator<Place> cIt = placeList.iterator();
                    while (cIt.hasNext()){
                        Place p = cIt.next();
                        if(!p.getAddress().getCity().equals(args[0])){
                            newCPlaceList.remove(p);
                        }
                    }
                    nightSpots = new NightSpot[newCPlaceList.size()];
                    nightSpots = newCPlaceList.toArray(nightSpots);
					NightSpotWindow window = new NightSpotWindow(nightSpots);
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
	public NightSpotWindow(NightSpot[] nightSpots) {
		initialize(nightSpots);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(NightSpot[] nightSpots) {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 700, 600);
		String data[][] = new String[nightSpots.length][4];
		for (int i = 0; i < nightSpots.length; i++) {
			for (int j = 0; j < 4; j++) {
				if (j == 0) {
					data[i][j] = nightSpots[i].getName();
				}
				if (j == 1) {
					data[i][j] = nightSpots[i].getAddress().toString();
				}
				if (j == 2) {
					if ((nightSpots[i].isAlcohol())) {
						data[i][j] = "		O";
					} else  {
						data[i][j] = "		x";
					}
				}
				if (j == 3) {
					if (nightSpots[i].isLiveMusic()) {
						data[i][j] = "		O";
					} else {
						data[i][j] = "		x"; 
					}
				}
			}

		}
		String comboNightSpot[] = new String[nightSpots.length];
		for (int i = 0; i < comboNightSpot.length; i++) {
			comboNightSpot[i] = nightSpots[i].getName();
		}
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
		String column[] = { "Name", "Address", "Alcohol", "Live Music" };
		frame.getContentPane().setLayout(null);
		JTable jt = new JTable(data, column);
		jt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jt.setColumnSelectionAllowed(true);
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jt.getColumnModel().getColumn(0).setPreferredWidth(200);
		jt.getColumnModel().getColumn(1).setPreferredWidth(400);
		jt.getColumnModel().getColumn(2).setPreferredWidth(50);
		jt.getColumnModel().getColumn(3).setPreferredWidth(50);
		jt.setFocusable(false);
		jt.setRowSelectionAllowed(false);

		JScrollPane sp = new JScrollPane(jt);
		sp.setBounds(0, 0, 710, 400);
		frame.getContentPane().add(sp);

		JComboBox comboBox = new JComboBox(comboNightSpot);
		comboBox.setBounds(241, 414, 213, 22);
		frame.getContentPane().add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nightSpotSelect = comboBox.getItemAt(comboBox.getSelectedIndex());
				NightSpot tempNightSpot = null;
				for (int i = 0; i < nightSpots.length; i++) {
					if ((nightSpots[i].getName()).equals(nightSpotSelect)) {
						tempNightSpot = nightSpots[i];
						break;
					}
				}
				PlaceDisplayerWindow.setPlace(tempNightSpot);
				PlaceDisplayerWindow.main("nightspot");

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
				frame.dispose();
				MainWindow.main(status);
				
			}
		});

		JLabel lblNewLabel = new JLabel("Choose your cafe here ---->    ");
		lblNewLabel.setBounds(38, 413, 172, 25);
		frame.getContentPane().add(lblNewLabel);
		frame.setSize(715, 500);
		frame.setVisible(true);
		
	}

}
