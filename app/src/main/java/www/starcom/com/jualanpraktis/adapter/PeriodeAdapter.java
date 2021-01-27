package www.starcom.com.jualanpraktis.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import www.starcom.com.jualanpraktis.Login.Pref;
import www.starcom.com.jualanpraktis.R;
import www.starcom.com.jualanpraktis.feature.akun.PenghasilanSayaActivity;
import www.starcom.com.jualanpraktis.feature.akun.PesananSelesaiFragment;
import www.starcom.com.jualanpraktis.interfaces.PilihBankClickInterface;
import www.starcom.com.jualanpraktis.interfaces.PilihPeriodeClickInterface;

public class PeriodeAdapter extends RecyclerView.Adapter<PeriodeAdapter.PeriodeViewHolder> {

    Context context;
    ArrayList<HashMap<String, String>> listPeriode = new ArrayList<>();
    PenghasilanSayaActivity penghasilanSayaActivity;
    private Pref pref;

    private View.OnClickListener onItemClickListener;

    public void setItemClickListener(View.OnClickListener clickListener) {
        onItemClickListener = clickListener;
    }

    public PeriodeAdapter(Context context, ArrayList<HashMap<String, String>> listPeriode, PenghasilanSayaActivity penghasilanSayaActivity) {
        this.context = context;
        this.listPeriode = listPeriode;
        this.penghasilanSayaActivity = penghasilanSayaActivity;
    }

    @NonNull
    @Override
    public PeriodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_periode, parent, false);
        return new PeriodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeriodeViewHolder holder, int position) {
        HashMap<String, String> item = new HashMap<>();
        item = this.listPeriode.get(position);
        pref = new Pref(context.getApplicationContext());

        holder.txtAwal.setText(item.get("tanggal_awal"));
        holder.txtAkhir.setText(item.get("tanggal_akhir"));

        holder.linearPeriode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                penghasilanSayaActivity.tanggalAwal = holder.txtAwal.getText().toString();
                penghasilanSayaActivity.tanggalAkhir = holder.txtAkhir.getText().toString();
                penghasilanSayaActivity.alertDialog.dismiss();
                penghasilanSayaActivity.txtButton.setText(penghasilanSayaActivity.tanggalAwal+" - "+penghasilanSayaActivity.tanggalAkhir);
                String range = penghasilanSayaActivity.tanggalAwal+" - "+penghasilanSayaActivity.tanggalAkhir;
            }
        });



    }

    @Override
    public int getItemCount() {
        return (null != listPeriode ? listPeriode.size() : 0);
    }

    public class PeriodeViewHolder extends RecyclerView.ViewHolder {
        TextView txtAwal, txtAkhir;
        LinearLayout linearPeriode;
        public PeriodeViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAwal = itemView.findViewById(R.id.text_item_tanggal_awal_periode);
            txtAkhir = itemView.findViewById(R.id.text_item_tanggal_akhir_periode);
            linearPeriode = itemView.findViewById(R.id.linear_item_periode);
            itemView.setTag(this);
            itemView.setOnClickListener(onItemClickListener);
        }
    }
}
