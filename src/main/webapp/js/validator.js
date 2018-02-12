function checkRegistration() {
    var inputLogin = $("input[name='nickname']");
    var inputPassword = $("input[name='password']");
    var inputEmail = $("input[name='email']");
    if(inputLogin.val().length >= 256){
        inputLogin.css('border-color','red');
        return false;
    }
    if(inputLogin.val().length<=4){
        inputLogin.css('border-color','red');
        return false;
    }
    if(inputLogin.val().length===0){
        inputLogin.css('border-color','red');
        return false;
    }
    if(inputPassword.val().length>=256) {
        inputPassword.css('border-color', 'red');
        return false;
    }
    if(inputPassword.val().length<=4){
        inputPassword.css('border-color','red');
        return false;
    }
    if(inputPassword.val().length===0){
        inputPassword.css('border-color','red');
        return false;
    }
    if(inputEmail.val().length===0){
        inputEmail.css('border-color','red');
        return false;
    }
    var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
    if(emailReg.test(inputEmail.val())!==true){
        inputEmail.css('border-color','red');
        return false;
    }
    return true;
}

function checkImageLink() {
    var inputImageLink = $("input[name='imageLink']");
    if(inputImageLink.val().length===0){
        inputImageLink.css('border-color','red');
        return false;
    }
    return true;
}

function checkArtist() {
    var inputArtist= $("input[name='artist']");
    if(inputArtist.val().length>=256) {
        inputArtist.css('border-color', 'red');
        return false;
    }
    if(inputArtist.val().length===0){
        inputArtist.css('border-color','red');
        return false;
    }
    return true;
}

function checkTrackName() {
    var inputTrackName= $("input[name='name']");
    if(inputTrackName.val().length>=256) {
        inputTrackName.css('border-color', 'red');
        return false;
    }
    if(inputTrackName.val().length===0){
        inputTrackName.css('border-color','red');
        return false;
    }
    return true;
}

function checkAlbum() {
    var inputAlbum= $("input[name='album']");
    if(inputAlbum.val().length>=256) {
        inputAlbum.css('border-color', 'red');
        return false;
    }
    if(inputAlbum.val().length===0){
        inputAlbum.css('border-color','red');
        return false;
    }
    var inputStudio= $("input[name='studio']");
    if(inputStudio.val().length>=256) {
        inputStudio.css('border-color', 'red');
        return false;
    }
    if(inputStudio.val().length===0){
        inputStudio.css('border-color','red');
        return false;
    }
    var inputDate= $("input[name='date']");
    if(inputDate.val().length===0) {
        inputDate.css('border-color', 'red');
        return false;
    }
    var dateReg = /([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))/;
    if(dateReg.test(inputDate.val())!==true){
        inputDate.css('border-color','red');
        return false;
    }
    return true;
}

function checkGenre() {
    var inputGenre= $("input[name='genre']");
    if(inputGenre.val().length>=256) {
        inputGenre.css('border-color', 'red');
        return false;
    }
    if(inputGenre.val().length===0){
        inputGenre.css('border-color','red');
        return false;
    }
    return true;
}

function checkPrice() {
    var inputPrice= $("input[name='price']");
    if(inputPrice.val().length===0){
        inputPrice.css('border-color','red');
        return false;
    }
    var priceReg = /[\d]+[.[0-9]+]?/;
    if(priceReg.test(inputPrice.val())!==true){
        inputPrice.css('border-color','red');
        return false;
    }
    return true;
}

function checkLink() {
    var inputLink = $("input[name='link']");
    if(inputLink.val().length===0){
        inputLink.css('border-color','red');
        return false;
    }
    return true;
}

function checkFeedback() {
    var inputText= $("textarea[name='text']");
    if(inputText.val().length >= 256){
        inputText.css('border-color','red');
        return false;
    }
    if(inputText.val().length===0){
        inputText.css('border-color','red');
        return false;
    }
    return true;
}

function checkCard() {
    var inputCardNumber= $("input[name='cardNumber']");
    if(inputCardNumber.val().length===0){
        inputCardNumber.css('border-color','red');
        return false;
    }
    var cardNumberReg = /\d{4}-?\d{4}-?\d{4}-?\d{4}/;
    if(cardNumberReg.test(inputCardNumber.val())!==true){
        inputCardNumber.css('border-color','red');
        return false;
    }
    var inputSvcCode= $("input[name='svcCode']");
    if(inputSvcCode.val().length===0){
        inputSvcCode.css('border-color','red');
        return false;
    }
    var svcReg = /^[0-9]{3,4}$/;
    if(svcReg.test(inputSvcCode.val())!==true){
        inputSvcCode.css('border-color','red');
        return false;
    }
    return true;
}

function checkNewLogin() {
    var inputLogin= $("input[name='nickname']");
    if(inputLogin.val().length>=256) {
        inputLogin.css('border-color', 'red');
        return false;
    }
    if(inputLogin.val().length===0){
        inputLogin.css('border-color','red');
        return false;
    }
    return true;
}

function checkNewEmail() {
    var inputLogin= $("input[name='email']");
    if(inputLogin.val().length>=256) {
        inputLogin.css('border-color', 'red');
        return false;
    }
    if(inputLogin.val().length===0){
        inputLogin.css('border-color','red');
        return false;
    }
    return true;
}

function checkNewPassword() {
    var inputPassword= $("input[name='password']");
    if(inputPassword.val().length>=256) {
        inputPassword.css('border-color', 'red');
        return false;
    }
    if(inputPassword.val().length<=4){
        inputPassword.css('border-color','red');
        return false;
    }
    if(inputPassword.val().length===0){
        inputPassword.css('border-color','red');
        return false;
    }
    return true
}

function checkFindText() {
    var inputFind= $("input[name='findText']");
    if(inputFind.val().length>=256) {
        inputFind.css('border-color', 'red');
        return false;
    }
    if(inputFind.val().length===0){
        inputFind.css('border-color','red');
        return false;
    }
    return true
}

function checkBonus() {
    var inputBonus= $("input[name='bonus']");
    if(inputBonus.val().length===0){
        inputBonus.css('border-color','red');
        return false;
    }
    var bonusReg = /^[0-9]*$/;
    if(bonusReg.test(inputBonus.val())!==true){
        inputBonus.css('border-color','red');
        return false;
    }
    return true
}

function checkAudioTrack() {
    if(checkTrackName()!==true){
        return false;
    }
    if(checkArtist()!==true) {
        return false;
    }
    if(checkAlbum()!==true){
        return false;
    }
    if(checkPrice()!==true){
        return false;
    }
    if( checkLink()!==true){
        return false;
    }
    if(checkImageLink()!==true){
        return false;
    }
    return true
}

function checkAssembly() {
    var inputAssembly= $("input[name='assembly']");
    if(inputAssembly.val().length>=256) {
        inputAssembly.css('border-color', 'red');
        return false;
    }
    if(inputAssembly.val().length===0){
        inputAssembly.css('border-color','red');
        return false;
    }
    return true
}