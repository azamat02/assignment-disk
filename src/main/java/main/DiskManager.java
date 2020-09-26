package main;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiskManager implements DiskManagerInterface {
    public String showFolder(String str) {
        str.replace("/","\\");
        File directoryPath = new File("C:\\Users\\azama\\IdeaProjects\\assignment\\src\\main\\webapp\\uploads"+"\\"+str);
        File filesList[] = directoryPath.listFiles();
        StringBuilder strr = new StringBuilder();

//            SHOW FOLDERS
        for(File file : filesList){
            if(!file.getName().contains(".")){
                String path = file.getPath().substring(63,file.getPath().length());
                path = path.replace("\\","/");
                strr.append("<div class=\"ui buttons\" style=\"margin-bottom: 20px !important\">\n" +
                        "                        <a href=\"servlet?link="+path+"&show-all=\" class=\"blue large ui primary button\"><i class=\"folder icon\"></i> "+file.getName()+"</a>\n" +
                        "                         <button onclick=\"get_info('"+file.getPath().replace("\\","/")+"',"+file.length()+",'folder')\" class=\"ui large red icon button\">\n" +
                        "                        <i class=\"edit icon\"></i>\n" +
                        "                    </button>                    " +
                        "</div>");
            }
        }

//            SHOW FILES
        for(File file : filesList){
            if(file.getName().contains(".")){
                strr.append("<div class=\"ui buttons\" style=\"margin-bottom: 20px !important\">\n" +
                        "                        <a href=\""+file.getPath()+"\" class=\"large ui button\"><i class=\"folder icon\"></i> "+file.getName()+"</a>\n" +
                        "                         <button onclick=\"get_info('"+file.getPath().replace("\\","/")+"',"+file.length()+",'file')\" class=\"ui red large icon button\">\n" +
                        "                        <i class=\"edit icon\"></i>\n" +
                        "                    </button>                    " +
                        "</div>");
            }
        }

        String result = strr.toString();
        if(result.length()<10){
            result = "<h2>No files or folders in this folder<h2>";
        }
        return result;
    }
    public String showBySearch(String search_text, String folder_path) {
        File directoryPath = new File(folder_path);
        File filesList[] = directoryPath.listFiles();
        StringBuilder strr = new StringBuilder();
//            SHOW FOLDERS
        for(File file : filesList){
            if(!file.getName().contains(".")){
                if(file.getName().toLowerCase().contains(search_text.toLowerCase())){
                    String path = file.getPath().substring(63,file.getPath().length());
                    path = path.replace("\\","/");
                    strr.append("<div class=\"ui buttons\" style=\"margin-bottom: 20px !important\">\n" +
                            "                        <a href=\"servlet?link="+path+"&show-all=\" class=\"blue large ui primary button\"><i class=\"folder icon\"></i> "+file.getName()+"</a>\n" +
                            "                         <button onclick=\"get_info('"+file.getPath().replace("\\","/")+"',"+file.length()+",'folder')\" class=\"ui large red icon button\">\n" +
                            "                        <i class=\"edit icon\"></i>\n" +
                            "                    </button>                    " +
                            "</div>");
                }
            }
        }
        //            SHOW FILES
        for(File file : filesList){
            if(file.getName().contains(".")){
                if(file.getName().toLowerCase().contains(search_text.toLowerCase())){
                    strr.append("<div class=\"ui buttons\" style=\"margin-bottom: 20px !important\">\n" +
                            "                        <a href=\""+file.getPath()+"\" class=\"large ui button\"><i class=\"folder icon\"></i> "+file.getName()+"</a>\n" +
                            "                         <button onclick=\"get_info('"+file.getPath().replace("\\","/")+"',"+file.length()+",'file')\" class=\"ui red large icon button\">\n" +
                            "                        <i class=\"edit icon\"></i>\n" +
                            "                    </button>                    " +
                            "</div>");
                }
            }
        }
        String result = strr.toString();
        if(result.length()<10){
            result = "<h2>No files or folders found with name '"+search_text+"'<h2>";
        }
        return result;
    }
}
