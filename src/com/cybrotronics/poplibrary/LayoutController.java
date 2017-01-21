package com.cybrotronics.poplibrary;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import com.ECS.client.jax.AWSECommerceService;
import com.ECS.client.jax.Item;
import com.ECS.client.jax.ItemSearch;
import com.ECS.client.jax.ItemSearchRequest;
import com.ECS.client.jax.ItemSearchResponse;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

public class LayoutController implements Initializable {

	@FXML ListView<Item> bookList;
	@FXML TextField searchBar;
	
	@FXML
	public void search() {
		// create and load default properties
		Properties defaultProps = new Properties();
					
		try (FileInputStream in = new FileInputStream("src/com/cybrotronics/poplibrary/secret.key")){
			defaultProps.load(in);	
		} catch (IOException e) {
			e.printStackTrace();
		}
						
		String awsAccessKey = defaultProps.getProperty("awsAccessKey");
		String awsSecretAccessKey = defaultProps.getProperty("awsSecretAccessKey");
		String awsAssociateTag = defaultProps.getProperty("awsAssociateTag");
				
		// Set the service:
		AWSECommerceService service = new AWSECommerceService();

		service.setHandlerResolver(new AwsHandlerResolver(awsSecretAccessKey));
				
		// Set the service port:
		com.ECS.client.jax.AWSECommerceServicePortType port = service.getAWSECommerceServicePort();

		// Get the operation object:
		ItemSearchRequest itemRequest = new ItemSearchRequest();

		// Fill in the request object:
		itemRequest.setSearchIndex("Books");
		itemRequest.setKeywords(searchBar.getText());
		itemRequest.getResponseGroup().add("Large");
		ItemSearch ItemElement= new ItemSearch();
		ItemElement.setAWSAccessKeyId(awsAccessKey);
		ItemElement.setAssociateTag(awsAssociateTag);
		ItemElement.getRequest().add(itemRequest);

		// Call the Web service operation and store the response in the response object:
		bookList.getItems().clear();
		ItemSearchResponse response = port.itemSearch(ItemElement);
		response.getItems().forEach(itemList -> itemList.getItem().forEach(item -> bookList.getItems().add(item)));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		searchBar.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {
				if (arg0.getCode().equals(KeyCode.ENTER)) {
					search();
				}
			}
			
		});
		
		bookList.setCellFactory(new Callback<ListView<Item>, 
	            ListCell<Item>>() {
	                @Override 
	                public ListCell<Item> call(ListView<Item> list) {
	                    return new BookCell();
	                }
	            }
	        );
	}
	
	static class BookCell extends ListCell<Item> {
        @Override
        public void updateItem(Item item, boolean empty) {
            super.updateItem(item, empty);
            
            if (item != null) {
            	this.setText(item.getItemAttributes().getTitle());
            }
        }
    }
	
}
