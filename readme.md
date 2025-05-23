# Projeto de exemplo em Java Spring Boot com RabbitMQ e MongoDB

## Requisitos
- Java 21
- Spring Boot
- Docker
- MongoDB Compass (para interagir com o banco de dados MongoDB)
- RabbitMQ
- Postman/Insomnia (para testar os endpoints)

## Plugins do projeto instalados do springIO:
- Spring Web
- Spring Data MongoDB
- Spring for RabbitMQ


comandos:
    ``
        docker compose up -d
    ``
    ``
        docker compose down
    ``

logar na RabbitMq:
    ``
        http://localhost:15672/
    ``
    usuario: guest
    senha: guest

estrutura para enviar msg:
```
{
  "codigoPedido": 1002,
  "codigoCliente": 1,
  "itens": [
    {
      "produto": "notebook",
      "quantidade": 1,
      "preco": 2300.00
    },
    {
      "produto": "mouse",
      "quantidade": 1,
      "preco": 250.00
    }
  ] 
}
```
```
    {
  "codigoPedido": 1001,
  "codigoCliente": 1,
  "itens": [
    {
      "produto": "lápis",
      "quantidade": 100,
      "preco": 1.10
    },
    {
      "produto": "caderno",
      "quantidade": 10,
      "preco": 1.00
    }
  ] 
}
```

Configuração do mongoDb compass
    ``
        mongodb://localhost:27017
    ``

em authentication utilize username/password:
    ```
        username: admin
        password: 123
    ```

caso queira modificar algum desses itens, altere em docker-compose.yml e application.properties

endpoint para obter o resumo dos pedidos:
    ``
        http://localhost:8080/customers/{customerId}/orders
    ``
exemplo de customerId: 1

Para maior entendimento do projeto acesse :
- [Build&Run Desafio Backend BTG Pactual](https://www.youtube.com/watch?v=e_WgAB0Th_I&t=11s)
- [Build&Run Desafio Backend BTG Pactual Part 2](https://www.youtube.com/watch?v=tL53Pk4gu-g)