package com.hieupham.data.dao;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.hieupham.data.data.DataManager;
import com.hieupham.data.source.local.api.dao.AssetDao;
import com.hieupham.data.source.local.api.dao.BlockDao;
import com.hieupham.domain.entity.Transaction;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;

/**
 * Created by hieupham on 5/25/18.
 */

@RunWith(AndroidJUnit4.class)
public abstract class DaoTest<T> {

    @Rule
    public final TestRule globalTimeoutRule = Timeout.seconds(120);

    @Rule
    public final TestRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    T dao;
    private DataManager dataManager;

    @Before
    public void before() {
        if (dataManager == null) {
            Context context = InstrumentationRegistry.getTargetContext();
            dataManager = new DataManager(context);
            dao = getDao();
        }
        dataManager.setup();
    }

    @After
    public void after() {
        dataManager.teardown();
    }

    @SuppressWarnings("unchecked")
    private T getDao() {
        Class<T> daoClass = getDaoClass();
        if (daoClass.isAssignableFrom(Transaction.class)) {
            return (T) dataManager.database().transactionDao();
        } else if (daoClass.isAssignableFrom(BlockDao.class)) {
            return (T) dataManager.database().blockDao();
        } else if (daoClass.isAssignableFrom(AssetDao.class)) {
            return (T) dataManager.database().assetDao();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private Class<T> getDaoClass() {
        Type daoType = getClass().getGenericSuperclass();
        while (!(daoType instanceof ParameterizedType)
                || ((ParameterizedType) daoType).getRawType() != DaoTest.class) {
            if (daoType instanceof ParameterizedType) {
                daoType =
                        ((Class<?>) ((ParameterizedType) daoType).getRawType()).getGenericSuperclass();
            } else {
                daoType = ((Class<?>) daoType).getGenericSuperclass();
            }
        }

        return (Class<T>) ((ParameterizedType) daoType).getActualTypeArguments()[0];
    }
}
