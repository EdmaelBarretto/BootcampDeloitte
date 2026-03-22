package com.deloitteportodigital.produtos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "produtos")
@Data                          // gera getters, setters, toString, equals, hashCode
@NoArgsConstructor             // construtor vazio (obrigatório para JPA)
@AllArgsConstructor            // construtor com todos os campos
@Builder                       // padrão Builder: Produto.builder().nome("x").build()
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, message = "Nome deve ter no mínimo 3 caracteres")
    @Column(nullable = false)
    private String nome;

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")
    @Column(nullable = false)
    private Double preco;

    @NotNull(message = "Estoque é obrigatório")
    @Min(value = 0, message = "O estoque não pode ser negativo")
    @Column(nullable = false)
    private Integer estoque;
}