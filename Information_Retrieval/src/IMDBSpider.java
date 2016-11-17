
//Importe f?????????r URL

import java.io.*;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.json.Json;
import javax.json.JsonWriter;
import javax.security.auth.Subject;
import javax.swing.border.TitledBorder;

//JSON importe
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

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
  
public void fetchIMDBMovies(String movieListJSON, String outputDir)	
	throws IOException {
	// TODO add code here
	try {
//		File F = new File(movieListJSON);
		//TODO: exceptions definieren
//		if(false)throw new FileNotFoundException();
//		if(false)throw new IOException();
//		if(false)throw new ParseException(outputDir, 0);

		//Lese Datei ein und extrahiere StringArray
		//meine Implementation
			JSONParser parser = new JSONParser();			
			
		    JSONArray movielist = (JSONArray) parser.parse(new FileReader(movieListJSON));
			String[] output = new String[movielist.size()+1];

			int output_zeiger=0;
			@SuppressWarnings("unchecked")
			Iterator<JSONObject> iterator = movielist.iterator();
			while (iterator.hasNext()) {
				String s = (String) iterator.next().toString();
				if(s.contains(":") && s.contains("}")){
					s = s.trim().substring(s.indexOf(":")+2,s.indexOf("}")-1);
				}
				output[output_zeiger] = s/*.substring(s.indexOf(":"), s.indexOf("}"))*/;
				output_zeiger++;
			}
			
			for(int i=0; i<output.length-1; i++){
				//output[i] = cleanText(output[i]);
				//System.out.println(output[i]);
				output[i] = output[i].replace("\\", " ");
				output[i] = output[i].replace("/", " ");
				//System.out.println(ersterLink(output[i]));
			}
			
			for(int i=0; i<output.length-1; i++){
				output[i] = output[i].replaceAll("[^\\p{ASCII}]", "");
			}
						
	        Movie[] M = new Movie[output.length];
			for(int i=0; i<output.length-1; i++){
				M[i] = extractMetadata(output[i]);
				//System.out.println(M[i].getTitle());
			}
			
//			//erstelle für jeden Film eine JSON
			for(int i=0; i<output.length-1; i++){
				gebeFilmAus(M[i], outputDir+"/"+i+".json");
			}	
			System.out.println("DEBUG");
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
 * @param Stringrepraesentation einer HTML
 * @return erster Link
 * @throws IOException 
 */
private static String ersterLink(String s) throws IOException{
	org.jsoup.nodes.Document doc = Jsoup.connect("http://akas.imdb.com/find?q="+s+"&s=tt&ttype=ft").get();
    Element ersterFund = doc.getElementsByClass("result_text").first();
    String link = ersterFund.select("a[href]").toString();
    //String name = link.substring(link.indexOf(">")+1,link.indexOf("</a>"));
    link = link.substring(link.indexOf("\"")+1,link.indexOf(">")-1);
	return link;
}

/**
 * 
 * @param html
 * @return Movie objekt mit allen geforderten Informationen
 * @throws IOException 
 */
private static Movie extractMetadata(String name) throws IOException{
	Movie M = new Movie();
	String ersterLink = ersterLink(name);
	//System.out.println(ersterLink);
//html von erster Suchergebnis  
	org.jsoup.nodes.Document doc = Jsoup.connect("http://akas.imdb.com"+ ersterLink).get();
	String html = doc.toString();
	
//Metadaten aus doc lesen
	//url
		M.setUrl("http://akas.imdb.com"+ ersterLink);
	//title
		if(!doc.select("h1[class]").select("itemprop").text().isEmpty()){
			M.setTitle(doc.select("h1[class]").select("itemprop").text().substring(0,doc.select("h1[class]").select("itemprop").text().indexOf("- IMDb")-2));
		}
		else{
			M.setTitle(doc.title().toString().substring(0,doc.title().indexOf("- IMDb")-1));
		}
		System.out.println("Titel: " + M.getTitle());
	//year
		M.setYear(doc.title().substring(doc.title().indexOf("(")+1,doc.title().indexOf(")")));
		//System.out.println("Jahr: " + M.getYear());
	//rating Value
		M.setRatingValue(doc.select("span[itemprop=ratingValue]").text());
		//System.out.println("RatingValue: " + M.getRatingValue());
	//rating Count 
		M.setRatingCount(doc.select("span[itemprop=ratingCount]").text());
		//System.out.println("RatingCount: " + M.getRatingCount());
	//gross
		String gross = "";
		//System.out.println(doc. select("div[class=txt-block]").get(10).text());
		
		boolean gross_vorhanden=false;
		for(int i=0; i<doc.select("div[class=txt-block]").size();i++){
			if(doc.select("div[class=txt-block]").get(i).text().contains("Gross")){
				gross = doc. select("div[class=txt-block]").get(i).text();
				gross_vorhanden = true;
				//System.out.println(gross);
			}
		}
		if(gross_vorhanden){
			if(gross.contains("$")){gross = gross.substring(gross.indexOf("$"),gross.indexOf("("));}
			else if (gross.contains("€")){gross = gross.substring(gross.indexOf("€"),gross.indexOf("("));}
		}
		else{gross = "-";}
		M.setGross(gross);

		
		//System.out.println("Gross: " + gross);
	//Countrylist
		String alleLaender = doc.select("div[class=txt-block]").get(4).select("a[href]").toString();
		List<String> countrylist = new ArrayList<String>();
		for(int i=0; i < doc.select("div[class=txt-block]").get(4).select("a[href]").size();i++){
			String test = doc.select("div[class=txt-block]").get(4).select("a[href]").get(i).text().toString();
			if(test.isEmpty())break;
			countrylist.add(test);
		}	
		M.setCountryList(countrylist);
		//System.out.println("" + M.getCountryList());			
	//Genrelist
		String alleGenre = doc.select("div[class=see-more inline canwrap]").select("a[href]").toString();
		List<String> genre = new ArrayList<String>();
		boolean ueberspringen = false;
		for(int i=0;i < doc.select("div[class=see-more inline canwrap]").select("a[href]").size();i++){
			if(doc.select("div[class=see-more inline canwrap]").select("a[href]").get(i).text().toString().startsWith("See All")) {ueberspringen=true;continue;}
			if(!ueberspringen)continue;
			String test = doc.select("div[class=see-more inline canwrap]").select("a[href]").get(i).text().toString();
			if(test.isEmpty())break;
			genre.add(test);
		}
		M.setGenreList(genre);
		//System.out.println(M.getGenreList());
	//duration
		String duration ="";
		if(!doc.select("time[itemprop]").isEmpty()){
			duration = doc.select("time[itemprop]").first().text().toString();
		}
		else {duration = "-";}
		M.setDuration(duration);
		//System.out.println(duration);
	//cast
		List<String> cast = new ArrayList<String>();
		for(int i=0;i < doc.select("table[class]").select("span[class]").select("span[itemprop]").size();i++){
			String test = doc.select("table[class]").select("span[class]").select("span[itemprop]").get(i).text();
			if(test.isEmpty())break;
			cast.add(test);
		}
		M.setCastList(cast);
		//System.out.println(cast);
	//Charaktere 
		List<String> charaktere = new ArrayList<String>();
		for(int i=0;i < doc.select("table[class]").select("td[class]").select("div").size();i++){
			String test = doc.select("table[class]").select("td[class]").select("div").get(i).text();
			if(test.isEmpty())break;
			charaktere.add(test);
		}
		M.setCharacterList(charaktere);
		//System.out.println(charaktere);
	//Budget
		String budget ="";
		boolean budget_vorhanden=false;
			for(int i=0; i<doc.select("div[class=txt-block]").size();i++){
				if(doc.select("div[class=txt-block]").get(i).text().contains("Budget")){
					budget = doc.select("div[class=txt-block]").get(i).text();
					budget_vorhanden = true;
				}
			}
		if(budget_vorhanden){
			if(budget.contains("$")){budget = budget.substring(budget.indexOf("$"),budget.indexOf("("));}
			else if (budget.contains("€")){budget = budget.substring(budget.indexOf("€"),budget.indexOf("("));}
		}
		else{budget = "-";}
		M.setBudget(budget);
		//System.out.println("Budget: " + budget);
	//Direktor
		List<String> direktoren = new ArrayList<String>();
		for(int i=0;i < doc.select("span[itemprop=director]").select("span[class=itemprop]").size();i++){
			String test = doc.select("span[itemprop=director]").select("span[class=itemprop]").get(i).text();
			if(test.isEmpty())break;
			direktoren.add(test);
		}
		M.setDirectorList(direktoren);
		//System.out.println(direktoren);
	//Beschreibung
		M.setDescription(doc.select("div[class=inline canwrap]").select("[itemprop]").text());
		//System.out.println(M.getDescription());
	return M;
}

  /**
   * Helper method to remove html and formating from text.
   * @param text
   *          The text to be cleaned
   * @return clean text
   */
  protected static String cleanText(String text) {
    return text.replaceAll("\\<.*?>", "").replace("&nbsp;", " ").replace("\n", " ").replaceAll("\\s+", " ").trim();
  }

 /**
  * Hilsroutine zum testen der Hilfsfunktionen
 * @throws IOException 
  */
public static void test() throws IOException{
	  //test URL
	  String movie = "X-Men: Days of Future Past";
	  String ersterLink = ersterLink(movie);
	  System.out.println(ersterLink);
	  
	  Movie test = extractMetadata(movie);
	  gebeFilmAus(test, "./data/blub.json");
}
//TODO: reihenfolge von attributen beachten ?

public static void gebeFilmAus(Movie M,String Outdir) throws IOException{
	  String metadaten = "";
	  //JSON Objekt schreiben
	  	//+budget
			  JSONObject ouput = new JSONObject();
			  ouput.put("budget", M.getBudget());
         //+characterList
		      JSONArray b = new JSONArray();
			  for(int i=0; i<M.getCharacterList().size(); i++){
			  		b.add(M.getCharacterList().get(i));
			  }
			  ouput.put("characterList", b);
		//+castlist
			  JSONArray a = new JSONArray();
			  for(int i=0; i<M.getCastList().size(); i++){
			  		a.add(M.getCastList().get(i));
			  }
		      ouput.put("cast list", a);	  
	     //+countryList
			  JSONArray c = new JSONArray();
			  for(int i=0; i<M.getCountryList().size(); i++){
			  		c.add(M.getCountryList().get(i));
			  }
			  ouput.put("countryList", c);
		 //+description
			  ouput.put("description", M.getDescription());
		 //+directorList
			  JSONArray d = new JSONArray();
			  for(int i=0; i<M.getDirectorList().size(); i++){
			  		d.add(M.getDirectorList().get(i));
			  }
			  ouput.put("directorList", d);
		//+duration
			  ouput.put("duration", M.getDuration());
		 //+genreList
			  JSONArray e = new JSONArray();
			  for(int i=0; i<M.getGenreList().size(); i++){
			  		e.add(M.getGenreList().get(i));
			  }
			  ouput.put("genreList", e);
		 //+gross
			  ouput.put("gross", M.getGross());
		//+ratingCount
			  ouput.put("ratingCount", M.getRatingCount());
		//+ratingValue
			  ouput.put("ratingValue", M.getRatingValue());
		//+title
			  ouput.put("title", M.getTitle());
		//+url
			  ouput.put("url", M.getUrl());
		//+year
			  ouput.put("Year", M.getYear());
			  
				// try-with-resources statement based on post comment below :)
		  try (FileWriter file = new FileWriter(Outdir)) {
		  file.write(ouput.toJSONString());
		  System.out.println("Successfully Copied JSON Object to File...");
		  System.out.println("\nJSON Object: " + ouput);
		  }
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
