package hust.soict.hedspi.aims.screen;

import javax.swing.*;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.store.Store;

import java.awt.*;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    private JTextField authorsField;
    public AddBookToStoreScreen(Store store, Cart cart) {
        super("Add Book to Store", store, cart);
        setVisible(true);
    }

    @Override
    protected JPanel createInputPanel() {
        JPanel panel = super.createInputPanel();
        panel.setLayout(new GridLayout(3, 2, 5, 5));

        return panel;
    }

    @Override
    protected JPanel createButtonPanel(Store store) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addButton = new JButton("Add Book");
        addButton.addActionListener(e -> {
            // Add logic to save the book
        	// Tạo đối tượng sách
            Book newBook = new Book(titleField.getText(), categoryField.getText(), Float.parseFloat(costField.getText()));

            // Thêm sách vào store
            store.addMedia(newBook);

            // Thông báo thêm thành công
            JOptionPane.showMessageDialog(null, "Book added successfully!");
            System.out.println("Book added: " + titleField.getText() +
                               ", " + categoryField.getText() +
                               ", " + costField.getText());

            // Reset fields
            resetFields();
        });
        panel.add(addButton);
        return panel;
    }
}
