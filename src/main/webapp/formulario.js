/**
 * 
 */


function verificarCpf(cpf, mensagemErro) {
	if (cpf.trim() == "") {
		$("#" + mensagemErro).html("Campo Vazio");
		return false;
	}
	if (cpf.length != 14) {
		$("#" + mensagemErro).html("CPF só pode ter 11 caracteres");
		return false;
	}
	if (!/^\d{3}\.\d{3}\.\d{3}-\d{2}$/.test(cpf)) {
		$("#" + mensagemErro).html("CPF inválido");
		return false;
	}

	$("#" + mensagemErro).html("");
	return true;
}


function verificarDataNascimento(dataNascimento, mensagemErro) {
	if (dataNascimento.trim() == "") {
		$("#" + mensagemErro).html("Campo Vazio");
		return false;
	}
	if (dataNascimento.length != 10) {
		$("#" + mensagemErro).html("Data de Nascimento só pode ter 10 caracteres");
		return false;
	}
	if (!/^\d{2}\/\d{2}\/\d{4}$/.test(dataNascimento)) {
		$("#" + mensagemErro).html("Data de Nascimento inválido");
		return false;
	}

	$("#" + mensagemErro).html("");
	return true;
}

function verificarTelefone(telefone, mensagemErro) {
	if (telefone.trim() == "") {
		$("#" + mensagemErro).html("Campo Vazio");
		return false;
	}
	if (telefone.length != 14) {
		$("#" + mensagemErro).html("Telefone só pode ter 11 caracteres");
		return false;
	}
	if (!/^\(\d{2}\)\d{5}-\d{4}$/.test(telefone)) {
		$("#" + mensagemErro).html("Telefone inválido");
		return false;
	}

	$("#" + mensagemErro).html("");
	return true;
}


function verificarLetras(palavra, mensagemErro) {
	if (palavra == "") {
		$("#" + mensagemErro).html("Campo Vazio");
		return false;
	} else {
		let digitosPermitidos = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

		for (let i = 0; i < palavra.length; i++) {
			let c = palavra.charAt(i);

			if (digitosPermitidos.indexOf(c) == -1) {
				$("#" + mensagemErro).html("Campo Invalido");
				return false;
			}
		}
		$("#" + mensagemErro).html("");
		return true;
	}
}

function verificarNumeros(valor, mensagemErro) {
	if (valor == "") {
		$("#" + mensagemErro).html("Campo Vazio");
		return false;
	} else {
		let digitosPermitidos = "0123456789";

		for (let i = 0; i < valor.length; i++) {
			let c = valor.charAt(i);

			if (digitosPermitidos.indexOf(c) == -1) {
				$("#" + mensagemErro).html("Campo Invalido");
				return false;
			}
		}
		$("#" + mensagemErro).html("");
		return true;
	}
}

function verificarCodigo(codigo, idErro) {
    if (!codigo || codigo.trim() === "") {
        $("#" + idErro).html("Código campo vazio");
        return false;
    }

    let digitosPermitidos = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    for (let i = 0; i < codigo.length; i++) {
        const c = codigo.charAt(i);
        if (!digitosPermitidos.includes(c)) {
            $("#" + idErro).html("Código caractere inválido");
            return false;
        }
    }

    $("#" + idErro).html(""); // limpa erro se válido
    return true;
}



function verificarSenha(idSenha) {
	let senha = $("#" + idSenha).val().trim();
	$("#" + idSenha).val(senha);

	let letrasMinusculas = "abcdefghijklmnopqrstuvwxyz";
	let letrasMaiusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	let numeros = "1234567890";
	let caracteresEspeciais = "!#@$";

	let temMinuscula = false;
	let temMaiuscula = false;
	let temNumero = false;
	let temEspecial = false;

	for (let i = 0; i < senha.length; i++) {
		let c = senha.charAt(i);
		if (letrasMinusculas.includes(c)) temMinuscula = true;
		else if (letrasMaiusculas.includes(c)) temMaiuscula = true;
		else if (numeros.includes(c)) temNumero = true;
		else if (caracteresEspeciais.includes(c)) temEspecial = true;
	}

	if (!temMinuscula || !temMaiuscula || !temNumero || !temEspecial) {
		$("#idErroSenha").html("A senha deve conter ao menos: uma letra minúscula, uma maiúscula, um número e um caractere especial (!#@$)");
		return false;
	}

	$("#idErroSenha").html("");
	return true;
}



function formatarCpf(inputCpf) {
	let cpf = inputCpf.val();
	if (cpf.length >= 3 && cpf.indexOf(".") == -1) {
		inputCpf.val(cpf.substring(0, 3) + '.' + cpf.substring(3));
	}
	cpf = inputCpf.val();
	if (cpf.length >= 7 && cpf.indexOf(".", 6) == -1) {
		inputCpf.val(cpf.substring(0, 7) + '.' + cpf.substring(7));
	}
	cpf = inputCpf.val();
	if (cpf.length >= 11 && cpf.indexOf("-", 7) == -1) {
		inputCpf.val(cpf.substring(0, 11) + '-' + cpf.substring(11));
	}
	cpf = inputCpf.val();
}


function formatarNascimento(inputNascimento) {
	let numeroNascimento = inputNascimento.val();
	if (numeroNascimento.length >= 2 && numeroNascimento.indexOf("/") == -1) {
		inputNascimento.val(numeroNascimento.substring(0, 2) + '/' + numeroNascimento.substring(2));
	}
	numeroNascimento = inputNascimento.val();
	if (numeroNascimento.length >= 5 && numeroNascimento.indexOf("/", 5) == -1) {
		inputNascimento.val(numeroNascimento.substring(0, 5) + '/' + numeroNascimento.substring(5));
	}
}


function formatarTelefone(inputTel) {
	let numero = inputTel.val();
	if (numero.length >= 10 && numero.indexOf("-") == -1) {
		inputTel.val((numero.substring(0, 7) + '-' + numero.substring(7)));
	}
	numero = inputTel.val();
	if (numero.length >= 12 && numero.indexOf("(") == -1) {
		inputTel.val('(' + numero.substring(0, 2) + ')' + numero.substring(2));
	}
}

