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
import java.util.Calendar;

import com.thoughtworks.selenium.SeleneseTestCase;
import com.thoughtworks.selenium.Wait;

public class BaseTest extends SeleneseTestCase {
	
	 
	protected File f;
	protected String url;
	protected String browser;
	protected String speed;
	protected String path;
	
	
	public String getUrl() {
		return url;
	}

	public BaseTest () {
		try {
			f = new File("config" + File.separator + "config.txt");
			url = Utils.getConfigEntry(f, "urlprofile");
			browser = Utils.getConfigEntry(f, "browser");
			speed = Utils.getConfigEntry(f, "speed");
			path = Utils.getConfigEntry(f, "path");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    @Override
    public void setUp() throws Exception {
        if(url.equals("https://profile.test.surfconext.nl")){
        	setUp("https://teams.test.surfconext.nl", "*" + browser);
            selenium.setSpeed(speed);
            selenium.windowMaximize();
            selenium.open("https://teams.test.surfconext.nl" + "/" + path);	
        }
        else {
    	setUp(url, "*" + browser);
        selenium.setSpeed(speed);
        selenium.windowMaximize();
        selenium.open(url + "/" + path);
        }
    }

    public void waitForElement(final String waitingElement) {
        new Wait() {
            @Override
            public boolean until() {
                return selenium.isElementPresent(waitingElement);
            }
        }.wait("Timeout while waiting for element " + waitingElement);
    }
	public void Screenshot() {
		Calendar cal = Calendar.getInstance();
		int dag = cal.get(Calendar.DAY_OF_MONTH);
		int maand = cal.get(Calendar.MONTH) + 1;
		int jaar = cal.get(Calendar.YEAR);
		int uur = cal.get(Calendar.HOUR_OF_DAY);
		int minuut = cal.get(Calendar.MINUTE);
		int seconde = cal.get(Calendar.SECOND);

		selenium.captureEntirePageScreenshot("C:\\Selenium\\Workspace\\teams_test_surfconext\\report\\Screens\\" + "selenium error on "
				+ selenium.getTitle() + " at " + uur + ";" + minuut + ";"
				+ seconde + " on " + dag + "-" + maand + "-" + jaar + ".PNG",
				"");
	}
}
