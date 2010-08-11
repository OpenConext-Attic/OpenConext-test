/**
 * Copyright 2010
 */
package nl.surfnet.coin.mock;

import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.io.ByteArrayResource;


/**
 * 
 * {@link Test} for {@link AbstractMockSoapServerTest}
 * 
 * @author oharsta
 * 
 */
public class MockSoapServerTest extends AbstractMockHttpServerTest {

    private boolean securityHandlerHit;

    /**
     * Test the MockSoapServer. In real test cases there will be a framework like JAX-WS responsible
     * for making the SOAP calls.
     * 
     * @throws Exception
     */
    @Test
    public void testMockSoapHappyFlow() throws Exception {
        String httpResponse = "test123";
        super.setResponseResource(new ByteArrayResource(httpResponse.getBytes()));
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(new HttpGet("http://localhost:8088/testUrl"));
    
        InputStream is = response.getEntity().getContent();
        OutputStream output = new ByteArrayOutputStream();
        IOUtils.copy(is, output);
        Assert.assertEquals(httpResponse, output.toString());
    }

   
}
