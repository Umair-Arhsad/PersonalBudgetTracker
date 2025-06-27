package task1;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class CategoryReportFrame extends JFrame {
    public CategoryReportFrame() {
        setTitle("Category-wise Expense Report");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextArea area = new JTextArea();
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));
        area.setEditable(false);

        StringBuilder sb = new StringBuilder("Category-wise Expense Report:\n\n");
        for (Map.Entry<String, Double> entry : DataStore.getCategoryWiseTotals().entrySet()) {
            sb.append(String.format("%-15s : %.2f\n", entry.getKey(), entry.getValue()));
        }

        area.setText(sb.toString());
        add(new JScrollPane(area), BorderLayout.CENTER);
    }
}
