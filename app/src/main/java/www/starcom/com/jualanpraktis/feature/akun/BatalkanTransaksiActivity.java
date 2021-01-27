package www.starcom.com.jualanpraktis.feature.akun;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.github.dhaval2404.imagepicker.ImagePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import www.starcom.com.jualanpraktis.Login.SharedPrefManager;
import www.starcom.com.jualanpraktis.Login.loginuser;
import www.starcom.com.jualanpraktis.R;
import www.starcom.com.jualanpraktis.adapter.BatalkanTransaksiAdapter;
import www.starcom.com.jualanpraktis.adapter.ProdukRincianTransaksiAdapter;

public class BatalkanTransaksiActivity extends AppCompatActivity {

    TextView txtId, txtTanggal, txtStatus;
    RecyclerView rvProdukBatal;
    EditText edtAlasan;
    LinearLayout linearTambahMedia1, linearTambahMedia2, linearTambahMedia3, linearAjukanPembatalan;
    ImageView imgBatal1, imgBatal2, imgBatal3;
    ShimmerFrameLayout shimmerProdukBatal;
    loginuser user;

    ArrayList<HashMap<String, String>> listProdukBatal = new ArrayList<>();

    private static final int IMG_REQUEST = 1;
    private static final int IMG_REQUEST2 = 2;
    private static final int IMG_REQUEST3 = 3;
    private static final int IMG_REQUEST4  = 4;

    private String PicturePath1, PicturePath2, PicturePath3;

