# Mars Rover #
This is a small scala app that simulates the movement of the mars rover

## Running Requirement ##
This project has being test with sbt 1.5.4 and AdoptOpenJDK 11, but is very possible 
it will also work with older or newer versions.

## Testing the application ##
`sbt test`

## Running the application ##
`sbt run` should be enough.
You should get a similar output as this

```
Traveling from initial Position(2,2) to final destination Position(3,3) on a Grid(4,4)

░░░░
░░░░
░░↑░
░░░░

░░░░
░░░░
░░→░
░░░░

░░░░
░░░░
░░↓░
░░░░

░░░░
░░░░
░░░░
░░↓░

░░░░
░░░░
░░░░
░░→░

░░░░
░░░░
░░░░
░░░→


Process finished with exit code 0

```

## Caveats and nice to know ##
The autopilot logic is very basic, it will not consider using the border to find the shortest paths. I am not to happy
with the current logic and to do it properly I think I should consider a different approach.
Also, this version does not support obstacles.

You can edit the Main object to change the parameters.

Please check the Tests for examples of how to use the different methods.




