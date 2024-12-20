package hust.soict.hedspi.aims.screen;

import javax.swing.*;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.store.Store;

import java.awt.*;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField directorField;
    private JTextField lengthField;

    public AddDigitalVideoDiscToStoreScreen(Store store, Cart cart) {
        super("Add DVD to Store", store, cart);
        setVisible(true);
    }

    @Override
    protected JPanel createInputPanel() {
        JPanel panel = super.createInputPanel();
        panel.setLayout(new GridLayout(5, 2, 5, 5));

        panel.add(new JLabel("Director:"));
        directorField = new JTextField(20);
        panel.add(directorField);

        panel.add(new JLabel("Length:"));
        lengthField = new JTextField(20);
        panel.add(lengthField);

        return panel;
    }

    @Override
    protected JPanel createButtonPanel(Store store) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addButton = new JButton("Add DVD");
        addButton.addActionListener(e -> {
            // Add logic to save the DVD
        	// Tạo đối tượng dvd
            DigitalVideoDisc newDVD = new DigitalVideoDisc(titleField.getText(), categoryField.getText(), directorField.getText(), Integer.parseInt(lengthField.getText()), Float.parseFloat(costField.getText()));

            // Thêm sách vào store
            store.addMedia(newDVD);

            // Thông báo thêm thành công
            JOptionPane.showMessageDialog(null, "CD added successfully!");
            System.out.println("DVD added: " + titleField.getText() +
                               ", " + categoryField.getText() +
                               ", " + costField.getText() +
                               ", " + directorField.getText() +
                               ", " + lengthField.getText());

            // Reset fields
            resetFields();
            directorField.setText("");
            lengthField.setText("");
        });
        panel.add(addButton);
        return panel;
    }
}
