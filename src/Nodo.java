public class Nodo {
    int x, y;
    String etiqueta;
    //Adaptamos los nodos para que tengan hasta 5 hijos
    Nodo izquierda1, izquierda2, derecha1, derecha2, centro0;


    //Constructor con las coordenadas, etiqeuta e hijos
    public Nodo(int x, int y, String etiqueta) {
        this.x = x;
        this.y = y;
        this.etiqueta = etiqueta;
        this.izquierda1 = null;
        this.izquierda2 = null;
        this.derecha1 = null;
        this.derecha2 = null;
        this.centro0 = null;
    }

    @Override
    public String toString() {
        return etiqueta + " (" + x + ", " + y + ")";
    }
}