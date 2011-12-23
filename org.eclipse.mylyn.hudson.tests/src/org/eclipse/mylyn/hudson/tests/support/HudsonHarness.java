/*******************************************************************************
 * Copyright (c) 2011 Tasktop Technologies and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Tasktop Technologies - initial API and implementation
 *******************************************************************************/

package org.eclipse.mylyn.hudson.tests.support;

import org.eclipse.mylyn.commons.repositories.core.RepositoryLocation;
import org.eclipse.mylyn.commons.repositories.core.auth.AuthenticationType;
import org.eclipse.mylyn.commons.repositories.core.auth.UsernamePasswordCredentials;
import org.eclipse.mylyn.internal.hudson.core.client.HudsonConfigurationCache;
import org.eclipse.mylyn.internal.hudson.core.client.HudsonException;
import org.eclipse.mylyn.internal.hudson.core.client.RestfulHudsonClient;
import org.eclipse.mylyn.internal.hudson.model.HudsonModelJob;
import org.eclipse.mylyn.tests.util.TestUtil;
import org.eclipse.mylyn.tests.util.TestUtil.Credentials;
import org.eclipse.mylyn.tests.util.TestUtil.PrivilegeLevel;

/**
 * @author Steffen Pingel
 */
public class HudsonHarness {

	private static final String PLAN_DISABLED = "test-disabled";

	private static final String PLAN_FAILING = "test-failing";

	private static final String PLAN_SUCCEEDING = "test-succeeding";

	private static final String PLAN_WHITESPACE = "test-white space";

	private final HudsonFixture fixture;

	private RestfulHudsonClient client;

	public HudsonHarness(HudsonFixture fixture) {
		this.fixture = fixture;
	}

	public RestfulHudsonClient connect() throws Exception {
		return connect(PrivilegeLevel.USER);
	}

	public RestfulHudsonClient connect(PrivilegeLevel level) throws Exception {
		Credentials credentials = TestUtil.readCredentials(level);
		RepositoryLocation location = new RepositoryLocation();
		location.setUrl(fixture.getRepositoryUrl());
		location.setCredentials(AuthenticationType.HTTP, new UsernamePasswordCredentials(credentials.username,
				credentials.password));
		client = new RestfulHudsonClient(location, new HudsonConfigurationCache());
		return client;
	}

	public void dispose() {
	}

	public HudsonFixture getFixture() {
		return fixture;
	}

	public String getPlanDisabled() {
		return PLAN_DISABLED;
	}

	public String getPlanFailing() {
		return PLAN_FAILING;
	}

	public String getPlanSucceeding() {
		return PLAN_SUCCEEDING;
	}

	public String getPlanWhitespace() {
		return PLAN_WHITESPACE;
	}

	public HudsonModelJob getJob(String name) throws HudsonException {
		for (HudsonModelJob job : client.getJobs(null, null)) {
			if (job.getName().equals(name)) {
				return job;
			}
		}
		return null;
	}

}