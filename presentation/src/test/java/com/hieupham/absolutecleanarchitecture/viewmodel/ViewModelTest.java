package com.hieupham.absolutecleanarchitecture.viewmodel;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import com.hieupham.absolutecleanarchitecture.util.RxImmediateSchedulerRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * Created by hieupham on 6/19/18.
 */

@RunWith(JUnit4.class)
public class ViewModelTest {

    @Rule
    public final MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule
    public final TestRule globalTimeoutRule = Timeout.seconds(20);

    @Rule
    public final ExpectedException thrownRule = ExpectedException.none();

    @Rule
    public final RxImmediateSchedulerRule rxImmediateSchedulerRule = new RxImmediateSchedulerRule();

    @Rule
    public final TestRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void before() {
    }

    @After
    public void after() {
    }
}
