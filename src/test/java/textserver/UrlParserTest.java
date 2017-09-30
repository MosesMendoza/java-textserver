package textserver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.util.ArrayList;
import java.net.MalformedURLException;
import java.net.URL;
import textserver.UrlParser;

public class UrlParserTest {
  @Test
  public void shouldParseTextAndReturnurls() throws MalformedURLException {
    String urlsAsString = "http://foo.bar.baz,http://baz.quux.qux";
    URL url0 = new URL("http://foo.bar.baz");
    URL url1 = new URL("http://baz.quux.qux");

    ArrayList<URL> urls = UrlParser.parseUrls(urlsAsString);
    assertEquals(urls.get(0), url0);
    assertEquals(urls.get(1), url1);
  }

  public void shouldReturnEmptyListGivenEmptyString(){
    String emptyUri = "";
    ArrayList<URL> urls = UrlParser.parseUrls(emptyUri);
    assertTrue(urls.isEmpty());
  }
}
