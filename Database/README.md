# SQL Practice (DBeaver)

This folder contains my **SQL practice and exercises** from the *Czechitas Digital Academy: Testing*.  
The goal of these queries was to develop strong analytical thinking, understand data relationships, and learn how to validate data integrity — a key skill in QA testing.

---

## Topics covered

- Basic `SELECT`, `WHERE`, `ORDER BY`, `LIMIT`
- Filtering and logical operators (`AND`, `OR`, `IN`, `BETWEEN`, `LIKE`)
- Aggregate functions: `COUNT`, `AVG`, `SUM`, `MIN`, `MAX`
- `GROUP BY` and `HAVING`
- Conditional expressions (`CASE WHEN`)
- Table joins: `INNER`, `LEFT`, `RIGHT`, `FULL OUTER`
- Data merging with `UNION ALL`
- Handling missing data with `COALESCE`
- Self joins (managers and subordinates)
- Querying relationships across multiple tables

---

## 💾 Example query
```sql
-- Task: Display the average attendance hours per person, sorted ascending
SELECT
  o.jmeno,
  o.prijmeni,
  ROUND(AVG(d.hodiny), 2) AS "Average_hours"
FROM training.dochazka d
JOIN training.osoba o ON o.id_osoba = d.osoba_id
GROUP BY o.jmeno, o.prijmeni
ORDER BY "Average_hours" ASC;


