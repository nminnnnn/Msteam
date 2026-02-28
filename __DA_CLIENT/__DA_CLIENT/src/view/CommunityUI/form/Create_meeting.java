package view.CommunityUI.form;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import com.toedter.calendar.JDateChooser;

import model.community.Model_Project;
import net.miginfocom.swing.MigLayout;

public class Create_meeting extends JPanel{
	private Model_Project project;
	private JDateChooser dateChooser;
	private JTextField tf_title;
	private JSpinner spinner;
	private JSpinner spinner2;
	private String day;
	public Create_meeting(Model_Project project) {
		this.project = project;
		
    	Font fontAdd = new Font("tohoma", Font.BOLD, 20);
        setSize(300, 300);
        setLayout(new MigLayout("fillx, filly", "10[100!]10[fill, 100%]10", "0[]10"));
        
        JLabel lb_title = new JLabel("Title");
        lb_title.setFont(fontAdd);
        tf_title = new JTextField();
        tf_title.setFont(fontAdd);
        add(lb_title);
        add(tf_title, "wrap");
        JLabel lb_day = new JLabel("Day");
        lb_day.setFont(fontAdd);
        add(lb_day);
        dateChooser = new JDateChooser();
        dateChooser.setFont(fontAdd);
        add(dateChooser, "wrap");
        
        JLabel lb_timeStart = new JLabel("Time start");
        lb_timeStart.setFont(fontAdd);
        add(lb_timeStart);
        
        SpinnerDateModel model = new SpinnerDateModel();
        spinner = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm");
        spinner.setEditor(editor);
        spinner.setFont(fontAdd);
        add(spinner, "wrap, width 100:125:150");
        
        JLabel lb_timeEnd = new JLabel("Time end");
        lb_timeEnd.setFont(fontAdd);
        add(lb_timeEnd);
        
        SpinnerDateModel model2 = new SpinnerDateModel();
        spinner2 = new JSpinner(model2);
        JSpinner.DateEditor editor2 = new JSpinner.DateEditor(spinner2, "HH:mm");
        spinner2.setEditor(editor2);
        spinner2.setFont(fontAdd);
        add(spinner2, "wrap, width 100:125:150");
        
	}
	public Model_Project getProject() {
		return project;
	}
	public String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String selectedDate = sdf.format(dateChooser.getDate());
        Date startTime = (Date) spinner.getValue();
        SimpleDateFormat fm = new SimpleDateFormat("HH:mm");
        String start = fm.format(startTime);
        Date endTime = (Date) spinner2.getValue();
        String end = fm.format(endTime);
        
        return start + " - " + end + " | " + selectedDate;
	}
	public JTextField getTf_title() {
		return tf_title;
	}
	
	
	
	
}
