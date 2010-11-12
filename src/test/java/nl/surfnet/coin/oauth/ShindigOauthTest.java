package nl.surfnet.coin.oauth;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import nl.surfnet.coin.mock.AbstractMockHttpServerTest;

import org.junit.Test;
import org.opensocial.Client;
import org.opensocial.Request;
import org.opensocial.RequestException;
import org.opensocial.Response;
import org.opensocial.auth.AuthScheme;
import org.opensocial.auth.OAuth2LeggedScheme;
import org.opensocial.models.Person;
import org.opensocial.providers.Provider;
import org.opensocial.providers.ShindigProvider;
import org.opensocial.services.PeopleService;
import org.springframework.core.io.ClassPathResource;

/**
 * {@link Test} for patch of the opensocial-java-client. In order to test
 * against an actual shindig container change the port (but not check anything
 * in else then 8088, because we want our junit test to be independent of
 * external dependencies)
 * 
 */
public class ShindigOauthTest extends AbstractMockHttpServerTest {

  private static final String PORT = "8088";
  private final static String consumerKey = "http://localhost:8080/samplecontainer/examples/SocialHelloWorld.xml";
  private final static String consumerSecret = "mysecret";
  private final static String openSocialId = "urn:collab:person:surfnet.nl:hansz"; 

  @Test
  public void testOAuthClient() throws RequestException, IOException {
    Provider provider = new ShindigProvider();

    provider.setRestEndpoint("http://localhost:" + PORT + "/social/rest/");
    provider.setRpcEndpoint("http://localhost:" + PORT + "/social/rpc/");
    provider.setVersion("0.9");

    AuthScheme scheme = new OAuth2LeggedScheme(consumerKey, consumerSecret,
        openSocialId);

    Client client = new Client(provider, scheme);

    super.setResponseResource(new ClassPathResource("persons.json"));

    Request request = PeopleService.getFriends();
    Response response = client.send(request);

    List<Person> friends = response.getEntries();
    assertEquals(1, friends.size());
    String id = friends.get(0).getId();
    assertEquals("john.doe", id);

  }

}
