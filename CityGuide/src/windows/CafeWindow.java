package windows;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import CityGuide.CityGuide;
import CityGuide.JSONFileHandler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import places.Cafe;
import places.Place;
import placeAttributes.*;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.xml.crypto.Data;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class CafeWindow {

	private static Cafe[] cafes;
	Object cafeSelect;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArrayList<Place> placeList = CityGuide.getInstance().getPlaceMap().get("cafes");
                    ArrayList<Place> newCPlaceList = (ArrayList<Place>) placeList.clone();
                    Iterator<Place> cIt = placeList.iterator();
                    while (cIt.hasNext()){
                        Place p = cIt.next();
                        if(!p.getAddress().getCity().equals(args[0])){
                            newCPlaceList.remove(p);
                        }
                    }
                    cafes = new Cafe[newCPlaceList.size()];
                    cafes = newCPlaceList.toArray(cafes);
					CafeWindow window = new CafeWindow(cafes);
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
	public CafeWindow(Cafe[] cafes) {
		initialize(cafes);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Cafe[] cafes) {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 800, 600);
		String data[][] = new String[cafes.length][3];
		for (int i = 0; i < cafes.length; i++) {
			for (int j = 0; j < 3; j++) {
				if (j == 0) {
					data[i][j] = cafes[i].getName();
				}
				if (j == 1) {
					data[i][j] = cafes[i].getAddress().toString();
				}
				if (j == 2) {
					data[i][j] = cafes[i].getPhoneNumber().toString();
				}
			}

		}
		String comboCafe[] = new String[cafes.length];
		for (int i = 0; i < comboCafe.length; i++) {
			comboCafe[i] = cafes[i].getName();
		}

		String column[] = { "Name", "Address", "Phone Number" };
		frame.getContentPane().setLayout(null);
		JTable jt = new JTable(data, column);
		jt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jt.setBackground(Color.WHITE);
		jt.setColumnSelectionAllowed(true);
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jt.getColumnModel().getColumn(0).setPreferredWidth(200);
		jt.getColumnModel().getColumn(1).setPreferredWidth(400);
		jt.getColumnModel().getColumn(2).setPreferredWidth(200);

		JScrollPane sp = new JScrollPane(jt);
		sp.setBounds(0, 0, 815, 400);
		frame.getContentPane().add(sp);

		JComboBox comboBox = new JComboBox(comboCafe);
		comboBox.setBounds(241, 414, 213, 22);
		frame.getContentPane().add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cafeSelect = comboBox.getItemAt(comboBox.getSelectedIndex());
				Cafe tempCafe=null;
				for (int i = 0; i < cafes.length; i++) {
					if ((cafes[i].getName()).equals(cafeSelect)) {
						tempCafe = cafes[i];
						break;
					}
				}
				PlaceDisplayerWindow.setPlace(tempCafe);
				PlaceDisplayerWindow.main("cafe");

			}

		});
 


		JLabel lblNewLabel = new JLabel("Choose your cafe here ---->    ");
		lblNewLabel.setBounds(38, 413, 172, 25);
		frame.getContentPane().add(lblNewLabel);
		
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
		returnButton.setBounds(519, 410, 139, 30);
		frame.getContentPane().add(returnButton);
		frame.setSize(815, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}