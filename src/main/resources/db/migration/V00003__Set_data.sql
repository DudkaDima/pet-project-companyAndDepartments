insert into employee(id, email, first_name, last_name, password, phone) values(3, 'dima@gmail.com', 'dima', 'dudka', '2323132', '120041');
insert into role(id, role_name, salary) values(3, 'CEO', '3000');
insert into company(id, company_name) values(1, 'Hi');
insert into department(id, dep_name, company_id) values(1, 'depAu', 1);
insert into emp_role_dep(id, emp_id, department_id, role_id) values(1, 1, 1, 1)