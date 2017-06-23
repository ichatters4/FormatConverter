package com.ic.attivio;

public class ConverterDemo extends Converter {

	private String inFileName;
	private String outFileName;
	
	public ConverterDemo(String inFileName, String outFileName) {
		this.setInFileName(inFileName);
		this.setOutFileName(outFileName);
		
		this.convert = new ConvertCSVToXML(inFileName, outFileName);
	}

	public String getInFileName() {
		return inFileName;
	}

	public void setInFileName(String inFileName) {
		this.inFileName = inFileName;
	}

	public String getOutFileName() {
		return outFileName;
	}

	public void setOutFileName(String outFileName) {
		this.outFileName = outFileName;
	}

}
