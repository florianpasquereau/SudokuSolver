Sudoku Solver
=================
Sudoku Solver developed in [java 11](https://www.oracle.com/fr/java/technologies/javase-downloads.html#JDK11). 
This project is to practice what I have seen during the Oracle [java SE 11 training](http://learn.oracle.com/ols/learning-path/java-se-11-developer/40805/79141).

Details
-----------------
This code go to pickup one grid into the website  [https://sudoku9x9.com](https://sudoku9x9.com).
Solve the grid with the [Backtracking](https://en.wikipedia.org/wiki/Backtracking) algorithm and fill the answer into the page.<br/>
To scrap and fill the grid I use [Selenium](https://www.selenium.dev/documentation/en/) with the [Chrome driver](https://chromedriver.chromium.org/downloads) of my current Chrome release. 
To run the java virtual machine memory is set to 2GO. withe the argument : `-Xmx2048m`

Release notes
-----------------
-   04/12/2020:
    -   Initial release