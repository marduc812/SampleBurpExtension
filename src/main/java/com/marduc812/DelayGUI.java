package com.marduc812;

import burp.api.montoya.logging.Logging;
import burp.api.montoya.persistence.PersistedObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.marduc812.Main.DELAY_TIME;
public class DelayGUI extends JPanel {
    Logging logging;
    PersistedObject persistence;
    public DelayGUI(PersistedObject persistence, Logging logging) {
        this.logging = logging;
        this.persistence = persistence;

        Integer delayT = persistence.getInteger(DELAY_TIME);

        JLabel label = new JLabel("Delay in ms: ");
        JTextField delayInput = new JTextField(Integer.toString(delayT),6);
        JButton saveBtn = new JButton("Save");

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String delayText = delayInput.getText();
                try {
                    int delay = Integer.parseInt(delayText);
                    if (delay < 0 || delay > 999999) {
                        throw new Exception("Invalid Size");
                    }
                    persistence.setInteger(DELAY_TIME, delay);
                    logging.raiseInfoEvent("Delay time set to: " + delay);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(DelayGUI.this, "Invalid value. allowed values are from 0 to 999999");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        this.add(label);
        this.add(delayInput);
        this.add(saveBtn);
    }

}
