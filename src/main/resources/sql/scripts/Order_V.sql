CREATE VIEW IF NOT EXISTS Order_V
as
select o.*,
c.CompanyName CustomerCompanyName,
e.FirstName || ' ' ||  e.LastName EmployeeFullName,
s.CompanyName ShipCompanyName
from "Order" o, Employee e, Customer c, Shipper s
where o.EmployeeId = e.Id
and o.CustomerId = c.id
and o.ShipVia = s.id;