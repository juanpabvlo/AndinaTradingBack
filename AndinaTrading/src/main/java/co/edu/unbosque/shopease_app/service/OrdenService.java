package co.edu.unbosque.shopease_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.edu.unbosque.shopease_app.model.EmpresaModel;
import co.edu.unbosque.shopease_app.model.FacturaRequest;
import co.edu.unbosque.shopease_app.model.OrdenModel;
import co.edu.unbosque.shopease_app.model.UsuarioModel;
import co.edu.unbosque.shopease_app.repository.OrdenRepository;
import co.edu.unbosque.shopease_app.repository.UsuarioRepository;

@Service
public class OrdenService {

    // Inyectar los servicios de Empresa y Usuario
    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PdfCoService pdfCoService;  

    public OrdenModel crearOrden(OrdenModel orden) {
        // Guardar la orden en la base de datos
        OrdenModel nuevaOrden = ordenRepository.save(orden);

        UsuarioModel inversionista = usuarioRepository.findById(orden.getIdInversionista());
        if (inversionista != null) {
            String email = inversionista.getEmail();
            if (email != null) {
                // Generar el HTML del correo
                String htmlContent = generarHtmlConfirmacion(orden);
                // Enviar el correo de confirmación
                emailService.enviarCorreoHtml(email, "Confirmación de Orden de " + orden.getTipo(), htmlContent);
            }
        }

        return nuevaOrden;
    }

    public String generarHtmlConfirmacion(OrdenModel orden) {

        // Obtener la empresa asociada a la orden
        int idEmpresa = orden.getIdEmpresa();
        EmpresaModel empresa = empresaService.findById(idEmpresa);

        // Obtener el cliente (usuario) asociado a la orden
        int idCliente = orden.getIdInversionista();
        UsuarioModel cliente = usuarioService.findById(idCliente);

        // Crear la solicitud de la factura
        FacturaRequest facturaRequest = new FacturaRequest();
        facturaRequest.setOrden_id(String.valueOf(orden.getId())); // ID de la orden
        facturaRequest.setCliente(cliente.getNombre()+" "+ cliente.getApellido()); // Nombre del cliente
        facturaRequest.setAccion(empresa.getNombre()); // Nombre de la empresa o acción
        facturaRequest.setCantidad(orden.getCantidad());
        facturaRequest.setPrecio_unitario(orden.getPrecioAccion());
        facturaRequest.setTotal(orden.getTotal());
        facturaRequest.setTotal_factura(orden.getTotal()); // Total de la factura
        facturaRequest.setFecha(orden.getFechaCreacion().toString()); // Fecha de creación de la orden

        String pdfUrl = pdfCoService.generatePdfFromTemplate(facturaRequest);
        System.out.println("URL del PDF generado: " + pdfUrl);

        // Verificar si la URL de la factura fue generada correctamente
        if (pdfUrl != null) {
            StringBuilder contenidoHtml = new StringBuilder();
            contenidoHtml.append(
                    "<html><body style='font-family: Arial, sans-serif; color: #333; background-color: #f4f6f9; padding: 30px;'>");
            contenidoHtml.append(
                    "<div style='max-width: 600px; margin: auto; background: #ffffff; padding: 30px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1);'>");

            // Encabezado con logo
            contenidoHtml.append("<div style='text-align: center;'>");
            contenidoHtml.append(
                    "<img src='https://i.imgur.com/5XiB39P.png' alt='Logo AndinaTrading' style='width: 100px; margin-bottom: 20px;'/>");
            contenidoHtml.append("</div>");

            // Título principal
            contenidoHtml.append("<h2 style='color: rgb(31, 99, 158); text-align: center;'>Confirmación de "
                    + orden.getTipo().name().toUpperCase() + " de Acciones</h2>");

            // Descripción de la operación
            contenidoHtml.append(
                    "<p style='font-size: 16px; line-height: 1.6;'>Se ha realizado la siguiente operación:</p>");
            contenidoHtml.append("<ul style='font-size: 16px;'>");
            contenidoHtml
                    .append("<li><strong>Tipo de Orden:</strong> " + orden.getTipo().name().toUpperCase() + "</li>");
            contenidoHtml.append("<li><strong>Acción:</strong> " + empresa.getNombre() + "</li>");
            contenidoHtml.append("<li><strong>Cantidad:</strong> " + orden.getCantidad() + "</li>");
            contenidoHtml.append("<li><strong>Precio por Acción:</strong> $" + orden.getPrecioAccion() + "</li>");
            contenidoHtml.append("<li><strong>Total:</strong> $" + orden.getTotal() + "</li>");
            contenidoHtml.append("<li><strong>Fecha de Creación:</strong> " + orden.getFechaCreacion() + "</li>");
            contenidoHtml.append("</ul>");

            // Mensaje de agradecimiento
            contenidoHtml.append(
                    "<p style='font-size: 16px; line-height: 1.6;'>Gracias por realizar su operación con nosotros. Estamos comprometidos a ofrecerle el mejor servicio.</p>");

            // Botón para descargar la factura
            contenidoHtml.append("<div style='margin: 30px 0; text-align: center;'>");
            contenidoHtml.append("<a href='" + pdfUrl
                    + "' style='background-color: rgb(10, 96, 172); color: white; padding: 12px 25px; text-decoration: none; border-radius: 5px;'>Descargar Factura</a>");
            contenidoHtml.append("</div>");

            // Cierre del contenido HTML
            contenidoHtml.append(
                    "<p style='font-size: 16px;'>Si tienes alguna duda o necesitas asistencia, no dudes en escribirnos a <a href='mailto:soporte@andinatrading.com'>soporte@andinatrading.com</a>.</p>");
            contenidoHtml.append(
                    "<p style='font-size: 16px;'>Gracias por confiar en <strong>AndinaTrading</strong>.<br>Atentamente,<br>El equipo de AndinaTrading</p>");
            contenidoHtml.append("</div>");
            contenidoHtml.append("</body></html>");

            return contenidoHtml.toString();
        } else {
            // Si no se pudo generar la factura
            return "<p>Error al generar la factura. Por favor, intente nuevamente.</p>";
        }
    }

 
}
