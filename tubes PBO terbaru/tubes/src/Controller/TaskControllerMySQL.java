/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Project;
import java.util.List;
import java.util.ArrayList;
import Database.DB;
import Model.Project;
import com.sun.jdi.connect.spi.Connection;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nasya Kirana M
 */
public class TaskControllerMySQL implements TaskController {
    private DB database;

    public TaskControllerMySQL() throws SQLException {
        this.database = new DB();   
    }

    @Override
    public List<Project> getAllProjects() throws SQLException {
        List<Project> projects = new ArrayList<>();
        String query = "SELECT project_id, project_name, member_count, description FROM projects";
        try (ResultSet resultSet = database.getData(query)) {
            while (resultSet.next()) {
                int projectId = resultSet.getInt("project_id");
                String projectName = resultSet.getString("project_name");
                int memberCount = resultSet.getInt("member_count");
                String description = resultSet.getString("description");
                Project project = new Project(projectId,projectName, memberCount, description) {};
                projects.add(project);
            }
        } catch (SQLException e) {
            throw new SQLException("Failed to load projects: " + e.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(TaskControllerMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return projects;
    }

    @Override
    public void saveProject(String projectName, String nip, int memberCount, String description) throws SQLException {
        try {
            String insertQuery = "INSERT INTO projects (project_name, nip, member_count, description) VALUES ('" +
                    projectName + "', '" + nip + "', " + memberCount + ", '" + description + "')";
            database.query(insertQuery);
        } catch (Exception ex) {
            Logger.getLogger(TaskControllerMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteProject(Project project) throws SQLException {
        try {
            String deleteQuery = "DELETE FROM projects WHERE project_id = ?";
            try (PreparedStatement stmt = database.getConnection().prepareStatement(deleteQuery)) {
                stmt.setInt(1, project.getProjectId());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new SQLException("Failed to delete project: " + e.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(TaskControllerMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteProject(String projectName) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   public Project getProjectByName(String selectedProjectName) throws SQLException {
        String query = "SELECT project_id, project_name, member_count, description FROM projects WHERE project_name = ?";
        try (PreparedStatement stmt = database.getConnection().prepareStatement(query)) {
            stmt.setString(1, selectedProjectName);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                int projectId = resultSet.getInt("project_id");
                String projectName = resultSet.getString("project_name");
                int memberCount = resultSet.getInt("member_count");
                String description = resultSet.getString("description");
                return new Project(projectId, projectName, memberCount, description) {};
            } else {
                throw new SQLException("Proyek tidak ditemukan: " + selectedProjectName);
            }
        } catch (SQLException e) {
            throw new SQLException("Gagal mengambil proyek: " + e.getMessage());
        }
    }
}
   