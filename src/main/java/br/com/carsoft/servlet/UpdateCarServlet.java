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

@WebServlet("/update-car")
public class UpdateCarServlet extends HttpServlet {
    private final CarRepository repository = new CarRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Car car = repository.findById(id);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Edit Car</h2>");
        out.println("<form method='post' action='update-car'>");
        out.println("<input type='hidden' name='id' value='" + car.getId() + "'/>");
        out.println("Name: <input type='text' name='car-name' value='" + car.getName() + "'/> <br>");
        out.println("<button type='submit'>Update</button>");
        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("car-name");

        Car car = new Car();
        car.setId(id);
        car.setName(name);

        repository.update(car);
        response.sendRedirect("list-cars");
    }
}
