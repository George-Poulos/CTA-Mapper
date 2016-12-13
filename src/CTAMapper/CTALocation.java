
package CTAMapper;

/**
 * This project takes information from CTA API and plots the current locations of every train on every line
 *
 * @author George Poulos
 * @version 1.0
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.*;
import java.math.BigDecimal;
import java.util.LinkedList;

import org.openstreetmap.gui.jmapviewer.*;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;


public class CTALocation {
    public static String BLUE_LINE = "";
    public static String RED_LINE = "";
    public static String BROWN_LINE = "";
    public static String GREEN_LINE = "";
    public static String ORANGE_LINE = "";
    public static String PURPLE_LINE = "";
    public static String PINK_LINE = "";
    public static String YELLOW_LINE = "";


    public static void setAPI(){
        File fin = new File("src/CTA-API.txt");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fin);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //Construct BufferedReader from InputStreamReader
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        try {
            BLUE_LINE = br.readLine();
            RED_LINE = br.readLine();
            BROWN_LINE = br.readLine();
            GREEN_LINE = br.readLine();
            ORANGE_LINE = br.readLine();
            PURPLE_LINE = br.readLine();
            PINK_LINE = br.readLine();
            YELLOW_LINE = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws Exception {
        JMapViewer map = new JMapViewer() {

            @Override
            public String getToolTipText(MouseEvent e) {
                ICoordinate c = getPosition(e.getX(),e.getY());
                return  c.getLat()+ " " + c.getLon();
            }
        };
        setAPI();
        JFrame frame = new JFrame();
        frame.setTitle("CTA Train Mapper");
        frame.setSize(1280,720);
        map.setDisplayToFitMapMarkers();
        map.setZoomContolsVisible(false);
        frame.add(map);
        frame.setVisible(true);
        boolean isValid = true;

        while(isValid){
            getLocations(map,BLUE_LINE,"Blue");
            getLocations(map,RED_LINE,"Red");
            getLocations(map,BROWN_LINE,"Brown");
            getLocations(map,GREEN_LINE,"Green");
            getLocations(map,ORANGE_LINE,"Orange");
            getLocations(map,PURPLE_LINE,"Purple");
            getLocations(map,PINK_LINE,"Pink");
            getLocations(map,YELLOW_LINE,"Yellow");
            map.setDisplayToFitMapMarkers();
            Thread.sleep(5000);
            map.removeAllMapMarkers();
        }

    }

    private static void getLocations(JMapViewer map, String URL, String line){
            CTAHandler tmp = new CTAHandler();
            LinkedList<BigDecimal> latData = new LinkedList<>();
            LinkedList<BigDecimal> lonData = new LinkedList<>();
            LinkedList<String>runData = new LinkedList<>();
            LinkedList<String>directionsList = new LinkedList<>();
            tmp.parser(URL, latData, lonData, runData,directionsList);
            for(int i = 0; i < latData.size(); i++) {
                System.out.println("AT: " + runData.get(i) + " --> Lat : " + latData.get(i) + " Lon : " + lonData.get(i));
                if (latData.get(i).doubleValue() == 0 && lonData.get(i).doubleValue() == 0){

                }
                else{
                    MapMarkerDot marker = new MapMarkerDot( new Coordinate(latData.get(i).doubleValue(), lonData.get(i).doubleValue()));

                    Style style = marker.getStyle();
                    switch (line) {
                        case "Blue":
                            style.setColor(Color.blue);
                            if(Integer.parseInt(directionsList.get(i)) == 5){
                                style.setColor(Color.black);}
                            style.setBackColor(Color.blue);
                            break;
                        case "Red":
                            style.setColor(Color.red);
                            if(Integer.parseInt(directionsList.get(i)) == 5){
                                style.setColor(Color.black);}
                            style.setBackColor(Color.red);
                            break;
                        case "Brown":
                            style.setColor(new Color(156, 93, 82));
                            if(Integer.parseInt(directionsList.get(i)) == 5){
                                style.setColor(Color.black);}
                            style.setBackColor(new Color(156, 93, 82));
                            break;
                        case "Green":
                            style.setColor(Color.green);
                            if(Integer.parseInt(directionsList.get(i)) == 5){
                                style.setColor(Color.black);}
                            style.setBackColor(Color.green);
                            break;
                        case "Orange":
                            style.setColor(Color.orange);
                            if(Integer.parseInt(directionsList.get(i)) == 5){
                                style.setColor(Color.black);}
                            style.setBackColor(Color.orange);
                            break;
                        case "Purple":
                            style.setColor(new Color(186, 85, 211));
                            if(Integer.parseInt(directionsList.get(i)) == 5){
                                style.setColor(Color.black);}
                            style.setBackColor(new Color(186, 85, 211));
                            break;
                        case "Pink":
                            style.setColor(Color.pink);
                            if(Integer.parseInt(directionsList.get(i)) == 5){
                                style.setColor(Color.black);}
                            style.setBackColor(Color.pink);
                            break;
                        case "Yellow":
                            style.setColor(Color.yellow);
                            if(Integer.parseInt(directionsList.get(i)) == 5){
                                style.setColor(Color.black);}
                            style.setBackColor(Color.yellow);
                            break;
                    }
                    map.addMapMarker(marker);
                }
            }
            System.out.println("--------------End " + line + " Line-------------" );


            // print SOAP Response
    }

}
