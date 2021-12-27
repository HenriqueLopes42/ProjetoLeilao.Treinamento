package com.example.ProjetoLeilao.business;

import com.example.ProjetoLeilao.entities.Vendedor;
import com.example.ProjetoLeilao.repositories.VendedorRepository;

import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VendedorBiz {

    VendedorRepository vendedorRepository;
    private Vendedor vendedor;
    private List<String> erros;

    public VendedorBiz(Vendedor vendedor, VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
        this.vendedor = vendedor;
        this.erros = new ArrayList<>();
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public List<String> getErros() {
        return erros;
    }

    public void setErros(List<String> erros) {
        this.erros = erros;
    }

    public Boolean isValid() {
        Boolean resultado;
        resultado = nomeUnico(vendedor.getNome());
        resultado = tamanhoNomeValido(vendedor.getNome()) && resultado;
        resultado = nomeIniciaMaiuscula(vendedor.getNome()) && resultado;
        resultado = validaDataNascimento(vendedor.getDataNascimento()) && resultado;
        resultado = validaTelefone(vendedor.getTelefone()) && resultado;
        resultado = validaEmail(vendedor.getEmail()) && resultado;
        return resultado;
    }

    public Boolean nomeUnico(String nome){

        Integer quantidade = vendedorRepository.findByNome(nome).size();
        if (quantidade == 0) {
            return true;
        } else {
            erros.add("Não é possível cadastrar dois vendedores com o mesmo nome :(");
            return false;
        }
    }

    public Boolean tamanhoNomeValido( String nome){
        Integer tamanho = nome.length();
        if (tamanho >= 10 ){
            return true;
        } else {
            erros.add("O tamanho do nome deve ter pelo menos 10 caracteres");
            return false;
        }
    }

    public Boolean nomeIniciaMaiuscula( String nome ){

        Boolean certo = nome.matches("^[A-Z]]");
        if (!certo){
            erros.add("O nome deve iniciar com letra maiuscula");
        }
        return certo;
    }

    public Boolean validaDataNascimento(Date data) {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String s = formatter.format(data);
        System.out.println(s);
        Boolean certo = s.matches("^[0-2][0-9]{3}/[0-1][0-9]/[0-3][0-9]");
        if (!certo)
            erros.add("A data de nascimento inserida é inválida.");
        return certo;
    }

    public Boolean validaTelefone(String telefone) {
        Boolean certo = telefone.matches("^([1-9]{2})[9][0-9]{4}[0-9]{4}$");
        if (!certo)
            erros.add("O telefone deve estar no formato XXXXXXXXXXX");
        return certo;
    }

    public Boolean validaEmail(String email) {
        Boolean certo = email.matches("^([0-9A-Za-z]+)(@{1})([A-Za-z]+)(\\.)([A-Za-z]{2,3})");
        if (!certo)
            erros.add("O endereco de email não segue o padrão");
        return certo;
    }
}