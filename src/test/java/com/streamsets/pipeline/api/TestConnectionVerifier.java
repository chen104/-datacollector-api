/*
 * Copyright 2017 StreamSets Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.streamsets.pipeline.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class TestConnectionVerifier {
  private Stage.Info info = Mockito.mock(Stage.Info.class);
  private Source.Context context = Mockito.mock(Source.Context.class);
  private boolean connInited;
  private List<Stage.ConfigIssue> initConnIssues = null;

  public class TConnectionVerifier extends ConnectionVerifier {

    @Override
    protected List<ConfigIssue> initConnection() {
      List<ConfigIssue> issues = new ArrayList<>();
      issues.addAll(initConnIssues);
      connInited = true;
      return issues;
    }
  }

  @Before
  public void before() {
    connInited = false;
    initConnIssues = new ArrayList<>();
  }

  @Test
  public void testConnectionVerifier() {
    ConnectionVerifier verifier = new TConnectionVerifier();
    List<Stage.ConfigIssue> issues = verifier.init(info, context);
    Assert.assertEquals(0, issues.size());
    Assert.assertTrue(connInited);
    verifier.destroy();
  }

  @Test
  public void testConnectionVerifierInitConnectionFailure() {
    ConnectionVerifier verifier = new TConnectionVerifier();
    initConnIssues.add(Mockito.mock(Stage.ConfigIssue.class));
    List<Stage.ConfigIssue> issues = verifier.init(info, context);
    Assert.assertEquals(1, issues.size());
    Assert.assertTrue(connInited);
    verifier.destroy();
  }
}
