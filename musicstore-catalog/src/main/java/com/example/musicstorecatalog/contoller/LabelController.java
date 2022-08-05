package com.example.musicstorecatalog.contoller;

import com.example.musicstorecatalog.models.Label;
import com.example.musicstorecatalog.repository.AlbumRepository;
import com.example.musicstorecatalog.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LabelController {

    private LabelRepository repo;
    @Autowired
    public LabelController(LabelRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/labels")
    @ResponseStatus(HttpStatus.OK)
    public List<Label> getAllLabels() {
        return repo.findAll();
    }

    @GetMapping("/labels/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Label getLabel(@PathVariable Long id) {
        return repo.findById(id).get();
    }

    @PostMapping("/labels")
    @ResponseStatus(HttpStatus.CREATED)
    public Label createLabel(@RequestBody Label label) {
        return repo.save(label);
    }

    @PutMapping("/labels/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Label updateLabel(@PathVariable Long id, @RequestBody Label label) {
        return repo.save(label);
    }

    @DeleteMapping("/labels/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLabel(@PathVariable Long id) {
        repo.deleteById(id);
    }
    }


