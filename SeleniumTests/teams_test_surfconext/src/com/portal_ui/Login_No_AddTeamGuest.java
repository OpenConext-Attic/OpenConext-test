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


public class Login_No_AddTeamGuest extends BaseTest {

	public void testlogin_AddTeam() throws Exception {
		// Sign in with SURFguest test account
		selenium.open("/Shibboleth.sso/Login?entityID=SURFnetGuests&target="+ getUrl());
		selenium.type("name=username", username2);
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
				Screenshot();
				throw new Error(e);
			}
			if ("SURFteams".equals(selenium.getText("SURFteams")))
				fail("Guest kan team maken.");
				
			
			Thread.sleep(1000);
		}
	}
	
}
