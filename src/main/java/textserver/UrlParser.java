/* 

  application
    receive a POST  with body of comma separated URLS
    reply with correct text

  controller
    take request, initialize handler, hand off to handler, return response?

  url constructor
    construct a list of URLs from this body 

  client class
    given urls, retrieve the text at each URL (parallel)

  text manipulator class
    join the text
    deduplicate the text
    remove numbers from text
  
  application
  reply with text

*/ 

// A class that only contains a single static method for parsing of URLs
// supplied to our application in the body of a POST
package textserver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class UrlParser {

  // This is a static-only class - no UrlParser objects should be instantiated
  private UrlParser() {}

  /**
   * Returns an ArrayList of URL objects corresponding to a supplied comma-separated string of URLs
   * @param body String containing a comma-separated list of URLs
   * @return ArrayList<URL> list of URL objects corresponding to supplied URLs in String body
   */
  public final static ArrayList<URL> parseUrls(String body) {
    if (body.isEmpty()) {
      return new ArrayList<URL>(0);
    }
    else {
      ArrayList<URL> urls = new ArrayList<URL>();
      for(String urlAsString : body.split(",")) {
          try {
            urls.add(new URL(urlAsString));
          }
          catch(MalformedURLException e) {
            e.printStackTrace();
          }
      }
      return urls;
    }
  }
}