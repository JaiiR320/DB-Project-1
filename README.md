# Section 1
Jair Meza, jdmeza

### Compiling and running
cd into this folder "DB-Project-1"

### Compiling
`javac -d bin -sourcepath src src/Main.java`

### Running
`java -cp bin Main arg1 arg2...`

### Running with jar (if applicable)
`java --enable-preview -jar JARFILENAME.jar arg1 arg2...`

# Section 2
All test cases are working as intended in the testcommand txt file. I have included a number of other tests that can be run by calling the test function in main, before the while loop. These test cases are also passing.

# Section 3

## Project Details
I used VSCode to write the program, so you may have to manually compile the project.
You could also just make a new project in your IDE and paste the source files in.

For writing new data to the "disk" I am just writing to a copy of the file, so that I maintain the original data for ease of debugging and running the program.