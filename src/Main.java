import cards.Library;
import json.Jason;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }
    public void run(){
        Library library = Jason.readFile("AllCards.json");
        println(library.toString());
    }
    private void print(Object obj){
        System.out.print(obj);
    }
    private void println(Object obj){
        System.out.println(obj);
    }
}
