
package Util;
import java.awt.Color;
import java.awt.Component;
import java.util.Set;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class HoraRenderer extends DefaultTableCellRenderer {
    private Set<Integer> horasOcupadas;

    public HoraRenderer(Set<Integer> horasOcupadas) {
        this.horasOcupadas = horasOcupadas;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {
        
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        try {
            String horaTexto = value.toString();
            int hora = Integer.parseInt(horaTexto.split(":")[0]);

            if (horasOcupadas.contains(hora)) {
                c.setBackground(Color.RED);
                c.setForeground(Color.WHITE);
            } else {
                c.setBackground(Color.WHITE);
                c.setForeground(Color.BLACK);
            }
        } catch (Exception e) {
            c.setBackground(Color.WHITE);
            c.setForeground(Color.BLACK);
        }

        return c;
    }
}
