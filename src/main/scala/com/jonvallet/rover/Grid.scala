package com.jonvallet.rover

case class Grid(rows: Int, columns: Int) {
  def moveRight(rover: Rover): Rover = {
    val newPosition = rover match {
      case Rover(_, Up) => Rover(rover.position, Right)
      case Rover(_, Down) => Rover(rover.position, Left)
      case Rover(_, Left) => Rover(rover.position, Up)
      case Rover(_, Right) => Rover(rover.position, Down)
    }
    rollPosition(newPosition)
  }

  def moveLeft(rover: Rover): Rover = {
    val newPosition = rover match {
      case Rover(_, Up) => Rover(rover.position, Left)
      case Rover(_, Down) => Rover(rover.position, Right)
      case Rover(_, Left) => Rover(rover.position, Down)
      case Rover(_, Right) => Rover(rover.position, Up)
    }
    rollPosition(newPosition)
  }

  def moveForward(rover: Rover): Rover = {
    val newPosition = rover match {
      case Rover(_, Up) => Rover(Position(rover.position.x, rover.position.y - 1), Up)
      case Rover(_, Down) => Rover(Position(rover.position.x, rover.position.y + 1), Down)
      case Rover(_, Left) => Rover(Position(rover.position.x - 1, rover.position.y), Left)
      case Rover(_, Right) => Rover(Position(rover.position.x + 1, rover.position.y), Right)
    }
    rollPosition(newPosition)
  }

  private def rollAxis(coordinate: Int, size: Int): Int = {
    coordinate match {
      case n if n < 0 => size - 1
      case n if n >= size => 0
      case _ => coordinate
    }
  }

  private def rollPosition(rover: Rover): Rover = {
    val x = rollAxis(rover.position.x, columns)
    val y = rollAxis(rover.position.y, rows)
    Rover(Position(x,y), rover.orientation)
  }
}
