package converter;

import javax.swing.*;
import java.awt.*;

public class ConverterApp {

    private JFrame frame;
    private Converter activeConverter;

    private JComboBox<String> fromUnitBox;
    private JComboBox<String> toUnitBox;
    private JTextField inputField;
    private JLabel resultLabel;
    private JLabel resultUnitLabel;

    // Constructor now accepts whichever converter was selected on welcome screen
    public ConverterApp(Converter converter) {
        this.activeConverter = converter;
    }

    public void show() {
        frame = new JFrame("Unit Converter - " + activeConverter.getCategoryName());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 420);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        frame.add(buildTitlePanel(),  BorderLayout.NORTH);
        frame.add(buildMainPanel(),   BorderLayout.CENTER);
        frame.add(buildResultPanel(), BorderLayout.SOUTH);

        updateUnitBoxes();

        frame.setVisible(true);
    }

    private JPanel buildTitlePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 128, 128));
        panel.setBorder(BorderFactory.createEmptyBorder(14, 20, 14, 20));

        JLabel title = new JLabel(activeConverter.getCategoryName() + " Converter");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.WHITE);

        JButton backBtn = new JButton("← Back");
        backBtn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        backBtn.setBackground(new Color(0, 100, 100));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.setBorderPainted(false);
        backBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backBtn.addActionListener(e -> {
            frame.dispose();
            WelcomeScreen welcome = new WelcomeScreen();
            welcome.show();
        });

        panel.add(backBtn);
        panel.add(title);
        return panel;
    }

    private JPanel buildMainPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 248, 248));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 40, 10, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets  = new Insets(8, 8, 8, 8);
        gbc.fill    = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Amount input
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(styledLabel("Amount:"), gbc);

        gbc.gridx = 1;
        inputField = new JTextField();
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        inputField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 150, 150)),
            BorderFactory.createEmptyBorder(6, 8, 6, 8)
        ));
        panel.add(inputField, gbc);

        // From unit
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(styledLabel("From:"), gbc);

        gbc.gridx = 1;
        fromUnitBox = new JComboBox<>();
        styleComboBox(fromUnitBox);
        panel.add(fromUnitBox, gbc);

        // To unit
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(styledLabel("To:"), gbc);

        gbc.gridx = 1;
        toUnitBox = new JComboBox<>();
        styleComboBox(toUnitBox);
        panel.add(toUnitBox, gbc);

        // Convert button
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(16, 8, 4, 8);
        JButton convertBtn = new JButton("Convert");
        convertBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        convertBtn.setBackground(new Color(0, 128, 128));
        convertBtn.setForeground(Color.WHITE);
        convertBtn.setFocusPainted(false);
        convertBtn.setBorderPainted(false);
        convertBtn.setPreferredSize(new Dimension(0, 40));
        convertBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        convertBtn.addActionListener(e -> performConversion());
        panel.add(convertBtn, gbc);

        return panel;
    }

    private JPanel buildResultPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 16));
        panel.setBackground(new Color(210, 240, 240));
        panel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(0, 150, 150)));

        JLabel resultPrompt = new JLabel("Result:");
        resultPrompt.setFont(new Font("Segoe UI", Font.BOLD, 15));
        resultPrompt.setForeground(new Color(0, 80, 80));

        resultLabel = new JLabel("—");
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        resultLabel.setForeground(new Color(0, 128, 128));

        resultUnitLabel = new JLabel("");
        resultUnitLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        resultUnitLabel.setForeground(new Color(0, 100, 100));

        panel.add(resultPrompt);
        panel.add(resultLabel);
        panel.add(resultUnitLabel);
        return panel;
    }

    private void updateUnitBoxes() {
        String[] units = activeConverter.getUnits();

        fromUnitBox.removeAllItems();
        toUnitBox.removeAllItems();

        for (String unit : units) {
            fromUnitBox.addItem(unit);
            toUnitBox.addItem(unit);
        }
        if (toUnitBox.getItemCount() > 1)
            toUnitBox.setSelectedIndex(1);
    }

    private void performConversion() {
        String input = inputField.getText().trim();

        if (input.isEmpty()) {
            resultLabel.setText("—");
            resultUnitLabel.setText("");
            return;
        }

        try {
            double value    = Double.parseDouble(input);
            String fromUnit = (String) fromUnitBox.getSelectedItem();
            String toUnit   = (String) toUnitBox.getSelectedItem();
            double result   = activeConverter.convert(value, fromUnit, toUnit);

            if (result == Math.floor(result) && !Double.isInfinite(result))
                resultLabel.setText(String.format("%.0f", result));
            else
                resultLabel.setText(String.format("%.4f", result));

            if (activeConverter instanceof CurrencyConverter) {
                CurrencyConverter cc = (CurrencyConverter) activeConverter;
                resultUnitLabel.setText(cc.getFullName(toUnit));
            } else {
                resultUnitLabel.setText(toUnit);
            }

        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input");
            resultUnitLabel.setText("");
        }
    }

    private JLabel styledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setForeground(new Color(0, 80, 80));
        return label;
    }

    private void styleComboBox(JComboBox<String> box) {
        box.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        box.setBackground(Color.WHITE);
        box.setBorder(BorderFactory.createLineBorder(new Color(0, 150, 150)));
    }
}