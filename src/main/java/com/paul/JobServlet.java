/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paul;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Mitchell Paul
 */
public class JobServlet extends HttpServlet {

    private static final String FILE_PATH = "WEB-INF/Assets/";
    private static final String FILE_NAME = "jobdata.csv";
    private static SortedSet<Job> jobs;
    private Map<Integer, Job> jobDatabase = new LinkedHashMap<>();


    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *
     */

    private void readFromFile(HttpServletRequest request, HttpServletResponse response) throws ParserConfigurationException, MalformedURLException, IOException, SAXException 
    {
        
        if (jobs == null) {
            try ( Scanner in = new Scanner(new File(getServletContext().getRealPath(FILE_PATH + FILE_NAME)))) {
                jobs = new TreeSet<>();
                
                int lineCount = 0;
                String line = in.nextLine();
                String[] fields;
                int id;
                boolean active = false;
                LocalDate dateCreated = null;
                String title = "";
                String city = "";
                String state = "";
                boolean fullTime = false;
                String department = "";
                String experience = "";
                String wageCategory = "";
                double salary = 0.0;

                while (in.hasNextLine()) {
                    lineCount++;
                    line = in.nextLine();
                    fields = line.split(",");
                    id = Integer.parseInt(fields[0]);
                    active = Boolean.parseBoolean(fields[1]);
                    dateCreated = LocalDate.parse(fields[2]);
                    title = fields[3];
                    city = fields[4];
                    state = fields[5];
                    fullTime = Boolean.parseBoolean(fields[6]);
                    department = fields[7];
                    experience = fields[8];
                    wageCategory = fields[9];
                    salary = Integer.parseInt(fields[10]);
                    if (active == true) {
                        jobs.add(new Job(id, active, dateCreated, title, city, state, fullTime, department, experience,
                                wageCategory, salary));
                        jobDatabase.put(id, new Job(id, active, dateCreated, title, city, state, fullTime, department, experience, wageCategory, salary));
                    }
                }
            } catch (FileNotFoundException fnfe) {
                response.setContentType("text/html;charset=UTF-8");
                try ( PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Data error</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Error reading data</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
                return;
            }
        }
    }

    protected void pagination(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            readFromFile(request, response);
        } catch (ParserConfigurationException | IOException | SAXException ex) {
            return;
        }
        int page = 1;
        int jobsPerPage = 4;
        int begin = 0;
        int end = 0;
        int maxPages = jobs.size() / jobsPerPage;
        if (jobs.size() % jobsPerPage != 0) {
            maxPages++;
        }
        String pageStr = request.getParameter("page");
        if (pageStr != null && !pageStr.equals("")) {
            try {
                page = Integer.parseInt(pageStr);
                if (page < 1) {
                    page = 1;
                } else if (page > maxPages) {
                    page = maxPages;
                }
            } catch (NumberFormatException e) {
                page = 1;
            }
        }
        begin = (page - 1) * jobsPerPage;
        end = begin + jobsPerPage - 1;
        request.setAttribute("begin", begin);
        request.setAttribute("end", end);
        request.setAttribute("maxPages", maxPages);
        request.setAttribute("currentPage", page);

        request.setAttribute("jobs", jobs);
        request.getRequestDispatcher("/WEB-INF/jsp/view/jobList.jsp").forward(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "jobList";
        }
        switch (action) {
            case "viewJob":
                viewJobListing(request, response);
                break;
             case "jobList":
                pagination(request, response);
                break;

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
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "viewJob":
                viewJobListing(request, response);
                break;

        }

    }

    private void viewJobListing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String Id = request.getParameter("Id");
        Job job = getJob(Id);

        request.setAttribute("ID", Id);
        request.setAttribute("job", job);
        request.getRequestDispatcher("/WEB-INF/jsp/view/job.jsp").forward(request, response);

    }
    
    private Job getJob(String idString) throws ServletException, IOException {
        if (idString == null || idString.length() == 0) {
            return null;
        }

        try {
            Job job = jobDatabase.get(Integer.parseInt(idString));
            if (job == null) {
                return null;
            }
            return job;
        } catch (Exception e) {
            return null;
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
