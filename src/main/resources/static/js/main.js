function gerarMatricula(){
    const txt = "ACA";
    const random = Math.floor(Math.random() * 9999999999);
    document.getElementById("matricula").value = txt + random;
}