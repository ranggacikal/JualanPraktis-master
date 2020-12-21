package www.starcom.com.jualanpraktis.api;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import www.starcom.com.jualanpraktis.model.ResponseGetDataBank;

public interface ApiService {

    @GET("list-rekening.php")
    Call<ResponseGetDataBank> getDataBank();

}
