package com.jonvallet.rover

case class Grid(rows: Int, columns: Int) {

  def rotateRight(rover: Rover): Rover = {
    val newPosition = rover match {
      case Rover(_, Up) => Rover(rover.position, Right)
      case Rover(_, Down) => Rover(rover.position, Left)
      case Rover(_, Left) => Rover(rover.position, Up)
      case Rover(_, Right) => Rover(rover.position, Down)
    }
    rollPosition(newPosition)
  }

  def applyCommand(command: Command, rover: Rover): Rover = {
    command match {
      case RotateLeft => rotateLeft(rover)
      case RotateRight => rotateRight(rover)
      case MoveForward => moveForward(rover)
    }
  }

  def rotateLeft(rover: Rover): Rover = {
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

  def path(rover: Rover, destination: Position): List[Command] = {
    pathRec(List(), rover, destination)
  }

  def roverStates(rover: Rover, destination: Position): List[Rover] = {
    val pathList = path(rover, destination)
    pathList.foldLeft(List(rover)) {
      (list, command) => list :+ applyCommand(command, list.last)
    }
  }

  private def orientationSymbol(orientation: Orientation):String = {
    orientation match {
      case Up => "↑"
      case Down => "↓"
      case Left => "←"
      case Right => "→"
    }
  }

  def printGrid(rover: Rover): Unit = {
    for {
      i <- 0 until rows
      j <- 0 until columns + 1
      cell = if (i == rover.position.y && j == rover.position.x) orientationSymbol(rover.orientation)
        else if (j < columns) "O"
        else "\n"
    } print(cell)
  }

  private def pathRec(path: List[Command], rover: Rover, destination: Position): List[Command] = {
    if (destination.y < rover.position.y) {
      rover.orientation match {
        case Up => return pathRec(path :+ MoveForward, moveForward(rover), destination)
        case Left => return pathRec(path :+ RotateLeft, rotateLeft(rover), destination)
        case Right => return pathRec(path :+ RotateRight, rotateRight(rover), destination)
        case Down => return pathRec(path :+ RotateRight, rotateRight(rover), destination)
      }
    }
    if (destination.y > rover.position.y) {
      rover.orientation match {
        case Down => return pathRec(path :+ MoveForward, moveForward(rover), destination)
        case Right => return pathRec(path :+ RotateRight, rotateRight(rover), destination)
        case Left => return pathRec(path :+ RotateLeft, rotateLeft(rover), destination)
        case Up => return pathRec(path :+ RotateRight, rotateRight(rover), destination)
      }
    }
    if (destination.x < rover.position.x) {
      rover.orientation match {
        case Left => return pathRec(path :+ MoveForward, moveForward(rover), destination)
        case Up => return pathRec(path :+ RotateLeft, rotateLeft(rover), destination)
        case Down => return pathRec(path :+ RotateRight, rotateRight(rover), destination)
        case Right => return pathRec(path :+ RotateRight, rotateRight(rover), destination)
      }
    }
    if (destination.x > rover.position.x) {
      rover.orientation match {
        case Right => return pathRec(path :+ MoveForward, moveForward(rover), destination)
        case Left => return pathRec(path :+ RotateLeft, rotateLeft(rover), destination)
        case Down => return pathRec(path :+ RotateLeft, rotateLeft(rover), destination)
        case Up => return pathRec(path :+ RotateRight, rotateRight(rover), destination)
      }
    }

    path
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
