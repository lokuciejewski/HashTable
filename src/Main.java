public class Main {

    public static void main(String []args){
        HashTableSC a = new HashTableSC(10);
        HashTableOA b = new HashTableOA(10);
        a.put("dupa", "blada");
        a.put(110, "mala");
        a.put("grzybol", "rybol");
        a.put(101, 20);
        b.put("dupa", "blada");
        b.put(110, "mala");
        b.put("dupsko", 10);
        b.put(10, 10);
        System.out.println(a);
        a.remove(110);
        b.remove(110);
        System.out.println(a);
        System.out.println(b);

        System.out.println(a.get("grzybol"));
    }

}
