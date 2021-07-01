package com.jonvallet.rover

import org.scalatest._
import flatspec._
import matchers._

class RoverBasicMovementSpec extends AnyFlatSpec with should.Matchers {
  private val grid: Grid = Grid(4,4)
  "A Rover" should "move forward" in {
    grid.moveForward(Rover(Position(2,2), Up)) should be (Rover(Position(2,1), Up))
    grid.moveForward(Rover(Position(2,2), Down)) should be (Rover(Position(2,3), Down))
    grid.moveForward(Rover(Position(2,2), Left)) should be (Rover(Position(1,2), Left))
    grid.moveForward(Rover(Position(2,2), Right)) should be (Rover(Position(3,2), Right))
    grid.moveForward(Rover(Position(0,0), Up)) should be (Rover(Position(0,3), Up))
    grid.moveForward(Rover(Position(0,3), Down)) should be (Rover(Position(0,0), Down))
    grid.moveForward(Rover(Position(0,0), Left)) should be (Rover(Position(3,0), Left))
    grid.moveForward(Rover(Position(3,0), Right)) should be (Rover(Position(0,0), Right))
  }

  it should "turn left" in {
    grid.rotateLeft(Rover(Position(2,2), Up)) should be (Rover(Position(2,2), Left))
    grid.rotateLeft(Rover(Position(2,2), Down)) should be (Rover(Position(2,2), Right))
    grid.rotateLeft(Rover(Position(2,2), Left)) should be (Rover(Position(2,2), Down))
    grid.rotateLeft(Rover(Position(2,2), Right)) should be (Rover(Position(2,2), Up))
  }

  it should "turn right" in {
    grid.rotateRight(Rover(Position(2,2), Up)) should be (Rover(Position(2,2), Right))
    grid.rotateRight(Rover(Position(2,2), Down)) should be (Rover(Position(2,2), Left))
    grid.rotateRight(Rover(Position(2,2), Left)) should be (Rover(Position(2,2), Up))
    grid.rotateRight(Rover(Position(2,2), Right)) should be (Rover(Position(2,2), Down))
  }
}
