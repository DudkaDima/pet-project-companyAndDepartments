insert into employee(id, email, first_name, last_name, password, phone) values(1, 'dima@gmail.com', 'dima', 'dudka', '2323132', '120041');
insert into employee(id, email, first_name, last_name, password, phone) values(2, 'josh@gmail.com', 'josh', 'marston', '1231312', '441321');
insert into role(id, role_name, salary) values(1, 'CEO', '3000');
insert into role(id, role_name, salary) values(2, 'worker', '1500');
insert into company(id, company_name) values(1, 'Hi');
insert into company(id, company_name) values(2, 'Jia');
insert into department(id, dep_name, company_id) values(1, 'depAu', 1);
insert into department(id, dep_name, company_id) values(2, 'depGA', 1);
insert into department(id, dep_name, company_id) values(3, 'depASSA', 2);
insert into department(id, dep_name, company_id) values(4, 'depXZCXZ', 2);
insert into emp_role_dep(id, department_id, emp_id, role_id) values(1, 1, 1, 1);
insert into emp_role_dep(id, department_id, emp_id, role_id) values(2, 2, 1, 2);
insert into emp_role_dep(id, department_id, emp_id, role_id) values(3, 3, 2, 1);
insert into emp_role_dep(id, department_id, emp_id, role_id) values(4, 4, 2, 2)