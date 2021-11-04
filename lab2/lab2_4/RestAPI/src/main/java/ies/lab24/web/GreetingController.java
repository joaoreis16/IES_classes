package ies.lab24.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Welcome, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/api")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@GetMapping("/api/quote")
	public String randomQuotes() {
		ArrayList<String> quotes = getQuotes();

		Random rand = new Random();
		int quote_index = rand.nextInt(quotes.size());

		return "{"+'"'+"id"+'"'+": "+ counter.incrementAndGet() +", "+ '"'+"quote"+'"'+": "+'"'+ quotes.get(quote_index)+'"'+"}";	
	}

	@GetMapping("/api/shows")
	public String getShows() {
		HashMap<Integer, String> id_show = getShowId();

		String msg = "";
		for (Map.Entry<Integer, String> entry : id_show.entrySet()) {
			int id = entry.getKey();
			String movie = entry.getValue();

			msg += "{"+'"'+"id"+'"'+": "+ id +", "+ '"'+"movie"+'"'+": "+'"'+ movie +'"'+"}\n";

		}
		return msg;
	}

	@GetMapping("/api/quotes")
	public String getShowById(@RequestParam(value = "show", defaultValue = "1") int id) {
		HashMap<Integer, String> id_show = getShowId();
		String show = "";
		int film_id = 1;

		for (Map.Entry<Integer, String> entry : id_show.entrySet()) {
			if (id == entry.getKey()) {
				show = entry.getValue();
				film_id = entry.getKey();
				break;
			}
		}

		if (show.equals("")) return "[ERRO]: id não encontrado";

		HashMap<String, ArrayList<String>> movies = getMovies();
		for (Map.Entry<String, ArrayList<String>> entry : movies.entrySet()) {
			if (show.equals(entry.getKey())) {
				Random rand = new Random();
				int quote_index = rand.nextInt(entry.getValue().size());
				
				return "{"+'"'+"film_id"+'"'+": "+ film_id +", "+ '"'+"quote"+'"'+": "+'"'+ entry.getValue().get(quote_index)+'"'+"}";
			}
		}
		return "";
	}


	public HashMap<String, ArrayList<String>> getMovies() {
		HashMap<String, ArrayList<String>> movies = new HashMap<>();
		ArrayList<String> title = new ArrayList<>();

		movies.put("Gone With the Wind", new ArrayList<String>(Arrays.asList("Frankly, my dear, I don't give a damn.")));
		movies.put("Casablanca", new ArrayList<String>(Arrays.asList("Here's looking at you, kid", "Louis, I think this is the beginning of a beautiful friendship", "Play it, Sam. Play 'As Time Goes By.","Round up the usual suspects.","We'll always have Paris.")));
		movies.put("Sudden Impact", new ArrayList<String>(Arrays.asList("Go ahead, make my day.")));
		movies.put("Star Wars", new ArrayList<String>(Arrays.asList("May the Force be with you.", "Try not. Do or do not. There is no try.","In a dark place we find ourselves and a little more knowledge lights our way")));
		movies.put("The Terminator", new ArrayList<String>(Arrays.asList("I'll be back.")));
		movies.put("Shane", new ArrayList<String>(Arrays.asList("Shane. Shane. Come back!")));
		movies.put("Frankenstein", new ArrayList<String>(Arrays.asList("It's alive! It's alive!")));
		movies.put("Psycho", new ArrayList<String>(Arrays.asList("A boy's best friend is his mother.")));
		movies.put("Dracula", new ArrayList<String>(Arrays.asList("Listen to them. Children of the night. What music they make")));

		return movies;
	}

	public ArrayList<String> getQuotes() {
		HashMap<String, ArrayList<String>> movies = getMovies();
		ArrayList<String> quotes = new ArrayList<>();

		for (Map.Entry<String, ArrayList<String>> entry : movies.entrySet()) {
			quotes.addAll(entry.getValue());
		}
		return quotes;
	}

	public HashMap<Integer, String> getShowId() {
		HashMap<Integer, String> id_show = new HashMap<>();
		HashMap<String, ArrayList<String>> movies = getMovies();

		int id = 0;
		for (Map.Entry<String, ArrayList<String>> entry : movies.entrySet()) {
			String movie = entry.getKey();

			id_show.put(id, movie);
			id += 1;
		}

		return id_show;
	}
}