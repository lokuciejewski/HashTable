public class Main {

    public static void main(String []args){
        HashTableSC a = new HashTableSC(10);
        a.put("dupa", "blada");
        a.put(110, "mala");
        a.put("grzybol", "rybol");
        a.put(101, 20);
        System.out.println(a);

        System.out.println(a.get("grzybol"));
    }

}
