
package Util;

import GUI.frmCitas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.time.LocalDate;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class RenderDia extends DefaultTableCellRenderer{

    private boolean tieneCita(LocalDate fecha) {
    return !CalendarioCitas.obtenerCitasDelDia(fecha).isEmpty();
}

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);

        if (value != null) {
            String s = value.toString();
            int dia = Integer.parseInt(s);
            LocalDate fecha = LocalDate.of(frmCitas.año, frmCitas.mes, dia);

            JLabel diaLabel = new JLabel(String.valueOf(dia));
            diaLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
            diaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(diaLabel);

            Map<String, Integer> resumen = CalendarioCitas.obtenerCitasDelDia(fecha);
            for (Map.Entry<String, Integer> entry : resumen.entrySet()) {
                JLabel lbl = new JLabel(entry.getKey() + ": " + entry.getValue());
                lbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                lbl.setForeground(Color.DARK_GRAY);
                lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
                panel.add(lbl);
            }

            if (Feriados.esFeriado(fecha)) {
                JLabel feriado = new JLabel(Feriados.obtenerNombreFeriado(fecha));
                feriado.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                feriado.setForeground(Color.RED);
                feriado.setAlignmentX(Component.CENTER_ALIGNMENT);
                panel.add(feriado);
                panel.setBackground(new Color(255, 220, 220));
            }
        }

        return panel;
    }

}

