package com.simples.api6.controller;

import com.simples.api6.model.Livro;
import com.simples.api6.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    public LivroRepository repository;

    @GetMapping
    public List<Livro> listarTodos(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Livro> exibirLivro(@PathVariable Long id){
        return repository.findById(id);
    }

    @PostMapping
    public String inserir(@RequestBody Livro livro){
        repository.save(livro);
        return "Inserido com sucesso!";
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id){
        if (repository.findById(id).isPresent()){
            repository.deleteById(id);
            return "Deletado com sucesso!!";
        }
        return "Livro não encotrado.";
    }

    @PutMapping("/{id}")
    public String atualizar(@PathVariable Long id, @RequestBody Livro livroAtualizado){
        Optional<Livro> livroAntigo = repository.findById(id);
        if (livroAntigo.isPresent()){
            Livro livro = livroAntigo.get();
            livro.setAutor(livroAtualizado.getAutor());
            livro.setNome(livroAtualizado.getNome());
            livro.setEditora(livroAtualizado.getEditora());
            repository.save(livro);
            return "Livro atualizado com sucesso!!";
        }
        return "Livro não encontrado.";
    }
}
