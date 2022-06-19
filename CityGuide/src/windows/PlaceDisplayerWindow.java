package windows;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import CityGuide.CityGuide;
import places.Cinema;
import places.Landscape;
import places.Museum;
import places.NightSpot;
import places.Place;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import login.Login;
import login.LoginWindow;
import placeAttributes.Comment;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PlaceDisplayerWindow {

	private JFrame frame;
	private static Place place;
	private JTextField commentField;
	private static String data[][];
	private static String mail[];

	/**
	 * Launch the application.
	 */
	public static void main(String arg) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlaceDisplayerWindow window = new PlaceDisplayerWindow(arg);
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
	public PlaceDisplayerWindow(String arg) {
		initialize(arg);
	}

	public static void setPlace(Place place2) {
		place = place2;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void update() {
		ArrayList<Comment> aList = CityGuide.getInstance().getPlaceComments(place.getName());
		if (aList != null) {
			data = new String[aList.size()][4];
			mail = new String[aList.size()];
			for (int i = 0; i < aList.size(); i++) {
				for (int j = 0; j < 4; j++) {
					switch (j) {
					case 0:
						data[i][j] = aList.get(i).getName() + " " + aList.get(i).getSurname();
						break;
					case 1:
						data[i][j] = aList.get(i).getContent();
						break;
					case 2:
						data[i][j] = aList.get(i).getRateAsString();
						break;
					case 3:
						data[i][j] = aList.get(i).getMailAddress();
						mail[i] = aList.get(i).getMailAddress();
						break;
					}

				}

			}

		} else {
			data = null;
		}
	}

	private void initialize(String arg) {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1000, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel nameLabel = new JLabel("Name : ");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nameLabel.setBounds(10, 11, 66, 23);
		frame.getContentPane().add(nameLabel);

		JLabel addressLabel = new JLabel("Address : ");
		addressLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		addressLabel.setBounds(10, 60, 83, 23);
		frame.getContentPane().add(addressLabel);

		JLabel ratingLabel = new JLabel("Average Rating : ");
		ratingLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ratingLabel.setBounds(10, 106, 145, 23);
		frame.getContentPane().add(ratingLabel);

		JLabel placeLabel = new JLabel(place.getName());
		placeLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		placeLabel.setBounds(86, 11, 505, 23);
		frame.getContentPane().add(placeLabel);

		JLabel placeAdressLabel = new JLabel(place.getAddress().toString());
		placeAdressLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		placeAdressLabel.setBounds(101, 60, 490, 23);
		frame.getContentPane().add(placeAdressLabel);

		JLabel placeRatingLabel = new JLabel(String.valueOf(place.getRating()));
		placeRatingLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		placeRatingLabel.setBounds(165, 106, 76, 23);
		frame.getContentPane().add(placeRatingLabel); 

		if (arg.equals("cinema")) {
			JLabel imaxLabel = new JLabel("Has imax ? : ");
			imaxLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			imaxLabel.setBounds(600, 11, 120, 21);
			frame.getContentPane().add(imaxLabel);
			String imax = "";
			if (((Cinema) place).isImax()) {
				imax = "Yes";
			} else {
				imax = "No";
			} 
			JLabel imaxReturnLabel = new JLabel(imax);
			imaxReturnLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			imaxReturnLabel.setBounds(700, 11, 30, 21);
			frame.getContentPane().add(imaxReturnLabel);

		} else if (arg.equals("landscape")) {
			JLabel ltypeLabel = new JLabel("Type : ");
			ltypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			ltypeLabel.setBounds(600, 11, 120, 21);
			frame.getContentPane().add(ltypeLabel);
			String type = ((Landscape) place).getType();
			JLabel ltypeReturnLabel = new JLabel(type);
			ltypeReturnLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			ltypeReturnLabel.setBounds(700, 11, 180, 21);
			frame.getContentPane().add(ltypeReturnLabel);

			JLabel feeLabel = new JLabel("Has fee : ");
			feeLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			feeLabel.setBounds(600, 62, 120, 25);
			frame.getContentPane().add(feeLabel);
			String fee = "";
			if ((((Landscape) place).hasFee())) {
				fee = "Yes";
			} else {
				fee = "No";
			}
			JLabel feeReturnLabel = new JLabel(fee);
			feeReturnLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			feeReturnLabel.setBounds(700, 63, 120, 20);
			frame.getContentPane().add(feeReturnLabel);

		} else if (arg.equals("museum")) {
			JLabel mtypeLabel = new JLabel("Type : ");
			mtypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			mtypeLabel.setBounds(600, 11, 120, 21);
			frame.getContentPane().add(mtypeLabel);
			String type = ((Museum) place).getType();
			JLabel mtypeReturnLabel = new JLabel(type);
			mtypeReturnLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			mtypeReturnLabel.setBounds(700, 11, 180, 21);
			frame.getContentPane().add(mtypeReturnLabel);

		} else if (arg.equals("nightspot")) {
			JLabel ntypeLabel = new JLabel("Alcohol : ");
			ntypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			ntypeLabel.setBounds(600, 11, 120, 21);
			frame.getContentPane().add(ntypeLabel);
			String alcohol;
			if ((((NightSpot) place).isAlcohol())) {
				alcohol = "Yes";
			} else {
				alcohol = "No";
			}
			JLabel ntypeReturnLabel = new JLabel(alcohol);
			ntypeReturnLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			ntypeReturnLabel.setBounds(700, 11, 180, 21);
			frame.getContentPane().add(ntypeReturnLabel);

			JLabel liveLabel = new JLabel("Live music : ");
			liveLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			liveLabel.setBounds(600, 62, 120, 25);
			frame.getContentPane().add(liveLabel);
			String liveMusic = "";
			if (((NightSpot) place).isLiveMusic()) {
				liveMusic = "Yes";
			} else {
				liveMusic = "No";
			}
			JLabel liveReturnLabel = new JLabel(liveMusic);
			liveReturnLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			liveReturnLabel.setBounds(700, 63, 120, 20);
			frame.getContentPane().add(liveReturnLabel);

		}

		String column[];
        update();
        frame.getContentPane().setLayout(null);
        JTable jt = new JTable();
        jt.setFont(new Font("Tahoma", Font.PLAIN, 18));
        if (CityGuide.getInstance().ifAdmin()) {
            column = new String[4];
            column[0] = "User";
            column[1] = "Comment";
            column[2] = "Rating";
            column[3] = "Mail";
            TableModel tablemodel = new DefaultTableModel(data, column);
            jt.setModel(tablemodel);
            jt.setColumnSelectionAllowed(true);
            jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

            jt.getColumnModel().getColumn(0).setPreferredWidth(150);
            jt.getColumnModel().getColumn(1).setPreferredWidth(550);
            jt.getColumnModel().getColumn(2).setPreferredWidth(100);
            jt.getColumnModel().getColumn(3).setPreferredWidth(200);
        } else {
            column = new String[3];
            column[0] = "User";
            column[1] = "Comment";
            column[2] = "Rating";
            TableModel tablemodel = new DefaultTableModel(data, column);
            jt.setModel(tablemodel);
            jt.setColumnSelectionAllowed(true);
            jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            jt.getColumnModel().getColumn(0).setPreferredWidth(150);
            jt.getColumnModel().getColumn(1).setPreferredWidth(550);
            jt.getColumnModel().getColumn(2).setPreferredWidth(300);
        }
        JScrollPane sp = new JScrollPane(jt);
        sp.setBounds(0, 264, 984, 336);
        frame.getContentPane().add(sp);


		String info = MainWindow.getInfo();
		if (info.equals("admin")) {
			if (mail != null) {
				JLabel deleteLabel = new JLabel("Delete comment:");
				deleteLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
				deleteLabel.setBounds(10, 163, 145, 27);
				frame.getContentPane().add(deleteLabel);

				JComboBox mailBox = new JComboBox(mail);
				mailBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
				mailBox.setBounds(165, 162, 480, 31);
				frame.getContentPane().add(mailBox);

				JButton deleteButton = new JButton("Delete");
				deleteButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String mail = (String) mailBox.getItemAt(mailBox.getSelectedIndex());
						CityGuide.getInstance().deleteComment(mail,place); 
						update();
						placeRatingLabel.setText(String.valueOf(CityGuide.getInstance().getPlaceMapId().get(place.getID()).getRating()));
						TableModel tablemodel = new DefaultTableModel(data, column);
						jt.setModel(tablemodel);
						jt.getColumnModel().getColumn(0).setPreferredWidth(150);
						jt.getColumnModel().getColumn(1).setPreferredWidth(550);
						  if (CityGuide.getInstance().ifAdmin()) {
							  jt.getColumnModel().getColumn(2).setPreferredWidth(100);
							  jt.getColumnModel().getColumn(3).setPreferredWidth(200);
						  }
						  else {
							  jt.getColumnModel().getColumn(2).setPreferredWidth(300);
						  }
						
					}
				});
				deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
				deleteButton.setBounds(718, 161, 113, 31);
				frame.getContentPane().add(deleteButton);
			}

		}

		else {
			commentField = new JTextField();
			commentField.setBounds(159, 150, 624, 30);
			frame.getContentPane().add(commentField);
			commentField.setColumns(10);

			JLabel commentLabel = new JLabel("Make comment :");
			commentLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			commentLabel.setBounds(10, 155, 145, 23);
			frame.getContentPane().add(commentLabel);

			JSlider slider = new JSlider(0, 5, 1);
			slider.setBounds(129, 200, 288, 45);
			slider.setMajorTickSpacing(1);
			slider.setMinorTickSpacing(1);
			slider.setPaintTicks(true);
			slider.setPaintLabels(true);
			frame.getContentPane().add(slider);

			JLabel rateLabel = new JLabel("Rate place:");
			rateLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			rateLabel.setBounds(10, 210, 109, 24);

			frame.getContentPane().add(rateLabel);

			JButton submitButton = new JButton("Submit");
			submitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int rating = slider.getValue();
					Login user = CityGuide.getInstance().getCurrentUser();
					Comment comment = new Comment(user.getMailAddress(), user.getName(), user.getSurname(), rating,
							commentField.getText(), place.getName());
					if (!CityGuide.getInstance().addComment(place.getName(), comment, place.getID())) {
						JOptionPane.showMessageDialog(null, "You have commented on this place before!",
								"Invalid comment!", JOptionPane.ERROR_MESSAGE);
					}
					update();
					placeRatingLabel.setText(String.valueOf(CityGuide.getInstance().getPlaceMapId().get(place.getID()).getRating()));
					TableModel tablemodel = new DefaultTableModel(data, column);
					jt.setModel(tablemodel);
					jt.getColumnModel().getColumn(0).setPreferredWidth(150);
					jt.getColumnModel().getColumn(1).setPreferredWidth(550);
					  if (CityGuide.getInstance().ifAdmin()) {
							jt.getColumnModel().getColumn(2).setPreferredWidth(100);
							jt.getColumnModel().getColumn(3).setPreferredWidth(200);
					  }
					  else {
						  jt.getColumnModel().getColumn(2).setPreferredWidth(300);
					  }
				
 
				}
			});
			

			submitButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
			submitButton.setBounds(439, 207, 200, 36);
			frame.getContentPane().add(submitButton);
		}
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {				
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			}
		});
	}
}