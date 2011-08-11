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

import com.portal_ui.Login;

public class Verifyprofile extends Login {
		
	public void testVerify() throws Exception {
		login();
		verifyTrue(selenium.isTextPresent("conexttest"));
		verifyTrue(selenium.isTextPresent("Conext Test"));
		verifyTrue(selenium.isTextPresent("conexttest@gmail.com"));
		verifyTrue(selenium.isTextPresent("test.surfguest.nl"));
		verifyTrue(selenium.isTextPresent("conexttest@SURFguest.nl"));
		verifyTrue(selenium.isTextPresent("guest"));
		verifyTrue(selenium.isTextPresent("attribute-manipulations/manipulations.php"));
		verifyTrue(selenium.isTextPresent("urn:collab:person:test.surfguest.nl:conexttest"));
//		verifyTrue(selenium.isTextPresent("Delete my information!"));
	}
}