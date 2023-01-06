drop table if exists comment CASCADE ;
drop table if exists survey CASCADE ;
drop table if exists poster CASCADE ;

create table comment (
                         comment_id bigint not null auto_increment,
                         created_at datetime(6),
                         modified_at datetime(6),
                         comment varchar(255) not null,
                         poster_id bigint,
                         primary key (comment_id)
);

create table poster (
                        poster_id bigint not null auto_increment,
                        created_at datetime(6),
                        modified_at datetime(6),
                        img_url varchar(255) not null,
                        passage varchar(255) not null,
                        poster_title varchar(255) not null,
                        poster_view_count integer default 0,
                        primary key (poster_id)
);

create table survey (
                        survey_id bigint not null auto_increment,
                        created_at datetime(6),
                        modified_at datetime(6),
                        choice varchar(255) not null,
                        choice_result varchar(255) not null,
                        choice_view_count integer default 0,
                        poster_id bigint,
                        primary key (survey_id)
);

alter table comment
    add constraint FK8ev0x3rhics23o6103ai7d71h
        foreign key (poster_id)
            references poster (poster_id);

alter table survey
    add constraint FKhmjqub75h1ucg2rpbgx4au56
        foreign key (poster_id)
            references poster (poster_id);