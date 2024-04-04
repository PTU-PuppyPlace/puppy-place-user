package kr.puppyplace.user.mypet.controller;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDate;
import kr.puppyplace.user.mypet.domain.enums.PetGender;
import kr.puppyplace.user.mypet.domain.enums.PetNeutralization;
import kr.puppyplace.user.mypet.dto.MyPetDto.MyPetCreateRequest;
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

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String contextPath;
    private final String prefixUrl;
    private static final FieldDescriptor[] myPetCreateRequest = new FieldDescriptor[]{
            fieldWithPath("id").description("반려동물 id (pk)"),
            fieldWithPath("petName").description("반려동물 이름"),
            fieldWithPath("petBreed").description("반려동물 종류"),
            fieldWithPath("petGender").description("반려동물 성별"),
            fieldWithPath("petBirth").description("반려동물 생년월일"),
            fieldWithPath("petWeight").description("반려동물 몸무게"),
            fieldWithPath("petTemperament").description("반려동물 성격"),
            fieldWithPath("petNeutralization").description("반려동물 중성화 여부"),
            fieldWithPath("petRegNumber").description("반려동물 등록번호")
    };

    @Autowired
    private MockMvc mockMvc;

    public PetControllerTest(
            @Value("${server.servlet.context-path}") String contextPath) {
        this.contextPath = contextPath;
        this.prefixUrl = contextPath;
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    @DisplayName("반려동물 등록")
    void myPetCreate() throws Exception {
        // given
        MyPetCreateRequest request = MyPetCreateRequest.builder()
                .petName("테스트")
                .petBreed("test")
                .petGender(PetGender.MALE)
                .petBirth(LocalDate.of(2024, 4, 1))
                .petWeight(10)
                .petTemperament("활발")
                .petNeutralization(PetNeutralization.valueOf("YES"))
                .petRegNumber("1234567890")
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
                .andExpect(jsonPath("$.petName").value(request.getPetName()))
                .andExpect(jsonPath("$.petBreed").value(request.getPetBreed()));

        // docs
        resultActions.andDo(document("mypet 등록 성공",
                requestFields(myPetCreateRequest)
        ));


    }

}