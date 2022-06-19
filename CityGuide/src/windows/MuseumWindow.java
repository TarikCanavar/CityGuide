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
import places.Museum;
import places.Place;
import places.Museum;
import java.awt.Font;

public class MuseumWindow {
	private static Museum[] museums;
	Object museumselect;

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArrayList<Place> placeList = CityGuide.getInstance().getPlaceMap().get("museums");
                    ArrayList<Place> newCPlaceList = (ArrayList<Place>) placeList.clone();
                    Iterator<Place> cIt = placeList.iterator();
                    while (cIt.hasNext()){
                        Place p = cIt.next();
                        if(!p.getAddress().getCity().equals(args[0])){
                            newCPlaceList.remove(p);
                        }
                    }
                    museums = new Museum[newCPlaceList.size()];
                    museums = newCPlaceList.toArray(museums);
					MuseumWindow window = new MuseumWindow(museums);
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
	public MuseumWindow(Museum[] museums) {
		initialize(museums);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Museum[] museums) {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 700, 600);
		String data[][] = new String[museums.length][3];
		for (int i = 0; i < museums.length; i++) {
			for (int j = 0; j < 3; j++) {
				if (j == 0) {
					data[i][j] = museums[i].getName();
				}
				if (j == 1) {
					data[i][j] = museums[i].getAddress().toString();
				}
				if (j == 2) {
					data[i][j] = String.valueOf(museums[i].getType());
				}
			}

		}
		String comboLandscape[] = new String[museums.length];
		for (int i = 0; i < comboLandscape.length; i++) {
			comboLandscape[i] = museums[i].getName();
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
		String column[] = { "Name", "Address", "Type" };
		frame.getContentPane().setLayout(null);
		JTable jt = new JTable(data, column);
		jt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jt.setColumnSelectionAllowed(true);
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jt.getColumnModel().getColumn(0).setPreferredWidth(100);
		jt.getColumnModel().getColumn(1).setPreferredWidth(400);
		jt.getColumnModel().getColumn(2).setPreferredWidth(200);

		JScrollPane sp = new JScrollPane(jt);
		sp.setBounds(0, 0, 710, 400);
		frame.getContentPane().add(sp);

		JComboBox comboBox = new JComboBox(comboLandscape);
		comboBox.setBounds(241, 414, 213, 22);
		frame.getContentPane().add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				museumselect = comboBox.getItemAt(comboBox.getSelectedIndex());
				Museum tempMuseum = null;
				for (int i = 0; i < museums.length; i++) {
					if ((museums[i].getName()).equals(museumselect)) {
						tempMuseum = museums[i];
						break;
					}
				}
				PlaceDisplayerWindow.setPlace(tempMuseum);
				PlaceDisplayerWindow.main("museum");

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
