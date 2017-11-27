/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.StudentDAO;
import com.dao.StudentDAOLocal;
import com.model.Student;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author felipe.ospinah
 */
public class StudentServlet extends HttpServlet {

    @EJB
    private StudentDAOLocal studentDAO;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
 /*out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StudentServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StudentServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");*/

            String action = request.getParameter("action");
            String studentIdStr = request.getParameter("studentId");
            String studentId = "";
            if (studentIdStr != null && !studentIdStr.equals("")) {
                studentId = studentIdStr;
            }
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String yearLevelStr = request.getParameter("yearLevel");
            String yearLevel = "";
            if (yearLevelStr != null && !yearLevelStr.equals("")) {
                yearLevel = yearLevelStr;
            }
            Student student = new Student(studentId, firstname, lastname, yearLevel);
            if ("Add".equalsIgnoreCase(action)) {
                studentDAO.addStudent(student);
            } else if ("Edit".equalsIgnoreCase(action)) {
                studentDAO.editStudent(student);
            } else if ("Delete".equalsIgnoreCase(action)) {
                studentDAO.deleteStudent(studentId);
            } else if("Search".equalsIgnoreCase(action)) {
                student = studentDAO.getStudent(studentId);
            }
            
            request.setAttribute("student", student);
            request.setAttribute("allStudents", studentDAO.getAllStudents());
            request.getRequestDispatcher("studentinfo.jsp").forward(request, response);

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
