package rupeek.com.rupeek;

import android.app.Application;

import rupeek.com.rupeek.entity.DaoMaster;
import rupeek.com.rupeek.entity.DaoSession;

public class App extends Application {

    private DaoSession mDaoSession;
    private static App instance;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        mDaoSession = new DaoMaster(new DaoMaster.DevOpenHelper(this, "travelmate.db").getWritableDb()).newSession();

    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public static synchronized App getInstance() {
        return instance;
    }


}
