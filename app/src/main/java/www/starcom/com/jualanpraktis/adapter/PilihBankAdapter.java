package www.starcom.com.jualanpraktis.adapter;

import android.content.Context;
import android.content.Intent;
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
import www.starcom.com.jualanpraktis.model.ListBank;

public class PilihBankAdapter extends RecyclerView.Adapter<PilihBankAdapter.PilihBankViewHolder> {

    Context context;
    ListBank[] listBank;

    public PilihBankAdapter(Context context, ListBank[] listBank) {
        this.context = context;
        this.listBank = listBank;
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

                Intent intent = new Intent(context, DetailRekeningBankActivity.class);
                intent.putExtra("extraNamaBank", namaBank);
                ((PilihBankDetailRekeningActivity)context).startActivityForResult(intent, 0);

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
