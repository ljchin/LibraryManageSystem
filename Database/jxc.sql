/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2025/3/25 21:06:01                           */
/*==============================================================*/

create Database LibraryMS;

drop table if exists tb_bookInfo;

drop table if exists tb_bookType;

drop table if exists tb_borrow;

drop table if exists tb_operator;

drop table if exists tb_order;

drop table if exists tb_reader;

drop table if exists tb_stockpile;

/*==============================================================*/
/* Table: tb_bookInfo                                           */
/*==============================================================*/
create table tb_bookInfo
(
   ISBN                 varchar(13) not null,
   typeId               int,
   bookName             varchar(0),
   author               varchar(0),
   translator           varchar(0),
   publisher            varchar(0),
   date                 datetime,
   price                int,
   primary key (ISBN)
);

alter table tb_bookInfo modify bookname varchar(50);
alter table tb_bookInfo modify author varchar(50);
alter table tb_bookInfo modify translator varchar(50);
alter table tb_bookInfo modify publisher varchar(50);

INSERT INTO tb_bookInfo (ISBN, typeId, bookName, author, translator, publisher, date, price)
VALUES
    ('9781234567890', 1, 'Java', 'James', NULL, 'TechBooks', '2020-01-01', 30),
    ('9780987654321', 2, 'Python', 'Guido', NULL, 'CodePress', '2019-05-15', 25),
    ('9781122334455', 1, 'C++ Basics', 'Bjarne', NULL, 'CPlusPub', '2018-08-10', 28),
    ('9782233445566', 3, 'HTML5', 'John', NULL, 'WebBooks', '2021-03-22', 20),
    ('9783344556677', 2, 'AI Concepts', 'Alan', NULL, 'AIPress', '2022-02-18', 35);



/*==============================================================*/
/* Table: tb_bookType                                           */
/*==============================================================*/
create table tb_bookType
(
   id                   int not null auto_increment,
   typeName             varchar(20),
   days                 int,
   fk                   float,
   primary key (id)
);

/*==============================================================*/
/* Table: tb_borrow                                             */
/*==============================================================*/
create table tb_borrow
(
   id                   varchar(20) not null,
   bookISBN             varchar(13),
   operatorID           int,
   readerID             varchar(13),
   isBack               int,
   borrowDate           datetime,
   backDate             datetime,
   primary key (id)
);


alter table tb_borrow modify readerID varchar(13);



select bookName,bookISBN,typeid,Name,borrowDate,backdate,operatorID
from tb_borrow
join tb_bookinfo on tb_bookinfo.ISBN=tb_borrow.bookISBN
join tb_reader on tb_reader.id=tb_borrow.readerID
where tb_borrow.readerID=?;


/*==============================================================*/
/* Table: tb_operator                                           */
/*==============================================================*/
create table tb_operator
(
   id                   int not null auto_increment,
   name                 varchar(12),
   sex                  varchar(2),
   age                  int,
   identityCard         varchar(30),
   workdate             datetime,
   tel                  varchar(50),
   admin                bit,
   password             varchar(10),
   primary key (id)
);

/*==============================================================*/
/* Table: tb_order                                              */
/*==============================================================*/
create table tb_order
(
   ISBN                 varchar(13) not null,
   date                 datetime,
   number               int,
   operator             varchar(6),
   chackAndAccept       int,
   discount             float,
   primary key (ISBN)
);

/*==============================================================*/
/* Table: tb_reader                                             */
/*==============================================================*/
create table tb_reader
(
   id                   varchar(13) not null,
   name                 varchar(10),
   sex                  varchar(2),
   age                  int,
   identityCard         varchar(30),
   date                 datetime,
   maxNum               int,
   tel                  varchar(50),
   keepMoney            int,
   certificateType      int,
   career               varchar(50),
   doCerDate            datetime,
   primary key (id)
);

/*==============================================================*/
/* Table: tb_stockpile                                          */
/*==============================================================*/
create table tb_stockpile
(
   ISBN                 varchar(13) not null,
   amount               int,
   primary key (ISBN)
);

alter table tb_bookInfo add constraint FK_fk_bookInfo_type foreign key (typeId)
      references tb_bookType (id) on delete restrict on update restrict;

alter table tb_borrow add constraint FK_Reference_5 foreign key (operatorID)
      references tb_operator (id) on delete restrict on update restrict;

alter table tb_borrow add constraint FK_fk_borrow_bookInfo foreign key (bookISBN)
      references tb_bookInfo (ISBN) on delete restrict on update restrict;

alter table tb_borrow add constraint FK_fk_borrow_reader foreign key (readerID)
      references tb_reader (id) on delete restrict on update restrict;

alter table tb_order add constraint FK_fk_order_bookInfo foreign key (ISBN)
      references tb_bookInfo (ISBN) on delete restrict on update restrict;

alter table tb_stockpile add constraint FK_fk_stockpile_bookInfo foreign key (ISBN)
      references tb_bookInfo (ISBN) on delete restrict on update restrict;


INSERT INTO tb_bookType (typeName, days, fk)
VALUES ('Science Fiction', 30, 0.1),
       ('Fantasy', 45, 0.15),
       ('Historical', 20, 0.05);






