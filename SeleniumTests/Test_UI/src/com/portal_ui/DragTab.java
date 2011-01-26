package com.portal_ui;

import java.io.File;

public class DragTab extends BaseTest {

    public void testDragTab() throws Exception {
        selenium.click("//div[@id='TabContainer']/h1");
        selenium.click("ButtonShowAll");
        selenium.click("//img[contains(@src,'https://www.surfguest.nl/img/surfnet_logo.gif')]");
        selenium.click("LoginSubmit");
        selenium.waitForPageToLoad("90000");
        File f = new File("config" + File.separator + "config.txt");
        selenium.type("username", Utils.getConfigEntry(f, "username"));
        selenium.type("password", Utils.getConfigEntry(f, "password"));
        waitForElement("//input[@value='   Login   ']");
        selenium.click("//input[@value='   Login   ']");
        waitForElement("AddTab");
        selenium.click("AddTab");
        selenium.type("InputTitle", "DragTab");
        waitForElement("//div[8]/div[3]/div/button[1]");
        selenium.click("//div[8]/div[3]/div/button[1]");
        selenium.click("AllTabsButton");
        selenium.click("AllTabsButton");
        selenium.waitForPageToLoad("30000");
        selenium.dragAndDrop("//*[@title='DragTab']", "200,70");
        selenium.click("//*[@title='remove from menu']");
        waitForElement("//div[8]/div[3]/div/button[1]");
        selenium.click("//div[8]/div[3]/div/button[1]");
        verifyFalse(selenium.isTextPresent("DragTab"));
    }
}

