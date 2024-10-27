CREATE TABLE IF NOT EXISTS address(
	id SERIAL PRIMARY KEY,
	address_name varchar(50),
	address_city varchar(50),
	address_number int,
	createdAt Timestamp default CURRENT_TIMESTAMP,
	createdBy varchar(100),
	lastUpdatedBy varchar(50),
	lastUpdateDate Timestamp
);

CREATE TABLE IF NOT EXISTS authors(
    id SERIAL PRIMARY KEY,
	first_name varchar(150),
	last_name varchar(100),
	bornDate Timestamp,
	picture varchar(255),
	createdAt Timestamp default CURRENT_TIMESTAMP,
	createdBy varchar(100),
	lastUpdatedBy varchar(50),
	lastUpdateDate Timestamp
);

CREATE TABLE IF NOT EXISTS category(
	id SERIAL PRIMARY KEY,
	category_name varchar(75),
	category_description varchar(150),
	createdAt Timestamp default CURRENT_TIMESTAMP,
	createdBy varchar(100),
	lastUpdatedBy varchar(50),
	lastUpdateDate Timestamp
);

CREATE TABLE IF NOT EXISTS library_Book(
	id SERIAL PRIMARY KEY,
	library_name varchar(100),
	createdAt Timestamp default CURRENT_TIMESTAMP,
	createdBy varchar(100),
	lastUpdatedBy varchar(50),
	lastUpdateDate Timestamp,
	addressId int,
	CONSTRAINT FK_AddressId FOREIGN KEY (addressId) REFERENCES address(id)
);

CREATE TABLE IF NOT EXISTS Books (
	id SERIAL PRIMARY KEY,
	book_name varchar(100),
	isbn varchar(100),
	outDate Timestamp,
	authorId int,
	is_available boolean,
	price float,
	createdAt Timestamp default CURRENT_TIMESTAMP,
	createdBy varchar(100),
	lastUpdatedBy varchar(50),
	lastUpdateDate Timestamp,
	libraryId int,
	categoryId int,
	CONSTRAINT FK_AuthorId foreign key (authorId) REFERENCES authors(id),
	CONSTRAINT FK_LibraryId foreign key (libraryId) REFERENCES library_Book(id),
	CONSTRAINT FK_CtegoryId foreign key (categoryId) REFERENCES category(id)
);
	
CREATE TABLE IF NOT EXISTS onlineReader(
	id SERIAL PRIMARY KEY, 
    reader_name varchar(100),
	reader_email varchar(100),
	reader_phone varchar(100),
	createdAt Timestamp default CURRENT_TIMESTAMP,
	createdBy varchar(100),
	lastUpdatedBy varchar(50),
	lastUpdateDate Timestamp
);

CREATE TABLE IF NOT EXISTS libray_subscriber(
    id SERIAL PRIMARY KEY,
	library_id int,
	onlineReader_id int,
	observation varchar(255),
	suscribeAt timestamp default CURRENT_TIMESTAMP,
	unSubscribeAt timestamp default CURRENT_TIMESTAMP,
	CONSTRAINT FK_LibraryId FOREIGN KEY (library_id) REFERENCES library_Book(id),
	CONSTRAINT FK_onlineReaderId FOREIGN KEY (onlineReader_id) REFERENCES onlineReader(id) 
);

CREATE TABLE IF NOT EXISTS book_onlineReader(
	book_id int REFERENCES Books(id) NOT NULL,
	onlineReader_id int REFERENCES onlineReader(id) NOT NULL,
	PRIMARY KEY (book_id, onlineReader_id)
);

CREATE OR REPLACE FUNCTION update_Last_UpdateDate()
RETURNS TRIGGER AS $$
	BEGIN
	    NEW.lastUpdateDate := CURRENT_TIMESTAMP;
	    RETURN NEW;
	END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_update_lastUpdateDate_Address
BEFORE UPDATE ON address
FOR EACH ROW 
EXECUTE FUNCTION update_Last_UpdateDate();

CREATE TRIGGER trigger_update_lastUpdateDate_category
BEFORE UPDATE ON category
FOR EACH ROW 
EXECUTE FUNCTION update_Last_UpdateDate();

CREATE TRIGGER trigger_update_lastUpdateDate_library_Book
BEFORE UPDATE ON library_Book
FOR EACH ROW 
EXECUTE FUNCTION update_Last_UpdateDate();

CREATE TRIGGER trigger_update_lastUpdateDate_Books
BEFORE UPDATE ON Books
FOR EACH ROW 
EXECUTE FUNCTION update_Last_UpdateDate();

CREATE TRIGGER trigger_update_lastUpdateDate_onlineReader
BEFORE UPDATE ON onlineReader
FOR EACH ROW 
EXECUTE FUNCTION update_Last_UpdateDate();

	
CREATE TRIGGER trigger_update_lastUpdateDate_authors
BEFORE UPDATE ON authors
FOR EACH ROW 
EXECUTE FUNCTION update_Last_UpdateDate();