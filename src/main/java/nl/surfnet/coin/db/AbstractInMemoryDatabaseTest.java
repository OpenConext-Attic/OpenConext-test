/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package nl.surfnet.coin.db;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

/**
 * Abstract class for running in-memory unit tests
 *
 * @deprecated Please use Flyway migrations or DbUnit for tests
 */
@Deprecated
public abstract class AbstractInMemoryDatabaseTest {

  private static final Logger logger = LoggerFactory.getLogger(AbstractInMemoryDatabaseTest.class);

  private JdbcTemplate jdbcTemplate;;

  /**
   * 
   * @return the path/filename of a sql file with database content
   */
  public abstract String getMockDataContentFilename();

  /**
   * 
   * @return the path/filename of a sql file with database cleanup commands
   */
  public abstract String getMockDataCleanUpFilename();

  /**
   * We use an in-memory database - no need for Spring in this one - and
   * populate it with the sql statements in test-data-eb.sql
   * 
   * @throws Exception
   *           unexpected
   */
  @Before
  public void before() throws Exception {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setPassword("");
    dataSource.setUsername("sa");
    String url = getDataSourceUrl();
    dataSource.setUrl(url);
    dataSource.setDriverClassName("org.hsqldb.jdbcDriver");

    jdbcTemplate = new JdbcTemplate(dataSource);

    ClassPathResource resource = new ClassPathResource(getMockDataContentFilename());
    logger.debug("Loading database content from " + resource);
    if (resource.exists()) {
      final String sql = IOUtils.toString(resource.getInputStream());
      final String[] split = sql.split(";");
      for (String s : split) {
        if (!StringUtils.hasText(s)) {
          continue;
        }
        jdbcTemplate.execute(s + ';');
      }
    }
  }

  protected String getDataSourceUrl() {
    return "jdbc:hsqldb:mem:coin";
  }

  @After
  public void afterClass() throws Exception {

    ClassPathResource resource = new ClassPathResource(getMockDataCleanUpFilename());
    if (resource.exists()) {
      logger.debug("Cleaning database content from " + resource);
      final String sql = IOUtils.toString(resource.getInputStream());
      final String[] split = sql.split(";");
      for (String s : split) {
        if (!StringUtils.hasText(s)) {
          continue;
        }
        jdbcTemplate.execute(s + ';');
      }

    }

  }

  /**
   * @return the jdbcTemplate
   */
  public JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }

}
