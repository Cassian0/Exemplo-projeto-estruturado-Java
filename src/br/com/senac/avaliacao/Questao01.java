package br.com.senac.avaliacao;

import javax.swing.JOptionPane;

/**
 * O banco de dados precisa armazenar a inscrição, proprietário, area e tipo;
 *
 * @author cassiano.schmitz
 */
public class Questao01 {

    /**
     * 0 = Inscrição do Imóvel(string); 1 = proprietario(String); 2 = Area do
     * imóvel(double); 3 = tipo do imóvel(int);
     */
    public static Object[][] BANCO_DADOS;
    public static int INDICE = 0;

    public static void main(String[] args) {
        BANCO_DADOS = new Object[20][5];
        int opcao = 0;
        do {
            String menu = "1) Incluir\n2)Excluir\n3)Editar\n4)Pesquisar\n6) Relatório\n0) Sair";
            String aux = JOptionPane.showInputDialog(menu);
            opcao = Integer.parseInt(aux);
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
                case 6:
                    relatorio();
                    break;
                case 0:
                    System.out.println("Tchau");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção Inválida");

            }
        } while (opcao != 0);

    }

    public static void incluir() {
        String inscricao = JOptionPane.showInputDialog("Informe a Inscricao do imóvel");
        String proprietario = JOptionPane.showInputDialog("Informe o Proprietario");
        double area = Double.parseDouble(JOptionPane.showInputDialog("Informe a area do imóvel"));
        int tipo = Integer.parseInt(JOptionPane.showInputDialog("Digite o tipo de imóvel:\n1) Residencial\n2) Comercial"));
        BANCO_DADOS[INDICE][0] = inscricao;
        BANCO_DADOS[INDICE][1] = proprietario;
        BANCO_DADOS[INDICE][2] = area;
        BANCO_DADOS[INDICE][3] = tipo;
        BANCO_DADOS[INDICE][4] = true;
        INDICE++;
    }

    public static void relatorio() {
        String relatorio = obterListagem();
        JOptionPane.showMessageDialog(null, relatorio);
    }

    public static String obterListagem() {
        String msg = "Incrição do Imóvel  Propiretário  Area  Tipo  Taxa de lixo\n";
        for (int i = 0; i < INDICE; i++) {
            Object temp = BANCO_DADOS[i][4];
            boolean status = (boolean) temp;
            if (status) {
                msg += BANCO_DADOS[i][0] + "  ";
                msg += BANCO_DADOS[i][1] + "  ";
                msg += BANCO_DADOS[i][2] + "  ";
                msg += BANCO_DADOS[i][3] + "  ";
                if ((int) BANCO_DADOS[i][3] == 1) {
                    msg += "Residencial  R$" + 15.0;
                } else {
                    msg += "Comercial  R$" + 21.0;
                }
            }
        }
        return msg;
    }

    public static void excluir() {
        int linhaApagar = obterImovel();
        if (linhaApagar >= 0) {
            BANCO_DADOS[linhaApagar][4] = false;
        }
    }

    public static int obterImovel() {
        String listagem = obterListagem();
        listagem += "\n\nQual inscricão você deseja selecionar";
        String numeroInscricaoAoagar = JOptionPane.showInputDialog(listagem);
        System.out.println(numeroInscricaoAoagar);
        int linhaSelecionada = -1;
        for (int i = 0; i < INDICE; i++) {
            String inscricao = (String) BANCO_DADOS[i][0];
            if (inscricao.equals(numeroInscricaoAoagar)) {
                linhaSelecionada = 1;
            }
        }
        return linhaSelecionada;
    }

    public static void editar() {
        int linhaEditar = obterImovel();
        if (linhaEditar >= 0) {
            String inscricao = JOptionPane.showInputDialog("Informe a Inscricao do imóvel");
            String proprietario = JOptionPane.showInputDialog("Informe o Proprietario");
            double area = Double.parseDouble(JOptionPane.showInputDialog("Informe a area do imóvel"));
            int tipo = Integer.parseInt(JOptionPane.showInputDialog("Digite o tipo de imóvel:\n1) Residencial\n2) Comercial"));
            BANCO_DADOS[linhaEditar][0] = inscricao;
            BANCO_DADOS[linhaEditar][1] = proprietario;
            BANCO_DADOS[linhaEditar][2] = area;
            BANCO_DADOS[linhaEditar][3] = tipo;
        }
    }

    public static void pesquisar() {
        String filtro = JOptionPane.showInputDialog("Digite o filtro:");
        String msg = "Incrição do Imóvel    Propiretário    Area    Tipo    Taxa de lixo\n";
        for (int i = 0; i < INDICE; i++) {
            Object temp = BANCO_DADOS[i][4];
            boolean status = (boolean) temp;
            if (status) {
                String insc = (String) BANCO_DADOS[i][0];
                String prop = (String) BANCO_DADOS[i][1];
                if (prop.toUpperCase().contains(filtro.toUpperCase()) || filtro.equalsIgnoreCase(insc)) {
                    msg += BANCO_DADOS[i][0] + "    ";
                    msg += BANCO_DADOS[i][1] + "    ";
                    msg += BANCO_DADOS[i][2] + "    ";
                    msg += BANCO_DADOS[i][3] + "    ";
                    if ((int) BANCO_DADOS[i][3] == 1) {
                        msg += "Residencial  R$" + 15.0;
                    } else {
                        msg += "Comercial  R$" + 21.0;

                    }
                }
            }
        }
        JOptionPane.showMessageDialog(null, msg);
    }
}
