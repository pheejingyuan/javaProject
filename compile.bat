@REM Have to compile the individual dependencies first. 
javac -d class -cp lib/*;src src/Faculty/Faculty.java
javac -d class -cp lib/*;src src/Admin/Admin.java
javac -d class -cp lib/*;src src/Student/Student.java

javac -d class -cp lib/*;class;src src/Main.java

java -cp class;lib/* Main