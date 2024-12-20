package hust.soict.hedspi.aims.screen;

import java.util.function.Predicate;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CartScreenController {
	
	private Cart cart;
	
	@FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;
    
    @FXML
    private Button btnPlaceOrder;
    
    @FXML
    private Label lblTotalCost;
    
    @FXML
    private TextField tfFilter;

    @FXML
    private RadioButton radioBtnFilterId;

    @FXML
    private RadioButton radioBtnFilterTitle;

	@FXML
	private TableView<Media> tblMedia;
	
	@FXML
	private TableColumn<Media, String> colMediaTitle;
	
	@FXML
	private TableColumn<Media, String> colMediacategory;
	
	@FXML
	private TableColumn<Media, Float> colMediaCost;
	
	private FilteredList<Media> filteredList;
	
	public CartScreenController(Cart cart) {
		super();
		this.cart = cart;
	}
	
	@FXML
	private void initialize() {
		colMediaTitle.setCellValueFactory(
				new PropertyValueFactory<Media, String>("title"));
		colMediacategory.setCellValueFactory(
				new PropertyValueFactory<Media, String>("category"));
		colMediaCost.setCellValueFactory(
				new PropertyValueFactory<Media, Float>("cost"));
		
		// Wrap the ObservableList in a FilteredList
        filteredList = new FilteredList<>(cart.getItemsOrdered(), media -> true);

        // Set the filtered list as the items for the TableView
        tblMedia.setItems(filteredList);
		
		btnPlay.setVisible(false);
		btnRemove.setVisible(false);
		
		tblMedia.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<Media>() {
					
				@Override
				public void changed(ObservableValue<? extends Media> observable, Media oldValue,
						Media newValue) {
					if(newValue!=null) {
						updateButtonBar(newValue);
					}
				}
			});
		
		tfFilter.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue,
					String newValue) {
				showFilteredMedia(newValue);
			}
		});
		
		ListChangeListener<Media> listChangeListener = change -> updateTotalCost();
		cart.getItemsOrdered().addListener(listChangeListener);

		
		// Initial total cost update
        updateTotalCost();
        
        
	}
	
	void updateButtonBar(Media media) {
		btnRemove.setVisible(true);
		if(media instanceof Playable) {
			btnPlay.setVisible(true);
		}
		else {
			btnPlay.setVisible(false);
		}
	}
	
	@FXML
	void btnRemovePressed(ActionEvent event) {
		Media media = tblMedia.getSelectionModel().getSelectedItem();
		cart.removeMedia(media);
	}
	
	private void showFilteredMedia(String filterText) {
        // Update the predicate based on the filter text and selected filter criteria
        Predicate<Media> filterPredicate;

        if (radioBtnFilterId.isSelected()) {
            filterPredicate = media -> {
                if (filterText == null || filterText.isEmpty()) return true;
                return Integer.toString(media.getId()).contains(filterText);
            };
        } else if (radioBtnFilterTitle.isSelected()) {
            filterPredicate = media -> {
                if (filterText == null || filterText.isEmpty()) return true;
                return media.getTitle().toLowerCase().contains(filterText.toLowerCase());
            };
        } else {
            filterPredicate = media -> true; // Default: show all items
        }

        filteredList.setPredicate(filterPredicate);
    }
	
	@FXML
    private void btnPlaceOrderPressed(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Order Placed");
        alert.setHeaderText(null);
        alert.setContentText("Your order has been placed successfully!\n"
        		+ "Total Cost: " + cart.totalCost() + " $");
        alert.showAndWait();
        cart.empty();
        updateTotalCost();
    }

    @FXML
    private void btnPlayPressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media instanceof Playable) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Playing Media");
            alert.setHeaderText(null);
            alert.setContentText(media.play());
            alert.showAndWait();
        }
    }

    private void updateTotalCost() {
        Platform.runLater(() -> {
            if (lblTotalCost != null) {
                lblTotalCost.setText(String.format(" %.2f $", cart.totalCost()));
            } else {
                System.err.println("lblTotalCost is null!");
            }
        });
    }
    
    
}
