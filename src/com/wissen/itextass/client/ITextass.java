package com.wissen.itextass.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ITextass implements EntryPoint {
	
	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		final Button sendButton = new Button("Generate PDF");
		
		sendButton.addClickHandler(new ClickHandler(){@Override
		public void onClick(ClickEvent event) {
			
			generatePdf();
		}
		
		}); 
		
		

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

	
		RootPanel.get("sendButtonContainer").add(sendButton);

		
		
		
		
		
		
	
		
	}// End of OnModule Load
	/**
	 * Method to set Frame for PDF on HTML
	 * @param s
	 */
	void setFrame(String s)
	{
		Frame pdfFrame = new Frame();
		pdfFrame.setPixelSize(800, 800);
		pdfFrame.setUrl(s);
		RootPanel.get("FrameContainer").add(pdfFrame);
		
		
	}
	/**
	 * Method to pass call to Async
	 */
	private void generatePdf() {
		
		
		
		
		greetingService.greetServer(
				new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						Window.alert("Error Occured");
					}

					public void onSuccess(String result) {
						Window.alert(result);
						setFrame(result);
					}
				});
	}
	
	
}
