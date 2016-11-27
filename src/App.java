
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
       /*if(args.length != 2){
           System.out.println("Deve ser passado dois parametros");
           System.exit(1);
       }*/
        String texto = "";

        //File arquivo = new File("./"+args[0]);
        File arquivo = new File("C:\\Users\\Santana\\Documents\\livro.txt");
        Leitor leitor =  new Leitor(arquivo);
        GeneralTreeOfObject arvore = leitor.getArvore();
        for(Object o : arvore.positionsPre()){
            texto += o+"\n";
           /* System.out.println(o);*/
        }
        arvore.printArvoreAsLivro();
        System.exit(1);
        System.out.println("Level Fundamentos da linguagem: "+arvore.level("Fundamentos da Linguagem"));
        System.out.println("Level Fontes de Consulta: "+arvore.level("Fontes de Consulta"));
        System.out.println("Level Requisitos Minimos: "+arvore.level("Requisitos Minimos"));
        System.out.println("Level Instalacao: "+arvore.level("Instalacao"));
        System.out.println("Paragrago 9: "+arvore.level(9));
        gravarArquivo(args[1],texto);
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

        System.out.println(arv.preFix());
//        l.forEach((item) -> {
//            System.out.println(item);
//        });
        
    }

    public static void gravarArquivo(String arquivo, String texto){
        Path path1 = Paths.get(arquivo);
        try(PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path1,
                Charset.defaultCharset()))) {
            writer.println(texto);
        }
        catch (IOException e) {
            System.err.format("Erro de E/S: %s%n", e);
        }
    }

    
}
