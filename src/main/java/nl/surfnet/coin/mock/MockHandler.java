/**
 * Copyright 2010
 */
package nl.surfnet.coin.mock;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.AbstractHandler;
import org.springframework.core.io.Resource;


/**
 * 
 * {@link MockHandler} is a very simple {@link AbstractHandler} that returns content based on the
 * configuration in the test classes. This class is NOT thread safe.
 * 
 * @author oharsta
 * 
 */
public class MockHandler extends AbstractHandler {

    /*
     * The resource of the (XML, JSON etc) file that should be returned. It is accessible through
     * the server instance and must be accessible from the classpath
     */
    private Resource responseResource;

    /**
     * Constructor
     * 
     * @param server the Server
     */
    public MockHandler(Server server) {
        setServer(server);
    }

    /*
     * Write the contents of the provided file to the response
     */
    private void respond(HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();

        InputStream inputStream = responseResource.getInputStream();

        IOUtils.copy(inputStream, outputStream);
        outputStream.flush();
    }

    /**
     * Set the Resource which contents will be returned as a SOAP response on the next call
     * 
     * @param responseResource the responseResource
     */
    public void setResponseResource(Resource responseResource) {
        this.responseResource = responseResource;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.mortbay.jetty.Handler#handle(java.lang.String,
     * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, int)
     */
    public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch) throws IOException, ServletException {
        invariant();
        addContentHeader(response);
        respond(response);
    }

    /**
     * @param response
     */
    private void addContentHeader(HttpServletResponse response) {
        String contentType;
        String description = responseResource.getDescription();
        // poor man's solution, but it works for most Resource implementation
        if (description.contains("json")) {
            contentType = "application/json";
        } else if (description.contains("xml")) {
            contentType = "text/xml";
        } else {
            contentType = "text/html";
        }
        response.addHeader("Content-Type", contentType);

    }

    /*
     * Check the response to render back
     */
    private void invariant() {
        if (this.responseResource == null) {
            throw new RuntimeException("No responseResource set");
        }
    }
}
