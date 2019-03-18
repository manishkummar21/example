package rupeek.com.rupeek.service;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rupeek.com.rupeek.model.Travelmate;

public interface ITravellService {

    @GET("{travelID}")
    Observable<Response<Travelmate>> getTravelMate(@Path("travelID") String travelID);


}
