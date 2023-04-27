package ch.jose.gonzalez.todoapp;
import ch.jose.gonzalez.todoapp.task.Task;
import ch.jose.gonzalez.todoapp.task.TaskRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RestControllerTests {
    @Autowired
    private MockMvc api;

    @Autowired
    private TaskRepository taskRepository;

    @BeforeAll
    void setup() {
        this.taskRepository.save(new Task("Meeting", "Vorbereitung meeting", LocalDate.of(2023, 4, 30), LocalDate.of(2023, 5, 5)));
        this.taskRepository.save(new Task("WebApp", "WebApp fertig machen", LocalDate.of(2023, 4, 27), LocalDate.of(2023, 5, 27)));
    }

    @Test
    @Order(1)
    void testGetTask() throws Exception {

        String accessToken = obtainAccessToken();

        api.perform(get("/api/task").header("Authorization", "Bearer " + accessToken)
                        .with(csrf()))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("BL 123")));
    }

    private String obtainAccessToken() {

        RestTemplate rest = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "client_id=todapp" +
                "grant_type=password&" +
                "scope=openid profile roles offline_access&" +
                "username=user" +
                "password=user";

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> resp = rest.postForEntity("http://localhost:8080/realms/TODO/protocol/openid-connect/token", entity, String.class);

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resp.getBody()).get("access_token").toString();
    }
}
