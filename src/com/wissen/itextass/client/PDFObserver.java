/**
 * 
 */
package com.wissen.itextass.client;

/**
 * Classes who want to observe login result must implement this interface.
 * 
 * Interface notify about Celsius value changed successfully and failure.
 * 
 * 
 * 
 */
public interface PDFObserver {

	/**
	 * Method to notify other widget observer about changed Fahrenheit value 	 
	 */
	void notifyPDFSucceeded(String url);

	/**
	 * Method to notify other widget observer about failure 	 
	 */
	void notifyPDFFailed(String errorMessage);
}
