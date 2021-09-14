public class Main2 {
    public static void main(String[] args) {

       List<Integer> strings1 = new ArrayList<>();

       for (int i = 0; i < 100; i++) {
           strings1.add(i);
       }

       for (int i = 0; i < 100; i++) {
           System.out.print(strings1.get(i) + " ");
       }

        System.out.println("\n___________________");

       while (strings1.iterator().hasNext()){
           System.out.print(strings1.iterator().next() + " ");
       }

    }
}
