package com.project.daos;

import com.project.ifaces.*;

import java.sql.*;
import java.time.LocalDate;
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
		
		
		String sqlQuery= "insert into bug(projectId,title,bugDescription,openDate,severityLevel,createdBy) values(?,?,?,?,?,?)";
		
		psmt= con.prepareStatement(sqlQuery);
		
		
		psmt.setInt(1, bug.getProjectId());
		
		
		psmt.setString(2, bug.getBugTitle());
		psmt.setString(3, bug.getBugDescription());
		
		Date openDate = Date.valueOf(LocalDate.now());
		
		psmt.setDate(4, openDate);
		
		psmt.setString(5, bug.getSeverityLevel());
		psmt.setInt(6, bug.getCreatedBy());
		
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
	public List<Bug> findList(int userId) throws SQLException {
		// TODO Auto-generated method stub
		
		bugList.clear();
		
		
		
		String sqlQuery= "select userType from userTable where userId=?";
		
		psmt= con.prepareStatement(sqlQuery);
		
		psmt.setInt(1, userId);
		
		ResultSet rs= psmt.executeQuery();
		rs.next();
		String userType= rs.getString(1);
		
		System.out.println("Type-----------------------------"+userType.toLowerCase());
		
		
		
		switch (userType.toLowerCase()) {
		case "tester":
						sqlQuery= "select * from bug where createdBy=? and bugStatus!='closed'";
						
						break;
			
		case "developer":
				sqlQuery= "select * from bug where assignedTo=? and bugStatus!='closed'";
			
				break;
			
		case "manager":
				sqlQuery= "select * from bug where projectId in (select projectId from team where userId=?)";
			
				break;		

		default:
			break;
		}
		
		
		psmt= con.prepareStatement(sqlQuery);
		
		psmt.setInt(1, userId);
		rs= psmt.executeQuery();
		
		while(rs.next())
		{

			Bug bug= new Bug();
			
			bug.setBugId(rs.getInt(1));
			bug.setProjectId(rs.getInt(2));
			bug.setAssignedTo(rs.getInt(3));
			
			bug.setBugTitle(rs.getString(4));
			
			bug.setBugDescription(rs.getString(5));
			
			
			Date openDate= rs.getDate(6);
			LocalDate openLocalDate = openDate.toLocalDate();
			bug.setOpenDate(openLocalDate);
			
			
			bug.setMarkedForClosing(rs.getBoolean(7));
			
			
			Date closedDate= rs.getDate(8);
			LocalDate closedLocalDate = closedDate.toLocalDate();
			bug.setClosedOn(closedLocalDate);
			
			bug.setClosedBy(rs.getInt(9));
			
			bug.setBugStatus(rs.getString(10));
			bug.setSeverityLevel(rs.getString(11));
			bug.setCreatedBy(rs.getInt(12));
			
			
			bugList.add(bug);
			
					
		}
		
		return bugList;
	}


	@Override
	public boolean update(int bugId) throws SQLException {
		// TODO Auto-generated method stub
		
		
		String sqlQuery = "update bug set markedForClosing=true where bugId=?";
		
		psmt= con.prepareStatement(sqlQuery);
		
		psmt.setInt(1, bugId);
		
		int flag= psmt.executeUpdate();
		
		return flag==1?true:false;
		
	}

	

}
