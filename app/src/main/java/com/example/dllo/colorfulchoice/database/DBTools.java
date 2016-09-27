package com.example.dllo.colorfulchoice.database;

import android.content.Context;

import com.example.dllo.colorfulchoice.base.MyApp;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by mayinling on 16/9/27.
 */
public class DBTools {

    private static String TAG = DBTools.class.getSimpleName();
    private static DBTools instance;
    private static Context appContext;
    private DaoSession mDaosession;
    private GoodThingsDao goodThingsDao;
    private ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
    private Query query;

    private DBTools() {

    }

    // 单例模式
    public static DBTools getInstance(Context context) {
        if (instance == null) {
            instance = new DBTools();
            if (appContext == null) {
                appContext = context.getApplicationContext();
            }
            instance.mDaosession = MyApp.getDaoSession();
            instance.goodThingsDao = instance.mDaosession.getGoodThingsDao();
        }
        return instance;
    }

    // 插入
    public void insertGoodThing(final GoodThings goodThings) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                if (hasData(goodThings)) {
                    return;
                }
                goodThingsDao.insert(goodThings);
            }
        });
    }

    // 去重
    private boolean hasData(GoodThings goodThings) {
        List<GoodThings> goodThingses;
        if (goodThings.getImgUrl() != null) {
            goodThingses = goodThingsDao.queryBuilder()
                    .where(GoodThingsDao.Properties.ImgUrl.eq(goodThings.getImgUrl()))
                    .build().list();
            if (goodThingses.size() > 0) {
                return true;
            }
        }
        return false;
    }

    // 同步用户名查询
    public void queryUserName(final String finaUrl, final String finaUser) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                Query query = goodThingsDao.queryBuilder().where(GoodThingsDao.Properties.ImgUrl
                        .eq(finaUrl), GoodThingsDao.Properties.Username.eq(finaUser))
                        .build();

                List list = query.list();
            }
        });
    }

    // 同步用户名删除
    public void deleteGood(final String str, String finaUser) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                GoodThings goodthings = goodThingsDao.queryBuilder().where(GoodThingsDao.Properties.ImgUrl.eq(str)
                        , GoodThingsDao.Properties.Username.eq("bmobUser.gerUserName()")).build()
                        .unique();

                // 删除数据和更新数据基本相似, 先查询出需要删除的条目, 然后调用goodthingsdao的deleteByKey
                // 将该条目的主键传入即可
                if (goodthings != null) {
                    goodThingsDao.deleteByKey(goodthings.getId());
                }
            }
        });
    }

    //
    public void queryUser(final String finaUrl, final String finaUser, ArrayList<GoodThings> goodThingses) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                Observable.create(new Observable.OnSubscribe<List<GoodThings>>() {
                    @Override
                    public void call(Subscriber<? super List<GoodThings>> subscriber) {
                        query = goodThingsDao.queryBuilder().where(GoodThingsDao.Properties.ImgUrl
                                .eq(finaUrl), GoodThingsDao.Properties.Username.eq(finaUser))
                                .build();
                    }
                })
                        .observeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<List<GoodThings>>() {
                            @Override
                            public void call(List<GoodThings> goodThingses) {
                                // 拿到查询的结果
                                List list = query.list();
                            }
                        });
            }
        });
    }


}
