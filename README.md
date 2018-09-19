# SIMPLE I/O ROMAN GALACTIC BUSSINESS
This is the simple advanced roman-decimal bussiness programm written by java and for purposes Assignment test for prospace.io

## GETTING STARTED
Deploy All source code to the java project, then compile it and running.

### EXAMPLE USAGE
Input format is standarize given by rules on the document with the following example. any wrong input data was handling with validation on the structure inside the function of the code.

*Input:
```
glob is I
prok is V
pish is X
tegj is L

glob glob Silver is 34 Credits
glob prok Gold is 57800 Credits
pish pish Iron is 3910 Credits
how much is pish tegj glob glob ?
how many Credits is glob prok Silver ?
how many Credits is glob prok Gold ?
how many Credits is glob prok Iron ?
```
## WORKFLOW
```
START
[1] -> Initialize Core Class
[2] -> Waiting Users Input
[3] -> Incoming input submitted Throw to handler
[3] -> Check input 'is' that given the type of types to decide.
[4] -> handler would execute the decision of define or calculate the variable
[5][a-0] -> handler: decide to define variable
[5][a-1] -> handler: push define and their value
[5][b-0] -> handler: decide to calculate.
[5][b-1] -> handler: check the variable would exist
[5][b-2][a-0] -> handler: if the variable has folowing number, handler push variable and folowing number
[5][b-2][a-1] -> handler: handler would read the question input.
[5][b-2][a-2] -> handler: check all variable exist
[5][b-2][a-2]-a0 -> handler: if the variable exist, then get the expect number
[5][b-2][a-2]-a1 -> handler: Show the expect number
[5][b-2][a-2]-b0 -> handler: if the variable didnt exist, then terminate the process
[5][b-2][a-2]-b1 -> handler is terminated, waiting user input. BACK TO STATE -> [2]
[5][b-2][b-0] -> handler: if the variable hasnt folowing number, handler terminate the process
[5][b-2][b-1] -> handler terminated, Waiting user input. BACK TO STATE -> [2]
```


