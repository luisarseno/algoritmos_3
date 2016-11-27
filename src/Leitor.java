/**
 * Created by Santana on 11/27/2016.
 */

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by Santana on 11/20/2016.
 */
public class Leitor {

    public Leitor(File file) {
        this.lerArquivo(file);
    }


    public void lerArquivo(File arquivo) throws LeitorException {
        Path path = Paths.get(arquivo.getAbsolutePath());
        System.out.println(path);
        try (BufferedReader br = Files.newBufferedReader(path, Charset.defaultCharset())) {
            String linha = null;
            while ((linha = br.readLine()) != null) {
                String[] separadaPorEspaco = linha.split("\\s+");
                switch (separadaPorEspaco[0]) {
                    case "L":
                        //livro
                        System.out.println("Nome do livro: "+retornaString(separadaPorEspaco));
                        break;
                    case "C":
                        System.out.println("Nome do Capítulo: "+retornaString(separadaPorEspaco));
                        //capitulo
                        break;
                    case "S":
                        System.out.println("Nome do Sessão: "+retornaString(separadaPorEspaco));
                        //subtitulo
                        break;
                    case "SS":
                        System.out.println("Nome do Subsessão: "+retornaString(separadaPorEspaco));
                        //subsecao
                        break;
                    case "P":
                        System.out.println("Paragrafo: "+retornaString(separadaPorEspaco));
                        //paragrafo
                        break;
                    default:
                        throw new LeitorException("Erro ao ler o arquivo");
                }
                System.out.println("\n=============\n");
            }
        } catch (IOException e) {
            System.err.format("Erro de E/S: %s%n", e);
        }
    }

    private String retornaString(String[] separadaPorEspaco){
        String texto = "";
        for (int i = 1; i < separadaPorEspaco.length; i++) {
            texto += separadaPorEspaco[i]+" ";
        }
        return texto;
    }
}
