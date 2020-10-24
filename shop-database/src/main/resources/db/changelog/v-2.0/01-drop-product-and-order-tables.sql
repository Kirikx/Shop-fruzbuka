alter table orders
    drop foreign key FKh3c37jq3nrv0f2edcxk0ine72;
GO

alter table order_items
    drop foreign key FKocimc7dtr037rh4ls4l95nlfi;
GO

alter table order_items
    drop foreign key FKgfyt8yv1cmqf3whaegva0n7ns;
GO

alter table products
    drop foreign key FKjg8ob3r0ws22krbj2xu30nhi1;
GO

alter table products_categories
    drop foreign key FKhmr3xn8ig3jpbavvw41lp8d56;
GO

alter table products_categories
    drop foreign key FKgfyt8yv1cmqf3whaegva0n7np;
GO

drop table orders;
GO

drop table products;
GO

drop table order_items;
GO

drop table categories;
GO

drop table brands;
GO

drop table products_categories;
GO