package com.portal_ui;

import java.io.File;

public class DragTab extends BaseTest {

    public void testDragTab() throws Exception {
        // Login
        /* selenium.click("//div[@id='TabContainer']/h1"); */
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

        // Add tab
        waitForElement("AddTab");
        selenium.click("AddTab");
        selenium.type("InputTitle", "DragTab");
        waitForElement("//div[10]/div[3]/div/button[1]");
        selenium.click("//div[10]/div[3]/div/button[1]");

        // Drag tab
        selenium.click("AllTabsButton");
        selenium.waitForPageToLoad("30000");
        selenium.dragAndDrop("//*[@title='DragTab']", "400,30");

        // Delete tab
        selenium.click("//*[@class='remove']");
        selenium.click("//*[@class='remove']");
        waitForElement("//div[10]/div[3]/div/button[1]");
        selenium.click("//div[10]/div[3]/div/button[1]");
        /* verifyFalse(selenium.isTextPresent("DragTab")); */
    }
}

