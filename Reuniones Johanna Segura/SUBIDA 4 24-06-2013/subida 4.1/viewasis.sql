USE [SOFCAP]
GO
/****** Object:  View [dbo].[reporteAsistencia]    Script Date: 06/30/2013 22:00:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER view [dbo].[reporteAsistencia]
as
select pa.id_plantilla,tm.codtema,tm.nomtema,cap.ceco,count(cap.ceco) planificados,asistPlanif.asistentes asistentes,pc.fecha_inicio,pc.fecha_fin
 ,cap.turno,lid.idlider,lid.cargo from plaasis pa join plantilla_curso pc on pa.id_plantilla=pc.id_plantilla 
join tema tm on pc.codtema=tm.codtema
join capacitado cap on pa.cedcapac=cap.cedcapac left join asistencia asi on asi.cedcapac=pa.cedcapac and asi.id_plantilla=pa.id_plantilla 
left join lider lid on lid.idlider=cap.idlider
left join 
(select pa.id_plantilla,pc.codtema,cap.ceco,count(cap.ceco) asistentes,cap.turno turno,lid.idlider idlider
 from plaasis pa join plantilla_curso pc on pa.id_plantilla=pc.id_plantilla 
join capacitado cap on pa.cedcapac=cap.cedcapac inner join asistencia asi on asi.cedcapac=pa.cedcapac and asi.id_plantilla=pa.id_plantilla 
join lider lid on lid.idlider=cap.idlider
group by cap.ceco,pa.id_plantilla,pc.codtema,cap.turno,lid.idlider) asistPlanif on asistPlanif.id_plantilla=pa.id_plantilla and asistPlanif.codtema=pc.codtema
and asistPlanif.ceco=cap.ceco and asistPlanif.turno=cap.turno
and asistPlanif.idlider=lid.idlider 
group by cap.ceco,pa.id_plantilla,pc.codtema,tm.nomtema,asistPlanif.asistentes,cap.turno,pc.fecha_inicio,pc.fecha_fin,tm.codtema,lid.idlider,lid.cargo;

