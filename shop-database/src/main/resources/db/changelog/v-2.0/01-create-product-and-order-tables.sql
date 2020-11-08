create table orders
(
    id      bigint not null auto_increment,
    user_id bigint not null,
    date    datetime,
    primary key (id)
) engine = InnoDB;
GO

create table products
(
    id          bigint         not null auto_increment,
    prise       decimal(19, 2) not null,
    name        varchar(255)    not null,
    description varchar(2048),
    brand_id    bigint,
    primary key (id)
) engine = InnoDB;
GO

create table order_items
(
    id             bigint not null auto_increment,
    cost           decimal(19, 2),
    count          integer,
    order_id       bigint not null,
    product_id     bigint not null,
    datetime_order datetime(6),
    primary key (id)
) engine = InnoDB;
GO

create table categories
(
    id          bigint      not null auto_increment,
    name        varchar(32) not null unique,
    description varchar(255),
    primary key (id)
) engine = InnoDB;
GO

create table brands
(
    id          bigint      not null auto_increment,
    name        varchar(32) not null unique,
    description varchar(255),
    primary key (id)
) engine = InnoDB;
GO

create table products_categories
(
    product_id  bigint not null,
    category_id bigint not null,
    primary key (product_id, category_id)
) engine = InnoDB;
GO

alter table orders
    add constraint FKh3c37jq3nrv0f2edcxk0ine72
        foreign key (user_id)
            references users (id);
GO

alter table order_items
    add constraint FKocimc7dtr037rh4ls4l95nlfi
        foreign key (order_id)
            references orders (id);
GO

alter table order_items
    add constraint FKgfyt8yv1cmqf3whaegva0n7ns
        foreign key (product_id)
            references orders (id);
GO

alter table products
    add constraint FKjg8ob3r0ws22krbj2xu30nhi1
        foreign key (brand_id)
            references brands (id);
GO

alter table products_categories
    add constraint FKhmr3xn8ig3jpbavvw41lp8d56
        foreign key (product_id)
            references products (id);
GO

alter table products_categories
    add constraint FKgfyt8yv1cmqf3whaegva0n7np
        foreign key (category_id)
            references categories (id);
GO