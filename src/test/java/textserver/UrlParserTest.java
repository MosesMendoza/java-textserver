package textserver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.util.ArrayList;
import java.net.URI;
import textserver.UrlParser;

public class UrlParserTest {
  @Test
  public void shouldParseTextAndReturnUris(){
    String urisAsString = "http://foo.bar.baz,http://baz.quux.qux";
    URI uri0 = URI.create("http://foo.bar.baz");
    URI uri1 = URI.create("http://baz.quux.qux");

    ArrayList<URI> uris = UrlParser.parseUris(urisAsString);
    assertEquals(uris.get(0), uri0);
    assertEquals(uris.get(1), uri1);
  }

  public void shouldReturnEmptyListGivenEmptyString(){
    String emptyUri = "";
    ArrayList<URI> uris = UrlParser.parseUris(emptyUri);
    assertTrue(uris.isEmpty());
  }
}
