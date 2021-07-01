package com.jonvallet.rover

object Main extends App {

  val rover = Rover(Position(2,2), Up)
  val grid = Grid(4,4)
  val destination = Position(3,3)

  println(s"Traveling from initial ${rover.position} to final destination $destination on a $grid")
  println

  val roverStates = grid.roverStates(rover, destination)

  for (rover <- roverStates) {
    grid.printGrid(rover)
    println
  }
}
