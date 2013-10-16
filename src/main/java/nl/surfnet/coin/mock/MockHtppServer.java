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

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.bio.SocketConnector;

/**
 * Very simple {@link Server} to mock http traffic
 *
 * @deprecated Please use httpclient's LocalTestServer which has exactly this behaviour. (maven classifier: 'tests')
 */
@Deprecated
public class MockHtppServer {
  private Server server;
  private MockHandler handler;

  /**
   * Construct MockHtppServer
   * @param port number it listens to
   */
  public MockHtppServer(int port) {
    super();
    initServer(port);

  }

  /*
   * Initialise the server
   */
  private void initServer(int port) {
    this.server = new Server();
    SocketConnector connector = new SocketConnector();
    connector.setPort(port);
    this.server.addConnector(connector);
    this.handler = createHandler(server);
//
//      HandlerCollection handlers = new HandlerCollection();
//      handlers.setHandlers(new Handler[]{handler});

    this.server.setHandler(handler);
  }

  /**
   * Create a handler for the server
   * @param server {@link Server}
   * @return the {@link MockHandler}
   */
  protected MockHandler createHandler(Server server) {
    return new MockHandler(server);
  }

  /**
   * Start the server in a non-blocking mode. The separate thread will be killed
   * when the test class finishes
   */
  public void startServer() {
    doStartServer(true);
  }

  /**
   * Start the server in a blocking mode. The method does not return untill the
   * thread is killed
   */
  public void startServerSync() {
    doStartServer(false);
  }

  /**
   * Start the server in a non-blocking mode. The separate thread will be killed
   * when the test class finishes
   * @param async if {@literal true} it will start with a short delay
   */
  public void doStartServer(boolean async) {
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          server.start();
          // just wait until the server gets killed
          System.in.read();
        } catch (Exception e) {
          stopServer();
          throw new RuntimeException(e);
        }
      }
    });
    if (async) {
      thread.start();
      // give the server some time to start
      sleep(150);
    } else {
      thread.run();
    }
  }

  /**
   * Stop the server
   */
  public void stopServer() {
    try {
      this.server.stop();
      this.server.join();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * @return the handler
   */
  public MockHandler getHandler() {
    return this.handler;
  }

  /*
   * Sleep for some time
   */
  private void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      stopServer();
      throw new RuntimeException(e);
    }
  }

  /**
   * @return the server
   */
  public Server getServer() {
    return this.server;
  }
}
