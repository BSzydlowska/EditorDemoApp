package com.banka.editor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banka.editor.dao.ProjectDAO;
import com.banka.editor.entity.HoursByProject;
import com.banka.editor.entity.Project;
import com.banka.editor.entity.ProjectDetails;

@Service
public class EditorServiceImpl implements EditorService {

	@Autowired
	private ProjectDAO projectDAO;
	
	@Override
	public List<Project> getProjects() {
		
		return projectDAO.getProjects();
	}

	@Override
	public void saveProject(Project theProject) {
		
		projectDAO.saveProject(theProject);

	}
	
	@Override
	public void updateProjectDetails(ProjectDetails details) {
		projectDAO.updateProjectDetails(details);
		
	}

	@Override
	public Project getProject(int theId) {
		
		return projectDAO.getProject(theId);
	}

	@Override
	public void deleteProject(int theId) {
		
		projectDAO.deleteProject(theId);
	}

	@Override
	public List<ProjectDetails> getProjectsDetails(int projId) {
		
		return projectDAO.getProjectsDetails(projId);
	}

	@Override
	public List<HoursByProject> getHoursByProject() {
		
		return projectDAO.getHoursByProject();
	}

	@Override
	public void deleteProjectDetails(int detailsId) {
		
		projectDAO.deleteProjectDetails(detailsId);
		
	}

	@Override
	public void closeProject(Project proj) {
		projectDAO.closeProject(proj);
		
	}	

	
}
