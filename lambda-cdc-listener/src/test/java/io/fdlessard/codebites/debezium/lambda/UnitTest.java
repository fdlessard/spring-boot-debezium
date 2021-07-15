package io.fdlessard.codebites.debezium.lambda;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UnitTest {


    @Test
    void deserialization() {
        String s = "[\n" +
                "{\n" +
                "    \"payload\": {\n" +
                "        \"key\": \"eyJpZCI6NX0=\",\n" +
                "        \"value\": \"{op=c, before=null, after={last_name=last-name-post-9, company=company-post, id=5, version=0, first_name=first-name-post}, source={schema=public, xmin=null, connector=postgresql, lsn=32883345008, name=dbserver1, txId=1355, version=1.3.1.Final, ts_ms=1626265579335, snapshot=false, db=postgres, table=customer}, ts_ms=1626265580094, transaction=null}\",\n" +
                "        \"timestamp\": 1626265580874,\n" +
                "        \"topic\": \"dbserver1.public.customer\",\n" +
                "        \"partition\": 0,\n" +
                "        \"offset\": 29\n" +
                "    }\n" +
                "}\n" +
                "]";
        assertNotNull(s);
    }
}
