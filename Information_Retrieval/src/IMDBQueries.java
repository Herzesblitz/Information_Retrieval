import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import java.util.Arrays;
//import com.sun.org.apache.xalan.internal.xsltc.dom.SortingIterator;


@SuppressWarnings("static-method")
public class IMDBQueries {

static boolean debug = true;


  /**
   * A helper class for pairs of objects of generic types 'K' and 'V'.
   *
   * @param <K>
   *          first value
   * @param <V>
   *          second value
   */
  class Tuple<K, V> {
    K first;
    V second;

    public Tuple(K f, V s) {
      this.first = f;
      this.second = s;
    }

    @Override
    public int hashCode() {
      return this.first.hashCode() + this.second.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      return this.first.equals(((Tuple<?, ?>) obj).first)
          && this.second.equals(((Tuple<?, ?>) obj).second);
    }
  }


private Movie first;
private String second;
  
//sortiert nach key (double, aufsteigend)
public List<Tuple<Movie,String>> insertionSort(List<Tuple<Movie, String>> unsortiert){
	  Tuple<Movie,String> Element= new Tuple<Movie,String>(first, second);
	  int j;
		for (int i = 0; i < unsortiert.size(); i++) {
			Element.first=unsortiert.get(i).first;
			Element.second=unsortiert.get(i).second;
			j=i;
			while(j>0&&(Double.parseDouble(unsortiert.get(j-1).second)<=(Double.parseDouble(Element.second)))) //Aenderung j>1 -> j>0
			{
				
				unsortiert.get(j).first=unsortiert.get(j-1).first;
				unsortiert.get(j).second=unsortiert.get(j-1).second;
				j--;
			}
			unsortiert.get(j).first=Element.first;
			unsortiert.get(j).second=Element.second;
			
					
//					INSERTIONSORT(A)
//					1 for i â†� 2 to LÃ¤nge(A) do
//					2      einzusortierender_wert â†� A[i]
//					3      j â†� i
//					4      while j > 1 and A[j-1] > einzusortierender_wert do
//					5           A[j] â†� A[j - 1]
//					6           j â†� j âˆ’ 1
//					7      A[j] â†� einzusortierender_wert
		}
	return unsortiert;
	  
  }
  
//sortiert nach key (aufsteigend)
  public List<Tuple<String, Integer>> insertionSort2(List<Tuple<String,Integer>> unsortiert){
	String f = null;
	Integer s=0;
	
	Tuple<String,Integer> Element= new Tuple<String,Integer>(f,s);
	  int j;
		for (int i = 2; i < unsortiert.size(); i++) {
			Element.first=unsortiert.get(i).first;
			Element.second=unsortiert.get(i).second;
			j=i;
			while(j>1&&(unsortiert.get(j-1).second)<(Element.second))
			{
				
				unsortiert.get(j).first=unsortiert.get(j-1).first;
				unsortiert.get(j).second=unsortiert.get(j-1).second;
				j--;
			}
			 
			unsortiert.get(j).first=Element.first;
			unsortiert.get(j).second=Element.second;
			
					
//					INSERTIONSORT(A)
//					1 for i â†� 2 to LÃ¤nge(A) do
//					2      einzusortierender_wert â†� A[i]
//					3      j â†� i
//					4      while j > 1 and A[j-1] > einzusortierender_wert do
//					5           A[j] â†� A[j - 1]
//					6           j â†� j âˆ’ 1
//					7      A[j] â†� einzusortierender_wert
		}
		unsortiert.remove(unsortiert.get(0));
	return unsortiert;
	  
  }
  
//sortiert nach key (aufsteigend)  
  public List<Movie> insertionSort3(List<Movie> unsortiert){
		String f = null;
		Integer s=0;
		
		Movie Element= new Movie();
		  int j;
			for (int i = 2; i < unsortiert.size(); i++) {
				Element=unsortiert.get(i);

				j=i;
				while(j>1&&(Integer.parseInt(unsortiert.get(j-1).getYear()))<Integer.parseInt((Element.getYear())))
				{
					
					unsortiert.set(j,unsortiert.get(j-1));
					j--;
				}
				 
				unsortiert.set(j,Element);

				
						
//						INSERTIONSORT(A)
//						1 for i â†� 2 to LÃ¤nge(A) do
//						2      einzusortierender_wert â†� A[i]
//						3      j â†� i
//						4      while j > 1 and A[j-1] > einzusortierender_wert do
//						5           A[j] â†� A[j - 1]
//						6           j â†� j âˆ’ 1
//						7      A[j] â†� einzusortierender_wert
			}
			unsortiert.remove(unsortiert.get(0));
		return unsortiert;
		  
	  }


