package br.senac.telemedicina.ui.recyclerview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import br.senac.telemedicina.R;
import br.senac.telemedicina.model.Paciente;

public class PacientesAdapter extends RecyclerView.Adapter<PacientesAdapter.ViewHolder> {
    private final List<Paciente> pacientes;

    public PacientesAdapter(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nomeTextView;
        public TextView idadeTextView;
        public TextView pressaoTextView;
        public TextView glicoseTextView;
        public TextView colesterolTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nomeTextView = itemView.findViewById(R.id.nome_paciente_item);
            idadeTextView = itemView.findViewById(R.id.idade_paciente_item);
            pressaoTextView = itemView.findViewById(R.id.pressao_paciente_item);
            glicoseTextView = itemView.findViewById(R.id.glicose_paciente_item);
            colesterolTextView = itemView.findViewById(R.id.colesterol_paciente_item);
        }
    }

    @Override
    public PacientesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_paciente, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Paciente paciente = pacientes.get(position);
        holder.nomeTextView.setText(paciente.getNome());
        holder.idadeTextView.setText("Idade: " + paciente.getIdade());
        holder.pressaoTextView.setText("Pressão arterial: " + paciente.getPressaoArterial());
        holder.glicoseTextView.setText("Glicose: " + paciente.getGlicose() + " mg/dL");
        holder.colesterolTextView.setText("Colesterol: " + paciente.getColesterol() + " mg/dL");
    }

    @Override
    public int getItemCount() {
        return pacientes.size();
    }

    public void atualizarLista(List<Paciente> novosPacientes) {
        pacientes.clear();
        pacientes.addAll(novosPacientes);
        notifyDataSetChanged(); // Notifica que a lista foi alterada
    }
}
