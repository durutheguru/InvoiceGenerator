package com.sample.invoice.controller;


import com.sample.invoice.config.TestBeansConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * created by julian
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestBeansConfig.class})
@AutoConfigureMockMvc
public class TimetableApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Value("classpath:uploads/sample-upload.csv")
    private Resource resource;


    @Test
    public void testTimetableFileUpload() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file", resource.getInputStream());

        mockMvc.perform(
                multipart("/api/v1/timetable/upload").file(multipartFile)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.generatedInvoices", hasSize(4)));
    }


}
