package converter;

import javax.swing.*;
import java.awt.*;

public class ConverterApp {

    private JFrame frame;

    private Converter[] converters = {
        new CurrencyConverter(),
        new TemperatureConverter(),
        new DistanceConverter()
    };

    private Converter activeConverter;

    private JComboBox<String> categoryBox;
    private JComboBox<String> fromUnitBox;
    private JComboBox<String> toUnitBox;
    private JTextField inputField;
    private JLabel resultLabel;
    private JLabel resultUnitLabel;

    public void show() {
        frame = new JFrame("Unit Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 420);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        frame.add(buildTitlePanel(),  BorderLayout.NORTH);
        frame.add(buildMainPanel(),   BorderLayout.CENTER);
        frame.add(buildResultPanel(), BorderLayout.SOUTH);

        activeConverter = converters[0];
        updateUnitBoxes();

        frame.setVisible(true);
    }

    private JPanel buildTitlePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 128, 128));
        panel.setBorder(BorderFactory.createEmptyBorder(14, 20, 14, 20));

        JLabel title = new JLabel("Unit Converter");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
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

        // Category dropdown
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(styledLabel("Category:"), gbc);

        gbc.gridx = 1;
        String[] categoryNames = {"Currency", "Temperature", "Distance"};
        categoryBox = new JComboBox<>(categoryNames);
        styleComboBox(categoryBox);
        categoryBox.addActionListener(e -> onCategoryChanged());
        panel.add(categoryBox, gbc);

        // Amount input
        gbc.gridx = 0; gbc.gridy = 1;
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
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(styledLabel("From:"), gbc);

        gbc.gridx = 1;
        fromUnitBox = new JComboBox<>();
        styleComboBox(fromUnitBox);
        panel.add(fromUnitBox, gbc);

        // To unit
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(styledLabel("To:"), gbc);

        gbc.gridx = 1;
        toUnitBox = new JComboBox<>();
        styleComboBox(toUnitBox);
        panel.add(toUnitBox, gbc);

        // Convert button
        gbc.gridx = 0; gbc.gridy = 4;
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

    private void onCategoryChanged() {
        int index = categoryBox.getSelectedIndex();
        activeConverter = converters[index];
        updateUnitBoxes();
        resultLabel.setText("—");
        resultUnitLabel.setText("");
        inputField.setText("");
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