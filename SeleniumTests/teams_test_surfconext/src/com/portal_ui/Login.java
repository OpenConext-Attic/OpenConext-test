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


public class Login extends BaseTest {

	public void login() throws Exception {
		// Sign in with SURFguest test account
		selenium.open("/Shibboleth.sso/Login?entityID=SURFnetGuests&target="+ getUrl());
		selenium.type("name=username", username);
		selenium.type("name=password", password);
		selenium.click("css=input[type=submit]");
		selenium.waitForPageToLoad("30000");
		for (int second = 0;; second++) {
			if (second >= 6)
				fail("timeout");
			try {
				if (selenium.isTextPresent("Logout"))
					break;
				if (selenium.isTextPresent("SURFconext - My Profile"))
					break;
				if ("I Accept".equals(selenium.getValue("id=accept_terms_button")))
					selenium.click("id=accept_terms_button");
			}
			catch (Exception e) {
			}
			Thread.sleep(1000);
		}
//		verifyTrue(selenium.isTextPresent("SURFconext - My Profile ")); 
//		verifyTrue(selenium.isTextPresent("Logout")); 
//		verifyEquals("I Accept", selenium.getValue("id=accept_terms_button"));
	}
	
}
/*
 * // check if user consent display is shown String username =
 * Utils.getConfigEntry(f, "username"); if (selenium.isTextPresent("Net-ID")) {
 * waitForElement("accept_terms_button"); selenium.click("accept_terms_button");
 * waitForElement("AddTab"); verifyTrue(selenium.isTextPresent("Other tabs"));
 * selenium.click("//li[@id='UserName']/strong");
 * verifyTrue(selenium.isTextPresent
 * ("exact:urn:collab:person:test.surfguest.nl:" + username));
 * selenium.click("PageContainer"); selenium.click("link=Sign out");
 * selenium.waitForPageToLoad("30000");
 * verifyTrue(selenium.isTextPresent("Local Logout")); } else {
 * waitForElement("AddTab"); verifyTrue(selenium.isTextPresent("Other tabs"));
 * selenium.click("//li[@id='UserName']/strong"); // check if the username is
 * right (same as sign in)
 * verifyTrue(selenium.isTextPresent("exact:urn:collab:person:test.surfguest.nl:"
 * + username)); selenium.click("PageContainer");
 * selenium.click("link=Sign out"); selenium.waitForPageToLoad("30000"); //
 * check if logout message is shown
 * verifyTrue(selenium.isTextPresent("Local Logout")); } }; }
 */