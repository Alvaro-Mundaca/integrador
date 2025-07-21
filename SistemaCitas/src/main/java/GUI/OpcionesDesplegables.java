
package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.fluentui.FluentUiFilledAL;
import org.kordamp.ikonli.medicons.Medicons;
import org.kordamp.ikonli.swing.FontIcon;


public class OpcionesDesplegables {

    private static int alto, ancho;
    
    public static void capturarDimension(){
        alto = frmHome.jpOpciones.getHeight();
        ancho = frmHome.jpOpciones.getWidth();
    }
    
    public static void minimizarOpciones(){
        frmHome.btnCitas.setText("");
        frmHome.btnInicio.setText("");
        frmHome.btnObstetras.setText("");
        frmHome.btnPacientes.setText("");
        frmHome.btnProgramas.setText("");
        frmHome.btnReportes.setText("");
        
        capturarDimension();
        
        frmHome.jpOpciones.setPreferredSize(new Dimension(50, alto));
        frmHome.jpOpciones.revalidate(); // actualiza el layout
        frmHome.jpOpciones.repaint();    // redibuja el panel


        frmHome.lblMinsa.setText("");

        FontIcon icon = FontIcon.of(FluentUiFilledAL.ARROW_FIT_WIDTH_24,32,Color.WHITE);
        frmHome.lblMinsa.setIcon(icon);
    }
    
    public static void maximizarOpciones(){

        frmHome.btnCitas.setText("Citas");
        frmHome.btnInicio.setText("Inicio");
        frmHome.btnObstetras.setText("Obstetras");
        frmHome.btnPacientes.setText("Pacientes");
        frmHome.btnProgramas.setText("Programas");
        frmHome.btnReportes.setText("Reportes");
        
        frmHome.jpOpciones.setPreferredSize(new Dimension(260, alto));
        frmHome.jpOpciones.revalidate(); // actualiza el layout
        frmHome.jpOpciones.repaint();    // redibuja el panel


        frmHome.lblMinsa.setText("Sistema de Citas");
        frmHome.ConfigurarLogoMinsa();
    
    }
    
    public static void animarMostrar() {
        Timer timer = new Timer(10, null);
        timer.addActionListener(new ActionListener() {
            int anchoActual = frmHome.jpOpciones.getWidth();
            final int anchoFinal = 260; // ← forzado para pruebas
            final int incremento = 25;

            @Override
            public void actionPerformed(ActionEvent e) {
                anchoActual += incremento;

                if (anchoActual >= anchoFinal) {
                    frmHome.jpOpciones.setPreferredSize(new Dimension(anchoFinal, alto));
                    frmHome.jpOpciones.revalidate();
                    frmHome.jpOpciones.repaint();
                    ((Timer) e.getSource()).stop();
                    maximizarOpciones();
                    return;
                }

                frmHome.jpOpciones.setPreferredSize(new Dimension(anchoActual, alto));
                frmHome.jpOpciones.revalidate();
                frmHome.jpOpciones.repaint();
            }
        });

        timer.start();
    }
    
    public static void animarOcultar() {
        Timer timer = new Timer(10, null);
        timer.addActionListener(new ActionListener() {
            int paso = 0;
            int anchoInicial = frmHome.jpOpciones.getWidth();
            int decremento = 25;

            @Override
            public void actionPerformed(ActionEvent e) {
                int nuevoAncho = anchoInicial - paso * decremento;
                minimizarOpciones();
                if (nuevoAncho <= 50) {
                    frmHome.jpOpciones.setPreferredSize(new Dimension(50, alto));
                    frmHome.jpOpciones.revalidate();
                    frmHome.jpOpciones.repaint();
                    timer.stop();
                    return;
                }

                frmHome.jpOpciones.setPreferredSize(new Dimension(nuevoAncho, alto));
                frmHome.jpOpciones.revalidate();
                frmHome.jpOpciones.repaint();
                paso++;
            }
        });

        timer.start();
    }

}
