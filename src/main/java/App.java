import com.google.gson.Gson;
import dao.Sql2oUserDao;
import dao.Sql2oNewsDao;
import dao.Sql2oDepartmentDao;
import models.Department;
import models.News;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;



public class App {
public static void main(String[] args){
    staticFileLocation("/public");
    Connection conn;
    String connectionString = "jdbc:postgresql://localhost:5432/newsapi";
    Sql2o sql2o = new Sql2o(connectionString, "moringa", "Wycky@1998");

    final Sql2oDepartmentDao departmentDao = new Sql2oDepartmentDao(sql2o);
    Sql2oNewsDao newsDao= new Sql2oNewsDao(sql2o);
    Sql2oUserDao userDao = new Sql2oUserDao(sql2o);
    conn = sql2o.open();
    Gson gson = new Gson();
    ProcessBuilder processBuilder = new ProcessBuilder();

    Integer port;

    if (processBuilder.environment().get("PORT") != null) {
        port = Integer.parseInt(processBuilder.environment().get("PORT"));
    } else {
        port = 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    port(port);

    get("/", (req,res)->{
        Map<String, Object> model = new HashMap<>();
        return new ModelAndView(model, "index.hbs");
    },new HandlebarsTemplateEngine());
    get("/Department/new", (req,res)->{
        Map<String, Object> model = new HashMap<>();
        return new ModelAndView(model, "departmentForm.hbs");
    },new HandlebarsTemplateEngine());

    post("/Department", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        String name = request.queryParams("name");
        String description = request.queryParams("description");
        int employees = Integer.parseInt(request.queryParams("employees"));
        Department newDepartment = new Department("Accounts", "Finance records",10);
        model.put("template", "public/templates/success.hbs");
        return new ModelAndView(model, "success.hbs");
    }, new HandlebarsTemplateEngine());
    get("/News/new", (req,res)->{
        Map<String, Object> model = new HashMap<>();
        return new ModelAndView(model, "NewsForm.hbs");
    },new HandlebarsTemplateEngine());
    post("/News", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        String content= request.queryParams("content");
        int departmentId = Integer.parseInt(request.queryParams("departmentId"));
        News news = new News("Four To be added to infra",3);
        model.put("template", "public/templates/success.hbs");
        return new ModelAndView(model, "success.hbs");
    }, new HandlebarsTemplateEngine());
    get("/Users/new", (req,res)->{
        Map<String, Object> model = new HashMap<>();
        return new ModelAndView(model, "UserForm.hbs");
    },new HandlebarsTemplateEngine());
    post("/Users", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        String name= request.queryParams("name");
        String position = request.queryParams("position");
        String role= request.queryParams("role");
        int departmentId = Integer.parseInt(request.queryParams("departmentId"));
        User user = new User("  Joh Doe","Lead Techie","Organize Infra",2);
        model.put("template", "public/templates/success.hbs");
        return new ModelAndView(model, "success.hbs");
    }, new HandlebarsTemplateEngine());



    post("/departments/new","application/json",(req, res) -> {
        Department department = gson.fromJson(req.body(), Department.class);
        departmentDao.add(department);
        res.status(201);
        res.type("application/json");
        return gson.toJson(department);
    });

    get("/departments/new","application/json",(req,res) ->{
        res.type("application/json");
        return gson.toJson(departmentDao.getAll());
    });

    post("/news/new","application/json", (req,res) ->{
        News news = gson.fromJson(req.body(), News.class);
        newsDao.add(news);
        res.status(201);
        res.type("application/json");
        return gson.toJson(news);
    });

    get("/news/new","application/json", (req,res) ->{
        res.type("application/json");
        return gson.toJson(newsDao.getAll());
    });

    post("/user/new", "application/json", (req,res) ->{
        User user = gson.fromJson(req.body(), User.class);
        userDao.add(user);
        res.status(201);
        res.type("application/json");
        return gson.toJson(user);
    });

    get("/user/new","application/json", (req,res) ->{
        res.type("application/json");
        return gson.toJson(userDao.getAll());
    });
}
}
