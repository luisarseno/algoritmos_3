//package Teste;

import java.util.ArrayList;

public class GeneralTreeOfObject {

    //para printar a arvore
    private static int NUMERO_LINHAS = 1;
    private static int NUMERO_PAGINAS = 1 ;


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

    private String pritaArvore() {
        String texto = "";
        String sumario = "-------------------------------------\nSUMÁRIO\n";
        ArrayList<Node> capitulos = new ArrayList<>();
        for(int i = 0; i < root.getSubtreesSize() ; i++){
            texto += "-------------------------------------\n";
            if(root.getSubtree(i).element instanceof Integer){
                for(int j = 0 ; j < (Integer) root.getSubtree(i).element ; j++){
                    texto += (NUMERO_LINHAS) +"\t"+"Lorem Ipsum "+(j+1)+"\n";;
                    NUMERO_LINHAS++;
                    texto += this.quebraPagina();
                }
            } else {
                capitulos.add(root.getSubtree(i));
            }
        }
        int capituloFor = 1;
        for (Node capitulo : capitulos){
            texto += (NUMERO_LINHAS) +"\t"+ capituloFor+"."+ "\t" +capitulo.element+"\n";;
            sumario += capituloFor+"."+ "\t" +capitulo.element+"\t\t"+NUMERO_PAGINAS+"\n";;
            NUMERO_LINHAS++;
            texto += this.quebraPagina();
            int numSessao  = 1;
            for (int i = 0 ; i < capitulo.getSubtreesSize() ; i++){
                //printa paragragos e as sessoes de cada capiutlo
                if(capitulo.getSubtree(i).element instanceof Integer){
                    for (int j = 0; j < (Integer) capitulo.getSubtree(i).element ; j++){
                        texto +=(NUMERO_LINHAS) +"\t"+"Lorem Ipsum "+(j+1)+"\n";;
                        NUMERO_LINHAS++;
                        texto += this.quebraPagina();
                    }
                } //fim if paragrafos do capitulo
                else {
                    Node nodeSessao = capitulo.getSubtree(i);
                    texto +=(NUMERO_LINHAS) + "\t" + capituloFor + "." + (numSessao) + "\t" + nodeSessao.element+"\n";;
                    sumario +="\t" + capituloFor + "." + (numSessao) + "\t" + nodeSessao.element+"\t\t"+NUMERO_PAGINAS+"\n";;
                    NUMERO_LINHAS++;
                    texto += this.quebraPagina();
                    int numSubSessao = 1;
                    //printa as subessoes de cada sessao e os paragrafos da sessao
                    for(int j = 0 ; j < nodeSessao.getSubtreesSize() ;  j++){
                        if(nodeSessao.getSubtree(j).element instanceof Integer){
                            for (int k = 0; k < (Integer) nodeSessao.getSubtree(j).element ; k++){
                                texto +=(NUMERO_LINHAS) +"\t"+"Lorem Ipsum "+(k+1)+"\n";;
                                NUMERO_LINHAS++;
                                texto += this.quebraPagina();
                            }
                        }//fim if paragrafos da sessao
                        else {
                            Node nodeSubSessao = nodeSessao.getSubtree(j);
                            texto +=(NUMERO_LINHAS) + "\t" + capituloFor + "." + (numSessao) + "." + numSubSessao +"\t" + nodeSubSessao.element+"\n";;
                            sumario +="\t\t" + capituloFor + "." + (numSessao) + "." + numSubSessao +"\t" + nodeSubSessao.element+"\t\t"+NUMERO_PAGINAS+"\n";;
                            NUMERO_LINHAS++;
                            texto +=this.quebraPagina();
                            //printa os paragrafos da subsessao
                            for(int k = 0 ; k < nodeSubSessao.getSubtreesSize() ; k++){
                                if(nodeSubSessao.getSubtree(k).element instanceof Integer){
                                    for (int l = 0; l < (Integer) nodeSubSessao.getSubtree(k).element ; l++){
                                        texto +=(NUMERO_LINHAS) +"\t"+"Lorem Ipsum "+(l+1)+"\n";;
                                        NUMERO_LINHAS++;
                                        texto += this.quebraPagina();
                                    }
                                } // fim if paragrafos da subsessoes
                            }
                            numSubSessao++;
                        } // fim else subsessoes
                    } // fim for nas sessoes
                    numSessao++;
                } //fim else sessoes do capitulo
            } // fim for no capitulo
            capituloFor++;
        }
        return texto+sumario;
    }

    public String printArvoreAsLivro(){
        //printa capa
        String texto = "";
        if(root != null){
            texto += "-------------------------------------\n";
            for (int i = 0 ; i < 15 ; i++){
                if(i == 6){
                    texto += (i+1) + "\t\t\t" + root.element+"\n";
                } else {
                    texto += (i+1)+"\n";
                }
            }
            texto += "------------------------------------- Capa\n";
        }
        texto += pritaArvore();
        return texto;
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

    private String quebraPagina(){
        String texto = "" ;
        if(NUMERO_LINHAS == 16){
            texto += "------------------------------------- \t\tPag."+NUMERO_PAGINAS+"\n";
            NUMERO_LINHAS = 1;
            NUMERO_PAGINAS++;
        }
        return texto;
    }

}
