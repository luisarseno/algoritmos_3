//package Teste;

import java.util.ArrayList;

public class GeneralTreeOfObject {

    //para printar a arvore
    private static final int CAPITULO = 1;
    private static final int SESSAO = 1 ;


    // Classe interna Node
    private class Node {

        public Node father;
        public Object element;
        public ArrayList<Node> subtrees;
                
        public Node(Object element) {
            father = null;
            this.element = element;
            subtrees = new ArrayList<>();
        }
        
        public Node(Object element, Node father) {
            this.father = father;
            this.element = element;
            subtrees = new ArrayList<>();
        }

        public void addSubtree(Node n) {
            n.father = this;
            subtrees.add(n);
        }

        public boolean removeSubtree(Node n) {
            n.father = null;
            return subtrees.remove(n);
        }

        public Node getSubtree(int i) {
            if ((i < 0) || (i >= subtrees.size())) {
                throw new IndexOutOfBoundsException();
            }
            return subtrees.get(i);
        }

        public int getSubtreesSize() {
            return subtrees.size();
        }
    }

    // Atributos
    private Node root;
    private int count;

    // Metodos
    public GeneralTreeOfObject() {
        root = null;
        count = 0;
    }

    public Object getRoot() {
        if (isEmpty()) {
            throw new EmptyTreeException();
        }
        return root.element;
    }

    public void setRoot(Object element) {
        if (isEmpty()) {
            throw new EmptyTreeException();
        }
        root.element = element;
    }

    public boolean isRoot(Object element) {
        if (root != null) {
            if (root.element.equals(element)) {
                return true;
            }
        }
        return false;
    }
    
//    public static <E> String toStringPreOrder(Object element){
//        String s = element.toString();
//        for
//                
//    } 

    public boolean isEmpty() {
        return (root == null);
    }

    public int size() {
        return count;
    }

    public void clear() {
        root = null;
        count = 0;
    }

    public Object getFaher(Object element) {
        Node n = searchNodeRef(element, root);
        if (n == null || n.father == null) {
            return null;
        } else {
            return n.father.element;
        }
    }

    public boolean contains(Integer element) {
        Node nAux = searchNodeRef(element, root);
        return (nAux != null);
    }

    private Node searchNodeRef(Object element, Node target) {
        Node res = null;
        if (target != null) {
            if (element.equals(target.element)) {
                res = target;
            } else {
                Node aux = null;
                int i = 0;
                while ((aux == null) && (i < target.getSubtreesSize())) {
                    aux = searchNodeRef(element, target.getSubtree(i));
                    i++;
                }
                res = aux;
            }
        }
        return res;
    }

    public boolean add(Object element, Object father) {
        Node n = new Node(element);
        Node nAux = null;
        boolean res = false;
        if (father == null) {   // Insere na raiz 	
            if (root != null) { //Atualiza o pai da raiz
                n.addSubtree(root);
                root.father = n;
            }
            root = n;   //Atualiza a raiz
            res = true;
        } else {        //Insere no meio da Árvore
            nAux = searchNodeRef(father, root);
            if (nAux != null) {
                nAux.addSubtree(n);
                res = true;
            }
        }
        count++;
        return res;
    }


    public ArrayList<Object> positionsPre() {
        ArrayList<Object> lista = new ArrayList<>();
        positionsPreAux(root, lista);
        return lista;
    }

    private void positionsPreAux(Node n, ArrayList<Object> lista) {
        if (n != null) {
            lista.add(n.element);
            for (int i = 0; i < n.getSubtreesSize(); i++) {
                positionsPreAux(n.getSubtree(i), lista);
            }
        }
    }

    private void printArvorePorNode() {
        ArrayList<Node> capitulos = new ArrayList<>();
        for(int i = 0; i < root.getSubtreesSize() ; i++){
            capitulos.add(root.getSubtree(i));
        }
        ArrayList<Node> sessoes = new ArrayList<>();
        System.out.println("-------------------------------------");
        int countNumPagina = 0;
        int capituloFor = 1;
        for (Node capitulo : capitulos){
            System.out.println((countNumPagina+1) +"\t"+ capituloFor+"."+ "\t" +capitulo.element);
            for (int i = 0 ; i < capitulo.getSubtreesSize() ; i++){
                if(countNumPagina == 15){
                    System.out.println("-------------------------------------");
                }
                System.out.println((countNumPagina+1) +"\t"+ capituloFor+"."+(i+1)+ "\t" +capitulo.getSubtree(i).element);
                countNumPagina++;
            }
            capituloFor++;
        }

    }

    public void printArvoreAsLivro(){
        //printa capa
        if(root != null){
            System.out.println("-------------------------------------");
            for (int i = 0 ; i < 15 ; i++){
                if(i == 6){
                    System.out.println((i+1) + "\t\t\t" + root.element);
                } else {
                    System.out.println(i+1);
                }
            }
            System.out.println("------------------------------------- Capa\n");
        }
        printArvorePorNode();
        //começa a printar o livro

    }
    
    public ArrayList<Object> preFix(){
        ArrayList res = new ArrayList();
        positionsPreAux(root, res);
        return res;
    }
    
    public ArrayList<Object> positionsWidth() {
        ArrayList<Object> lista = new ArrayList<>();

        Queue<Node> fila = new Queue<>();
        Node atual;

        if (root != null) {
            fila.enqueue(root);
            while (!fila.isEmpty()) {
                atual = fila.dequeue();
                lista.add(atual.element);
                for (int i = 0; i < atual.getSubtreesSize(); i++) {
                    fila.enqueue(atual.getSubtree(i));
                }
            }
        }
        return lista;
    }

    public int level (Object element) {
        Node n = this.searchNodeRef(element, root);
        if (n==null)
            return -1;
        int cont=0;
        while (n!= root) {
            cont++;
            n = n.father;
        }
        return cont;
    }

}
