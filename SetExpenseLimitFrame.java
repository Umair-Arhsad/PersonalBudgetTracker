package task1;

import javax.swing.*;

public class SetExpenseLimitFrame extends JFrame {
    private JTextField txtLimit;
    private JComboBox<String> cmbCategory;

    public SetExpenseLimitFrame() {
        setTitle("Set Expense Limit");
        setSize(350, 200);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lblCat = new JLabel("Category:");
        lblCat.setBounds(30, 30, 80, 25);
        add(lblCat);

        cmbCategory = new JComboBox<>(new String[]{"Food", "Travel", "Utilities", "Entertainment", "Health", "Other"});
        cmbCategory.setBounds(120, 30, 180, 25);
        add(cmbCategory);

        JLabel lblLimit = new JLabel("Limit:");
        lblLimit.setBounds(30, 70, 80, 25);
        add(lblLimit);

        txtLimit = new JTextField();
        txtLimit.setBounds(120, 70, 180, 25);
        add(txtLimit);

        JButton btnSet = new JButton("Set Limit");
        btnSet.setBounds(120, 110, 100, 30);
        btnSet.addActionListener(e -> {
            try {
                String category = (String) cmbCategory.getSelectedItem();
                double limit = Double.parseDouble(txtLimit.getText().trim());
                DataStore.setExpenseLimit(category, limit);
                JOptionPane.showMessageDialog(this, "Limit set for " + category);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input.");
            }
        });
        add(btnSet);
    }
}
