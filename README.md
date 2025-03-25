# Descrição

Projeto Spring Boot de exemplo criado para interagir com o Schema Customer Orders do banco de dados Oracle 21c. São utilizados também neste projeto Spring Data JPA, Lombok e para sua documentação via Swagger o Spring Doc da OpenAPI. São abordados neste projeto também conceitos como o de consultas nativas com NativeQuery, tratativas de exceções com mensagens personalizadas e Bean Validation.

# Rodando o container Oracle com persistência

docker run -d --name oracle-21-container --mount type=bind,src=/opt/oracle/oradata,dst=/opt/oracle/oradata -p 1521:1521 -e "ORACLE_PASSWORD=passw716!" gvenzl/oracle-xe:21. 

# Instalando o Schema Customer Orders

https://github.com/oracle-samples/db-sample-schemas/tree/main/customer_orders

# Rodando o projeto

mvn clean install

mvn spring-boot:run








