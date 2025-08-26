package br.com.carsoft.servlet;

import br.com.carsoft.model.Car;
import br.com.carsoft.repository.CarRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create-car")
public class CreateCarServlet extends HttpServlet {
    private final CarRepository repository = new CarRepository();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String carName = request.getParameter("car-name");

        Car car = new Car(carName);
        repository.save(car);

        response.sendRedirect("list-cars");
    }
}


