package org.obszczymucha.tradereportingengine.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ReportTriggerControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void reportShouldReturnDummyMessage() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/report"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].sellerParty", hasItem("EMU_BANK")));
    }
}