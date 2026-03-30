package converter;

import javax.swing.*;
import java.awt.*;

public class WelcomeScreen {

    private JFrame frame;

    public void show() {
        frame = new JFrame("Unit Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 450);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        frame.add(buildTitlePanel(),  BorderLayout.NORTH);
        frame.add(buildButtonPanel(), BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private JPanel buildTitlePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 128, 128));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Unit Converter");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel("Select a category to get started");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(new Color(200, 235, 235));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(new Color(0, 128, 128));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPanel.add(title);
        textPanel.add(Box.createVerticalStrut(6));
        textPanel.add(subtitle);

        panel.add(textPanel);
        return panel;
    }

    private JPanel buildButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 0, 15));
        panel.setBackground(new Color(240, 248, 248));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        panel.add(categoryButton("Currency",    "Convert between world currencies", new CurrencyConverter()));
        panel.add(categoryButton("Temperature", "Celsius, Fahrenheit and Kelvin", new TemperatureConverter()));
        panel.add(categoryButton("Distance",    "Kilometres, Miles and Metres", new DistanceConverter()));
        panel.add(categoryButton("Weight",      "Kilograms, Pounds and Stone", new WeightConverter()));

        return panel;
    }

    private JButton categoryButton(String title, String subtitle, Converter converter) {
        JButton button = new JButton("<html><b>" + title + "</b><br><font size='3' color='#aadddd'>" + subtitle + "</font></html>");
        button.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        button.setBackground(new Color(0, 128, 128));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));

        button.addActionListener(e -> {
            frame.dispose();
            ConverterApp app = new ConverterApp(converter);
            app.show();
        });

        return button;
    }

}
