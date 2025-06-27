package task1;

import javax.swing.*;
import java.awt.*;

public class ViewBalanceFrame extends JFrame {
    public ViewBalanceFrame() {
        setTitle("Current Balance");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        double income = DataStore.getTotalIncome();
        double expense = DataStore.getTotalExpense();
        double balance = DataStore.getBalance();

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));
        area.setText(String.format("Total Income:  %.2f\nTotal Expense: %.2f\n\nBalance:       %.2f",
            income, expense, balance));

        add(area);
    }
}
