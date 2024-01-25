package To_Do_List.shar1140.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import To_Do_List.shar1140.beans.Task;
import To_Do_List.shar1140.database.DatabaseAccess;


@Controller
public class TaskController {
	@Autowired
	DatabaseAccess da;
	
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/permission-denied")
	public String permissionDenied() {
		return "/error/permission-denied";
	}
	
	@GetMapping("/register")
	public String getRegister () {
	return "register";
	}
	

	@PostMapping("/secure/login")
	public String afterLogin(Model model) {
		model.addAttribute("task",new Task());
		model.addAttribute("taskList",da.getTask());
		return "/secure/tasks";
	}
	
	@PostMapping("/register")
	public String postRegister(@RequestParam String username, @RequestParam String password) {
	da.addUser(username, password);
	Long userId = da.findUserAccount(username).getUserId();
	// this next line is dangerous! For extra credit, try making a DatabaseAccess method to find a roleId
	// associate with a role of “ROLE_USER” and add the “correct” id every time
	da.addRole(userId, Long.valueOf(1));
	return "register";
	}

	
	@GetMapping("/")
	public String index(Model model) {
		return "index";
	}
	
	@GetMapping("/secure/insertTask")
	public String insertTask(Model model,@ModelAttribute Task task) {
		model.addAttribute("task",new Task());
		model.addAttribute("taskList",da.getTask());
		List<Task> taskList = da.getTaskById(task.getId());
		if(taskList.isEmpty()) {
			da.insertNewTask(task);
		}
		else
		{
			da.updateTask(task);
		}
		model.addAttribute("taskList", da.getTask());
		model.addAttribute("task", new Task());
		
		return "/secure/addTask";
	}
	
	@GetMapping("/secure/deleteTask/{id}")
	public String deleteTask(Model model,@PathVariable Long id) {
		da.deleteTaskById(id);
		model.addAttribute("taskList", da.getTask());
		model.addAttribute("task", new Task());
		
		return "/secure/tasks";
	}
	
	@GetMapping("/secure/editTask/{id}")
	public String editTask(Model model, @PathVariable Long id) {
	    Task task = da.getTaskById(id).get(0);

	    // Set the task to be edited in the model
	    model.addAttribute("task", task);

	    // Add the list of tasks for display
	    model.addAttribute("taskList", da.getTask());

	    return "/secure/addTask";
	}
	@GetMapping("/secure/tasksList")
	public String showTasks(Model model) {
		model.addAttribute("task",new Task());
		model.addAttribute("taskList",da.getTask());
		return "/secure/tasks";
	}
	@PostMapping("/secure/tasksList")
	public String showTask(Model model,@ModelAttribute Task task) {
		model.addAttribute("task",new Task());
		model.addAttribute("taskList",da.getTask());
		List<Task> taskList = da.getTaskById(task.getId());
		if(taskList.isEmpty()) {
			da.insertNewTask(task);
		}
		else
		{
			da.updateTask(task);
		}
		model.addAttribute("taskList", da.getTask());
		model.addAttribute("task", new Task());
		
		return "/secure/tasks";
	}
}
