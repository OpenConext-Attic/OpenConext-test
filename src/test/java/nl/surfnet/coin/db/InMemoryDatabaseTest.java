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

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import nl.surfnet.coin.db.AbstractInMemoryDatabaseTest;

/**
 * 
 *
 */
public class InMemoryDatabaseTest extends AbstractInMemoryDatabaseTest {

  /*
   * (non-Javadoc)
   * 
   * @see
   * nl.surfnet.coin.db.AbstractInMemoryDatabaseTest#getMockDataContentFilename
   * ()
   */
  @Override
  public String getMockDataContentFilename() {
    return "sql/test-data.sql";
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * nl.surfnet.coin.db.AbstractInMemoryDatabaseTest#getMockDataCleanUpFilename
   * ()
   */
  @Override
  public String getMockDataCleanUpFilename() {
    return "sql/cleanup-test-data.sql";
  }

  @Test
  public void testDatabaseTemplate() throws Exception {
    List<String> result = getJdbcTemplate().query("select user_id from example_table", new RowMapper<String>() {
      @Override
      public String mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getString(1);
      }
    });
    assertEquals(1, result.size());
    assertEquals("whatever", result.get(0));
  }

}
