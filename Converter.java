package com.ic.attivio;

public abstract class Converter {
	
	/*
	 * Reference variables for the behavior 
	 * interface. 
	 */
	Convert convert;
	
	public Converter() {
	}
	
	public void performConvert() {
		/*
		 * Delegate to the behavior interface
		 * Composition relationship.
		 */
		convert.convert();
	}
	
	public void setConvertBehavior(Convert conv) {
		this.convert = conv;
	}
}
