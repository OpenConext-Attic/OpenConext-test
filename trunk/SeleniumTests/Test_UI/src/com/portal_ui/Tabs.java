package com.portal_ui;

import java.io.File;

public class Tabs extends BaseTest {
    public void testTabs() throws Exception {
        // Login
        selenium.open("/coin/");
        selenium.click("ButtonShowAll");
        selenium.click("//*[@class='SURFnetGuests']");
        selenium.click("LoginSubmit");
        selenium.waitForPageToLoad("90000");
        File f = new File("config" + File.separator + "config.txt");
        selenium.type("username", Utils.getConfigEntry(f, "username"));
        selenium.type("password", Utils.getConfigEntry(f, "password"));
        waitForElement("//input[@value='   Login   ']");
        selenium.click("//input[@value='   Login   ']");

        // Add one tab "First tab"
        waitForElement("AddTab");
        selenium.click("AddTab");
        waitForElement("AddTabDialog");
        selenium.type("InputTitle", "First tab");
        waitForElement("//div[10]/div[3]/div/button[1]");
        selenium.click("//div[10]/div[3]/div/button[1]");
        waitForElement("requestedTabName");
        verifyTrue(selenium.isTextPresent("First tab"));
        //Add tab without a name
        waitForElement("AddTab");
        selenium.click("AddTab");
        waitForElement("//div[10]/div[3]/div/button[1]");
        selenium.click("//div[10]/div[3]/div/button[1]");
        verifyTrue(selenium
                .isTextPresent("A tab needs to have a name. Please add a name for this tab."));
        // Add second tab
        selenium.type("InputTitle", "Second tab");
        waitForElement("//div[10]/div[3]/div/button[1]");
        selenium.click("//div[10]/div[3]/div/button[1]");
        waitForElement("requestedTabName");
        verifyTrue(selenium.isTextPresent("Second tab"));
        // Cancel delete tab
        waitForElement("link=x");
        selenium.click("link=x");
        waitForElement("//div[10]/div[3]/div/button[2]");
        selenium.click("//div[10]/div[3]/div/button[2]");
        waitForElement("requestedTabName");
        verifyTrue(selenium.isTextPresent("Second tab"));
        // Delete all tabs
        waitForElement("link=x");
        selenium.click("link=x");
        waitForElement("//div[10]/div[3]/div/button[1]");
        selenium.click("//div[10]/div[3]/div/button[1]");
        waitForElement("requestedTabName");
        verifyTrue(selenium.isTextPresent("First tab"));
        waitForElement("link=x");
        selenium.click("link=x");
        waitForElement("//div[10]/div[3]/div/button[1]");
        selenium.click("//div[10]/div[3]/div/button[1]");
        waitForElement("//div[@id='TabContainer']/h1");
        verifyTrue(selenium.isTextPresent("Other tabs"));
    }
}
