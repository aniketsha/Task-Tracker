package To_Do_List.shar1140.database;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;


import To_Do_List.shar1140.beans.Task;
import To_Do_List.shar1140.beans.*;

@Repository
public class DatabaseAccess {
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;

	public void insertNewTask(Task task) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query="INSERT INTO task(title,description,dueDate,completed) VALUES (:title,:description,:dueDate,:completed)";

		namedParameters.addValue("title", task.getTitle());
		namedParameters.addValue("description", task.getDescription());
		namedParameters.addValue("dueDate", task.getDueDate());
		namedParameters.addValue("completed", task.isCompleted());
		int rowsAffected = jdbc.update(query, namedParameters);
		
		if(rowsAffected>0) {
			System.out.println("Insertions Successful");
		}
	}
	
	public void updateTask(Task task) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query="UPDATE task SET title=:title,description = :description,dueDate=:dueDate,completed=:completed where id=:id  ";

		namedParameters.addValue("id", task.getId());
		namedParameters.addValue("title", task.getTitle());
		namedParameters.addValue("description", task.getDescription());
		namedParameters.addValue("dueDate", task.getDueDate());
		namedParameters.addValue("completed", task.isCompleted());
		int rowsAffected = jdbc.update(query, namedParameters);
		
		if(rowsAffected>0) {
			System.out.println("Insertions Successful");
		}
	}
	public List<Task> getTask() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		String query="SELECT * FROM task";
		
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Task>(Task.class));
	}
	
	public List<Task> getTaskById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		String query="SELECT * FROM task WHERE id=:id";
		namedParameters.addValue("id", id);
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Task>(Task.class));
	}
	
	public void deleteTaskById(Long id) {
		MapSqlParameterSource namedParameters= new MapSqlParameterSource();
		
		
		String query="DELETE  FROM task WHERE id = :id";
		System.out.println(id);
		namedParameters.addValue("id",id);
		
		int rowsAffected = jdbc.update(query, namedParameters);
		if(rowsAffected>0) {

			System.out.println("Deletion Successful");
		}
	}
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

// Method to find a user account by email
	public User findUserAccount(String email) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM sec_user where email = :email";
		namedParameters.addValue("email", email);
		try {
			return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<>(User.class));
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}

// Method to get User Roles for a specific User id
	public List<String> getRolesById(Long userId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT sec_role.roleName " + "FROM user_role, sec_role "
				+ "WHERE user_role.roleId = sec_role.roleId " + "AND userId = :userId";
		namedParameters.addValue("userId", userId);
		return jdbc.queryForList(query, namedParameters, String.class);
	}
	
	public void addUser(String email, String password) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "INSERT INTO sec_user "
		+ "(email, encryptedPassword, enabled) "
		+ "VALUES (:email, :encryptedPassword, 1)";
		namedParameters.addValue("email", email);
		namedParameters.addValue("encryptedPassword",
		passwordEncoder.encode(password));
		jdbc.update(query, namedParameters);
		}
	
	public void addRole(Long userId, Long roleId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "INSERT INTO user_role (userId, roleId) "
		+ "VALUES (:userId, :roleId)";
		namedParameters.addValue("userId", userId);
		namedParameters.addValue("roleId", roleId);
		jdbc.update(query, namedParameters);
		}

}
