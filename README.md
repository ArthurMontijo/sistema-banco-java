# Sistema Banco Java

## Descrição do Projeto

O Sistema Banco Java é uma aplicação desenvolvida em Java com foco na utilização dos principais conceitos de Programação Orientada a Objetos (POO), como encapsulamento, herança, abstração, polimorfismo e interfaces.

O sistema permite o gerenciamento de contas bancárias de diferentes tipos, possibilitando o cadastro de clientes, criação de contas correntes e poupanças, realização de depósitos e saques, consulta de saldo, emissão de extratos, visualização de histórico de transações e geração de relatórios gerais do banco.

Todas as interações com o usuário são realizadas através do JOptionPane, conforme solicitado na atividade.

---

## Tecnologias Utilizadas

* Java 21
* Visual Studio Code (VS Code)
* IntelliJ IDEA

---

## Como Compilar e Executar o Projeto

### 1. Baixar ou clonar o projeto

Abra o terminal e execute:

```bash
git clone https://github.com/seu-usuario/sistema-banco-java.git
```

### 2. Abrir o projeto

Abra a pasta do projeto no VS Code ou IntelliJ IDEA.

### 3. Compilar o projeto

Compile todas as classes Java do projeto.

### 4. Executar o sistema

Execute a classe:

```text
App.java
```

O sistema será iniciado através de janelas JOptionPane.

---

## Estrutura de Pacotes e Classes

### Pacote: banco.app

#### SistemaBanco

Classe principal responsável pelo funcionamento do sistema.

Responsabilidades:

* Exibir o menu principal.
* Receber dados do usuário.
* Chamar os métodos da camada de serviço.
* Controlar o fluxo da aplicação.

---

### Pacote: banco.interfaces

#### Operavel

Interface que define as operações básicas de uma conta bancária.

Métodos:

* depositar()
* sacar()
* exibirSaldo()
* exibirHistorico()

---

### Pacote: banco.model

#### Cliente

Representa os dados do titular da conta.

Atributos:

* nome
* cpf
* telefone

Responsabilidade:

* Armazenar os dados do cliente.

---

#### ContaBancaria

Classe abstrata que serve como base para os demais tipos de conta.

Responsabilidades:

* Armazenar número da conta.
* Armazenar titular.
* Armazenar saldo.
* Armazenar histórico de transações.
* Implementar operações comuns.

---

#### ContaCorrente

Classe que herda de ContaBancaria.

Responsabilidades:

* Gerenciar limite de cheque especial.
* Permitir saques utilizando o cheque especial.
* Gerar extrato da conta corrente.

---

#### ContaPoupanca

Classe que herda de ContaBancaria.

Responsabilidades:

* Armazenar taxa de rendimento mensal.
* Calcular rendimento.
* Aplicar rendimento.
* Gerar extrato da conta poupança.

---

### Pacote: banco.service

#### BancoService

Classe responsável pelas regras de negócio do sistema.

Responsabilidades:

* Cadastrar contas.
* Buscar contas pelo número.
* Listar contas cadastradas.
* Calcular patrimônio total.
* Gerar relatório geral.

---

## Diagrama Textual da Hierarquia de Classes

```text
Operavel (Interface)
        ▲
        │
ContaBancaria (Classe Abstrata)
        ▲
        │
 ┌───────────────┬───────────────┐
 │               │
ContaCorrente   ContaPoupanca
```

---

## Funcionalidades do Sistema

* Cadastro de Conta Corrente.
* Cadastro de Conta Poupança.
* Depósito em contas.
* Saque em contas.
* Consulta de saldo.
* Emissão de extrato.
* Exibição do histórico de transações.
* Listagem de todas as contas cadastradas.
* Relatório geral do banco.
* Controle de cheque especial.
* Controle de rendimento da poupança.

---

## Nome do Aluno

Arthur Montijo

## Turma

Turma 1 - Módulo 2

## Disciplina

Programação Orientada a Objetos (POO)

## Professor

MSc Rodrigo de Lima Cunha
