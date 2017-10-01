package textserver;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.InterruptedException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextClient {

  /**
   * Given a URL, connects and retrieves (GETs) content, and returns a String
   * representation of the content.
   * @param url a URL to retrieve content from 
   * @return String the string content at the given URL
   */
  public String retrieveContentAtURL(URL url) throws IOException {
      // to make this testable, we should take an interface as an argument that
      // we could then build a stub interface for in testing. As-is, we can
      // really only test this at the integration level. It also implies this
      // method is doing too much - it could/should be decomposed.
      URLConnection connection = url.openConnection();
      InputStream response = connection.getInputStream();
      BufferedReader reader = new BufferedReader(new InputStreamReader(response));
      Stream<String> stream = reader.lines();
      String text = stream.parallel().collect(Collectors.joining());
      return text;
  }

  /**
   * Given an ArrayList of URLs, retrieve the content from these URLs
   * concurrently, using concurrency-safe types. Concatenates all retrieved
   * content into a string.
   * @param urls an ArrayList<URL> of URLs to retrieve content from
   * @return String the joined string content from retrieving the URLs
   */
  public String retrieveContentAtURLs(ArrayList<URL> urls) {
    ExecutorService executorService = Executors.newFixedThreadPool(urls.size());

    ArrayList<CompletableFuture<String>> tasks = new ArrayList<CompletableFuture<String>>(urls.size());

    for(URL url : urls) {
      CompletableFuture<String> completableFuture = new CompletableFuture<String>();
      executorService.submit(() -> {
        try {
          completableFuture.complete(retrieveContentAtURL(url));
        }
        catch(IOException e) {
          e.printStackTrace();
        }
      });
      tasks.add(completableFuture);
    }

    // block until every CompletableFuture has completed unfortunately,
    // CompletableFuture doesn't support collections (at least not that I know
    // of) so we have to convert o an array
    CompletableFuture.allOf(tasks.toArray(new CompletableFuture[tasks.size()])).join();

    String results = "";
    for (CompletableFuture<String> task : tasks) {
      try {
        results += task.get();
      }
      catch(CancellationException | InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    }
    return results;
  }
}
