package com.portal_ui;

import java.io.File;

public class AssignTeam extends BaseTest {
        
    public void testAssignTeam() throws Exception {
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
        selenium.type("InputTitle", "Assign2Team");
        waitForElement("//div[8]/div[3]/div/button[1]");
        selenium.click("//div[8]/div[3]/div/button[1]");
        waitForElement("link=Team");
        selenium.click("link=Team");
        waitForElement("teamSettingsShowAssign");
        selenium.click("teamSettingsShowAssign");
        waitForElement("link=about");
        selenium.click("link=about");
        verifyTrue(selenium.isTextPresent("This team contains 4 test accounts: conexttest, conexttest1, conexttest2 and conexttest3."));
        selenium.click("//input[@value='nl:surfnet:diensten:conexttest']");
        selenium.click("AssignToTeamButton");
        waitForElement("teamSettingsSnapshotLater");
        selenium.click("teamSettingsSnapshotLater");
        waitForElement("requestedTabName");
        verifyTrue(selenium.isTextPresent("Assign2Team"));
        verifyTrue(selenium.isTextPresent("ConextTest"));
        selenium.click("//a[@id='TeamsButton']/span");
        verifyTrue(selenium.isTextPresent("ConextTest"));
        selenium.click("//*[.='ConextTest']");
        verifyTrue(selenium.isTextPresent("Conext Test"));
        verifyTrue(selenium.isTextPresent("Conext Testtwo"));
        verifyTrue(selenium.isTextPresent("Conext Testthree"));
        verifyTrue(selenium.isTextPresent("Conext Testone"));
        verifyTrue(selenium.isTextPresent("Assign2Team"));
        selenium.click("//a[contains(text(),'Assign2Team')]");
        //selenium.click("//*[@title='Assign2Team']");
        waitForElement("link=x");
        selenium.click("link=x");
        waitForElement("//div[8]/div[3]/div/button[1]");
        selenium.click("//div[8]/div[3]/div/button[1]");
    }
}
