/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paul;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedSet;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Mitchell Paul
 */
@WebServlet(name = "ApplicationServlet", urlPatterns = {"/applications"})
@MultipartConfig(
        fileSizeThreshold = 5_242_880, //5MB
        maxFileSize = 20_971_520L //20MB
)
public class ApplicationServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Map<Integer, Application> applicationDatabase = new LinkedHashMap<>();

    private volatile int APPLICATION_ID_SEQUENCE = 1;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "viewApplications";
        }
        switch (action) {
            case "submitApplication":
                doPost(request, response);
                break;
            case "viewApplications":
                viewApplications(request, response);
                break;
            case "viewApplication":
                viewApplication(request, response);
                break;
            case "download":
                downloadAttachment(request, response);
                break;
            case "disqualify":
                disqualify(request, response);
                break;
        }

    }

    private void disqualify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int Id = Integer.parseInt(request.getParameter("Id"));
        Application application = applicationDatabase.get(Id);

        application.setActive(false);
        request.getRequestDispatcher("/WEB-INF/jsp/view/applicationListings.jsp").forward(request, response);

    }

    private void viewApplications(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int begin = 0;
        int end = applicationDatabase.size();
        request.setAttribute("begin", begin);
        request.setAttribute("end", end);

        request.setAttribute("applications", applicationDatabase);
        request.getRequestDispatcher("/WEB-INF/jsp/view/applicationListings.jsp").forward(request, response);

    }

    private void viewApplication(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Id = request.getParameter("Id");
        Application application = getApplication(Id);
        Attachment attachmentName = application.getAttachment(Id);
        request.setAttribute("ID", Id);
        request.setAttribute("application", application);
        request.setAttribute("attachment", attachmentName);
        request.getRequestDispatcher("/WEB-INF/jsp/view/application.jsp").forward(request, response);

    }

    private Application getApplication(String idString) throws ServletException, IOException {

        if (idString == null || idString.length() == 0) {
            return null;
        }

        try {
            Application application = applicationDatabase.get(Integer.parseInt(idString));
            if (application == null) {
                return null;
            }
            return application;
        } catch (Exception e) {
            return null;
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
        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null) {
            response.sendRedirect("login");
            return;
        }
        String action = request.getParameter("action");
        if (action == null) {
            action = "viewApplications";
        }
        switch (action) {
            case "submitApplication":
                doPost(request, response);
                break;
            case "viewApplications":
                viewApplications(request, response);
                break;
            case "viewApplication":
                viewApplication(request, response);
                break;
            case "download":
                downloadAttachment(request, response);
                break;
            case "disqualify":
                disqualify(request, response);
                break;
        }
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
        HttpSession session = request.getSession();
        Application application = new Application();
        boolean error = false;

        String firstName = request.getParameter("firstName");
        if (firstName == null || firstName.equals("")) {
            error = true;
            application.setFirstNameError("First Name Required");
        } else {
            application.setFirstName(request.getParameter("firstName"));
        }

        String lastName = request.getParameter("lastName");
        if (lastName == null || lastName.equals("")) {
            error = true;
            application.setLastNameError("Last Name Required");
        } else {
            application.setLastName(request.getParameter("lastName"));

        }
        
        String email = request.getParameter("email");
        if (email == null || email.equals("")) {
            error = true;
            application.setEmailError("Email Required");
        } else {
            application.setEmail(request.getParameter("email"));

        }

        String phone = request.getParameter("phoneNumber");
        
        if (phone == null || phone.equals("")) {
            error = true;
            application.setPhoneError("Phone Number Required");
        } else {
            
            
            String phoneFormatted = phone.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
            application.setPhone(phoneFormatted);

        }
        
        application.setTitle(request.getParameter("jobTitle"));
        application.setJobID(Integer.parseInt(request.getParameter("jobId")));
        
        
                String desired = request.getParameter("desiredSalary");
        if (desired == null || desired.equals("")) {
            error = true;
            application.setSalaryError("Desired Salary Required");
        } else {
        application.setDesiredSalary(Double.parseDouble(request.getParameter("desiredSalary")));

        }
        
                
                String start = request.getParameter("startDate");
        if (start == null || start.equals("")) {
            error = true;
            application.setSalaryError("Start Date Required");
        } else {
        application.setStartDate(LocalDate.parse(request.getParameter("startDate")));
        
        }
        

                
        String jobId = request.getParameter("jobId");
        String jobTitle = request.getParameter("jobTitle");
        if(!error)
        {
        application.setJobID(Integer.parseInt(jobId));
        application.setTitle(jobTitle);
        application.setDateTimeSubmitted(Instant.now());
        application.setActive(true);
        application.setId(APPLICATION_ID_SEQUENCE);
        Part filePart = request.getPart("resume");
        if (filePart != null && filePart.getSize() > 0) {
            Attachment attachment = processAttachment(filePart);
            if (attachment != null) {
                application.addAttachment(attachment);
            }

        int id;
        synchronized (this) {
            id = this.APPLICATION_ID_SEQUENCE++;
            application.setId(id);
            applicationDatabase.put(id, application);
        }
        String attachmentName = attachment.getName();
        application.setAttachmentName(attachmentName);
        
        request.setAttribute("applicationSent", true);
        response.sendRedirect("jobs?action=viewJob&Id=" + jobId);
    }
        }
        else
        {
        session.setAttribute("application", application);
        response.sendRedirect("jobs?action=viewJob&Id=" + jobId);
        }

    }

    private Attachment processAttachment(Part filePart) throws IOException {
        Attachment attachment = new Attachment();
        try ( InputStream inputStream = filePart.getInputStream();  ByteArrayOutputStream outputStream = new ByteArrayOutputStream();) {
            int read;
            final byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            attachment.setName(filePart.getSubmittedFileName());
            attachment.setContents(outputStream.toByteArray());
        }
        return attachment;
    }

    private void downloadAttachment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idString = request.getParameter("id");
        Application application = getApplication(idString);
        String name = request.getParameter("attachment");
        if (application == null || name == null) {
            response.sendRedirect("applications");
            return;
        }

        Attachment attachment = application.getAttachment(name);
        if (attachment == null) {
            response.sendRedirect("applications?action=view&viewApplication=" + idString);
            return;
        }

        response.setHeader("Content-Disposition", "attachment; filename=" + attachment.getName());
        response.setContentType("application/octet-stream");

        try ( ServletOutputStream stream = response.getOutputStream()) {
            stream.write(attachment.getContents());
        }
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
