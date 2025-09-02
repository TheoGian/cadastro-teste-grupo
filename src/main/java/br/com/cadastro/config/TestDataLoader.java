package br.com.cadastro.config;

import br.com.cadastro.model.Pessoa;
import br.com.cadastro.repository.PessoaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestDataLoader implements CommandLineRunner {

    private final PessoaRepository repo;

    public TestDataLoader(PessoaRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        Pessoa p = new Pessoa();
        p.setNome("João");
        p.setEmail("theo@email.com");
        repo.save(p);

        System.out.println("Pessoa salva: " + repo.findAll());
    }
}
