package com.example.users.controller;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.users.model.User;
import com.example.users.service.UserService;
import com.example.users.util.FileUpload;

@WebServlet(
		description = "UserServlet", name = "UserServlet",
        urlPatterns = {"/user"}
)

public class UserServlet extends HttpServlet {
    
    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("searchAction");
        if (action!=null){
            switch (action) {           
            case "searchById":
                searchUserById(req, resp);
                break;           
            case "searchByName":
                searchUserByName(req, resp);
                break;
            }
        }else{
            List<User> result = userService.getAllUsers();
            forwardListUsers(req, resp, result);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {       
        String action = req.getParameter("action");
        switch (action) {
            case "add":
                addUserAction(req, resp);
                break;
            case "edit":
                editUserAction(req, resp);
                break;            
            case "remove":
                removeUserByName(req, resp);
                break;            
        }

    } 

    private void searchUserById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idUser = Integer.valueOf(req.getParameter("idUser"));
        User user = null;
        try {
            user = userService.getUser(idUser);
        } catch (Exception ex) {
            // Logger.getLogger(EmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        req.setAttribute("user", user);
        req.setAttribute("action", "edit");
        String nextJSP = "/jsp/new-user.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(req, resp);
    }
    
    private void searchUserByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        List<User> result = userService.searchUsersByName(userName);        
        forwardListUsers(req, resp, result);
    }

    private void forwardListUsers(HttpServletRequest req, HttpServletResponse resp, List userList) throws ServletException, IOException {
        String nextJSP = "/jsp/list-users.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        req.setAttribute("userList", userList);
        dispatcher.forward(req, resp);
    }     

    private void addUserAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String lastName = req.getParameter("lastName");
        Date birthday = null;
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            birthday = df.parse(req.getParameter("birthDate"));
        } catch(Exception e) {
            System.err.println("Date Parse Exception");
        }
        
        String role = req.getParameter("role");
        String department = req.getParameter("department");
        String email = req.getParameter("email");
        String image = req.getParameter("image");
        File file = new File(image);
        FileUpload fileUpload = new FileUpload();
        fileUpload.uploadFile(file);

        User user = new User(name, lastName, birthday, role, department, email, null);
        int idUser = userService.addUser(user);
        List<User> userList = userService.getAllUsers();
        req.setAttribute("idUser", idUser);
        String message = "The new user has been successfully created.";
        req.setAttribute("message", message);
        forwardListUsers(req, resp, userList);
    }

    private void editUserAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String lastName = req.getParameter("lastName");
        Date birthday = null;
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            birthday = df.parse(req.getParameter("birthDate"));
        } catch(Exception e) {
            System.err.println("Date Parse Exception");
        }
        String role = req.getParameter("role");
        String department = req.getParameter("department");
        String email = req.getParameter("email");
        //String image = req.getParameter("image");
        int idUser = Integer.valueOf(req.getParameter("idUser"));
        User user = new User(name, lastName, birthday, role, department, email, idUser, null);
        user.setId(idUser);
        boolean success = userService.updateUser(user);
        String message = null;
        if (success) {
            message = "The User has been successfully updated.";
        }
        List<User> userList = userService.getAllUsers();
        req.setAttribute("idUser", idUser);
        req.setAttribute("message", message);
        forwardListUsers(req, resp, userList);
    }  

    private void removeUserByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idUser = Integer.valueOf(req.getParameter("idUser"));
        boolean confirm = userService.deleteUser(idUser);
        if (confirm){
            String message = "The User has been successfully removed.";
            req.setAttribute("message", message);
        }
        List<User> userList = userService.getAllUsers();
        forwardListUsers(req, resp, userList);
    }

}