import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ArbolGrafico extends JPanel {
    private Arbol arbol;

    public ArbolGrafico (Arbol arbol) {
        this.arbol = arbol;
    }

    @Override

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Obtener el tamaño del panel
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Calcular la posición inicial (centrado en el panel)
        int x = panelWidth / 2;
        int y = 50; // Un valor fijo para la raíz del árbol

        // Dimensiones para los nodos hijos
        int dimensionX = panelWidth / 4;
        int dimensionY = 50;

        // Dibujar el árbol
        dibujarArbol(g2d, arbol.getRaiz(),x, y, dimensionX, dimensionY);
    }

    public void dibujarArbol(Graphics2D g2d, Nodo nodo, int x, int y, int dimensionX, int dimensionY) {
        if (nodo != null) {
            int radioNodo = 20; // Tamaño más pequeño
            int textoOffset = 5; // Ajuste para centrar el texto

            // Dibuja el nodo actual
            g2d.fillOval(x - radioNodo / 2, y - radioNodo / 2, radioNodo, radioNodo);
            g2d.drawString(nodo.etiqueta, x - textoOffset, y + textoOffset);

            // Dibujar líneas y nodos hijos
            if (nodo.izquierda1 != null) {
                g2d.drawLine(x, y, x - dimensionX, y + dimensionY);
                dibujarArbol(g2d, nodo.izquierda1, x - dimensionX, y + dimensionY, dimensionX / 2, dimensionY);
            }
            if (nodo.izquierda2 != null) {
                g2d.drawLine(x, y, x - dimensionX / 2, y + dimensionY);
                dibujarArbol(g2d, nodo.izquierda2, x - dimensionX / 2, y + dimensionY, dimensionX / 2, dimensionY);
            }
            if (nodo.centro0 != null) {
                g2d.drawLine(x, y, x, y + dimensionY);
                dibujarArbol(g2d, nodo.centro0, x, y + dimensionY, dimensionX / 2, dimensionY);
            }
            if (nodo.derecha2 != null) {
                g2d.drawLine(x, y, x + dimensionX / 2, y + dimensionY);
                dibujarArbol(g2d, nodo.derecha2, x + dimensionX / 2, y + dimensionY, dimensionX / 2, dimensionY);
            }
            if (nodo.derecha1 != null) {
                g2d.drawLine(x, y, x + dimensionX, y + dimensionY);
                dibujarArbol(g2d, nodo.derecha1, x + dimensionX, y + dimensionY, dimensionX / 2, dimensionY);
            }
        }
    }
}
