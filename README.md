# FormatConverter

This FormatConverter has been implemented using the "Strategy" Design Pattern.

A convert "behavior" is an interface that just mandates a convert routine to be implemented.  It is expected that concrete classes would implement their own convert routines.  For our case, we have a concrete class ConvertCSVtoXML that does our job. If tomorrow we have to convert XML to JSON we would implement a class ConvertXMLtoJSON etc.

An abstract class Converter delegates these "behaviors" to objects that implement these behaviors/interfaces.  It also allows dynamically allows the behavior to be changed at run-time via a "setConvertBehavior" (dynamic polymorphism).

ConverterDemo is the concrete realization of this abstraction.

Finally, a client would use this as demonstrated via the example shown in Client.java

By no means is this a production ready - I can think of many ways to enhance it and make it more robust.  

- This parser does not handle tabs or other escape characters if found in the first row of the CSV file.