  /**
   * All-rounder: Determine all movies in which the director stars as an actor
   * (cast). Return the top ten matches sorted by decreasing IMDB rating.
   *
   * @param movies
   *          the list of movies which is to be queried
   * @return top ten movies and the director, sorted by decreasing IMDB rating
   */
  public List<Tuple<Movie, String>> queryAllRounder(List<Movie> movies) {
    // TODO Basic Query: insert code here
	  //Alle Filme suchen wo Direktor auch actor ist
	//wenn leer breche ab:
	if(movies ==  null) {System.out.println("Bitte Filme eingeben");return null;}

	  
	 int index=0;
	  List<Tuple<Movie, String>> T = new ArrayList<Tuple<Movie, String>>();
	  boolean ueberspringen = false;
	  index=index;
	   for(int m=0; m<movies.size(); m++){
		   for(int d=0; d<movies.get(m).getDirectorList().size(); d++){
			   if(ueberspringen){ueberspringen = false; break;}
			   for(int a=0; a<movies.get(m).getCastList().size(); a++){
				   if(ueberspringen)break;
				   String director = movies.get(m).getDirectorList().get(d).toString();
				   String actor = movies.get(m).getCastList().get(a).toString();
				   if(director.equals(actor)){
					   Tuple<Movie, String> movie_gefunden= new Tuple<Movie,String>(movies.get(m),movies.get(m).getRatingValue());
				    	T.add(movie_gefunden);
					   index++;
			    	ueberspringen = true;
			       }
			   }
		   }
	   }
	  //Filme sortieren
	  List<Tuple<Movie, String>> sort=insertionSort(T);
	  //return new ArrayList<>();
	  List<Tuple<Movie, String>> top10 = new ArrayList<Tuple<Movie,String>>();
	  for(int i=0; i<11;i++){
		  Tuple<Movie,String> Element= new Tuple<Movie,String>(first, second);
		  Element.first=sort.get(i).first;
		  Element.second=sort.get(i).second;
		  top10.add(Element);
	  }
	  top10.remove(top10.get(0));
	  return top10;
  }


  /**
   * Under the Radar: Determine the top ten US-American movies until (including)
   * 2015 that have made the biggest loss despite an IMDB score above
   * (excluding) 8.0, based on at least 1,000 votes. Here, loss is defined as
   * budget minus gross.
   *
   * @param movies
   *          the list of movies which is to be queried
   * @return top ten highest rated US-American movie until 2015, sorted by
   *         monetary loss, which is also returned
   */
  public List<Tuple<Movie, Long>> queryUnderTheRadar(List<Movie> movies) {
    // TODO Basic Query: insert code here
	  
	  
	  
	  
    return new ArrayList<>();
  }

  /**
   * The Pillars of Storytelling: Determine 
 movies that contain both
   * (sub-)strings "kill" and "love" in their lowercase description
   * (String.toLowerCase()). Sort the results by the number of appearances of
   * these strings and return the top ten matches.
   *
   * @param movies
   *          the list of movies which is to be queried
   * @return top ten movies, which have the words "kill" and "love" as part of
   *         their lowercase description, sorted by the number of appearances of
   *         these words, which is also returned.
   */
  public List<Tuple<Movie, Integer>> queryPillarsOfStorytelling(
      List<Movie> movies) {
    List<Movie> movie=new ArrayList<Movie>();
    
    int appearances[] = new int[movies.size()];
	for(int i=0; i<appearances.length;i++){appearances[i]=0;}
	  
	for(int i=0;i<movies.size();i++)
	{
		  String description_lower_case = movies.get(i).getDescription().toLowerCase();
		  if(description_lower_case.contains("kill") && description_lower_case.contains("love"))
		  {
			  appearances[i]++;
			  Movie Element =new Movie();
			  Element=movies.get(i);
			  movie.add(Element);
		  }
	}
	
	  
	int max_value=Integer.MIN_VALUE;
	int max_indeze = Integer.MIN_VALUE;
	for(int i=0; i<10; i++){
	   //ermittle das max	
       for(int j=0;j<movies.size();j++){
	      if(appearances[j] > max_value){
			max_indeze = j;
		  }
       }
	  //gebe den film mit meisten vorkommen zurueck
	  Movie Element =new Movie();
	  Element=movies.get(max_indeze);
	  movie.add(Element);
	  //setze max auf 0
	  appearances[max_indeze] = 0;
	  //setze min zurueck
	  max_value=Integer.MIN_VALUE;
	  max_indeze = Integer.MIN_VALUE;
	}
	  
	  
    return new ArrayList<>();
  }

