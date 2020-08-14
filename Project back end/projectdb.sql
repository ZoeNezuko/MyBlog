DROP DATABASE IF EXISTS prjectdb;

CREATE DATABASE projectdb;

USE projectdb;

/*
Table structure for user
*/
DROP TABLE IF EXISTS user;
CREATE TABLE user
(
	id INT(20) NOT NULL AUTO_INCREMENT COMMENT 'PK is ID',
	username VARCHAR(255) DEFAULT NULL COMMENT 'Name',
    password VARCHAR(255) DEFAULT NULL COMMENT 'Password',
    email VARCHAR(50) DEFAULT NULL COMMENT 'Email',
    enable TINYINT(1) NOT NULL DEFAULT '1' COMMENT 'User status', 
	PRIMARY KEY (id)
);

/*
Insert user
*/
INSERT INTO user (id, username, password, email, enable) VALUES
(1, 'Admin', '111','123@gamil.com', 1),
(2, 'Test', '456','123@gamil.com', 1),
(3, 'Jie', '000','123@gamil.com', 1);

/*
Table structure for blog_article
*/
DROP TABLE IF EXISTS blog_article;
CREATE TABLE blog_article
(
	id INT(20) NOT NULL AUTO_INCREMENT COMMENT 'PK is ID',
	title VARCHAR(255) NOT NULL DEFAULT '' COMMENT 'Article Title',
    author VARCHAR(255) DEFAULT '' COMMENT 'Article author',
    summary VARCHAR(255) DEFAULT NULL COMMENT 'Article abstract',
    date DATETIME DEFAULT NULL COMMENT 'Article publish date',
    html_content LONGTEXT COMMENT 'HTML file source',
    md_content LONGTEXT COMMENT 'Markdown File source',
	uid INT(20) DEFAULT NULL COMMENT 'User id',
	PRIMARY KEY (id),
	KEY uid (uid),
	CONSTRAINT article_ibfk_1 FOREIGN KEY (uid) REFERENCES user (id)
);

/*
Table structure for comments
*/
DROP TABLE IF EXISTS comments;
CREATE TABLE comments
(
	id INT(20) NOT NULL AUTO_INCREMENT COMMENT 'PK is ID',
	content TEXT COMMENT 'Content',
	aid INT(20) DEFAULT NULL COMMENT 'Article id',
	uid INT(20) DEFAULT NULL COMMENT 'User id',
	PRIMARY KEY (id),
    KEY aid (aid),
	KEY uid (uid),
	CONSTRAINT comments_ibfk_1 FOREIGN KEY (aid) REFERENCES blog_article (id),
	CONSTRAINT comments_ibfk_2 FOREIGN KEY (uid) REFERENCES user (id)
);

/*
Table structure for category
*/
DROP TABLE IF EXISTS category;
CREATE TABLE category
(
	id INT(20) NOT NULL COMMENT 'PK is ID',
	name VARCHAR(255) DEFAULT NULL COMMENT 'Category name',
	PRIMARY KEY (id)
);

/*
Insert category
*/
INSERT INTO category (id, name) VALUES
(1, 'Python'),
(2, 'Java'),
(3, 'C'),
(4, 'C++'),
(5, 'JavaScript'),
(6, 'MySQL'),
(7, 'PHP'),
(8, 'Angular'),
(9, 'React'),
(10, 'Vue'),
(11,'Others');

/*
Table structure for document
*/
DROP TABLE IF EXISTS document;
CREATE TABLE document
(
	id INT(20) NOT NULL AUTO_INCREMENT COMMENT 'PK is ID',
	author VARCHAR(255) DEFAULT '' COMMENT 'Upload Author',
	title VARCHAR(255) NOT NULL DEFAULT '' COMMENT 'Title',
    description VARCHAR(255) NOT NULL DEFAULT '' COMMENT 'Description',
    date DATETIME DEFAULT NULL COMMENT 'Document publish date',
    format VARCHAR(255) NOT NULL DEFAULT '' COMMENT 'Format',
    file VARCHAR(255) NOT NULL DEFAULT '' COMMENT 'Save pdf or docx file',
    cid INT(20) DEFAULT NULL COMMENT 'FK',
	uid INT(20) DEFAULT NULL COMMENT 'User id',
	PRIMARY KEY (id),
	KEY uid (uid),
    KEY fk_document_category_on_cid (cid),
	CONSTRAINT document_ibfk_1 FOREIGN KEY (uid) REFERENCES user (id),
    CONSTRAINT fk_document_category_on_cid FOREIGN KEY (cid) REFERENCES category (id) ON DELETE SET NULL ON UPDATE CASCADE
);

--  Role-Based Access Control----------------------------------------------------------------------------------------------------------

