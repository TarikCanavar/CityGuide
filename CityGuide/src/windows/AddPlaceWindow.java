package windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import CityGuide.CityGuide;
import CityGuide.PlaceholderTextField;
import login.Login;
import login.LoginWindow;
import placeAttributes.Address;
import placeAttributes.Comment;
import placeAttributes.PhoneNumber;
import places.*;

import javax.swing.JComboBox;
import javax.lang.model.type.NullType;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.event.ActionEvent;

public class AddPlaceWindow {

	private JFrame frame;
	private JTextField name;
	private JLabel lblType;
	private JLabel lblPhoneNumber;
	private JTextField phoneNumber;
	private JTextField lType;
	private JTextField mType;
	private JTextField txtDistrict;
	private JTextField txtNeighborhood;
	private JTextField txtStreet;
	private JTextField txtDoorNumber;
	private JTextField txtCode;
	private JTextField txtNumber;
	private PhoneNumber cafeNumber;
	private boolean imaxChoice;
	private boolean landFee;
	private boolean alcohol;
	private boolean liveMusic;
	private JComboBox imaxBox;
	private JComboBox liveMusicBox;
	private JComboBox alcoholBox;
	private JComboBox feeBox;
	/**
	 * Launch the application.
	 */
	public static void main(String arg) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPlaceWindow window = new AddPlaceWindow(arg);
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
	public AddPlaceWindow(String arg) {
		initialize(arg);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String arg) {
		String[] yesNo = new String[2];
		yesNo[0] = "Yes";
		yesNo[1] = "No";

		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 850, 432);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});

		JLabel nameLabel = new JLabel("Name of the " + arg + " :");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		nameLabel.setBounds(34, 38, 291, 49);
		frame.getContentPane().add(nameLabel);

		name = new JTextField();
		name.setBounds(362, 46, 216, 41);
		frame.getContentPane().add(name);
		name.setColumns(10);

		JLabel addLabel = new JLabel("Address of the " + arg + " :");
		addLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		addLabel.setBounds(34, 98, 291, 49);
		frame.getContentPane().add(addLabel);

		txtDistrict = new PlaceholderTextField();
		((PlaceholderTextField) txtDistrict).setPlaceholder("District");
		txtDistrict.setColumns(10);
		txtDistrict.setBounds(403, 113, 95, 27);
		frame.getContentPane().add(txtDistrict);

		txtNeighborhood = new PlaceholderTextField();
		((PlaceholderTextField) txtNeighborhood).setPlaceholder("neighborhood");
		txtNeighborhood.setColumns(10);
		txtNeighborhood.setBounds(508, 113, 95, 27);
		frame.getContentPane().add(txtNeighborhood);

		txtStreet = new PlaceholderTextField();
		((PlaceholderTextField) txtStreet).setPlaceholder("Street");
		txtStreet.setColumns(10);
		txtStreet.setBounds(613, 113, 95, 27);
		frame.getContentPane().add(txtStreet);

		txtDoorNumber = new PlaceholderTextField();
		((PlaceholderTextField) txtDoorNumber).setPlaceholder("Door Number");
		txtDoorNumber.setColumns(10);
		txtDoorNumber.setBounds(718, 113, 95, 27);
		frame.getContentPane().add(txtDoorNumber);

		JButton addPlaceButton = new JButton("Add place");

		addPlaceButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		addPlaceButton.setBounds(676, 284, 124, 49);
		frame.getContentPane().add(addPlaceButton);
		
		String cityArray[] = {"Choose", "İzmir","İstanbul"};
		JComboBox cityBox = new JComboBox(cityArray);
		cityBox.setBounds(307, 113, 86, 27);
		frame.getContentPane().add(cityBox);

		if (arg.equals("NightSpot")) {
			JLabel lblAlcohol = new JLabel("Alcohol :");
			lblAlcohol.setFont(new Font("Tahoma", Font.PLAIN, 21));
			lblAlcohol.setBounds(34, 158, 193, 49);
			frame.getContentPane().add(lblAlcohol);

			alcoholBox = new JComboBox(yesNo);
			alcoholBox.setBounds(306, 170, 144, 32);
			frame.getContentPane().add(alcoholBox);
			
			JLabel liveCombo = new JLabel("Live Music : ");
			liveCombo.setFont(new Font("Tahoma", Font.PLAIN, 21));
			liveCombo.setBounds(34, 218, 193, 49);
			frame.getContentPane().add(liveCombo);

			liveMusicBox= new JComboBox(yesNo);
			liveMusicBox.setBounds(306, 230, 144, 32);
			frame.getContentPane().add(liveMusicBox);
			

		} else if (arg.equals("Cafe")) {
			lblPhoneNumber = new JLabel("Phone number of " + arg + " :");
			lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 21));
			lblPhoneNumber.setBounds(34, 158, 314, 49);
			frame.getContentPane().add(lblPhoneNumber);

			phoneNumber = new PlaceholderTextField();
			((PlaceholderTextField) phoneNumber).setPlaceholder("Country Code");
			phoneNumber.setBounds(403, 166, 124, 41);
			frame.getContentPane().add(phoneNumber);
			phoneNumber.setColumns(10);

			txtCode = new PlaceholderTextField();
			((PlaceholderTextField) txtCode).setPlaceholder("Code");
			txtCode.setColumns(10);
			txtCode.setBounds(542, 166, 124, 41);
			frame.getContentPane().add(txtCode);

			txtNumber = new PlaceholderTextField();
			((PlaceholderTextField) txtNumber).setPlaceholder("Number");
			txtNumber.setColumns(10);
			txtNumber.setBounds(676, 166, 124, 41);
			frame.getContentPane().add(txtNumber);

		} else if (arg.equals("Cinema")) {
			JLabel imax = new JLabel("Imax :");
			imax.setFont(new Font("Tahoma", Font.PLAIN, 21));
			imax.setBounds(34, 158, 193, 49);
			frame.getContentPane().add(imax);

			imaxBox = new JComboBox(yesNo);
			imaxBox.setBounds(306, 170, 144, 32);
			frame.getContentPane().add(imaxBox);
			
		} else if (arg.equals("Landscape")) {
			lblType = new JLabel("Type of " + arg + " :");
			lblType.setFont(new Font("Tahoma", Font.PLAIN, 21));
			lblType.setBounds(34, 158, 193, 49);
			frame.getContentPane().add(lblType);

			lType = new JTextField();
			lType.setBounds(306, 170, 144, 32);
			frame.getContentPane().add(lType);
			lType.setColumns(10);
			JLabel fee = new JLabel("Has fee :");
			fee.setFont(new Font("Tahoma", Font.PLAIN, 21));
			fee.setBounds(34, 218, 193, 49);
			frame.getContentPane().add(fee);

			feeBox = new JComboBox(yesNo);
			feeBox.setBounds(306, 230, 144, 32);
			frame.getContentPane().add(feeBox);
			
		} else if (arg.equals("Museum")) {
			lblType = new JLabel("Type of " + arg + " :");
			lblType.setFont(new Font("Tahoma", Font.PLAIN, 21));
			lblType.setBounds(34, 158, 291, 49);
			frame.getContentPane().add(lblType);

			mType = new JTextField();
			mType.setBounds(362, 166, 216, 41);
			frame.getContentPane().add(mType);
			mType.setColumns(10);
		}
		addPlaceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(cityBox.getSelectedIndex()==0) {
						JOptionPane.showMessageDialog(frame, "Please choose a place type.");				
					}
					else {
						Address placeAddress = new Address((String)cityBox.getItemAt(cityBox.getSelectedIndex()), txtDistrict.getText(),
								txtNeighborhood.getText(), txtStreet.getText(), Integer.parseInt(txtDoorNumber.getText()));
						switch (arg) {
						case "Cafe": {
							if(name.getText().length() > 0 && txtDistrict.getText().length() > 0 && txtNeighborhood.getText().length() > 0 && txtStreet.getText().length() > 0) {
								cafeNumber = new PhoneNumber(Integer.parseInt(txtCode.getText()),
										Integer.parseInt(phoneNumber.getText()), Integer.parseInt(txtNumber.getText()));
								Cafe cafe = new Cafe(name.getText(), placeAddress, cafeNumber);
								CityGuide.getInstance().getPlaceMap().get("cafes").add(cafe);
								CityGuide.getInstance().getPlaceMapId().put(cafe.getID(), cafe);
								JSONParser parser = new JSONParser();
								try {
								
									JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("cafes.json"));
									JSONArray placeList = (JSONArray) jsonObject.get("cafes");

									JSONObject placeAttributes = new JSONObject();

									placeAttributes.put("name", cafe.getName());
									placeAttributes.put("city", cafe.getAddress().getCity());
									placeAttributes.put("district", cafe.getAddress().getDistrict());
									placeAttributes.put("neighborhood", cafe.getAddress().getDistrict());
									placeAttributes.put("street", cafe.getAddress().getStreet());

									placeAttributes.put("doorNumber", cafe.getAddress().getDoorNumber());
									placeAttributes.put("countryCode", cafe.getPhoneNumber().getCountryCode());
									placeAttributes.put("code", cafe.getPhoneNumber().getCode());
									placeAttributes.put("number", cafe.getPhoneNumber().getNumber());

									placeList.add(placeAttributes);
									JSONObject newRecords = new JSONObject();
									newRecords.put("cafes", placeList);
									try (FileWriter file = new FileWriter("cafes.json")) {
										file.write(newRecords.toString());
										file.flush();

									} catch (Exception e1) {
										System.out.println(e1);
									}

								} catch (Exception e1) {

									e1.printStackTrace();
								}
								JOptionPane.showMessageDialog(frame, "Add successful.");
							}
							else {
								JOptionPane.showMessageDialog(frame, "Fields cannot be empty.");
							}
							

							break;
						}
						case "Cinema": {
							if(name.getText().length() > 0 && txtDistrict.getText().length() > 0 && txtNeighborhood.getText().length() > 0 && txtStreet.getText().length() > 0) {
								if (imaxBox.getItemAt(imaxBox.getSelectedIndex()).equals("Yes")) {							
									imaxChoice = true;
								} else {							
									imaxChoice = false;
								}
								Cinema cinema = new Cinema(name.getText(), placeAddress, imaxChoice);
								CityGuide.getInstance().getPlaceMap().get("cinemas").add(cinema);
								CityGuide.getInstance().getPlaceMapId().put(cinema.getID(), cinema);
								JSONParser parser = new JSONParser();
								try {

									JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("cinemas.json"));
									JSONArray placeList = (JSONArray) jsonObject.get("cinemas");

									JSONObject placeAttributes = new JSONObject();

									placeAttributes.put("name", cinema.getName());
									placeAttributes.put("city", cinema.getAddress().getCity());
									placeAttributes.put("district", cinema.getAddress().getDistrict());
									placeAttributes.put("neighborhood", cinema.getAddress().getDistrict());
									placeAttributes.put("street", cinema.getAddress().getStreet());

									placeAttributes.put("doorNumber", cinema.getAddress().getDoorNumber());
									placeAttributes.put("imax", cinema.isImax());

									placeList.add(placeAttributes);
									JSONObject newRecords = new JSONObject();
									newRecords.put("cinemas", placeList);
									try (FileWriter file = new FileWriter("cinemas.json")) {
										file.write(newRecords.toString());
										file.flush();

									} catch (Exception e1) {
										System.out.println(e1);
									}

								} catch (Exception e1) {

									e1.printStackTrace();
								}
								JOptionPane.showMessageDialog(frame, "Add successful.");
							}
							else {
								JOptionPane.showMessageDialog(frame, "Fields cannot be empty.");
							}
							break;
						
						}
						case "Restaurant": {
							if(name.getText().length() > 0 && txtDistrict.getText().length() > 0 && txtNeighborhood.getText().length() > 0 && txtStreet.getText().length() > 0) {
								Restaurant restaurant = new Restaurant(name.getText(), placeAddress);
								CityGuide.getInstance().getPlaceMap().get("restaurants").add(restaurant);
								CityGuide.getInstance().getPlaceMapId().put(restaurant.getID(), restaurant);
								JSONParser parser = new JSONParser();
								try {

									JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("restaurants.json"));
									JSONArray placeList = (JSONArray) jsonObject.get("restaurants");

									JSONObject placeAttributes = new JSONObject();

									placeAttributes.put("name", restaurant.getName());
									placeAttributes.put("city", restaurant.getAddress().getCity());
									placeAttributes.put("district", restaurant.getAddress().getDistrict());
									placeAttributes.put("neighborhood", restaurant.getAddress().getDistrict());
									placeAttributes.put("street", restaurant.getAddress().getStreet());

									placeAttributes.put("doorNumber", restaurant.getAddress().getDoorNumber());

									placeList.add(placeAttributes);
									JSONObject newRecords = new JSONObject();
									newRecords.put("restaurants", placeList);
									try (FileWriter file = new FileWriter("restaurants.json")) {
										file.write(newRecords.toString());
										file.flush();

									} catch (Exception e1) {
										System.out.println(e1);
									}

								} catch (Exception e1) {

									e1.printStackTrace();
								}
								JOptionPane.showMessageDialog(frame, "Add successful.");
							}
							else {
								JOptionPane.showMessageDialog(frame, "Fields cannot be empty.");
							}
							break;
							
						}
						case "Landscape": {
							if(name.getText().length() > 0 && txtDistrict.getText().length() > 0 && txtNeighborhood.getText().length() > 0 && txtStreet.getText().length() > 0) {
								if (feeBox.getItemAt(feeBox.getSelectedIndex()).equals("Yes")) {
									landFee = true;
								} else {
									landFee = false;
								}
								Landscape landscape = new Landscape(name.getText(), placeAddress, lType.getText(), landFee);
								CityGuide.getInstance().getPlaceMap().get("landscapes").add(landscape);
								CityGuide.getInstance().getPlaceMapId().put(landscape.getID(), landscape);
								JSONParser parser = new JSONParser();
								try {

									JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("landscapes.json"));
									JSONArray placeList = (JSONArray) jsonObject.get("landscapes");

									JSONObject placeAttributes = new JSONObject();

									placeAttributes.put("name", landscape.getName());
									placeAttributes.put("city", landscape.getAddress().getCity());
									placeAttributes.put("district", landscape.getAddress().getDistrict());
									placeAttributes.put("neighborhood", landscape.getAddress().getDistrict());
									placeAttributes.put("street", landscape.getAddress().getStreet());

									placeAttributes.put("doorNumber", landscape.getAddress().getDoorNumber());
									placeAttributes.put("fee", landscape.hasFee());
									placeAttributes.put("type", landscape.getType());

									placeList.add(placeAttributes);
									JSONObject newRecords = new JSONObject();
									newRecords.put("landscapes", placeList);
									try (FileWriter file = new FileWriter("landscapes.json")) {
										file.write(newRecords.toString());
										file.flush();

									} catch (Exception e1) {
										System.out.println(e1);
									}

								} catch (Exception e1) {

									e1.printStackTrace();
								}
								JOptionPane.showMessageDialog(frame, "Add successful.");
							}
														
							else {
								JOptionPane.showMessageDialog(frame, "Fields cannot be empty.");
							}
							break;
						}
						case "Museum": {
							if(mType.getText().length() > 0 && name.getText().length() > 0 && txtDistrict.getText().length() > 0 && txtNeighborhood.getText().length() > 0 && txtStreet.getText().length() > 0) {
								System.out.println(mType.getText());
								Museum museum = new Museum(name.getText(), placeAddress, mType.getText());
								CityGuide.getInstance().getPlaceMap().get("museums").add(museum);
								CityGuide.getInstance().getPlaceMapId().put(museum.getID(), museum);
								JSONParser parser = new JSONParser();
								try {

									JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("museums.json"));
									JSONArray placeList = (JSONArray) jsonObject.get("museums");

									JSONObject placeAttributes = new JSONObject();

									placeAttributes.put("name", museum.getName());
									placeAttributes.put("city", museum.getAddress().getCity());
									placeAttributes.put("district", museum.getAddress().getDistrict());
									placeAttributes.put("neighborhood", museum.getAddress().getDistrict());
									placeAttributes.put("street", museum.getAddress().getStreet());

									placeAttributes.put("doorNumber", museum.getAddress().getDoorNumber());

									placeAttributes.put("type", museum.getType());

									placeList.add(placeAttributes);
									JSONObject newRecords = new JSONObject();
									newRecords.put("museums", placeList);
									try (FileWriter file = new FileWriter("museums.json")) {
										file.write(newRecords.toString());
										file.flush();

									} catch (Exception e1) {
										System.out.println(e1);
									}

								} catch (Exception e1) {

									e1.printStackTrace();
								}
								JOptionPane.showMessageDialog(frame, "Add successful.");
								
							}
							else {
								JOptionPane.showMessageDialog(frame, "Fields cannot be empty.");
							}
							break;
						
						}
						case "NightSpot": {
							if(name.getText().length() > 0 && txtDistrict.getText().length() > 0 && txtNeighborhood.getText().length() > 0 && txtStreet.getText().length() > 0) {
								if (liveMusicBox.getItemAt(liveMusicBox.getSelectedIndex()).equals("Yes")) {
									liveMusic = true;
								} else {
									liveMusic = false;
								}
								if (alcoholBox.getItemAt(alcoholBox.getSelectedIndex()).equals("Yes")) {
									alcohol = true;
								} else {
									alcohol = false;
								}
								NightSpot nightSpot = new NightSpot(name.getText(), placeAddress, alcohol, liveMusic);
								CityGuide.getInstance().getPlaceMap().get("nightspots").add(nightSpot);
								CityGuide.getInstance().getPlaceMapId().put(nightSpot.getID(), nightSpot);
								JSONParser parser = new JSONParser();
								try {

									JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("nightspots.json"));
									JSONArray placeList = (JSONArray) jsonObject.get("nightspots");

									JSONObject placeAttributes = new JSONObject();

									placeAttributes.put("name", nightSpot.getName());
									placeAttributes.put("city", nightSpot.getAddress().getCity());
									placeAttributes.put("district", nightSpot.getAddress().getDistrict());
									placeAttributes.put("neighborhood", nightSpot.getAddress().getDistrict());
									placeAttributes.put("street", nightSpot.getAddress().getStreet());

									placeAttributes.put("doorNumber", nightSpot.getAddress().getDoorNumber());

									placeAttributes.put("alcohol", nightSpot.isAlcohol());
									placeAttributes.put("liveMusic", nightSpot.isLiveMusic());

									placeList.add(placeAttributes);
									JSONObject newRecords = new JSONObject();
									newRecords.put("nightspots", placeList);
									try (FileWriter file = new FileWriter("nightspots.json")) {
										file.write(newRecords.toString());
										file.flush();

									} catch (Exception e1) {
										System.out.println(e1);
									}

								} catch (Exception e1) {
		 
									e1.printStackTrace();
								}
								JOptionPane.showMessageDialog(frame, "Add successful.");
							}
							else {
								JOptionPane.showMessageDialog(frame, "Fields cannot be empty.");
							}
							break;
						}
						}
						
					}
				
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(frame, "Please check the information given.");
					
				}

			}
		});
	}
}