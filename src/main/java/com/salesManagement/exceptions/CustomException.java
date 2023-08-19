package com.highradius.salesManagement.exceptions;

/**
 * This method for handle the every exception that can be happen...
 * @author thisara.kaushan
 *
 */
public class CustomException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomException(String message) {
        super(message);
    }
}
