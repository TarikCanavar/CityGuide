package windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.border.BevelBorder;

import CityGuide.CityGuide;
import places.Cafe;
import places.Cinema;
import places.Landscape;
import places.Museum;
import places.NightSpot;
import places.Place;
import places.Restaurant;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JSeparator;

public class MainWindow {
	Object citySelect;
	private JFrame frame;
	private static String loginInfo;
	private String typeToDelete;
	private String nameToDelete;
	private boolean flag = false;
	/**
	 * Launch the application.
	 */
	public static void main(String arg) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow(arg);
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
	public MainWindow(String arg) {
		sendInfo(arg);
		initialize(arg);

	}

	public void sendInfo(String arg) {
		loginInfo = arg;
	}

	public static String getInfo() {
		return loginInfo;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String arg) {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		String city[] = { "Choose", "İzmir", "İstanbul" };
		JComboBox cityBox = new JComboBox(city);
		cityBox.setFont(new Font("Tahoma", Font.PLAIN, 21));
		cityBox.setBounds(233, 37, 232, 46);
		cityBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				citySelect = cityBox.getItemAt(cityBox.getSelectedIndex());
				if (citySelect.equals("Choose")) {
					JOptionPane.showMessageDialog(frame, "Please choose a city.");
				}

			}

		});

		frame.getContentPane().add(cityBox);

		String types[] = {"Choose" ,"Cafe", "Cinema", "Restaurant", "Landscape", "Museum", "NightSpot" };
		JComboBox typeBox = new JComboBox(types);

		typeBox.setFont(new Font("Tahoma", Font.PLAIN, 21));
		typeBox.setBounds(233, 94, 232, 46);

		typeBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] ar = new String[1];
				ar[0] = (String) citySelect;
				if(typeBox.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(frame, "Please choose a place type.");				
				}
				else if(citySelect == null) {
					JOptionPane.showMessageDialog(frame, "Please choose a city.");
				}
				else if(citySelect != null && citySelect.equals("Choose")) {
					JOptionPane.showMessageDialog(frame, "Please choose a city.");
				}
				else {
					switch (typeBox.getSelectedIndex()) {
					
					case 1:
	
						CafeWindow.main(ar);
						break;
	
					case 2:
						CinemaWindow.main(ar);
						break;
					case 3:
	
						RestaurantWindow.main(ar);
						break;
					case 4:
	
						LandscapeWindow.main(ar);
						break;
					case 5:
	
						MuseumWindow.main(ar);
						break;
					case 6:
	
						NightSpotWindow.main(ar);
						break;
	
					}
	
					frame.dispose();
				}
			}
		}); 

		frame.getContentPane().add(typeBox);

		JLabel logoLabel = new JLabel("");
		logoLabel.setBounds(549, 37, 400, 400);
		frame.getContentPane().add(logoLabel);
		ImageIcon logo = new ImageIcon("Image/logo2.png");
		Image img = logo.getImage();
		Image modifImage = img.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
		logo = new ImageIcon(modifImage);
		logoLabel.setIcon(logo);

		JLabel lblNewLabel = new JLabel("Choose city :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel.setBounds(35, 37, 136, 46);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Choose place type :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel_1.setBounds(35, 94, 232, 46);
		frame.getContentPane().add(lblNewLabel_1);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				JFrame exit = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(exit, "Do you want to exit?","City Guide",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
				frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
 
			}
		});
		if (arg.equals("admin")) {
			String places[] = {"Cafe", "Cinema", "Restaurant", "Landscape", "Museum", "NightSpot" };
			JComboBox comboBox = new JComboBox(places);
			comboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
			comboBox.setBounds(233, 182, 232, 46);
			frame.getContentPane().add(comboBox);
			JButton btnNewButton = new JButton("Add new place");
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 21));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String item = (String) comboBox.getItemAt(comboBox.getSelectedIndex());
					AddPlaceWindow.main(item);
				}
			});
			btnNewButton.setBounds(233, 245, 232, 39);
			frame.getContentPane().add(btnNewButton);
			JComboBox deletePlace = new JComboBox(places);
			deletePlace.setBounds(45, 331, 159, 39);
			frame.getContentPane().add(deletePlace);
			JComboBox deletePlace2 = new JComboBox();
			deletePlace2.setBounds(45, 390, 159, 39);
			frame.getContentPane().add(deletePlace2);
			deletePlace2.setVisible(false);

			deletePlace2.setBounds(45, 390, 159, 39);
			frame.getContentPane().add(deletePlace2);
			
			deletePlace.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					String item = (String) deletePlace.getItemAt(deletePlace.getSelectedIndex());
					item = item.toLowerCase();

					deletePlace2.setVisible(true);
					String[] placesCombo=new String[CityGuide.getInstance().getPlaceMap().get(item + "s").size()];
					Iterator<Place> placeIt = CityGuide.getInstance().getPlaceMap().get(item + "s").iterator();
					for (int i = 0; placeIt.hasNext(); i++) {
						placesCombo[i] = placeIt.next().getName();
					}
					deletePlace2.removeAllItems();
					for(String s:placesCombo){
						deletePlace2.addItem(s); 
					}
					flag = true;
				}
			});
			JButton btnDeletePlace = new JButton("Delete place");
			btnDeletePlace.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(flag) {
						CityGuide.getInstance().deletePlace(((String) deletePlace.getItemAt(deletePlace.getSelectedIndex())).toLowerCase(), (String) deletePlace2.getItemAt(deletePlace2.getSelectedIndex()));
						if(deletePlace2.getItemCount() != 0) {
							JOptionPane.showMessageDialog(frame, "Delete successful.");
							String item = (String) deletePlace.getItemAt(deletePlace.getSelectedIndex());
							item = item.toLowerCase();
							String[] placesCombo=new String[CityGuide.getInstance().getPlaceMap().get(item + "s").size()];
							Iterator<Place> placeIt = CityGuide.getInstance().getPlaceMap().get(item + "s").iterator();
							for (int i = 0; placeIt.hasNext(); i++) {
								placesCombo[i] = placeIt.next().getName();
							}
							deletePlace2.removeAllItems();
							for(String s:placesCombo){
								deletePlace2.addItem(s);
							}
						}
						else {
							JOptionPane.showMessageDialog(frame, "No item to delete.");
						}
						
					}
					else {
						JOptionPane.showMessageDialog(frame, "Please select a type.");
					}
				
				}
			});
			btnDeletePlace.setFont(new Font("Tahoma", Font.PLAIN, 21));
			btnDeletePlace.setBounds(277, 331, 190, 39);
			frame.getContentPane().add(btnDeletePlace);
			
			JLabel lblNewLabel_2 = new JLabel("Choose place type :");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblNewLabel_2.setBounds(35, 186, 175, 42);
			frame.getContentPane().add(lblNewLabel_2);
			
			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(45, 151, 420, 2);
			frame.getContentPane().add(separator_1);
			
			JSeparator separator_2 = new JSeparator();
			separator_2.setBounds(35, 307, 420, 2);
			frame.getContentPane().add(separator_2);

		}
	}
}