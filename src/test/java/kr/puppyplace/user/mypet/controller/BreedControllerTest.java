package kr.puppyplace.user.mypet.controller;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import kr.puppyplace.user.mypet.domain.Breed;
import kr.puppyplace.user.mypet.repository.BreedRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Transactional
class BreedControllerTest {

    private static final ParameterDescriptor[] breedRequestFields = new ParameterDescriptor[]{
            parameterWithName("breedKey").description("견종 검색 키워드"),
    };

    private static final FieldDescriptor[] breedResponseFields = new FieldDescriptor[]{
            fieldWithPath("[].id").description("견종 id (pk)"),
            fieldWithPath("[].name").description("견종 한글명"),
            fieldWithPath("[].engName").description("견종 영어명"),
    };

    private final String prefixUrl;

    private final String contextPath;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BreedRepository breedRepository;

    public BreedControllerTest(
            @Value("${server.servlet.context-path}") String contextPath) {
        this.contextPath = contextPath;
        this.prefixUrl = contextPath + "/breeds";
    }

    @Test
    @DisplayName("견종을 검색 할 수 있어야 한다.")
    void 견종을_검색_할_수_있어야_한다() throws Exception {
        // given
        String breedKey = "콜리";

        breedRepository.save(Breed.builder()
                .name("콜리")
                .engName("collie")
                .build());

        // when
        ResultActions resultActions = mockMvc.perform(
                get(prefixUrl + "/search?breedKey={breedKey}", breedKey)
                        .contextPath(contextPath)
                        .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNotEmpty())
                .andExpect(jsonPath("$[0].name").value("콜리"))
                .andExpect(jsonPath("$[0].engName").value("collie"));

        // docs
        resultActions.andDo(document("견종 검색 성공",
                "견종을 검색한 결과를 조회한다.",
                "견종 검색",
                false,
                false,
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                queryParameters(breedRequestFields),
                responseFields(breedResponseFields)
        ));
    }
}