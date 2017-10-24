package textserver;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.ArrayList;
import java.net.URL;
import java.io.IOException;

public class TextClientTest {
  @Test
  public void shouldRetrieveTextAtURL() {
    /* this is an integration-style test that actually calls out to a known live
    URL. This would ideally be refactored so we don't need to actually reach out
    to the internet for this test at a unit-level. */
    String expected_text = "“Naïvely, the café charged only £1 for a cachaça”.";
    String site_text;
    URL url;
    TextClient client = new TextClient();

    try {
      url = new URL("http://mackerron.com/text/test.utf8.txt");
      site_text = client.retrieveContentAtURL(url);
      assertEquals(site_text, expected_text);
    }
    catch(IOException e){
      e.printStackTrace();
    }
  }

  @Test
  public void shouldRetrieveTextAtURLs() {
    /* this is an integration-style test that actually calls out to a known live
    URL. This would ideally be refactored so we don't need to actually reach out
    to the internet for this test at a unit-level. */
    String expected_text = "“Naïvely, the café charged only £1 for a cachaça”.“Naïvely, the café charged only £1 for a cachaça”.";
    String site_text;
    URL firstUrl;
    URL secondUrl;
    TextClient client = new TextClient();

    try {
      firstUrl = new URL("http://mackerron.com/text/test.utf8.txt");
      secondUrl = new URL("http://mackerron.com/text/test.utf8.txt");
      ArrayList<URL> urls = new ArrayList<URL>(2);
      urls.add(firstUrl);
      urls.add(secondUrl);
      site_text = client.retrieveContentAtURLs(urls);
      assertEquals(site_text, expected_text);
    }
    catch(IOException e){
      e.printStackTrace();
    }
  }
}