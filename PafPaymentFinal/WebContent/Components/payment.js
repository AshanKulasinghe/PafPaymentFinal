$(document).ready(function() 
{  
    if ($("#alertSuccess").text().trim() == "")  
    {   
        $("#alertSuccess").hide();  
    } 
    $("#alertError").hide(); 
}); 

 

//SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
    // Clear alerts---------------------  
    $("#alertSuccess").text("");  
    $("#alertSuccess").hide();  
    $("#alertError").text("");  
    $("#alertError").hide(); 
 

    // Form validation-------------------  
    var status = validateCardForm();  
    if (status != true)  
    {   
        $("#alertError").text(status);   
        $("#alertError").show();   
    return;  
    } 

 

    // If valid------------------------  
    var t = ($("#hidAppIDSave").val() == "") ? "POST" : "PUT";
    var hidAppIDSave = $('#hidAppIDSave').val();
    var Pay_type = $('#Pay_type').val();
    var Pay_cno = $('#Pay_cno').val();
    var Pay_expdate = $('#Pay_expdate').val();
    var Pay_code = $('#Pay_code').val();
    var Appointment_ID = $('#Appointment_ID').val();
    
    $.ajax(
    {
        url : "PaymentAPI",
        type : t,
        data : "Pay_type=" + Pay_type + "&Pay_cno=" + Pay_cno  + "&Pay_expdate=" + Pay_expdate + "&Pay_code=" + Pay_code+ "&Appointment_ID=" + Appointment_ID  + "&hidAppIDSave=" + hidAppIDSave,
        dataType : "text",
        complete : function(response, status)
        {
            onCardSaveComplete(response.responseText, status);
        }
    });
}); 

 

function onCardSaveComplete(response, status){
    if(status == "success")
    {
        var resultSet = JSON.parse(response);
            
        if(resultSet.status.trim() == "success")
        {
            $("#alertSuccess").text("Successfully Saved.");
            $("#alertSuccess").show();
                    
            $("#divItemsGrid").html(resultSet.data);
    
        }else if(resultSet.status.trim() == "error"){
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    }else if(status == "error"){
        $("#alertError").text("Error While Saving.");
        $("#slertError").show();
    }else{
        $("#alertError").text("Unknown Error while Saving.");
        $("#alertError").show();
    }
    $("#hidAppIDSave").val("");
    $("#formPayment")[0].reset();
}
  

 


$(document).on("click", ".btnUpdate", function(event) 
        {     
    $("#hidAppIDSave").val($(this).closest("tr").find('#hidAppIDUpdate').val());     
    $("#Pay_type").val($(this).closest("tr").find('td:eq(0)').text());    
    $("#Pay_cno").val($(this).closest("tr").find('td:eq(1)').text());     
    $("#Pay_expdate").val($(this).closest("tr").find('td:eq(2)').text());     
    $("#Pay_code").val($(this).closest("tr").find('td:eq(3)').text());
    $("#Appointment_ID").val($(this).closest("tr").find('td:eq(4)').text());
    

 

});

 


//Delete
$(document).on("click", ".btnRemove", function(event){
    $.ajax(
    {
        url : "PaymentAPI",
        type : "DELETE",
        data : "pay_ID=" + $(this).data("id"),
        dataType : "text",
        complete : function(response, status)
        {
            onCardDeletedComplete(response.responseText, status);
        }
    });
});

 

function onCardDeletedComplete(response, status)
{
    if(status == "success")
    {
        var resultSet = JSON.parse(response);
            
        if(resultSet.status.trim() == "success")
        {
            $("#alertSuccess").text("Successfully Deleted.");
            $("#alertSuccess").show();
                    
            $("#divItemsGrid").html(resultSet.data);
    
        }else if(resultSet.status.trim() == "error"){
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    }else if(status == "error"){
        $("#alertError").text("Error While Deleting.");
        $("#alertError").show();
    }else{
        $("#alertError").text("Unknown Error While Deleting.");
        $("#alertError").show();
    }
}

 


function validateCardForm() {  

 

    if ($("#Pay_type").val().trim() == "")  {   
        return "Insert payment Type.";  
        
    } 
    

 

    if ($("#Pay_cno").val().trim() == "")  {   
        return "Insert card number.";  
        
    } 
     
     

 

    if ($("#Pay_expdate").val().trim() == "")  {   
        return "Insert expiriation date.";  
        
    } 
    

 

    if ($("#Pay_code").val().trim() == "")  {   
        return "Insert cvv.";  
        
    }
    
    if ($("#Appointment_ID").val().trim() == "")  {   
        return "Insert appointment Id.";  
        
    } 
      
     
     return true; 
     
}