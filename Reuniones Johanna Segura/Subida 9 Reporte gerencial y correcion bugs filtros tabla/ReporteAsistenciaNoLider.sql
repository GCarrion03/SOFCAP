USE [SOFCAP]
GO
/****** Object:  View [dbo].[reporteAsistenciaNoLider]    Script Date: 01/09/2014 16:22:17 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE view [dbo].[reporteAsistenciaNoLider]
as
select pa.id_plantilla,tm.codtema,tm.nomtema,cap.ceco,count(cap.ceco) planificados,asistPlanif.asistentes asistentes,pc.fecha_inicio,pc.fecha_fin
 ,cap.turno from plaasis pa join plantilla_curso pc on pa.id_plantilla=pc.id_plantilla 
join tema tm on pc.codtema=tm.codtema
join capacitado cap on pa.cedcapac=cap.cedcapac left join asistencia asi on asi.cedcapac=pa.cedcapac and asi.id_plantilla=pa.id_plantilla 
left join 
(select pa.id_plantilla,pc.codtema,cap.ceco,count(cap.ceco) asistentes,cap.turno turno
 from plaasis pa join plantilla_curso pc on pa.id_plantilla=pc.id_plantilla 
join capacitado cap on pa.cedcapac=cap.cedcapac inner join asistencia asi on asi.cedcapac=pa.cedcapac and asi.id_plantilla=pa.id_plantilla 
group by cap.ceco,pa.id_plantilla,pc.codtema,cap.turno) asistPlanif on asistPlanif.id_plantilla=pa.id_plantilla and asistPlanif.codtema=pc.codtema
and asistPlanif.ceco=cap.ceco and asistPlanif.turno=cap.turno
group by cap.ceco,pa.id_plantilla,pc.codtema,tm.nomtema,asistPlanif.asistentes,cap.turno,pc.fecha_inicio,pc.fecha_fin,tm.codtema;
