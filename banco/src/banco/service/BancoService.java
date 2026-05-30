package banco.service;

import banco.model.ContaBancaria;
import banco.model.ContaCorrente;
import banco.model.ContaPoupanca;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class BancoService {

    private List<ContaCorrente> contasCorrentes;
    private List<ContaPoupanca> contasPoupanca;

    public BancoService() {
        contasCorrentes = new ArrayList<>();
        contasPoupanca = new ArrayList<>();
    }

    public boolean cadastrarContaCorrente(ContaCorrente conta) {

        if (buscarConta(conta.getNumeroConta()) != null) {
            return false;
        }

        contasCorrentes.add(conta);
        return true;
    }

    public boolean cadastrarContaPoupanca(ContaPoupanca conta) {

        if (buscarConta(conta.getNumeroConta()) != null) {
            return false;
        }

        contasPoupanca.add(conta);
        return true;
    }

    public ContaBancaria buscarConta(String numeroConta) {

        for (ContaCorrente conta : contasCorrentes) {
            if (conta.getNumeroConta().equals(numeroConta)) {
                return conta;
            }
        }

        for (ContaPoupanca conta : contasPoupanca) {
            if (conta.getNumeroConta().equals(numeroConta)) {
                return conta;
            }
        }

        return null;
    }

    public void listarTodasAsContas() {

        StringBuilder sb = new StringBuilder();

        sb.append("LISTA DE CONTAS\n\n");

        for (ContaCorrente conta : contasCorrentes) {

            sb.append("Conta: ")
              .append(conta.getNumeroConta())
              .append("\n");

            sb.append("Titular: ")
              .append(conta.getTitular().getNome())
              .append("\n");

            sb.append("Saldo: R$ ")
              .append(String.format("%.2f", conta.getSaldo()))
              .append("\n\n");
        }

        for (ContaPoupanca conta : contasPoupanca) {

            sb.append("Conta: ")
              .append(conta.getNumeroConta())
              .append("\n");

            sb.append("Titular: ")
              .append(conta.getTitular().getNome())
              .append("\n");

            sb.append("Saldo: R$ ")
              .append(String.format("%.2f", conta.getSaldo()))
              .append("\n\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public double calcularPatrimonioTotal() {

        double total = 0;

        for (ContaCorrente conta : contasCorrentes) {
            total += conta.getSaldo();
        }

        for (ContaPoupanca conta : contasPoupanca) {
            total += conta.getSaldo();
        }

        return total;
    }

    public void exibirRelatorioGeral() {

        ContaBancaria maior = null;
        ContaBancaria menor = null;

        for (ContaCorrente conta : contasCorrentes) {

            if (maior == null || conta.getSaldo() > maior.getSaldo()) {
                maior = conta;
            }

            if (menor == null || conta.getSaldo() < menor.getSaldo()) {
                menor = conta;
            }
        }

        for (ContaPoupanca conta : contasPoupanca) {

            if (maior == null || conta.getSaldo() > maior.getSaldo()) {
                maior = conta;
            }

            if (menor == null || conta.getSaldo() < menor.getSaldo()) {
                menor = conta;
            }
        }

        StringBuilder sb = new StringBuilder();

        sb.append("RELATÓRIO GERAL\n\n");

        sb.append("Contas Corrente: ")
          .append(contasCorrentes.size())
          .append("\n");

        sb.append("Contas Poupança: ")
          .append(contasPoupanca.size())
          .append("\n");

        sb.append("Patrimônio Total: R$ ")
          .append(String.format("%.2f", calcularPatrimonioTotal()))
          .append("\n\n");

        if (maior != null) {

            sb.append("Maior Saldo:\n");
            sb.append(maior.getNumeroConta())
              .append(" - R$ ")
              .append(String.format("%.2f", maior.getSaldo()))
              .append("\n\n");
        }

        if (menor != null) {

            sb.append("Menor Saldo:\n");
            sb.append(menor.getNumeroConta())
              .append(" - R$ ")
              .append(String.format("%.2f", menor.getSaldo()));
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public List<ContaCorrente> getContasCorrentes() {
        return contasCorrentes;
    }

    public List<ContaPoupanca> getContasPoupanca() {
        return contasPoupanca;
    }
}