package br.senac.telemedicina.ui.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import br.senac.telemedicina.R;
import br.senac.telemedicina.model.Consulta;

public class ConsultaAdapter extends BaseAdapter {
    private Context context;
    private List<Consulta> consultas;

    public ConsultaAdapter(Context context, List<Consulta> consultas)  {
        this.context = context;
        this. consultas = consultas;
    }

    @Override
    public int getCount() {
        return consultas.size();
    }

    @Override
    public Object getItem(int position) {
        return consultas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return consultas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Infla o layout do item se for nulo
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_consulta, parent, false);
        }

        // Obtém o objeto Consulta atual
        Consulta consulta = consultas.get(position);

        // Encontra as views dentro do layout do item
        TextView textViewPacienteId = convertView.findViewById(R.id.textViewPacienteId);
        TextView textViewMedicoId = convertView.findViewById(R.id.textViewMedicoId);
        TextView textViewDataConsulta = convertView.findViewById(R.id.textViewDataConsulta);
        TextView textViewDiagnostico = convertView.findViewById(R.id.textViewDiagnostico);
        TextView textViewPrescricao = convertView.findViewById(R.id.textViewPrescricao);

        // Define os dados nas views
        textViewPacienteId.setText(String.valueOf(consulta.getPacienteId()));
        textViewMedicoId.setText(String.valueOf(consulta.getMedicoId()));
        textViewDataConsulta.setText(consulta.getDataConsulta());
        textViewDiagnostico.setText(consulta.getDiagnostico());
        textViewPrescricao.setText(consulta.getPrescricao());

        // Retorna a view populada
        return convertView;
    }
}
