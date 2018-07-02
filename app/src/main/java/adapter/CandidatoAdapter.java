package adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gabriel.trabalho2.R;

import java.text.SimpleDateFormat;
import java.util.List;

import model.Candidato;

public class CandidatoAdapter extends RecyclerView.Adapter{

    private List<Candidato> candidatos;
    private Context context;
    private static ClickRecyclerViewListener clickRecyclerViewListener;

    public CandidatoAdapter(List<Candidato> candidatos, Context context, ClickRecyclerViewListener clickRecyclerViewListener) {
        this.candidatos = candidatos;
        this.context = context;
        this.clickRecyclerViewListener = clickRecyclerViewListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_candidato_cv, parent, false);
        CandidatoAdapter.CandidatoViewHolder candidatoViewHolder = new CandidatoAdapter.CandidatoViewHolder(view);
        return candidatoViewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        CandidatoAdapter.CandidatoViewHolder candidatoHolder = (CandidatoAdapter.CandidatoViewHolder) viewHolder;

        Candidato candidato  = this.candidatos.get(position);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");


        candidatoHolder.nomeCandidato.setText(candidato.getNome());
        candidatoHolder.partidoCandidato.setText(candidato.getnomePartido());
        candidatoHolder.numeroCandidato.setText(String.valueOf(candidato.getNumeroUrna()));
        candidatoHolder.cargoCandidato.setText(candidato.getCargo());

    }

    @Override
    public int getItemCount() {

        return candidatos.size();
    }


    public class CandidatoViewHolder extends RecyclerView.ViewHolder {

        private final TextView nomeCandidato;
        private final TextView partidoCandidato;
        private final TextView numeroCandidato;
        private final TextView cargoCandidato;


        public CandidatoViewHolder(View itemView) {
            super(itemView);

            nomeCandidato = (TextView) itemView.findViewById(R.id.tvNomeCandidato);
            partidoCandidato = (TextView) itemView.findViewById(R.id.tvPartido);
            numeroCandidato = (TextView) itemView.findViewById(R.id.tvNumeroCandidato);
            cargoCandidato = (TextView) itemView.findViewById(R.id.tvCargoCandidato);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickRecyclerViewListener.onClick(candidatos.get(getLayoutPosition()));

                }
            });


        }
    }
}


