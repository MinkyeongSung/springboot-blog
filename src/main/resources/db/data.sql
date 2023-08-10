insert into user_tb(username, password, email) values('ssar', '$2a$10$iWZFqk/6aukkmEwcG2gFUuGLU1NtJkrp1Vpc4YsaPZnT9AH1IRVX.', 'ssar@nate.com');
insert into user_tb(username, password, email) values('cos', '$2a$10$iWZFqk/6aukkmEwcG2gFUuGLU1NtJkrp1Vpc4YsaPZnT9AH1IRVX.', 'cos@nate.com');
insert into board_tb(title, content, user_id, created_at) values('제목1', '내용1', 1, now());
insert into board_tb(title, content, user_id, created_at) values('제목2', '내용2', 1, now());
insert into board_tb(title, content, user_id, created_at) values('제목3', '내용3', 1, now());
insert into board_tb(title, content, user_id, created_at) values('제목4', '내용4', 2, now());
insert into board_tb(title, content, user_id, created_at) values('제목5', '내용5', 2, now());
insert into reply_tb(comment, board_id, user_id) values('댓글1', 1, 1);
insert into reply_tb(comment, board_id, user_id) values('댓글2', 1, 1);