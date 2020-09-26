package main;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet to handle File upload request from Client
 * @author Javin Paul
 */
public class UploadFileServlet extends HttpServlet {
    private String UPLOAD_DIRECTORY = "C:\\Users\\azama\\IdeaProjects\\assignment\\src\\main\\webapp\\uploads";


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                ServletContext application = getServletConfig().getServletContext();
                String str = (String) application.getAttribute("file-upload-path");
                //process only if its multipart content
                if(ServletFileUpload.isMultipartContent(request)){
                    try {
                        List<FileItem> multiparts = new ServletFileUpload(
                                new DiskFileItemFactory()).parseRequest(request);

                        for(FileItem item : multiparts){
                            if(!item.isFormField()){
                                String name = new File(item.getName()).getName();
                                UPLOAD_DIRECTORY = "C:\\Users\\azama\\IdeaProjects\\assignment\\src\\main\\webapp\\uploads";
                                UPLOAD_DIRECTORY = UPLOAD_DIRECTORY + "\\" + str;
                                item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
                            }
                        }

                        //File uploaded successfully
                        request.setAttribute("message", "File Uploaded Successfully");
                    } catch (Exception ex) {
                        request.setAttribute("message", "File Upload Failed due to " + ex);
                    }

                }else{
                    request.setAttribute("message",
                            "Sorry this Servlet only handles file upload request");
                }
                String referer = request.getHeader("Referer");
                response.getWriter().append("{"+UPLOAD_DIRECTORY+"}");
                response.getWriter().append("{"+str+"}");
                response.sendRedirect(referer);

            }

}