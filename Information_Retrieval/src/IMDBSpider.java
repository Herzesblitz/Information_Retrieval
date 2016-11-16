

//Importe f?????????r URL

import java.io.*;
import java.net.*;
import java.text.ParseException;
import java.util.Iterator;

import javax.json.JsonReader;
import javax.swing.text.Document;
import javax.json.*;

import org.htmlcleaner.*;

//JSON importe
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.*;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

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
   * movie???????????????????????????s URL: http://akas.imdb.com/find?q=<MOVIE>&s=tt&ttype=ft
   *
   * - Thirdly, for each movie, extract metadata (actors, budget, description)
   * from movie???????????????????????????s URL and store to a JSON file in directory 'outputDir':
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
   // movies URL: http://akas.imdb.com/find?q=<MOVIE>&s=tt&ttype=ft
//TODO 3:Thirdly, for each movie, extract metadata (actors, budget, description)
  // from movies URL and store to a JSON file in directory 'outputDir':
  //   http://www.imdb.com/title/tt0499549/?ref_=fn_al_tt_1 for Avatar - store
  
public void fetchIMDBMovies(String movieListJSON, String outputDir)	
	throws IOException {
	// TODO add code here
	try {
//		File F = new File(movieListJSON);
		//TODO: exceptions definieren
		if(false)throw new FileNotFoundException();
		if(false)throw new IOException();
		if(false)throw new ParseException(outputDir, 0);

		//Lese Datei ein und extrahiere StringArray
		//meine Implementation
			JSONParser parser = new JSONParser();			
			
		    JSONArray movielist = (JSONArray) parser.parse(new FileReader(movieListJSON));
			String[] output = new String[movielist.size()+1];

			int output_zeiger=0;
			Iterator<JSONObject> iterator = movielist.iterator();
			while (iterator.hasNext()) {
				String s = (String) iterator.next().toString();
				//leider beginnen ab hier schon die Probleme: auf das Stringarray l?????????sst sich nicht ordentlich zugreifen!
				if(s.contains(":") && s.contains("}")){
					//System.out.println(s.indexOf(":") + ", " + s.indexOf("}"));
					s = s.trim().substring(s.indexOf(":")+2,s.indexOf("}")-1);
				}
				output[output_zeiger] = s/*.substring(s.indexOf(":"), s.indexOf("}"))*/;
				output_zeiger++;
			}
			
			for (String s : output) {
				System.out.println(s);
			}
					
			String html_movies[] = new String[output.length];
		
			for(String movies: output){
				html_movies[movies.indexOf(movies)] =  URL_aufrufen(ersterLink(URL_aufrufen(movies)));
			}
			
	
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
		//		s = "http://akas.imdb.com/find?q="+ s + "&s=tt&ttype=ft";
		//        String html="";
		//        try {
		//            // get URL content
		//           URL url = new URL(s);
		//            URLConnection conn = url.openConnection();
		//
		//            // open the stream and put it into BufferedReader
		//            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		//
		//            String inputLine;
		//            while ((inputLine = br.readLine()) != null) {
		//                    html += inputLine + "\n";
		//            }
		//            br.close();
		//        
		//            System.out.println(html);
		//        } catch (MalformedURLException e) {
		//            e.printStackTrace();
		//        } catch (IOException e) {
		//            e.printStackTrace();
		//        }
//htmlcleaner benutzen
		//	try{
		//		if(false)throw new FileNotFoundException();
		//		if(false)throw new IOException();
		//		//if(false)throw new ParseException(outputDir, 0);
		//		HtmlCleaner cleaner = new HtmlCleaner();
		//		//String urlEncoded = URLEncoder.encode("akas.imdb.com/find?q="+ s + "%22&s=tt&ttype=ft", "UTF8");
		//		String url2 = "http://akas.imdb.com/find?q=%22"+s+"%22&s=tt&ttype=ft";
		//		URL url = new URL(url2);
		//		
		//		TagNode root = cleaner.clean(url);
		//		//HtmlCleaner.getInnerHtml(node);
		//		//root.findElementByName(, isRecursive)
		//		String html = "<" + root.getName() + ">" + cleaner.getInnerHtml(root) + "</" + root.getName() + ">";
		//		
		//		//html.writeToFile(node, "/data/movie.html","UTF-8");
		//		
		//		String HREF_XPATH="//table(@class='findList']//td[@class='result_text']/a@href";
		//		String TITLE_XPATH="//table(@class='findList']//td[@class='result_text']/a/text";
		//		
		//	    Object[] movie_href = root.evaluateXPath(TITLE_XPATH);
		//		
		//		System.out.println();
		//	}
		//		 
		//	    catch (FileNotFoundException e) {
		//          e.printStackTrace();
		//        } 
		//	    catch (IOException e) {
		//          e.printStackTrace();
		//        } 
		//	    catch (Exception e) {
		//            e.printStackTrace();
		//        } 
		//
//jsop
	//String html = "<html><head><title>First parse</title></head>"  + "<body><p>Parsed HTML into a doc.</p></body></html>";
			org.jsoup.nodes.Document doc = Jsoup.parse(s);
return "";
}

/**
 * @param Stringrepr?????????sentation einer HTML
 * @return erster Link
 */
private static String ersterLink(String html){
	return "";
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
	  String movie = "Terminator";
	  System.out.println(URL_aufrufen(movie));
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
    //sp.fetchIMDBMovies(moviesPath, outputDir);
    
    test();
  }
}

