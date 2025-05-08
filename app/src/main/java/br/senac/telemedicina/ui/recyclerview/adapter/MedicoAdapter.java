package br.senac.telemedicina.ui.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.senac.telemedicina.model.Medico;
import br.senac.telemedicina.R;
import java.util.List;

public class MedicoAdapter extends BaseAdapter {
    private Context context;
    private List<Medico> medicos;

    public MedicoAdapter(Context context, List<Medico> medicos) {
        this.context = context;
        this.medicos = medicos;
    }

    @Override
    public int getCount() {
        return medicos.size();
    }

    @Override
    public Object getItem(int position) {
        return medicos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return medicos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Se convertView for null, inflar o layout
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_medico, parent, false); // Certifique-se de ter o layout item_medico.xml
        }

        // Obter a instância do Medico da posição
        Medico medico = medicos.get(position);

        // Referenciar os TextViews e definir seus valores
        TextView nomeTextView = convertView.findViewById(R.id.nome_medico);
        TextView especialidadeTextView = convertView.findViewById(R.id.especialidade_medico);
        TextView crmTextView = convertView.findViewById(R.id.crm_medico);

        nomeTextView.setText(medico.getNome());
        especialidadeTextView.setText(medico.getEspecialidade());
        crmTextView.setText(medico.getCrm());

        return convertView;
    }
}
