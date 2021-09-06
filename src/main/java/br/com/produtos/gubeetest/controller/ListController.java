package br.com.produtos.gubeetest.controller;

import br.com.produtos.gubeetest.dto.Produto;
import br.com.produtos.gubeetest.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
public class ListController {

    @Autowired
    ProdutoRepository produtoRepository;

    @PostMapping("/produtos")
    public ResponseEntity<Produto> createProduto(@RequestBody Produto produto) {
        System.out.printf(produto.toString());
        produtoRepository.save(produto);
        return new ResponseEntity<>(produto, HttpStatus.CREATED);
    }

    @PutMapping("/produtos/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable("id") long id, @RequestBody Produto produto) {
        Optional<Produto> produtoUpdate = produtoRepository.findById(id);

        produtoUpdate.get().setNomeProduto(produto.getNomeProduto());
        return new ResponseEntity<>(produtoRepository.save(produto), HttpStatus.OK);
    }


    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<Produto> deleteProduto(@PathVariable("id") long id) {
        produtoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/produtos")
    public ResponseEntity<List<Produto>> getAllProdutos() {
        return new ResponseEntity<>(produtoRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable("id") Long id) {
        Optional<Produto> byId = produtoRepository.findById(id);
        System.out.printf("getID" + byId.get());
        Produto produto = byId.get();
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @GetMapping("/produtos/alvo/{alvo}")
    public ResponseEntity<List<Produto>> getAlvo(@PathVariable("alvo") String alvo) {
        List<Produto> byMercadoAlvoContaining = produtoRepository.findByMercadoAlvoContaining(alvo);
        return new ResponseEntity<>(byMercadoAlvoContaining, HttpStatus.OK);
    }

    @GetMapping("/produtos/tecnologia/{tec}")
    public ResponseEntity<List<Produto>> getTecnologia(@PathVariable("tec") String tec) {
        List<Produto> byMercadoAlvoContaining = produtoRepository.findByTecnologiasContaining(tec);
        return new ResponseEntity<>(byMercadoAlvoContaining, HttpStatus.OK);
    }


    @GetMapping("/produtos/filter")
    public ResponseEntity<List<Produto>> getAllProdutos(@RequestBody Produto request) {

        List<Produto> produtosFiltrados = produtoRepository.findAll().stream()
                .filter(produto -> produto.getMercadoAlvo().contains(request.getMercadoAlvo()))
                .filter(produto -> produto.getTecnologias().contains(request.getTecnologias()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(produtosFiltrados, HttpStatus.OK);



        }
    }

