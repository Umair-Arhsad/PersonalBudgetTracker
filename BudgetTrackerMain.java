package task1;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BudgetTrackerMain extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                BudgetTrackerMain frame = new BudgetTrackerMain();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public BudgetTrackerMain() {
        setTitle("Budget Tracker - Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 500);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(new BorderLayout(10, 10));
        setContentPane(contentPane);

        JLabel lblTitle = new JLabel("Personal Budget Tracker", JLabel.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
        contentPane.add(lblTitle, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 2, 10, 10));

        String[] buttons = {
            "Add Income", "Add Expense",
            "View Balance", "Expense Report",
            "Set Expense Limits", "Edit/Delete Records",
            "Session Summary Report", "Reset Data",
            "Exit"
        };

        for (String label : buttons) {
            JButton btn = new JButton(label);
            btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
            btn.addActionListener(e -> handleButton(label));
            buttonPanel.add(btn);
        }

        contentPane.add(buttonPanel, BorderLayout.CENTER);
    }

    private void handleButton(String action) {
        switch (action) {
            case "Add Income":
                new AddIncomeFrame().setVisible(true);
                break;
            case "Add Expense":
                new AddExpenseFrame().setVisible(true);
                break;
            case "View Balance":
                new ViewBalanceFrame().setVisible(true);
                break;
            case "Expense Report":
                new CategoryReportFrame().setVisible(true);
                break;
            case "Set Expense Limits":
                new SetExpenseLimitFrame().setVisible(true);
                break;
            case "Edit/Delete Records":
                new EditRecordsFrame().setVisible(true);
                break;
            case "Session Summary Report":
                new SessionSummaryFrame().setVisible(true);
                break;
            case "Reset Data":
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to reset all data?",
                        "Confirm Reset", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    DataStore.resetAllData();
                    JOptionPane.showMessageDialog(this, "All records have been reset.");
                }
                break;
            case "Exit":
                System.exit(0);
                break;
        }
    }
}
