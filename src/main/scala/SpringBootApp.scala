package org.example.application

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class SpringBootApp {
}

object SpringBootApp extends App {

  SpringApplication.run(classOf[SpringBootApp])

}
