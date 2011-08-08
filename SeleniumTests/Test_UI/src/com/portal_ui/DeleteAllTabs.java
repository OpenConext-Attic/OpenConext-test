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

public class DeleteAllTabs extends BaseTest {
	
	public void testDeleteAllTabs() throws Exception {
		waitForElement("AddTab");
		while (selenium.isElementPresent("//li[starts-with(@id, 'Tab_')]")){
				waitForElement("//li[starts-with(@id, 'Tab_')]");
				selenium.click("//li[starts-with(@id, 'Tab_')]");
				waitForElement("link=x");
				selenium.click("link=x");
				waitForElement("//div[4]/div[3]/div/button[1]");
				selenium.click("//div[4]/div[3]/div/button[1]");
		}
		// TODO Delete 'other tabs'
	};
}
