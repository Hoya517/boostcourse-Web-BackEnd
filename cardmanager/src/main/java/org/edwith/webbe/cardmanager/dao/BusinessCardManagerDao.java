package org.edwith.webbe.cardmanager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.edwith.webbe.cardmanager.dto.BusinessCard;

public class BusinessCardManagerDao {
	private Connection conn;
	private ResultSet rs;
	
	public BusinessCardManagerDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e ) {
		 	e.printStackTrace();
		}
		try{
			String dbURL = "jdbc:mysql://localhost:3306/CLI?serverTimezone=UTC";
			String dbID = "root";
			String dbPassword = "hoy2158831a@";
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
    public List<BusinessCard> searchBusinessCard(String keyword){
    	List<BusinessCard> list = new ArrayList<>();
    	String SQL = "SELECT * FROM cli where name = ?";
    	try {
    		PreparedStatement pstmt = conn.prepareStatement(SQL);
    		pstmt.setString(1, keyword);
    		rs = pstmt.executeQuery();
    		while (rs.next()) {
    			BusinessCard businessCard = new BusinessCard(rs.getString(1), rs.getString(2),rs.getString(3));
    			list.add(businessCard);
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return list;
    }

    public BusinessCard addBusinessCard(BusinessCard businessCard){
    	String SQL = "INSERT INTO `CLI`.`cli` (`name`, `phone`, `companyName`, `createDate`) VALUES (?, ?, ?, ?)";
    	try {
    		PreparedStatement pstmt = conn.prepareStatement(SQL);
    		pstmt.setString(1, businessCard.getName());
    		pstmt.setString(2, businessCard.getPhone());
    		pstmt.setString(3, businessCard.getCompanyName());
    		pstmt.setObject(4, businessCard.getCreateDate());
    		pstmt.executeUpdate();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
		return null;
    }
}
