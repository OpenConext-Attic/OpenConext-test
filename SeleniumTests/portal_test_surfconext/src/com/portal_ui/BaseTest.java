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

import java.io.File;

import com.thoughtworks.selenium.*;

public class BaseTest extends SeleneseTestCase {
	public void setUp() throws Exception {
		File f = new File("config" + File.separator + "config.txt");
		String url = Utils.getConfigEntry(f, "url");
		String browser = Utils.getConfigEntry(f, "browser");
		String speed = Utils.getConfigEntry(f, "speed");
		String path = Utils.getConfigEntry(f, "path");
		setUp(url, "*" + browser);
		selenium.setSpeed(speed);
		selenium.windowMaximize();
		selenium.open(url + "/" + path);
		signIn();
	}

	public void signIn() throws Exception {
		// Open portal page
		File f = new File("config" + File.separator + "config.txt");
		selenium.open(Utils.getConfigEntry(f, "path"));
		selenium.waitForPageToLoad("90000");
		// Sign in with SURFguest test account
		selenium.type("username", Utils.getConfigEntry(f, "username"));
		selenium.type("password", Utils.getConfigEntry(f, "password"));
		waitForElement("//input[@value='   Login   ']");
		selenium.click("//input[@value='   Login   ']");

		// skip POST data screen...
		selenium.waitForPageToLoad("30000");
		while (selenium.isTextPresent("Processing... please wait")) {
			selenium.waitForPageToLoad("10000");
		}

		// Supply consent
		if (selenium.getTitle().equalsIgnoreCase("SURFconext - Attribute Release")) {
			waitForElement("accept_terms_button");
			selenium.click("accept_terms_button");
		}
	}

	public void deleteAllTabs() throws Exception {
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


	public void waitForElement(final String waitingElement) {
		new Wait() {
			public boolean until() {
				return selenium.isElementPresent(waitingElement);
			}
		}.wait("Timeout while waiting for element " + waitingElement);
	}

}
