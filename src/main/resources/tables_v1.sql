create table cameras
(
    id         BIGINT primary key,
    nasa_id    int,
    name       varchar(240)
);

create table pictures
(
    id         BIGINT primary key,
    nasa_id    int,
    img_src    varchar(240),
    camera_id  int,
    created_at varchar(240),
    foreign key (camera_id) references cameras (id)
);