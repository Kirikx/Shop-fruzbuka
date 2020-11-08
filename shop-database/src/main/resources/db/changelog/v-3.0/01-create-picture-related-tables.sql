create table `pictures_data`(
    `id` bigint(20) not null AUTO_INCREMENT,
    `data` longblob,
    `file_name` varchar(255),
    primary key (`id`)
) ENGINE=InnoDB;
GO

create table `pictures`(
    `id` bigint(20) not null AUTO_INCREMENT,
    `content_type` varchar(255) not null,
    `name` varchar(255) not null,
    `picture_data_id` bigint(20) not null,
    primary key (`id`),
    unique key `UK_ehsu2tyinipypjox1ijxt3g3c` (`picture_data_id`),
    constraint `FKe9cv52k04xoy6cj8xy308gnw3` foreign key (`picture_data_id`) references `pictures_data` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 default CHARSET=latin1;
GO

create table `products_pictures`(
    `product_id` bigint(20) not null,
    `picture_id` bigint(20) not null,
    key `FKh3amnci4cl7xcl1al140xw79e` (`product_id`),
    key `FKloucf8ggy74nmdej2jmvttvi4` (`picture_id`),
    constraint `FKh3amnci4cl7xcl1al140xw79e` foreign key (`product_id`) references `products` (`id`),
    constraint `FKloucf8ggy74nmdej2jmvttvi4` foreign key (`picture_id`) references `pictures` (`id`)
) ENGINE=InnoDB;
GO
