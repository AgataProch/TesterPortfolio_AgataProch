-- ========================== Homework SQL ==========================
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

-- ========================== Practice I ==========================
--Task 1
select
jmeno, prijmeni, email
from training.osoba
where email
like '%2%';

--Task 2
select
jmeno, prijmeni, vek
from training.osoba
where (pohlavi = 'F' and vek <35)
or (pohlavi = 'M' and vek>40);

--Task 3
select jmeno, prijmeni, nadrazeny_osoba_id
from training.osoba
where pohlavi = 'F'
and nadrazeny_osoba_id is null;

--Task 4
select *
from training.osoba
order by vek desc;

--Task 5
select *
from training.osoba
order by vek desc
limit 2;

--Task 6
select distinct jmeno
from training.osoba;

--Task 7
select
count(*) as "Pocet_osob",
round(avg(Vek),2) as "Prumerny_vek",
max(Vek) as "Nejstarsi",
min(Vek) as "Nejmladsi"
from training.osoba;

--Task 8
select
pohlavi,
count(*) as "Pocet osob",
round(avg(Vek), 2) as "Prumerny vek"
from training.osoba
group by pohlavi;

--Task 9
select
pohlavi,
count(*) as "Pocet osob"
from training.osoba
group by pohlavi
having count(*)>=50;

--Task 10
select
jmeno, prijmeni, vek,
case when vek<25 then 'Junior'
when vek between 25 and 40 then 'Medior'
else 'Senior'
end as "Kategorie"
from training.osoba;



--Vypiš jméno, příjmeni vedoucího oddělení a lokaci oddělení
select
training.osoba.jmeno,
training.osoba.prijmeni,
training.oddeleni.nazev as "Oddeleni",
training.oddeleni.lokace as "Lokace"
from training.oddeleni
join training.osoba
on training.osoba.id_osoba = training.oddeleni.vedouci_osoba_id;

--Vypiš průměrný počet hodin v docházce podle osoby a seřaď výsledek podle průměru vzestupně
select
training.osoba.jmeno,
training.osoba.prijmeni,
round(avg(training.dochazka.hodiny),2) as "Prumerny_pocet_hodin"
from training.dochazka
join training.osoba
on training.osoba.id_osoba = training.dochazka.osoba_id
group by training.osoba.jmeno, training.osoba.prijmeni
order by "Prumerny_pocet_hodin" asc;

--Zobraz názvy projektů a jejich úkolů včetně stavu úkoli
select
distinct training.projekt.id_projekt,
training.projekt.nazev, training.ukol.nazev as "nazev ukolu",
training.ukol.stav as "stav ukolu"
from training.ukol
join training.projekt
on training.projekt.id_projekt = training.ukol.projekt_id;

--Zobraz projekty a počet osob přiřazených na každý projekt
select
training.projekt.id_projekt,
training.projekt.nazev,
count(training.osoba_projekt.osoba_id) as "pocet osob"
from training.osoba_projekt
join training.projekt
on training.projekt.id_projekt = training.osoba_projekt.projekt_id
group by training.projekt.id_projekt; --group by je pouze u funkcí

--Zobraz projekty seřazené podle celkového počtu odpracovaných hodin
select
training.projekt.id_projekt,
training.projekt.nazev,
sum(training.dochazka.hodiny) as "odpracovane hodiny"
from training.dochazka
join training.projekt
on training.projekt.id_projekt = training.dochazka.projekt_id
group by training.projekt.id_projekt
order by "odpracovane hodiny" asc;

--Zobraz osoby seřazené podle celkového počtu odpracovaných hodin
select
training.osoba.jmeno,
training.osoba.prijmeni,
sum(training.dochazka.hodiny) as "odpracovane hodiny"
from training.dochazka
join training.osoba
on training.osoba.id_osoba = training.dochazka.osoba_id
group by training.osoba.jmeno, training.osoba.prijmeni
order by "odpracovane hodiny" asc;

--Zobraz rozpočet a celkový počet odpracovaných hodin pro každý projekt
select
training.projekt.id_projekt,
training.projekt.nazev,
training.projekt.rozpocet,
sum(training.dochazka.hodiny) as "odpracovane hodiny"
from training.dochazka
join training.projekt
on training.projekt.id_projekt = training.dochazka.projekt_id
group by training.projekt.id_projekt
order by "odpracovane hodiny" asc;

--Zobraz osoby a datum jejich posledního záznamu docházky včetně těch, které žádnou docházku nemají
select
training.osoba.jmeno,
training.osoba.prijmeni,
max(training.dochazka.datum) as "posledni dochazka"
from training.dochazka
join training.osoba
on training.osoba.id_osoba = training.dochazka.osoba_id
group by training.osoba.jmeno, training.osoba.prijmeni
order by "posledni dochazka" desc;

--Zobraz projekty a počet unikátních osob, které na nich skutečně odpracovaly hodiny, včetně projektů bez docházky
select
training.projekt.id_projekt,
training.projekt.nazev,
count(distinct training.dochazka.osoba_id) as "pocet pracujicich"
from training.projekt
join training.dochazka
on training.projekt.id_projekt = training.dochazka.projekt_id
group by training.projekt.id_projekt, training.projekt.nazev
order by "pocet pracujicich" desc, training.projekt.nazev;

--Vypiš jen ty osoby, které pracují v některém z oddělení.
select
osoba.id_osoba,
osoba.jmeno,
osoba.prijmeni,
zamestnani.konec,
zamestnani.oddeleni_id
from osoba
inner join zamestnani
on zamestnani.osoba_id = osoba.id_osoba
where zamestnani.oddeleni_id is not null and zamestnani.konec is null; --aby to nevypsalo lidi, které už nejsou zaměstnanci oddělení

