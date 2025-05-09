package br.senac.telemedicina.ui.recyclerview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import br.senac.telemedicina.model.Medico;
import br.senac.telemedicina.R;
import java.util.List;

public class MedicoAdapter extends RecyclerView.Adapter<MedicoAdapter.MedicoViewHolder> {
    private List<Medico> medicos;

    public MedicoAdapter(List<Medico> medicos) {
        this.medicos = medicos;
    }

    @NonNull
    @Override
    public MedicoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_medico, parent, false);
        return new MedicoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicoViewHolder holder, int position) {
        Medico medico = medicos.get(position);
        holder.nomeTextView.setText(medico.getNome());
        holder.especialidadeTextView.setText(medico.getEspecialidade());
        holder.crmTextView.setText(medico.getCrm());
    }

    @Override
    public int getItemCount() {
        return medicos.size();
    }

    public static class MedicoViewHolder extends RecyclerView.ViewHolder {
        TextView nomeTextView;
        TextView especialidadeTextView;
        TextView crmTextView;

        public MedicoViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeTextView = itemView.findViewById(R.id.nome_medico);
            especialidadeTextView = itemView.findViewById(R.id.especialidade_medico);
            crmTextView = itemView.findViewById(R.id.crm_medico);
        }
    }
}
