package com.jonvallet.rover

import org.scalatest._
import flatspec._
import matchers._

class RoverBasicMovementSpec extends AnyFlatSpec with should.Matchers {
  "A Rover" should "move forward" in {
    moveForward(Rover(Position(2,2), Up)) should be (Rover(Position(1,2), Up))
    moveForward(Rover(Position(2,2), Down)) should be (Rover(Position(3,2), Down))
    moveForward(Rover(Position(2,2), Left)) should be (Rover(Position(2,1), Left))
    moveForward(Rover(Position(2,2), Right)) should be (Rover(Position(2,3), Right))
  }

  it should "turn left" in {
    moveLeft(Rover(Position(2,2), Up)) should be (Rover(Position(2,2), Left))
    moveLeft(Rover(Position(2,2), Down)) should be (Rover(Position(2,2), Right))
    moveLeft(Rover(Position(2,2), Left)) should be (Rover(Position(2,2), Down))
    moveLeft(Rover(Position(2,2), Right)) should be (Rover(Position(2,2), Up))
  }

  it should "turn right" in {
    moveRight(Rover(Position(2,2), Up)) should be (Rover(Position(2,2), Right))
    moveRight(Rover(Position(2,2), Down)) should be (Rover(Position(2,2), Left))
    moveRight(Rover(Position(2,2), Left)) should be (Rover(Position(2,2), Up))
    moveRight(Rover(Position(2,2), Right)) should be (Rover(Position(2,2), Down))
  }

  def moveRight(rover: Rover): Rover = {
    rover match {
      case Rover(_, Up) => Rover(rover.position, Right)
      case Rover(_, Down) => Rover(rover.position, Left)
      case Rover(_, Left) => Rover(rover.position, Up)
      case Rover(_, Right) => Rover(rover.position, Down)
    }
  }

  def moveLeft(rover: Rover): Rover = {
    rover match {
      case Rover(_, Up) => Rover(rover.position, Left)
      case Rover(_, Down) => Rover(rover.position, Right)
      case Rover(_, Left) => Rover(rover.position, Down)
      case Rover(_, Right) => Rover(rover.position, Up)
    }
  }

  def moveForward(rover: Rover): Rover = {
    rover match {
      case Rover(_, Up) => Rover(Position(rover.position.x -1, rover.position.y), Up)
      case Rover(_, Down) => Rover(Position(rover.position.x + 1, rover.position.y), Down)
      case Rover(_, Left) => Rover(Position(rover.position.x, rover.position.y - 1), Left)
      case Rover(_, Right) => Rover(Position(rover.position.x, rover.position.y + 1), Right)
    }
  }
}

case class Rover(position: Position, orientation: Orientation)
case class Position(x: Int, y: Int)
sealed trait Orientation
case object Up extends Orientation
case object Down extends Orientation
case object Left extends Orientation
case object Right extends Orientation
sealed trait Command
case object MoveForward extends Command
case object RotateLeft extends Command
case object RotateRight extends Command