--Vypiš všechny osoby a ID oddělení ve kterém pracují.
select distinct
osoba.id_osoba,
osoba.jmeno,
osoba.prijmeni,
zamestnani.oddeleni_id
from osoba left join zamestnani --bereme vše z toho levého
on zamestnani.osoba_id = osoba.id_osoba
order by osoba.id_osoba; 

--Vypiš všechna oddělení a jejich vedoucí pomocí RIGHT JOIN.
select
oddeleni.id_oddeleni,
oddeleni.nazev as oddeleni,
osoba.jmeno,
osoba.prijmeni
from training.osoba right join training.oddeleni --bereme vše z toho pravého
on oddeleni.vedouci_osoba_id = osoba.id_osoba;

--Vypiš všechny osoby a všechny jejich úkoly (včetně osob bez úkolů a úkolů bez přiřazeného řešitele)
select
osoba.id_osoba,
osoba.jmeno,
osoba.prijmeni,
ukol.nazev
from osoba
full outer join ukol
on osoba.id_osoba = ukol.prirazenemu;

select --Davidovo řešení
ukol.id_ukol,
ukol.nazev as ukol,
osoba.id_osoba,
osoba.jmeno as osoba
from ukol
full outer join osoba
on osoba.id_osoba = ukol.prirazenemu
order by ukol.id_ukol, osoba.id_osoba;

--Vypiš názvy všech pozic, oddělení a projektů v jedné tabulce
select 'pozice' as typ, nazev from pozice
union all
select 'oddeleni' as typ, nazev from oddeleni
union all
select 'projekt' as typ, nazev from projekt;

--Vypiš odhadovanou pracnost úkolů v jednotlivých projektech ve dnech
select
projekt.id_projekt,
projekt.nazev as projekt,
round(sum(ukol.odhad_hodin)/8, 0) as menday
from projekt
join ukol
on projekt.id_projekt = ukol.projekt_id
group by projekt.id_projekt, projekt.nazev --
order by projekt.id_projekt;

--Vypiš odhadovanou pracnost úkolů v jednotlivých projektech ve dnech, počítej, že úkoly bez pracnosti mají pracnost 1 MD
select
projekt.id_projekt,
projekt.nazev as projekt,
round(sum(ukol.odhad_hodin)/8, 0) as menday1,
round(sum(coalesce(ukol.odhad_hodin, 8))/8, 0) as menday --proč ROUND(SUM(COALESCE(u.odhad_hodin, 8)) / 8.0, 1) AS odhad_dny_norm? - protože je tam 12 záznamů (pokud je NULL, dej tam 8 hodin a pak to vyděl 8), ten sum je z toho coalesce
from training.projekt
join training.ukol
on projekt.id_projekt = ukol.projekt_id
group by projekt.id_projekt, projekt.nazev
order by projekt.id_projekt;

select
projekt.nazev as projekt,
sum(coalesce(ukol.odhad_hodin, 8))/8 as zkouška
from projekt
join ukol
on projekt.id_projekt = ukol.projekt_id
group by projekt
order by projekt.nazev;

--Vypiš vykázanou práci na jednotlivé projekty ve dnech
select
projekt.id_projekt,
projekt.nazev as projekt,
round(sum(coalesce(dochazka.hodiny, 8))/8, 0) as odpracovane_hodiny --proč (COALESCE(SUM(d.hodiny), 0) / 8.0, 0) AS vykazano_dny?
from training.projekt
left join training.dochazka
on projekt.id_projekt = dochazka.projekt_id
group by projekt.id_projekt, projekt.nazev
order by projekt.id_projekt;

select --Davidův výpočet
training.projekt.id_projekt,
training.projekt.nazev as projekt,
ROUND(COALESCE(SUM(training.dochazka.hodiny), 0) / 8.0, 0) AS vykazano_dny --první 0 patří ke Coalesce (když je tam null, tak tam dej 0), pak to dělím 8 na mendaye, pak to zaokrouhlujeme na 0 desetinných míst
FROM training.projekt
LEFT JOIN training.dochazka
ON training.dochazka.projekt_id = training.projekt.id_projekt
GROUP BY training.projekt.id_projekt, training.projekt.nazev
ORDER BY training.projekt.id_projekt;

--Vypiš všechny osoby, které nejsou přiřazeny na projekt - projekty mají platnost od a do, musím zkontrolovat, jeslti mají projekt, který je platný
select
osoba.id_osoba,
osoba.jmeno,
osoba.prijmeni
from osoba
join osoba_projekt on osoba.id_osoba = osoba_projekt.osoba_id
where osoba_projekt.prirazeno_do is not NULL;


--Vypiš všechny oddělení, kde nikdo včera nevykázal práci


--Vypiš všechny osoby bez úkolů a úkoly bez přiřazených řešitelů v jedné tabulce


--Vypiš všechny nadřízené a kolik úkolů mají přiřazených jejich přímí podřízení
select
--o.id_osoba,
--o.jmeno,
--o.prijmeni,
n.jmeno as nadr_jmeno,
n.prijmeni as nadr_prijmeni,
count(u.id_ukol)
from osoba o 
join osoba n on n.id_osoba = o.nadrazeny_osoba_id --spojuju dvakrát tu samou tabulku, protože o osoby mám jejího nadřízeného jako ID, ale ta ID mají v té tabulce také jméno (proto rozděluju tabulku osoba o (osoba) a osoba n (nadřízený)
join ukol u on o.id_osoba = u.prirazenemu
group by nadr_jmeno, nadr_prijmeni;

--Vypiš všechny osoby, které mají vykázaný nadprůměrný počet hodin


