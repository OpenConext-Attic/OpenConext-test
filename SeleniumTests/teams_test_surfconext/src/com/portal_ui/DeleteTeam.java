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

public class DeleteTeam extends Login {

    public void testSignIn() throws Exception {
		login();
    	verifyTrue(selenium.isTextPresent("Logout"));
		selenium.open("/teams/home.shtml?teams=my&view=app");
		selenium.click("link=teamteamteam");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=DeleteTeam");
		selenium.click("//div[3]/div[3]/div/button");
		selenium.waitForPageToLoad("30000");
    }
}