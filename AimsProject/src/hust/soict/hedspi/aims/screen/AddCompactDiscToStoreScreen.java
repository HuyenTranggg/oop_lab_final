package hust.soict.hedspi.aims.screen;

import javax.swing.*;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.store.Store;

import java.awt.*;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField artistField;

    public AddCompactDiscToStoreScreen(Store store, Cart cart) {
        super("Add CD to Store", store, cart);
        setVisible(true);
    }

    @Override
    protected JPanel createInputPanel() {
        JPanel panel = super.createInputPanel();
        panel.setLayout(new GridLayout(4, 2, 5, 5));

        panel.add(new JLabel("Artist:"));
        artistField = new JTextField(20);
        panel.add(artistField);

        return panel;
    }

    @Override
    protected JPanel createButtonPanel(Store store) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addButton = new JButton("Add CD");
        addButton.addActionListener(e -> {
            // Add logic to save the CD
        	// Tạo đối tượng sách
            CompactDisc newCD = new CompactDisc(titleField.getText(), categoryField.getText(), artistField.getText(), Float.parseFloat(costField.getText()));

            // Thêm sách vào store
            store.addMedia(newCD);

            // Thông báo thêm thành công
            JOptionPane.showMessageDialog(null, "CD added successfully!");
            System.out.println("CD added: " + titleField.getText() +
                               ", " + categoryField.getText() +
                               ", " + costField.getText() +
                               ", " + artistField.getText());

            // Reset fields
            resetFields();
            artistField.setText("");
        });
        panel.add(addButton);
        return panel;
    }
}
