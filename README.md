 Trabalho de APSOO
================================================================
[![NPM package][npm]][npm-url]
[![Build Size][build-size]][build-size-url]
[![Build Status][build-status]][build-status-url]
[![Dependencies][dependencies]][dependencies-url]
[![Dev Dependencies][dev-dependencies]][dev-dependencies-url]
[![Language Grade][lgtm]][lgtm-url]

Obs*: Esses badges são originais deste [readme.md](https://github.com/mrdoob/three.js/)

Obs**: Criar esse readme.md foi pra aprender a usar a sintaxe de criação também. Não creio que seja muito necessário.

#### Organização ####

A pasta Assets possui a configuração do banco, por meio dos arquivos de **`model`** (mwb), de **`imagem`** (png) e do **`template do banco`** (txt). Existe ainda neste pacote os scripts de contrução de banco de dados, população e querys usadas (nem todas foram usadas).

&mdash; [Assets](https://github.com/Wesleycampagna/apsoo_trabalho/tree/master/Assets)

A pasta src é o local ao qual é construido o **`codigo base`** do projeto. É o diretório que se encontra os arquivos que trabalham para fazer o sistema poposto por este trabalho funcionar. Seus três principais focos são banco de dados, design de interface e conexão dos anteriores.

&mdash; [src](https://github.com/Wesleycampagna/apsoo_trabalho/tree/master/src)

Para testes de **`query`** foi criado um diretório de base localizado em **`src/DataBase/testesQuerys`** para realiza-los.

&mdash; [testesQuery](https://github.com/Wesleycampagna/apsoo_trabalho/tree/master/src/DataBase/testesQuerys)


## Organização Interna do projeto

> src > lib

Existe uma pasta chamada lib que possui duas classes. É importante a Classe `Constantes` que setam para o projeto quais são as variaveis constantes que serão usados nos métodos.

exemplo:

```java
    public static final String DESAPARECIDO = "desaparecido";
    public static final String ATIVO = "ativo";
    public static final String FALECIDO = "falecido";
```

&mdash; [library própria](https://github.com/Wesleycampagna/apsoo_trabalho/tree/master/src/lib)

> src > Controls

A pasta controls possui as classes controladoras. 

&mdash; [Controladoras](https://github.com/Wesleycampagna/apsoo_trabalho/tree/master/src/Controls)

> src > Policia

A pasta Policia possui a implementação que teve em boa parte de sua composição a ajuda de @Pedro_Henrique durante a matéria de LPOO. 
A classe principal do javafx, aqui como **`main`**, a execução acontece por esta classe.

A classe **`PoliciaPages`** contem o caminho para a Tela que será realizada a troca na chamada de changePage(<page>)
Para atribuir uma pagina deve-se saber o caminho para o arquivo fxml que possui o design da Tela. Dar o `PAGE NAME` (constant), o `title` e `caminho` como abaixo.

```java
    public static final Page <PAGE NAME> = new Page("<title>", "<caminho>");   
```

`AS EXAMPLE:`

```java 
    public static final Page LOGIN = new Page("Log In", "/pages/login/GUI.fxml");      
```

A classe **`ControlPage`** possui o script de transição entre as pages por meio do método changePage. 

Toda tela que deseja utilizar desta transição deve obrigatorimente realizar três procedimentos em seu controlador (FXMLController):

1. extends ControlPages

```java
    public class FXMLController `extends ControlPages` {
```
2. usar changePage(PoliciaPages.<PAGE NAME IN `PoliciaPages`>)

```java
    changePage(PoliciaPages.TESTE);
```
3. No fxml da Tela deve estar atribuido um `AnchorPane` com `id` como `rootPane`. Veja como: 
[veja](https://github.com/Wesleycampagna/apsoo_trabalho/blob/master/src/pages/login/GUI.fxml)

```xml
    <AnchorPane id="rootPane" fx:id="rootPane" prefHeight="230" prefWidth="320" xmlns="http://javafx.com/javafx/10.0.1" xmlns:fx="http://javafx.com/fxml/1" fx:controller="pages.login.FXMLController">
    <children>
```
Basta apenas `fx:id="rootPane"`, mas pode ser deixado os dois `id="rootPane" fx:id="rootPane"`
Duvidas! [veja](https://github.com/Wesleycampagna/apsoo_trabalho/blob/master/src/pages/login/FXMLController.java)

&mdash; [Policia](https://github.com/Wesleycampagna/apsoo_trabalho/tree/master/src/Policia)

> src > Conceitos|DataBase|pages

As pastas **`Conceitos`**, **`DataBase`** e **`pages`** representam respectvamente [classes de conceito/persistencia](https://github.com/Wesleycampagna/apsoo_trabalho/tree/master/src/Conceitos), [Banco de Dados](https://github.com/Wesleycampagna/apsoo_trabalho/tree/master/src/DataBase) e as [Telas de Transição](https://github.com/Wesleycampagna/apsoo_trabalho/tree/master/src/pages).

# Instalação

###  Base ###
- NetBeans com java JDK
- SceneBuilder
- mySQL 
- mySQL Workbench

### criando banco de dados via script ###

> via WorkBench - script

No menu faça o caminho `FILE > RUN SCRIPT... > (selecione` [generate.sql](https://github.com/Wesleycampagna/apsoo_trabalho/blob/master/Assets/generate.sql)`) > ..` até terminar;

> via Workbench - model

Clique sobre [model_bd_apsoo.mwb](https://github.com/Wesleycampagna/apsoo_trabalho/blob/master/Assets/model_bd_apsoo.mwb) faça o caminho `DATABASE > ENGINEER FORWARD... > (configs) > NEXT > NEXT ...` e está criado o banco de dados.

### polulando banco de dados via script ###

> via WorkBench - script

No menu faça o caminho `FILE > OPEN... > (selecione` [populate.sql](https://github.com/Wesleycampagna/apsoo_trabalho/blob/master/Assets/populate.sql)`)` clique sobre o `raizinho` e `gg!`;

### connector mySQL JDBC ###

Foi escolhido usar banco de dados via Connector [JDBC](https://dev.mysql.com/downloads/connector/j/8.0.html) para obter os dados do banco de dados. Via link acima é possivel baixar conforme sistema operacional.

para instalar: abra o folder TRABALHO_APSOO no NetBeans. Em _`Libraries`_ clique segundo botão > _`adicionar jar..`_ e instale o connector.

# Usar banco de dados

Para configura-lo será necessário:

```java
    private Connection connect;
    private Statement statement;
    private final String dataBaseName;
    private final String password = "<sua senha bd>";
    private final String user = "<seu login bd>";
```
que atende a chamada de conexão:

```java
    try {
      // to problems with timezone, see bottom code
      connect = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/" + dataBaseName, 
        user, 
        password
      );
```
Pode ocorrer problemas com timezone, atribua o trecho acima como:

```java
    try {
      connect = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/" + 
        dataBaseName + "?useTimezone=true&serverTimezone=UTC",
        user, 
        password
      );
```

teste a conexão com [isto](https://github.com/Wesleycampagna/apsoo_trabalho/blob/master/src/DataBase/testesQuerys/connection_teste.java).


realizar a chamada do banco:

```java
      Object obj = new Object();
      
      ControladoraBD instance = new ControladoraBD("<nome banco>");
      obj = instance.query();
      //or
      obj = new ControladoraBD("<nome banco>").query();
```


`AS DEFAULT` nome do banco é **`policia_db`**




[npm]: https://img.shields.io/npm/v/three.svg
[npm-url]: https://www.npmjs.com/package/three
[build-size]: https://badge-size.herokuapp.com/mrdoob/three.js/master/build/three.min.js.svg?compression=gzip
[build-size-url]: https://github.com/mrdoob/three.js/tree/master/build
[build-status]: https://travis-ci.org/mrdoob/three.js.svg?branch=dev
[build-status-url]: https://travis-ci.org/mrdoob/three.js
[dependencies]: https://img.shields.io/david/mrdoob/three.js.svg
[dependencies-url]: https://david-dm.org/mrdoob/three.js
[dev-dependencies]: https://img.shields.io/david/dev/mrdoob/three.js.svg
[dev-dependencies-url]: https://david-dm.org/mrdoob/three.js#info=devDependencies
[lgtm]: https://img.shields.io/lgtm/grade/javascript/g/mrdoob/three.js.svg?label=code%20quality
[lgtm-url]: https://lgtm.com/projects/g/mrdoob/three.js/
