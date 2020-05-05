# Java Object Oriented Programming: Personal Project 

## *A library Management System*

**What does it do?**

This application keeps a digital record of all items ever been in stock upon reception
from the vendor and track of when they are checked out. 


**Who will use it?**

My application initially targets library 
record keepers that can now digitize their records. 
Through doing this, they will be able to keep a more reliable 
back-up of their crucial data that is key in monitoring trends in popularity of books.

**Why is this project of interest to you?**

Libraries have always been the type of organizations that must sort lots of data 
and continually be able to keep track of the data. This requires creating catalogues, 
classifying books into genres, being able to track what is available at any time, 
and being able to continually have a smooth flow of checking in and checking out books.


## *User Stories*
- As a user, I want to be able to add incoming items received to the library catalogue.
- As a user, I want to be able to search the entire list of the all items.
- As a user, I want to be able to view a list of all checked out items at any given time.
- As a user, I want to be able to modify status of items : checked in or checked out.
- As a user, I want to be able to save the items in the inventory when I exit.
- As a user, I want to be able to reload my inventory upon running the library portal app.

Instructions for Grader

Run the menu file in the gui package

You can generate the first required event by clicking the Add Book Button (will open a pop up window to get the fields
for the constructor)
You can generate the second required event by pressing 'n' key on the keyboard (will open a pop up window to get the fields
for the constructor)

You can trigger my audio component by clicking the reload button
You can save the state of my application by clicking the save button
You can reload the state of my application by clicking the reload button

Additional events related to X's - Check in, check out, and renew buttons use the selected row and update cells in the 
table as necessary. Search goes through all the items and determines if searched item is found or not (can be performed
by either pressing enter in the search bar or clicking the search button)

Phase 4: Task 2
Changes made:
Type Hierarchy created: model.Item is now an abstract superclass
to model.Book and model.Magazine. A new interface was also created IIssuableItem 
that is implemented in the abstract class Item. Both Book and Magazine class override
the getItemInfo method from super class Item and issueItem (an implementation from the interface IIssuable Item).
They both have distinct functionality since book allows checkout to happen, however magazine when an attempt to check out
is made, throws an exception called ItemNotIssuableException.

Phase 4: Task 3 (Code Analysis)
Problem 1: Menu class deals with all changes made to data, adding data(Reload), and retrieval of data information. (Poor Cohesion)
Solution Implemented: Separated into three classes: UpdateTableData, InformationRetrieval, and Menu.
UpdateTableData contains checkIn, checkOut and renew methods.
InformationRetrieval contains searching associated with search bar, list of CheckedOutBooks, and operation of the moreInfo
button.
Menu class only contains instances of UpdateTableData and InformationRetrieval, and itself only saves and reloads existing data
(from text file).
addBook method in Menu moved to addItemPopUp.

Problem 2: Lots of coupling within the UpdateTableData Class all the methods have nearly identical methods for all three calls.
Solution: Refactor to group same code together, and use new methods within the old ones: (getValues and modify) .

Citations:
The TellerApp inspired the additional code that added the persistence feature to this Library portal.
