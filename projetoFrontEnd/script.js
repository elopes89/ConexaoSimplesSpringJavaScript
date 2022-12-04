// npm install node fetch
const formulario = document.querySelector("form");

const Inome = document.querySelector(".nome");
const Iemail = document.querySelector(".email");
const Isenha = document.querySelector(".senha");
const Itel = document.querySelector(".tel");

function Cadastrar() {
    fetch("http://localhost:8080/usuarios",
    {
        headers: {
            "Accept": "application/json",
            "Content-Type" : "application/json"
        },
        method: "POST",
        body: JSON.stringify({
            nome: Inome.value,
            email: Isenha.value,
            senha: Isenha.value,
            telefone: Itel.value
        })
    })
    .then(function (res) {console.log(res) })
    .catch(function (res) {console.log(res) })
};

function Limpar() {
    
        Inome.value = "",
        Isenha.value = "",
        Iemail.value = "",
        Itel.value = ""

};

formulario.addEventListener('submit', function (event){
    event.preventDefault();
    Cadastrar();
    Limpar();

});
    // const dados = {
    //     nome: Inome.value,
    //     email: Isenha.value,
    //     senha: Isenha.value,
    //     telefone: Itel.value
    // };
