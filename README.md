<div align="center">
  <a><img src="logo.png" width="400" alt="junit5-and-mockito" /></a>
</div>

# QuickBank
O QuickBank é um sistema bancário simplificado, implementado em Java utilizando o framework Spring Boot. Desenvolvido como um projeto prático, o sistema tem como objetivo principal a aplicação dos conceitos de Test-Driven Development (TDD) e a exploração das funcionalidades do JUnit 5 e Mockito.

## Endpoints da aplicação

### Customer
```markdown
GET /custormers - listar todos clientes.

GET /custormers/{cpf} - recupera cliente por cpf.

POST /custormers - cria um cliente.
```

### Accont
```markdown
GET /accounts - lista todas as contas.

GET /accounts/{accNumber} - recupera conta por número da conta.

POST /accounts - cria uma conta.
```

## Dependecias Utilizadas:
- spring-boot-web
- spring-boot-data-jpa
- spring-boot-validation
- flyway-core
- h2
- lombok