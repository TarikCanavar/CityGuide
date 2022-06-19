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
import places.Place;
import places.Restaurant;
import java.awt.Font;

public class RestaurantWindow {
	private static Restaurant[] restaurants;
	Object restaurantSelect;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArrayList<Place> placeList = CityGuide.getInstance().getPlaceMap().get("restaurants");
                    ArrayList<Place> newCPlaceList = (ArrayList<Place>) placeList.clone();
                    Iterator<Place> cIt = placeList.iterator();
                    while (cIt.hasNext()){
                        Place p = cIt.next();
                        if(!p.getAddress().getCity().equals(args[0])){
                            newCPlaceList.remove(p);
                        }
                    }
                    restaurants = new Restaurant[newCPlaceList.size()];
                    restaurants = newCPlaceList.toArray(restaurants);
					RestaurantWindow window = new RestaurantWindow(restaurants);
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
	public RestaurantWindow(Restaurant[] restaurants) {
		initialize(restaurants);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Restaurant[] restaurants) {
			frame = new JFrame();
			frame.setResizable(false);
			frame.setBounds(100, 100, 700, 600);
			String data[][] = new String[restaurants.length][2];
			for (int i = 0; i < restaurants.length; i++) {
				for (int j = 0; j < 2; j++) {
					if (j == 0) {
						data[i][j] = restaurants[i].getName();
					}
					if (j == 1) {
						data[i][j] = restaurants[i].getAddress().toString();
					}
				}

			}
			String comboRestaurant[] = new String[restaurants.length];
			for (int i = 0; i < comboRestaurant.length; i++) {
				comboRestaurant[i] = restaurants[i].getName();
			}
			
			String column[] = { "Name", "Address"};
			frame.getContentPane().setLayout(null);
			JTable jt = new JTable(data, column);
			jt.setFont(new Font("Tahoma", Font.PLAIN, 18));
			jt.setColumnSelectionAllowed(true);
			jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			jt.getColumnModel().getColumn(0).setPreferredWidth(200);
			jt.getColumnModel().getColumn(1).setPreferredWidth(500);


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
				}
			});
			returnButton.setBounds(519, 410, 139, 30);
			frame.getContentPane().add(returnButton);
			JComboBox comboBox = new JComboBox(comboRestaurant);
			comboBox.setBounds(241, 414, 213, 22);
			frame.getContentPane().add(comboBox);
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					restaurantSelect = comboBox.getItemAt(comboBox.getSelectedIndex());
					Restaurant tempRestaurant=null;
					for (int i = 0; i < restaurants.length; i++) {
						if ((restaurants[i].getName()).equals(restaurantSelect)) {
							tempRestaurant = restaurants[i];
							break;
						}
					}
					PlaceDisplayerWindow.setPlace(tempRestaurant);
					PlaceDisplayerWindow.main("restaurant");

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


			JLabel lblNewLabel = new JLabel("Choose your cafe here ---->    ");
			lblNewLabel.setBounds(38, 413, 172, 25);
			frame.getContentPane().add(lblNewLabel);
			frame.setSize(715, 500);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		} 

}
