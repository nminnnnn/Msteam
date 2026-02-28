package view.ChatUI.form;

import javax.swing.*;

import view.Panel_Register;
import view.ChatUI.event.EventMessage;
import view.ChatUI.event.PublicEvent;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.MD5;
import model.Chat.Model_Message;
import model.Chat.Model_Register;
import service.Service;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;

public class P_Register extends javax.swing.JPanel {
    private JButton cmdBackLogin;
    private JButton cmdRegister;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel lbTitle;
    private JPasswordField txtPass;
    private JPasswordField txtRePassword;
    private JTextField txtUser;
    private JLabel lbError;
	
    public P_Register() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    	
    	setSize(354, 435);
    	
        lbTitle = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 18));
        txtUser = new javax.swing.JTextField();
        txtUser.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        jLabel2 = new javax.swing.JLabel();
        jLabel2.setFont(new Font("Segoe UI", Font.BOLD, 18));
        txtPass = new javax.swing.JPasswordField();
        txtPass.setFont(new Font("Tahoma", Font.PLAIN, 20));
        cmdRegister = new javax.swing.JButton();
        cmdRegister.setFont(new Font("Tahoma", Font.BOLD, 20));
        cmdRegister.setIcon(new ImageIcon(getClass().getResource("/images/background/button_register.png")));
        cmdBackLogin = new javax.swing.JButton();
        txtRePassword = new javax.swing.JPasswordField();
        txtRePassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        jLabel3 = new javax.swing.JLabel();
        jLabel3.setFont(new Font("Segoe UI", Font.BOLD, 18));

        setBackground(new java.awt.Color(255, 255, 255));

        lbTitle.setFont(new Font("Segoe UI", Font.BOLD, 40)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(87, 87, 87));
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("Register");

        jLabel1.setText("User Name");

        jLabel2.setText("Password");

//        cmdRegister.setText("Register");
        cmdRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRegisterActionPerformed(evt);
            }
        });

        cmdBackLogin.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16)); // NOI18N
        cmdBackLogin.setForeground(new java.awt.Color(15, 128, 206));
        cmdBackLogin.setText("Back Login");
        cmdBackLogin.setContentAreaFilled(false);
        cmdBackLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdBackLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBackLoginActionPerformed(evt);
            }
        });

        jLabel3.setText("Confirm Password");
        
        lbError = new JLabel("");
        lbError.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
        lbError.setForeground(Color.RED);
        lbError.setHorizontalAlignment(SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addComponent(lbTitle, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(18)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(lbError, GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
        						.addGroup(layout.createSequentialGroup()
        							.addGap(10)
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(cmdRegister, GroupLayout.PREFERRED_SIZE, 305, Short.MAX_VALUE)
        								.addComponent(cmdBackLogin, GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)))))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(29)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(txtRePassword, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
        						.addComponent(jLabel3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
        						.addComponent(txtPass, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
        						.addComponent(jLabel2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
        						.addComponent(txtUser, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
        						.addComponent(jLabel1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE))
        					.addPreferredGap(ComponentPlacement.RELATED)))
        			.addGap(21))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(16)
        			.addComponent(lbTitle)
        			.addGap(18)
        			.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        			.addGap(2)
        			.addComponent(txtUser, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(txtPass, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(txtRePassword, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(cmdRegister, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(cmdBackLogin, GroupLayout.PREFERRED_SIZE, 23, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(lbError, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
        			.addGap(12))
        );
        this.setLayout(layout);
    }// </editor-fold>//GEN-END:initComponents

    private void cmdBackLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBackLoginActionPerformed
        PublicEvent.getInstance().getEventLogin().goLogin();
    }//GEN-LAST:event_cmdBackLoginActionPerformed

    private void cmdRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRegisterActionPerformed
        String userName = txtUser.getText().trim();
        String password = String.valueOf(txtPass.getPassword());
        String confirmPassword = String.valueOf(txtRePassword.getPassword());
        if (userName.equals("")) {
            txtUser.grabFocus();
        } else if (password.equals("")) {
            txtPass.grabFocus();
        } else if (!password.equals(confirmPassword)) {
        	txtRePassword.grabFocus();
        } else {
            Model_Register data = new Model_Register(userName, MD5.getMd5(password));
            Service.getInstance().sendRegister(data.toJsonObject());
        }
    }
    
    public void checkRegister() {
        Model_Message model_Message = Service.getInstance().getModel_message();
        if (!model_Message.isAction()) {
            lbError.setText(model_Message.getMessage());
            lbError.setForeground(Color.red);
        } else {
//            PublicEvent.getInstance().getEventLogin().goLogin();
        	lbError.setText(model_Message.getMessage());
        	lbError.setForeground(Color.green);
            
        	JDialog dialog = new JDialog();
        	Panel_Register register = new Panel_Register(dialog, txtUser.getText());
    		dialog.getContentPane().setLayout(new GridLayout(1,1));
    		dialog.setSize(1400, 620);
//    		dialog.setUndecorated(true);
    		dialog.setLocationRelativeTo(null);
        	dialog.getContentPane().add(register);
        	dialog.setVisible(true);
        }
    }


}
