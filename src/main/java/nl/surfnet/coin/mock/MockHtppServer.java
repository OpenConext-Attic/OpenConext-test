/**
 * Copyright 2010
 */
package nl.surfnet.coin.mock;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.handler.AbstractHandler;

/**
 * Very simple {@link Server} to mock http traffic
 * 
 */
public class MockHtppServer {
  private Server server;
  private MockHandler handler;

  /**
   * Construct MockHtppServer
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
    this.server.addHandler(handler);
  }

  /**
   * Create a handler for the server
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
