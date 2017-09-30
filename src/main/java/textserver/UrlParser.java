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

import java.net.URI;
import java.util.ArrayList;

public class UrlParser {

  // This is a static-only class - no UrlParser objects should be instantiated
  private UrlParser() {}

  public final static ArrayList<URI> parseUris(String body) {
    if (body.isEmpty()) {
      return new ArrayList<URI>(0);
    }
    else {
      ArrayList<URI> uris = new ArrayList<URI>();
      for(String uriAsString : body.split(",")) {
          uris.add(URI.create(uriAsString));
      }
      return uris;
    }
  }
}