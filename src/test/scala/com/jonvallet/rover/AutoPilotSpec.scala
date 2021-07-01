package com.jonvallet.rover

import org.scalatest._
import flatspec._
import matchers._

class AutoPilotSpec extends AnyFlatSpec with should.Matchers {
  private val grid: Grid = Grid(4,4)
  "A rover" should "determine the shortest possible path from one position to another" in {
    grid.path(Rover(Position(2,2), Up), Position(0,0)) should
      be (List(MoveForward, MoveForward, RotateLeft, MoveForward, MoveForward))
    grid.path(Rover(Position(2,2), Up), Position(3,3)) should
      be (List(RotateRight, RotateRight, MoveForward, RotateLeft, MoveForward))
    grid.path(Rover(Position(2,2), Up), Position(2,2)) should
      be (List())
    grid.path(Rover(Position(0,0), Up), Position(3,3)) should
      be (List(RotateRight, RotateRight, MoveForward, MoveForward, MoveForward, RotateLeft, MoveForward, MoveForward, MoveForward))
  }
  "A rover" should "output the different states follow from one positiont to another" in {
    val rover = Rover(Position(2,2), Up)
    val destination = Position(3,3)
    val expectedValue = List(Rover(Position(2,2), Up), Rover(Position(2,2), Right), Rover(Position(2,2), Down), Rover(Position(2,3), Down), Rover(Position(2,3), Right), Rover(Position(3,3), Right))
    grid.roverStates(rover, destination) should
      be (expectedValue)
  }
}
