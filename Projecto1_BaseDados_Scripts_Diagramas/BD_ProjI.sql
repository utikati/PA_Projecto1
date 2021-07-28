/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     06/04/2021 09:36:41                          */
/*==============================================================*/


drop table if exists CATEGORIA;

drop table if exists CLIENTES;

drop table if exists ENCOMENDA;

drop table if exists ENCOMENDA_PRODUTO;

drop table if exists FUNCIONARIOS;

drop table if exists LOGS;

drop table if exists NOTIFICACAO;

drop table if exists PEDIDO;

drop table if exists PRODUTO;

drop table if exists UTILIZADORES;

/*==============================================================*/
/* Table: CATEGORIA                                             */
/*==============================================================*/
create table CATEGORIA
(
   DESIGNACAO_PRODUTO   varchar(100) not null,
   CLASSIFICACAO_PRODUTO varchar(30) not null,
   ID_CATEGORIA         int not null AUTO_INCREMENT,
   primary key (ID_CATEGORIA)
);

/*==============================================================*/
/* Table: UTILIZADORES                                          */
/*==============================================================*/
create table UTILIZADORES
(
   ID_UTILIZADOR        int not null AUTO_INCREMENT,
   NOME_UTILIZADOR      varchar(150) not null,
   LOGIN_UTILIZADOR     varchar(20) not null,
   PASSWORD_UTILIZADOR  varchar(30) not null,
   EMAIL_UTILIZADOR     varchar(50) not null,
   ESTADO_UTILIZADOR    varchar(30) not null,
   TIPO_UTILIZADOR      varchar(20) not null,
   primary key (ID_UTILIZADOR)
);

/*==============================================================*/
/* Table: CLIENTES                                              */
/*==============================================================*/
create table CLIENTES
(
   ID_UTILIZADOR        int not null,
   CONTRIBUINTE_UTILIZADOR int not null,
   CONTACTO_UTILIZADOR  int not null,
   MORADA_UTILIZADOR    varchar(250) not null,
   primary key (ID_UTILIZADOR),
   constraint FK_HERANCA_UTILIZADOR_CLIENTE foreign key (ID_UTILIZADOR)
      references UTILIZADORES (ID_UTILIZADOR) on delete restrict on update restrict
);

/*==============================================================*/
/* Table: FUNCIONARIOS                                          */
/*==============================================================*/
create table FUNCIONARIOS
(
   ID_UTILIZADOR        int not null,
   FUNCIONARIO_DATA_INICIO date not null,
   ESPECIALIZACAO_FUNCIONARIO varchar(30) not null,
   primary key (ID_UTILIZADOR),
   constraint FK_HERANCA_CLIENTE_FUNCIONARIO foreign key (ID_UTILIZADOR)
      references CLIENTES (ID_UTILIZADOR) on delete restrict on update restrict
);

/*==============================================================*/
/* Table: ENCOMENDA                                             */
/*==============================================================*/
create table ENCOMENDA
(
   ID_ENCOMENDA         int not null AUTO_INCREMENT,
   ID_UTILIZADOR        int,
   CLI_ID_UTILIZADOR    int not null,
   FUN_ID_UTILIZADOR    int,
   FUN_ID_UTILIZADOR2   int,
   IDENTIFICADOR_ENCOMENDA varchar(50) not null,
   CUSTO_ENCOMENDA      float(3) not null,
   DATACRIACAO_ENCOMENDA date not null,
   DATAACEITACAO_ENCOMENDA date,
   DATAENTREGA_ENCOMENDA date,
   ESTADO_ENCOMENDA     varchar(20) not null,
   primary key (ID_ENCOMENDA),
   constraint FK_CLIENTE_ENCOMENDA foreign key (CLI_ID_UTILIZADOR)
      references CLIENTES (ID_UTILIZADOR) on delete restrict on update restrict,
   constraint FK_ARMAZENISTA_ENCOMENDA foreign key (FUN_ID_UTILIZADOR)
      references FUNCIONARIOS (ID_UTILIZADOR) on delete restrict on update restrict,
   constraint FK_ESTAFETA_ENCOMENDA foreign key (FUN_ID_UTILIZADOR2)
      references FUNCIONARIOS (ID_UTILIZADOR) on delete restrict on update restrict,
   constraint FK_UTILIZADOR_ENCOMENDA foreign key (ID_UTILIZADOR)
      references UTILIZADORES (ID_UTILIZADOR) on delete restrict on update restrict
);

