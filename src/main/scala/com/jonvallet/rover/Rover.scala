package com.jonvallet.rover

case class Rover(position: Position, orientation: Orientation)
case class Position(x: Int, y: Int)
sealed trait Orientation
case object Up extends Orientation
case object Down extends Orientation
case object Left extends Orientation
case object Right extends Orientation

