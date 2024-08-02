drop table if exists department CASCADE;
drop table if exists employee_account CASCADE;
drop table if exists employee CASCADE;
drop table if exists product_category CASCADE;
drop table if exists product CASCADE;
drop table if exists product_stock CASCADE;
drop table if exists order_status CASCADE;
drop table if exists orders CASCADE;
drop table if exists order_detail CASCADE;
drop table if exists customer CASCADE;
drop table if exists payment_method CASCADE;

drop sequence if exists employee_id_seq CASCADE;
drop sequence if exists product_category_id_seq CASCADE;


create table department(
  id serial primary key,
  name varchar(100) not null
);

-- 支払い方法テーブル

create table payment_method(
  id serial primary key,
  name varchar(100) not null
);

-- 社員テーブル

create table employee(
  id serial primary key,
  name varchar(100) not null,
  name_kana varchar(100) not null,
  department_id integer references department(id)
);

-- 社員アカウントテーブル

create table employee_account(
  id serial primary key,
  name varchar(20) not null,
  password varchar(200) not null,
  employee_id integer references employee(id)
);

-- 商品カテゴリテーブル

create table product_category(
  id serial primary key,
  name varchar(100) not null
);

-- 商品テーブル

create table product(
  id serial primary key,
  name varchar(20) not null,
  price integer not null,
  image_url varchar(200) not null,
  product_category_id integer not null references product_category(id),
  delete_flag integer not null default 0
);

-- 在庫テーブル

create table product_stock(
  id serial primary key,
  quantity integer not null,
  product_id integer not null references product(id)
);

-- 注文ステータステーブル

create table order_status(
  id serial primary key,
  name varchar(100) not null
);

-- アカウント（顧客）テーブル
;
create table customer(
  id serial primary key,
  name varchar(20) not null,
  name_kana varchar(20) not null,
  address1 varchar(100) not null,
  address2 varchar(100),
  phone_number varchar(20) not null,
  mail_address varchar(200) not null,
  username varchar(30) not null,
  password varchar(200) not null,
  register_date timestamp with time zone not null
);

-- 注文テーブル

create table orders(
  id serial primary key,
  order_date timestamp with time zone not null,
  amount_total integer not null,
  customer_id integer not null references customer(id),
  order_status_id integer not null references order_status(id),
  payment_method_id integer not null references payment_method(id)
);

-- 注文明細テーブル

create table order_detail(
  id serial primary key,
  order_id integer not null references orders(id),
  product_id integer not null references product(id),
  count integer not null,
  customer_id integer not null references customer(id)
);


insert into department(name) values('開発部');
insert into department(name) values('商品企画部');
insert into department(name) values('営業部');


insert into employee(name,name_kana,department_id) values('高橋太郎','タカハシ',1);
insert into employee(name,name_kana,department_id) values('田中花子','タナカ',1);
insert into employee(name,name_kana,department_id) values('佐藤次郎','サトウ',1);

-- employee account data
-- insert into employee_account(name,password,employee_id) values('admin','admin',1);

CREATE EXTENSION IF NOT EXISTS pgcrypto;
INSERT INTO employee_account(name,password,employee_id) VALUES ('admin', crypt('admin', gen_salt('bf')),1);


insert into product_category(name) values('文房具');
insert into product_category(name) values('雑貨');
insert into product_category(name) values('パソコン周辺機器');


insert into order_status(name) values('注文済み');
insert into order_status(name) values('入金済み');
insert into order_status(name) values('配送中');
insert into order_status(name) values('完了');
 　　　

insert into payment_method(name) values('現金');


