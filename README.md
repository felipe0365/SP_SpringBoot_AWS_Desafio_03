# 🛠️ Desafio 03 - Microserviços com Java, Spring Boot e AWS EC2

Este projeto faz parte do **Desafio 03** do programa **SP - SpringBoot + AWS - Setembro/2024**. O objetivo é desenvolver um sistema de microserviços utilizando **Java**, **Spring Boot** e **AWS EC2**, que simula um sistema de gerenciamento de pedidos.

---

## 🎯 Objetivo do Projeto

Desenvolver um sistema de microserviços que inclui três serviços principais:

1. **Serviço de Pedido**: Gerenciamento de pedidos, incluindo verificação e atualização de estoque.
2. **Serviço de Estoque**: Controle de produtos disponíveis e suas quantidades.
3. **Serviço de Cliente**: Cadastro de clientes e consulta de histórico de pedidos.

Os serviços se comunicam via **REST API** e devem ser implantados em instâncias **AWS EC2**.

---

## 🛠️ Tecnologias Utilizadas

- **Java** (JDK 17)
- **Spring Boot** (versão 2.6.5)
- **Spring Data JPA**
- **PostgreSQL** (usando Docker)
- **Docker Compose**
- **OpenFeign** (para comunicação entre microserviços)
- **AWS EC2** (implantação)
- **Elastic Load Balancer (ELB)** (balanceamento de carga)
- **Swagger** (documentação de APIs)
- **Log4j2** (logs)
- **JUnit e Spring Boot Test** (testes)

---

## 🗂️ Estrutura dos Microserviços

### 1️⃣ **Serviço de Pedido**
- Criação, atualização e consulta de pedidos.
- Verifica a disponibilidade de produtos no estoque antes de criar o pedido.
- Atualiza o estoque após a confirmação do pedido.

### 2️⃣ **Serviço de Estoque**
- Gerenciamento de produtos e suas quantidades disponíveis.
- Oferece endpoints para consulta e atualização do estoque.

### 3️⃣ **Serviço de Cliente**
- Gerenciamento de cadastro de clientes.
- Consulta de histórico de pedidos de clientes.

---

## 📑 Funcionalidades

- **Criação de Pedidos:**
    - Verifica estoque antes de concluir a operação.
    - Atualiza o estoque automaticamente.

- **Gerenciamento de Estoque:**
    - Atualização e consulta de produtos disponíveis.

- **Cadastro de Clientes:**
    - Registra e gerencia informações de clientes.

- **Comunicação entre Microserviços:**
    - Realizada via chamadas REST utilizando **OpenFeign**.

---

## 📚 Documentação e Testes

- **Swagger**: Documentação automatizada para APIs REST.
- **Testes Unitários**: Validação da lógica com JUnit.
- **Testes de Integração**: Comunicação entre serviços utilizando Spring Boot Test.
- **Logs Detalhados**: Monitore operações com Log4j2.

---
