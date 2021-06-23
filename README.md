<h2> Este repositorio tem como objetivo fazer uma das entregas do curso
```
https://digitalinnovation.one/bootcamps/gft-start-2-java
```
No curso foi ensinado:
* Setup inicial de projeto com o Spring Boot Initialzr
* Criação de modelo de dados para o mapeamento de entidades em bancos de dados
* Desenvolvimento de operações de gerenciamento de usuários (Cadastro, leitura, atualização e remoção de pessoas de um sistema).
* Relação de cada uma das operações acima com o padrão arquitetural REST, e a explicação de cada um dos conceitos REST envolvidos durante o desenvolvimento do projeto.
* Desenvolvimento de testes unitários para validação das funcionalidades(Em nivel de servico e controller/resource) 
* Implantação do sistema na nuvem através do Heroku

* Adicionei o swagger 2 para documentação
* Adicionei o configuração para test e dev
* Adicionei o "Person Job Information" como detalhe de pessoas.
* Adicionei a validação doSonarQube versão light docker.

Para executar o projeto no terminal, digite o seguinte comando:

```shell script
mvn spring-boot:run 
```

Após executar o comando acima, basta apenas abrir o seguinte endereço e visualizar a execução do projeto:
```
http://localhost:8080/api/v2/people
```
Execução no heroku na conta dev:
```
http://localhost:8080/api/v2/people
```
Processos adicionados por mim:

SonarQube para controlar a qualidade do codigo:
```
mvn sonar:sonar \
-Dsonar.projectKey=dio-personal-api \
-Dsonar.host.url=http://localhost:9000 \
-Dsonar.login=20d68a5f70132fbbbac1a194b91157fc6b6dbef2
```

Swagger para para controlar a documentação web:

Pendências:
Necessário adicionar Spring-security
Necessário adicionar Envio de senha por e-mail , reset por email e contracheque.
Necessário adicionar com banco de dados SGBD.

Detalhes do curso:
São necessários os seguintes pré-requisitos para a execução do projeto desenvolvido durante a aula:

* Java 11 ou versões superiores.
* Maven 3.6.3 ou versões superiores.
* Intellj IDEA Community Edition ou sua IDE favorita.
* Controle de versão GIT instalado na sua máquina.
* Conta no GitHub para o armazenamento do seu projeto na nuvem.
* Conta no Heroku para o deploy do projeto na nuvem

Abaixo, seguem links bem bacanas, sobre tópicos mencionados durante a aula:

* [SDKMan! para gerenciamento e instalação do Java e Maven](https://sdkman.io/)
* [Referência do Intellij IDEA Community, para download](https://www.jetbrains.com/idea/download)
* [Palheta de atalhos de comandos do Intellij](https://resources.jetbrains.com/storage/products/intellij-idea/docs/IntelliJIDEA_ReferenceCard.pdf)
* [Site oficial do Spring](https://spring.io/)
* [Site oficial do Spring Initialzr, para setup do projeto](https://start.spring.io/)
* [Site oficial do Heroku](https://www.heroku.com/)
* [Site oficial do GIT](https://git-scm.com/)
* [Site oficial do GitHub](http://github.com/)
* [Documentação oficial do Lombok](https://projectlombok.org/)
* [Documentação oficial do Map Struct](https://mapstruct.org/)
* [Referência para o padrão arquitetural REST](https://restfulapi.net/)
* [REST API Documentation Tool | Swagger UI](https://swagger.io/tools/swagger-ui/)
  
* [Neste link](https://drive.google.com/file/d/1crVPOVl6ok2HeYjh3fjQuGQn2lDZVHrn/view?usp=sharing), seguem os slides apresentados como o roteiro utilizado para o desenvolvimento do projeto da nossa sessão.

Observações particulares:
 Não alterar a ordem do Lombok e Map Struct no pow.xml.
 No pow.xml foi adicionado número de verões para algumas dependências para resolver problemas de imcompatibilidades.
 A linguagem de codifição é inglês.
