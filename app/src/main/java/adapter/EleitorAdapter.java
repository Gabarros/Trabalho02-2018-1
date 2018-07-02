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

import model.Eleitor;

public class EleitorAdapter extends RecyclerView.Adapter {

    private List<Eleitor> eleitores;
    private Context context;
    private static ClickRecyclerViewListener clickRecyclerViewListener;

    public EleitorAdapter(List<Eleitor> eleitores, Context context, ClickRecyclerViewListener clickRecyclerViewListener) {
        this.eleitores = eleitores;
        this.context = context;
        this.clickRecyclerViewListener = clickRecyclerViewListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_eleitor_cv, parent, false);
        EleitorViewHolder eleitorViewHolder = new EleitorViewHolder(view);
        return eleitorViewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        EleitorViewHolder eleitorHolder = (EleitorViewHolder) viewHolder;

        Eleitor eleitor  = this.eleitores.get(position);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");


        eleitorHolder.nomeEleitor.setText(eleitor.getNome());
        eleitorHolder.numeroEleitor.setText(String.valueOf(eleitor.getNumerotitulo()));
        eleitorHolder.zonaEleitor.setText(String.valueOf(eleitor.getZona()));
        eleitorHolder.secaoEleitor.setText(String.valueOf(eleitor.getSecao()));


    }

    @Override
    public int getItemCount() {

        return eleitores.size();
    }


    public class EleitorViewHolder extends RecyclerView.ViewHolder {

        private final TextView nomeEleitor;
        private final TextView numeroEleitor;
        private final TextView zonaEleitor;
        private final TextView secaoEleitor;


        public EleitorViewHolder(View itemView) {
            super(itemView);
            nomeEleitor = (TextView) itemView.findViewById(R.id.tvNomeEleitor);

            numeroEleitor = (TextView) itemView.findViewById(R.id.tvNumeroEleitor);
            zonaEleitor = (TextView) itemView.findViewById(R.id.tvZonaEleitor);
            secaoEleitor = (TextView) itemView.findViewById(R.id.tvSecaoEleitor);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickRecyclerViewListener.onClick(eleitores.get(getLayoutPosition()));

                }
            });


        }
    }
}
