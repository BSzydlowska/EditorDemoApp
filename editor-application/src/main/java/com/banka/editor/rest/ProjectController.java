package com.banka.editor.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banka.editor.entity.HoursByProject;
import com.banka.editor.entity.Project;
import com.banka.editor.entity.ProjectDetails;
import com.banka.editor.service.EditorService;

@RestController
@RequestMapping("/api")
public class ProjectController {

	// autowire the editorService
	@Autowired
	private EditorService editorService;
	
	// add mapping for GET /projects
	@GetMapping("/projects")
	public List<Project> getProjects() {
		
		return editorService.getProjects();
	}
	
	@GetMapping("/project/{theId}")
	public Project getProject(@PathVariable int theId) {
		
		return editorService.getProject(theId);
	}
	
	@PostMapping(path = "/projects")
	public Project saveProject(@RequestBody Project proj) {

		proj.setId(0);
		editorService.saveProject(proj);
		return proj;
	}
	
	@GetMapping("/details/{projId}")
	public List<ProjectDetails> getProjectsDetails(@PathVariable int projId) {
		
		return editorService.getProjectsDetails(projId);
	}
	
	@PostMapping(path = "/details")
	public ProjectDetails updateProjectDetails(@RequestBody ProjectDetails details) {

		details.setId(0);
		editorService.updateProjectDetails(details);
		return details;
	}
	
	@GetMapping("/hours")
	public List<HoursByProject> getHoursByProject() {
		
		return editorService.getHoursByProject();
	}
	
	@DeleteMapping("/deleteProject/{projId}")
	public void deleteProject(@PathVariable int projId) {
		
		editorService.deleteProject(projId);
	}
	
	@DeleteMapping("/deleteDetails/{detailsId}")
	public void deleteProjectDetails(@PathVariable int detailsId) {
		
		editorService.deleteProjectDetails(detailsId);
	}
	
	@PostMapping(path = "/closeOrOpen")
	public Project closeProject(@RequestBody Project proj) {

		editorService.closeProject(proj);
		return proj;
	}
}