package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.DatabaseConnection;
import model.Model_Calendar;
import model.Model_Post;
import model.Model_Project;
import model.Model_User_Account;

public class ServiceCalendar {
    private final Connection con;
    private int user_Id;
    private final String INSERT_CALENDAR = "INSERT INTO calendar (user_Id, title, day, timeStart, timeEnd, color) VALUES (?, ?, ?, ?, ?, ?)";
    private final String SELECT_CALENDAR = "SELECT title, day, timeStart, timeEnd, color FROM calendar WHERE user_Id=?";
    
    public ServiceCalendar(int user_Id) {
        this.con = DatabaseConnection.getInstance().getConnection();
        this.user_Id = user_Id;
    }
    
    public Model_Calendar addCalendar(String title, String day, String timeStart, String timeEnd, String color) {
    	Model_Calendar item = new Model_Calendar(0, title, day, timeStart, timeEnd, color);
    	try {
    		PreparedStatement p = con.prepareStatement(INSERT_CALENDAR, PreparedStatement.RETURN_GENERATED_KEYS);
            p.setInt(1, user_Id);
    		p.setString(2, title);
            p.setString(3, day);
            p.setString(4, timeStart);
            p.setString(5, timeEnd);
            p.setString(6, color);
            p.execute();
            ResultSet r = p.getGeneratedKeys();
            r.first();
            int calendar_Id = r.getInt(1);
            item.setCalendar_Id(calendar_Id);
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return item;
    }
    
    public List<Model_Calendar> getCalendar(int User_Id) {
        List<Model_Calendar> list = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(SELECT_CALENDAR);
            p.setInt(1, User_Id);
            ResultSet r = p.executeQuery();
            while (r.next()) {
            	String title = r.getString(1);
            	String day = r.getString(2);
            	String timeStart = r.getString(3);
            	String timeEnd = r.getString(4);
            	String color = r.getString(5);
            	
            	Model_Calendar item = new Model_Calendar(0, title, day, timeStart, timeEnd, color);
            	list.add(item);
            }
            r.close();
            p.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
    }
    
    
    

}
