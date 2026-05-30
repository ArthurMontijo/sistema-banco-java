package banco.app;

import banco.model.Cliente;
import banco.model.ContaBancaria;
import banco.model.ContaCorrente;
import banco.model.ContaPoupanca;
import banco.service.BancoService;

import javax.swing.JOptionPane;

public class SistemaBanco {

    private BancoService service;

    public SistemaBanco() {
        service = new BancoService();
    }

    public static void main(String[] args) {
        SistemaBanco sistema = new SistemaBanco();
        sistema.iniciar();
    }

    public void iniciar() {

        int opcao;

        do {

            String entrada = JOptionPane.showInputDialog(
                    "===== SISTEMA BANCÁRIO =====\n\n" +
                    "1 - Cadastrar Conta Corrente\n" +
                    "2 - Cadastrar Conta Poupança\n" +
                    "3 - Depositar\n" +
                    "4 - Sacar\n" +
                    "5 - Consultar Saldo\n" +
                    "6 - Exibir Extrato\n" +
                    "7 - Exibir Histórico\n" +
                    "8 - Listar Todas as Contas\n" +
                    "9 - Relatório Geral\n" +
                    "0 - Sair"
            );

            if (entrada == null) {
                return;
            }

            try {
                opcao = Integer.parseInt(entrada);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Opção inválida.");
                continue;
            }

            switch (opcao) {

                case 1:
                    cadastrarContaCorrente();
                    break;

                case 2:
                    cadastrarContaPoupanca();
                    break;

                case 3:
                    depositar();
                    break;

                case 4:
                    sacar();
                    break;

                case 5:
                    consultarSaldo();
                    break;

                case 6:
                    exibirExtrato();
                    break;

                case 7:
                    exibirHistorico();
                    break;

                case 8:
                    service.listarTodasAsContas();
                    break;

                case 9:
                    service.exibirRelatorioGeral();
                    break;

                case 0:
                   JOptionPane.showMessageDialog(
                     null,
                      "Sistema encerrado."
                     );
                    return;

                default:
                    JOptionPane.showMessageDialog(
                            null,
                            "Opção inválida."
                    );
            }

        } while (true);
    }

    private void cadastrarContaCorrente() {

        try {

            String numeroConta =
                    JOptionPane.showInputDialog("Número da conta:");

            String nome =
                    JOptionPane.showInputDialog("Nome:");

            String cpf =
                    JOptionPane.showInputDialog("CPF:");

            String telefone =
                    JOptionPane.showInputDialog("Telefone:");

            double saldoInicial = Double.parseDouble(
                    JOptionPane.showInputDialog("Saldo inicial:")
            );

            double limite = Double.parseDouble(
                    JOptionPane.showInputDialog(
                            "Limite cheque especial:"
                    )
            );

            Cliente cliente =
                    new Cliente(nome, cpf, telefone);

            ContaCorrente conta =
                    new ContaCorrente(
                            numeroConta,
                            cliente,
                            saldoInicial,
                            limite
                    );

            if (service.cadastrarContaCorrente(conta)) {

                JOptionPane.showMessageDialog(
                        null,
                        "Conta corrente cadastrada."
                );

            } else {

                JOptionPane.showMessageDialog(
                        null,
                        "Número de conta já existe."
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Dados inválidos."
            );
        }
    }

    private void cadastrarContaPoupanca() {

        try {

            String numeroConta =
                    JOptionPane.showInputDialog("Número da conta:");

            String nome =
                    JOptionPane.showInputDialog("Nome:");

            String cpf =
                    JOptionPane.showInputDialog("CPF:");

            String telefone =
                    JOptionPane.showInputDialog("Telefone:");

            double saldoInicial = Double.parseDouble(
                    JOptionPane.showInputDialog("Saldo inicial:")
            );

            double taxa = Double.parseDouble(
                    JOptionPane.showInputDialog(
                            "Taxa de rendimento:"
                    )
            );

            Cliente cliente =
                    new Cliente(nome, cpf, telefone);

            ContaPoupanca conta =
                    new ContaPoupanca(
                            numeroConta,
                            cliente,
                            saldoInicial,
                            taxa
                    );

            if (service.cadastrarContaPoupanca(conta)) {

                JOptionPane.showMessageDialog(
                        null,
                        "Conta poupança cadastrada."
                );

            } else {

                JOptionPane.showMessageDialog(
                        null,
                        "Número de conta já existe."
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Dados inválidos."
            );
        }
    }

    private ContaBancaria buscarConta() {

        String numeroConta =
                JOptionPane.showInputDialog(
                        "Número da conta:"
                );

        ContaBancaria conta =
                service.buscarConta(numeroConta);

        if (conta == null) {

            JOptionPane.showMessageDialog(
                    null,
                    "Conta não encontrada."
            );
        }

        return conta;
    }

    private void depositar() {

        try {

            ContaBancaria conta = buscarConta();

            if (conta == null) {
                return;
            }

            double valor = Double.parseDouble(
                    JOptionPane.showInputDialog(
                            "Valor do depósito:"
                    )
            );

            conta.depositar(valor);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Valor inválido."
            );
        }
    }

    private void sacar() {

        try {

            ContaBancaria conta = buscarConta();

            if (conta == null) {
                return;
            }

            double valor = Double.parseDouble(
                    JOptionPane.showInputDialog(
                            "Valor do saque:"
                    )
            );

            boolean sucesso = conta.sacar(valor);

            if (sucesso) {

                JOptionPane.showMessageDialog(
                        null,
                        "Saque realizado."
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Valor inválido."
            );
        }
    }

    private void consultarSaldo() {

        ContaBancaria conta = buscarConta();

        if (conta == null) {
            return;
        }

        conta.exibirSaldo();
    }

    private void exibirExtrato() {

        ContaBancaria conta = buscarConta();

        if (conta == null) {
            return;
        }

        if (conta instanceof ContaCorrente) {

            ((ContaCorrente) conta).gerarExtrato();

        } else if (conta instanceof ContaPoupanca) {

            ((ContaPoupanca) conta).gerarExtrato();
        }
    }

    private void exibirHistorico() {

        ContaBancaria conta = buscarConta();

        if (conta == null) {
            return;
        }

        conta.exibirHistorico();
    }
}

