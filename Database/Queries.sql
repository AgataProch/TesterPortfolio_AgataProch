-- Homework SQL
--Task 1: Počet osob na odděleních (k datu)
select
o.nazev as "Oddeleni",
count(z.osoba_id) as "Pocet_osob"
from training.oddeleni o 
join training.zamestnani z 
    on o.id_oddeleni = z.oddeleni_id
where z.nastup <='10-15-2025' and z.konec is null
group by "Oddeleni"
order by "Pocet_osob" desc;

--Task 2: Oddělení podle odpracovaných hodin
select
o.nazev as "Oddeleni",
sum(d.hodiny) as "Hodiny"
from training.oddeleni o 
join training.zamestnani z
on o.id_oddeleni = z.oddeleni_id
join training.dochazka d 
on z.osoba_id = d.osoba_id
where d.datum between '2025-10-01' and '2025-10-31'
group by "Oddeleni"
order by "Hodiny" desc
limit 3;