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

    private GeneralTreeOfObject arvore;
    private String lastCapitulo;
    private String lastSessao;

    private String lastElement;
    private Integer numParagrafo;
    private String nomeLivro;

    public Leitor(File file) {
        arvore = new GeneralTreeOfObject();
        try {
            this.lerArquivo(file);
        } catch (LeitorException e){
            System.out.println(e.getMessage());
        }
    }

    public GeneralTreeOfObject getArvore() {
        return arvore;
    }

    public void lerArquivo(File arquivo) throws LeitorException {
        Path path = Paths.get(arquivo.getAbsolutePath());
        System.out.println(path);
        try (BufferedReader br = Files.newBufferedReader(path, Charset.defaultCharset())) {
            String linha = br.readLine();
            //caso primeira linha não for livro, lança exceção
            String[] primeiraLinha = linha.split("\\s+");
            if(!primeiraLinha[0].equals("L")){
                throw new LeitorException("Primeira linha precisar ser o nome do livro!");
            } else {
                System.out.println("Nome do livro: "+retornaString(primeiraLinha).trim());
                setNomeLivro(retornaString(primeiraLinha).trim());
                arvore.add(getNomeLivro(),null);
            }
            while ((linha = br.readLine()) != null) {
                String[] separadaPorEspaco = linha.split("\\s+");
                switch (separadaPorEspaco[0]) {
                    case "C":
                        //capitulo
                        System.out.println("Nome do Capítulo: "+retornaString(separadaPorEspaco));
                        arvore.add(retornaString(separadaPorEspaco).trim(),getNomeLivro());
                        setLastElement(retornaString(separadaPorEspaco).trim());
                        setLastCapitulo(retornaString(separadaPorEspaco).trim());
                        break;
                    case "S":
                        //subtitulo
                        System.out.println("Nome do Sessão: "+retornaString(separadaPorEspaco));
                        arvore.add(retornaString(separadaPorEspaco).trim(), getLastCapitulo());
                        setLastSessao(retornaString(separadaPorEspaco).trim());
                        setLastElement(retornaString(separadaPorEspaco).trim());
                        break;
                    case "SS":
                        //subsecao
                        System.out.println("Nome do Subsessão: "+retornaString(separadaPorEspaco));
                        arvore.add(retornaString(separadaPorEspaco).trim(), getLastSessao());
                        setLastElement(retornaString(separadaPorEspaco).trim());
                        break;
                    case "P":
                        System.out.println("Paragrafo: "+retornaString(separadaPorEspaco));
                        numParagrafo = Integer.valueOf(retornaString(separadaPorEspaco).trim());
                        arvore.add(numParagrafo,getLastElement());
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

    public String getLastElement() {
        return lastElement;
    }

    public void setLastElement(String lastElement) {
        this.lastElement = lastElement;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }

    public String getLastCapitulo() {
        return lastCapitulo;
    }

    public void setLastCapitulo(String lastCapitulo) {
        this.lastCapitulo = lastCapitulo;
    }

    public String getLastSessao() {
        return lastSessao;
    }

    public void setLastSessao(String lastSessao) {
        this.lastSessao = lastSessao;
    }
}
