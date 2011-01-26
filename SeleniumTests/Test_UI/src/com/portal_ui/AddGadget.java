package com.portal_ui;

import java.io.File;

public class AddGadget extends BaseTest {
        
    public void testAddGadget() throws Exception {
        selenium.click("//div[@id='TabContainer']/h1");
        selenium.click("ButtonShowAll");
        selenium.click("//img[contains(@src,'https://www.surfguest.nl/img/surfnet_logo.gif')]");
        selenium.click("LoginSubmit");
        selenium.waitForPageToLoad("90000");
        File f = new File("config/config.txt");
        selenium.type("username", Utils.getConfigEntry(f, "username"));
        selenium.type("password", Utils.getConfigEntry(f, "password"));
        waitForElement("//input[@value='   Login   ']");
        selenium.click("//input[@value='   Login   ']");
        waitForElement("AddTab");
        selenium.click("AddTab");
        selenium.type("InputTitle", "add gadget");
        waitForElement("//div[8]/div[3]/div/button[1]");
        selenium.click("//div[8]/div[3]/div/button[1]");
        waitForElement("link=Gadget");
        selenium.click("link=Gadget");
        waitForElement("GadgetQuery");
        selenium.type("GadgetQuery", "SURFteams");
        selenium.click("SubmitGadgetSearch");
        verifyTrue(selenium.isTextPresent("SURFteams"));
        selenium.click("//*[@class='addGadgetBttn button-primary']");
        waitForElement("//div[8]/div[3]/div/button[1]");
        selenium.click("//div[8]/div[3]/div/button[1]");
        waitForElement("link=Gadget");
        selenium.click("link=Gadget");
        waitForElement("GadgetQuery");
        selenium.type("GadgetQuery", "surfmedia");
        selenium.click("SubmitGadgetSearch");
        verifyTrue(selenium.isTextPresent("SURFmedia player"));
        verifyTrue(selenium.isTextPresent("SURFmedia list"));
        selenium.click("//*[@class='addGadgetBttn button-primary']");
        waitForElement("//div[8]/div[3]/div/button[2]");
        selenium.click("//div[8]/div[3]/div/button[2]");
        verifyTrue(selenium.isTextPresent("+ Add Gadget"));
        selenium.click("//*[@class='addGadgetBttn button-primary']");
        waitForElement("//div[8]/div[3]/div/button[1]");
        selenium.click("//div[8]/div[3]/div/button[1]");
        waitForElement("link=Gadget");
        selenium.click("link=Gadget");
        waitForElement("GadgetQuery");
        selenium.type("GadgetQuery", "SURFteams");
        selenium.click("SubmitGadgetSearch");
        selenium.click("//*[@class='addGadgetBttn button-primary']");
        waitForElement("//div[8]/div[3]/div/button[2]");
        selenium.click("//div[8]/div[3]/div/button[2]");
        waitForElement("GadgetQuery");
        selenium.type("GadgetQuery", "SURFteams");
        selenium.click("SubmitGadgetSearch");
        selenium.click("//*[@class='addGadgetBttn button-primary']");
        waitForElement("//div[8]/div[3]/div/button[1]");
        selenium.click("//div[8]/div[3]/div/button[1]");
        selenium.click("link=x");
        waitForElement("//div[8]/div[3]/div/button[1]");
        selenium.click("//div[8]/div[3]/div/button[1]");
    }
}
