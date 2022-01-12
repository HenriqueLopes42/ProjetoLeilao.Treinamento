package com.example.ProjetoLeilao.ControllersTests;

import com.example.ProjetoLeilao.Mensagem;
import com.example.ProjetoLeilao.controllers.LeilaoController;
import com.example.ProjetoLeilao.entities.Leilao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LeilaoControllerTests {

    @Autowired
    private LeilaoController leilaoController;
    
    
    Leilao leilao = new Leilao(); // Olhar construtor e quais parametros passar pra apagar as linhas de baixo
    leilao.setIdLeilao(0);
    leilao.setAtivo(true);
    leilao.setNome("André");
    leilao.setData(new Date());
    leilao.setDescricao("Descricao");

    @BeforeEach
    public void purposelessMethod() {

    }

    @Test
    public void deveRetornarSuccessQuandoListarLeiloes() {
        Boolean expected = true;
        Boolean result = false;

        try {
            List<Leilao> list = leilaoController.listar();
            System.out.println("Teste de listar leilões");
            result = true;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            result = false;

        }
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void deveRetornarSuccessQuandoAdicionarLeilao(){
        Boolean expected = true;
        Boolean result = false;

        try {
            int quantidadeAnterior = this.leilaoController.listar().size();

            Mensagem msg = this.leilaoController.incluir(leilao);
            int quantidadeNova = this.leilaoController.listar().size(); // não seria incluir?
            
            if (quantidadeNova == quantidadeAnterior + 1)
                result = true;
            else {
                System.out.println("Nao foi possível incluir o leilão: " + msg.getMensagem());
                // result = false;
                for( String s: msg.getErros()){
                    System.out.println(s);
                }
            }
        } catch ( Exception ex ){
            System.out.println("Erro ao incluir o leilão: " + ex.getMessage());
            // result = false;
        }

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void DeveRetornarSuccessQuandoAlterarUmLeilao() {
        Boolean expected = true;
        Boolean result = false;

        try {
             Mensagem msg = this.leilaoController.alterar(leilao);
             result = true;
        } catch ( Exception ex ) {
            System.out.println("Erro ao incluir o leilão: " + ex.getMessage());
        }
        Assertions.assertEquals()
    }
}