  /**
   * The Red Planet: Determine all movies of the Sci-Fi genre that mention
   * "Mars" in their description (case-aware!). List all found movies in
   * ascending order of publication (year).
   *
   * @param movies
   *          the list of movies which is to be queried
   * @return list of Sci-Fi movies involving Mars in ascending order of
   *         publication.
   */
  public List<Movie> queryRedPlanet(List<Movie> movies) {
	    // TODO Basic Query: insert code here
		  
		  //%%
		  
		  List<Movie> movie=new ArrayList<Movie>();
		  
		  for(int i=0;i<movies.size();i++)
		  {
			  for(int j=0;j<movies.get(i).getGenreList().size();j++)
			  {
				  if(movies.get(i).getGenreList().get(j).startsWith("Sci-Fi")&&movies.get(i).getDescription().contains("Mars"))
				  {
					  Movie Element =new Movie();
					  Element=movies.get(i);
					  movie.add(Element);
				  }
			  }
		  }
		  
		  List<Movie> sort=insertionSort3(movie);
		  //return new ArrayList<>();
		  List<Movie> top10 = new ArrayList<Movie>();
		  for(int i=0; i<11;i++){
			  if(i==sort.size()){
				  break;
			  }
			  Movie Element= new Movie();
			  Element=sort.get(i);
			  top10.add(Element);
		  }
		  return top10;
		  
		  
	  }

  /**
   * Colossal Failure: Determine all US-American movies with a duration beyond 2
   * hours, a budget beyond 1 million and an IMDB rating below 5.0. Sort results
   * by ascending IMDB rating.
   *
   * @param movies
   *          the list of movies which is to be queried
   * @return list of US-American movies with high duration, large budgets and a
   *         bad IMDB rating, sorted by ascending IMDB rating
   */
  public List<Movie> queryColossalFailure(List<Movie> movies) {
//    // TODO Basic Query: insert code heremai
//	  List<Movie> movie=new ArrayList<Movie>();
//	  double appearances[] = new double[movies.size()];
//	  for(int i=0; i<appearances.length;i++){appearances[i]=11;}
//	  
//	  for(int i=0;i<movies.size();i++)
//	  {
////		  if(i>990)System.out.println(movies.get(i).getDescription());
//		  String duration_str = movies.get(i).getDuration();
//		  int duration_int = Integer.parseInt(duration_str.substring(0,1))*60 + Integer.parseInt(duration_str.substring(duration_str.indexOf(" "),duration_str.indexOf("min")-2)); 
//		  String budget_str = movies.get(i).getBudget();
//		  int budget_int = Integer.parseInt(budget_str.substring(1,budget_str.length()-1));
//		  double rating = Double.parseDouble(movies.get(i).getRatingValue());
//		  
//			  if(duration_int > 120 && budget_int > 1000000 && rating > 5.0)
//			  {
//				  appearances[i] = rating;
//			  }
//	  }
//	  
//	  int min_indeze=Integer.MAX_VALUE;
//	  double min_value = Integer.MAX_VALUE;
//		for(int i=0; i<movies.size(); i++){
//		   //ermittle das min	
//	       for(int j=0;j<movies.size();j++){
//		      if(appearances[j] < min_value){
//				min_value = appearances[j];
//				min_indeze = j;
//			  }
//	       }
//	      if(appearances[min_indeze] >= 11)break; //hoere auf wenn keine Filme die Kritierien mehr erfuellen
//		  //gebe den film mit meisten vorkommen zurueck
//		  Movie Element =new Movie();
//		  Element=movies.get(min_indeze);
//		  movie.add(Element);
//		  //setze max auf 0
//		  appearances[min_indeze] =Integer.MAX_VALUE;
//		  //setze min zurueck
//		  min_indeze=Integer.MAX_VALUE;
//		  min_value = Integer.MAX_VALUE;
//		}
//	  
	  
    return new ArrayList<>();
  }

