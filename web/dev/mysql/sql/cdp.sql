-- 记录 repo
CREATE TABLE cdp_repo(
id INT PRIMARY KEY     NOT NULL,
name varchar(1024) NOT NULL,
namespace varchar(32) DEFAULT 'default' NOT NULL,
url text,
create_time timestamp,
update_time timestamp
);

-- 记录下所有的commit
CREATE TABLE cdp_repo_commits(
id varchar(64) PRIMARY KEY     NOT NULL,
repo_id INT,
namespace varchar(32) DEFAULT 'default' NOT NULL,
json_src json,
author_id varchar(64),

added_lines int,
deleted_lines int,
changed_lines int,

created_at timestamp,
message text,
shortId varchar(16),
title text,
create_time timestamp,
update_time timestamp
);


CREATE TABLE cdp_repo_diffs(
id INT PRIMARY KEY     NOT NULL,
repo_id INT,
namespace varchar(32) DEFAULT 'default' NOT NULL,
commit_id varchar(64) NOT NULL,

diff text,
deleted_file boolean,
new_file boolean,
new_path text,
old_path text,
renamed_file boolean,
a_mode varchar(16),
b_mode varchar(16),
create_time timestamp,
update_time timestamp
);


-- 记录行总数
CREATE TABLE cdp_repo_lines(
id INT PRIMARY KEY     NOT NULL,
repo_id INT NOT NULL,
namespace varchar(32) DEFAULT 'default' NOT NULL,

total_lines INT,
create_time timestamp,
update_time timestamp
);

-- 记录行变化
CREATE TABLE cdp_repo_line_history(
id INT PRIMARY KEY     NOT NULL,
repo_id INT NOT NULL,
namespace varchar(32) DEFAULT 'default' NOT NULL,

year INT,
month int,
date int,
hour_of_day int,
day_of_week int,
day_of_the_week int,
added_lines int,
deleted_lines int,
changed_lines int,

create_time timestamp,
update_time timestamp

);

-- 记录文件变化
CREATE TABLE cdp_repo_file_history(
id INT PRIMARY KEY     NOT NULL,
repo_id INT NOT NULL,
namespace varchar(32) DEFAULT 'default' NOT NULL,
file_path text,
extension varchar(32),

added_lines int,
deleted_lines int,
changed_lines int,

total_lines int,

author_id int,

year INT,
month int,
date int,
hour_of_day int,
day_of_week int,
day_of_the_week int,

create_time timestamp,
update_time timestamp
);


