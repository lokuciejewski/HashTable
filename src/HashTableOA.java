import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class HashTableOA<K, V> implements Map<K,V> {
    private int number_of_keys, size; //number of keys should be > 3
    public final double SIZE_MULT = 1.3;
    private Element[] table;

    public HashTableOA(){
        this.size = 0;
        this.number_of_keys = 30;
        table = new Element[(int) (SIZE_MULT*number_of_keys)];
    }

    public HashTableOA(int number_of_keys){
        this.size = 0;
        this.number_of_keys = number_of_keys;
        table = new Element[(int) (SIZE_MULT*number_of_keys)];
    }

    private int hash(K key){
        String keystr = key.toString();
        return Math.abs(keystr.hashCode()%number_of_keys);
    }
    private int hash(Element el){
        String key = el.getKey().toString();
        return Math.abs(key.hashCode()%number_of_keys);
    }

    private int probe(K key){
        int hashKey = hash(key);
        if(size == table.length){
            expand();
        }
        int j = 0;
        for(int i = hashKey; ; i++){
            if(table[(i+j*j)%table.length] == null){
                return i;
            }
            j++;
        }
    }
    private int search(K key){
        int hashKey = hash(key);
        int j = -number_of_keys;
        for(int i = hashKey; ; i++){
            if(table[(i+j*j)%table.length]!=null && table[(i+j*j)%table.length].getKey() == key){
                return i;
            }
            j++;
        }
    }


    private void expand(){
        table = Arrays.copyOf(table, (int) (table.length*SIZE_MULT));
    }

    private class Element<K, V>{
        private K key;
        private V value;

        private Element(K key, V value){
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public String toString(){
            return "("+key.toString()+")"+value.toString();
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if(size==0) return true;
        else return false;
    }

    public boolean containsKey(Object key) {
        if(search((K) key)==-1) return false;
        else return true;
    }

    public boolean containsValue(Object value) {
        for(int i = 0; i<table.length; i++){
            if(table[i]!=null & table[i].getValue()==value){
                return true;
            }
        }
        return false;
    }

    public V get(Object key) {
        int loc = search((K) key);
        if(loc != -1) return (V) table[loc].getValue();
        else return null;
    }

    public V put(K key, V value){
        table[probe(key)] = new Element(key, value);
        return value;
    }

    public V remove(Object key) {
        int loc = search((K) key);
        V val = null;
        if(loc != -1) {
            val = (V) table[loc].getValue();
            table[loc] = null;
        }
        return val;
    }

    public void putAll(Map<? extends K, ? extends V> m) {

    }

    public void clear() {
        table = new Element[(int) (number_of_keys*SIZE_MULT)];
        System.gc();
    }

    public Set<K> keySet() {
        return null;
    }

    public Collection<V> values() {
        return null;
    }

    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
