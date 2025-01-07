//package com.zogirdex.weather_calendar;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.IOException;
//import java.io.FileNotFoundException;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.net.HttpURLConnection;
//
//import java.util.List;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.GregorianCalendar;
//
//import java.nio.charset.Charset;
//
//import mt.edu.um.cf2.jgribx.GribFile;
//import mt.edu.um.cf2.jgribx.GribRecord;
//import mt.edu.um.cf2.jgribx.NotSupportedException;
//import mt.edu.um.cf2.jgribx.NoValidGribException;
//
//
///**
// *
// * @author tom3k
// */
//public class FileDownloader {
//    
//    private static final String API_KEY = "VHEMMB29AXXDT86HR399VV4RT";
//    private static final String CONTENT_TYPE = "json";
//    
////    public static List<String> urls = new ArrayList() {{
////        add("https://danepubliczne.imgw.pl/api/data/product/id/COSMO_HVD_00_00");
////   }};
//    
//    public FileDownloader() {
//        
//    }
//    
//    // lub "createRequestUrl"
//    public static URL createEndpoint(String location, String unitGroup) {
//        try {
//            return new URL(String.format(
//                "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/%s?unitGroup=%s&key=%s&contentType=%s",
//                location, unitGroup, API_KEY, CONTENT_TYPE
//            ));
//        }
//        catch(MalformedURLException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }
//    
//    public static void sendRequest() {
//        
//    }
//    
//    public static void loadData(String location, String unitGroup) {
//        try {
//            URL endpoint = createEndpoint(location, unitGroup);
//            
//            HttpURLConnection connection = (HttpURLConnection) endpoint.openConnection();
//            
//            connection.setRequestProperty("Accept", "application/json");
//            
//            if (connection.getResponseCode() == 200) {
//                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
//                br.lines().forEach(item -> {
//                System.out.println(item);
//                });
//            }
//        }
//        catch(MalformedURLException ex) {
//            ex.printStackTrace();
//        }
//        catch(IOException ex) {
//            ex.printStackTrace();
//        }
//        
//    // filename = "filename.grb"
////    public static void downloadGrib(String filename) {
////        try {
////            GribFile gribFile = new GribFile(filename);
////            
////            Calendar forecastDate = new GregorianCalendar(2017, Calendar.NOVEMBER, 6, 14, 0, 0);
////            String parameterCode = "TMP";    // parameter code for temperature
////            String ltvid = "ISBL:200";       // LTVID (level type-value ID)
////            double latitude = 35.8985;       // latitude at point of interest
////            double longitude = 14.5133;      // longitude at point of interest
////            
////            GribRecord record = gribFile.getRecord(forecastDate, parameterCode, ltvid);
////            double value = record.getValue(latitude, longitude);
////            System.out.println(value);
////        }
////        catch(FileNotFoundException ex) {
////            ex.printStackTrace();
////        }
////        catch(IOException ex) {
////            ex.printStackTrace();
////        }
////        catch(NotSupportedException ex) {
////            ex.printStackTrace();
////        }
////        catch(NoValidGribException ex) {
////            ex.printStackTrace();
////        }
////        
////    }
//            
//    }
//}
