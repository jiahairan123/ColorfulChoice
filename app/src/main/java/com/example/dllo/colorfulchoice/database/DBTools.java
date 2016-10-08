package com.example.dllo.colorfulchoice.database;

import android.util.Log;

import com.example.dllo.colorfulchoice.base.MyApp;

import org.greenrobot.greendao.query.Query;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by mayinling on 16/9/27.
 */
public class DBTools {

    private static String TAG = DBTools.class.getSimpleName();
    private static DBTools instance;
    private DaoSession mDaosession;
    private GoodThingsDao goodThingsDao;
    private ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
    private Query query;
    private static DesignerSignDao designerSignDao;
    private DBVideoViewDao dbVideoViewDao;

    private DBTools() {

    }

    // 单例模式
    public static DBTools getInstance() {
        if (instance == null) {
            synchronized (DBTools.class) {
                if (instance == null) {
                    instance = new DBTools();
                    instance.mDaosession = MyApp.getDaoSession();
                    instance.goodThingsDao = instance.mDaosession.getGoodThingsDao();
                    designerSignDao = instance.mDaosession.getDesignerSignDao();
                    instance.dbVideoViewDao = instance.mDaosession.getDBVideoViewDao();
                }
            }
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


    // 同步用户名查询
    public void queryUser(final String finaUrl, final String finaUser, Action1<List<GoodThings>> action1) {

        Observable.just(finaUrl, finaUrl)
                .flatMap(new Func1<String, Observable<List<GoodThings>>>() {
                    @Override
                    public Observable<List<GoodThings>> call(String s) {
                        query = goodThingsDao.queryBuilder().where(GoodThingsDao.Properties.ImgUrl
                                .eq(finaUrl), GoodThingsDao.Properties.Username.eq(finaUser))
                                .build();
                        List<GoodThings> goodThingses = query.list();
                        return Observable.just(goodThingses);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);
    }

    //designer的收藏

    public void insertSignDesigner(final DesignerSign designerSign) {
        Log.d("Sysout", "diaoyong");
        Observable.create(new Observable.OnSubscribe<DesignerSign>() {
            @Override
            public void call(Subscriber<? super DesignerSign> subscriber) {
                Log.d("Sysout", "Insert");
                designerSignDao.insert(designerSign);
            }
        }).observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DesignerSign>() {
                    @Override
                    public void call(DesignerSign designerSign) {

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }

    //增加视频的收藏
    public void insertVideoView(List<DBVideoView> dbVideoViewList) {
        if (dbVideoViewDao != null) {
            for (int i = 0; i < dbVideoViewList.size(); i++) {
                if (!queryVideoView(dbVideoViewList.get(i))) {
                    dbVideoViewDao.insert(dbVideoViewList.get(i));
                }
            }
        }
    }

    //单个视频收藏的添加
    public void insertVideoView(DBVideoView dbVideoView) {
        if (dbVideoViewDao != null) {
            if (!queryVideoView(dbVideoView)) {
                dbVideoViewDao.insert(dbVideoView);
            }
        }
    }

    //删除视频的收藏
    public void deleteVideoView(DBVideoView dbVideoView) {
        if (dbVideoViewDao != null) {
            dbVideoViewDao.delete(dbVideoView);
        }
    }

    //按用户查询
    public List<DBVideoView> queryVideoView(String userName) {
        List<DBVideoView> dbVideoViewList = null;
        if (dbVideoViewDao != null) {
            dbVideoViewList = dbVideoViewDao.queryBuilder().where(DBVideoViewDao.Properties.UserName.eq(userName)).build().list();
        }
        return dbVideoViewList;
    }

    //收藏时查询
    public boolean queryVideoView(String userName, String itemId) {
        List<DBVideoView> dbVideoViewList = null;
        if (dbVideoViewDao != null) {
            dbVideoViewList = dbVideoViewDao.queryBuilder().where(DBVideoViewDao.Properties.UserName.eq(userName),
                    DBVideoViewDao.Properties.ItemId.eq(itemId)).build().list();
            if (dbVideoViewList.size() > 0) {
                return true;
            }
        }
        return false;
    }

    //保存时对视频数据库去重
    public boolean queryVideoView(DBVideoView dbVideoView) {
        List<DBVideoView> dbVideoViewList;
        if (dbVideoView != null) {
            dbVideoViewList = dbVideoViewDao.queryBuilder().where(DBVideoViewDao.Properties.UserName.eq(dbVideoView.getUserName()),
                    DBVideoViewDao.Properties.ItemId.eq(dbVideoView.getItemId())).build().list();
            if (dbVideoViewList.size() > 0) {
                return true;
            }
        }
        return false;
    }

}







