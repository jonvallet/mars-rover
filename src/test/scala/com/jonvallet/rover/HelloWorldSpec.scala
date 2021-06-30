package com.jonvallet.rover

import org.scalatest._
import flatspec._
import matchers._

class HelloWorldSpec extends AnyFlatSpec with should.Matchers {
  "A Hello" should "print" in {
    println("Hello World!")
  }
}
