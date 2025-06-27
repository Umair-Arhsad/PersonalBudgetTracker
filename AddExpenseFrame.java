package task1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddExpenseFrame extends JFrame {

    private JTextField txtAmount;
    private JComboBox<String> cmbCategory;

    public AddExpenseFrame() {
        setTitle("Add Expense");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitle = new JLabel("Add Expense");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(100, 10, 200, 30);
        add(lblTitle);

        JLabel lblAmount = new JLabel("Amount:");
        lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblAmount.setBounds(50, 60, 100, 25);
        add(lblAmount);

        txtAmount = new JTextField();
        txtAmount.setBounds(150, 60, 180, 25);
        add(txtAmount);
        txtAmount.setColumns(10);

        JLabel lblCategory = new JLabel("Category:");
        lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCategory.setBounds(50, 100, 100, 25);
        add(lblCategory);

        cmbCategory = new JComboBox<>(new String[]{
            "Food", "Travel", "Utilities", "Entertainment", "Health", "Other"
        });
        cmbCategory.setBounds(150, 100, 180, 25);
        add(cmbCategory);

        JButton btnSubmit = new JButton("Add Expense");
        btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnSubmit.setBounds(130, 150, 120, 30);
        add(btnSubmit);

        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addExpense();
            }
        });
    }

    private void addExpense() {
        try {
            String amountText = txtAmount.getText().trim();
            if (amountText.isEmpty()) throw new NumberFormatException("Empty input");

            double amount = Double.parseDouble(amountText);
            if (amount <= 0) throw new NumberFormatException("Amount must be positive");

            String category = (String) cmbCategory.getSelectedItem();

            // Check limit
            if (DataStore.isLimitExceeded(category, amount)) {
                JOptionPane.showMessageDialog(this,
                    "Warning: Expense limit for '" + category + "' is about to be exceeded!",
                    "Limit Warning", JOptionPane.WARNING_MESSAGE);
            }

            // Add to datastore
            DataStore.addExpense(amount, category);

            JOptionPane.showMessageDialog(this,
                    "Expense added successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);

            txtAmount.setText("");
            cmbCategory.setSelectedIndex(0);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a valid positive number for amount.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddExpenseFrame().setVisible(true));
    }
}
