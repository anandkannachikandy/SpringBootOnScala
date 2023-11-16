package org.example.application
package config

import com.fasterxml.jackson.databind.ObjectMapper
import util.Util.mapper
import org.springframework.context.annotation.{Bean, Configuration}

@Configuration
class JacksonConfiguration {

  @Bean
  def objectMapper: ObjectMapper = {
    mapper
  }
}

