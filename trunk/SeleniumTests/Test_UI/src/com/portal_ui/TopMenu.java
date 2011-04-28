package com.portal_ui;

import java.io.File;

public class TopMenu extends BaseTest {

    public void testTopMenu() throws Exception {
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

        waitForElement("AddTab");
        selenium.click("link=About");
        verifyTrue(selenium.isTextPresent("Version"));
        verifyTrue(selenium.isTextPresent("Copyright"));
        verifyTrue(selenium.isTextPresent("Terms of Service"));
        selenium.click("//div[7]/div[3]/div/button");
        selenium.click("//li[@id='UserName']/strong");
        selenium.click("//li[@id='UserName']/strong");
        verifyTrue(selenium.isTextPresent("test.surfguest.nl"));
        selenium.click("//li[@id='UserName']/strong");
        verifyTrue(selenium.isTextPresent("exact:urn:collab:person:test.surfguest.nl:" + Utils.getConfigEntry(f, "username")));
        selenium.click("link=Help");
        waitForElement("//div[7]/div[3]/div/button");
        verifyTrue(selenium.isTextPresent("Getting started in just 5 stepsclose"));
        selenium.click("//div[7]/div[3]/div/button");
        }
}
