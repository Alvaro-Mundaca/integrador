
package Util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import javax.swing.*;

public class EstilosHelper {

    public static void aplicarEstiloAmarillo(Component componente) {
        Color amarillo = new Color(255, 204, 0);

        if (componente instanceof JComboBox<?> comboBox) {
            comboBox.setBackground(amarillo);
            comboBox.setForeground(Color.BLACK);

            Component editor = comboBox.getEditor().getEditorComponent();
            if (editor instanceof JTextField textField) {
                textField.setBackground(amarillo);
                textField.setForeground(Color.BLACK);
            }
        } else if (componente instanceof JSpinner spinner) {
            spinner.setBackground(amarillo);
            spinner.setForeground(Color.BLACK);

            for (Component c : spinner.getComponents()) {
                if (c instanceof JButton boton) {
                    boton.setBackground(amarillo);
                    boton.setForeground(Color.BLACK);
                    boton.setFocusPainted(false);
                    boton.setBorder(BorderFactory.createEmptyBorder());
                } else if (c instanceof JTextField tf) {
                    tf.setBackground(amarillo);
                    tf.setForeground(Color.BLACK);
                    tf.setEditable(false);
                    tf.setFocusable(false);
                    tf.setBorder(null);
                }
            }
        } else if (componente instanceof JButton boton) {
            boton.setBackground(amarillo);
            boton.setForeground(Color.BLACK);
        }

        if (componente instanceof Container contenedor) {
            for (Component hijo : contenedor.getComponents()) {
                aplicarEstiloAmarillo(hijo);
            }
        }
    }
}

