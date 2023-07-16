package projects.service;

import projects.Project;
import projects.dao.ProjectDao;

//this class serves as a passthrough between the java code and the dao.
public class ProjectService {

	private ProjectDao projectDao = new ProjectDao();
	
	public Project addProject(Project project) {
		return projectDao.insertProject(project);
		
}
}
