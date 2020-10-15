## User Guide

Big hello to Nus Expenses Tracker(NET). NET is an app for managing expenses, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). 

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest NusExpensesTracker.jar from [here](<links>).
3. Copy the file to the folder you want to use as the home folder for your Nus Expenses Tracker Application.
4. Open a command window in that folder.
5. Run the command java -jar NusExpensesTracker.jar to open up the applcation.
6. Type the command in the command box and press Enter to execute it.

## Features (v1.0)
Add: transactions with any date/time attached to it e.g., add breakfast $5.00 10 Oct 2020 <br/>
Total: total transactions amount will be displayed e.g., The total amount you have spent so far is $13.90 <br/>
Delete: delete transactions that you specified <br/>
View: list down all the transactions that has been added <br/>
Search: find keywords in the transactions <br/>

### Add
Format: add <description><amount><date><br/>
Example: add lime juice $1.80 10 Oct 2020 <br/>
Output: <br/>
New transaction added<br/>

### Total
Format: total  <br/>
Example: total <br/>
Output: <br/>
1.  Date: lunch Description: 3.5 Amount: 
2.  Date: lime juice Description: 1.8 Amount: 
3.  Date: dinner Description: 5.6 Amount: 
***************************************************
The total amount you have spent so far is $10.90 <br/>


### Delete
Format: delete {index number} - The index must be a positive integer 1, 2, 3,(...) <br/>
Example: delete 1 <br/>
Output: <br/>
Transaction deleted. <br/>

### View
Format: list <br/>
Example: list <br/>
Output: <br/>
1.  Date: lunch Description: 3.5 Amount: 
2.  Date: lime juice Description: 1.8 Amount: 
3.  Date: dinner Description: 5.6 Amount: 

### Search
Format: search {keyword} <br/>
Example: find lime juice <br/>
Output: <br/>

## Saving the data <br/>
Data/NusExpensesTracker.txt is the directory where you can find the list of transactions being saved. <br/>

### Support or Contact <br/>
Having trouble with executing the program? Email the team [here](e0261618@u.nus.edu) and We'll help you sort it out. 
