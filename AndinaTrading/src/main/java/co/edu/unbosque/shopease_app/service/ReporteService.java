// src/main/java/co/edu/unbosque/shopease_app/service/ReporteService.java
package co.edu.unbosque.shopease_app.service;

import co.edu.unbosque.shopease_app.model.InversionistaModel;
import co.edu.unbosque.shopease_app.model.UsuarioModel;
import co.edu.unbosque.shopease_app.repository.InversionistaRepository;
import co.edu.unbosque.shopease_app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReporteService {

    @Autowired
    private InversionistaRepository inversionistaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailService emailService;

    public void enviarReporteBalance(int idInversionista) {
        InversionistaModel inversionista = inversionistaRepository.findById(idInversionista).orElse(null);

        if (inversionista != null) {
            UsuarioModel usuario = usuarioRepository.findById(idInversionista); // Asumimos relación 1:1 por ID
            if (usuario != null) {
                String htmlContent = generarHtmlReporte(inversionista, usuario);
                emailService.enviarCorreoHtml(usuario.getEmail(), "Reporte de Balance General", htmlContent);
            }
        }
    }

    public String generarHtmlReporte(InversionistaModel inversionista, UsuarioModel usuario) {
        String nombreCompleto = usuario.getNombre() + " " + usuario.getApellido();

        StringBuilder contenidoHtml = new StringBuilder();
        contenidoHtml.append(
                "<html><body style='font-family: Arial, sans-serif; color: #333; background-color: #f4f6f9; padding: 30px;'>");
        contenidoHtml.append(
                "<div style='max-width: 600px; margin: auto; background: #ffffff; padding: 30px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1);'>");

        // Logo
        contenidoHtml.append("<div style='text-align: center;'>");
        contenidoHtml.append("<img src='https://i.imgur.com/5XiB39P.png' alt='Logo AndinaTrading' style='width: 100px; margin-bottom: 20px;'/>");
        contenidoHtml.append("</div>");

        // Título
        contenidoHtml.append("<h2 style='color: rgb(31, 99, 158); text-align: center;'>Reporte de Balance General</h2>");

        // Contenido
        contenidoHtml.append("<p style='font-size: 16px;'>Hola <strong>" + nombreCompleto + "</strong>,</p>");
        contenidoHtml.append("<p style='font-size: 16px;'>Este es tu balance actualizado:</p>");
        contenidoHtml.append("<ul style='font-size: 16px;'>");
        contenidoHtml.append("<li><strong>Saldo Actual:</strong> $" + inversionista.getSaldo() + "</li>");
        contenidoHtml.append("<li><strong>Saldo Anterior:</strong> $" + inversionista.getSaldo_anterior() + "</li>");
        contenidoHtml.append("<li><strong>Fecha de Registro:</strong> " + inversionista.getFecha_registro() + "</li>");
        contenidoHtml.append("</ul>");

        // Footer
        contenidoHtml.append("<p style='font-size: 16px;'>Gracias por confiar en <strong>AndinaTrading</strong>.<br>Atentamente,<br>El equipo de AndinaTrading</p>");
        contenidoHtml.append("</div></body></html>");

        return contenidoHtml.toString();
    }
}
