package banco.model;

import javax.swing.JOptionPane;

public class ContaCorrente extends ContaBancaria {

    private double limiteChequeEspecial;

    public ContaCorrente(String numeroConta,
                         Cliente titular,
                         double saldoInicial,
                         double limiteChequeEspecial) {

        super(numeroConta, titular, saldoInicial);
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    public double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    public void setLimiteChequeEspecial(double limiteChequeEspecial) {
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    @Override
    public boolean sacar(double valor) {

        if (valor <= 0) {
            JOptionPane.showMessageDialog(
                    null,
                    "Valor inválido."
            );
            return false;
        }

        double limiteTotal =
                getSaldo() + limiteChequeEspecial;

        if (valor > limiteTotal) {

            JOptionPane.showMessageDialog(
                    null,
                    "Limite insuficiente."
            );

            registrarTransacao(
                    "Saque negado - R$ "
                            + String.format("%.2f", valor)
            );

            return false;
        }

        if (valor > getSaldo()) {

            JOptionPane.showMessageDialog(
                    null,
                    "Cheque especial utilizado."
            );

            registrarTransacao(
                    "Cheque especial utilizado - R$ "
                            + String.format("%.2f", valor)
            );
        }

        setSaldo(getSaldo() - valor);

        registrarTransacao(
                "Saque - R$ "
                        + String.format("%.2f", valor)
        );

        return true;
    }

    public void usarChequeEspecial(double valor) {
        sacar(valor);
    }

    @Override
    public void gerarExtrato() {

        StringBuilder sb = new StringBuilder();

        sb.append("EXTRATO CONTA CORRENTE\n\n");
        sb.append("Conta: ")
                .append(getNumeroConta())
                .append("\n");

        sb.append(getTitular())
                .append("\n\n");

        sb.append("Saldo: R$ ")
                .append(String.format("%.2f", getSaldo()))
                .append("\n");

        sb.append("Limite Cheque Especial: R$ ")
                .append(String.format("%.2f", limiteChequeEspecial))
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