/*********************************** 
* Filename: Expense.java
* Author: Aiden McCollum
* Created: March 28th, 2025
* Purpose: A subclass for items that are listed as an expense.
*
* Attributes:
* - expenseType: String
* - timespan: int
*
* Methods:
* + Expense(String, String, float, int, String, String, String, String, int)
* + getExpenseType(): String
* + setExpenseType(String): void
* + getTimespan(): int
* + setTimespan(int): void
* + toString(): String
***********************************/

public class Expense extends Item {

    String expenseType = "";
    int timespan = 0;

    public Expense(String id, String itemName, float unitCost, int quantity, String supplier, String status, String location, String type, int duration) {
        super(id, itemName, unitCost, quantity, supplier, status,location);

        expenseType = type;
        timespan = duration;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String type) {
        expenseType = type;
    }

    public int getTimespan() {
        return timespan;
    }

    public void setTimespan(int duration) {
        timespan = duration;
    }

    @Override
    public String toString() {
        return super.toString() + 
               "\n  Expense Type: " + expenseType + 
               "\n  Timespan: " + timespan;
    }

};