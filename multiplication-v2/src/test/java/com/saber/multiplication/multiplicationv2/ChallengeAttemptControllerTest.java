package com.saber.multiplication.multiplicationv2;

import com.saber.multiplication.multiplicationv2.controllers.ChallengeAttemptController;
import com.saber.multiplication.multiplicationv2.dto.ChallengeAttempt;
import com.saber.multiplication.multiplicationv2.dto.ChallengeAttemptDto;
import com.saber.multiplication.multiplicationv2.dto.User;
import com.saber.multiplication.multiplicationv2.services.ChallengeService;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
@WebMvcTest(ChallengeAttemptController.class)
public class ChallengeAttemptControllerTest {

    @MockBean
    private ChallengeService challengeService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ChallengeAttemptDto> jsonAttemptDto;

    @Autowired
    private JacksonTester<ChallengeAttempt> jsonAttempt;


    @Test
    public void postValidResult() throws Exception {

        User user = new User(10L, "saber");
        long attemptId = 5L;
        ChallengeAttemptDto attemptDto = new ChallengeAttemptDto(2000, -5, "saber", 3500);

        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.
                post("/challenge/attempt")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonAttemptDto.write(attemptDto).getJson()))
                .andReturn().getResponse();

        BDDAssertions.then(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());


    }

    @Test
    public void postInValidResult() throws Exception {

        User user = new User(10L, "saber");
        long attemptId = 5L;
        ChallengeAttemptDto attemptDto = new ChallengeAttemptDto(50, 70, "saber", 3600);
        ChallengeAttempt challengeAttempt = new ChallengeAttempt(attemptId, user, 50, 70, 3500, true);
        BDDMockito.given(challengeService.verifying(BDDMockito.eq(attemptDto)))
                .willReturn(challengeAttempt);

        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.
                post("/challenge/attempt")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonAttemptDto.write(attemptDto).getJson()))
                .andReturn().getResponse();

        BDDAssertions.then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        BDDAssertions.then(response.getContentAsString()).isEqualTo(jsonAttempt.write(challengeAttempt).getJson());

    }
}