insert into product values(nextval('product_id_seq'),'水性ボールペン(黒)',120,'black_pen_w.jpg',1,0);
insert into product values(nextval('product_id_seq'),'水性ボールペン(赤)',120,'red_pen_w.jpg',1,0);
insert into product values(nextval('product_id_seq'),'水性ボールペン(青)',120,'blue_pen_w.jpg',1,0);
insert into product values(nextval('product_id_seq'),'油性ボールペン(黒)',100,'black_pen_o.jpg',1,0);
insert into product values(nextval('product_id_seq'),'油性ボールペン(赤)',100,'red_pen_o.jpg',1,0);
insert into product values(nextval('product_id_seq'),'油性ボールペン(青)',100,'blue_pen_w.jpg',1,0);
insert into product values(nextval('product_id_seq'),'蛍光ペン(黄)',130,'yellow_maker.jpg',1,0);
insert into product values(nextval('product_id_seq'),'蛍光ペン(赤)',130,'red_maker.jpg',1,0);
insert into product values(nextval('product_id_seq'),'蛍光ペン(青)',130,'blue_maker.jpg',1,0);
insert into product values(nextval('product_id_seq'),'蛍光ペン(緑)',130,'green_maker.jpg',1,0);
insert into product values(nextval('product_id_seq'),'鉛筆(黒)',100,'black_pen.jpg',1,0);
insert into product values(nextval('product_id_seq'),'鉛筆(赤)',100,'red_pen.jpg',1,0);
insert into product values(nextval('product_id_seq'),'色鉛筆(12色)',400,'color_pen12.jpg',1,0);
insert into product values(nextval('product_id_seq'),'色鉛筆(48色)',1300,'color_pen48.jpg',1,0);
insert into product values(nextval('product_id_seq'),'ワンタッチ開閉傘',3000,'umbrella.jpg',2,0);
insert into product values(nextval('product_id_seq'),'折畳トートバッグ',600,'bag.jpg',2,0);
insert into product values(nextval('product_id_seq'),'アイマスク',900,'mask.jpg',2,0);
insert into product values(nextval('product_id_seq'),'防水スプレー',500,'spray.jpg',2,0);
insert into product values(nextval('product_id_seq'),'キーホルダ',800,'keyholder.jpg',2,0);
insert into product values(nextval('product_id_seq'),'ワイヤレスマウス',900,'mouse_c.jpg',3,0);
insert into product values(nextval('product_id_seq'),'ワイヤレストラックボール',1300,'mouse_d.jpg',3,0);
insert into product values(nextval('product_id_seq'),'有線光学式マウス',500,'mouse_b.jpg',3,0);
insert into product values(nextval('product_id_seq'),'有線ゲーミングマウス',3800,'mouse_a.jpg',3,0);
insert into product values(nextval('product_id_seq'),'USB有線式キーボード',1400,'keybord.jpg',3,0);
insert into product values(nextval('product_id_seq'),'無線式キーボード',1900,'keybord_wireless.jpg',3,0);


insert into product_stock values(nextval('product_stock_id_seq'),10,1);
insert into product_stock values(nextval('product_stock_id_seq'),20,2);
insert into product_stock values(nextval('product_stock_id_seq'),30,3);
insert into product_stock values(nextval('product_stock_id_seq'),40,4);
insert into product_stock values(nextval('product_stock_id_seq'),50,5);
insert into product_stock values(nextval('product_stock_id_seq'),60,6);
insert into product_stock values(nextval('product_stock_id_seq'),70,7);
insert into product_stock values(nextval('product_stock_id_seq'),80,8);
insert into product_stock values(nextval('product_stock_id_seq'),90,9);
insert into product_stock values(nextval('product_stock_id_seq'),100,10);
insert into product_stock values(nextval('product_stock_id_seq'),110,11);
insert into product_stock values(nextval('product_stock_id_seq'),120,12);
insert into product_stock values(nextval('product_stock_id_seq'),130,13);
insert into product_stock values(nextval('product_stock_id_seq'),140,14);
insert into product_stock values(nextval('product_stock_id_seq'),150,15);
insert into product_stock values(nextval('product_stock_id_seq'),160,16);
insert into product_stock values(nextval('product_stock_id_seq'),170,17);
insert into product_stock values(nextval('product_stock_id_seq'),180,18);
insert into product_stock values(nextval('product_stock_id_seq'),190,19);
insert into product_stock values(nextval('product_stock_id_seq'),200,20);
insert into product_stock values(nextval('product_stock_id_seq'),210,21);
insert into product_stock values(nextval('product_stock_id_seq'),220,22);
insert into product_stock values(nextval('product_stock_id_seq'),230,23);
insert into product_stock values(nextval('product_stock_id_seq'),240,24);
insert into product_stock values(nextval('product_stock_id_seq'),250,25);