package www.starcom.com.jualanpraktis.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import www.starcom.com.jualanpraktis.R;
import www.starcom.com.jualanpraktis.feature.akun.DetailRekeningBankActivity;
import www.starcom.com.jualanpraktis.feature.akun.PilihBankDetailRekeningActivity;
import www.starcom.com.jualanpraktis.interfaces.PilihBankClickInterface;
import www.starcom.com.jualanpraktis.model.ListBank;

public class PilihBankAdapter extends RecyclerView.Adapter<PilihBankAdapter.PilihBankViewHolder> {

    Context context;
    ListBank[] listBank;
    PilihBankDetailRekeningActivity pilihBankDetailRekeningActivity;
//    private PilihBankClickInterface onItemClickListener;
//    private PilihBankDetailRekeningActivity pilihBankDetailRekeningActivity;


    public PilihBankAdapter(Context context, ListBank[] listBank, PilihBankDetailRekeningActivity pilihBankDetailRekeningActivity) {
        this.context = context;
        this.listBank = listBank;
        this.pilihBankDetailRekeningActivity = pilihBankDetailRekeningActivity;
    }

    @NonNull
    @Override
    public PilihBankViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pilih_bank, parent, false);
        return new PilihBankViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PilihBankViewHolder holder, int position) {

        final String urlImage = listBank[position].getGambar();
        final String namaBank = listBank[position].getNama_bank();

        Glide.with(context)
                .load(urlImage)
                .into(holder.imgBank);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String intentNama = pilihBankDetailRekeningActivity.nama;
                String intentRekening = pilihBankDetailRekeningActivity.rekening;
                Intent intent = new Intent(context, DetailRekeningBankActivity.class);
                intent.putExtra("nama_bank", namaBank);
                intent.putExtra("nama", intentNama);
                intent.putExtra("rekening", intentRekening);
                intent.putExtra(DetailRekeningBankActivity.ExtraNamaBank, namaBank);
//                ((PilihBankDetailRekeningActivity)context).setResult(Activity.RESULT_OK, intent);
//                ((PilihBankDetailRekeningActivity)context).finish();
                ((PilihBankDetailRekeningActivity)context).startActivityForResult(intent, 0);
                ((PilihBankDetailRekeningActivity)context).finish();
                Log.d("PilihBankAdapter", "onClick Data: "+namaBank);
            }
        });

        holder.imgBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

    @Override
    public int getItemCount() {
        return listBank.length;
    }

    public class PilihBankViewHolder extends RecyclerView.ViewHolder {

        ImageView imgBank;

        public PilihBankViewHolder(@NonNull View itemView) {
            super(itemView);

            imgBank = itemView.findViewById(R.id.img_item_pilih_bank);
        }
    }
}