  /**
   * Uncreative Writers: Determine the 10 most frequent character names of all
   * times ordered by frequency of occurrence. Filter any lowercase names
   * containing substrings "himself", "doctor", and "herself" from the result.
   *
   * @param movies
   *          the list of movies which is to be queried
   * @return the top 10 character names and their frequency of occurrence;
   *         sorted in decreasing order of frequency
   */


  public List<Tuple<String, Integer>> queryUncreativeWriters(List<Movie> movies) {
     // TODO Impossibly Hard Query: insert code here
//       ArrayList<String> character_string = new ArrayList<String>();
//       ArrayList<Integer> character_indeze = new ArrayList<Integer>();
//          List<Movie> movie=new ArrayList<Movie>();
//
//       boolean einzigartig=false;
//
//       for(int i=0;i<movies.size();i++)
//       {
//           einzigartig = true;
//           for(int k=0; k<movies.get(i).getCharacterList().size();k++){
//               for(int j=0;j<character_string.size();j++){
//                 if(movies.get(i).getCharacterList().get(k).equals(character_string.get(j)))
//                 {
//                      einzigartig=false;
//                      character_indeze.set(j,j+1);
//                 }
//               }
//               String name = movies.get(i).getCharacterList().get(k);
//               if(einzigartig && !name.equals("himself") && !name.equals("herself") && !name.equals("doctor")){
//                   character_string.add(movies.get(i).getCharacterList().get(k));
//                   character_indeze.add(0);
//               }
//           }
//       }
//       
//       int max_indeze=Integer.MIN_VALUE;
//       int max_value=Integer.MIN_VALUE;
//         for(int i=0; i<movies.size(); i++){
//            //ermittle das max    
//            for(int j=0;j<character_indeze.size();j++){
//               if(character_indeze.get(j) > max_indeze){
//                 max_value = character_indeze.get(j);
//                 max_indeze = j;
//               }
//            }
//           if(max_value == Integer.MIN_VALUE)break; //hoere auf wenn keine Filme die Kritierien mehr erfuellen
//           //gebe den film mit meisten vorkommen zurueck
//           Movie Element =new Movie();
//           Element=movies.get(max_indeze);
//           movie.add(Element);
//           //setze max auf 0
//           character_indeze.set(max_indeze,Integer.MAX_VALUE);
//           //setze min zueruck
//           max_indeze=Integer.MAX_VALUE;
//           max_indeze=Integer.MAX_VALUE;
//         }
       
     return new ArrayList<>();
   }



  /**
   * Workhorse: Provide a ranked list of the top ten most active actors (i.e.
   * starred in most movies) and the number of movies they played a role in.
   *
   * @param movies
   *          the list of movies which is to be queried
   * @return the top ten actors and the number of movies they had a role in,
   *         sorted by the latter.
   */
  public List<Tuple<String, Integer>> queryWorkHorse(List<Movie> movies)
  {
    // TODO Impossibly Hard Query: insert code here
	  

      //TODO erstelle Liste mit allen Schauspielern

	  String f=null;
      int s=0;
      int test1=0;
        int maxindex=1;
      List<Tuple<String,Integer>> arr2=new ArrayList<Tuple<String,Integer>>();
       for(int m=0; m<movies.size(); m++)
       {
           for(int d=0; d<movies.get(m).getCastList().size(); d++)
           {
        	   test1++;
                	if(arr2.isEmpty())
                		{
                	
	                		
							Tuple<String,Integer> Element=new Tuple<String,Integer>(f,s);   
							Element.first=movies.get(m).getCastList().get(d);
							Element.second=1;
							arr2.add(Element);
							break;
						}
                	
                	
                	else{ 
                		
                		for(int i=0;i<=maxindex;i++)
                		{
                		//test
                		
                			//System.out.println(movies.get(m).getCastList().get(d)+ "==" + arr2.get(i).first+ " : "+ i);
                			if(i<maxindex&&movies.get(m).getCastList().get(d).startsWith(arr2.get(i).first))
					                    {
                							
					                       
					                        arr2.get(i).second++;
					                        break;
					                        
					                    }
                			
                			
                			if(i==maxindex)
                			{
                				Tuple<String,Integer> Element2=new Tuple<String,Integer>(f,s);   
    						Element2.first=movies.get(m).getCastList().get(d);
    						Element2.second=1;
    						arr2.add(Element2);
    						maxindex++;
    						break;
    						}
    						  
                	       
					     }
                		
                }

         }
      }
     
		
       //Filme sortieren
      List<Tuple<String,Integer>> sort= insertionSort2(arr2);
      //return new ArrayList<>();
      List<Tuple<String, Integer>> top10 = new ArrayList<Tuple<String,Integer>>();
	  for(int i=0; i<10;i++){
		 

		  
		Tuple<String,Integer> Element= new Tuple<String,Integer>(f,s);
		  Element.first=sort.get(i).first;
		  Element.second=sort.get(i).second;
		  top10.add(Element);
	  }

	  return top10;
  }
  /**
   * Must See: List the best-rated movie of each year starting from 1990 until
   * (including) 2010 with more than 10,000 ratings. Order the movies by
   * ascending year.
   *
   * @param movies
   *          the list of movies which is to be queried
   * @return best movies by year, starting from 1990 until 2010.
   */
  public List<Movie> queryMustSee(List<Movie> movies) {
    // TODO Impossibly Hard Query: insert code here
    return new ArrayList<>();
  }

