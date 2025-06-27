package task1;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditRecordsFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;
    private JButton btnEdit, btnDelete;
    private final String[] columns = {"Type", "Amount", "Category", "Date"};

    public EditRecordsFrame() {
        setTitle("Edit/Delete Records");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoCreateRowSorter(true); // Enable sorting

        loadTableData();

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Delete");
        btnPanel.add(btnEdit);
        btnPanel.add(btnDelete);
        add(btnPanel, BorderLayout.SOUTH);

        btnDelete.addActionListener(e -> deleteSelected());
        btnEdit.addActionListener(e -> editSelected());
    }

    private void loadTableData() {
        model.setRowCount(0); // Clear existing
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (Transaction t : DataStore.transactions) {
            model.addRow(new Object[]{
                t.getType().toString(),
                t.getAmount(),
                (t.getType() == Transaction.Type.INCOME ? "-" : t.getCategory()),
                sdf.format(t.getDate())
            });
        }
    }

    private void deleteSelected() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            return;
        }

        int modelIndex = table.convertRowIndexToModel(row);
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this record?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            DataStore.deleteTransaction(modelIndex);
            loadTableData();
        }
    }

    private void editSelected() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to edit.");
            return;
        }

        int modelIndex = table.convertRowIndexToModel(row);
        Transaction t = DataStore.transactions.get(modelIndex);

        JTextField amountField = new JTextField(String.valueOf(t.getAmount()));
        JTextField categoryField = new JTextField(t.getCategory() == null ? "" : t.getCategory());
        JTextField dateField = new JTextField(new SimpleDateFormat("yyyy-MM-dd").format(t.getDate()));

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Amount:"));
        panel.add(amountField);
        if (t.getType() == Transaction.Type.EXPENSE) {
            panel.add(new JLabel("Category:"));
            panel.add(categoryField);
        }
        panel.add(new JLabel("Date (yyyy-MM-dd):"));
        panel.add(dateField);

        int result = JOptionPane.showConfirmDialog(this, panel,
                "Edit Record", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                double newAmount = Double.parseDouble(amountField.getText().trim());
                if (newAmount <= 0) throw new NumberFormatException();
                Date newDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateField.getText().trim());

                t.setAmount(newAmount);
                t.setDate(newDate);
                if (t.getType() == Transaction.Type.EXPENSE) {
                    t.setCategory(categoryField.getText().trim());
                }

                loadTableData();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please check values.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EditRecordsFrame().setVisible(true));
    }
}
