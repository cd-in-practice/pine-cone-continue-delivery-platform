package codes.showme.pinecone.cdp.techcommon;

import java.util.Date;

import static org.junit.Assert.*;

public class JsonServiceImplTest {

    @org.junit.Test
    public void toJsonString() {
        Example example = new Example();
        example.setName("name");
        example.setAge(1);
        assertEquals("{\"name\":\"name\",\"age\":1}", new JsonServiceImpl().toJsonString(example));
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