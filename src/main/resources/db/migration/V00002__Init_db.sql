  create table company(
  id  bigserial not null,
  company_name varchar(255) not null,
  primary key (id)
  );

  create table employee (
  id  bigserial not null,
  email varchar(255) not null,
  first_name varchar(255) not null,
  last_name varchar(255) not null,
  password varchar(255) not null,
  phone varchar(255) not null,
  primary key (id)
  );

  create table department (
  id  bigserial not null,
  dep_name varchar(255) not null,
  company_id int8,
  primary key (id)
  );

  alter table if exists department add constraint FkCompany_Dep foreign key (company_id) references company;

  create table emp_role_dep (
  id int8 not null,
  department_id int8,
  emp_id int8,
  role_id int8,
  primary key (id)
  );

  alter table if exists emp_role_dep add constraint Fk_dep_all foreign key (department_id) references department;
  alter table if exists emp_role_dep add constraint Fk_emp_all foreign key (emp_id) references employee;
  alter table if exists emp_role_dep add constraint Fk_role_all foreign key (role_id) references role;

  create table role (
  id  bigserial not null,
  role_name varchar(255) not null,
  salary varchar(255) not null,
  primary key (id)
  );


