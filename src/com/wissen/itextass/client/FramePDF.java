package com.wissen.itextass.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Frame;


/**
 * @author Saurabh Sonawane
 *
 */
public class FramePDF extends Composite implements PDFObserver {
	Frame pdfFrame;
	public FramePDF() {
		pdfFrame = new Frame();
		pdfFrame.setPixelSize(800, 800);
		
		Controller.getInstance().addPDFObserver(this);
		
		
	}

	@Override
	public void notifyPDFFailed(String errorMessage) {
		Window.alert("Error : "+errorMessage);
		
	}

	@Override
	public void notifyPDFSucceeded(String url) {
		pdfFrame.setUrl(url);
		
	}
	
	

}