    String nama_file1, nama_file2, nama_file3;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batalkan_transaksi);

        AndroidNetworking.initialize(getApplicationContext());
        user = SharedPrefManager.getInstance(BatalkanTransaksiActivity.this).getUser();

        txtId = findViewById(R.id.text_id_batalkan_transaksi);
        txtTanggal = findViewById(R.id.text_tanggal_batalkan_transaksi);
        txtStatus = findViewById(R.id.text_status_batalkan_transaksi);
        rvProdukBatal = findViewById(R.id.rv_list_produk_batalkan_transaksi);
        edtAlasan = findViewById(R.id.edt_alasan_pembatalan);
        linearAjukanPembatalan = findViewById(R.id.linear_ajukan_batal);
        linearTambahMedia1 = findViewById(R.id.tambah_media_batal1);
        linearTambahMedia2 = findViewById(R.id.tambah_media_batal2);
        linearTambahMedia3 = findViewById(R.id.tambah_media_batal3);
        imgBatal1 = findViewById(R.id.img_batal_transaksi1);
        imgBatal2 = findViewById(R.id.img_batal_transaksi2);
        imgBatal3 = findViewById(R.id.img_batal_transaksi3);
        shimmerProdukBatal = findViewById(R.id.shimmer_batalkan_transaksi);

        rvProdukBatal.setHasFixedSize(true);
        rvProdukBatal.setLayoutManager(new LinearLayoutManager(BatalkanTransaksiActivity.this));

        txtId.setText(getIntent().getStringExtra("id_transaksi"));
        txtTanggal.setText(getIntent().getStringExtra("tanggal"));
        txtStatus.setText(getIntent().getStringExtra("status"));

        linearTambahMedia1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(BatalkanTransaksiActivity.this)
                        .crop(340,340)
                        .compress(512)
                        .start(0);
            }
        });

        linearTambahMedia2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(BatalkanTransaksiActivity.this)
                        .crop(340,340)
                        .compress(512)
                        .start(1);
            }
        });

        linearTambahMedia3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(BatalkanTransaksiActivity.this)
                        .crop(340,340)
                        .compress(512)
                        .start(2);
            }
        });

        linearAjukanPembatalan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajukanBatalkanPesanan();
            }
        });

        loadBatalkanProduk();

    }

    private void ajukanBatalkanPesanan() {

        progressDialog = new ProgressDialog(BatalkanTransaksiActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Mengajukan Pembatalan");
        progressDialog.show();

        HashMap<String, String> params = new HashMap<>();
        params.put("id_member", user.getId());
        params.put("id_transaksi", txtId.getText().toString());
        params.put("alasan", edtAlasan.getText().toString());
        //params.put("password2",picturePath);

        try {
            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .build();


            AndroidNetworking.upload("https://jualanpraktis.net/android/batalkan_pesanan.php")
                    .addMultipartParameter(params)
                    .addMultipartFile("file1", new File(PicturePath1))
                    .addMultipartFile("file2", new File(PicturePath2))
                    .addMultipartFile("file3", new File(PicturePath3))
                    .setTag(BatalkanTransaksiActivity.this)
                    .setPriority(Priority.HIGH)
                    .setOkHttpClient(okHttpClient)
                    .build()
                    .setUploadProgressListener(new UploadProgressListener() {
                        @Override
                        public void onProgress(long bytesUploaded, long totalBytes) {

                        }
                    })
                    .getAsString(new StringRequestListener() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            if (response.contains("Data Berhasil Dikirim")) {
                                Toast.makeText(BatalkanTransaksiActivity.this,
                                        "Berhasil Kirim Ajukan Pembatalan", Toast.LENGTH_LONG).show();
                                finish();
                            } else {
                                Toast.makeText(BatalkanTransaksiActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
//                        progressDialog.dismiss();
                            progressDialog.dismiss();
                            Toast.makeText(BatalkanTransaksiActivity.this, anError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }catch (NullPointerException e){
            //kalau kosong
            e.printStackTrace();
            Toast.makeText(BatalkanTransaksiActivity.this,
                    "Upload dulu Imagenya, tekan di bagian gambar.", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        try {
            if (resultCode==RESULT_OK && requestCode == 0){


                    Uri uri1 = data.getData();
                    nama_file1 = uri1.getLastPathSegment();
                    linearTambahMedia1.setVisibility(View.GONE);
                    imgBatal1.setVisibility(View.VISIBLE);
                    imgBatal1.setImageURI(uri1);
                    PicturePath1 = uri1.getPath();

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            if (resultCode==RESULT_OK && requestCode == 1){


                Uri uri2 = data.getData();
                nama_file2 = uri2.getLastPathSegment();
                linearTambahMedia2.setVisibility(View.GONE);
                imgBatal2.setVisibility(View.VISIBLE);
                imgBatal2.setImageURI(uri2);
                PicturePath2 = uri2.getPath();

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            if (resultCode==RESULT_OK && requestCode == 2){


                Uri uri3 = data.getData();
                nama_file3 = uri3.getLastPathSegment();
                linearTambahMedia3.setVisibility(View.GONE);
                imgBatal3.setVisibility(View.VISIBLE);
                imgBatal3.setImageURI(uri3);
                PicturePath3 = uri3.getPath();

            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private void loadBatalkanProduk() {

        rvProdukBatal.setVisibility(View.GONE);
        shimmerProdukBatal.setVisibility(View.VISIBLE);
        shimmerProdukBatal.startShimmerAnimation();

        String url = "https://jualanpraktis.net/android/detail_pesanan.php";

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();

        AndroidNetworking.post(url)
                .addBodyParameter("id_transaksi", getIntent().getStringExtra("id_transaksi"))
                .setTag(BatalkanTransaksiActivity.this)
                .setPriority(Priority.MEDIUM)
                .setOkHttpClient(okHttpClient)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        shimmerProdukBatal.stopShimmerAnimation();
                        shimmerProdukBatal.setVisibility(View.GONE);

                        listProdukBatal.clear();
                        try {

                            JSONArray array = response.getJSONArray("data_produk");
                            for (int i = 0;i<array.length();i++){
                                JSONObject jsonObject = array.getJSONObject(i);
                                HashMap<String,String> data = new HashMap<>();
                                data.put("nama_produk",jsonObject.getString("nama_produk"));
                                data.put("gambar",jsonObject.getString("image_o"));
                                data.put("nomor",jsonObject.getString("nomor"));
                                data.put("variasi",jsonObject.getString("ket2"));
                                data.put("harga_produk",jsonObject.getString("harga_item"));
                                data.put("harga_jual",jsonObject.getString("harga_jual"));
                                data.put("harga_produk2",jsonObject.getString("harga_item2"));
                                data.put("harga_jual2",jsonObject.getString("harga_jual2"));
                                data.put("jumlah",jsonObject.getString("jumlah"));
                                data.put("id_member",jsonObject.getString("id_member"));
                                data.put("untung1",jsonObject.getString("untung1"));
                                data.put("untung2",jsonObject.getString("untung2"));

                                listProdukBatal.add(data);
                                rvProdukBatal.setVisibility(View.VISIBLE);
                                BatalkanTransaksiAdapter batalkanAdapter = new BatalkanTransaksiAdapter(BatalkanTransaksiActivity.this, listProdukBatal);
                                rvProdukBatal.setAdapter(batalkanAdapter);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        shimmerProdukBatal.stopShimmerAnimation();
                        shimmerProdukBatal.setVisibility(View.GONE);
                        listProdukBatal.clear();

                        if (anError.getErrorCode() != 0) {
                            // received error from server
                            // error.getErrorCode() - the error code from server
                            // error.getErrorBody() - the error body from server
                            // error.getErrorDetail() - just an error detail

                            // get parsed error object (If ApiError is your class)
                            Toast.makeText(BatalkanTransaksiActivity.this, "Gagal mendapatkan data.", Toast.LENGTH_SHORT).show();
                        } else {
                            // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                            if (anError.getErrorDetail().equals("connectionError")){
                                Toast.makeText(BatalkanTransaksiActivity.this, "Tidak ada koneksi internet.", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(BatalkanTransaksiActivity.this, "Gagal mendapatkan data.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

    }
}