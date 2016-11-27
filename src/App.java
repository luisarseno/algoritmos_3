
import java.io.File;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        File arquivo = new File("C:\\Users\\Santana\\Documents\\livro.txt");
        new Leitor(arquivo);
        System.exit(1);
        GeneralTreeOfObject arv = new GeneralTreeOfObject();
        arv.add(1, null);
        arv.add(2, 1);
        arv.add(3, 1);
        arv.add(4, 2);
        arv.add(6, 2);
        arv.add(0, null);
        arv.add(8, 6); 
        arv.add(7, 3); 
        arv.add(5, 2); 
        ArrayList<Object> l = arv.positionsWidth();
        System.out.println(l);
        System.out.println(l.size());
        
    }
    
}
