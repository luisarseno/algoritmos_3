
import java.io.File;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        File arquivo = new File("C:\\Users\\Santana\\Documents\\livro.txt");
        new Leitor(arquivo);
        System.exit(1);
        GeneralTreeOfObject arv = new GeneralTreeOfObject();
        arv.add("Big Java", null);
        arv.add("Introdução", "Big Java");
        arv.add("Conceitos Básicos", "Introdução");
        arv.add("Lorem Ipsum 1 - 3 linhas", "Conceitos Básicos");
        arv.add("Lorem Ipsum 2 - 2 linhas", "Conceitos Básicos");
        arv.add("Preparação do Ambiente", "Introdução");
        arv.add("Lorem Ipsum 1 - 2 linhas", "Preparação do Ambiente");
        arv.add("Requisitos Mínimos", "Preparação do Ambiente");
        arv.add("Lorem Ipsum 1 - 9 linhas", "Requisitos Mínimos");
        arv.add("Instação", "Preparação do Ambiente");
        arv.add("Lorem Ipsum 1 - 2 linhas", "Instalação");
        arv.add("Lorem Ipsum 2 - 4 linhas", "Instalação");
        ArrayList<Object> l = arv.positionsWidth();
        //System.out.println(l);
        System.out.println("Tamanho: " + l.size() + "\n");
        
        l.forEach((item) -> {
            System.out.println(item);
        });
        
    }
    
}
