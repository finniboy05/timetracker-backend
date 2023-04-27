package ch.coop.timetracker.project;

import ch.coop.timetracker.base.MessageResponse;
import ch.coop.timetracker.dataaccess.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    public Project getProject(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Project.class));
    }

    public Project insertProject(Project project) {
        return projectRepository.save(project);
    }

    public Project updateProject(Project project, Long id) {
        return projectRepository.findById(id)
                .map(employeeOrig -> {
                    employeeOrig.setName(project.getName());
                    return projectRepository.save(employeeOrig);
                })
                .orElseGet(() -> projectRepository.save(project));

    }

    public MessageResponse deleteProject(Long id) {
        projectRepository.deleteById(id);
        return new MessageResponse("Project " + id + " deleted");

    }

}
