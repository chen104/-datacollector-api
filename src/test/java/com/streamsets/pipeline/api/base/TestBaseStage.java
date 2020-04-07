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
package com.streamsets.pipeline.api.base;

import com.streamsets.pipeline.api.Stage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestBaseStage {
  private Stage.Info info = Mockito.mock(Stage.Info.class);
  private Stage.Context context = Mockito.mock(Stage.Context.class);
  private boolean inited, connInited;
  private List<Stage.ConfigIssue> initIssues = null;
  private List<Stage.ConfigIssue> initConnIssues = null;

  public class TBaseStage extends BaseStage<Stage.Context> {

    @Override
    protected List<ConfigIssue> initConnection() {
      List<ConfigIssue> issues = super.initConnection();
      issues.addAll(initConnIssues);
      Assert.assertEquals(info, getInfo());
      Assert.assertEquals(context, getContext());
      connInited = true;
      return issues;
    }

    @Override
    protected List<ConfigIssue> init() {
      List<ConfigIssue> issues = super.init();
      issues.addAll(initIssues);
      Assert.assertEquals(info, getInfo());
      Assert.assertEquals(context, getContext());
      inited = true;
      return issues;
    }

  }

  @Before
  public void before() {
    connInited = false;
    initConnIssues = new ArrayList<>();
    inited = false;
    initIssues = new ArrayList<>();
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testBaseStage() throws Exception {
    Stage stage = new TBaseStage();
    List<Stage.ConfigIssue> issues = stage.init(info, context);
    Assert.assertEquals(0, issues.size());
    Assert.assertTrue(connInited);
    Assert.assertTrue(inited);
    stage.destroy();
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testBaseStageInitConnFailure() throws Exception {
    Stage stage = new TBaseStage();
    initConnIssues.add(Mockito.mock(Stage.ConfigIssue.class));
    List<Stage.ConfigIssue> issues = stage.init(info, context);
    Assert.assertEquals(1, issues.size());
    Assert.assertTrue(connInited);
    Assert.assertFalse(inited);
    stage.destroy();
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testBaseStageInitFailure() throws Exception {
    Stage stage = new TBaseStage();
    initIssues.add(Mockito.mock(Stage.ConfigIssue.class));
    List<Stage.ConfigIssue> issues = stage.init(info, context);
    Assert.assertEquals(1, issues.size());
    Assert.assertTrue(connInited);
    Assert.assertTrue(inited);
    stage.destroy();
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testBaseStageBothFailure() throws Exception {
    Stage stage = new TBaseStage();
    initConnIssues.add(Mockito.mock(Stage.ConfigIssue.class));
    initIssues.add(Mockito.mock(Stage.ConfigIssue.class));
    List<Stage.ConfigIssue> issues = stage.init(info, context);
    // should be only 1, because the logic short-circuits init() call if
    // initConnection() failed
    Assert.assertEquals(1, issues.size());
    Assert.assertTrue(connInited);
    Assert.assertFalse(inited);
    stage.destroy();
  }
}
