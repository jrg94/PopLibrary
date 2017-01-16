package com.cybrotronics.poplibrary;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.ECS.client.jax.AWSECommerceService;
import com.ECS.client.jax.ItemSearch;
import com.ECS.client.jax.ItemSearchRequest;
import com.ECS.client.jax.ItemSearchResponse;

public class Main {

	public static void main(String[] args) {
		
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
		itemRequest.setKeywords("dog");
		itemRequest.getResponseGroup().add("Large");
		ItemSearch ItemElement= new ItemSearch();
		ItemElement.setAWSAccessKeyId(awsAccessKey);
		ItemElement.setAssociateTag(awsAssociateTag);
		ItemElement.getRequest().add(itemRequest);

		// Call the Web service operation and store the response in the response object:
		ItemSearchResponse response = port.itemSearch(ItemElement);
		response.getItems().forEach(itemList -> itemList.getItem().forEach(item -> System.out.println(item.getItemAttributes().getTitle())));
	}

}
