package com.wissen.itextass.client;

//import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;



/**
 * Class represents a controller part of MVC. Its acts as a Mediator to
 * application.
 * 
 */
public class Controller {

	/**
	 * Create a remote service proxy to talk to the server-side Login
	 * service.
	 */
	private final GreetingServiceAsync greetingService = GWT
	.create(GreetingService.class);
	public static Controller _instance;

	private Controller() {
	}

	public static synchronized Controller getInstance() {
		if (_instance == null) {
			_instance = new Controller();
		}
		return _instance;
	}

	public GreetingServiceAsync getLoginService() {
		return greetingService;
	}

	// ---------------------- Observers -------------------------------
	
	private List<PDFObserver> pdfObservers = new ArrayList<PDFObserver>();
	

	
	// --------------- Registration/DeRegitration methods --------------
	
	public void addPDFObserver(PDFObserver observer) {
		pdfObservers.add(observer);
	}
	
	

	public void removePDFObserver(PDFObserver observer) {
		pdfObservers.remove(observer);
	}

	
	// ---------------------- Controller's Methods ---------------------
	
	public void greetServer() {
		greetingService.greetServer(tempCallback);
	}
	
	
	// -------------------- Call Back classes ---------------------------
	/**
	 * Call back Class for Celsius to Fahrenheit conversion
	 */
	
	AsyncCallback<String> tempCallback = new AsyncCallback<String>() {
		public void onFailure(Throwable caught) {
			GWT.log("Error in Conversion", caught);
			Window.alert("Error in Asysnc");
		}
		
		

		@Override
		public void onSuccess(String result) {
			for (PDFObserver observer : pdfObservers) {
				observer.notifyPDFSucceeded(result);
			}
			
		}
	};

	
	
}
