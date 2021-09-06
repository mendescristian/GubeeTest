package br.com.produtos.gubeetest.dto;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "produtos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeProduto;
    private String descricao;
    private String mercadoAlvo;
    private String tecnologias;

}
