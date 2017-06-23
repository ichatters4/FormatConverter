package com.ic.attivio;

/**
 * This is our enterprise :) client that will use the 
 * converter routines.. 
 */
public class Client {

	public static void main(String[] args) {
		
		String inFileName = "c:\\ic\\in.csv";
		String outFileName = "c:\\ic\\out.xml";
		
		Converter csvToXmlComv = new ConverterDemo(inFileName, outFileName);
		csvToXmlComv.performConvert();
		
		/*
		 * In order to dynamically change behavior, all we have to do
		 * is (for new interfaces):
		 * 
		 * csvToXmlComv.setConvertBehavior(new Converter...)
		 */
	}

}
