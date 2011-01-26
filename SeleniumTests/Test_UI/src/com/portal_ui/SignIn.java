package com.portal_ui;

import java.io.File;

public class SignIn extends BaseTest {

    public void testSignIn() throws Exception {
        selenium.click("//div[@id='TabContainer']/h1");
        selenium.click("ButtonShowAll");
        // Sign in with SURFguest test account
        selenium.click("//img[contains(@src,'https://www.surfguest.nl/img/surfnet_logo.gif')]");
        selenium.click("LoginSubmit");
        selenium.waitForPageToLoad("90000");
        File f = new File("config" + File.separator + "config.txt");
        String username = Utils.getConfigEntry(f, "username");
        selenium.type("username", username);
        selenium.type("password", Utils.getConfigEntry(f, "password"));
        waitForElement("//input[@value='   Login   ']");
        selenium.click("//input[@value='   Login   ']");

        // check if user consent display is shown
        if (selenium.isTextPresent("Net-ID")) {
            waitForElement("accept_terms_button");
            selenium.click("accept_terms_button");
            waitForElement("AddTab");
            verifyTrue(selenium.isTextPresent("Other tabs"));
            selenium.click("//li[@id='UserName']/strong");
            verifyTrue(selenium.isTextPresent("exact:urn:collab:person:test.surfguest.nl:"
                    + username));
            selenium.click("PageContainer");
            selenium.click("link=Sign out");
            selenium.waitForPageToLoad("30000");
            verifyTrue(selenium.isTextPresent("Local Logout"));
        } else {
            waitForElement("AddTab");
            verifyTrue(selenium.isTextPresent("Other tabs"));
            selenium.click("//li[@id='UserName']/strong");
            // check if the username is right (same as sign in)
            verifyTrue(selenium.isTextPresent("exact:urn:collab:person:test.surfguest.nl:"
                    + username));
            selenium.click("PageContainer");
            selenium.click("link=Sign out");
            selenium.waitForPageToLoad("30000");
            // check if logout message is shown
            verifyTrue(selenium.isTextPresent("Local Logout"));
        }
    };
}
