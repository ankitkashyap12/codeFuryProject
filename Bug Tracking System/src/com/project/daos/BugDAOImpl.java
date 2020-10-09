package com.project.daos;

import com.project.ifaces.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import com.project.entity.Bug;
import com.project.utils.ConnectionUtility;


public class BugDAOImpl implements BugDAO {

	Connection con = null;
	PreparedStatement psmt= null;
	List<Bug> bugList= null;
		
	public BugDAOImpl() {
		super();
		bugList= new ArrayList<>();
		
		try {
			con= ConnectionUtility.getDerbyConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}
	
	public boolean add(Bug bug) throws SQLException {
		// TODO Auto-generated method stub
		
		
		String sqlQuery= "insert into bug values(?,?,?,?,?,?,?,?,?,?,?,?)";
		
		psmt= con.prepareStatement(sqlQuery);
		
		psmt.setInt(1, bug.getBugId());
		psmt.setInt(2, bug.getProjectId());
		psmt.setInt(3, bug.getAssignedTo());
		
		psmt.setString(4, bug.getBugTitle());
		psmt.setString(5, bug.getBugDescription());
		psmt.setDate(6, bug.getOpenDate());
		psmt.setBoolean(7, bug.isMarkedForClosing());
		psmt.setDate(8, bug.getClosedOn());
		psmt.setInt(9,bug.getClosedBy());
		psmt.setString(10, bug.getBugStatus());
		psmt.setString(11, bug.getSeverityLevel());
		psmt.setInt(12, bug.getCreatedBy());
		
		int flag=psmt.executeUpdate();
		
		return (flag==1)?true:false;
	}



	@Override
	public List<Bug> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bug findById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(int id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<Bug> sortedByDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bug> findList(int id) throws SQLException {
		// TODO Auto-generated method stub
		
		bugList.clear();
		
		
		
		String sqlQuery= "select userType from userTable where userId=?";
		
		psmt= con.prepareStatement(sqlQuery);
		
		psmt.setInt(1, id);
		
		ResultSet rs= psmt.executeQuery();
		rs.next();
		String userType= rs.getString(1);
		
		System.out.println("Type-----------------------------"+userType.toLowerCase());
		
		
		
		switch (userType.toLowerCase()) {
		case "tester":
						sqlQuery= "select * from bug where createdBy=?";
						
						break;
			
		case "developer":
				sqlQuery= "select * from bug where assignedTo=?";
			
				break;

		default:
			break;
		}
		
		
		psmt= con.prepareStatement(sqlQuery);
		
		psmt.setInt(1, id);
		rs= psmt.executeQuery();
		
		while(rs.next())
		{

			Bug bug= new Bug();
			
			bug.setBugId(rs.getInt(1));
			bug.setProjectId(rs.getInt(2));
			bug.setAssignedTo(rs.getInt(3));
			
			bug.setBugTitle(rs.getString(4));
			
			bug.setBugDescription(rs.getString(5));
			
			bug.setOpenDate(rs.getDate(6));
			bug.setMarkedForClosing(rs.getBoolean(7));
			bug.setClosedOn(rs.getDate(8));
			bug.setClosedBy(rs.getInt(9));
			
			bug.setBugStatus(rs.getString(10));
			bug.setSeverityLevel(rs.getString(11));
			bug.setCreatedBy(rs.getInt(12));
			
			
			bugList.add(bug);
			
					
		}
		
		return bugList;
	}

	@Override
	public boolean add(Object t) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Object t) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	

}
