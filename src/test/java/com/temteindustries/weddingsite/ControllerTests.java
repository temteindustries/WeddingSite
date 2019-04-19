package com.temteindustries.weddingsite;

import com.temteindustries.weddingsite.Controller.WeddingController;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ControllerTests {

    WeddingController controller = new WeddingController();


    @Test
    public void cleanRequestWithEmptyTest(){
        String stmn = "";
        assertEquals(null,controller.cleanRequest(stmn));
    }

    @Test
    public void cleanRequestWithExpectedValueTest(){
        String stmn = "json=[\n" +
                "    {\n" +
                "        \"fName\": \"Joe\",\n" +
                "        \"lName\": \"Temte\",\n" +
                "        \"ceremony\": \"Attending\",\n" +
                "        \"reception\": \"Attending\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"fName\": \"Jessica\",\n" +
                "        \"lName\": \"Livingston\",\n" +
                "        \"ceremony\": \"Attending\",\n" +
                "        \"reception\": \"Attending\"\n" +
                "    }\n" +
                "    \n" +
                "]";

        String expected = "[\n" +
                "    {\n" +
                "        \"fName\": \"Joe\",\n" +
                "        \"lName\": \"Temte\",\n" +
                "        \"ceremony\": \"Attending\",\n" +
                "        \"reception\": \"Attending\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"fName\": \"Jessica\",\n" +
                "        \"lName\": \"Livingston\",\n" +
                "        \"ceremony\": \"Attending\",\n" +
                "        \"reception\": \"Attending\"\n" +
                "    }\n" +
                "    \n" +
                "]";
        assertEquals(expected,controller.cleanRequest(stmn));
    }
}
