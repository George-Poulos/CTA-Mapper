package CTAMapper;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.util.LinkedList;

public class CTAHandler extends DefaultHandler {
    public void parser(String URL_SOURCE, LinkedList<BigDecimal> listOfLat, LinkedList<BigDecimal> listOfLon,
                       LinkedList<String> listOfRuns, LinkedList<String> listOfDirections) {
        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                boolean bfLat = false;
                boolean bfLon = false;
                boolean bRun = false;
                boolean bStation = false;
                boolean bDir = false;

                public void startElement(String uri, String localName, String qName,
                                         Attributes attributes) throws SAXException {

                    //System.out.println("Start Element :" + qName);

                    if (qName.equalsIgnoreCase("lat")) {
                        bfLat = true;
                    }

                    if (qName.equalsIgnoreCase("lon")) {
                        bfLon = true;
                    }

                    if (qName.equalsIgnoreCase("rn")) {
                        bRun = true;
                    }

                    if (qName.equalsIgnoreCase("trDr")) {
                        bDir = true;
                    }
                    if (qName.equalsIgnoreCase("nextStaNm")) {
                        bStation = true;
                    }

                }

                public void endElement(String uri, String localName,
                                       String qName) throws SAXException {

                    //System.out.println("End Element :" + qName);

                }

                public void characters(char ch[], int start, int length) throws SAXException {
                    String newLat, newLon, newRun, directon;
                    BigDecimal decLat = null, decLon = null;
                    if(bfLat) {
                        newLat = new String(ch, start, length);
                        decLat = new BigDecimal(newLat);
                        bfLat = false;
                        listOfLat.add(decLat);
                    }

                    if(bfLon){
                        newLon = new String(ch, start, length);
                        decLon = new BigDecimal(newLon);
                        bfLon = false;
                        listOfLon.add(decLon);
                    }

                    if(bStation){
                        newRun = new String(ch, start, length);
                        bStation = false;
                        listOfRuns.add(newRun);
                    }

                    if (bRun) {
                        bRun = false;
                    }

                    if (bDir) {
                        directon = new String(ch, start, length);
                        listOfDirections.add(directon);
                        bDir = false;
                    }

                }

            };
            URL urlObject = new URL(URL_SOURCE);
            // Open the stream (which returns an InputStream):
            InputStream in = urlObject.openStream();
            saxParser.parse(in, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}