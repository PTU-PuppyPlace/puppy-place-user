package kr.puppyplace.user.mypet.controller;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDate;
import kr.puppyplace.user.mypet.dto.MyPetDto.MyPetCreateRequest;
import kr.puppyplace.user.mypet.enums.PetGender;
import kr.puppyplace.user.mypet.enums.PetNeutralization;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class PetControllerTest {

    private static final FieldDescriptor[] myPetCreateRequestFields = new FieldDescriptor[]{
            fieldWithPath("id").description("반려동물 id (pk)"),
            fieldWithPath("name").description("반려동물 이름"),
            fieldWithPath("breed").description("반려동물 종류"),
            fieldWithPath("gender").description("반려동물 성별"),
            fieldWithPath("birth").description("반려동물 생년월일"),
            fieldWithPath("weight").description("반려동물 몸무게"),
            fieldWithPath("temperament").description("반려동물 성격"),
            fieldWithPath("neutralization").description("반려동물 중성화 여부"),
            fieldWithPath("registerNumber").description("반려동물 등록번호")
    };

    private static final FieldDescriptor[] myPetCreateResponseFields = new FieldDescriptor[]{
            fieldWithPath("id").description("반려동물 id (pk)"),
            fieldWithPath("name").description("반려동물 이름"),
            fieldWithPath("breed").description("반려동물 종류"),
            fieldWithPath("gender").description("반려동물 성별"),
            fieldWithPath("birth").description("반려동물 생년월일"),
            fieldWithPath("weight").description("반려동물 몸무게"),
            fieldWithPath("temperament").description("반려동물 성격"),
            fieldWithPath("neutralization").description("반려동물 중성화 여부"),
            fieldWithPath("registerNumber").description("반려동물 등록번호")
    };

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String contextPath;
    private final String prefixUrl;

    @Autowired
    private MockMvc mockMvc;

    public PetControllerTest(
            @Value("${server.servlet.context-path}") String contextPath) {
        this.contextPath = contextPath;
        this.prefixUrl = contextPath;
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    @DisplayName("반려동물을 등록 할 수 있어야 한다.")
    void 반려동물을_등록_할_수_있어야_한다() throws Exception {
        // given
        MyPetCreateRequest request = MyPetCreateRequest.builder()
                .name("테스트")
                .breed("test")
                .gender(PetGender.MALE)
                .birth(LocalDate.of(2024, 4, 1))
                .weight(10)
                .temperament("활발")
                .neutralization(PetNeutralization.valueOf("YES"))
                .registerNumber("1234567890")
                .build();

        // when
        ResultActions resultActions = mockMvc.perform(post(prefixUrl + "/mypet")
                .contextPath(contextPath)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value(request.getName()))
                .andExpect(jsonPath("$.breed").value(request.getBreed()));

        // docs
        resultActions.andDo(document("내 반려동물 등록 성공",
                "내 반려동물을 등록한다.",
                "내 반려동물 등록",
                false,
                false,
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFields(myPetCreateRequestFields),
                responseFields(myPetCreateResponseFields)
        ));
    }
}