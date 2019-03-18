package rupeek.com.rupeek.presenter;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import rupeek.com.rupeek.App;
import rupeek.com.rupeek.BuildConfig;
import rupeek.com.rupeek.entity.LocationEntity;
import rupeek.com.rupeek.entity.TravelmateEntity;
import rupeek.com.rupeek.model.Locations;
import rupeek.com.rupeek.model.Travelmate;
import rupeek.com.rupeek.service.ITravellService;

public class MainPresenter implements IMainPresenter {


    private Context context;
    private IMainView listener;
    private Retrofit retrofit;
    private ITravellService iTravellService;


    public MainPresenter(Context context, IMainView listener) {
        this.context = context;
        this.listener = listener;
        iTravellService = provideClient();
    }

    @Override
    public void loadData() {
        iTravellService.getTravelMate("5c261ccb3000004f0067f6ec")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<Travelmate>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<Travelmate> travelmateResponse) {

                        if (travelmateResponse.isSuccessful() && travelmateResponse.body() != null)
                            saveintodb(travelmateResponse.body());


                    }

                    @Override
                    public void onError(Throwable e) {

                        //when no internet ther these exception will occure
                        if (e instanceof IOException)
                            getDatafromDB();

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void saveintodb(Travelmate data) {

        //clear the previous data
        App.getInstance().getDaoSession().getTravelmateEntityDao().deleteAll();
        App.getInstance().getDaoSession().getLocationEntityDao().deleteAll();

        long id = App.getInstance().getDaoSession().getTravelmateEntityDao().insert(new TravelmateEntity(null, data.getCust_name()));

        //now adding all locations to this customer
        List<LocationEntity> locationdata = new ArrayList<>();
        for (Locations locations : data.getLocations())
            locationdata.add(new LocationEntity(null, id, locations.getPlace(), locations.getUrl(), locations.getDate(), locations.getRate(), locations.getDescription()));

        App.getInstance().getDaoSession().getLocationEntityDao().insertInTx(locationdata);

        listener.sendresult(data);
    }

    private void getDatafromDB() {

        List<TravelmateEntity> travelmateEntities = App.getInstance().getDaoSession().getTravelmateEntityDao().loadAll();
        for (TravelmateEntity travelmateEntity : travelmateEntities) {
            listener.sendresult(new Travelmate(travelmateEntity.getCust_name(), getLocations(App.getInstance().getDaoSession().getTravelmateEntityDao().load(travelmateEntity.getId()).getLocations())));
        }
    }

    private List<Locations> getLocations(List<LocationEntity> locations) {
        List<Locations> locationsList = new ArrayList<>();
        for (LocationEntity locationEntity : locations)
            locationsList.add(new Locations(locationEntity.getPlace(), locationEntity.getUrl(), locationEntity.getDate(), locationEntity.getRate(), locationEntity.getDescription()));
        return locationsList;
    }


    private ITravellService provideClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .client(okHttpClient)
                .build().create(ITravellService.class);

    }


    public interface IMainView {


        public void sendresult(Travelmate travelmate);
    }
}
