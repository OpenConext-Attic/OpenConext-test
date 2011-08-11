/*
 * Copyright 2011 SURFnet bv, The Netherlands
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.portal_ui.Original;

import java.io.File;

import com.portal_ui.BaseTest;

public class AssignTeam extends BaseTest {
        
    public void testAssignTeam() throws Exception {
        // Sign in with SURFguest test account
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
        selenium.click("AddTab");
        selenium.type("InputTitle", "Assign2Team");
        waitForElement("//div[10]/div[3]/div/button[1]");
        selenium.click("//div[10]/div[3]/div/button[1]");
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
        waitForElement("//div[10]/div[3]/div/button[1]");
        selenium.click("//div[10]/div[3]/div/button[1]");
    }
}
