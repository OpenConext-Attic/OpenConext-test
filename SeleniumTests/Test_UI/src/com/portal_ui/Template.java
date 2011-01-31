package com.portal_ui;

import java.io.File;

//Replace Template and testTemplate with the name of this Test to NewName and testNewName (2 times in next two lines)
public class Template extends BaseTest {

    public void testTemplate() throws Exception {
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
        waitForElement("AddTab");
        //[paste here your code from Selenium IDE without the sign in part]
        
    };
}
