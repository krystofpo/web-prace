package kry.web;

import kry.bizlogic.Spitter;
import kry.data.SpitterRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by polansky on 7.12.2017.
 */
public class SpitterControllerTest {

    private  MockMvc mockMvc;
    private SpitterController controller;
    private SpitterRepository mockRepository;

    @Before
    public void setUp() throws Exception {
        mockRepository = mock(SpitterRepository.class);
        controller = new SpitterController(mockRepository);
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void showRegistrationForm() throws Exception {
        mockMvc.perform(get("/spitter/register"))
                .andExpect(view().name("registerForm"));
    }

    @Test
    public void shouldRegisterWithValidData() throws Exception {
        Spitter unsaved = new Spitter( "kpol", "heslo", "Krystof", "Polansky");
        mockMvc.perform(post("/spitter/register")
                .param("firstName", "Krystof")
                .param("lastName", "Polansky")
                .param("login", "kpol")
                .param("password", "heslo"))
                .andExpect(redirectedUrl("/spitter/kpol"));

        verify(mockRepository, atLeastOnce()).save(unsaved);

    }

    @Test
    public void registerWithInvalidData() throws Exception {
        new Spitter();
        mockMvc.perform(post("/spitter/register")
                .param("firstName", "")
                .param("lastName", "Po")
                .param("login", "kpol")
                .param("password", "heslo"))
                .andExpect(view().name("registerForm"));
    }

    @Test
    public void showProfile() throws Exception {
        Spitter expectedSpitter = new Spitter("kpol2", "pass","xxx", "xxx" );
        when(mockRepository.findByLogin("kpol2")).thenReturn(expectedSpitter);
        mockMvc.perform(get("/spitter/kpol2"))
                .andExpect(view().name("profile"))
                .andExpect(model().attributeExists("spitter"))
                .andExpect(model().attribute("spitter", equalTo(expectedSpitter)));
    }


}