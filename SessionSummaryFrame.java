package task1;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class SessionSummaryFrame extends JFrame {
    public SessionSummaryFrame() {
        setTitle("Session Summary");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        double income = DataStore.getTotalIncome();
        double expense = DataStore.getTotalExpense();
        double balance = DataStore.getBalance();

        Map<String, Double> totals = DataStore.getCategoryWiseTotals();
        String topCategory = "None";
        double max = 0;
        for (Map.Entry<String, Double> e : totals.entrySet()) {
            if (e.getValue() > max) {
                max = e.getValue();
                topCategory = e.getKey();
            }
        }

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));
        area.setText(String.format(
            "Total Income  : %.2f\nTotal Expense : %.2f\nBalance       : %.2f\n\nTop Category  : %s (%.2f)",
            income, expense, balance, topCategory, max));
        add(new JScrollPane(area), BorderLayout.CENTER);
    }
}
