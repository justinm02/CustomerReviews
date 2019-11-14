create table PRODUCTS (
    PRODUCT_ID int not null AUTO_INCREMENT,
    PRIMARY KEY (PRODUCT_ID),
    NAME varchar(100) not null
) ENGINE=INNODB;

create table REVIEWS (
    REVIEW_ID int not null AUTO_INCREMENT,
    PRODUCT_ID int not null,
    RATE int not null,
    PRIMARY KEY (REVIEW_ID),
    FOREIGN KEY (PRODUCT_ID)
        REFERENCES PRODUCTS(PRODUCT_ID)
        ON DELETE CASCADE
) ENGINE=INNODB;

create table COMMENTS (
    COMMENT_ID int not null AUTO_INCREMENT,
    REVIEW_ID int not null,
    CONTENT varchar(100) not null,
    PRIMARY KEY (COMMENT_ID),
    FOREIGN KEY (REVIEW_ID)
        REFERENCES REVIEWS(REVIEW_ID)
        ON DELETE CASCADE
) ENGINE=INNODB;

create INDEX products_id_idx on REVIEWS (PRODUCT_ID);
create INDEX reviews_id_idx on COMMENTS (REVIEW_ID);
