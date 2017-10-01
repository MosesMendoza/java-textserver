package textserver;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
// An http client class to retrieve text from a given set of URLS

public class TextClient {

  /**
   * Given a URL, connects and retrieves (GETs) content, and returns a String
   * representation of the content.
   * @param url a URL to retrieve content from 
   * @return String the string content at the given URL
   */
  public String retrieveContentAtURL(URL url) throws IOException {
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
   return "foo"; 
  }
}
