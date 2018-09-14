CREATE TABLE artist (
    id   BIGINT      NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW() ON UPDATE NOW(),
    PRIMARY KEY (id)
);

CREATE TABLE album (
    id   BIGINT      NOT NULL AUTO_INCREMENT,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW() ON UPDATE NOW(),
    name VARCHAR(100) NOT NULL,
    year_released BIGINT NOT NULL,
    artist_id BIGINT NOT NULL,
    
    PRIMARY KEY (id),
    FOREIGN KEY (artist_id) REFERENCES artist (id)
);

CREATE TABLE song (
    id   BIGINT      NOT NULL AUTO_INCREMENT,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW() ON UPDATE NOW(),
    name VARCHAR(100) NOT NULL,
    track BIGINT NOT NULL,
    album_id BIGINT NOT NULL,
    
    PRIMARY KEY (id),
    FOREIGN KEY (album_id) REFERENCES album (id)
);
