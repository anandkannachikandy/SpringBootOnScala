package org.example.application
package service

import model.{MyRequest, MyResponse}
import org.springframework.stereotype.Component

@Component
class MyService {

  def processRequest(request: MyRequest): MyResponse = {

    val response = MyResponse(s"Got the request from ${request.id}:${request.name}")
    response
  }

}

