/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Project;
import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author Nasya Kirana M
 */
public interface TaskController {
    List<Project> getAllProjects() throws SQLException;
    void saveProject(String projectName, String nip, int memberCount, String description) 
            throws SQLException;
    void deleteProject(String projectName) throws SQLException;
}