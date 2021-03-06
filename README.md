Sudoku Solver
=================
Sudoku Solver developed in [java 11](https://www.oracle.com/fr/java/technologies/javase-downloads.html#JDK11). 
This project is to practice what I have seen during the Oracle [java SE 11 training](http://learn.oracle.com/ols/learning-path/java-se-11-developer/40805/79141).

Details
-----------------
This code go to pickup one grid into the website  [https://sudoku9x9.com](https://sudoku9x9.com).
Solve the grid with the [Backtracking](https://en.wikipedia.org/wiki/Backtracking) algorithm and fill the answer into the page.<br/>
To scrape and fill the grid I use [Selenium](https://www.selenium.dev/documentation/en/) with the [Firefox Driver](https://github.com/mozilla/geckodriver/releases). 
To run this code correctly, the java virtual must have the argument : `-Xmx2048m -Xms2048m` due to the recurcivity. You will find the Youtube link [here](https://www.youtube.com/watch?v=jUl-gDKmHX8).

Release notes
-----------------
-   07/12/2020:
    -   Backtracking optimized
-   04/12/2020:
    -   Initial release
    -   Updated to Firefox driver
    -   Updated java virtual machine argument(s)
