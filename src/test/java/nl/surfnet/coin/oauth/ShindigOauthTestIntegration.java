/*
 * Copyright 2011 SURFnet bv, The Netherlands
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nl.surfnet.coin.oauth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
public class ShindigOauthTestIntegration {

  private static final int _EXPECTED_GROUP_COUNT = 5;
  // private static final String BASE_URL =
  // "https://gadgets.dev.coin.surf.net/social/";
  // https://engine-internal.dev.surfconext.nl/social/people/urn%3Acollab%3Aperson%3Atest.surfguest.nl%3Asmibuildings?fields=all
  //private static final String BASE_URL = "https://os.test.surfconext.nl/social/";

  //  private static final String BASE_URL = "http://localhost:8080/vo/someVo/social/";
  
  private static final String BASE_URL = "http://localhost:8080/vo/devWorldData/social/";
  /*
   * Proteon key-secret
   */
  // private final static String CONSUMER_KEY =
  // "http://conext.proteon.nl/.SomeGadget";
  // private final static String CONSUMER_SECRET =
  // "4FA60498-9204-4D6A-929A-8DC70822F9CD";
  private static final String restTemplate = "people/{guid}/{selector}/{pid}";
  // private final static String CONSUMER_KEY =
  // "http://coin.edia.nl.SomeGadget";
  private final static String CONSUMER_KEY = "https://teams.test.surfconext.nl/teams/teams.xml";
  // private final static String CONSUMER_KEY = "foodle";

  //private final static String CONSUMER_SECRET = "mysecret";
   private final static String CONSUMER_SECRET = "2e/UbBTlwdTRoF5jbg5NDexU";
  // private final static String OPEN_SOCIAL_ID =
  // "urn:collab:person:test.surfguest.nl:oharsta";

  private final static String OPEN_SOCIAL_ID = "urn:collab:person:test.surfguest.nl:peterclijsters";

  @Test
  public void testOAuthClient() throws RequestException, IOException {
    /*
     * We use rest, but rpc also works. Just use the no-argument ShindigProvider
     * constructor
     */
    Provider provider = new ShindigProvider(true);

    provider.setRestEndpoint(BASE_URL + "rest/");
    //provider.setRpcEndpoint(BASE_URL + "rpc/");
    provider.setVersion("0.9");

    AuthScheme scheme = new OAuth2LeggedScheme(CONSUMER_KEY, CONSUMER_SECRET,
        OPEN_SOCIAL_ID);

    Client client = new Client(provider, scheme);

    Request request = PeopleService.getUser(OPEN_SOCIAL_ID);

//    request = GroupsService.getGroups(OPEN_SOCIAL_ID);
//    Response response = client.send(request);
//
//    List<Group> groups = response.getEntries();
//    int size = groups.size();
//    assertEquals(4, size);

    //    for (Group group : groups) {
//      request = new Request(restTemplate, "people.get", "GET");
//      request.setModelClass(Person.class);
//      request.setGuid(OPEN_SOCIAL_ID);
//      request.setSelector(group.getId());
//      List<Person> persons = client.send(request).getEntries();
//      System.out.println("Group " + group.getId() + " members:"
//          + persons.size());
//    }
    //
    // Activity activity = new Activity();
    // activity.setBody("body_test");
    // activity.setBodyId("body_id");
    // activity.setField("externalId", "groupId");
    // activity.setField("url", "abcdef");
    // activity.setTitle("title");
    // activity.setTitleId("titleId");
    // request = ActivitiesService.createActivity(activity);
    // response = client.send(request);
    // List<Model> entries = response.getEntries();
    // // assertEquals(1,entries.size());
    //
     request = new Request(restTemplate, "people.get", "GET");
     request.setModelClass(Person.class);
     request.setGuid(OPEN_SOCIAL_ID);
     // request.setSelector("nl:surfnet:diensten:a-team");
 //    request.setSelector("nl:surfnet:diensten:a-team");
     List<Person> persons = client.send(request).getEntries();
     assertTrue(persons.size() > 0);
     Person personFromGroup = persons.get(0);
     assertTrue(personFromGroup.getEmail().contains("@"));
     System.out.println(personFromGroup);
  }

}
