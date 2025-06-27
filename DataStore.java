package task1;

import java.util.*;

public class DataStore {
    public static List<Transaction> transactions = new ArrayList<>();
    public static Map<String, Double> expenseLimits = new HashMap<>();

    public static void addIncome(double amount) {
        transactions.add(new Transaction(Transaction.Type.INCOME, amount));
    }

    public static void addExpense(double amount, String category) {
        transactions.add(new Transaction(Transaction.Type.EXPENSE, amount, category));
    }

    public static void setExpenseLimit(String category, double limit) {
        expenseLimits.put(category, limit);
    }

    public static boolean isLimitExceeded(String category, double newAmount) {
        double spent = 0;
        for (Transaction t : transactions) {
            if (t.getType() == Transaction.Type.EXPENSE && t.getCategory().equals(category)) {
                spent += t.getAmount();
            }
        }
        return (spent + newAmount > expenseLimits.getOrDefault(category, Double.MAX_VALUE));
    }

    public static double getTotalIncome() {
        return transactions.stream()
            .filter(t -> t.getType() == Transaction.Type.INCOME)
            .mapToDouble(Transaction::getAmount).sum();
    }

    public static double getTotalExpense() {
        return transactions.stream()
            .filter(t -> t.getType() == Transaction.Type.EXPENSE)
            .mapToDouble(Transaction::getAmount).sum();
    }

    public static double getBalance() {
        return getTotalIncome() - getTotalExpense();
    }

    public static Map<String, Double> getCategoryWiseTotals() {
        Map<String, Double> totals = new HashMap<>();
        for (Transaction t : transactions) {
            if (t.getType() == Transaction.Type.EXPENSE) {
                totals.put(t.getCategory(), totals.getOrDefault(t.getCategory(), 0.0) + t.getAmount());
            }
        }
        return totals;
    }

    public static void updateTransaction(int index, Transaction updated) {
        if (index >= 0 && index < transactions.size()) {
            transactions.set(index, updated);
        }
    }

    public static void deleteTransaction(int index) {
        if (index >= 0 && index < transactions.size()) {
            transactions.remove(index);
        }
    }

    public static void resetAllData() {
        transactions.clear();
        expenseLimits.clear();
    }
}
