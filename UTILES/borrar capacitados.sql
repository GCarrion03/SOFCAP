delete from capacitado where cedcapac in (select cedcapac from capacitado where idlider =200);  
delete from plaasis where cedcapac in (select cedcapac from capacitado where idlider =200);
delete from asistencia where cedcapac in (select cedcapac from capacitado where idlider =200);