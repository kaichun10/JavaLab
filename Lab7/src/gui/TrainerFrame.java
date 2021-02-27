package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.UnsupportedLookAndFeelException;

import monster.Monster;
import monster.MonsterException;
import trainer.Trade;
import trainer.Trainer;

/**
 * The toplevel window to present Trainer objects and let users update their
 * lists of Monsters.
 * 
 * @author mefoster
 */
// Suppress an annoying and (in this case) irrelevant Eclipse compiler warning
@SuppressWarnings("serial")
public class TrainerFrame extends JFrame implements ActionListener {
	// The buttons
	private JButton tradeButton;
	private JButton add1Button, delete1Button;
	private JButton add2Button, delete2Button;

	// The two trainers being presented
	private Trainer trainer1, trainer2;

	// The two lists of monsters displayed
	private DefaultListModel<Monster> trainer1Model, trainer2Model;
	private JList<Monster> trainer1List, trainer2List;

	/**
	 * Creates a new TrainerFrame manipulating the two given Trainers.
	 * 
	 * @param trainer1 The first trainer (will be shown on the left)
	 * @param trainer2 The second trainer (will be shown on the right)
	 */
	public TrainerFrame(Trainer trainer1, Trainer trainer2) {
		// Basic window features
		super("Trading");
		setLocationByPlatform(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		// Create the two JLists and their corresponding list models
		this.trainer1 = trainer1;
		trainer1Model = new DefaultListModel<>();
		trainer1Model.addAll(trainer1.getMonsters());
		trainer1List = new JList<>(trainer1Model);

		this.trainer2 = trainer2;
		trainer2Model = new DefaultListModel<>();
		trainer2Model.addAll(trainer2.getMonsters());
		trainer2List = new JList<>(trainer2Model);

		// Add titled scrolling pane to hold the lists, and add them to the layout
		JScrollPane trainer1Scroll = new JScrollPane(trainer1List);
		trainer1Scroll.setBorder(new TitledBorder(trainer1.getName() + "'s Monsters"));
		JScrollPane trainer2Scroll = new JScrollPane(trainer2List);
		trainer2Scroll.setBorder(new TitledBorder(trainer2.getName() + "'s Monsters"));

		// Add the lists to the layout
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new GridLayout(1, 2));
		listPanel.add(trainer1Scroll);
		listPanel.add(trainer2Scroll);

		// Create the buttons and add the current class as a listener
		tradeButton = new JButton("Trade");
		tradeButton.addActionListener(this);

		add1Button = new JButton("Add");
		add1Button.addActionListener(this);
		add2Button = new JButton("Add");
		add2Button.addActionListener(this);
		delete1Button = new JButton("Delete");
		delete1Button.addActionListener(this);
		delete2Button = new JButton("Delete");
		delete2Button.addActionListener(this);

		// Lay out the buttons
		Box buttonBox = Box.createHorizontalBox();
		buttonBox.add(add1Button);
		buttonBox.add(delete1Button);
		buttonBox.add(Box.createGlue());
		buttonBox.add(tradeButton);
		buttonBox.add(Box.createGlue());
		buttonBox.add(add2Button);
		buttonBox.add(delete2Button);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(listPanel);
		getContentPane().add(BorderLayout.SOUTH, buttonBox);
		pack();
	}

	/**
	 * Returns the ListModel -- i.e., the list of all monster.
	 * 
	 * @return The current list of monster
	 */
	public DefaultListModel<Monster> getListModel() {
		return this.trainer1Model;
	}

	public DefaultListModel<Monster> getListModel2() {
		return this.trainer2Model;
	}

	/**
	 * Responds to a click on one of the buttons.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		// When one of the delete buttons is clicked and no monster is selected, the
		// button should do nothing; if a monster is selected, then it should be removed
		// from the displayed list and the Trainer's list.
		if (source == delete1Button) {
			Monster selection = trainer1List.getSelectedValue();
			if (selection != null) {
				trainer1Model.removeElement(selection);
			}
		} else if (source == delete2Button) {
			Monster selection = trainer2List.getSelectedValue();
			if (selection != null) {
				trainer2Model.removeElement(selection);
			}
		}
		// When one of the add buttons is clicked, a new instance of AddMonsterDialog
		// class is created and being set visible
		else if (source == add1Button) {
			new AddMonsterDialog(this, trainer1, this.trainer1Model).setVisible(true);
		} else if (source == add2Button) {
			new AddMonsterDialog(this, trainer2, this.trainer2Model).setVisible(true);

		}

		// when the trade button is clicked and two monsters are selected, a new Trade
		// object is created executed. the monsters inside each trainer object is
		// updated and also the list on the GUI menu is updated
		else if (source == tradeButton) {
			Monster selection1 = trainer1List.getSelectedValue();
			Monster selection2 = trainer2List.getSelectedValue();
			if ((selection1 != null) && (selection2 != null)) {
				// Updating the GUI menu
				trainer1Model.addElement(selection2);
				trainer2Model.addElement(selection1);
				trainer1Model.removeElement(selection1);
				trainer2Model.removeElement(selection2);

				// Updating the each of the trainer object using the Trade class
				Trade trade;
				try {
					trade = new Trade(trainer1, selection1, trainer2, selection2);
					trade.doTrade();
				} catch (MonsterException e1) {
					e1.printStackTrace();
				}

			}
		}
	}

	/**
	 * Main method: loads the two Trainers and displays the toplevel window.
	 * 
	 * @param args Command-line arguments (not used)
	 * @throws IOException If one of the Trainer objects cannot be loaded
	 */
	public static void main(String[] args) throws IOException {
		Trainer trainer1 = Trainer.loadFromFile("brendan.txt");
		Trainer trainer2 = Trainer.loadFromFile("ethan.txt");

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new TrainerFrame(trainer1, trainer2).setVisible(true);
			}
		});
	}

}