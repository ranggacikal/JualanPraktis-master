package www.starcom.com.jualanpraktis.api;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import www.starcom.com.jualanpraktis.model.ResponseGetDataBank;
import www.starcom.com.jualanpraktis.model_retrofit.ResponseDataFavorite;
import www.starcom.com.jualanpraktis.model_retrofit.ResponseDataVideo;
import www.starcom.com.jualanpraktis.model_retrofit.ResponseRegister;
import www.starcom.com.jualanpraktis.model_retrofit.ResponseUpdateRekening;

public interface ApiService {

    @GET("list-rekening.php")
    Call<ResponseGetDataBank> getDataBank();

    @FormUrlEncoded
    @POST("list-favorit.php")
    Call<ResponseDataFavorite> getDataFavorite(@Field("customer") String customer);

    @GET("list-panduan.php")
    Call<ResponseDataVideo> getDataVideo();

    @FormUrlEncoded
    @POST("signup.php")
    Call<ResponseRegister> register(@Field("email") String email,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("update_rekening.php")
    Call<ResponseUpdateRekening> updateRekening(@Field("id_member") String id_member,
                                                @Field("atas_nama") String atas_nama,
                                                @Field("no_rek") String no_rek,
                                                @Field("nama_bank") String nama_bank);

}