/*
Table structure for nav
*/
DROP TABLE IF EXISTS nav;
CREATE TABLE nav
(
	id INT(20) NOT NULL AUTO_INCREMENT COMMENT 'PK is ID',
	path VARCHAR(255) DEFAULT NULL COMMENT 'Corresponding to the path in Vue routing',
    name VARCHAR(255) DEFAULT NULL COMMENT 'Render side nav bar',
    component VARCHAR(255) DEFAULT NULL COMMENT 'Component name corresponding to Vue',
    icon VARCHAR(255) DEFAULT NULL COMMENT 'Used to render small icons in front of the navigation bar',
    parent_id INT DEFAULT NULL COMMENT 'Parent node id, used to store the hierarchical relationship of the navigation bar',
	PRIMARY KEY (id)
);

/*
Table structure for role
*/
INSERT INTO nav (id, path, name, component, icon, parent_id) VALUES
(1, '/admin', 'Dashboard', 'AdminIndex', 'el-icon-odometer', '0'),
(2, '/admin', 'User management', 'AdminIndex', 'el-icon-s-custom', '0'),
(3, '/admin', 'Content management', 'AdminIndex', 'el-icon-tickets', '0'),
(4, '/admin', 'Upload management', 'AdminIndex', 'el-icon-folder', '0'),
(5, '/admin/user/info', 'User configuration', 'user/UserInfo', null, '2'),
(6, '/admin/role/info', 'Role configuration', 'user/Roles', null, '2'),
(7, '/admin/manage/blog', 'Blog information', 'manage/BlogManagement', null, '3'),
(8, '/admin/manage/document', 'Document information', 'manage/DocumentManagement', null, '4');

/*
Table structure for role
*/
DROP TABLE IF EXISTS role;
CREATE TABLE role
(
	id INT(20) NOT NULL AUTO_INCREMENT COMMENT 'PK is ID',
    name VARCHAR(255) DEFAULT NULL COMMENT 'Role name',
    description VARCHAR(255) DEFAULT NULL COMMENT 'Role information description',
	PRIMARY KEY (id)
);

/*
Insert role
*/
INSERT INTO role (id, name, description) VALUES
(1, 'Admin', 'System administrator'),
(2, 'Tester', 'System tester'),
(3, 'User', 'registered user');

/*
Table structure for nav_roles
*/
DROP TABLE IF EXISTS nav_roles;
CREATE TABLE nav_roles
(
	id INT(20) NOT NULL AUTO_INCREMENT COMMENT 'PK is ID',
    rid INT(20) DEFAULT NULL COMMENT 'Role id',
    nid INT(20) DEFAULT NULL COMMENT 'Nav id',
	PRIMARY KEY (id),
	KEY nid (nid),
	KEY rid (rid),
	CONSTRAINT nav_role_ibfk_1 FOREIGN KEY (nid) REFERENCES nav (id),
	CONSTRAINT nav_role_ibfk_2 FOREIGN KEY (rid) REFERENCES role (id)
);

/*
Insert nav_roles
*/
INSERT INTO nav_roles (id, rid, nid) VALUES
(1, 1, 1), -- Role id 1 is admin can access nav from 1-8
(2, 1, 2),
(3, 1, 3),
(4, 1, 4),
(6, 1, 6),
(7, 1, 7),
(8, 1, 8),
(9, 3, 3), -- Role id 3 is user or jie can access nav of 3 4 7 8 
(10 ,3, 4),
(11 ,3, 7),
(12, 3, 8);

-- INSERT INTO nav_roles (id, rid, nid) VALUES
-- (10, 2, 1), -- 2 is test
-- (11, 2, 3),
-- (12, 2, 4);

/*
Table structure for user_roles
*/
DROP TABLE IF EXISTS user_roles;
CREATE TABLE user_roles
(
	id INT(20) NOT NULL AUTO_INCREMENT COMMENT 'PK is ID',
    uid INT(20) DEFAULT NULL COMMENT 'User id',
    rid INT(20) DEFAULT NULL COMMENT 'Role id',
	PRIMARY KEY (id),
	KEY rid (rid),
    KEY user_role_ibfk_1(uid),
	CONSTRAINT user_role_ibfk_1 FOREIGN KEY (uid) REFERENCES user (id) ON DELETE CASCADE,
	CONSTRAINT user_role_ibfk_2 FOREIGN KEY (rid) REFERENCES role (id)
);

/*
Insert user_roles
*/
INSERT INTO user_roles (id, uid, rid) VALUES
(1, 1, 1), -- Admin representative role id is 1
(2, 2, 2), -- Tester representative role id is 2
(3, 3, 3); -- User representative role id is 3



