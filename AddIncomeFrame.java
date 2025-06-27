package task1;

import javax.swing.*;

public class AddIncomeFrame extends JFrame {
    private JTextField txtAmount;

    public AddIncomeFrame() {
        setTitle("Add Income");
        setSize(300, 200);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lblAmount = new JLabel("Amount:");
        lblAmount.setBounds(30, 40, 80, 25);
        add(lblAmount);

        txtAmount = new JTextField();
        txtAmount.setBounds(100, 40, 150, 25);
        add(txtAmount);

        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(100, 90, 80, 30);
        btnAdd.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(txtAmount.getText().trim());
                DataStore.addIncome(amount);
                JOptionPane.showMessageDialog(this, "Income added.");
                txtAmount.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount.");
            }
        });
        add(btnAdd);
    }
}
