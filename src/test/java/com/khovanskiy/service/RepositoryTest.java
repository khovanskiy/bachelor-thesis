package com.khovanskiy.service;

import com.khovanskiy.model.Ref;
import com.khovanskiy.model.TrainRun;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author victor
 */
public class RepositoryTest {
    private Repository repository = new Repository();

    @Before
    public void save() {
        Ref<TrainRun> trainRunId1 = new TrainRun.Id("1");
        TrainRun trainRun1 = new TrainRun();
        trainRun1.setId(trainRunId1);
        repository.create(trainRun1);

        Ref<TrainRun> trainRunId2 = new TrainRun.Id("2");
        TrainRun trainRun2 = new TrainRun();
        trainRun2.setId(trainRunId2);
        repository.create(trainRun2);
    }

    @Test
    public void find() {
        Assert.assertTrue(repository.find(new TrainRun.Id("1")).isPresent());
    }

    @Test
    public void findAll() {
        Assert.assertEquals(2, repository.findAll(TrainRun.class).size());
    }

    @After
    public void after() {
        repository.clear();
    }
}
