import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashTableSCTest {

    HashTableSC table = new HashTableSC(10);

    @org.junit.Test (expected = IllegalArgumentException.class)
    public void exceptionTest(){
        table.put("key", 1);
        table.put("key", 2);
    }

    @Test
    public void testContainsKey() {
        table.put("key", "value");
        table.put(10, 100);
        table.put("ala", 100);
        assertTrue(table.containsKey("key"));
        assertTrue(table.containsKey(10));
        assertFalse(table.containsKey(1000));
        table.remove(10);
        assertFalse(table.containsKey(10));
    }

    @Test
    public void testGet(){
        table.put("key", "value");
        table.put(10, 100);
        assertSame("value", table.get("key"));
        assertSame(100, table.get(10));
    }

    @Test
    public void testContainsValue() {
        table.put("key", "value");
        assertTrue(table.containsValue("value"));
        assertFalse(table.containsValue("ala"));
    }


    @Test
    public void testClear() {
        table.put("key", "value");
        table.put(10, 100);
        table.put("ala", 100);
        table.clear();
        assertSame(0, table.size());
    }
}