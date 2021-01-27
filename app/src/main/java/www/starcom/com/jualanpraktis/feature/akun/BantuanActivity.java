package www.starcom.com.jualanpraktis.feature.akun;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.starcom.com.jualanpraktis.R;
import www.starcom.com.jualanpraktis.api.ConfigRetrofit;
import www.starcom.com.jualanpraktis.model_retrofit.model_bantuan.DataItem;
import www.starcom.com.jualanpraktis.model_retrofit.model_bantuan.ResponseDataBantuan;

public class BantuanActivity extends AppCompatActivity {

    @BindView(R.id.imgBackBantuan)
    ImageView imgBackBantuan;
    @BindView(R.id.linear_email)
    LinearLayout linearEmail;
    @BindView(R.id.linear_fb)
    LinearLayout linearFb;
    @BindView(R.id.linear_ig)
    LinearLayout linearIg;
    @BindView(R.id.linear_twitter)
    LinearLayout linearTwitter;
    @BindView(R.id.linear_tiktok)
    LinearLayout linearTiktok;
    @BindView(R.id.linear_linkedin)
    LinearLayout linearLinkedin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bantuan);
        ButterKnife.bind(this);

        imgBackBantuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getDataBantuan();
    }

    private void getDataBantuan() {

        ConfigRetrofit.service.dataBantuan().enqueue(new Callback<ResponseDataBantuan>() {
            @Override
            public void onResponse(Call<ResponseDataBantuan> call, Response<ResponseDataBantuan> response) {
                if (response.isSuccessful()){

                    List<DataItem> dataBantuan = response.body().getData();

                    if (!dataBantuan.isEmpty()){
                        String id_email = response.body().getData().get(6).getLink();
                        String email = response.body().getData().get(6).getPlatform();
                        String id_facebook = response.body().getData().get(5).getLink();
                        String facebook = response.body().getData().get(5).getPlatform();

                        linearEmail.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(BantuanActivity.this, email+" link : "+id_email, Toast.LENGTH_SHORT).show();
                            }
                        });

                    }else{
                        Toast.makeText(BantuanActivity.this, "Data Bantuan Kosong", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(BantuanActivity.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataBantuan> call, Throwable t) {
                Toast.makeText(BantuanActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}