package com.wedatalab.project.user.controller;

import com.wedatalab.project.domain.User.controller.UserRestController;
import com.wedatalab.project.domain.User.dto.response.UserGetResponse;
import com.wedatalab.project.domain.User.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UserRestController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @Nested
    @DisplayName("user()는")
    class Context_userGet {

        @Test
        @DisplayName("유저 정보를 얻을 수 있다.")
        void _willSuccess() throws Exception {
            //given
            Long userId = 1L;
            UserGetResponse userGetResponse = new UserGetResponse("이름", 23, "이메일");

            BDDMockito.given(userService.getUser(userId)).willReturn(userGetResponse);

            mockMvc.perform(MockMvcRequestBuilders.get("/users/" + userId)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

            BDDMockito.verify(userService).getUser(userId);
        }
    }
}
