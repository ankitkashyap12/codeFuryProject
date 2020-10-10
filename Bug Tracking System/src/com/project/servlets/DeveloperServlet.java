package com.project.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.daos.BugDAOImpl;
import com.project.daos.TeamDAOImpl;
import com.project.entity.Bug;
import com.project.ifaces.BugDAO;
import com.project.ifaces.TeamDAO;

/**
 * Servlet implementation class DeveloperServlet
 */
@WebServlet(urlPatterns = {"/developer/*"})
public class DeveloperServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BugDAO bugDAO= null;
	List<Bug> bugList=null;
	Bug bug;
	HttpSession session;
	
	
		@Override
		public void init() throws ServletException {
			// TODO Auto-generated method stub
			
	    	bugDAO= new BugDAOImpl();
	    	bugList= new ArrayList<Bug>();
	    	bug= new Bug();
	    		    	
	    }

	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeveloperServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String action = request.getPathInfo();
		
		
		if(action == null)
			action="/";
		
					
		try {
			
			
			switch (action) {
			case "/update":
						
						updateMarkedForClosing(request, response);
						break;
						
			case "/logout":
				
				logout(request, response);
				break;

				
			default:
					displayBugList(request, response);
		
				break;
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	
	public void updateMarkedForClosing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int bugId=0;
		String[] bugIds=null;
		
		try 
		{
			bugIds= request.getParameterValues("notMarked");
			if(bugIds.length > 0)
			{
				for(String eachitem:bugIds)
				{
					try 
					{
						bugId= Integer.parseInt(eachitem);
						bugDAO.update(bugId);
					}
					catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			
		} catch (Exception e) {
			System.out.println("NULL POINTER EXCEPTION");
		}
		finally {
			RequestDispatcher dispatcher = request.getRequestDispatcher("developer");
			dispatcher.forward(request, response);			
		}
		
			
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		
		dispatcher.forward(request, response);
		
		
	}
	
	public void displayBugList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		bugList.clear();
		try {
			bugList=bugDAO.findList(101);
			
			Predicate<Bug> testPredicate= bug->(bug.isMarkedForClosing()==true);
			
			Stream<Bug> markedTrueList= bugList.stream().filter(testPredicate);
			Stream<Bug> markedFalseList= bugList.stream().filter(testPredicate.negate());
			
			bugList = Stream.concat(markedFalseList, markedTrueList).collect(Collectors.toList());
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("listOfBugs", bugList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/developerPage.jsp");
		dispatcher.forward(request, response);
		
		
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
