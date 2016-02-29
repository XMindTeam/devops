package com.intel.spring.boot.sample.boot.web.controller;

import com.intel.spring.boot.sample.boot.SampleApplication;
import com.intel.spring.boot.sample.boot.web.dao.CharacterRepository;
import com.intel.spring.boot.sample.boot.model.Character;
import com.jayway.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;

import static com.jayway.restassured.RestAssured.when;

/**
 * Created by Ecic Chen on 2016/2/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class CharacterControllerTest {


    @Autowired   // 5
    CharacterRepository repository;

    Character mickey;
    Character minnie;
    Character pluto;

    @Value("${local.server.port}")   // 6
    int port;

    @Before
    public void setUp() {
        // 7
        mickey = new Character("Mickey Mouse");
        minnie = new Character("Minnie Mouse");
        pluto = new Character("Pluto");

        // 8
        repository.deleteAll();
        repository.save(Arrays.asList(mickey,minnie,pluto));

        // 9
        RestAssured.port = port;
    }

    // 10
    @Test
    public void canFetchMickey() {
        Integer mickeyId = mickey.getId();

        when().
                get("/characters/{id}", mickeyId).
                then().
                statusCode(HttpStatus.SC_OK).
                body("name", Matchers.is("Mickey Mouse")).
                body("id", Matchers.is(mickeyId));
    }

    @Test
    public void canFetchAll() {
        when().
                get("/characters").
                then().
                statusCode(HttpStatus.SC_OK).
                body("name", Matchers.hasItems("Mickey Mouse", "Minnie Mouse", "Pluto"));
    }

    @Test
    public void canDeletePluto() {
        Integer plutoId = pluto.getId();

        when()
                .delete("/characters/{id}", plutoId).
                then().
                statusCode(HttpStatus.SC_NO_CONTENT);
    }

}
