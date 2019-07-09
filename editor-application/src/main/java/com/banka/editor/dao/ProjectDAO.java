package com.banka.editor.dao;

import java.util.List;

import com.banka.editor.entity.HoursByProject;
import com.banka.editor.entity.Project;
import com.banka.editor.entity.ProjectDetails;

public interface ProjectDAO {

		public List<Project> getProjects();

		public void saveProject(Project theProject);

		public Project getProject(int theId);

		public void deleteProject(int theId);
		
		public List<ProjectDetails> getProjectsDetails(int projId);
		
		public void deleteProjectDetails(int theId);
		
		public List<HoursByProject> getHoursByProject();

		public void updateProjectDetails(ProjectDetails details);

		public void closeProject(Project proj);
}