  /**
   * Rotten Tomatoes: List the worst-rated movie of each year starting from 1990
   * till (including) 2010 with an IMDB score larger than 0. Order the movies by
   * increasing year.
   *
   * @param movies
   *          the list of movies which is to be queried
   * @return worst movies by year, starting from 1990 till (including) 2010.
   */
  public List<Movie> queryRottenTomatoes(List<Movie> movies) {
    // TODO Impossibly Hard Query: insert code here
    return new ArrayList<>();
  }

  /**
   * Magic Couples: Determine those couples that feature together in the most
   * movies. E.g., Adam Sandler and Allen Covert feature together in multiple
   * movies. Report the top ten pairs of actors, their number of movies and sort
   * the result by the number of movies.
   *
   * @param movies
   *          the list of movies which is to be queried
   * @return report the top 10 pairs of actors and the number of movies they
   *         feature together. Sort by number of movies.
   */
  public List<Tuple<Tuple<String, String>, Integer>> queryMagicCouple(
      List<Movie> movies) {
    // TODO Impossibly Hard Query: insert code here
    return new ArrayList<>();
  }


  public static void main(String argv[]) throws IOException {
    String moviesPath = "./data/movies/";

    if (argv.length == 1) {
      moviesPath = argv[0];
    } else if (argv.length != 0) {
      System.out.println("Call with: IMDBQueries.jar <moviesPath>");
      System.exit(0);
    }

    JSONObject M = new JSONObject();
    M.put("", MovieReader.readMoviesFrom(new File(moviesPath)));
    List<Movie> movies = MovieReader.readMoviesFrom(new File(moviesPath));
    
    
 

    System.out.println("All-rounder");
    {
      IMDBQueries queries = new IMDBQueries();
      long time = System.currentTimeMillis();
      List<Tuple<Movie, String>> result = queries.queryAllRounder(movies);
      System.out.println("Time:" + (System.currentTimeMillis() - time));

      if (result != null && !result.isEmpty() && result.size() == 10) {
        for (Tuple<Movie, String> tuple : result) {
          System.out.println("\t" + tuple.first.getRatingValue() + "\t"
              + tuple.first.getTitle() + "\t" + tuple.second);
        }
      } else {
        System.out.println("Error? Or not implemented?");
      }
    }
    System.out.println("");

    System.out.println("Under the radar");
    {
      IMDBQueries queries = new IMDBQueries();
      long time = System.currentTimeMillis();
      List<Tuple<Movie, Long>> result = queries.queryUnderTheRadar(movies);
      System.out.println("Time:" + (System.currentTimeMillis() - time));

      if (result != null && !result.isEmpty() && result.size() <= 10) {
        for (Tuple<Movie, Long> tuple : result) {
          System.out.println("\t" + tuple.first.getTitle() + "\t"
              + tuple.first.getRatingCount() + "\t"
              + tuple.first.getRatingValue() + "\t" + tuple.second);
        }
      } else {
        System.out.println("Error? Or not implemented?");
      }
    }
    System.out.println("");

    System.out.println("The pillars of storytelling");
    {
      IMDBQueries queries = new IMDBQueries();
      long time = System.currentTimeMillis();
      List<Tuple<Movie, Integer>> result = queries
          .queryPillarsOfStorytelling(movies);
      System.out.println("Time:" + (System.currentTimeMillis() - time));

      if (result != null && !result.isEmpty() && result.size() <= 10) {
        for (Tuple<Movie, Integer> tuple : result) {
          System.out.println("\t" + tuple.first.getTitle() + "\t"
              + tuple.second);
        }
      } else {
        System.out.println("Error? Or not implemented?");
      }
    }
    System.out.println("");

    System.out.println("The red planet");
    {
      IMDBQueries queries = new IMDBQueries();
      long time = System.currentTimeMillis();
      List<Movie> result = queries.queryRedPlanet(movies);
      System.out.println("Time:" + (System.currentTimeMillis() - time));

      if (result != null && !result.isEmpty()) {
        for (Movie movie : result) {
          System.out.println("\t" + movie.getTitle());
        }
      } else {
        System.out.println("Error? Or not implemented?");
      }
    }
    System.out.println("");

    System.out.println("ColossalFailure");
    {
      IMDBQueries queries = new IMDBQueries();
      long time = System.currentTimeMillis();
      List<Movie> result = queries.queryColossalFailure(movies);
      System.out.println("Time:" + (System.currentTimeMillis() - time));

      if (result != null && !result.isEmpty()) {
        for (Movie movie : result) {
          System.out.println("\t" + movie.getTitle() + "\t"
              + movie.getRatingValue());
        }
      } else {
        System.out.println("Error? Or not implemented?");
      }
    }
    System.out.println("");

    System.out.println("Uncreative writers");
    {
      IMDBQueries queries = new IMDBQueries();
      long time = System.currentTimeMillis();
      List<Tuple<String, Integer>> result = queries
          .queryUncreativeWriters(movies);
      System.out.println("Time:" + (System.currentTimeMillis() - time));

      if (result != null && !result.isEmpty() && result.size() <= 10) {
        for (Tuple<String, Integer> tuple : result) {
          System.out.println("\t" + tuple.first + "\t" + tuple.second);
        }
      } else {
        System.out.println("Error? Or not implemented?");
      }
    }
    System.out.println("");

    System.out.println("Workhorse");
    {
      IMDBQueries queries = new IMDBQueries();
      long time = System.currentTimeMillis();
      List<Tuple<String, Integer>> result = queries.queryWorkHorse(movies);
      System.out.println("Time:" + (System.currentTimeMillis() - time));

      if (result != null && !result.isEmpty() && result.size() <= 10) {
        for (Tuple<String, Integer> actor : result) {
          System.out.println("\t" + actor.first + "\t" + actor.second);
        }
      } else {
        System.out.println("Error? Or not implemented?");
      }
    }
    System.out.println("");

    System.out.println("Must see");
    {
      IMDBQueries queries = new IMDBQueries();
      long time = System.currentTimeMillis();
      List<Movie> result = queries.queryMustSee(movies);
      System.out.println("Time:" + (System.currentTimeMillis() - time));

      if (result != null && !result.isEmpty() && !result.isEmpty()) {
        for (Movie m : result) {
          System.out.println("\t" + m.getYear() + "\t" + m.getRatingValue()
              + "\t" + m.getTitle());
        }
      } else {
        System.out.println("Error? Or not implemented?");
      }
    }
    System.out.println("");

    System.out.println("Rotten tomatoes");
    {
      IMDBQueries queries = new IMDBQueries();
      long time = System.currentTimeMillis();
      List<Movie> result = queries.queryRottenTomatoes(movies);
      System.out.println("Time:" + (System.currentTimeMillis() - time));

      if (result != null && !result.isEmpty() && !result.isEmpty()) {
        for (Movie m : result) {
          System.out.println("\t" + m.getYear() + "\t" + m.getRatingValue()
              + "\t" + m.getTitle());
        }
      } else {
        System.out.println("Error? Or not implemented?");
      }
    }
    System.out.println("");

    System.out.println("Magic Couples");
    {
      IMDBQueries queries = new IMDBQueries();
      long time = System.currentTimeMillis();
      List<Tuple<Tuple<String, String>, Integer>> result = queries
          .queryMagicCouple(movies);
      System.out.println("Time:" + (System.currentTimeMillis() - time));

      if (result != null && !result.isEmpty()) {
        for (Tuple<Tuple<String, String>, Integer> tuple : result) {
          System.out.println("\t" + tuple.first.first + ":"
              + tuple.first.second + "\t" + tuple.second);
        }
      } else {
        System.out.println("Error? Or not implemented?");
      }
      System.out.println("");

    }
  }
}