package org.example.application
package controller

import model.MyRequest
import service.MyService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.{PostMapping, RequestBody, RequestMapping, RestController}

@RestController
@RequestMapping(path = Array("/base/demo"))
class MyController(myService: MyService) {

  @PostMapping(path = Array("/test"))
  def controllerSampleRequest(@RequestBody req: MyRequest): ResponseEntity[String] = {
    val response = myService.processRequest(req)
    ResponseEntity.ok().body(response.response)
  }
}
