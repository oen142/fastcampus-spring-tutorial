package com.wani.fastcampusspringtutorial.documentation;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

import com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wani.fastcampusspringtutorial.dto.UserRequest;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.restassured3.RestAssuredRestDocumentation;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(RestDocumentationExtension.class)
class UserApiDocumentation {

    @LocalServerPort
    int port;

    protected RequestSpecification spec;

    @BeforeEach
    void setUp(RestDocumentationContextProvider restDocumentation) {
        RestAssured.port = port;
        this.spec = new RequestSpecBuilder()
            .addFilter(RestAssuredRestDocumentation.documentationConfiguration(restDocumentation))
            .build();

    }

    @Test
    void test() throws Exception {

        RestAssured.given(spec).log().all()
            .filter(
                RestAssuredRestDocumentationWrapper.document(
                    "user",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestFields(
                        fieldWithPath("account").type(JsonFieldType.STRING).description("????????? ??????"),
                        fieldWithPath("email").type(JsonFieldType.STRING).description("????????? ?????????"),
                        fieldWithPath("phoneNumber").type(JsonFieldType.STRING)
                            .description("????????? ???????????????")
                    ),
                    responseFields(
                        fieldWithPath("resultCode").description("????????????"),
                        fieldWithPath("data").description("?????????")
                    )
                )
            )
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .body(new ObjectMapper().writeValueAsString(new UserRequest("test", "test", "test")))
            .when().post("/api/user")
            .then().log().all().extract();

        RestAssured.given(spec).log().all()
            .filter(
                RestAssuredRestDocumentationWrapper.document(
                    "user",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    pathParameters(
                        parameterWithName("id").description("?????????")
                    ),
                    responseFields(
                        fieldWithPath("resultCode").description("????????????"),
                        fieldWithPath("data.id").description("id"),
                        fieldWithPath("data.account").description("??????"),
                        fieldWithPath("data.email").description("?????????"),
                        fieldWithPath("data.phoneNumber").description("????????????"),
                        fieldWithPath("data.createdAt").description("????????????"),
                        fieldWithPath("data.updatedAt").description("????????????")
                    )
                )
            ).accept(MediaType.APPLICATION_JSON_VALUE)
            .when().get("/api/user/{id}", 1)
            .then().log().all().extract();

    }
}