/*==============================================================*/
/* Table: PRODUTO                                               */
/*==============================================================*/
create table PRODUTO
(
   ID_PRODUTO           int not null AUTO_INCREMENT,
   ID_CATEGORIA         int not null,
   DESIGNACAO_PRODUTO   varchar(100) not null,
   FABRICANTE_PRODUTO   varchar(100) not null,
   QUANTIDADE_PRODUTO   int not null,
   PRECO_PRODUTO        float(3) not null,
   SKU_PRODUTO          int not null,
   LOTE_PRODUTO         varchar(30) not null,
   DATAPRODUCAO_PRODUTO date not null,
   DATAVALIDADE_PRODUTO date,
   PESO_PRODUTO         float(2),
   primary key (ID_PRODUTO),
   constraint FK_PRODUTO_CATEGORIA foreign key (ID_CATEGORIA)
      references CATEGORIA (ID_CATEGORIA) on delete restrict on update restrict
);

/*==============================================================*/
/* Table: ENCOMENDA_PRODUTO                                     */
/*==============================================================*/
create table ENCOMENDA_PRODUTO
(
   ID_ENCOMENDA         int not null,
   ID_PRODUTO           int not null,
   QUANTIDADE_PRODUTO   int not null,
   primary key (ID_ENCOMENDA, ID_PRODUTO),
   constraint FK_RELATIONSHIP_9 foreign key (ID_ENCOMENDA)
      references ENCOMENDA (ID_ENCOMENDA) on delete restrict on update restrict,
   constraint FK_RELATIONSHIP_10 foreign key (ID_PRODUTO)
      references PRODUTO (ID_PRODUTO) on delete restrict on update restrict
);

/*==============================================================*/
/* Table: LOGS                                                  */
/*==============================================================*/
create table LOGS
(
   ID_LOG               int not null AUTO_INCREMENT,
   ID_UTILIZADOR        int,
   ACAO_LOG             varchar(100) not null,
   DATA_LOG             date not null,
   primary key (ID_LOG),
   constraint FK_LOG_UTILIZADORES foreign key (ID_UTILIZADOR)
      references UTILIZADORES (ID_UTILIZADOR) on delete restrict on update restrict
);

/*==============================================================*/
/* Table: PEDIDO                                                */
/*==============================================================*/
create table PEDIDO
(
   ID_PEDIDO            int not null AUTO_INCREMENT,
   ID_UTILIZADOR        int,
   ID_ENCOMENDA         int,
   CLI_ID_UTILIZADOR    int,
   FUN_ID_UTILIZADOR    int,
   TIPO_PEDIDO          varchar(150) not null,
   ESTADO_PEDIDO        varchar(30) not null,
   primary key (ID_PEDIDO),
   constraint FK_PEDIDO_FUNCIONARIO foreign key (FUN_ID_UTILIZADOR)
      references FUNCIONARIOS (ID_UTILIZADOR) on delete restrict on update restrict,
   constraint FK_PEDIDO_CLIENTE foreign key (CLI_ID_UTILIZADOR)
      references CLIENTES (ID_UTILIZADOR) on delete restrict on update restrict,
   constraint FK_PEDIDO_UTILIZADOR foreign key (ID_UTILIZADOR)
      references UTILIZADORES (ID_UTILIZADOR) on delete restrict on update restrict,
   constraint FK_PEDIDO_ENCOMENDA foreign key (ID_ENCOMENDA)
      references ENCOMENDA (ID_ENCOMENDA) on delete restrict on update restrict
);

/*==============================================================*/
/* Table: NOTIFICACAO                                           */
/*==============================================================*/
create table NOTIFICACAO
(
   ID_NOTIFICACAO       int not null AUTO_INCREMENT,
   ID_ENCOMENDA         int,
   ID_UTILIZADOR        int,
   ID_PEDIDO            int,
   ESTADO_NOTIFICACAO   varchar(30) not null,
   DESCRICAO_NOTIFICACAO varchar(100) not null,
   primary key (ID_NOTIFICACAO),
   constraint FK_UTILIZADORES_NOTIFICACAO foreign key (ID_UTILIZADOR)
      references UTILIZADORES (ID_UTILIZADOR) on delete restrict on update restrict,
   constraint FK_ENCOMENDA_NOTIFICACAO foreign key (ID_ENCOMENDA)
      references ENCOMENDA (ID_ENCOMENDA) on delete restrict on update restrict,
   constraint FK_PEDIDO_NOTIFICACAO foreign key (ID_PEDIDO)
      references PEDIDO (ID_PEDIDO) on delete restrict on update restrict
);

