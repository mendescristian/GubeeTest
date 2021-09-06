package br.com.produtos.gubeetest.repository;

import br.com.produtos.gubeetest.dto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByMercadoAlvoContaining(String mercadoAlvo);
    List<Produto> findByTecnologiasContaining(String tecnologias);

}
