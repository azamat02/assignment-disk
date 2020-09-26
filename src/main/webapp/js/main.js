function get_info(path, total_space, type){
    $("#edit-field").hide("fast").delay("100").show("fast");
    if(type == "file"){
        $("#edit-field").html("<h1>Information</h1>\n" +
            "                    <p>\n" +
            "                        <h3>Path: "+path+"</h3>\n" +
            "                    </p>\n" +
            "                    <p>\n" +
            "                        <h3>Size: "+parseFloat(total_space)/1000+" KB</h3>\n" +
            "                    </p>\n" +
            "                    <p>\n" +
            "                        <a href='servlet?delete=&delete-path="+path+"' class=\"negative ui button\"><i class=\"trash icon\"></i>Delete</a>\n" +
            "                        <a href='download?fldp="+path+"' class=\"primary ui button\"><i class=\"cloud download icon\"></i>Download</a>\n" +
            "                    </p>\n" +
            "                    <div class=\"ui warning message\" style=\"width: 450px\">\n" +
            "                        <i class=\"icon info\"></i>\n" +
            "                        After deleting folder all inside files will be deleted.\n" +
            "                    </div>\n" +
            "                    <div class=\"ui divider\"></div>")
        $("#edit-field").show("fast");
    }
    else {
        $("#edit-field").html("<h1>Information</h1>\n" +
            "                    <p>\n" +
            "                        <h3>Path: "+path+"</h3>\n" +
            "                    </p>\n" +
            "                    <p>\n" +
            "                        <h3>Size: "+parseFloat(total_space)/1000+" KB</h3>\n" +
            "                    </p>\n" +
            "                    <p>\n" +
            "                        <a href='servlet?delete=&delete-path="+path+"' class=\"negative ui button\"><i class=\"trash icon\"></i>Delete</a>\n" +
            "                    </p>\n" +
            "                    <div class=\"ui warning message\" style=\"width: 450px\">\n" +
            "                        <i class=\"icon info\"></i>\n" +
            "                        After deleting folder all inside files will be deleted.\n" +
            "                    </div>\n" +
            "                    <div class=\"ui divider\"></div>")
        $("#edit-field").show("fast");
    }
}
$(document).ready(function (){

    $("#upload").hide();
    $("#folder").hide();
    $("#edit-field").hide();

    $("#upload-file").click(function (){
        $("#upload").show("slow");
        $("#folder").hide("slow");
    })
    $("#create-folder").click(function (){
        $("#upload").hide("slow");
        $("#folder").show("slow");
    })

})


