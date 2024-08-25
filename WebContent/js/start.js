function creator_fun()
{
    $("#login_wrap").slideUp(200,function(){$("#signup_wrap").slideDown(300,function(){$("#creator_n").focus()})});
    return false;
}

function logger_fun()
{
    $("#signup_wrap").slideUp(300,function(){$("#login_wrap").slideDown(200,function(){$("#logger_u").focus()})});
    return false;
}

function login_fun()
{
    $(".warn").hide();
    $("#warn").hide();
    if(navigator.cookieEnabled==false)
    {
        $("#l_warn").hide().html("Please enable cookies.").fadeIn(1000);
        return false;
    }
    else if( $("#logger_u").val().trim()=="" || $("#logger_p").val().trim()=="" )
    {
        $("#l_warn").hide().html("Please type both User ID and Password.").fadeIn(1000);
        return false;
    }
    else
    {
        $("#l_warn").hide();$("#l_heading").html('Loading...');
        $("#l_loader").show();
    }
}

function create_fun()
{
    var m=document.getElementById("male").checked;
    var f=document.getElementById("female").checked;$(".warn").hide();
    $("#warn").hide();
    if(navigator.cookieEnabled==false)
    {
        $("#c_warn").hide().html("Please enable cookies.").fadeIn(1000);
        return false;
    }
    else if($("#creator_n").val().trim()==""||$("#creator_y").val()=="0"||$("#creator_b").val()=="0" || (m==false && f==false) ||$("#creator_u").val().trim()==""||$("#creator_p").val()==""||$("#creator_cp").val()=="" )
    {
        $("#c_warn").hide().html("Please fill all the fields.").fadeIn(1000);
        return false;
    }
    else
    {
        $("#c_warn").hide();$("#c_heading").html('Loading...');$("#c_loader").show();
    }
}