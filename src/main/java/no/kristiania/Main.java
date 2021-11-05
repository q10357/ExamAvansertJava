package no.kristiania;

import no.kristiania.database.Datasource;
import no.kristiania.database.daos.AnswerOptionDao;
import no.kristiania.database.daos.QuestionDao;
import no.kristiania.http.HttpServer;
import no.kristiania.http.controllers.AddOptionController;
import no.kristiania.http.controllers.AddQuestionController;
import no.kristiania.http.controllers.QuestionOptionsController;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        DataSource dataSource = Datasource.createDataSource();
        HttpServer server = new HttpServer( 3000, dataSource);
        QuestionDao qDao = new QuestionDao(dataSource);
        AnswerOptionDao aoDao = new AnswerOptionDao(dataSource);
        server.setRoot(Paths.get("src/main/resources"));
        server.addController("/api/newQuestion", new AddQuestionController(qDao));
        server.addController("/api/questionOptions", new QuestionOptionsController(qDao));
        server.addController("/api/alternativeAnswers", new AddOptionController(aoDao));
        server.start();

    }
}
