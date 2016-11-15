

//andere Importe
import java.io.*;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;




import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;
import javax.json.stream.JsonParser;
import javax.swing.text.html.HTMLEditorKit.Parser;










//Importe für URL
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream.PutField;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;















import org.glassfish.json.JsonParserImpl;
//JSON importe
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class IMDBSpider {
  

  public IMDBSpider() {
  }
  
  public static void json_beispiel(String[] args) {        
  }  
  
  /**
   * For each title in file movieListJSON:
   *
   * <pre>
   * You should:
   * - First, read a list of 500 movie titles from the JSON file in 'movieListJSON'.
   *
   * - Secondly, for each movie title, perform a web search on IMDB and retrieve
   * movieâ€™s URL: http://akas.imdb.com/find?q=<MOVIE>&s=tt&ttype=ft
   *
   * - Thirdly, for each movie, extract metadata (actors, budget, description)
   * from movieâ€™s URL and store to a JSON file in directory 'outputDir':
   *    http://www.imdb.com/title/tt0499549/?ref_=fn_al_tt_1 for Avatar - store
   * </pre>
   *
   * @param inputFileName
   *          JSON file containing movie titles
   * @param outputDir
   *          output directory for JSON files with metadata of movies.
   * @throws IOException
   */
//TODO 2:  for each movie title, perform a web search on IMDB and retrieve
   // movieâ€™s URL: http://akas.imdb.com/find?q=<MOVIE>&s=tt&ttype=ft
//TODO 3:Thirdly, for each movie, extract metadata (actors, budget, description)
  // from movieâ€™s URL and store to a JSON file in directory 'outputDir':
  //   http://www.imdb.com/title/tt0499549/?ref_=fn_al_tt_1 for Avatar - store
  
public void fetchIMDBMovies(String movieListJSON, String outputDir)	
	throws IOException {
	// TODO add code here
	try {
		 //prüfe ob Datei existiert
		 File file = new File(movieListJSON);
		 if(/*file.exists()*/false)throw new FileNotFoundException();
		//Lese Datei ein und extrahiere StringArray
		//meine Implementation
//			JSONParser parser = new JSONParser();
//		    JSONArray movielist = (JSONArray) parser.parse(new FileReader(movieListJSON));
//			String[] output = new String[movielist.size()+1];
//			int output_zeiger=0;
//			Iterator<JSONObject> iterator = movielist.iterator();
//			while (iterator.hasNext()) {
//				String s = (String) iterator.next().toString();
//				//leider beginnen ab hier schon die Probleme: auf das Stringarray lässt sich nicht ordentlich zugreifen!
//				if(s.contains(":") && s.contains("}")){
//					//System.out.println(s.indexOf(":") + ", " + s.indexOf("}"));
//					//s = s.trim().substring(13,s.length()-3); FUNZT NICHT!!
//				}
//				output[output_zeiger] = s/*.substring(s.indexOf(":"), s.indexOf("}"))*/;
//				output_zeiger++;
//			}
//		
//			//Aus Stringpräsentation des jsonobject den Titel filtern
//			//TODO: Hier stimmt etwas nicht: Strings können nicht richtig angesprochen werden!
//			
//			String K[] = new String[output.length];
//			//Sonderzeichen rausfiltern und Stringpräsentation des jsonboj in Filmnamen
//			for(int i=1; i<output.length; i++){
//				//output[i] = cleanText(output[i]); //TODO: kann auf String nicht zugreifen!
//				//output[i] = "http://akas.imdb.com/find?q=<"+ output[i]+ ">&s=tt&ttype=ft";
//				K[i] = (String) output[i].substring(13,output[i].length()-3);
//				System.out.println(output[i]);
//				
//			}
		//Implementation mithile von MovieReader (hab ich zu spät gesehen ;)) und Jsonparser 
		//der eine Datei als Stream in Jsonobject umwandeln kann
			//Klappt nicht (denke ich) JsonObject ml = (JsonObject)  new JsonParserImpl(JsonParser.class.getResourceAsStream(movieListJSON));
		 	MovieReader MR = new MovieReader();		
			//movieListJSON:String -> jsonobjekt
				//file -> String
				String inhalt = new Scanner(new File(movieListJSON)).useDelimiter("\\Z").next();
				//String -> jsonarray
		        JsonReader reader = Json.createReader(new StringReader(inhalt));
		        JsonArray movies_arr = (JsonArray) reader.readArray();
		        //jsonarray -> jsonobj??
		        JsonObject movie_obj = new JsonObject();
		        
		        List<String> movielist = MR.getJsonArray(movies, "movie_name");
			
			for(String s: movielist){
				System.out.println(s);
			}
			
		//Suche Titel und gebe
//		for(String i: output){
//			String html = URL_aufrufen(ersterLink(URL_aufrufen(i)));
//		}
		
		
	
	    } 
	    catch (FileNotFoundException e) {
          e.printStackTrace();
        } 
	    catch (IOException e) {
          e.printStackTrace();
        } 
	    catch (Exception e) {
            e.printStackTrace();
        } 
//		catch (ParseException e) {
//          e.printStackTrace();
//      }
}
	  
/**
 * 	  
 * @param String s (URL)
 * @return html string
 */
public static String URL_aufrufen(String s){
		s = "http://akas.imdb.com/find?q="+ s + "&s=tt&ttype=ft";
        URL url;
        String html="";
        try {
            // get URL content
            url = new URL(s);
            URLConnection conn = url.openConnection();

            // open the stream and put it into BufferedReader
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                    html += inputLine + "\n";
            }
            br.close();
        
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return html;
}

/**
 * @param Stringrepräsentation einer HTML
 * @return erster Link
 */
private static String ersterLink(String html){
	String l1 = html.substring(html.indexOf("primary_photo")+25,html.indexOf("primary_photo")+100);
	String l2 = l1.substring(0, l1.indexOf("\""));
	return l2;
}

/**
 * 
 * @param html
 * @return Movie objekt mit allen geforderten Informationen
 */
private static Movie extractMetadata(String html){
	Movie M = new Movie();
	//TODO: Magic?!
	
	
	
	return M;
}
  /**
   * Helper method to remove html and formating from text.
   * @param text
   *          The text to be cleaned
   * @return clean text
   */
  protected static String cleanText(String text) {
    return text.replaceAll("\\<.*?>", "").replace("&nbsp;", " ")
        .replace("\n", " ").replaceAll("\\s+", " ").trim();
  }

 /**
  * Hilsroutine zum testen der Hilfsfunktionen
  */
  public static void test(){
	  //test URL
	  String movie = "Matrix";
	  System.out.println(ersterLink(movie));
  }
  
  public static void main(String argv[]) throws IOException {
    String moviesPath = "./data/movies.json";
    String outputDir = "./data";

    if (argv.length == 2) {
      moviesPath = argv[0];
      outputDir = argv[1];
    } else if (argv.length != 0) {
      System.out.println("Call with: IMDBSpider.jar <moviesPath> <outputDir>");
      System.exit(0);
    }
    
    IMDBSpider sp = new IMDBSpider();
    sp.fetchIMDBMovies(moviesPath, outputDir);
    
    //test();
  }
}
