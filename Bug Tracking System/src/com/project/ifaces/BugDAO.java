package com.project.ifaces;

import java.util.Collection;

import com.project.entity.Bug;

public interface BugDAO<T> extends DAO<T> {
	
	Collection<Bug> sortedByDate(); 	//Sort By StartDate using StartDateComparator

}
