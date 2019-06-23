/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.avaliacao;

import static br.com.senac.avaliacao.Questao01.editar;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author Cassiano
 */
public class Questao02 {

    /**
     * 0 = tipo (carro/ moto/ outros) 1 = marca;(String) 2 = modelo;(String) 3 =
     * ano;(int) 4 = valor;(double) 5 = inativar;(boolean)
     *
     *
     */
    public static Object[][] BANCO_DADOS;
    public static int INDICE = 0;

    public static void main(String[] args) {
        BANCO_DADOS = new Object[20][6];
        int opcao = 0;
        do {
            String menu ="______________________________________________\n"
                    + "\n                                             MENU\n"
                    + "______________________________________________\n\n"
                    + "1) Incluir\n2) Excluir\n3) Editar\n4) Pesquisar\n5) Relatório\n0) Sair";
            opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (opcao) {
                case 1:
                    incluir();
                    break;
                case 2:
                    excluir();
                    break;
                case 3:
                    editar();
                    break;
                case 4:
                    pesquisar();
                    break;
                case 5:
                    relatorio();
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "ByeBye");
                    break;
                default:
            }

        } while (opcao != 0);
    }

    public static void incluir() {
        /**
         * FUNCAO CRIADA PARA A INCLUSAO DOS VEICULOS INCLUINDO TIPO/ MODELO/
         * MARCA/ ANO/ VALOR/ E UM BOLEANO PARA EXCLUSAO (OU SEJA APENAS
         * INATIVAS O VEICULO SELECIONADA SEM QUE SEJA EXCLUIDO TOTALMENTE DO
         * SISTEMA) APOS A INCLUSAO DE TODAS AS OPCOES O INDICE ACRESCENTA MAIS
         * UM (++) PARA A REPETIÇÃO DO PROCESSO AGORA EM UMA NOVA LINHA;
         */
        String tipo = JOptionPane.showInputDialog(null, "Informe o tipo do veículo\n1) Carro\n2) Moto\n3) Ônibus");
        String marca = JOptionPane.showInputDialog(null, "Informe o marca do veículo:");
        String modelo = JOptionPane.showInputDialog(null, "Informe o modelo do veículo:");
        int ano = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o ano do veículo:"));
        double valor = Double.parseDouble(JOptionPane.showInputDialog(null, "Informe o valor do veículo:"));
        BANCO_DADOS[INDICE][0] = tipo;
        BANCO_DADOS[INDICE][1] = marca;
        BANCO_DADOS[INDICE][2] = modelo;
        BANCO_DADOS[INDICE][3] = ano;
        BANCO_DADOS[INDICE][4] = valor;
        BANCO_DADOS[INDICE][5] = true;
        INDICE++;
    }

    public static String obterLista() {
        /**
         * FUNCAO CRIADA PARA VERIFICAÇÃO SE O AUTOMOVEL SELECIONADO ESTA
         * INATIVO NO SISTEMA OU NAO. APOS A VERIFICAÇÃO NO SISTEMA ELE BUSCA O
         * AUTOMOVEL DESEJADO INFORMANDO O IPVA DE ACORDO COM A CATEGORIA DO
         * VEICULO.
         */
        String msg = " Tipo do veículo      Marca     Modelo    Ano    Valor   IPVA(%)\n\n ";
        for (int i = 0; i < INDICE; i++) {
            boolean status = (boolean) BANCO_DADOS[i][5];
            if (status) {
                msg += BANCO_DADOS[i][0] + "           ";
                msg += BANCO_DADOS[i][1] + "           ";
                msg += BANCO_DADOS[i][2] + "           ";
                msg += BANCO_DADOS[i][3] + "           ";
                msg += BANCO_DADOS[i][4] + "           ";
                String tipo = (String) BANCO_DADOS[i][0];
                if (tipo.equalsIgnoreCase("carro")) {
                    msg += "IPVA aplicado é de : -" + 3 + "%\n";
                }
                if (tipo.equalsIgnoreCase("moto")) {
                    msg += "IPVA aplicado é de : -" + 2 + "%\n";
                }
                if (tipo.equalsIgnoreCase("onibus")) {
                    msg += "IPVA aplicado é de : -" + 4 + "%\n";
                }
            }
        }
        return msg;
    }

    public static int obterAutomovel() {
        /**
         * ATRAVAES DA FUNÇÃO OBTERLISTA E FEITA E VERIFICAÇÃO SE O VEICULO ESTA
         * OU NAO INATIVO NA LISTA É POSSIVEL FAZER A BUSCA DO VEICULO PARA A
         * EDIÇÃO OU EXCLUSÃO DO MESMO.
         */
        String listagem = obterLista();
        listagem += "\n\nQual modelo você deseja selecionar?";
        String veiculoSelecionado = JOptionPane.showInputDialog(listagem);
        System.out.println(veiculoSelecionado);
        int linhaSelelcionada = -1;
        for (int i = 0; i < INDICE; i++) {
            String modelo = (String) BANCO_DADOS[i][2];
            if (modelo.equals(veiculoSelecionado)) {
                linhaSelelcionada = 1;
            }
        }
        return linhaSelelcionada;
    }

    public static void excluir() {
        int linhaExcluir = obterAutomovel();
        if (linhaExcluir >= 0) {
            BANCO_DADOS[linhaExcluir][5] = false;
        }
    }

    public static void editar() {
        int linhaEditar = obterAutomovel();
        if (linhaEditar >= 0) {
            String tipo = JOptionPane.showInputDialog(null, "Informe o tipo do veículo\n1) Carro\n2) Moto\n3) Ônibus");
            String marca = JOptionPane.showInputDialog(null, "Informe o marca do veículo:");
            String modelo = JOptionPane.showInputDialog(null, "Informe o modelo do veículo:");
            int ano = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o ano do veículo:"));
            double valor = Double.parseDouble(JOptionPane.showInputDialog(null, "Informe o valor do veículo:"));
            BANCO_DADOS[linhaEditar][0] = tipo;
            BANCO_DADOS[linhaEditar][1] = marca;
            BANCO_DADOS[linhaEditar][2] = modelo;
            BANCO_DADOS[linhaEditar][3] = ano;
            BANCO_DADOS[linhaEditar][4] = valor;
        }
    }

    public static void relatorio() {
        String relatorio = obterLista();
        JOptionPane.showMessageDialog(null, relatorio);
    }

    public static void pesquisar() {
        String filtro = JOptionPane.showInputDialog("Digite o filtro:\n\n*Marca\n*Modelo");
        String msg = " Tipo do veículo      Marca     Modelo    Ano    Valor   IPVA(%)\n\n ";
        for (int i = 0; i < INDICE; i++) {
            boolean status = (boolean) BANCO_DADOS[i][5];
            if (status) {
                String marca = (String) BANCO_DADOS[i][1];
                String modelo = (String) BANCO_DADOS[i][2];
                if (marca.toUpperCase().contains(filtro.toUpperCase()) || modelo.toUpperCase().contains(filtro.toUpperCase())) {
                    msg += BANCO_DADOS[i][0] + "             ";
                    msg += BANCO_DADOS[i][1] + "           ";
                    msg += BANCO_DADOS[i][2] + "           ";
                    msg += BANCO_DADOS[i][3] + "           ";
                    msg += BANCO_DADOS[i][4] + "           ";
                    String tipo = (String) BANCO_DADOS[i][0];
                    if (tipo.equalsIgnoreCase("carro")) {
                        msg += "IPVA aplicado é de : -" + 3 + "%\n";
                    }
                    if (tipo.equalsIgnoreCase("moto")) {
                        msg += "IPVA aplicado é de : -" + 2 + "%\n";
                    }
                    if (tipo.equalsIgnoreCase("onibus")) {
                        msg += "IPVA aplicado é de : -" + 4 + "%\n";
                    }
                }
            }
        }
        JOptionPane.showMessageDialog(null, msg);

    }
}
