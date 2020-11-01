## User Guide

Welcome to Nus Expenses Tracker(NET). NET is an app for managing expenses, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). 

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest NusExpensesTracker.jar from [here](https://github.com/AY2021S1-TIC4001-4/tp/releases).
3. Copy the file to the directory you want to use as the home directory for your Nus Expenses Tracker Application.
4. Open a command prompt / terminal window in that directory.
5. Run the command ```java -jar NusExpensesTracker.jar``` to open up the application.
6. Type the ```help``` in the command box and press ```Enter``` to execute it.

## Features
Help: Lists the commands the user can use to interact with the application. <br/>
Add: Add Transactions with optional date/time or category. E.g., add breakfast $5.00 10 Oct 2020 /c Food<br/>
Update: Update a transaction's attributes, description, amount, category...  <br/>
Total: Display the sum total spent from all transactions. E.g. The total amount you have spent so far is $13.90 <br/>
Delete: Delete transactions a specified transaction <br/>
View: View transactions that have been added. <br/>
Search: Find transactions using a specified keyword. <br/>
Add Budget: Add a Budget with optional date/time. E.g., budgetadd daily/food $20.00 <br/>
View Budget: View all budgets that have been added. <br/>
Generate Report: Export data into an Excel file (.xlsx) containing a summary of transactions with an optional specified time period. <br/>
Exit: Exit NUSExpenses Tracker <br/>

### Help
Format: help <br/>
Example: help <br/>
Output: <br/>
***************************************************
add: Adds a transaction to the NUS Expenses Tracker.
Parameters: DESCRIPTION $AMOUNT DATE...
Example: add Lunch at Com2 $4.50 14-Oct-2020
***************************************************

### Add
Format: add <description><amount><date><br/>
Example: add lime juice $1.80 10 Oct 2020 <br/>
Output: <br/>
***************************************************
New transaction added<br/>
***************************************************

### Update
Format: update <index> /d <date>(optional)/u <usage>(optional)/c <category>(optional)/a <amount><br/>
Example: update 1 /u dinner /d 2020-09-24 /a $5.0 /c FOOD <br/>
Output: <br/>
***************************************************
Transaction updated<br/>
***************************************************

### Total
Format: total  <br/>
Example: total <br/>
Output: <br/>
1.  Date: lunch Description: 3.5 Amount: 
2.  Date: lime juice Description: 1.8 Amount: 
3.  Date: dinner Description: 5.6 Amount: 
***************************************************
The total amount you have spent so far is $10.90 <br/>
***************************************************

### Delete
Format: delete {index number} - The index must be a positive integer 1, 2, 3,(...) <br/>
Example: delete 1 <br/>
Output: <br/>
***************************************************
Transaction deleted. <br/>
***************************************************

### View
Format 1: view {category} <br/>
Format 2: view /sd {date} /ed {date}
Format 3: view /sd {date}
Format 4: view /ed {date}
Example 1: view MISC <br/>
Output: <br/>
1.  Description: lunch Amount: $3.0 Category: MISC
2.  Description: dinner Amount: $4.0 Date: 2020-09-09 Category: MISC
***************************************************
Displaying category: MISC 
Above are all transaction entered.
***************************************************
Example 2: view /sd 2020-09-08 /ed 2020-09-10 <br/>
Output: <br/>
1.  Description: dinner Amount: $4.0 Date: 2020-09-09 Category: MISC
***************************************************
Displaying : 2020-09-08 - 2020-09-10
Above are all transaction entered.
***************************************************
Example 3: view /sd 2020-09-08
Output: <br/>
1.  Description: dinner Amount: $4.0 Date: 2020-09-09 Category: MISC
***************************************************
Displaying : Every Transaction After 2020-09-08
Above are all transaction entered.
***************************************************
Example 4: view /ed 2020-09-10
Output: <br/>
1.  Description: dinner Amount: $4.0 Date: 2020-09-09 Category: MISC
***************************************************
Displaying : Every Transaction Before 2020-09-10
Above are all transaction entered.
***************************************************

### Search
Format: search {keyword} <br/>
Example: search dinner <br/>
Output: <br/>
1.  Description: dinner Amount: $4.0 Date: 2020-09-09 Category: MISC
2.  Description:  dinner  Amount: $5.0 Date: 2020-09-24 Category: FOOD
***************************************************
2 transactions listed!
***************************************************

### Add Budget
Format: budgetadd {category} {/description} {amount}<br/>
Example: budgetadd daily /FOOD $20.00 <br/>
Output: <br/>
***************************************************
New Budget has been added<br/>
***************************************************

### View Budget
Format: budgetview <br/>
Example: budgetview <br/>
Output: <br/>
CATEGORY: DAILY || DESCRIPTION: FOOD || Budget : $50.0 <br/>
 Current Spending TODAY for CATEGORY FOOD is $5.0 <br/>
 You expenses is on track. Good Job!
***************************************************
Above are all budgets entered.
***************************************************

### Generate Report
Format: report /sd <start date>(optional) /ed <end date> (optional)<br/>
Example 1: report /sd 2020-09-28 /ed 2020-09-30 <br/>
Example 2: report
Output: <br/>
***************************************************
Report exported successfully.
***************************************************

## Saving the data <br/>
Saving is done  automatically after each command. It will be saved into the
expenses/mytransactions.txt is the directory where you can find the list of transactions being saved. <br/>

### Support or Contact <br/>
Having trouble with executing the program? Contact the team [here](https://ay2021s1-tic4001-4.github.io/tp/AboutUs.html) and We'll help you sort it out. 