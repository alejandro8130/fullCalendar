<%-- 
    Document   : index
    Created on : 9/12/2016, 07:58:37 AM
    Author     : ficha1020611
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="controller.conectadb"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Reservas"%>
<%@page import="model.Ambientes"%>
<%@page import="model.Programaciones"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Ambientes> listarAmbientes = new ArrayList<>();
    ArrayList<Reservas> listarReservas = new ArrayList<>();
    try {
        try {
            conectadb con = new conectadb();
            Connection cnn = con.conectar();
            Statement stm = cnn.createStatement();
            String queryAmbientes = "SELECT Ambientes.id,Ambientes.ambiente,Programaciones.horaInicio,Programaciones.horaFin FROM Ambientes INNER JOIN Programaciones ON Ambientes.programacion_id=Programaciones.id;";
            System.out.println(queryAmbientes);
            ResultSet rs = stm.executeQuery(queryAmbientes);
            while (rs.next()) {
                Ambientes ambiente = new Ambientes();
                Programaciones programacionId = new Programaciones();
                int id = Integer.parseInt(rs.getString(1));
                String nombreAmbiente = rs.getString(2);
                programacionId.setHoraInicio(rs.getTime(3));
                programacionId.setHoraInicio(rs.getTime(4));

                ambiente.setId(id);
                ambiente.setAmbiente(nombreAmbiente);
                ambiente.setProgramacionId(programacionId);

                listarAmbientes.add(ambiente);

            }

            rs.close();
            cnn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");

        }
        conectadb con = new conectadb();
        Connection cnn = con.conectar();
        Statement stm = cnn.createStatement();
        String queryReservas = "SELECT Reservas.id,Reservas.nombre,Ambientes.ambiente,Reservas.fechaInicio,Reservas.fechaFinal FROM Reservas INNER JOIN Ambientes ON Reservas.ambiente_id=ambiente_id;";
        System.out.println(queryReservas);
        ResultSet rs = stm.executeQuery(queryReservas);
        while (rs.next()) {
            Reservas reserva = new Reservas();
            Ambientes ambienteId = new Ambientes();
            int id = Integer.parseInt(rs.getString(1));
            String nombre = rs.getString(2);
            ambienteId.setAmbiente(rs.getString(3));

            reserva.setId(id);
            reserva.setNombre(nombre);
            reserva.setAmbienteId(ambienteId);
            reserva.setFechaInicio(rs.getDate(4));
            reserva.setFechaFinal(rs.getDate(5));

            listarReservas.add(reserva);

        }

        rs.close();
        cnn.close();
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("error");

    }

%>
<!DOCTYPE html>
<html>
    <head>
        <title>Full Calendar</title>
        <link href='calendar/fullcalendar.min.css' rel='stylesheet' />
        <link href='calendar/fullcalendar.print.min.css' rel='stylesheet' media='print' />
        <link href='css/styles.css' rel='stylesheet' media='print' />
        <script src='lib/moment.min.js'></script>
        <script src='lib/jquery.min.js'></script>
        <script src='calendar/fullcalendar.min.js'></script>
        <script>

            $(document).ready(function () {

                $('#calendar').fullCalendar({
                    header: {
                        left: 'prev,next today',
                        center: 'title',
                        right: 'month,agendaWeek,agendaDay,listWeek'
                    },
                    defaultDate: '2016-12-12',
                    navLinks: true, // can click day/week names to navigate views
                    editable: true,
                    eventLimit: true, // allow "more" link when too many events
                    events: [
            <% for (Reservas c : listarReservas) {%>
                        {
                            id: <%= c.getId()%>,
                            title: '<%= c.getNombre()%>',
                            start: '<%= c.getFechaInicio()%>T16:00:00',
                            end: '<%= c.getFechaFinal()%>T20:00:00'
                        },
            <% }%>
                    ]
                });

            });

        </script>
        <style>

            body {
                margin: 40px 10px;
                padding: 0;
                font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
                font-size: 14px;
            }

            #calendar {
                max-width: 900px;
                margin: 0 auto;
            }

        </style>
    </head>
    <body>

        <table>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>Ambiente</th>
                    <th>Inicio</th>
                    <th>Final</th>
                </tr>
            </thead>
            <tbody>                            
                <% for (Reservas c : listarReservas) {%>
                <% int id = c.getId();%>
                <tr id="<%=id%>">
                    <td><%= c.getId()%></td>
                    <td><%= c.getNombre()%></td>
                    <td><%= c.getAmbienteId().getAmbiente()%></td>                   
                    <td><%= c.getFechaInicio()%></td>
                    <td><%= c.getFechaFinal()%></td>
                </tr>
                <% }%>

            </tbody>
        </table>

        <div id='calendar'></div>
    </body>
</html>
