package hust.soict.hedspi.aims.screen;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.store.Store;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class CartScreen extends JFrame{
    private Cart cart;
    private Store store;

    public CartScreen(Store store, Cart cart) {
        super();

        this.cart = cart;

        JFXPanel fxPanel = new JFXPanel();
        this.add(fxPanel);

        this.setTitle("Cart");
        this.setVisible(true);
        setSize(1024, 768); // Đặt kích thước mặc định
        
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/hust/soict/hedspi/aims/screen/cart.fxml"));
                CartScreenController controller = new CartScreenController(cart);
                loader.setController(controller);

                Parent root = loader.load();
                fxPanel.setScene(new Scene(root));

                // Add menu options to navigate between screens
                JMenuBar menuBar = new JMenuBar();
                JMenu optionsMenu = new JMenu("Options");

                JMenu updateStoreMenu = new JMenu("Update Store");
                JMenuItem addBookItem = new JMenuItem("Add Book");
                addBookItem.addActionListener(e -> new AddBookToStoreScreen(store, cart).setVisible(true));
                JMenuItem addCDItem = new JMenuItem("Add CD");
                addCDItem.addActionListener(e -> new AddCompactDiscToStoreScreen(store, cart).setVisible(true));
                JMenuItem addDVDItem = new JMenuItem("Add DVD");
                addDVDItem.addActionListener(e -> new AddDigitalVideoDiscToStoreScreen(store, cart).setVisible(true));

                updateStoreMenu.add(addBookItem);
                updateStoreMenu.add(addCDItem);
                updateStoreMenu.add(addDVDItem);

                optionsMenu.add(updateStoreMenu);

                JMenuItem viewStoreItem = new JMenuItem("View Store");
                viewStoreItem.addActionListener(e -> new StoreScreen(store, cart).setVisible(true));
                optionsMenu.add(viewStoreItem);

                JMenuItem viewCartItem = new JMenuItem("View Cart");
                optionsMenu.add(viewCartItem);

                menuBar.add(optionsMenu);

                setJMenuBar(menuBar);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
