import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.*;

public class Arbol {
    private Nodo raiz;
    private ArrayList<Nodo> nodos;
    //Contador para generar etiquetas nodos
    private int numNodos;

    //Iinicar arbol vacio
    public Arbol() {
        raiz = null;
        nodos = new ArrayList<>();
        numNodos = 0;
    }

    //Anadir nodo a la posicion que se indica. la posicion es entre 0 y 4
    public void anadirNodo(Nodo nodo, Nodo padre, int posicionHijo) {
        if (padre == null) {
            //Si no hay nodo se tiene el nodo raiz
            if (raiz == null) {
                raiz= nodo;
            } else {
                throw new IllegalArgumentException("La raíz ya existe");
            }
        } else {
            switch (posicionHijo) {
                //Se anade el nodo de acuerdo a la posicion
                case 0:
                    if (padre.izquierda1 == null) {
                        padre.izquierda1 = nodo;
                    } else {
                      throw new IllegalArgumentException("El hijo izquierda1 ya existe");
                    }
                    break;
                case 1:
                    if (padre.izquierda2 == null) {
                        padre.izquierda2 = nodo;
                    } else {
                        throw new IllegalArgumentException("El hijo izquierda2 ya existe");
                    }
                    break;
                case 2:
                    if (padre.centro0 == null) {
                        padre.centro0 = nodo;
                    } else {
                        throw new IllegalArgumentException("El hijo centro0 ya existe");
                    }
                    break;
                case 3:
                    if (padre.derecha2 == null) {
                        padre.derecha2 = nodo;
                    } else {
                        throw new IllegalArgumentException("El hijo derecha2 ya existe");
                    }
                    break;
                case 4:
                    if (padre.derecha1 == null) {
                        padre.derecha1 = nodo;
                    } else {
                        throw new IllegalArgumentException("El hijo derecha1 ya existe");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("La posición del hijo debe estar entre 0 y 4");
            }
        }
        nodos.add(nodo);
    }
    public ArrayList<Nodo> getNodos() {
        return nodos;
    }

    public String bfs() {
        if (raiz == null) return "";

        StringBuilder resultado = new StringBuilder();
        Queue<Nodo> queue = new LinkedList<>();
        queue.add(raiz);

        while (!queue.isEmpty()) {
            Nodo nodo = queue.poll();
            resultado.append(nodo.etiqueta).append(" ");

            // Agregar los hijos en el orden definido
            if (nodo.izquierda1 != null) queue.add(nodo.izquierda1);
            if (nodo.izquierda2 != null) queue.add(nodo.izquierda2);
            if (nodo.centro0 != null) queue.add(nodo.centro0);
            if (nodo.derecha2 != null) queue.add(nodo.derecha2);
            if (nodo.derecha1 != null) queue.add(nodo.derecha1);
        }

        return resultado.toString().trim();
    }

    public String dfs() {
        if (raiz == null) return "";

        StringBuilder resultado = new StringBuilder();
        Stack<Nodo> stack = new Stack<>();
        stack.push(raiz);

        while (!stack.isEmpty()) {
            Nodo nodo = stack.pop();
            resultado.append(nodo.etiqueta).append(" ");

            // Agregar los hijos en orden inverso para que se procesen correctamente en DFS
            if (nodo.derecha1 != null) stack.push(nodo.derecha1);
            if (nodo.derecha2 != null) stack.push(nodo.derecha2);
            if (nodo.centro0 != null) stack.push(nodo.centro0);
            if (nodo.izquierda2 != null) stack.push(nodo.izquierda2);
            if (nodo.izquierda1 != null) stack.push(nodo.izquierda1);
        }

        return resultado.toString().trim();
    }



    public Nodo getRaiz() {
        return raiz;
    }

    public String getEtiquetaNodoSiguiente() {
        return String.valueOf((char) ('A' + numNodos++));
    }

    //Retornar la cadena con los nodos en preorden
    public String preorden() {
        return preordenImpresion(raiz).trim();
    }

    //Anadimos los nodos para la Impresion
    private String preordenImpresion(Nodo nodo) {
        if (nodo == null) return "";
        return nodo.etiqueta + " "
                + preordenImpresion(nodo.izquierda1)
                + preordenImpresion(nodo.izquierda2)
                + preordenImpresion(nodo.centro0)
                + preordenImpresion(nodo.derecha2)
                + preordenImpresion(nodo.derecha1);
    }

    public String inorden() {
        return inordenImpresion(raiz).trim();
    }
    //Anadimos los nodos para la impresion
    private String inordenImpresion(Nodo nodo) {
        if (nodo == null) return "";
        return inordenImpresion(nodo.izquierda1)
                + inordenImpresion(nodo.izquierda2)
                + nodo.etiqueta + " "
                + inordenImpresion(nodo.centro0)
                + inordenImpresion(nodo.derecha2)
                + inordenImpresion(nodo.derecha1);
    }

    public String postorden() {
        return postordenImpresion(raiz).trim();
    }

    //Anadaimos nodos para la impresion
    private String postordenImpresion(Nodo nodo) {
        if (nodo == null) return "";
        return postordenImpresion(nodo.izquierda1)
                + postordenImpresion(nodo.izquierda2)
                + postordenImpresion(nodo.centro0)
                + postordenImpresion(nodo.derecha2)
                + postordenImpresion(nodo.derecha1)
                + nodo.etiqueta + " ";
    }

    //Matriz adyacencia con las conexiones
    public Object[][] getMatrizAdyacencia() {
        int tam = nodos.size();
        Object[][] matriz = new Object[tam][tam];
        Map<String, Integer> etiquetaAIndice = new HashMap<>();

        for (int i = 0; i < tam; i++) {
            etiquetaAIndice.put(nodos.get(i).etiqueta, i);
            for (int j = 0; j < tam; j++) {
                matriz[i][j] = 0;
            }
        }

        for (Nodo nodo : nodos) {
            int desdeIndice = etiquetaAIndice.get(nodo.etiqueta);
            if (nodo.izquierda1 != null) matriz[desdeIndice][etiquetaAIndice.get(nodo.izquierda1.etiqueta)] = 1;
            if (nodo.izquierda2 != null) matriz[desdeIndice][etiquetaAIndice.get(nodo.izquierda2.etiqueta)] = 1;
            if (nodo.centro0 != null) matriz[desdeIndice][etiquetaAIndice.get(nodo.centro0.etiqueta)] = 1;
            if (nodo.derecha2 != null) matriz[desdeIndice][etiquetaAIndice.get(nodo.derecha2.etiqueta)] = 1;
            if (nodo.derecha1 != null) matriz[desdeIndice][etiquetaAIndice.get(nodo.derecha1.etiqueta)] = 1;
        }

        return matriz;
    }
}