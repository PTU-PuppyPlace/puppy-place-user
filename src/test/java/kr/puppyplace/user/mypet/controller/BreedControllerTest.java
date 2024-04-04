package kr.puppyplace.user.mypet.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class BreedControllerTest {

    private final String prefixUrl;

    private final String contextPath;

    @Autowired
    private MockMvc mockMvc;

    public BreedControllerTest(
            @Value("${server.servlet.context-path}") String contextPath) {
        this.contextPath = contextPath;
        this.prefixUrl = contextPath;
    }

    @Test
    @DisplayName("견종 검색")
    void getSearchedBreed() throws Exception {
        // given
        String breedKey = "콜리";

        // when
        ResultActions resultActions = mockMvc.perform(get(prefixUrl + "/breeds/search")
                .param("breedKey", breedKey)
                .contextPath(contextPath)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions
                .andExpect(status().isOk());

        // docs
        resultActions
                .andDo(document("견종 검색 성공"));
    }

}