package view.CalendarUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.*;

import com.mindfusion.common.DateTime;
import com.mindfusion.drawing.SolidBrush;
import com.mindfusion.scheduling.Calendar;
import com.mindfusion.scheduling.CalendarAdapter;
import com.mindfusion.scheduling.ItemMouseEvent;
import com.mindfusion.scheduling.ResourceDateEvent;
import com.mindfusion.scheduling.ThemeType;
import com.mindfusion.scheduling.model.Appointment;

import model.calendar.Model_Calendar;
import net.miginfocom.swing.MigLayout;
import service.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CalendarUI extends JPanel{
	
	private DateTime previousSelectedDate = null;
	private Calendar calendar;
	private DateTime selectedDate;
	private ArrayList<Appointment> addItems = new ArrayList<>();
	private int red, blue, green;
	
	public CalendarUI (){
		
		setLayout(new GridLayout(1, 1));
		calendar = new Calendar();

		calendar.addCalendarListener(new CalendarAdapter() {
		    @Override
		    public void dateClick(ResourceDateEvent e) {
		        selectedDate = e.getDate();
		        // Kiểm tra xem ngày được chọn có tồn tại không
		        if (selectedDate != null) {
		            // Kiểm tra xem có ngày trước đó được chọn hay không
//		            if (previousSelectedDate != null && !previousSelectedDate.equals(selectedDate)) {
		        	if (previousSelectedDate != null) {
		        	// Nếu có, xóa tất cả các mục "ADD" liên kết với ngày trước đó
		                removeAddItems();
		            }
		            Appointment app = new Appointment();
		            app.setStartTime(selectedDate);
		            app.setEndTime(selectedDate);
		            app.setHeaderText("ADD");        
		            
		            // Thêm mục "ADD" vào danh sách tạm thời
		            addItems.add(app);		          
		            // Thêm mục "ADD" vào lịch
		            calendar.getSchedule().getItems().add(app);            
		            // Cập nhật ngày được chọn trước đó
		            previousSelectedDate = selectedDate;
		        }
		    }

		    @Override
		    public void itemClick(ItemMouseEvent e) {
		        if (e.getItem().getHeaderText().equals("ADD")) {
	        	
		        	Font fontAdd = new Font("tohoma", Font.BOLD, 20);
		            JPanel panel_add = new JPanel();
		            panel_add.setSize(300, 300);
		            panel_add.setLayout(new MigLayout("fillx, filly", "10[100!]10[fill, 100%]10", "0[]10"));
		            
		            JLabel lb_title = new JLabel("Title");
		            lb_title.setFont(fontAdd);
		            JTextField tf_title = new JTextField();
		            tf_title.setFont(fontAdd);
		            panel_add.add(lb_title);
		            panel_add.add(tf_title, "wrap");
		            JLabel lb_day = new JLabel("Day");
		            lb_day.setFont(fontAdd);
		            JTextField tf_day = new JTextField(selectedDate.toLongDateString());
		            tf_day.setFont(fontAdd);
		            panel_add.add(lb_day);
		            panel_add.add(tf_day, "wrap");
		            JLabel lb_timeStart = new JLabel("Time start");
		            lb_timeStart.setFont(fontAdd);
		            panel_add.add(lb_timeStart);
		            
		            SpinnerDateModel model = new SpinnerDateModel();
		            JSpinner spinner = new JSpinner(model);
		            JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm");
		            spinner.setEditor(editor);
		            spinner.setFont(fontAdd);
		            panel_add.add(spinner, "wrap, width 100:125:150");
		            
		            JLabel lb_timeEnd = new JLabel("Time end");
		            lb_timeEnd.setFont(fontAdd);
		            panel_add.add(lb_timeEnd);
		            
		            SpinnerDateModel model2 = new SpinnerDateModel();
		            JSpinner spinner2 = new JSpinner(model2);
		            JSpinner.DateEditor editor2 = new JSpinner.DateEditor(spinner2, "HH:mm");
		            spinner2.setEditor(editor2);
		            spinner2.setFont(fontAdd);
		            panel_add.add(spinner2, "wrap, width 100:125:150");
		            
		            JLabel lb_color = new JLabel("Color");
		            lb_color.setFont(fontAdd);
		            panel_add.add(lb_color);
		           
		            JButton bt_choseColor = new JButton();	  
		            bt_choseColor.setPreferredSize(new Dimension(80, 30));
		            bt_choseColor.setBackground(new Color(51, 255, 51));
		            red = 51; green = 255; blue = 51;
		            bt_choseColor.addActionListener(new ActionListener() {
		    			public void actionPerformed(ActionEvent e) {
		    				Color selectedColor = JColorChooser.showDialog(null, "Chọn màu", Color.WHITE);		
				            if (selectedColor != null) {
				                red = selectedColor.getRed();
				                green = selectedColor.getGreen();
				                blue = selectedColor.getBlue();		                
				                bt_choseColor.setBackground(new Color(red, green, blue));
				            }
		    			}
		    		});
		            panel_add.add(bt_choseColor, "wrap, width 50:80:100");

		            int option = JOptionPane.showOptionDialog(calendar, panel_add, "Thông báo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
		            if (option == JOptionPane.OK_OPTION) {
		                // Tạo một mục "ADD" mới với dữ liệu từ hộp thoại nhập liệu
		                Appointment app = new Appointment();
		                app.setStartTime(selectedDate);
		                app.setEndTime(selectedDate);
		                Date startTime = (Date) spinner.getValue();
		                SimpleDateFormat fm = new SimpleDateFormat("HH:mm");
		                String start = fm.format(startTime);
		                Date endTime = (Date) spinner2.getValue();
		                String end = fm.format(endTime);
		                app.setHeaderText(start + "-" + end +" | "+ tf_title.getText());
		                app.getStyle().setFont(new Font("arial", Font.BOLD, 15));
		                app.getStyle().setBrush(new SolidBrush(new Color(red, green, blue)));
		                app.setLocked(true);
		                // Thêm mục "ADD" vào danh sách tạm thời
		                addItems.add(app);
		
		                // Thêm mục "ADD" vào lịch
		                calendar.getSchedule().getItems().add(app);
		                
//		                SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy, hh:mm:ss a zzz", Locale.ENGLISH);
//
//		                // Chuyển từ DateTime sang String
//		                String dateString = dateFormat.format(selectedDate);
		                addCalendarToServer(tf_title.getText(), selectedDate.toShortDateString(), start, end, red + "," + green + "," + blue);
		            } else {
		                // Xử lý khi người dùng nhấn Cancel hoặc đóng cửa sổ
		            }
		        }
		        else {
		        	Font fontAdd = new Font("tohoma", Font.BOLD, 20);
		            JPanel panel_add = new JPanel();
		            panel_add.setSize(300, 300);
		            panel_add.setLayout(new MigLayout("fillx, filly", "10[100!]10[fill, 100%]10", "0[]10"));
		            
		            String[] parts = e.getItem().getHeaderText().split("\\|");
			        String title = parts[1].trim();
			        String time = parts[0].trim();
		            JLabel lb_title = new JLabel("Title");
		            lb_title.setFont(fontAdd);
		            JTextField tf_title = new JTextField(title);
		            tf_title.setFont(fontAdd);
		            panel_add.add(lb_title);
		            panel_add.add(tf_title, "wrap");
		            JLabel lb_day = new JLabel("Day");
		            lb_day.setFont(fontAdd);
		            JTextField tf_day = new JTextField(selectedDate.toLongDateString());
		            tf_day.setFont(fontAdd);
		            panel_add.add(lb_day);
		            panel_add.add(tf_day, "wrap");
		            
		            String[] times = time.split("-");
			        String st = times[0].trim();
			        String en = times[1].trim();
		            JLabel lb_timeStart = new JLabel("Time start");
		            lb_timeStart.setFont(fontAdd);
		            panel_add.add(lb_timeStart);
		            JSpinner spinner = null;
		            String start = "";
		            try {
		                // Tạo đối tượng SimpleDateFormat để phân tích chuỗi thời gian
		                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		                
		                // Chuyển đổi chuỗi thời gian thành đối tượng Date
		                Date date = format.parse(st);
		                
		                // Tạo một SpinnerDateModel và đặt giá trị của nó thành thời gian đã chuyển đổi
		                SpinnerDateModel model = new SpinnerDateModel();
		                model.setValue(date);
		                
		                // Tạo JSpinner với SpinnerDateModel đã tạo
		                spinner = new JSpinner(model);
		                
		                // Tạo JSpinner.DateEditor với định dạng "HH:mm" và gắn vào spinner
		                JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm");
		                spinner.setEditor(editor);
		                
		                // Thêm spinner vào giao diện của bạn
		                // Ví dụ: frame.add(spinner);
		                
		                Date startTime = (Date) spinner.getValue();
		                SimpleDateFormat fm = new SimpleDateFormat("HH:mm");
		                start = fm.format(startTime);
		                
		                spinner.setFont(fontAdd);
			            panel_add.add(spinner, "wrap, width 100:125:150");
		            } catch (Exception e1) {
		                e1.printStackTrace();
		            }
		            
//		            SpinnerDateModel model = new SpinnerDateModel();
//		            JSpinner spinner = new JSpinner(model);
//		            JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm");
//		            spinner.setEditor(editor);
//		            spinner.setFont(fontAdd);
//		            panel_add.add(spinner, "wrap, width 100:125:150");
		            
		            JLabel lb_timeEnd = new JLabel("Time end");
		            lb_timeEnd.setFont(fontAdd);
		            panel_add.add(lb_timeEnd);
		            JSpinner spinner2 = null;
		            String end = "";
		            try {
		                // Tạo đối tượng SimpleDateFormat để phân tích chuỗi thời gian
		                SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
		                
		                // Chuyển đổi chuỗi thời gian thành đối tượng Date
		                Date date2 = format2.parse(en);
		                
		                // Tạo một SpinnerDateModel và đặt giá trị của nó thành thời gian đã chuyển đổi
		                SpinnerDateModel model2 = new SpinnerDateModel();
		                model2.setValue(date2);
		                
		                // Tạo JSpinner với SpinnerDateModel đã tạo
		                spinner2 = new JSpinner(model2);
		                
		                // Tạo JSpinner.DateEditor với định dạng "HH:mm" và gắn vào spinner
		                JSpinner.DateEditor editor2 = new JSpinner.DateEditor(spinner2, "HH:mm");
		                spinner2.setEditor(editor2);
		                
		                // Thêm spinner vào giao diện của bạn
		                // Ví dụ: frame.add(spinner);
		                
		                Date endTime = (Date) spinner2.getValue();
		                SimpleDateFormat fm2 = new SimpleDateFormat("HH:mm");
		                end = fm2.format(endTime);
		                
		                spinner2.setFont(fontAdd);
			            panel_add.add(spinner2, "wrap, width 100:125:150");
		            } catch (Exception e1) {
		                e1.printStackTrace();
		            }
		            
		            JLabel lb_color = new JLabel("Color");
		            lb_color.setFont(fontAdd);
		            panel_add.add(lb_color);
		           
		            JButton bt_choseColor = new JButton();	  
		            bt_choseColor.setPreferredSize(new Dimension(80, 30));
		            SolidBrush brush = (SolidBrush) e.getItem().getStyle().getBrush();
		            bt_choseColor.setBackground(brush.getColor());
		            red = brush.getColor().getRed();
		            green = brush.getColor().getGreen();
		            blue = brush.getColor().getBlue();
		            bt_choseColor.addActionListener(new ActionListener() {
		    			public void actionPerformed(ActionEvent e) {
		    				Color selectedColor = JColorChooser.showDialog(null, "Chọn màu", Color.WHITE);		
				            if (selectedColor != null) {
				                red = selectedColor.getRed();
				                green = selectedColor.getGreen();
				                blue = selectedColor.getBlue();		                
				                bt_choseColor.setBackground(new Color(red, green, blue));
				            }
		    			}
		    		});
		            panel_add.add(bt_choseColor, "wrap, width 50:80:100");

		            int option1 = JOptionPane.showOptionDialog(calendar, panel_add, "Thông báo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
		        	
		            if (option1 == JOptionPane.OK_OPTION) {
//		                // Tạo một mục "ADD" mới với dữ liệu từ hộp thoại nhập liệu
//		                Appointment app = new Appointment();
//		                app.setStartTime(selectedDate);
//		                app.setEndTime(selectedDate);
		                Date startTime = (Date) spinner.getValue();
		                SimpleDateFormat fm = new SimpleDateFormat("HH:mm");
		                String start1 = fm.format(startTime);
		                Date endTime = (Date) spinner2.getValue();
		                String end1 = fm.format(endTime);
		                e.getItem().setHeaderText(start1 + "-" + end1 +" | "+ tf_title.getText());
//		                app.getStyle().setFont(new Font("arial", Font.BOLD, 15));
		                e.getItem().getStyle().setBrush(new SolidBrush(new Color(red, green, blue)));
//		                app.setLocked(true);
//		                // Thêm mục "ADD" vào danh sách tạm thời
//		                addItems.add(app);
//		
//		                // Thêm mục "ADD" vào lịch
//		                calendar.getSchedule().getItems().add(app);
		                
//		                updateCalendarToServer(tf_title.getText(), selectedDate.toShortDateString(), start, end, red + "," + green + "," + blue);
		            } else {
		                // Xử lý khi người dùng nhấn Cancel hoặc đóng cửa sổ
		            }
		        }
		    }
		});

		calendar.setFont(new Font("Arial", Font.BOLD, 15));
		calendar.setBounds(0, 0, 856, 623);
		calendar.setTheme(ThemeType.Light);
		add(calendar);
	}
	
	private void removeAddItems() {
	    Iterator<Appointment> iterator = addItems.iterator();
	    while (iterator.hasNext()) {
	        Appointment item = iterator.next();
	        if ("ADD".equals(item.getHeaderText()) || item.getHeaderText().isEmpty()) {
	            calendar.getSchedule().getItems().remove(item);
	            iterator.remove();
	        }
	    }
	}
	
	public void removeAllItems() {
	    Iterator<Appointment> iterator = addItems.iterator();
	    while (iterator.hasNext()) {
	        Appointment item = iterator.next();
            calendar.getSchedule().getItems().remove(item);
            iterator.remove();
	    }
	}
	
	public void addCalendarToServer(String title, String day, String timeStart, String timeEnd, String color) {
		Model_Calendar item = new Model_Calendar(0, title, day, timeStart, timeEnd, color);
		Service.getInstance().addCalendar(item.toJsonObject("addCalendar"));
	}
	
	public void updateCalendarToServer(String title, String day, String timeStart, String timeEnd, String color) {
		Model_Calendar item = new Model_Calendar(0, title, day, timeStart, timeEnd, color);
		Service.getInstance().updateCalendar(item.toJsonObject("updateCalendar"));
	}
	
	
	
	
	public void addCalendarFromServer(Model_Calendar item) {
        SimpleDateFormat formatter = new SimpleDateFormat("M/d/yy");
        DateTime dateTime = null;
        
            Date date;
			try {
				date = formatter.parse(item.getDay());
	            dateTime = new DateTime(date);

			} catch (ParseException e) {
				e.printStackTrace();
			}
		
		Appointment app = new Appointment();
		app.setStartTime(dateTime); // Chuyển đổi DateTime thành Date
		app.setEndTime(dateTime);   // Chuyển đổi DateTime thành Date
        
        app.setHeaderText(item.getTimeStart() + "-" + item.getTimeEnd() +" | "+ item.getTitle());
        app.getStyle().setFont(new Font("arial", Font.BOLD, 15));
        
        String[] colorComponents = item.getColor().split(",");
        red = Integer.parseInt(colorComponents[0]);
        green = Integer.parseInt(colorComponents[1]);
        blue = Integer.parseInt(colorComponents[2]);
        app.getStyle().setBrush(new SolidBrush(new Color(red, green, blue)));
        app.setLocked(true);
        // Thêm mục "ADD" vào danh sách tạm thời
        addItems.add(app);

        // Thêm mục "ADD" vào lịch
        calendar.getSchedule().getItems().add(app);
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}
	
	
}