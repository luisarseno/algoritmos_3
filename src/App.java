
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Deve ser passado dois parametros");
            System.exit(1);
        }
        String texto = "";
        File arquivo = new File("./" + args[0]);
       // File arquivo = new File("C:\\Users\\Santana\\Documents\\livro.txt");
        Leitor leitor = new Leitor(arquivo);
        GeneralTreeOfObject arvore = leitor.getArvore();
        texto += arvore.printArvoreAsLivro();
        //System.out.println(texto);
        gravarArquivo(args[1], texto);
    }

    public static void gravarArquivo(String arquivo, String texto) {
        Path path1 = Paths.get(arquivo);
        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path1,
                Charset.defaultCharset()))) {
            writer.println(texto);
        } catch (IOException e) {
            System.err.format("Erro de E/S: %s%n", e);
        }
    }


}
