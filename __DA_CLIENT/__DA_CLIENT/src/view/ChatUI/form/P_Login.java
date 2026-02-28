package view.ChatUI.form;

import javax.swing.*;

import view.ChatUI.event.PublicEvent;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.MD5;
import model.Chat.Model_Login;
import model.Chat.Model_Message;
import model.Chat.Model_Register;
import service.Service;

import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;

public class P_Login extends JPanel{
    private JButton cmdLogin;
    private JButton cmdRegister;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel lbTitle;
    private JPasswordField txtPass;
    private JTextField txtUser;
    private JLabel lbError;
    
    public P_Login() {
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
        cmdLogin = new javax.swing.JButton();
        cmdLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cmdLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
        cmdLogin.setIcon(new ImageIcon(getClass().getResource("/images/background/button_login.png")));
        
        cmdRegister = new javax.swing.JButton();
        cmdRegister.setContentAreaFilled(false);

        setBackground(new Color(255, 255, 255));

        lbTitle.setFont(new Font("Segoe UI", Font.BOLD, 40)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(87, 87, 87));
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("Login");

        jLabel1.setText("User Name");

        jLabel2.setText("Password");

//        cmdLogin.setText("Login");
        cmdLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLoginActionPerformed(evt);
            }
        });

        cmdRegister.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16)); // NOI18N
        cmdRegister.setForeground(new java.awt.Color(15, 128, 206));
        cmdRegister.setText("Register");
        cmdRegister.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRegisterActionPerformed(evt);
            }
        });
        
        lbError = new JLabel("");
        lbError.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
        lbError.setForeground(Color.RED);
        lbError.setHorizontalAlignment(SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addComponent(lbTitle, GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(20)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(cmdLogin, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 317, Short.MAX_VALUE)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        						.addComponent(jLabel1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
        						.addComponent(jLabel2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
        						.addComponent(txtUser, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
        						.addComponent(txtPass, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE))
        					.addPreferredGap(ComponentPlacement.RELATED))
        				.addComponent(lbError, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
        				.addComponent(cmdRegister, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE))
        			.addGap(23))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(20)
        			.addComponent(lbTitle)
        			.addGap(18)
        			.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(txtUser, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        			.addGap(22)
        			.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 19, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(txtPass, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
        			.addGap(47)
        			.addComponent(cmdLogin, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(cmdRegister)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(lbError, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        			.addGap(27))
        );
        this.setLayout(layout);
    }
    
    private void cmdRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRegisterActionPerformed
        PublicEvent.getInstance().getEventLogin().goRegister();
    }//GEN-LAST:event_cmdRegisterActionPerformed

    private void cmdLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLoginActionPerformed
        String userName = txtUser.getText().trim();
        String password = String.valueOf(txtPass.getPassword());
        if (userName.equals("")) {
            txtUser.grabFocus();
        } else if (password.equals("")) {
            txtPass.grabFocus();
        } else {
            Model_Login data = new Model_Login(userName, MD5.getMd5(password));
            Service.getInstance().sendLogin(data.toJsonObject());
        }
    }
    
    public void checkLogin() {
        Model_Message model_Message = Service.getInstance().getModel_message();
        if (!model_Message.isAction()) {
            lbError.setText(model_Message.getMessage());
        } else {
            PublicEvent.getInstance().getEventLogin().login();
        }
    }
    
}
