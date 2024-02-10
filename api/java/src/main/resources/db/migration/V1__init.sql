CREATE TABLE profile
(
    id                 VARCHAR(255) NOT NULL,
    created_date       datetime     NOT NULL,
    created_by         VARCHAR(255) NOT NULL,
    last_modified_date datetime     NOT NULL,
    last_modified_by   VARCHAR(255) NOT NULL,
    name               VARCHAR(255) NOT NULL,
    description        VARCHAR(255) NULL,
    type               VARCHAR(255) NOT NULL DEFAULT 'USER',
    CONSTRAINT pk_profile PRIMARY KEY (id),
    CONSTRAINT unique_profile UNIQUE (name)
) engine = InnoDB
  default charset = utf8;

CREATE TABLE user
(
    id                 VARCHAR(255) NOT NULL,
    created_date       datetime     NOT NULL,
    created_by         VARCHAR(255) NOT NULL,
    last_modified_date datetime     NOT NULL,
    last_modified_by   VARCHAR(255) NOT NULL,
    identification     VARCHAR(255) NOT NULL,
    secret             VARCHAR(255) NOT NULL,
    name               VARCHAR(255),
    profile_id         VARCHAR(255),
    contact            VARCHAR(255),
    born               datetime,
    CONSTRAINT pk_user PRIMARY KEY (id),
    CONSTRAINT unique_user UNIQUE (identification),
    CONSTRAINT fk_profile_id FOREIGN KEY (profile_id) REFERENCES profile (id)
) engine = InnoDB
  default charset = utf8;

CREATE TABLE user_master
(
    id      VARCHAR(255) NOT NULL,
    user_id VARCHAR(255) NOT NULL,
    CONSTRAINT pk_user_master PRIMARY KEY (id),
    CONSTRAINT unique_user_id UNIQUE (user_id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES user (id)
) engine = InnoDB
  default charset = utf8;