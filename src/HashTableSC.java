import java.util.*;

public class HashTableSC<K, V> implements Map<K, V>{

    private LinkedList<Element<K, V>>[] table;
    private int size;
    int number_of_keys; //should be reasonably low

    public HashTableSC(){
        this.size = 0;
        this.number_of_keys = 30;
        table = new LinkedList[number_of_keys];
        for(int i = 0; i<number_of_keys; i++){
            table[i] = new LinkedList<>();
        }
    }

    public HashTableSC(int numberOfKeys){
        this.size = 0;
        this.number_of_keys = numberOfKeys;
        table = new LinkedList[numberOfKeys];
        for(int i = 0; i<numberOfKeys; i++){
            table[i] = new LinkedList<>();
        }
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

    private int hash(Element el){
        String key = el.getKey().toString();
        return Math.abs(key.hashCode()%number_of_keys);
    }
    private int hash(K key){
        String keystr = key.toString();
        return Math.abs(keystr.hashCode()%number_of_keys);
    }


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if(size>0) return false;
        else return true;
    }

    public boolean containsKey(Object key) {
        LinkedList<Element<K, V>> list = table[hash((K) key)];
        while(list.iterator().hasNext()){
            if(list.iterator().next().getKey() == key) return true;
        }
        return false;
    }

    public boolean containsValue(Object value) {
        for(int i = 0; i< table.length; i++){
            LinkedList<Element<K, V>> list = table[i];
            while(list.iterator().hasNext()){
                if(list.iterator().next().getValue() == value) return true;
            }
        }
        return false;
    }

    public V get(Object key) {
        for(Element el: table[hash((K) key)]){
            if(el.getKey()==key) return (V) el.getValue();
        }
        return null;
    }

    public Object put(Object key, Object value) throws IllegalArgumentException{
        Element el = new Element(key, value);
        int hashValue = hash(el);
        for(Element elem: table[hashValue]){
            if(elem.getKey()==key) throw new IllegalArgumentException("The key "+key+" already exists in HashTable.");
        }
        table[hashValue].addFirst(el);
        return el;
    }

    public V remove(Object key) {
        LinkedList<Element<K, V>> list = table[hash((K)key)];
        Element<K, V> el = null;
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getKey() == key){
                el = list.get(i);
                list.remove(i);
            }
        }
        return (V)el.getValue();
    }

    public void putAll(Map<? extends K, ? extends V> m) {

    }

    public void clear() {
        for(int i = 0; i<table.length; i++){
            table[i] = new LinkedList<>();
        }
        System.gc();
    }

    public Set keySet() {
        return null;
    }

    public Collection values() {
        return null;
    }

    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    public boolean remove(Object key, Object value) {
        V val = remove(key);
        if(val != null) return true;
        else return false;
    }

    public boolean replace(Object key, Object oldValue, Object newValue) {
        LinkedList<Element<K, V>> list = new LinkedList<>();
        list = table[hash((K) key)];
        for(int i=0; i<list.size(); i++){
            if(list.get(i).getKey() == key){
                list.get(i).setValue((V) newValue);
                return true;
            }
        }
        return false;
    }

    public Object replace(Object key, Object value) {
        LinkedList<Element<K, V>> list = new LinkedList<>();
        list = table[hash((K) key)];
        for(int i=0; i<list.size(); i++){
            if(list.get(i).getKey() == key){
                list.get(i).setValue((V) value);
                return list.get(i);
            }
        }
        return null;
    }

    public String toString(){
        String s = "";
        for(int i = 0; i<table.length; i++){
            s+="\nKey: "+i+" has elements: ";
            for(Element el: table[i]){
                s+=el.toString()+"; ";
            }
        }
        return s;
    }
}
