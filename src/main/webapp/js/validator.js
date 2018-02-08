function checkRegistration() {
    var inputLogin = $("input[name=nickname]").val();
    if(inputLogin.length >= 256){
        inputLogin.css('border-color','red');
        return false;
    }
    if(inputLogin.length<=4){
        inputLogin.css('border-color','red');
        return false;
    }
    return true;
}