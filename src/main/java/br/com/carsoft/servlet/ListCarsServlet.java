package br.com.carsoft.servlet;

import br.com.carsoft.model.Car;
import br.com.carsoft.repository.CarRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/list-cars")
public class ListCarsServlet extends HttpServlet {
    private final CarRepository repository = new CarRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        List<Car> cars = repository.findAll();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='pt-BR'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Cars Registered</title>");
        out.println("<link rel='stylesheet' href='css/style.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Cars Registered</h2>");
        out.println("<ul>");
        for (Car car : cars) {
            out.println("<li>" + car.getName() + "</li>");
        }
        out.println("</ul>");
        out.println("<a href='index.html'>Back</a>");
        out.println("</body></html>");
    }
}
