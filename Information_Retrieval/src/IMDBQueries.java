import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

@SuppressWarnings("static-method")
public class IMDBQueries {

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
  
  public List<Tuple> insertionSort(List<Tuple> sortieren){
	  ArrayList<Tuple> sort=new ArrayList<Tuple>();
	  Tuple T;
	  
		for (int i = 0; i < sortieren.size(); i++) {
			T = sortieren.get(i);
			int j = i;
			while(j>0 && Double.parseDouble((String) sortieren.get(j-1).second) > Double.parseDouble((String) T.second)){
				sortieren.set(j, sortieren.get(j-1));		
						//sortieren.get(j-1).second; 
				j--;
			}
			sortieren.set(j, T);
					
//					INSERTIONSORT(A)
//					1 for i â†� 2 to LÃ¤nge(A) do
//					2      einzusortierender_wert â†� A[i]
//					3      j â†� i
//					4      while j > 1 and A[j-1] > einzusortierender_wert do
//					5           A[j] â†� A[j - 1]
//					6           j â†� j âˆ’ 1
//					7      A[j] â†� einzusortierender_wert
					
			
		}
	
	return sort;
	  
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
      ArrayList<Tuple> arr=new ArrayList<Tuple>();
	  boolean ueberspringen = false;
	   for(int m=0; m<movies.size(); m++){
		   for(int d=0; d<movies.get(m).getDirectorList().size(); d++){
			   if(ueberspringen){ueberspringen = false; break;}
			   for(int a=0; a<movies.get(m).getCastList().size(); a++){
				   if(ueberspringen)break;
				   String director = movies.get(m).getDirectorList().get(d).toString();
				   String actor = movies.get(m).getCastList().get(a).toString();
				   if(director.equals(actor)){
			    	System.out.println(m);
				    	Tuple T = null;
				    	T.first = movies.get(m);
				    	T.second = movies.get(m).getRatingValue();
				    	arr.add(T);
			    	ueberspringen = true;
			       }
			   }
		   }
	   }
	   //Filme sortieren
	  ArrayList<Tuple> sort=(ArrayList<Tuple>) insertionSort(arr);
	  //return new ArrayList<>();
	  List<Tuple<Movie, String>> top10 = null;
	  for(int i=0; i<10;i++){
		  top10.set(i,sort.get(i)); 
	  }
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
   * The Pillars of Storytelling: Determine all movies that contain both
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
    // TODO Basic Query: insert code here
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
	  
	  ArrayList<Tuple<Movie,Integer>> Planet=new ArrayList<Tuple<Movie, Integer>>();
	  for(int i=0;i<movies.size();i++)
	  {
		  for(int j=0;j<movies.get(i).getGenreList().size();j++)
		  { 
			  if(movies.get(i).getGenreList().get(j)=="Sci-Fi")
			  {
				  if(movies.get(i).getDescription().contains("Mars"))
				  {
					  Tuple<Movie, Integer> t=null;
					  t.first=movies.get(i);
					  t.second=Integer.parseInt(movies.get(i).getYear());
					  Planet.add(t);
					  
				  }
			  }
		  }
		  
		 
	  }
	return movies;
	  
	  
    
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
    // TODO Basic Query: insert code here
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
  public List<Tuple<String, Integer>> queryWorkHorse(List<Movie> movies) {
    // TODO Impossibly Hard Query: insert code here
	  
	  
	  
	  //TODO erstelle Liste mit allen Schauspielern
	  
 	   int maxindex=0;
	  ArrayList<Tuple> arr2=new ArrayList<Tuple>();
	  boolean ueberspringen = false;
	   for(int m=0; m<movies.size(); m++){
		   for(int d=0; d<movies.get(m).getCastList().size(); d++){
				for(int i=0;i<=maxindex;i++)
				{
					if(arr2.get(i).first==movies.get(m).getCastList().get(d))
					{
						int tmp=((int)arr2.get(i).second+1);
						arr2.get(i).second=(char)tmp;
					}
					else
					{
						if(arr2.get(i).first==null)
						{
							arr2.get(i).first=movies.get(m).getCastList().get(d);
							maxindex++;
						}
					}
				}
			        
			   }
		   }
	   
	   //Filme sortieren
	  ArrayList<Tuple> sort=(ArrayList<Tuple>) insertionSort(arr2);
	  //return new ArrayList<>();
	  List<Tuple<String, Integer>> top10 = null;
	  for(int i=0; i<10;i++){
		  top10.add(sort.get(i)); 
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