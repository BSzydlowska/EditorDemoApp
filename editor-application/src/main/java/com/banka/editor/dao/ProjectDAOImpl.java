package com.banka.editor.dao;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.tags.ThemeTag;

import com.banka.editor.entity.HoursByProject;
import com.banka.editor.entity.Project;
import com.banka.editor.entity.ProjectDetails;

@Repository
public class ProjectDAOImpl implements ProjectDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public List<Project> getProjects() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// create a query  ... sort by last name
		Query<Project> theQuery = 
				currentSession.createQuery("from Project",
						Project.class);
		
		// execute query and get result list
		List<Project> projects = theQuery.getResultList();
				
		// return the results		
		return projects;
	}

	@Override
	@Transactional
	public void saveProject(Project theProject) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theProject);
	}
	
	@Override
	@Transactional
	public void updateProjectDetails(ProjectDetails details) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save/upate the customer ... finally LOL
		currentSession.saveOrUpdate(details);
	}

	@Override
	@Transactional
	public Project getProject(int theId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using the primary key
		Project theProject = currentSession.get(Project.class, theId);
		
		return theProject;
	}

	@Override
	@Transactional
	public void deleteProject(int theId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<?> theQueryforDetails = 
				currentSession.createQuery("delete from ProjectDetails where projectId=:projectId");
		theQueryforDetails.setParameter("projectId", theId);
		
		theQueryforDetails.executeUpdate();
		// delete object with primary key
		Query<?> theQuery = 
				currentSession.createQuery("delete from Project where id=:projectId");
		theQuery.setParameter("projectId", theId);
		
		theQuery.executeUpdate();
		
		
	}
	
	@Override
	@Transactional
	public List<ProjectDetails> getProjectsDetails(int projId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// create a query  ... sort by last name
		Query<ProjectDetails> theQuery = 
				currentSession.createQuery("from ProjectDetails where projectId=:theId",
						ProjectDetails.class);
		
		theQuery.setParameter("theId", projId);
		
		// execute query and get result list
		List<ProjectDetails> details = theQuery.getResultList();
				
		// return the results		
		return details;
	}

	@Override
	@Transactional 
	public List<HoursByProject> getHoursByProject() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query theQuery = 
				currentSession.createQuery("SELECT new list(projectId, sum(hours)) from ProjectDetails group by projectId");
		
		List<HoursByProject> hours = theQuery.getResultList();
		return hours;  
	}
	
	@Override
	@Transactional
	public void deleteProjectDetails(int detailsId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object with primary key
		Query<?> theQuery = 
				currentSession.createQuery("delete from ProjectDetails where id=:detailsId");
		theQuery.setParameter("detailsId", detailsId);
		
		theQuery.executeUpdate();
	}

	@Override
	@Transactional
	public void closeProject(Project proj) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(proj);
		
	}
}
