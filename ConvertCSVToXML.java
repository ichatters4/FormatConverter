package com.ic.attivio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

/**
 * This concrete class implements the convert 
 * behavior to read a CSV file and convert it 
 * to XML and write to a file.
 * Concrete classes should implement this interface 
 * to convert from one format to another..
 */
public class ConvertCSVToXML implements Convert {
	
	private String inFileName;
	private String outFileName;
	
	public ConvertCSVToXML(String inFileName, String outFileName) {
		this.inFileName = inFileName;
		this.outFileName = outFileName;
	}
	
	public void convert() {
		FileReader f = null;
		BufferedReader b = null;
		try {
			DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = df.newDocumentBuilder();
			Document document = db.newDocument();
			Element rootElement = document.createElement("XML");
			document.appendChild(rootElement);

			f = new FileReader(inFileName);
			b = new BufferedReader(f);
			
			int line = 0;
			List<String> headers = new ArrayList<String>();			
			String s = null;
			int tokenCount = 0;
			while ((s = b.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(s, ",", false);
                tokenCount = st.countTokens();                
                String[] rowTokens = new String[tokenCount];
                
                int index = 0;
                while (st.hasMoreTokens()) {
                    String next = st.nextToken();
                    rowTokens[index++] = next;
                }

                if (line == 0) { // 1st line is assumed to be the header
                    for (String col : rowTokens) {
                        headers.add(col);
                    }
                } else { // Data row
                    Element rowElement = document.createElement("row");
                    rootElement.appendChild(rowElement);
                    for (int col = 0; col < headers.size(); col++) {
                        String header = headers.get(col);
                        String value = null;

                        if (col < rowTokens.length) {
                            value = rowTokens[col];
                        } else {
                            // Default value
                            value = "";
                        }
                        
                        Element curElement = document.createElement(header);
                        curElement.appendChild(document.createTextNode(value));
                        rowElement.appendChild(curElement);
                    }
                }
                line++;			
			}
            try {
                TransformerFactory tranFactory = TransformerFactory.newInstance();
                Transformer aTransformer = tranFactory.newTransformer();
                aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
                aTransformer.setOutputProperty(OutputKeys.METHOD, "xml");
                aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                Source src = new DOMSource(document);
                Result result = new StreamResult(new File(outFileName));
                aTransformer.transform(src, result);

            } catch (TransformerException | TransformerFactoryConfigurationError exp) {
    			System.out.println(exp.getLocalizedMessage());
            } 
		} catch (FileNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
		} catch (ParserConfigurationException e) {
			System.out.println(e.getLocalizedMessage());
		}
		finally {
			try {
				if (b != null) {
					b.close();
				}
				
			} catch (IOException logOrIgnore) {}
		}
	}
}








