package banco.model;

import banco.interfaces.Operavel;

import javax.swing.JOptionPane;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class ContaBancaria implements Operavel {

    private String numeroConta;
    private Cliente titular;
    private double saldo;
    private List<String> historico;

    public ContaBancaria(String numeroConta,
                         Cliente titular,
                         double saldoInicial) {

        this.numeroConta = numeroConta;
        this.titular = titular;
        this.saldo = saldoInicial;
        this.historico = new ArrayList<>();

        registrarTransacao(
                "Conta criada - Saldo inicial: R$ "
                        + String.format("%.2f", saldoInicial)
        );
    }

    @Override
    public void depositar(double valor) {

        if (valor <= 0) {
            JOptionPane.showMessageDialog(
                    null,
                    "Valor inválido para depósito."
            );
            return;
        }

        saldo += valor;

        registrarTransacao(
                "Depósito de R$ "
                        + String.format("%.2f", valor)
        );
    }

    @Override
    public boolean sacar(double valor) {

        if (valor <= 0) {
            JOptionPane.showMessageDialog(
                    null,
                    "Valor inválido para saque."
            );
            return false;
        }

        if (valor > saldo) {
            JOptionPane.showMessageDialog(
                    null,
                    "Saldo insuficiente."
            );
            return false;
        }

        saldo -= valor;

        registrarTransacao(
                "Saque de R$ "
                        + String.format("%.2f", valor)
        );

        return true;
    }

    @Override
    public void exibirSaldo() {

        JOptionPane.showMessageDialog(
                null,
                "Saldo atual: R$ "
                        + String.format("%.2f", saldo)
        );
    }

    protected void registrarTransacao(String descricao) {

        DateTimeFormatter formato =
                DateTimeFormatter.ofPattern(
                        "dd/MM/yyyy HH:mm:ss"
                );

        historico.add(
                LocalDateTime.now().format(formato)
                        + " - "
                        + descricao
        );
    }

    public void exibirHistorico() {

        StringBuilder sb = new StringBuilder();

        for (String item : historico) {
            sb.append(item).append("\n");
        }

        JOptionPane.showMessageDialog(
                null,
                sb.toString()
        );
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Cliente getTitular() {
        return titular;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public List<String> getHistorico() {
        return historico;
    }

    public void setHistorico(List<String> historico) {
        this.historico = historico;
    }

    public abstract void gerarExtrato();
}