package employeemanager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import workers.Employee;
import workers.HourlyEmployee;
import workers.SalariedEmployee;

/**
 * A dialog box that allows a user to specify the details of a new Employee. If
 * the user clicks the "OK" button, the new employee is added to the list; if
 * they click "Cancel" (or close the window) then no employee is added.
 * 
 * @author mefoster
 *
 */
// Quiet an Eclipse warning that's not relevant here
@SuppressWarnings("serial")
public class AddEmployeeDialog extends JDialog implements ActionListener {

	// Common fields
	private JComboBox<String> workerType;
	private JTextField givenNameField;
	private JTextField familyNameField;

	// Fields that depend on the employee type
	private JTextField rateField;
	private JTextField hoursField;
	private JTextField salaryField;

	// Buttons
	private JButton okButton;
	private JButton cancelButton;

	// The employee frame -- used to position the dialog and to access the
	// employee list
	private EmployeeFrame employeeFrame;

	/**
	 * Creates a new AddEmployeeDialog as a child of the given EmployeeFrame.
	 * 
	 * @param frame
	 *            The parent EmployeeFrame -- used to position the dialog and to
	 *            access the list of employees
	 * @see EmployeeFrame#getListModel()
	 */
	public AddEmployeeDialog(final EmployeeFrame frame) {
		// Basic initialisation
		super(frame, "Add Employee", true);
		setLocationRelativeTo(employeeFrame);
		this.employeeFrame = frame;

		// Common fields
		workerType = new JComboBox<String>(Employee.getEmployeeTypes());
		givenNameField = new JTextField(20);
		familyNameField = new JTextField(20);

		// Fields only for hourly workers
		rateField = new JFormattedTextField();
		hoursField = new JFormattedTextField();

		// Field only for salaried worker
		salaryField = new JFormattedTextField();

		// Top line
		Box workerBox = Box.createHorizontalBox();
		workerBox.add(new JLabel("Worker type"));
		workerBox.add(workerType);

		// Next lines (names)
		Box givenNameBox = Box.createHorizontalBox();
		givenNameBox.add(new JLabel("Given name "));
		givenNameBox.add(givenNameField);

		Box familyNameBox = Box.createHorizontalBox();
		familyNameBox.add(new JLabel("Family name"));
		familyNameBox.add(familyNameField);

		// Hourly-worker fields
		Box hourlyBox = Box.createHorizontalBox();
		hourlyBox.setBorder(new TitledBorder("Hourly worker information"));
		hourlyBox.add(new JLabel("Rate"));
		hourlyBox.add(rateField);
		hourlyBox.add(Box.createHorizontalStrut(10));
		hourlyBox.add(new JLabel("Hours"));
		hourlyBox.add(hoursField);

		// Salaried-worker fields
		Box salariedBox = Box.createHorizontalBox();
		salariedBox.setBorder(new TitledBorder("Salaried worker information"));
		salariedBox.add(new JLabel("Salary"));
		salariedBox.add(salaryField);

		// Ensure that only the appropriate fields are enabled, depending on the
		// worker type chosen
		workerType.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				String type = (String) workerType.getSelectedItem();
				salaryField.setEnabled("Salaried".equals(type));
				rateField.setEnabled("Hourly".equals(type));
				hoursField.setEnabled("Hourly".equals(type));
			}
		});
		workerType.setSelectedItem(null);

		// Create buttons and add the current class as an ActionListener
		okButton = new JButton("OK");
		okButton.addActionListener(this);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);

		// Bottom row of GUI: buttons
		Box bottomBox = Box.createHorizontalBox();
		bottomBox.add(Box.createHorizontalGlue());
		bottomBox.add(okButton);
		bottomBox.add(Box.createHorizontalGlue());
		bottomBox.add(cancelButton);
		bottomBox.add(Box.createHorizontalGlue());

		// Lay out the GUI
		getContentPane()
				.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		getContentPane().add(workerBox);
		getContentPane().add(givenNameBox);
		getContentPane().add(familyNameBox);
		getContentPane().add(hourlyBox);
		getContentPane().add(salariedBox);
		getContentPane().add(Box.createVerticalStrut(10));
		getContentPane().add(bottomBox);
		pack();
	}

	/**
	 * Responds to a click on either of the buttons on the dialog. If the OK
	 * button is pressed, a new employee is added to the list; if the Cancel
	 * button is pressed, then nothing is changed.
	 * 
	 * The type of employee that is added depends on the state of the "Worker
	 * type" combo box.
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		// Work out which button was pressed
		Object source = event.getSource();

		if (source == cancelButton) {
			// Just close the window
			dispose();

		} else if (source == okButton) {
			// Get values for the common fields
			String type = (String) workerType.getSelectedItem();
			String givenName = givenNameField.getText();
			String familyName = familyNameField.getText();
			Employee newEmp = null;

			// Read the other fields, depending on the employee type, and try to
			// create an employee.
			// If there is an error with the number fields, don't create
			// anything
			switch (type) {
			case "Salaried":
				String salaryText = salaryField.getText();
				try {
					double salary = Double.valueOf(salaryText);
					newEmp = new SalariedEmployee(familyName, givenName,
							salary);
				} catch (NumberFormatException ex) {
					// Don't create the employee
				}
				break;

			case "Hourly":
				String rateText = rateField.getText();
				String hoursText = hoursField.getText();
				try {
					double rate = Double.valueOf(rateText);
					int hours = Integer.valueOf(hoursText);
					newEmp = new HourlyEmployee(familyName, givenName, rate,
							hours);
				} catch (NumberFormatException ex) {
					// Ignore it but don't create the employee
				}
				break;
			}

			// If we have created a new employee, then add it to the list in the
			// main window
			if (newEmp != null) {
				employeeFrame.getListModel().addElement(newEmp);
			}

			// Get rid of the dialog box
			dispose();
		}
	}

}