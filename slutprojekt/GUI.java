package slutprojekt;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class GUI {
	/*
	 * This code creates a weekly calendar that begins from Monday and ends at Sunday.
	 * All components and their content is created in addComponents method.
	 * addButtonListener listens on 2 buttons that either add or remove newly created labels.
	 */

	static JFrame frame = new JFrame("Weekly Calendar");

	static void createAndDisplay() {
		frame.setSize(1400, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout());

		for (int i = 0; i < 7; i++) {
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			addComponentsAndDate(panel);
			frame.add(panel);
		}

		frame.setVisible(true);

	}

	private static void addComponentsAndDate(JPanel panel) {
		JLabel labelDay = new JLabel("");
		JLabel labelDate = new JLabel("");
		JTextField textField = new JTextField("", 17);
		JButton buttonAdd = new JButton("Add event");
		JButton buttonRemove = new JButton("Remove event");

		panel.setBackground(Color.LIGHT_GRAY);

		labelDay.setText(DayDate.whenIsMonday.getDayOfWeek() + "");
		labelDate.setText(DayDate.whenIsMonday.getMonth() + " " + DayDate.whenIsMonday.getDayOfMonth());

		if (DayDate.currentDate.getDayOfMonth() == DayDate.whenIsMonday.getDayOfMonth()) {
			labelDay.setForeground(Color.RED);
			labelDate.setForeground(Color.RED);
			panel.setBackground(Color.YELLOW);
		}

		DayDate.whenIsMonday = DayDate.whenIsMonday.plusDays(1);
		

		labelDay.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelDate.setAlignmentX(Component.CENTER_ALIGNMENT);
		textField.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonRemove.setAlignmentX(Component.CENTER_ALIGNMENT);

		textField.setPreferredSize(new Dimension(0, 50));
		buttonAdd.setPreferredSize(new Dimension(150, 20));
		buttonRemove.setPreferredSize(new Dimension(150, 20));

		textField.setMaximumSize(textField.getPreferredSize());
		buttonAdd.setMaximumSize(buttonAdd.getPreferredSize());
		buttonRemove.setMaximumSize(buttonRemove.getPreferredSize());

		buttonAdd.setBackground(Color.ORANGE);
		buttonRemove.setBackground(Color.RED);

		addButtonListener(buttonAdd, buttonRemove, textField, panel);

		panel.add(Box.createVerticalStrut(30));
		panel.add(labelDay);
		panel.add(labelDate);
		panel.add(Box.createVerticalStrut(30));
		panel.add(textField);
		panel.add(buttonAdd);
		panel.add(buttonRemove);
		panel.add(Box.createVerticalStrut(70));

		frame.setVisible(true);
	}

	private static void addButtonListener(JButton b, JButton bRemove, JTextField tf, JPanel p) {
		ActionListener bListener = new ActionListener() {
			JLabel newLabel = new JLabel();
			JLabel labelArray[] = new JLabel[10];
			int place = 0;
			int counter = 1;

			@Override
			public void actionPerformed(ActionEvent e) {
				Object action = e.getSource();
				// TODO Auto-generated method stub
				if (action == b) {

					if (place < labelArray.length && !tf.getText().equals("")) {
						newLabel = new JLabel(counter + ". " + tf.getText());
						newLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

						labelArray[place] = newLabel;
						p.add(labelArray[place]);
						p.add(Box.createVerticalStrut(5));
						place++;
						counter++;
					} else if (place == 10) {
						b.setText("The max is 10");
					}
					tf.setText("");

				} else if (action == bRemove) {
					if (place > 0) {
						b.setText("Add event");
						place--;
						counter--;
						p.remove(labelArray[place]);
						p.add(Box.createVerticalStrut(-5));
						p.revalidate();
						p.repaint();
					}
				}
				frame.setVisible(true);
			}

		};
		b.addActionListener(bListener);
		bRemove.addActionListener(bListener);
	}
}
