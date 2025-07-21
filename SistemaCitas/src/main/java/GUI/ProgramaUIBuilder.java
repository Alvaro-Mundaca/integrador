
package GUI;

import Model.MetaProgramaDTO;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProgramaUIBuilder {

    public static JPanel generarPanelMetas(List<MetaProgramaDTO> lista) {
    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    panel.setBackground(Color.WHITE);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridy = 0;

    Font fuenteGrande = new Font("Segoe UI", Font.BOLD, 20);  // Fuente grande

    for (MetaProgramaDTO dto : lista) {
        // Nombre del programa
        gbc.gridx = 0;
        gbc.weightx = 0.2;
        JLabel lblInicio = new JLabel(dto.getPrograma());
        lblInicio.setFont(fuenteGrande);
        panel.add(lblInicio, gbc);

        // Barra de progreso con porcentaje
        gbc.gridx = 1;
        gbc.weightx = 0.6;
        JProgressBar barra = new JProgressBar(0, dto.getMetaAnual());
        barra.setValue(dto.getProgreso());
        barra.setStringPainted(true);
        barra.setString(String.format("%.1f%%", dto.getPorcentaje())); // Porcentaje como texto
        barra.setForeground(colorSegunPrograma(dto.getPrograma()));
        barra.setFont(fuenteGrande);
        panel.add(barra, gbc);

        // Progreso / Meta al final
        gbc.gridx = 2;
        gbc.weightx = 0.2;
        JLabel lblFinal = new JLabel(dto.getProgreso() + " / " + dto.getMetaAnual());
        lblFinal.setFont(fuenteGrande);
        lblFinal.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(lblFinal, gbc);

        gbc.gridy++;
    }

    panel.setPreferredSize(new Dimension(900, lista.size() * 60));
    return panel;
}

    private static Color colorSegunPrograma(String nombre) {
        return switch (nombre.toUpperCase()) {
            case "PAP" -> new Color(102, 102, 255);        // Azul
            case "IVA" -> new Color(255, 0, 51);           // Rojo
            case "VPH" -> new Color(255, 204, 0);          // Amarillo
            case "CONSEJERIA" -> new Color(100, 255, 100);     // Verde fosforescente
            case "MAMAS" -> new Color(153, 0, 153);   // Púrpura
            default -> Color.GRAY;
        };
    }
}


