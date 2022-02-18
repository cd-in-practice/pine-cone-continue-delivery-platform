package codes.showme.pinecone.cdp.techcommon;


import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class JsonServiceImplTest {
    String jsonString = "{\"name\":\"name\",\"age\":1}";

    @org.junit.Test
    public void toJsonString() {
        Example example = new Example();
        example.setName("name");
        example.setAge(1);
        assertEquals(jsonString, new JsonServiceImpl().toJsonString(example));
    }
    
    
    @Test
    public void testToObject(){

        Example example = new JsonServiceImpl().toObject(jsonString.getBytes(), Example.class);
        assertEquals("name", example.getName());
        assertEquals(1, example.getAge());

    }

    @Test
    public void toMap(){
        Map<String, Object> stringObjectMap = new JsonServiceImpl().toHashMap(jsonString);
        assertEquals("name", stringObjectMap.get("name"));
        assertEquals(1, stringObjectMap.get("age"));
    }
    

    public static class Example {

        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}