package com.github.mstraub.swing;

import java.awt.FlowLayout;

import javax.swing.*;

public class SimpleSwingExample {

	public static void main(String s[]) {

		JFrame frame = new JFrame("JFrame Example");
		frame.setAlwaysOnTop(true);

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());

		JLabel label = new JLabel("This is a label!");

		JButton button = new JButton();
		button.setText("Press me");

		panel.add(label);
		panel.add(button);

		frame.add(panel);
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
