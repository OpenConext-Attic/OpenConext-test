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

package nl.surfnet.coin.mock;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.core.io.Resource;

/**
 * Tests subclassing {@link AbstractMockHttpServerTest} can communicate with a
 * mock Http endpoint that behavious like a real server.
 * 
 * The idea behind this class is that http responses can be tested (regardless
 * of the input request).
 * 
 * 
 * 
 */
public abstract class AbstractMockHttpServerTest {
  /*
   * The Server that handles the Soap calls
   */
  private static MockHtppServer server = new MockHtppServer(8088);

  @BeforeClass
  public static void startServer() {
    server.startServer();
  }

  @AfterClass
  public static void stopServer() {
    server.stopServer();
  }

  /**
   * Set the expected response on the Handler
   * 
   * @param responseResource
   *          the resource of the file containing the xml response
   */
  protected void setResponseResource(Resource responseResource) {
    server.getHandler().setResponseResource(responseResource);
  }
  
  /**
   * Set the expected response on the Handler
   * 
   * @param responseResource
   *          the resource of the file containing the xml response
   */
  protected void setResponseResource(Resource[] responseResource) {
    server.getHandler().setResponseResource(responseResource);
  }

}
