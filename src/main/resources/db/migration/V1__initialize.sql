drop table if exists students cascade;
create table students (id bigserial, name varchar(255), age int, primary key(id));
insert into students
(name, age) values
('Boris Ivanov', 32),
('lya Petrov', 30),
('Sergey Frolov', 32),
('Alexey Panfilov', 32),
('Ivan Selin', 32),
('Petr Shirokov', 32);