package com.banka.editor.service;

import java.util.List;

import com.banka.editor.entity.HoursByProject;
import com.banka.editor.entity.Project;
import com.banka.editor.entity.ProjectDetails;

public interface EditorService {
	
	public List<Project> getProjects();

	public void saveProject(Project theProject);

	public Project getProject(int theId);

	public void deleteProject(int theId);
	
	public List<ProjectDetails> getProjectsDetails(int projId);
	
	public List<HoursByProject> getHoursByProject();

	public void updateProjectDetails(ProjectDetails details);
	
	public void deleteProjectDetails(int detailsId);

	public void closeProject(Project proj);
}
