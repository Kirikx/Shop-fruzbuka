    alter table users
        add column patronymic varchar(32);
GO

    alter table users
        add column surname varchar(32);
GO

    alter table users
        modify column password varchar(64);
GO