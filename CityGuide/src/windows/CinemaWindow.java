package windows;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import CityGuide.CityGuide;
import CityGuide.JSONFileHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import placeAttributes.Address;
import places.Cinema;
import places.Place;

import java.awt.Font;

public class CinemaWindow {
	private static Cinema[] cinemas;
	Object cinemaSelect;
	private JFrame frame;




	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { 
			public void run() {
				try {
					ArrayList<Place> placeList = CityGuide.getInstance().getPlaceMap().get("cinemas");
                    ArrayList<Place> newCPlaceList = (ArrayList<Place>) placeList.clone();
                    Iterator<Place> cIt = placeList.iterator();
                    while (cIt.hasNext()){
                        Place p = cIt.next();
                        if(!p.getAddress().getCity().equals(args[0])){
                            newCPlaceList.remove(p);
                        } 
                    }
                    cinemas = new Cinema[newCPlaceList.size()];
                    cinemas = newCPlaceList.toArray(cinemas);

					CinemaWindow window = new CinemaWindow(cinemas);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace(); 
				}
			}		});
	}

	/**
	 * Create the application.
	 */
	public CinemaWindow(Cinema[] cinemas) {
		initialize(cinemas);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Cinema[] cinema) {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 700, 600);
		String data[][] = new String[cinemas.length][3];
		for (int i = 0; i < cinemas.length; i++) {
			for (int j = 0; j < 3; j++) {
				if (j == 0) {
					data[i][j] = cinemas[i].getName();
				}
				if (j == 1) {
					data[i][j] = cinemas[i].getAddress().toString();
				}
				if (j == 2) {
					if (cinemas[i].isImax()) {
						data[i][j] = "		O";
					} else{
						data[i][j] = "		x";
					}
				}
			}

		}
		String comboCinema[] = new String[cinemas.length];
		for (int i = 0; i < comboCinema.length; i++) {
			comboCinema[i] = cinemas[i].getName();
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
		String column[] = { "Name", "Address", "Imax" };
		frame.getContentPane().setLayout(null);
		JTable jt = new JTable(data, column);
		jt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jt.setColumnSelectionAllowed(true);
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jt.getColumnModel().getColumn(0).setPreferredWidth(200);
		jt.getColumnModel().getColumn(1).setPreferredWidth(400);
		jt.getColumnModel().getColumn(2).setPreferredWidth(100);

		JScrollPane sp = new JScrollPane(jt);
		sp.setBounds(0, 0, 710, 400);
		frame.getContentPane().add(sp);

		JComboBox comboBox = new JComboBox(comboCinema);
		comboBox.setBounds(241, 414, 213, 22);
		frame.getContentPane().add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cinemaSelect = comboBox.getItemAt(comboBox.getSelectedIndex());
				Cinema tempCinema=null;
				for (int i = 0; i < cinemas.length; i++) {
					if ((cinemas[i].getName()).equals(cinemaSelect)) {
						tempCinema = cinemas[i];
						break;
					}
				}
				PlaceDisplayerWindow.setPlace(tempCinema);
				PlaceDisplayerWindow.main("cinema");

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

		JLabel lblNewLabel = new JLabel("Choose your cinema here ---->    ");
		lblNewLabel.setBounds(38, 413, 172, 25);
		frame.getContentPane().add(lblNewLabel);
		frame.setSize(715, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
