package www.starcom.com.jualanpraktis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.starcom.com.jualanpraktis.Login.SharedPrefManager;
import www.starcom.com.jualanpraktis.api.ConfigRetrofit;
import www.starcom.com.jualanpraktis.model_retrofit.DataItem;
import www.starcom.com.jualanpraktis.model_retrofit.ResponseDataVideo;

public class WelcomePageActivity extends YouTubeBaseActivity {

    ImageView imgPlay;
    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer.OnInitializedListener onInitializedListener;
    String getVideo;
    CardView cardTontonNanti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        getDataVideo();
        youTubePlayerView = findViewById(R.id.viewYoutubeWelcome);
        imgPlay = findViewById(R.id.img_play_video);
        cardTontonNanti = findViewById(R.id.card_tonton_nanti);

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(getVideo);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerView.initialize("AIzaSyDNd1vGpd_aKAhrQIN6xJ5dBiG0N6yAsN0", onInitializedListener);
                imgPlay.setVisibility(View.GONE);
                Log.d("dataVideo", "onClick: "+getVideo);
            }
        });

        cardTontonNanti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(getApplicationContext(), login.class);
                    intent.putExtra("tab",0);
                    startActivity(intent);
                    finish();
            }
        });
    }

    private void getDataVideo() {

        ConfigRetrofit.service.getDataVideo().enqueue(new Callback<ResponseDataVideo>() {
            @Override
            public void onResponse(Call<ResponseDataVideo> call, Response<ResponseDataVideo> response) {
                if (response.isSuccessful()){

                    List<DataItem> id_video = response.body().getData();

                    if (!id_video.isEmpty()){

                        getVideo = String.valueOf(response.body().getData().get(0).getIdVideo());

                    }else{
                        Toast.makeText(WelcomePageActivity.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(WelcomePageActivity.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDataVideo> call, Throwable t) {
                Toast.makeText(WelcomePageActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}