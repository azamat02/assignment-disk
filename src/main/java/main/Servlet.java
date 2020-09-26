package main;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Servlet", urlPatterns = "/servlet")
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("create-folder") != null){
            String path;
            if(request.getParameter("folder-path") != "")
            {
                path = "C:\\Users\\azama\\IdeaProjects\\assignment\\src\\main\\webapp\\uploads"
                        + "\\" + request.getParameter("folder-path")
                        + "\\" + request.getParameter("folder-name");
            }
            else  {
                path = "C:\\Users\\azama\\IdeaProjects\\assignment\\src\\main\\webapp\\uploads"
                        + request.getParameter("folder-path")
                        + "\\" + request.getParameter("folder-name");
            }
            System.out.println("Directory created with path: "+path);
            File file = new File(path);
            if (file.mkdirs()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }

            String referer = request.getHeader("Referer");
            response.sendRedirect(referer);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiskManager disk = new DiskManager();
        if(request.getParameter("show-all") != null){
            String result = disk.showFolder(request.getParameter("link"));
            request.setAttribute("result", result);
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("index.jsp");
            dispatcher.forward( request, response );
        }
        if(request.getParameter("delete") != null){
            String path = request.getParameter("delete-path").replace("/","\\");
            File file = new File(path);
            deleteDirectoryOrFile(file);
            String referer = request.getHeader("Referer");
            response.sendRedirect(referer);
        }
        if(request.getParameter("search-text") != null){

            String path = "C:\\Users\\azama\\IdeaProjects\\assignment\\src\\main\\webapp\\uploads\\" + request.getParameter("search-path");
            String search = request.getParameter("search-text");
            String result = disk.showBySearch(search, path);

            request.setAttribute("search-result", result);
            request.setAttribute("searching", "true");
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("result.jsp");
            dispatcher.forward( request, response );

        }
    }
    public static boolean deleteDirectoryOrFile(File dir) {
        if (dir.isDirectory()) {
            File[] children = dir.listFiles();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDirectoryOrFile(children[i]);
                if (!success) {
                    return false;
                }
            }
        }
        // either file or an empty directory
        System.out.println("removing file or directory : " + dir.getName());
        return dir.delete();
    }
}


