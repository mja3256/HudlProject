# HudlProject
Selenium testing framework for login

This repository contains the source code for testing the Hudl login page. Along with the the code, there are 2 runnable jar files that run the tests.
The 'HudlLoginSecured.jar' file is an executable jar file that was compiled using the code without the passwords, so it does not actually run the tests.
The other jar file, 'HudlTests.jar' was compiled using the actual email and password, so it is able to run the tests.

Therefore, to run these tests, you must download and run the HudlTests.jar file, which will go through a series of tests for proper and imporper logins.

In the source code, I created test cases to test the validity of various login credentials.
  1) The first test is to see if proper login credentials gets a user to the right page
  2, 3, 4, 5, 6) These tests ensure that a user is unable to access other parts of the page using invalid credentials.
  7) This test is to check the reliability of saving credentials by navigating back and forth between the login and home pages
  
Some other possible test cases could include testing the websites 'Remember Me' function to see if it is working and properly saving user information.
