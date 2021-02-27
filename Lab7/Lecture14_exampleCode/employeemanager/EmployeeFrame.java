package employeemanager;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;

import workers.Employee;

/**
 * A window on an employee record system. The window displays all employee
 * records and allows the user to add a new employee, edit an existing employee,
 * delete an employee from the list, or save the list of employees to a file.
 * 
 * @author mefoster
 *
 */
// Disable an Eclipse warning that is not relevant here
@SuppressWarnings("serial")
public class EmployeeFrame extends JFrame implements ActionListener {

	// The buttons to display
	private JButton addButton;
	private JButton deleteButton;

	// The underlying list of employees, and the GUI object to display them
	private DefaultListModel<Employee> listModel;
	private JList<Employee> employeeList;

	/**
	 * Creates and displays a new EmployeeFrame. The program exits when the
	 * window is closed.
	 */
	public EmployeeFrame() {
		// Basic window features
		super("Employee Manager");
		setLocationByPlatform(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Try to make it look like a native application -- using
		// try/multi-catch
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		// Initialise an empty list model, a JList to display it, and a scroll
		// pane to contain the list
		listModel = new DefaultListModel<>();
		employeeList = new JList<>(listModel);
		JScrollPane employeeScroll = new JScrollPane(employeeList);
		employeeScroll.setBorder(new TitledBorder("Employee List"));

		// Initialise all buttons and add the current class as an action
		// listener to all
		addButton = new JButton("Add Employee");
		addButton.addActionListener(this);
		deleteButton = new JButton("Delete Employee");
		deleteButton.addActionListener(this);

		// Lay out the buttons in a line
		Box topBox = Box.createHorizontalBox();
		topBox.add(addButton);
		topBox.add(Box.createHorizontalStrut(10));
		topBox.add(deleteButton);

		// Lay out the window
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(topBox, BorderLayout.NORTH);
		getContentPane().add(employeeScroll, BorderLayout.CENTER);
		pack();
	}

	/**
	 * Returns the ListModel -- i.e., the list of all employees.
	 * 
	 * @return The current list of employees
	 */
	public DefaultListModel<Employee> getListModel() {
		return this.listModel;
	}

	/**
	 * Responds to a click on any of the buttons on the window.
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		// Determine which button was pushed
		Object source = event.getSource();

		if (source == deleteButton) {
			// If an employee is selected in the list, remove it from the model
			Employee selection = employeeList.getSelectedValue();
			if (selection != null) {
				listModel.removeElement(selection);
			}

		} else if (source == addButton) {
			// Create and display a new dialogue to add the employee
			new AddEmployeeDialog(this).setVisible(true);
		}
	}

	/**
	 * Main method: just creates and displays a new EmployeeFrame.
	 * 
	 * @param args
	 *            Command-line arguments (not used)
	 */
	public static void main(String[] args) {
		new EmployeeFrame().setVisible(true);
	}

}
