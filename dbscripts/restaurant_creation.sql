create table items
(
  id          int(10) auto_increment
    primary key,
  name        varchar(50)    not null,
  description varchar(100)   null,
  cost        decimal(10, 3) not null
);


create table items_orders
(
  id        int(10) auto_increment
    primary key,
  orders_id int(10)          not null,
  items_id  int(10)          not null,
  quantity  int(2) default 1 not null,
  constraint items_orders_items_id_fk
    foreign key (items_id) references items (id),
  constraint items_orders_orders_id_fk
    foreign key (orders_id) references orders (id)
);


create table officiants
(
  id          int(10) auto_increment
    primary key,
  first_name  varchar(50) not null,
  second_name varchar(50) not null
);


create table orders
(
  id           int(10) auto_increment
    primary key,
  date         date    not null,
  officiant_id int(10) not null,
  constraint orders_officiants_id_fk
    foreign key (officiant_id) references officiants (id)
);

