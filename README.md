# Springboot
Microsserviço em SpringBoot

# mvn clean install
Esse comando irá construir o artefato .jar, caso seja a primeira vez que esteja executando a construção, serão feitos downloads de algumas bibliotecas essenciais 
Após a mensagem de sucesso (BUILD SUCCESS),  importe o projeto na sua IDE (Eclipse e etc…)

No ${meu_diretorio}, rode:
# mvn spring-boot:run

#  Comandos docker:
Para Listar os containers:
# docker ps

Para parar o container:
# docker stop {container_id}

Após salvar o arquivo no seu diretório padrão, rodar no terminal (no mesmo diretório a onde está o seu “docker-compose.yml”:
# docker-compose up -d
Para validar se os containers estão no ar:
# docker ps

# Docker Compose
Em algum diretório da sua máquina crie o arquivo  docker-compose.yml

Coloque o conteúdo a seguir:

version: '3'
services:
  mongo:
    image: mongo:3
    ports:
      - "27017:27017"
  consul1:
    image: "consul:0.9.3"
    container_name: "consul1"
    hostname: "consul1"
    ports:
      - "8300:8300"
      - "8400:8400"
      - "8500:8500"
      - "8600:53"

	  


