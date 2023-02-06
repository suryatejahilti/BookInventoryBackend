package app.library.common;

import app.library.audit.entity.Audit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class JsonParser {

    static ObjectMapper mapper = new ObjectMapper();

    public String stringify(Audit audit) throws JsonProcessingException {

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(audit);

    }
    public static Audit parser(String jsonString) throws JsonProcessingException {

        return mapper.readValue(jsonString, Audit.class);

    }
}
