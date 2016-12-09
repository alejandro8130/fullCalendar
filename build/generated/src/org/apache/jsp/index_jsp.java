package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import controller.conectadb;
import java.util.ArrayList;
import model.Reservas;
import model.Ambientes;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

    ArrayList<Reservas> listarReservas = new ArrayList<>();
    try {
        conectadb con = new conectadb();
        Connection cnn = con.conectar();
        Statement stm = cnn.createStatement();
        String queryReservas = "SELECT Reservas.nombre,Ambientes.ambiente,Reservas.fechaInicio,Reservas.fechaFinal FROM Reservas INNER JOIN Ambientes ON Reservas.ambiente_id=ambiente_id;";
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


      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>Full Calendar</title>\n");
      out.write("        <link href='calendar/fullcalendar.min.css' rel='stylesheet' />\n");
      out.write("        <link href='calendar/fullcalendar.print.min.css' rel='stylesheet' media='print' />\n");
      out.write("        <link href='css/styles.css' rel='stylesheet' media='print' />\n");
      out.write("        <script src='lib/moment.min.js'></script>\n");
      out.write("        <script src='lib/jquery.min.js'></script>\n");
      out.write("        <script src='calendar/fullcalendar.min.js'></script>\n");
      out.write("        <script>\n");
      out.write("\n");
      out.write("            $(document).ready(function () {\n");
      out.write("\n");
      out.write("                $('#calendar').fullCalendar({\n");
      out.write("                    header: {\n");
      out.write("                        left: 'prev,next today',\n");
      out.write("                        center: 'title',\n");
      out.write("                        right: 'month,agendaWeek,agendaDay,listWeek'\n");
      out.write("                    },\n");
      out.write("                    defaultDate: '2016-12-12',\n");
      out.write("                    navLinks: true, // can click day/week names to navigate views\n");
      out.write("                    editable: true,\n");
      out.write("                    eventLimit: true, // allow \"more\" link when too many events\n");
      out.write("                    events: [\n");
      out.write("            ");
 for (Reservas c : listarReservas) {
      out.write("\n");
      out.write("                        {\n");
      out.write("                            id: ");
      out.print( c.getId());
      out.write(",\n");
      out.write("                            title: '");
      out.print( c.getNombre());
      out.write("',\n");
      out.write("                            start: '");
      out.print( c.getFechaInicio());
      out.write("',\n");
      out.write("                            end: '");
      out.print( c.getFechaFinal());
      out.write("'\n");
      out.write("                        },\n");
      out.write("            ");
 }
      out.write("\n");
      out.write("                    ]\n");
      out.write("                });\n");
      out.write("\n");
      out.write("            });\n");
      out.write("\n");
      out.write("        </script>\n");
      out.write("        <style>\n");
      out.write("\n");
      out.write("            body {\n");
      out.write("                margin: 40px 10px;\n");
      out.write("                padding: 0;\n");
      out.write("                font-family: \"Lucida Grande\",Helvetica,Arial,Verdana,sans-serif;\n");
      out.write("                font-size: 14px;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            #calendar {\n");
      out.write("                max-width: 900px;\n");
      out.write("                margin: 0 auto;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("\n");
      out.write("        <table>\n");
      out.write("            <thead>\n");
      out.write("                <tr>\n");
      out.write("                    <th>Id</th>\n");
      out.write("                    <th>Nombre</th>\n");
      out.write("                    <th>Ambiente</th>\n");
      out.write("                    <th>Inicio</th>\n");
      out.write("                    <th>Final</th>\n");
      out.write("                </tr>\n");
      out.write("            </thead>\n");
      out.write("            <tbody>                            \n");
      out.write("                ");
 for (Reservas c : listarReservas) {
      out.write("\n");
      out.write("                ");
 int id = c.getId();
      out.write("\n");
      out.write("                <tr id=\"");
      out.print(id);
      out.write("\">\n");
      out.write("                    <td>");
      out.print( c.getId());
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( c.getNombre());
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( c.getAmbienteId().getAmbiente());
      out.write("</td>                   \n");
      out.write("                    <td>");
      out.print( c.getFechaInicio());
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( c.getFechaFinal());
      out.write("</td>\n");
      out.write("                </tr>\n");
      out.write("                ");
 }
      out.write("\n");
      out.write("\n");
      out.write("            </tbody>\n");
      out.write("        </table>\n");
      out.write("\n");
      out.write("        <div id='calendar'></div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
