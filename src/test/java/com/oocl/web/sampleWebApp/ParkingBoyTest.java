package com.oocl.web.sampleWebApp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.web.sampleWebApp.domain.ParkingBoy;
import com.oocl.web.sampleWebApp.domain.ParkingBoyRepository;
import com.oocl.web.sampleWebApp.models.ParkingBoyResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.oocl.web.sampleWebApp.WebTestUtil.getContentAsObject;
import static jdk.nashorn.internal.objects.NativeDate.toJSON;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingBoyTest {

    @Autowired
    private ParkingBoyRepository parkingBoyRepository;

    @Autowired
    private MockMvc mvc;

    //Story 1 AC 1
    @Test
    public void should_able_to_create_parkingboy_if_employeeId_does_not_exist_in_database() throws Exception {

        //Given
        final String employeeId = "ID001";
        final ParkingBoy expectedParkingBoy = new ParkingBoy(employeeId);
        ObjectMapper mapper = new ObjectMapper();
        final String expectedParkingBoyInJson = mapper.writeValueAsString(expectedParkingBoy);
        final String url = "/parkingboys";
        final String redirecturl = url + "/" + parkingBoyRepository.getOne(1L).getId();

        //When
        mvc.perform(MockMvcRequestBuilders.post(url)
        .contentType(MediaType.APPLICATION_JSON).content(expectedParkingBoyInJson))

        //Then

        .andExpect(status().isCreated())
        .andExpect(header().string("Location", containsString(redirecturl)))
        .andExpect(redirectedUrl(redirecturl));
    }
}
