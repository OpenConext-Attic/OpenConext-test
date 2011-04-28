package nl.surfnet.coin.oauth;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.opensocial.Client;
import org.opensocial.Request;
import org.opensocial.RequestException;
import org.opensocial.Response;
import org.opensocial.auth.AuthScheme;
import org.opensocial.auth.OAuth2LeggedScheme;
import org.opensocial.models.Activity;
import org.opensocial.models.Group;
import org.opensocial.models.Model;
import org.opensocial.models.Person;
import org.opensocial.providers.Provider;
import org.opensocial.providers.ShindigProvider;
import org.opensocial.services.ActivitiesService;
import org.opensocial.services.GroupsService;
import org.opensocial.services.PeopleService;

/**
 * {@link Test} for an actual call to coin shindig to test oauth. This is an
 * integration test (e.g. depends on the availability of coin shindig and
 * therefore does not run in the normal build (hence the TestIntegration
 * suffix).
 * 
 */
public class ShindigOauthTestproductionIntegration {

  private static final int _EXPECTED_GROUP_COUNT = 2;
  private static final String BASE_URL = "https://os.surfconext.nl/social/";
  /*
   * Proteon key-secret
   */
//   private final static String CONSUMER_KEY =
//   "http://conext.proteon.nl/.SomeGadget";
//   private final static String CONSUMER_SECRET =
//   "ap9ajn2mKuXCA";

  private final static String CONSUMER_KEY = "https://foodle.coin.surfnetlabs.nl/gadget";
  private final static String CONSUMER_SECRET = "6bbfd361-b488-418e-8def-d9990e9942ec";
  private final static String OPEN_SOCIAL_ID = "urn:collab:person:surfnet.nl:niels";

  @Test
  public void testOAuthClient() throws RequestException, IOException {
    /*
     * We use rest, but rpc also works. Just use the no-argument ShindigProvider
     * constructor
     */
    Provider provider = new ShindigProvider(true);

    provider.setRestEndpoint(BASE_URL + "rest/");
    provider.setRpcEndpoint(null);//BASE_URL + "rpc/");
    provider.setVersion("0.9");

    AuthScheme scheme = new OAuth2LeggedScheme(CONSUMER_KEY, CONSUMER_SECRET,
        OPEN_SOCIAL_ID);

    Client client = new Client(provider, scheme);

    Request request = PeopleService.getFriends();
    Response response = client.send(request);

    List<Person> friends = response.getEntries();
    assertEquals(1, friends.size());
    String id = friends.get(0).getId();
    assertEquals(OPEN_SOCIAL_ID, id);

    request = GroupsService.getGroups(OPEN_SOCIAL_ID);
    response = client.send(request);

    List<Group> groups = response.getEntries();
    int size = groups.size();
    assertEquals(_EXPECTED_GROUP_COUNT, size);

    Activity activity = new Activity();
    activity.setBody("body_test");
    activity.setBodyId("body_id");
    activity.setField("externalId", "groupId");
    activity.setField("url", "abcdef");
    activity.setTitle("title");
    activity.setTitleId("titleId");
    request = ActivitiesService.createActivity(activity);
    response = client.send(request);
    List<Model> entries = response.getEntries();
  //  assertEquals(1,entries.size());
  }

}
