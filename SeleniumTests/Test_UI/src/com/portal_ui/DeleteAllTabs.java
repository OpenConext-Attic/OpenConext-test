package com.portal_ui;

import java.io.File;

public class DeleteAllTabs extends BaseTest {
    
    public void testDeleteAllTabs() throws Exception {
        // Sign in with SURFguest test account
        selenium.open("/coin/");
        selenium.click("ButtonShowAll");
        selenium.click("//*[@class='SURFnetGuests']");
        selenium.click("LoginSubmit");
        selenium.waitForPageToLoad("90000");
        File f = new File("config" + File.separator + "config.txt");
        String username = Utils.getConfigEntry(f, "username");
        selenium.type("username", username);
        selenium.type("password", Utils.getConfigEntry(f, "password"));
        waitForElement("//input[@value='   Login   ']");
        selenium.click("//input[@value='   Login   ']");

           waitForElement("AddTab");
           int count = selenium.getXpathCount("//li/a/span[1]").intValue(); // usually 20 or more
                   for (int i = 1; i < count; i++) {
                           if(selenium.isElementPresent("//li/a/span[1]")) {
                               waitForElement("//li/a/span[1]");
                               selenium.click("//li/a/span[1]");
                               waitForElement("link=x");
                               selenium.click("link=x");
                               waitForElement("//div[10]/div[3]/div/button[1]");
                               selenium.click("//div[10]/div[3]/div/button[1]");
                                  }

                   } 
     
    };
}
