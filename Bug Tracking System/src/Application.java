

import java.sql.*;
import java.util.List;

import com.project.daos.BugDAOImpl;
import com.project.entity.Bug;

public interface Application {
	
	public static void main(String[] args) {
		
		Date openDate= new Date(2020-12-15);
		
		
		//Bug bug= new Bug(555,111,101,"ABC","XYZ",openDate,false,openDate,100,"in process","high",102);
		
		BugDAOImpl dao= new BugDAOImpl();
		
		try {
			//dao.add(bug);
			
			
			List<Bug> bugList=dao.findList(101);
			
			bugList.forEach(System.out::println);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}

}
