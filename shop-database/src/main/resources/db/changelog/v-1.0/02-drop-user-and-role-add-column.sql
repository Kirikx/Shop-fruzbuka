alter table users
    drop column is_enable;
GO

alter table users
    drop column patronymic;
GO

alter table users
    drop column surname;
GO

alter table users
    modify column password varchar(128);
GO