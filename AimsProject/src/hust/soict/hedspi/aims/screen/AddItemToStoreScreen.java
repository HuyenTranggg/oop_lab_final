package hust.soict.hedspi.aims.screen;

import javax.swing.*;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.store.Store;

import java.awt.*;

public abstract class AddItemToStoreScreen extends JFrame {
//	private Store store;
//	private Cart cart;
	
    protected JTextField titleField;
    protected JTextField categoryField;
    protected JTextField costField;

    public AddItemToStoreScreen(String title, Store store, Cart cart) {
        super(title);
        setLayout(new BorderLayout());
        setSize(1024, 768);

        // Create and set the menu bar
        setJMenuBar(createMenuBar(store, cart));

        // Add input and button panels
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(createInputPanel(), BorderLayout.CENTER);
        mainPanel.add(createButtonPanel(store), BorderLayout.SOUTH);

        add(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    protected JMenuBar createMenuBar(Store store, Cart cart) {
        JMenuBar menuBar = new JMenuBar();

        JMenu optionsMenu = new JMenu("Options");

        JMenu updateStoreMenu = new JMenu("Update Store");
        JMenuItem addBookItem = new JMenuItem("Add Book");
        addBookItem.addActionListener(e -> switchScreen(new AddBookToStoreScreen(store, cart)));
        JMenuItem addCDItem = new JMenuItem("Add CD");
        addCDItem.addActionListener(e -> switchScreen(new AddCompactDiscToStoreScreen(store, cart)));
        JMenuItem addDVDItem = new JMenuItem("Add DVD");
        addDVDItem.addActionListener(e -> switchScreen(new AddDigitalVideoDiscToStoreScreen(store, cart)));

        updateStoreMenu.add(addBookItem);
        updateStoreMenu.add(addCDItem);
        updateStoreMenu.add(addDVDItem);

        optionsMenu.add(updateStoreMenu);

        JMenuItem viewStoreItem = new JMenuItem("View Store");
        viewStoreItem.addActionListener(e -> switchScreen(new StoreScreen(store, cart)));
        optionsMenu.add(viewStoreItem);

        JMenuItem viewCartItem = new JMenuItem("View Cart");
        viewCartItem.addActionListener(e -> new CartScreen(store, cart));
        optionsMenu.add(viewCartItem);

        menuBar.add(optionsMenu);

        return menuBar;
    }

    protected void switchScreen(JFrame newScreen) {
    	dispose();
        newScreen.setVisible(true);
    }

    protected JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 5, 5));

        panel.add(new JLabel("Title:"));
        titleField = new JTextField(20);
        panel.add(titleField);

        panel.add(new JLabel("Category:"));
        categoryField = new JTextField(20);
        panel.add(categoryField);

        panel.add(new JLabel("Cost:"));
        costField = new JTextField(20);
        panel.add(costField);

        return panel;
    }

    protected abstract JPanel createButtonPanel(Store store);

    protected void resetFields() {
        titleField.setText("");
        categoryField.setText("");
        costField.setText("");
    }
}
