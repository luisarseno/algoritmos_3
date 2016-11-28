//package Teste;

import java.util.ArrayList;

public class GeneralTreeOfObject {

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
    private int numCapitulos;
    private int numSecoes;
    private int numSubSecoes;
    private int numParagrafo;
    //para printar a arvore
    private static int NUMERO_LINHAS = 1;
    private static int NUMERO_PAGINAS = 1 ;


    // Metodos
    public GeneralTreeOfObject() {
        root = null;
        count = 0;
        numCapitulos = 0;
        numSecoes = 0;
        numSubSecoes = 0;
        numParagrafo =0 ;
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

    private String printaArvore() {
        String texto = "";
        String sumario = "-------------------------------------\nSUMÁRIO\n";
        ArrayList<Node> capitulos = new ArrayList<>();
        for(int i = 0; i < root.getSubtreesSize() ; i++){
            if(root.getSubtree(i).element instanceof Integer){
                for(int j = 0 ; j < (Integer) root.getSubtree(i).element ; j++){
                    texto += (NUMERO_LINHAS) +"\t"+"Lorem Ipsum "+(j+1)+"\n";
                    numParagrafo++;
                    NUMERO_LINHAS++;
                    texto += this.quebraPagina(false);
                }
            } else {
                capitulos.add(root.getSubtree(i));
            }
        }
        for (Node capitulo : capitulos){
            numCapitulos++;
            if(NUMERO_LINHAS != 1){
                texto += this.quebraPagina(true);
            }
            texto += (NUMERO_LINHAS) +"\t"+ numCapitulos+"."+ "\t" +capitulo.element+"\n";;
            sumario += numCapitulos+"."+ "\t" +capitulo.element+"\t\t"+NUMERO_PAGINAS+"\n";;
            NUMERO_LINHAS++;
            for (int i = 0 ; i < capitulo.getSubtreesSize() ; i++){
                numSecoes++;
                //printa paragragos e as sessoes de cada capiutlo
                if(capitulo.getSubtree(i).element instanceof Integer){
                    for (int j = 0; j < (Integer) capitulo.getSubtree(i).element ; j++){
                        texto +=(NUMERO_LINHAS) +"\t"+"Lorem Ipsum "+(j+1)+"\n";
                        numParagrafo++;
                        NUMERO_LINHAS++;
                        texto += this.quebraPagina(false);
                    }
                } //fim if paragrafos do capitulo
                else {
                    Node nodeSessao = capitulo.getSubtree(i);
                    texto +=(NUMERO_LINHAS) + "\t" + numCapitulos + "." + (numSecoes) + "\t" + nodeSessao.element+"\n";;
                    sumario +="\t" + numCapitulos + "." + (numSecoes) + "\t" + nodeSessao.element+"\t\t"+NUMERO_PAGINAS+"\n";;
                    NUMERO_LINHAS++;
                    texto += this.quebraPagina(false);
                    //printa as subessoes de cada sessao e os paragrafos da sessao
                    for(int j = 0 ; j < nodeSessao.getSubtreesSize() ;  j++){
                        if(nodeSessao.getSubtree(j).element instanceof Integer){
                            for (int k = 0; k < (Integer) nodeSessao.getSubtree(j).element ; k++){
                                texto +=(NUMERO_LINHAS) +"\t"+"Lorem Ipsum "+(k+1)+"\n";
                                numParagrafo++;
                                NUMERO_LINHAS++;
                                texto += this.quebraPagina(false);
                            }
                        }//fim if paragrafos da sessao
                        else {
                            numSubSecoes++;
                            Node nodeSubSessao = nodeSessao.getSubtree(j);
                            texto +=(NUMERO_LINHAS) + "\t" + numCapitulos + "." + (numSecoes) + "." + numSubSecoes +"\t" + nodeSubSessao.element+"\n";;
                            sumario +="\t\t" + numCapitulos + "." + (numSecoes) + "." + numSubSecoes +"\t" + nodeSubSessao.element+"\t\t"+NUMERO_PAGINAS+"\n";;
                            NUMERO_LINHAS++;
                            texto +=this.quebraPagina(false);
                            //printa os paragrafos da subsessao
                            for(int k = 0 ; k < nodeSubSessao.getSubtreesSize() ; k++){
                                if(nodeSubSessao.getSubtree(k).element instanceof Integer){
                                    for (int l = 0; l < (Integer) nodeSubSessao.getSubtree(k).element ; l++){
                                        texto +=(NUMERO_LINHAS) +"\t"+"Lorem Ipsum "+(l+1)+"\n";
                                        numParagrafo++;
                                        NUMERO_LINHAS++;
                                        texto += this.quebraPagina(false);
                                    }
                                } // fim if paragrafos da subsessoes
                            }//fim for paragrafos subsessoes
                        } // fim else subsessoes
                    } // fim for nas sessoes
                } //fim else sessoes do capitulo
            } // fim for no capitulo
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
        texto += printaArvore();
        return texto;
        //começa a printar o livro

    }

    private String quebraPagina(Boolean force){
        String texto = "" ;
        if(NUMERO_LINHAS == 16){
            texto += "------------------------------------- \t\tPag."+NUMERO_PAGINAS+"\n";
            NUMERO_LINHAS = 1;
            NUMERO_PAGINAS++;
        }
        if(force){
            texto += "------------------------------------- \t\tPag."+NUMERO_PAGINAS+"\n";
            NUMERO_LINHAS = 1;
            NUMERO_PAGINAS++;
        }
        return texto;
    }

    public int getNumCapitulos() {
        return numCapitulos;
    }

    public int getNumSecoes() {
        return numSecoes;
    }

    public int getNumParagrafo() {
        return numParagrafo;
    }

    public int getNumSubsessoes() {
        return numSubSecoes;
    }
}
