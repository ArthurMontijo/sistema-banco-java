package banco.model;

import javax.swing.JOptionPane;

public class ContaPoupanca extends ContaBancaria {

    private double taxaRendimentoMensal;

    public ContaPoupanca(String numeroConta,
                         Cliente titular,
                         double saldoInicial,
                         double taxaRendimentoMensal) {

        super(numeroConta, titular, saldoInicial);
        this.taxaRendimentoMensal = taxaRendimentoMensal;
    }

    public double getTaxaRendimentoMensal() {
        return taxaRendimentoMensal;
    }

    public void setTaxaRendimentoMensal(double taxaRendimentoMensal) {
        this.taxaRendimentoMensal = taxaRendimentoMensal;
    }

    public double calcularRendimento() {
        return getSaldo() * taxaRendimentoMensal;
    }

    public void aplicarRendimento() {

        double rendimento = calcularRendimento();

        setSaldo(getSaldo() + rendimento);

        registrarTransacao(
                "Rendimento aplicado - R$ "
                        + String.format("%.2f", rendimento)
        );
    }

    @Override
    public void gerarExtrato() {

        StringBuilder sb = new StringBuilder();

        sb.append("EXTRATO CONTA POUPANÇA\n\n");

        sb.append("Conta: ")
                .append(getNumeroConta())
                .append("\n");

        sb.append(getTitular())
                .append("\n\n");

        sb.append("Saldo Atual: R$ ")
                .append(String.format("%.2f", getSaldo()))
                .append("\n");

        sb.append("Taxa de Rendimento: ")
                .append(String.format("%.2f%%",
                        taxaRendimentoMensal * 100))
                .append("\n");

        sb.append("Rendimento Estimado: R$ ")
                .append(String.format("%.2f",
                        calcularRendimento()))
                .append("\n\n");

        sb.append("Histórico:\n");

        for (String item : getHistorico()) {
            sb.append(item).append("\n");
        }

        JOptionPane.showMessageDialog(
                null,
                sb.toString()
        );
    }
}