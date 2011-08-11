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

package com.portal_ui;

public class AddGadget extends BaseTest {
        
    public void testAddGadget() throws Exception {
        // Add tab
        waitForElement("AddTab");
        selenium.click("AddTab");
        selenium.type("InputTitle", "add gadget");
        //waitForElement("//div[3]/div[3]/div/button[1]");
        //selenium.click("//div[3]/div[3]/div/button[1]");
        waitForElement("//span[text()='Add']");
        selenium.click("//span[text()='Add']");
        
        // search SURFteams gadget
        waitForElement("link=Gadget");
        selenium.click("link=Gadget");
        waitForElement("GadgetQuery");
        selenium.type("GadgetQuery", "SURFteams");
        selenium.click("SubmitGadgetSearch");
        // add SURFteams gadget
        verifyTrue(selenium.isTextPresent("SURFteams"));
        selenium.click("//*[@class='addGadgetBttn button-primary']");
        // give consent
        waitForElement("//div[6]/div[3]/div/button[1]");
        selenium.click("//div[6]/div[3]/div/button[1]");
        
        // search SURFmedia gadgets
        waitForElement("link=Gadget");
        selenium.click("link=Gadget");
        waitForElement("GadgetQuery");
        selenium.type("GadgetQuery", "surfmedia");
        selenium.click("SubmitGadgetSearch");
        verifyTrue(selenium.isTextPresent("SURFmedia player"));
        verifyTrue(selenium.isTextPresent("SURFmedia list"));
        // add SURFmedia player gadget
        selenium.click("//*[@class='addGadgetBttn button-primary']");
        waitForElement("//div[6]/div[3]/div/button[2]");
        selenium.click("//div[6]/div[3]/div/button[2]");
        verifyTrue(selenium.isTextPresent("+ Add Gadget"));
        // add SURFmedia gadget
        selenium.click("//*[@class='addGadgetBttn button-primary']");
        waitForElement("//div[6]/div[3]/div/button[1]");
        selenium.click("//div[6]/div[3]/div/button[1]");
        
        
        // search SURFteams gadget
        waitForElement("link=Gadget");
        selenium.click("link=Gadget");
        waitForElement("GadgetQuery");
        selenium.type("GadgetQuery", "SURFteams");
        selenium.click("SubmitGadgetSearch");
        // add SURFteams gadget
        selenium.click("//*[@class='addGadgetBttn button-primary']");
        // cancel consent
        waitForElement("//div[6]/div[3]/div/button[2]");
        selenium.click("//div[6]/div[3]/div/button[2]");
        
        // search SURFteams gadget
        waitForElement("GadgetQuery");
        selenium.type("GadgetQuery", "SURFteams");
        // add SURFteams gadget
        selenium.click("SubmitGadgetSearch");
        selenium.click("//*[@class='addGadgetBttn button-primary']");
        waitForElement("//div[6]/div[3]/div/button[1]");
        selenium.click("//div[6]/div[3]/div/button[1]");
        
        //remove tab
		waitForElement("//li[starts-with(@id, 'Tab_')]");
		selenium.click("//li[starts-with(@id, 'Tab_')]");
		waitForElement("link=x");
		selenium.click("link=x");
		waitForElement("//div[4]/div[3]/div/button[1]");
		selenium.click("//div[4]/div[3]/div/button[1]");
		
		//cleanup
		super.deleteAllTabs();
    }
}