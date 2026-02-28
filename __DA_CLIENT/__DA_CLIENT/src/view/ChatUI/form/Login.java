package view.ChatUI.form;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import view.ChatUI.event.EventLogin;
import view.ChatUI.event.EventMessage;
import view.ChatUI.event.PublicEvent;
import view.ChatUI.swing.PanelSlide;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

import io.socket.client.Ack;
import model.Chat.Model_Message;
import model.Chat.Model_Register;
import model.Chat.Model_User_Account;
import service.Service;
import java.awt.Component;

public class Login extends JPanel {

    private PanelSlide slide;
	private P_Login login;
	private P_Register register;

    public Login(JFrame frameParent) {    	
    	setSize(1554, 850);
        
    	slide = new PanelSlide();
    	
        slide.setBackground(new java.awt.Color(255, 255, 255));
//        slide.setBackground(new java.awt.Color(200, 200, 200));
    	
    	GroupLayout groupLayout = new GroupLayout(this);
    	groupLayout.setHorizontalGroup(
    		groupLayout.createParallelGroup(Alignment.LEADING)
    			.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
    				.addContainerGap(840, Short.MAX_VALUE)
    				.addComponent(slide, 354, 354, 354)
    				.addGap(116))
    	);
    	groupLayout.setVerticalGroup(
    		groupLayout.createParallelGroup(Alignment.LEADING)
    			.addGroup(groupLayout.createSequentialGroup()
    				.addGap(174)
    				.addComponent(slide, 435, 435, 435)
    				.addGap(192))
    	);
    	setLayout(groupLayout);
        
        
        add(slide);

        PublicEvent.getInstance().addEventLogin(new EventLogin() {
            @Override
            public void login() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        PublicEvent.getInstance().getEventMain().initChat();
//                        Service.getInstance().listProject();
                        setVisible(false);
                        frameParent.dispose();
                    }
                }).start();
            }
            @Override
            public void register(Model_Register data, EventMessage message) {

            }
            @Override
            public void goRegister() {
                slide.show(0);
            }

            @Override
            public void goLogin() {
                slide.show(1);
            }
        });
      
        login = new P_Login();
        register = new P_Register();
//        slide.setLayout(new CardLayout());
        slide.setLayout(new FlowLayout());
        slide.init(register, login);
        
        
//    	JLabel background = new JLabel(new ImageIcon(getClass().getResource("/images/background/bg1.jpg")));
    	JLabel background = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/images/background/bg_login.jpg"))
    	        .getImage().getScaledInstance(1554, 850, Image.SCALE_SMOOTH)));
    	background.setBounds(0, 0, 1554, 850);
        background.setOpaque(false);
    	add(background);
    	setComponentZOrder(background, 0);
    	
    }

	public P_Login getLogin() {
		return login;
	}

	public P_Register getRegister() {
		return register;
	}
}
