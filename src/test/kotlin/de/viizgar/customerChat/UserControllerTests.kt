package de.viizgar.customerChat

import de.viizgar.customerChat.controller.UserController
import de.viizgar.customerChat.repository.UserRepository
import de.viizgar.customerChat.service.UserService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post


@AutoConfigureMockMvc
@ContextConfiguration(classes = [UserController::class, UserService::class, UserRepository::class])
@WebMvcTest
class UserControllerTests {

    @Autowired
    private val mockMvc: MockMvc? = null

    @Test
    fun createNewUser_returnsStatus200() {
        mockMvc!!.perform(
            post("/api/user/")
                .content("{\"id\": 1,\"username\": \"test\", \"isAgent\":\"true\"}")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
        )

        var test2 = mockMvc.perform(
            get("/api/user/")
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
        )
        //.andExpect(status().isOk)
        //.andExpect(content().contentType(APPLICATION_JSON))
        //.andExpect(jsonPath("$.username").value("test"));

        println(test2.toString())
    }
}