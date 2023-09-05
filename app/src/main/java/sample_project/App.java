package sample_project;

public class App {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(1);
        list.add(2);

        for (Object i : list) {
            System.out.println(i);
        }
    }
}
