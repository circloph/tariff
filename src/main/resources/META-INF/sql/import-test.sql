INSERT INTO tariffs (name, archived, deleted) values ('big tariff', false, false);
INSERT INTO tariffs (name, archived, deleted) values ('small tariff', false, false);

insert into packages(name, category, meaning, deleted, tariff_id) values ('internet', 'INTERNET', -1, false, 1);
insert into packages(name, category, meaning, deleted, tariff_id) values ('internet', 'CALLS', -1, false, 2);
