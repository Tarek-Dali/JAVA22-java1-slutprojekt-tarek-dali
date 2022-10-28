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
	 * The code creates 7 JPanels using a loop and sets their layout and bounds.
	 * Each JPanel is sent to a method called addComponents.
	 * 
	 * addComponents creates labels, textfield and buttons. The method also contains
	 * code that gets LocalDate from DayDate class and uses them in
	 * GUI.addComponents to set the labels from Monday to Sunday and sets correct
	 * dates to them.
	 * 
	 * ActionListener listens on the Add event and Remove events buttons in order to
	 * either add new labels or remove them.
	 */

	static JFrame frame = new JFrame("Weekly Calendar");

	static void createAndDisplay() {
		frame.setSize(1400, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout());

		for (int i = 0; i < 7; i++) {
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			addComponents(panel);
			frame.add(panel);
		}

		frame.setVisible(true);

	}

	private static void addComponents(JPanel panel) {
		JLabel addDay = new JLabel("");
		JLabel addDate = new JLabel("");
		JTextField addTextField = new JTextField("", 17);
		JButton addButton = new JButton("Add event");
		JButton removeLabel = new JButton("Remove event");

		panel.setBackground(Color.LIGHT_GRAY);

		addDay.setText(DayDate.whenIsMonday.getDayOfWeek() + "");
		addDate.setText(DayDate.whenIsMonday.getDayOfMonth() + " " + DayDate.date.getMonth());

		if (DayDate.date.getDayOfMonth() == DayDate.whenIsMonday.getDayOfMonth()) {
			addDay.setForeground(Color.RED);
			addDate.setForeground(Color.RED);
			panel.setBackground(Color.YELLOW);
		}

		DayDate.whenIsMonday = DayDate.whenIsMonday.plusDays(1);

		if (DayDate.whenIsMonday.getDayOfMonth() == 1) {
			DayDate.date = DayDate.date.plusMonths(1);
		}

		addDay.setAlignmentX(Component.CENTER_ALIGNMENT);
		addDate.setAlignmentX(Component.CENTER_ALIGNMENT);
		addTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
		addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		removeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		addTextField.setPreferredSize(new Dimension(0, 50));
		addButton.setPreferredSize(new Dimension(150, 20));
		removeLabel.setPreferredSize(new Dimension(150, 20));

		addTextField.setMaximumSize(addTextField.getPreferredSize());
		addButton.setMaximumSize(addButton.getPreferredSize());
		removeLabel.setMaximumSize(removeLabel.getPreferredSize());

		addButton.setBackground(Color.ORANGE);
		removeLabel.setBackground(Color.RED);

		addButtonListener(addButton, addTextField, panel, removeLabel);

		panel.add(Box.createVerticalStrut(30));
		panel.add(addDay);
		panel.add(addDate);
		panel.add(Box.createVerticalStrut(30));
		panel.add(addTextField);
		panel.add(addButton);
		panel.add(removeLabel);
		panel.add(Box.createVerticalStrut(70));

		frame.setVisible(true);
	}

	private static void addButtonListener(JButton b, JTextField tf, JPanel p, JButton rl) {
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

				} else if (action == rl) {
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
		rl.addActionListener(bListener);
	}
}
