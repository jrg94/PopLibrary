package com.cybrotronics.poplibrary;
import com.ECS.client.jax.AWSECommerceService;
import com.ECS.client.jax.ItemSearch;
import com.ECS.client.jax.ItemSearchRequest;
import com.ECS.client.jax.ItemSearchResponse;

public class Main {

	public static void main(String[] args) {
		// Set the service:
		AWSECommerceService service = new AWSECommerceService();

		service.setHandlerResolver(new AwsHandlerResolver("<SECRET KEY>"));
		
		// Set the service port:
		com.ECS.client.jax.AWSECommerceServicePortType port = service.getAWSECommerceServicePort();

		// Get the operation object:
		ItemSearchRequest itemRequest = new ItemSearchRequest();

		// Fill in the request object:
		itemRequest.setSearchIndex("Books");
		itemRequest.setKeywords("dog");
		itemRequest.getResponseGroup().add("Large");
		ItemSearch ItemElement= new ItemSearch();
		ItemElement.setAWSAccessKeyId("<ACCESS KEY>");
		ItemElement.setAssociateTag("<ASSOCIATE TAG");
		ItemElement.getRequest().add(itemRequest);

		// Call the Web service operation and store the response in the response object:
		ItemSearchResponse response = port.itemSearch(ItemElement);
		System.out.println(response.getItems());
	}

}
