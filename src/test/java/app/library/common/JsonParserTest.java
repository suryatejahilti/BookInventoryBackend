package app.library.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import app.library.audit.entity.Audit;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.sql.Timestamp;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JsonParser.class})
@ExtendWith(SpringExtension.class)
class JsonParserTest {
    @Autowired
    private JsonParser jsonParser;


    @Test
    void testStringify() throws JsonProcessingException {
        Timestamp timestamp = mock(Timestamp.class);
        when(timestamp.getTime()).thenReturn(10L);

        Audit audit = new Audit();
        audit.setAction("Action");
        audit.setAuditId(123L);
        audit.setBookId("42");
        audit.setUpdatedOn(timestamp);
        audit.setUser("User");
        assertEquals("{\r\n" + "  \"auditId\" : 123,\r\n" + "  \"bookId\" : \"42\",\r\n" + "  \"updatedOn\" : 10,\r\n"
                + "  \"user\" : \"User\",\r\n" + "  \"action\" : \"Action\"\r\n" + "}", jsonParser.stringify(audit));
        verify(timestamp).getTime();
    }







    void testParser4() throws JsonProcessingException {


        JsonParser.parser("{\n" +
                "  \"auditId\" : 0,\n" +
                "  \"bookId\" : \"63e0c30b4edb4e79e408b0e8\",\n" +
                "  \"updatedOn\" : 1675674380099,\n" +
                "  \"user\" : \"surya\",\n" +
                "  \"action\" : \"CREATE\"\n" +
                "}");
    }


}

