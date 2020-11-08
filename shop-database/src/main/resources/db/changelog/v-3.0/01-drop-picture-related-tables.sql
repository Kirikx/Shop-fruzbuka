alter table `products_pictures`
    drop foreign key `FKh3amnci4cl7xcl1al140xw79e`;
GO

alter table `pictures`
    drop foreign key `FKe9cv52k04xoy6cj8xy308gnw3`;
GO

alter table `pictures`
    drop key `UK_ehsu2tyinipypjox1ijxt3g3c`;
GO

alter table `products_pictures`
    drop foreign key `FKloucf8ggy74nmdej2jmvttvi4`;
GO

drop table `pictures`;
GO

drop table `products_pictures`;
GO

drop table `pictures_data`;
GO