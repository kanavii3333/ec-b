-- department data
insert into department(name) values('開発部');
insert into department(name) values('商品企画部');
insert into department(name) values('営業部');

-- employee data
insert into employee(name,name_kana,department_id) values('高橋太郎','タカハシ',1);
insert into employee(name,name_kana,department_id) values('田中花子','タナカ',1);
insert into employee(name,name_kana,department_id) values('佐藤次郎','サトウ',1);

-- employee account data
-- insert into employee_account(name,password,employee_id) values('admin','admin',1);

-- product_category data
insert into product_category(name) values('文房具');
insert into product_category(name) values('雑貨');
insert into product_category(name) values('パソコン周辺機器');

-- order_status data
insert into order_status(name) values('注文済み');
insert into order_status(name) values('入金済み');
insert into order_status(name) values('配送中');
insert into order_status(name) values('完了');

-- payment_method data
insert into payment_method(name) values('現金');

-- product data
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

-- product_stock
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