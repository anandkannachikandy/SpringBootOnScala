package org.example.application
package controller

import model.{MyRequest, MyResponse}
import service.MyService
import util.Util.mapper
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.{BeforeEach, Test}
import org.mockito.Mockito.{verify, verifyNoInteractions, when}
import org.mockito.{InjectMocks, Mock}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

import scala.beans.BeanProperty

@SpringBootTest(Array("MyController"))
@AutoConfigureMockMvc
@ExtendWith(Array(classOf[SpringExtension]))
@WebAppConfiguration
class MyControllerTest {

  @Mock
  val myService: MyService = null

  @InjectMocks
  val myController: MyController = null


  @Autowired
  @BeanProperty
  val objectMapper: ObjectMapper = null

  var mockMvc: MockMvc = _

  @BeforeEach
  def setup(): Unit = {
    val converter = new MappingJackson2HttpMessageConverter()
    converter.setObjectMapper(objectMapper)
    mockMvc = MockMvcBuilders
      .standaloneSetup(myController)
      .setMessageConverters(converter)
      .build()
  }

  @Test
  def testControllerGoodRequest(): Unit = {
    val request = MyRequest("anand", 1)
    val json = mapper.writeValueAsString(request)
    val response = MyResponse(s"Got the request from anand:1")
    when(myService.processRequest(request)).thenReturn(response)
    this.mockMvc.perform(post("/base/demo/test").content(json).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)).andExpect(status.isOk)
    verify(myService).processRequest(request)
  }

  @Test
  def testControllerBadRequest(): Unit = {
    this.mockMvc.perform(post("/base/demo/test").content("bad request").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)).andExpect(status.isBadRequest)
    verifyNoInteractions(myService)
  }

}